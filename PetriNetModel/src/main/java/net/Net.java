package net;

import java.util.ArrayList;
import java.util.List;
import net.marking.Marking;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Honza
 */
public class Net
{

    private final String DEFAULTNAME = "UNKNOWN";
    private final String name;
    private List<Place> places = new ArrayList<>();
    private List<Transition> transitions = new ArrayList<>();
    private List<Arc> arcs = new ArrayList<>();

    public Net()
    {
        this.name = DEFAULTNAME;
    }

    public Net(String name)
    {
        if (name.equals(""))
        {
            this.name = DEFAULTNAME;
        }
        else
        {
            this.name = name;
        }
    }

    public Net clone() throws CloneNotSupportedException
    {
        Net cloneNet = new Net(this.name);
        for (Place p : places)
        {
            cloneNet.addPlace((Place) p.clone());
        }
        for (Transition t : transitions)
        {
            cloneNet.addTransition((Transition) t.clone());
        }

        for (Arc a : arcs)
        {
            Arc newArc = (Arc) a.clone();
            int tId = newArc.getTransition().getId();
            int pId = newArc.getPlace().getId();
            for (Transition t : cloneNet.getTransitions())
            {
                if (t.getId() == tId)
                {
                    newArc.setTransition(t);
                    break;
                }
            }

            for (Place p : cloneNet.getPlaces())
            {
                if (p.getId() == pId)
                {
                    newArc.setPlace(p);
                    break;
                }
            }
            cloneNet.addArc(newArc);
        }
        cloneNet.setMarking(getMarking());

        return cloneNet;
    }

    public String getName()
    {
        return name;
    }

    public void setPlaces(List<Place> places)
    {
        this.places = places;
    }

    public void setTransitions(List<Transition> transitions)
    {
        this.transitions = transitions;
    }

    public void setArcs(List<Arc> arcs)
    {
        this.arcs = arcs;
    }

    public List<Transition> getTransitions()
    {
        return this.transitions;
    }

    public List<Place> getPlaces()
    {
        return this.places;
    }

    public List<Arc> getArcs()
    {
        return this.arcs;
    }

    public void addPlace(Place place)
    {
        this.places.add(place);
    }

    public void addTransition(Transition transition)
    {
        this.transitions.add(transition);
    }

    public void addArc(Arc arc)
    {
        this.arcs.add(arc);
    }

    public void removePlace(Place place)
    {
        List<Arc> remove = new ArrayList<>();
        for (Arc a : arcs)
        {
            if (a.getPlace().getId() == place.getId())
            {
                remove.add(a);
            }
        }
        arcs.removeAll(remove);
        this.places.remove(place);
    }

    public void removeTransition(Transition transition)
    {
        List<Arc> remove = new ArrayList<>();
        for (Arc a : arcs)
        {
            if (a.getTransition().getId() == transition.getId())
            {
                remove.add(a);
            }
        }
        arcs.removeAll(remove);
        this.transitions.remove(transition);
    }

    public void removeArc(Arc arc)
    {
        List<Arc> remove = new ArrayList<>();
        for (Arc a : arcs)
        {
            if (a.getId() == arc.getId())
            {
                remove.add(a);
            }
        }
        arcs.removeAll(remove);
        this.arcs.remove(arc);
    }

    public void setMarking(Marking marking)
    {
        for (Place p : places)
        {
            p.setTokensCount(marking.get(p.getId()));
        }
    }

    public Marking getMarking()
    {
        Marking m = new Marking();
        for (Place p : places)
        {
            m.putMapping(p.getId(), p.getTokensCount());
        }
        return m;
    }

    public List<Arc> getTransitionInputArc(Transition t)
    {
        List<Arc> inputArcs = new ArrayList<>();
        for (Arc a : arcs)
        {
            if (a.getArcType() == ArcType.INPUT && a.getTransition().getId() == t.getId())
            {
                inputArcs.add(a);
            }
        }
        return inputArcs;
    }

    public List<Arc> getTransitionOutputArc(Transition t)
    {
        List<Arc> outputArcs = new ArrayList<>();

        for (Arc a : arcs)
        {
            if (a.getArcType() == ArcType.OUTPUT && a.getTransition().getId() == t.getId())
            {
                outputArcs.add(a);
            }
        }
        return outputArcs;
    }

    public List<Arc> getTransitionInhibitionArc(Transition t)
    {
        List<Arc> inhibitionArcs = new ArrayList<>();
        for (Arc a : arcs)
        {
            if (a.getArcType() == ArcType.INHIBITION && a.getTransition().getId() == t.getId())
            {
                inhibitionArcs.add(a);
            }
        }
        return inhibitionArcs;
    }

}
