/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import lib.GeneratorID;
import net.interfaces.IDrawable;
import net.interfaces.ISelectable;

/**
 *
 * @author Honza
 */
public abstract class NetItem implements IDrawable, ISelectable
{

    protected final int ID;
    protected boolean isSelected = false;

    public NetItem()
    {
        ID = GeneratorID.getInstance().getNewId();
    }

    public NetItem(int id)
    {
        ID = id;
        GeneratorID.getInstance().scaleId(id);
    }

    public int getId()
    {
        return ID;
    }

    public abstract NetItemType getType();

    @Override
    public abstract NetItem clone();

    @Override
    public boolean isSelected()
    {
        return isSelected;
    }

    @Override
    public void setSelected(boolean val)
    {
        this.isSelected = val;
    }

}
