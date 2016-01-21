/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import lib.Utility;

/**
 *
 * @author Honza
 */
public abstract class Arc extends NetItem
{

    protected int cardinality;
    protected Place place;
    protected Transition transition;
    public Point[] points;

    public Arc(int id, Transition t, Place p)
    {
        super(id);
        this.place = p;
        this.transition = t;
        points = new Point[2];
        points[0] = new Point(p.getX(), p.getY());
        points[1] = new Point(t.getX(), t.getY());
    }

    public Arc(Transition t, Place p)
    {
        super();
        this.place = p;
        this.transition = t;
        points = new Point[2];
        points[0] = new Point(p.getX(), p.getY());
        points[1] = new Point(t.getX(), t.getY());
    }

    public Arc(Transition t, Place p, Point[] points)
    {
        super();
        this.place = p;
        this.transition = t;
        this.points = points;
    }

    @Override
    public int getX()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getY()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setX(int x)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setY(int y)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPosition(int x, int y)
    {
    }

    @Override
    public void draw(Graphics2D g)
    {
        if (isSelected())
        {
            g.setColor(Color.red);
        }
        else
        {
            g.setColor(Color.BLACK);
        }

        if (points.length == 2)
        {
            Line2D line = getArcLine();
            g.drawLine((int) line.getX1(), (int) line.getY1(), (int) line.getX2(), (int) line.getY2());
            if (getCardinality() > 1)
            {
                Point centerPoint = Utility.getCenterPoint((int) line.getX1(), (int) line.getY1(), (int) line.getX2(), (int) line.getY2());
                g.drawString("" + getCardinality(), centerPoint.x, centerPoint.y);
            }
        }
        else
        {

            for (int i = 2; i < points.length - 1; i++)
            {
                Point prev = points[i - 1];
                Point curr = points[i];
                g.drawLine(prev.x, prev.y, curr.x, curr.y);
            }

            int pIndex = 1;
            int p2Index = points.length - 2;
            if (getArcType() == ArcType.OUTPUT)
            {
                pIndex = p2Index;
                p2Index = 1;
            }

            Point p = getPlacePoint(points[pIndex].x, points[pIndex].y);
            Point p2 = getTransitionPoint(points[p2Index].x, points[p2Index].y);
            g.drawLine(p.x, p.y, points[pIndex].x, points[pIndex].y);
            g.drawLine(p2.x, p2.y, points[p2Index].x, points[p2Index].y);

            if (getCardinality() > 1)
            {
                Point centerPoint;
                if (getArcType() == ArcType.INPUT || getArcType() == ArcType.INHIBITION)
                {
                    centerPoint = Utility.getCenterPoint(p2.x, p2.y, points[points.length - 2].x, points[points.length - 2].y);
                }
                else
                {
                    centerPoint = Utility.getCenterPoint(p.x, p.y, points[1].x, points[1].y);
                }

                g.drawString("" + getCardinality(), centerPoint.x, centerPoint.y);
            }

        }
    }

    public abstract ArcType getArcType();

    public int getCardinality()
    {
        return cardinality;
    }

    public void setCardinality(int val)
    {
        this.cardinality = val;
    }

    public Transition getTransition()
    {
        return transition;
    }

    public Place getPlace()
    {
        return place;
    }

    public void setTransition(Transition t)
    {
        this.transition = t;
    }

    public void setPlace(Place p)
    {
        this.place = p;
    }

    public Line2D getArcLine()
    {
        int tx = getTransition().getX();
        int ty = getTransition().getY();
        Point p = getPlacePoint(tx, ty);
        Point p2 = getTransitionPoint(p.x, p.y);
        return new Line2D.Float(p, p2);
    }

    public Point getPlacePoint(int otherX, int otherY)
    {
        int x = getPlace().getX() + Place.PLACE_DIAMETER / 2;
        int y = getPlace().getY() + Place.PLACE_DIAMETER / 2;
        return Utility.calculatePlacePoint(otherX, otherY, x, y, Place.PLACE_DIAMETER);
    }

    public Point getTransitionPoint(int otherX, int otherY)
    {
        int tx = getTransition().getX();
        int ty = getTransition().getY();
        Rectangle rect = new Rectangle(tx - Transition.TRANSITION_WIDTH / 2,
                ty - Transition.TRANSITION_HEIGHT / 2,
                Transition.TRANSITION_WIDTH,
                Transition.TRANSITION_WIDTH);

        return Utility.calculateTransactionPoint(tx, ty, otherX, otherY, rect);
    }

}
