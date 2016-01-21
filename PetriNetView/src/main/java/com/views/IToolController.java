/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views;

import com.ISimulator;

/**
 *
 * @author Honza
 */
public interface IToolController
{

    void openProject();

    void createProject();

    void saveProject();

    void saveProjectAs();

    void closeCurrentProject();

    boolean isOpenedProject();

    ISimulator getSimulator();

    void generateReachibilityGraph();

}
