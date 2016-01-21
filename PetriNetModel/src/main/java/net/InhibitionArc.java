/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

/**
 *
 * @author Honza
 */
public class InhibitionArc extends Arc
{

    public InhibitionArc(int id, int cardinality, Transition transition, Place place)
    {
        super(id, transition, place);
        setCardinality(cardinality);
    }

    public InhibitionArc(int cardinality, Transition transition, Place place)
    {
        super(transition, place);
        setCardinality(cardinality);
    }

    public InhibitionArc(int cardinality, Transition transition, Place place, Point[] points)
    {
        super(transition, place, points);
        setCardinality(cardinality);
    }

    @Override
    public ArcType getArcType()
    {
        return ArcType.INHIBITION;
    }

    @Override
    public void draw(Graphics2D g)
    {
        super.draw(g);
        if (points.length == 2)
        {
            Line2D line = getArcLine();
            g.drawRoundRect((int) (line.getX2() - 5), (int) (line.getY2() - 5), 10, 10, 10, 10);
        }
        else
        {
            Point p = getTransitionPoint(points[points.length - 2].x, points[points.length - 2].y);
            g.drawRoundRect(p.x - 5, p.y - 5, 10, 10, 10, 10);
        }
    }

    @Override
    public NetItemType getType()
    {
        return NetItemType.ARC;
    }

    @Override
    public NetItem clone()
    {
        InhibitionArc ia = new InhibitionArc(getId(), getCardinality(), getTransition(), getPlace());
        ia.points = points;
        return ia;
    }

}
