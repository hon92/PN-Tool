/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.interfaces;

import java.awt.Graphics2D;

/**
 *
 * @author Honza
 */
public interface IDrawable
{

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);

    void setPosition(int x, int y);

    void draw(Graphics2D g);

}
