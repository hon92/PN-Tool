package net;

import java.awt.Point;
import java.awt.geom.Line2D;
import net.interfaces.INetSelector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Honza
 */
public class NetSelector implements INetSelector
{

    public final double ARC_SELECT_RAD = 5.0;

    public NetSelector()
    {
    }

    @Override
    public NetItem getItemAtPoint(int x, int y, Net net)
    {
        for (Place p : net.getPlaces())
        {
            int d = Place.PLACE_DIAMETER;
            int px = p.getX() + d / 2;
            int py = p.getY() + d / 2;

            int diffx = px - x;
            int diffy = py - y;

            if (Math.sqrt(diffx * diffx + diffy * diffy) <= d / 2)
            {
                return p;
            }
        }

        for (Transition t : net.getTransitions())
        {
            int tx = t.getX() - Transition.TRANSITION_WIDTH / 2;
            int ty = t.getY() - Transition.TRANSITION_HEIGHT / 2;

            if (x >= tx && x <= tx + Transition.TRANSITION_WIDTH
                    && y >= ty && y <= ty + Transition.TRANSITION_HEIGHT)
            {
                return t;
            }
        }

        for (Arc a : net.getArcs())
        {
            if (a.points.length == 2)
            {
                Line2D line = a.getArcLine();
                double dist = Line2D.ptLineDistSq(line.getX1(), line.getY1(), line.getX2(), line.getY2(), x, y);
                if (dist < ARC_SELECT_RAD)
                {
                    return a;
                }
            }
            else
            {
                int pIndex = 1;
                int p2Index = a.points.length - 2;
                if (a.getArcType() == ArcType.OUTPUT)
                {
                    pIndex = p2Index;
                    p2Index = 1;
                }

                Point first = a.getPlacePoint(a.points[pIndex].x, a.points[pIndex].y);
                double dist = Line2D.ptSegDistSq(first.x, first.y, a.points[pIndex].x, a.points[pIndex].y, x, y);
                if (dist < ARC_SELECT_RAD)
                {
                    return a;
                }
                Point last = a.getTransitionPoint(a.points[p2Index].x, a.points[p2Index].y);
                dist = Line2D.ptSegDistSq(last.x, last.y, a.points[p2Index].x, a.points[p2Index].y, x, y);
                if (dist < ARC_SELECT_RAD)
                {
                    return a;
                }

                for (int i = 2; i < a.points.length - 1; i++)
                {
                    Point p0 = a.points[i - 1];
                    Point p1 = a.points[i];
                    dist = Line2D.ptSegDistSq(p0.x, p0.y, p1.x, p1.y, x, y);
                    if (dist < ARC_SELECT_RAD)
                    {
                        return a;
                    }
                }
            }
        }
        return null;
    }
}
