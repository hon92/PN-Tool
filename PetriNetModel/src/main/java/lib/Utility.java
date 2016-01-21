/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

/**
 *
 * @author Honza
 */
public final class Utility
{

    private Utility()
    {
    }

    public static Point calculatePlacePoint(int x1, int y1, int x2, int y2, int placeDim)
    {
        int offx = x1 - x2;
        int offy = y1 - y2;
        placeDim /= 2;
        double len = Math.sqrt(offx * offx + offy * offy);
        if (len < placeDim)
        {
            return new Point(x1, y1);
        }
        double scale = placeDim / len;

        double newX = offx * scale + x2;
        double newY = offy * scale + y2;

        return new Point((int) newX, (int) newY);
    }

    public static Point calculateTransactionPoint(int x1, int y1, int x2, int y2, Rectangle rect)
    {
        Line2D lines[] =
        {
            new Line2D.Float(rect.x, rect.y, rect.x + rect.width, rect.y),
            new Line2D.Float(rect.x, rect.y, rect.x, rect.y + rect.height / 2),
            new Line2D.Float(rect.x, rect.y + rect.height / 2, rect.x + rect.width, rect.y + rect.height / 2),
            new Line2D.Float(rect.x + rect.width, rect.y, rect.x + rect.width, rect.y + rect.height / 2)
        };

        for (int i = 0; i < lines.length; i++)
        {
            Line2D l = lines[i];
            int x3 = (int) l.getX1();
            int y3 = (int) l.getY1();
            int x4 = (int) l.getX2();
            int y4 = (int) l.getY2();

            double denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
            if (denom == 0.0)
            { // Lines are parallel.
                if (x2 == x1)
                {
                    return new Point(x2, y4);
                }

                if (y1 == y2 && x2 > x1)
                {
                    return new Point(x4, y2);
                }

                if (y1 == y2 && x1 > x2)
                {
                    return new Point(x3, y2);
                }

                return new Point(x2, y4);
            }
            double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denom;
            double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denom;
            if (ua >= 0.0f && ua <= 1.0f && ub >= 0.0f && ub <= 1.0f)
            {
                // Get the intersection point.
                return new Point((int) (x1 + ua * (x2 - x1)), (int) (y1 + ua * (y2 - y1)));
            }
        }
        return new Point(x2, y2);
    }

    public static Point getCenterPoint(int x1, int y1, int x2, int y2)
    {
        return new Point((x1 + x2) / 2, (y1 + y2) / 2);
    }
}
