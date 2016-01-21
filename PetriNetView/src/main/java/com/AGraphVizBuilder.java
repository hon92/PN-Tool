/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import net.Transition;
import net.marking.Marking;

/**
 *
 * @author Honza
 */
public abstract class AGraphVizBuilder
{

    protected MarkingGraph markingGraph;
    public StringBuilder sb = new StringBuilder();
    private final String GRAPH_NAME = "G";

    public AGraphVizBuilder(MarkingGraph markingGraph)
    {
        this.markingGraph = markingGraph;
    }

    public abstract void writeNodesAndEdges();

    public final String build()
    {
        writeStart();
        writeInitialMarking(markingGraph.getInitialMarking());
        writeNodesAndEdges();
        writeEnd();
        return sb.toString();
    }

    public void writeStart()
    {
        sb.append("digraph " + GRAPH_NAME + " {\n");
    }

    public void writeEnd()
    {
        sb.append("\n}");
    }

    public void writeInitialMarking(Marking m)
    {
        sb.append("\"(");
        sb.append(m.toString());
        sb.append(")\"");
        sb.append("[fillcolor=grey, style=filled];\n");
    }

    public void writeMarking(Marking m)
    {
        sb.append("\"(");
        sb.append(m.toString());
        sb.append(")\"");
    }

    public void writeArrow()
    {
        sb.append(" -> ");
    }

    public void writeEndLine()
    {
        sb.append(";\n");
    }

    public void writeTransition(Transition t)
    {
        sb.append("[label=");
        sb.append(t.getId());
        sb.append("]");
    }

}
