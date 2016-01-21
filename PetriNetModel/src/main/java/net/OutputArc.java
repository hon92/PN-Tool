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
public class OutputArc extends Arc
{

    public OutputArc(int id, int cardinality, Transition outputTransition, Place place)
    {
        super(id, outputTransition, place);
        setCardinality(cardinality);
    }

    public OutputArc(int cardinality, Transition outputTransition, Place place)
    {
        super(outputTransition, place);
        setCardinality(cardinality);
    }

    public OutputArc(int cardinality, Transition outputTransition, Place place, Point[] points)
    {
        super(outputTransition, place, points);
        setCardinality(cardinality);
    }

    @Override
    public ArcType getArcType()
    {
        return ArcType.OUTPUT;
    }

    @Override
    public void draw(Graphics2D g)
    {
        super.draw(g);
        if (points.length == 2)
        {
            Line2D line = getArcLine();
            double dx = line.getX2() - line.getX1();
            double dy = line.getY2() - line.getY1();
            int tx = (int) line.getX1();
            int ty = (int) line.getY1();
            g.translate(tx, ty);
            g.rotate(Math.atan2(dy, dx));
            g.drawLine(0, 0, 10, 10);
            g.drawLine(0, 0, 10, - 10);
            g.rotate(-Math.atan2(dy, dx));
            g.translate(-tx, -ty);
        }
        else
        {
            Point p = getPlacePoint(points[1].x, points[1].y);
            double dx = p.x - points[1].x;
            double dy = p.y - points[1].y;
            int txx = p.x;
            int tyy = p.y;
            g.translate(txx, tyy);
            g.rotate(Math.atan2(dy, dx));
            g.drawLine(0, 0, -10, 10);
            g.drawLine(0, 0, -10, - 10);
            g.rotate(-Math.atan2(dy, dx));
            g.translate(-txx, -tyy);
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
        OutputArc oa = new OutputArc(getId(), getCardinality(), getTransition(), getPlace());
        oa.points = points;
        return oa;
    }

}
