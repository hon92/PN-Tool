package net.interfaces;

import net.NetItem;
import net.Net;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Honza
 */
public interface INetSelector
{

    NetItem getItemAtPoint(int x, int y, Net net);
}
