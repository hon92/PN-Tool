/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.views.SimulationProjectView;
import com.views.SimulationView;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import net.Net;

/**
 *
 * @author Honza
 */
public class Simulator implements ISimulator
{

    private final PNTool tool;
    private SimulationProjectView simulationProjectView;
    private SimulationView simulationView;
    private boolean running = false;

    public Simulator(PNTool tool)
    {
        this.tool = tool;
    }

    private void createSimulation() throws CloneNotSupportedException
    {
        if (!tool.isOpenedProject())
        {
            return;
        }
        Net net = tool.getProject().getNet();
        JPanel contentPanel = tool.getWindow().getContentPanel();
        contentPanel.removeAll();

        simulationProjectView = new SimulationProjectView();
        simulationView = new SimulationView(simulationProjectView, net);
        simulationProjectView.setSimulationView(simulationView);
        contentPanel.add(simulationProjectView, BorderLayout.CENTER);
        contentPanel.repaint();
        simulationView.startSimulation();
        running = true;
//        JDialog dialog = new JDialog(tool.getWindow(), "Simulation", false);
//        dialog.setLocation(tool.getWindow().getLocation());
//        View netView = tool.getWindow().getProjectView().getNetView();
//        Dimension dim = new Dimension(netView.getWidth(), netView.getHeight());
//        dialog.setPreferredSize(dim);
//        dialog.setMinimumSize(dim);
//        SimulationView simulationView = new SimulationView(net);
//        dialog.add(simulationView);
//        dialog.pack();
//        simulationView.startSimulation();
//        dialog.addWindowListener(new WindowAdapter()
//        {
//
//            @Override
//            public void windowClosing(WindowEvent e)
//            {
//                simulationView.stopSimulation();
//            }
//
//        });
//        dialog.setVisible(true);
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

    @Override
    public void stopSimulation()
    {
        if (simulationView != null)
        {
            simulationView.stopSimulation();
        }
        running = false;

        tool.getWindow().setProjectView();
    }

    @Override
    public boolean isRunning()
    {
        return running;
    }

}
