/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.views.SimulationView;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import net.Net;

/**
 *
 * @author Honza
 */
public class Simulator implements ISimulator
{

    private PNTool tool;

    public Simulator(PNTool tool)
    {
        this.tool = tool;
    }

    private void createSimulation() throws CloneNotSupportedException
    {
        Net net = tool.getProject().getNet();

        JDialog dialog = new JDialog(tool.getWindow(), "Simulation", false);
        dialog.setLocation(tool.getWindow().getLocation());
        Dimension dim = new Dimension(640, 480);
        dialog.setPreferredSize(dim);
        dialog.setMinimumSize(dim);
        SimulationView simulationView = new SimulationView(net);
        dialog.add(simulationView);
        dialog.pack();
        simulationView.startSimulation();

        dialog.addWindowListener(new WindowAdapter()
        {

            @Override
            public void windowClosing(WindowEvent e)
            {
                simulationView.stopSimulation();
            }

        });
        dialog.setVisible(true);
    }

    @Override
    public void startSimulation()
    {
        try
        {
            createSimulation();
        }
        catch (CloneNotSupportedException ex)
        {

            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
