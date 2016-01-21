/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import net.Arc;
import net.Net;
import net.NetItem;
import net.NetItemType;
import net.NetSelector;
import net.NetSimulator;
import net.Place;
import net.Transition;

/**
 *
 * @author Honza
 */
public class SimulationView extends JPanel implements Runnable
{

    private final Net net;
    private Thread simulationThread;
    private boolean simulationRunning = false;
    private final MouseSimulationHandler mouseHandler;
    private final NetSimulator netSimulator;

    public SimulationView(Net net) throws CloneNotSupportedException
    {
        setBackground(Color.WHITE);
        this.net = net.clone();
        mouseHandler = new MouseSimulationHandler();
        netSimulator = new NetSimulator(this.net);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        if (net != null)
        {
            drawNet(g);
        }
    }

    private void drawNet(Graphics graphics)
    {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(1.5f));
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (Place p : net.getPlaces())
        {
            p.draw(g);
        }

        for (Arc a : net.getArcs())
        {
            a.draw(g);
        }

        for (Transition t : net.getTransitions())
        {
            if (netSimulator.canFireTransition(t))
            {
                t.draw(g);
                g.setColor(Color.GREEN);
                g.setStroke(new BasicStroke(3f));
                int w = Transition.TRANSITION_WIDTH;
                int h = Transition.TRANSITION_HEIGHT;
                g.drawRect(t.getX() - w / 2, t.getY() - h / 2, w, h);
            }
            else
            {
                g.setStroke(new BasicStroke(1.5f));
                t.draw(g);
            }
        }
    }

    public void update()
    {

    }

    @Override
    public void run()
    {
        while (simulationRunning)
        {
            update();
            repaint();

            try
            {
                Thread.sleep(16);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(SimulationView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stopSimulation()
    {
        simulationRunning = false;
        net.setMarking(netSimulator.getInitialMarking());
        try
        {
            simulationThread.join();
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(SimulationView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startSimulation()
    {
        simulationRunning = true;
        simulationThread = new Thread(this);
        simulationThread.start();
    }

    public void clickAction(NetItem item)
    {
        if (item.getType() == NetItemType.TRANSITION)
        {
            Transition t = (Transition) item;
            if (netSimulator.canFireTransition(t))
            {
                netSimulator.fireTransition(t);
            }
        }
    }

    private class MouseSimulationHandler extends MouseAdapter
    {

        private final NetSelector selector = new NetSelector();

        public MouseSimulationHandler()
        {
            SimulationView.this.addMouseListener(this);
            SimulationView.this.addMouseMotionListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            NetItem item = selector.getItemAtPoint(e.getX(), e.getY(), net);
            if (item != null)
            {
                clickAction(item);
            }
        }

    }

}
