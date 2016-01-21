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
public class SimpleProject implements IProject
{

    private final Net net;
    private final File projectFile;

    public SimpleProject(Net net, File projectFile)
    {
        this.net = net;
        this.projectFile = projectFile;
    }

    @Override
    public Net getNet()
    {
        return net;
    }

    @Override
    public String getProjectFileName()
    {
        return projectFile.getName();
    }

    @Override
    public String getProjectDirectory()
    {
        String directory = projectFile.getAbsoluteFile().getParent();
        if (!directory.equals(""))
        {
            directory += File.separator;
        }

        return directory;
    }

    @Override
    public File getProjectFile()
    {
        return projectFile;
    }

    @Override
    public String getProjectNameWithoutExtension()
    {
        String fullName = getProjectFileName();
        return fullName.substring(0, fullName.lastIndexOf("."));
    }

}
