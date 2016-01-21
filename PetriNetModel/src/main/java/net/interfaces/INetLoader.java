package net.interfaces;

import java.io.File;
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
public interface INetLoader
{

    Net loadNet(File file) throws Exception;

    void saveNet(File file, Net net) throws Exception;
}
