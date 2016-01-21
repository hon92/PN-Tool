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
public class Place extends NetItem
{

    public static final int PLACE_DIAMETER = 60;
    public static final String DEFAULT_NAME = "P";
    private int tokenCount = 0;
    private int capacity = Integer.MAX_VALUE;
    private int x;
    private int y;
    private Color color = Color.WHITE;
    private String name = "";

    public Place(int id, int x, int y)
    {
        super(id);
        this.x = x;
        this.y = y;
        setName(DEFAULT_NAME + id);
    }

    public Place(int x, int y)
    {
        super();
        this.x = x;
        this.y = y;
        setName(DEFAULT_NAME + getId());
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Color getColor()
    {
        return this.color;
    }

    public int getTokensCount()
    {
        return tokenCount;
    }

    public void addToken()
    {
        tokenCount++;
    }

    public void removeToken()
    {
        if (tokenCount > 0)
        {
            tokenCount--;
        }
    }

    public void removeTokens(int count)
    {
        if (tokenCount - count >= 0)
        {
            tokenCount -= count;
        }
    }

    public void addTokens(int count)
    {
        if (tokenCount + count <= capacity)
        {
            tokenCount += count;
        }
    }

    public void setTokensCount(int val)
    {
        tokenCount = val;
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

    @Override
    public void draw(Graphics2D g)
    {
        FontMetrics fm = g.getFontMetrics();
        int x = getX();
        int y = getY();
        String placeText = getName() + "[" + getTokensCount() + "]";
        g.setColor(color);
        g.fillRoundRect(x, y, PLACE_DIAMETER, PLACE_DIAMETER, PLACE_DIAMETER, PLACE_DIAMETER);

        if (isSelected())
        {
            g.setColor(Color.red);
        }
        else
        {
            g.setColor(Color.black);
        }

        g.drawRoundRect(x, y, PLACE_DIAMETER, PLACE_DIAMETER, PLACE_DIAMETER, PLACE_DIAMETER);
        g.setColor(Color.black);
        int l = fm.stringWidth(placeText);
        g.drawString(placeText, x + (PLACE_DIAMETER / 2 - l / 2), y + (PLACE_DIAMETER / 2) + fm.getHeight() / 2);
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
        x -= PLACE_DIAMETER / 2;
        y -= PLACE_DIAMETER / 2;
        setX(x);
        setY(y);
    }

    @Override
    public NetItemType getType()
    {
        return NetItemType.PLACE;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
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
        Place p = new Place(getId(), getX(), getY());
        p.setColor(color);
        p.setCapacity(capacity);
        p.setTokensCount(tokenCount);
        p.setName(name);
        return p;
    }

}
