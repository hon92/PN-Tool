/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import net.NetItem;
import net.NetItemType;

/**
 *
 * @author Honza
 */
public class SelectionArgs implements ISelectionArgs
{

    private final NetItem netItem;

    public SelectionArgs(NetItem netItem)
    {
        this.netItem = netItem;
    }

    @Override
    public boolean isPlace()
    {
        return netItem.getType() == NetItemType.PLACE;
    }

    @Override
    public boolean isTransition()
    {
        return netItem.getType() == NetItemType.TRANSITION;
    }

    @Override
    public boolean isArc()
    {
        return netItem.getType() == NetItemType.ARC;
    }

    @Override
    public NetItem getSelectedItem()
    {
        return netItem;
    }

}
