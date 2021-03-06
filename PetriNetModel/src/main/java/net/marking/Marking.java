/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.marking;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Honza
 */
public class Marking
{

    private final HashMap<Integer, Integer> mapping; // key -> place ID, value -> token count

    public Marking()
    {
        mapping = new HashMap<>();
    }

    public Marking(HashMap<Integer, Integer> initialMarking)
    {
        mapping = initialMarking;
    }

    public void putMapping(int placeId, int tokensCount)
    {
        this.mapping.put(placeId, tokensCount);
    }

    public void popMarking(int placeId)
    {
        this.mapping.remove(placeId);
    }

    public int get(int placeId)
    {
        return mapping.get(placeId);
    }

    public HashMap<Integer, Integer> getMap()
    {
        return mapping;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj != null && obj instanceof Marking)
        {
            Marking m = (Marking) obj;
            return (m.getMap().equals(getMap()));
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.mapping);
        return hash;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        int size = mapping.size();
        int i = 0;
        for (Integer val : mapping.values())
        {

            if (i + 1 < size)
            {
                if (val == Integer.MAX_VALUE)
                {
                    sb.append("w");
                }
                else
                {
                    sb.append(val);
                }
                sb.append(", ");
            }
            else
            {
                if (val == Integer.MAX_VALUE)
                {
                    sb.append("w");
                }
                else
                {
                    sb.append(val);
                }
            }
            i++;

        }

        return sb.toString();

    }

    public boolean isCoveringBy(Marking marking) // m > this
    {
        for (Map.Entry<Integer, Integer> m : marking.getMap().entrySet())
        {
            int key = m.getKey();
            int val = m.getValue();

            if (mapping.get(key) > val)
            {
                return false;
            }

        }
        return true;
    }

    public Marking coverBy(Marking marking)
    {
        Marking newMarking = new Marking();
        for (Map.Entry<Integer, Integer> entry : getMap().entrySet())
        {
            int p = entry.getKey();
            int tokens = entry.getValue();
            if (tokens == Integer.MAX_VALUE)
            {
                marking.getMap().put(p, tokens);
            }
        }

        boolean noCovering = false;
        for (Map.Entry<Integer, Integer> entry : getMap().entrySet())
        {
            int key = entry.getKey();
            int value = entry.getValue();

            int markingValue = marking.get(key);

            if (markingValue >= value)
            {
                if (markingValue > value || (markingValue == Integer.MAX_VALUE || value == Integer.MAX_VALUE))
                {
                    newMarking.putMapping(key, Integer.MAX_VALUE);
                }
                else
                {
                    newMarking.putMapping(key, value);
                }
            }
            else if (value == Integer.MAX_VALUE)
            {
                newMarking.putMapping(key, Integer.MAX_VALUE);
            }
            else
            {
                noCovering = true;
                break;
            }

        }
        if (noCovering)
        {
            return marking;
        }
        return newMarking;
    }

}
