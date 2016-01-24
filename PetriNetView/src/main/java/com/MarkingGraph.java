/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import net.Transition;
import net.marking.Marking;

/**
 *
 * @author Honza
 */
public class MarkingGraph
{

    private final Marking initialMarking;
    private final Map<Marking, List<Edge>> graph = new HashMap<>();

    public MarkingGraph(Marking initial)
    {
        initialMarking = initial;
    }

    public Marking getInitialMarking()
    {
        return initialMarking;
    }

    public boolean containsMarking(Marking m)
    {
        Queue<Marking> markings = new ArrayDeque<>();
        markings.add(initialMarking);

        while (!markings.isEmpty())
        {
            Marking marking = markings.poll();
            if (marking.equals(m))
            {
                return true;
            }
            List<Edge> edges = graph.get(marking);
            if (edges != null)
            {
                for (Edge e : edges)
                {
                    markings.add(e.toMarking);
                }
            }
        }

        return false;
    }

    public boolean getPath(Marking root, Marking marking, List<Marking> markings, Set<Marking> uniqueMarkings)
    {
        uniqueMarkings.add(root);
        if (root.equals(marking))
        {
            markings.add(root);
            return true;
        }

        for (Edge e : graph.get(root))
        {
            if (!uniqueMarkings.contains(e.toMarking))
            {
                if (getPath(e.toMarking, marking, markings, uniqueMarkings))
                {
                    markings.add(0, root);
                    return true;
                }
            }
        }
        return false;
    }

    public Marking getCoveringMarking(Marking newMarking)
    {
        Queue<Marking> tempQ = new ArrayDeque<>();
        tempQ.add(initialMarking);
        Marking resultMarking = newMarking;
        List<Marking> markingsOnPath = new ArrayList<>();

        while (!tempQ.isEmpty())
        {
            Marking marking = tempQ.poll();
            if (marking.equals(newMarking))
            {
                break;
            }
            else
            {
                markingsOnPath.add(marking);
            }

            List<Edge> edges = graph.get(marking);
            if (edges != null)
            {
                for (Edge e : edges)
                {
                    tempQ.add(e.toMarking);
                }
            }
        }

        for (Marking m : markingsOnPath)
        {
            resultMarking = m.coverBy(resultMarking);
        }

        return resultMarking;
    }

    public Map<Marking, List<Edge>> getGraph()
    {
        return graph;
    }

    static class Edge
    {

        public final Transition transition;
        public final Marking toMarking;
        public List<Color> colors;

        public Edge(Transition transition, Marking toMarking, Color color)
        {
            this.transition = transition;
            this.toMarking = toMarking;
            this.colors = new ArrayList<>();
            this.colors.add(color);
        }

        public void addColor(Color color)
        {
            this.colors.add(color);
        }

    }

    public void addEdge(Marking from, Marking to, Transition t, Color color)
    {
        if (graph.containsKey(from))
        {
            List<Edge> edges = graph.get(from);

            for (Edge e : edges)
            {
                if (e.transition.getId() == t.getId() && e.toMarking.equals(to))
                {
                    e.addColor(color);
                    return;
                }

            }
            edges.add(new Edge(t, to, color));
        }
    }

    public void addNode(Marking next)
    {
        graph.put(next, new ArrayList<>());
    }

    public void print()
    {
        for (Map.Entry<Marking, List<MarkingGraph.Edge>> e : graph.entrySet())
        {
            System.out.println("from " + e.getKey());
            for (Edge ed : e.getValue())
            {
                System.out.println("    to " + ed.toMarking + " using " + ed.transition.getId());
            }
        }

    }

    public String build()
    {
        return new GraphMultiColorBuilder(this).build();
    }
}
