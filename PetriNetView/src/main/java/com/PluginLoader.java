/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import plugin.IPruningAlgoritmus;

/**
 *
 * @author Honza
 */
public class PluginLoader
{

    public Set<IPruningAlgoritmus> loadPlugins(File selectedFile) throws Exception, RuntimeException
    {
        List<String> classesNames = new ArrayList<>();

        URL url = selectedFile.toURI().toURL();

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]
        {
            url
        });

        JarFile jarFile = new JarFile(selectedFile);
        Enumeration<JarEntry> entries = jarFile.entries();

        while (entries.hasMoreElements())
        {
            JarEntry entry = entries.nextElement();
            if (entry.getName().endsWith(".class"))
            {
                String className = entry.getName().replace("/", "\\.");
                String mClass = className.substring(0, className.lastIndexOf("."));
                String clName = mClass.replace("\\", "");
                classesNames.add(clName);
            }
        }

        Set<Class> foundClasses = new HashSet<>();
        final String PLUGIN_INTERFACE_NAME = IPruningAlgoritmus.class.getName();
        for (String className : classesNames)
        {
            Class cl = classLoader.loadClass(className);
            Class[] interfaces = cl.getInterfaces();
            if (interfaces.length == 0)
            {
                continue;
            }

            for (Class interfaceClass : interfaces)
            {
                if (interfaceClass.getName().equals(PLUGIN_INTERFACE_NAME))
                {
                    foundClasses.add(cl);
                }
            }
        }
        Set<IPruningAlgoritmus> foundAlgorithms = new HashSet<>();
        for (Class foundClasse : foundClasses)
        {
            IPruningAlgoritmus algorithm = (IPruningAlgoritmus) foundClasse.newInstance();
            if (algorithm != null)
            {
                foundAlgorithms.add(algorithm);
            }
        }
        return foundAlgorithms;
    }

}
