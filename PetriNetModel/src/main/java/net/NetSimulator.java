/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.util.ArrayList;
import java.util.List;
import net.marking.Marking;

/**
 *
 * @author Honza
 */
public class NetSimulator
{

    private final Marking initialMarking;
    private final Net net;

    public NetSimulator(Net net)
    {
        this.net = net;
        initialMarking = net.getMarking();
    }

    public void fireTransition(Transition t)
    {
        List<Arc> outputArcs = net.getTransitionOutputArc(t);
        List<Arc> inputArcs = net.getTransitionInputArc(t);

        for (Arc a : inputArcs)
        {
            if (a.getPlace().getTokensCount() != Integer.MAX_VALUE)
            {
                a.getPlace().removeTokens(a.getCardinality());
            }
        }

        for (Arc a : outputArcs)
        {
            if (a.getPlace().getTokensCount() != Integer.MAX_VALUE)
            {
                a.getPlace().addTokens(a.getCardinality());
            }
        }
    }

    public boolean canFireTransition(Transition t)
    {
        List<Arc> outputArcs = net.getTransitionInputArc(t);
        List<Arc> inhibitionArcs = net.getTransitionInhibitionArc(t);

        for (Arc a : outputArcs)
        {
            if (a.getPlace().getTokensCount() < a.getCardinality())
            {
                return false;
            }
        }

        for (Arc a : inhibitionArcs)
        {
            if (a.getPlace().getTokensCount() >= a.getCardinality())
            {
                return false;
            }
        }

        return true;
    }

    public List<Transition> getAvailableTransitions()
    {
        List<Transition> availableTransitions = new ArrayList<>();
        for (Transition t : net.getTransitions())
        {
            if (canFireTransition(t))
            {
                availableTransitions.add(t);
            }
        }
        return availableTransitions;
    }

    public Marking getInitialMarking()
    {
        return initialMarking;
    }

    public Net getNet()
    {
        return net;
    }

    public void setMarking(Marking marking)
    {
        net.setMarking(marking);
    }
}
