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
public class InputArc extends Arc
{

    public InputArc(int id, int cardinality, Transition inputTransition, Place place)
    {
        super(id, inputTransition, place);
        setCardinality(cardinality);
    }

    public InputArc(int cardinality, Transition inputTransition, Place place)
    {
        super(inputTransition, place);
        setCardinality(cardinality);
    }

    public InputArc(int cardinality, Transition inputTransition, Place place, Point[] points)
    {
        super(inputTransition, place, points);
        setCardinality(cardinality);
    }

    @Override
    public ArcType getArcType()
    {
        return ArcType.INPUT;
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
            int tx = (int) line.getX2();
            int ty = (int) line.getY2();
            g.translate(tx, ty);
            g.rotate(Math.atan2(dy, dx));
            g.drawLine(0, 0, -10, 10);
            g.drawLine(0, 0, -10, - 10);
            g.rotate(-Math.atan2(dy, dx));
            g.translate(-tx, -ty);
        }
        else
        {
            Point p2 = getTransitionPoint(points[points.length - 2].x, points[points.length - 2].y);
            double dx = p2.x - points[points.length - 2].x;
            double dy = p2.y - points[points.length - 2].y;
            int txx = p2.x;
            int tyy = p2.y;
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
        InputArc ia = new InputArc(getId(), getCardinality(), getTransition(), getPlace());
        ia.points = points;
        return ia;
    }

}
