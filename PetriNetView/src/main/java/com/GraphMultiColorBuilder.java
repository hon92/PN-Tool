/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.marking.Marking;

/**
 *
 * @author Honza
 */
public class GraphMultiColorBuilder extends AGraphVizBuilder
{

    private int counter = 0;
    private final Map<Marking, Integer> clusterInfoMap = new HashMap<>();
    private final StringBuilder tb = new StringBuilder();
    private final Map<String, Cluster> clusters = new HashMap<>();

    private class Cluster
    {

        public int id;
        public String marking;
        public String fromMarking;
        public List<String> colors;

        public Cluster()
        {
            colors = new ArrayList<>();
        }

    }

    public GraphMultiColorBuilder(MarkingGraph markingGraph)
    {
        super(markingGraph);
    }

    @Override
    public void writeStart()
    {
        super.writeStart();
        sb.append("compound=true;\n");
    }

    private void writeLabelInfo(int transitionId, Marking to)
    {
        tb.append("[label=");
        tb.append(transitionId);
        tb.append(";lhead=cluster");
        tb.append(clusterInfoMap.get(to));
        tb.append("]");
    }

    private void writeTempMarking(Marking m)
    {
        tb.append("\"(");
        tb.append(m.toString());
        tb.append(")\"");
    }

    private void writeTempArrow()
    {
        tb.append(" -> ");
    }

    private void writeTempEndLine()
    {
        tb.append(";\n");
    }

    @Override
    public void writeNodesAndEdges()
    {
        for (Map.Entry<Marking, List<MarkingGraph.Edge>> entry : markingGraph.getGraph().entrySet())
        {
            for (MarkingGraph.Edge edge : entry.getValue())
            {
                if (!clusters.containsKey(edge.toMarking.toString()))
                {
                    Cluster c = new Cluster();
                    c.id = counter;
                    c.marking = edge.toMarking.toString();
                    for (int i = 0; i < edge.colors.size(); i++)
                    {
                        c.colors.add(colorConverter(edge.colors.get(i)));
                    }
                    clusters.put(edge.toMarking.toString(), c);
                    clusterInfoMap.put(edge.toMarking, counter);
                    counter++;

                }
                else
                {
                    Cluster c = clusters.get(edge.toMarking.toString());

                    if (c.colors.size() < edge.colors.size())
                    {
                        c.colors.clear();
                        for (int i = 0; i < edge.colors.size(); i++)
                        {
                            c.colors.add(colorConverter(edge.colors.get(i)));
                        }
                    }
                }
                writeTempMarking(entry.getKey());
                writeTempArrow();
                writeTempMarking(edge.toMarking);
                writeLabelInfo(edge.transition.getId(), edge.toMarking);
                writeTempEndLine();
            }
        }

        for (Map.Entry<String, Cluster> entry : clusters.entrySet())
        {
            Cluster c = entry.getValue();
            writeCluster(c);
        }
        sb.append(tb);
    }

    private void writeCluster(Cluster cluster)
    {
        sb.append("subgraph cluster");
        sb.append(cluster.id);
        sb.append("\n{\n\t");
        sb.append("node[style=filled, fillcolor=gray];\n\t");
        sb.append("style=striped;\n\t");
        String colorsString = "";
        colorsString = String.join(":", cluster.colors);
        sb.append("fillcolor=\"");
        sb.append(colorsString);
        sb.append("\";\n\t");
        sb.append("\"(");
        sb.append(cluster.marking);
        sb.append(")\";\n");
        sb.append("}\n");
    }

    private String colorConverter(Color color)
    {
        return String.format("#%06X", (0xFFFFFF & color.getRGB()));
    }

}
