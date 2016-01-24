/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import net.Net;
import net.NetSimulator;
import net.Transition;
import net.marking.Marking;

/**
 *
 * @author Honza
 */
public class ReachibilitySet
{

    private final Net net;
    private final NetSimulator simulator;
    private final List<AlgorithmModel> algorithms = new ArrayList<>();

    public ReachibilitySet(Net net)
    {
        this.net = net;
        this.simulator = new NetSimulator(net);
    }

    public void addAlgoritmus(AlgorithmModel alg)
    {
        algorithms.add(alg);
    }

    public Set<Marking> getReachibilitySet() // not working
    {
        Set<Marking> reachibilitySet = new HashSet<>();
        Marking initialMarking = net.getMarking();
        reachibilitySet.add(initialMarking);

        boolean newFound = true;
        while (newFound)
        {
            Set<Marking> newToAdd = new HashSet<>();

            for (Marking m : reachibilitySet)
            {
                newFound = false;
                simulator.setMarking(m);
                List<Transition> avaT = simulator.getAvailableTransitions();
                for (Transition t : avaT)
                {
                    Marking newM = tryTransition(m, t);
                    if (!reachibilitySet.contains(newM))
                    {
                        newToAdd.add(newM);
                        newFound = true;
                    }
                }
            }
            reachibilitySet.addAll(newToAdd);
        }

        return reachibilitySet;
    }

    public MarkingGraph getGraph()
    {
        Marking m0 = net.getMarking();
        MarkingGraph graph = new MarkingGraph(m0);
        Queue<Marking> newMarkings = new ArrayDeque<>();
        newMarkings.add(m0);
        graph.addNode(m0);

        while (!newMarkings.isEmpty())
        {
            Marking m = newMarkings.poll();
            simulator.setMarking(m);

            for (AlgorithmModel alg : algorithms)
            {

                List<Transition> ts = alg.algorithm.getAmpleSet(simulator);
                if (ts.isEmpty())
                {
                    continue;
                }
                for (Transition t : ts)
                {
                    Marking mm = tryTransition(m, t);
                    List<Marking> path2 = new ArrayList<>();
                    graph.getPath(m0, m, path2, new HashSet<>());

                    for (Marking mpp : path2)
                    {
                        mm = mpp.coverBy(mm);
                    }

                    if (!graph.getGraph().containsKey(mm))
                    {
                        graph.addNode(mm);
                        newMarkings.add(mm);
                    }
                    graph.addEdge(m, mm, t, alg.color);
                }
            }
        }
        net.setMarking(m0);
        return graph;
    }

    public MarkingGraph getGraph_OLD()
    {
        Marking initial = net.getMarking();
        Queue<Marking> q = new ArrayDeque<>();
        Queue<Marking> temp = new ArrayDeque<>();
        Set<Marking> finalMarks = new HashSet<>();
        q.add(initial);
        temp.add(initial);
        finalMarks.add(initial);

        MarkingGraph g = new MarkingGraph(initial);

        while (!q.isEmpty())
        {
            Marking next = q.poll();
            Marking te = temp.poll();
            net.setMarking(te);

            g.addNode(next);

            for (AlgorithmModel alg : algorithms)
            {
                List<Transition> available = alg.algorithm.getAmpleSet(simulator);
                Color color = alg.color;

                if (available.isEmpty())
                {
                    continue;
                }

                for (Transition t : available)
                {
                    Marking newMarking = tryTransition(next, t);
                    Marking tem = new Marking(newMarking.getMap());
                    Marking marking = setPrevInfinity(next, newMarking);

                    Marking coveringMarking = null;

                    for (Marking m : finalMarks)
                    {
                        if (isCovering(marking, m))
                        {
                            coveringMarking = coveringMarking(marking, m);
                        }
                    }

                    if (coveringMarking == null)
                    {
                        coveringMarking = marking;
                    }
                    if (!finalMarks.contains(coveringMarking))
                    {
                        q.add(coveringMarking);
                        temp.add(tem);
                        finalMarks.add(coveringMarking);
                    }
                    g.addEdge(next, coveringMarking, t, color);
                }

            }
        }

        net.setMarking(initial);
        return g;
    }

    private Marking tryTransition(Marking original, Transition t)
    {
        simulator.fireTransition(t);
        Marking resultMarking = net.getMarking();
        net.setMarking(original);
        return resultMarking;
    }

    public boolean isCovering(Marking m1, Marking m2) //m1 pokryva m2 -> m1 > m2
    {
        for (Map.Entry<Integer, Integer> m : m1.getMap().entrySet())
        {
            int key = m.getKey();
            int val = m.getValue();

            if (m2.getMap().get(key) > val)
            {
                return false;
            }

        }
        return true;
    }

    public Marking coveringMarking(Marking marking, Marking prevMark) //marking > prevMark
    {
        Marking newM = new Marking();
        boolean covering = true;
        for (Map.Entry<Integer, Integer> m : marking.getMap().entrySet())
        {
            int key = m.getKey();
            int val = m.getValue();

            if (prevMark.get(key) > val)
            {
                covering = false;
            }

            if (prevMark.get(key) < val && covering)
            {
                newM.putMapping(key, Integer.MAX_VALUE);
            }
            else
            {
                newM.putMapping(key, val);
            }

        }
        if (!covering)
        {
            return marking;
        }
        return newM;
    }

    public Marking setPrevInfinity(Marking prev, Marking newMarking)
    {
        Marking newMark = new Marking();
        for (Map.Entry<Integer, Integer> e : prev.getMap().entrySet())
        {
            int prevVal = e.getValue();
            int prevKey = e.getKey();
            int newVal = newMarking.getMap().get(prevKey);
            if (prevVal == Integer.MAX_VALUE)
            {
                newMark.putMapping(prevKey, Integer.MAX_VALUE);
            }
            else
            {
                newMark.putMapping(prevKey, newVal);
            }

        }
        return newMark;
    }

}
