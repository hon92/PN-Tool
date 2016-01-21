package com;

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
public interface IProject
{

    String getProjectFileName();

    String getProjectNameWithoutExtension();

    Net getNet();

    String getProjectDirectory();

    File getProjectFile();

}
