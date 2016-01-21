/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 *
 * @author Honza
 */
public class Transition extends NetItem
{

    public static final int TRANSITION_WIDTH = 80;
    public static final int TRANSITION_HEIGHT = 40;
    public static final String DEFAULT_NAME = "T";
    private Color color = Color.WHITE;
    private int x;
    private int y;
    private String name = "";

    public Transition(int id, int x, int y)
    {
        super(id);
        this.x = x;
        this.y = y;
        setName(DEFAULT_NAME + id);
    }

    public Transition(int x, int y)
    {
        super();
        this.x = x;
        this.y = y;
        setName(DEFAULT_NAME + getId());
    }

    @Override
    public int getX()
    {
        return x;
    }

    @Override
    public int getY()
    {
        return y;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }

    @Override
    public void draw(Graphics2D g)
    {
        FontMetrics fm = g.getFontMetrics();
        int x = getX();
        int y = getY();

        g.setColor(color);
        g.fillRect(x - TRANSITION_WIDTH / 2, y - TRANSITION_HEIGHT / 2, TRANSITION_WIDTH, TRANSITION_HEIGHT);
        if (isSelected())
        {
            g.setColor(Color.red);
        }
        else
        {
            g.setColor(Color.black);
        }
        g.drawRect(x - TRANSITION_WIDTH / 2, y - TRANSITION_HEIGHT / 2, TRANSITION_WIDTH, TRANSITION_HEIGHT);
        String transitionText = getName();
        int tlen = fm.stringWidth(transitionText);
        g.setColor(Color.black);
        g.drawString(transitionText, x - tlen / 2, y + fm.getHeight() / 2);

    }

    @Override
    public void setX(int x)
    {
        this.x = x;
    }

    @Override
    public void setY(int y)
    {
        this.y = y;
    }

    @Override
    public void setPosition(int x, int y)
    {
        setX(x);
        setY(y);
    }

    @Override
    public NetItemType getType()
    {
        return NetItemType.TRANSITION;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public NetItem clone()
    {
        Transition t = new Transition(getId(), getX(), getY());
        t.setName(name);
        t.setColor(color);
        return t;
    }
}
