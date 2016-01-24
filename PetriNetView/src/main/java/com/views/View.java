package com.views;

import com.ICommand;
import com.KeyHandler;
import com.MouseHandler;
import com.contextmenus.ViewContextMenu;
import dnd.ImportButtonTransferHandler;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import net.Arc;
import net.Net;
import net.NetItem;
import net.Place;
import net.Transition;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Honza
 */
public class View extends javax.swing.JPanel
{

    private Net net = null;
    private ProjectView projectView;
    private final ImportButtonTransferHandler importHandler = new ImportButtonTransferHandler();
    private final MouseHandler mouseHandler = new MouseHandler(this);
    private final KeyHandler keyHandler = new KeyHandler(this);
    private final Timer repaintTimer;
    private boolean selectingMode = true;
    private int mouseX, mouseY;
    public List<Point> arcPoints = new ArrayList<>();

    public enum DRAWABLE
    {

        PLACE, TRANSITION, ARC, IARC, NONE;
    };

    private DRAWABLE drawable = DRAWABLE.NONE;

    public View(ProjectView projectView)
    {
        initComponents();
        init();
        this.projectView = projectView;
        repaintTimer = new Timer(100, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                repaint();
            }
        });
        repaintTimer.start();

        mouseHandler.addSelectingListener(this.projectView);
    }

    public void setProjectView(ProjectView projectView)
    {
        this.projectView = projectView;
    }

    public ProjectView getProjectView()
    {
        return this.projectView;
    }

    private void init()
    {

        addKeyListener(keyHandler);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        setTransferHandler(importHandler);

        keyHandler.addCommand(KeyEvent.VK_P, new ICommand()
        {

            @Override
            public void execute()
            {
                newPlaceRequest();
            }
        });

        keyHandler.addCommand(KeyEvent.VK_T, new ICommand()
        {

            @Override
            public void execute()
            {
                newTransitionRequest();
            }
        });
        keyHandler.addCommand(KeyEvent.VK_A, new ICommand()
        {

            @Override
            public void execute()
            {
                newArcRequest();
            }
        });
        keyHandler.addCommand(KeyEvent.VK_I, new ICommand()
        {

            @Override
            public void execute()
            {
                newInhibitionArcRequest();
            }
        });

        keyHandler.addCommand(KeyEvent.VK_ESCAPE, new ICommand()
        {

            @Override
            public void execute()
            {
                if (!isSelectingMode())
                {
                    switchMode();
                }
            }
        });

        keyHandler.addCommand(KeyEvent.VK_DELETE, new ICommand()
        {

            @Override
            public void execute()
            {
                if (mouseHandler.getSelectedItem() != null)
                {
                    NetItem item = mouseHandler.getSelectedItem();
                    if (item != null)
                    {
                        switch (item.getType())
                        {
                            case ARC:
                                getNet().removeArc((Arc) item);
                                break;
                            case PLACE:
                                getNet().removePlace((Place) item);
                                break;
                            case TRANSITION:
                                getNet().removeTransition((Transition) item);
                                break;
                        }
                        projectView.hidePropertiesPanel();
                    }
                }
            }
        });

        setFocusable(true);
        grabFocus();
    }

    public DRAWABLE getDrawable()
    {
        return drawable;
    }

    public void switchMode()
    {
        selectingMode = !selectingMode;
        drawable = DRAWABLE.NONE;
        arcPoints.clear();
        mouseHandler.resetSelectingItems();
    }

    public boolean isSelectingMode()
    {
        return selectingMode;
    }

    public void setCursorLocation(int x, int y)
    {
        mouseX = x;
        mouseY = y;
    }

    public void setContextMenu(ViewContextMenu contextMenu, MouseEvent e)
    {
        contextMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    public void setNet(Net net)
    {
        this.net = net;
        repaint();
    }

    public Net getNet()
    {
        return net;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (this.net != null)
        {
            drawNet(g);

            if (!selectingMode)
            {
                float c = 0.8f;
                g.setColor(new Color(c, c, c, 0.6f));
                g.fillRect(0, 0, getWidth(), getHeight());
                drawDrawingTools(g, mouseX, mouseY);
            }
        }
    }

    public void drawDrawingTools(Graphics g, int x, int y)
    {
        g.setColor(Color.red);
        switch (drawable)
        {
            case PLACE:
                int d = Place.PLACE_DIAMETER;
                g.drawRoundRect(x - d / 2, y - d / 2, d, d, d, d);
                break;
            case TRANSITION:
                int w = Transition.TRANSITION_WIDTH;
                int h = Transition.TRANSITION_HEIGHT;
                g.drawRect(x - w / 2, y - h / 2, w, h);
                break;
            case ARC:
            case IARC:
                g.setColor(Color.black);
                for (int i = 1; i < arcPoints.size(); i++)
                {
                    Point prev = arcPoints.get(i - 1);
                    Point curr = arcPoints.get(i);
                    g.drawLine(prev.x, prev.y, curr.x, curr.y);
                }
                if (arcPoints.size() > 0)
                {
                    Point last = arcPoints.get(arcPoints.size() - 1);
                    g.drawLine(last.x, last.y, mouseX, mouseY);
                }
                break;
            case NONE:
                break;
        }

    }

    private void drawNet(Graphics graphics)
    {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setStroke(new BasicStroke(1.5f));
        int itemsSize = net.getPlaces().size() + net.getTransitions().size() + net.getArcs().size();

        List<NetItem> items = new ArrayList<>(itemsSize);
        items.addAll(net.getTransitions());
        items.addAll(net.getPlaces());
        items.addAll(net.getArcs());

        for (NetItem item : items)
        {
            item.draw(g);
        }
    }

    public void newPlaceRequest()
    {
        switchMode();
        drawable = DRAWABLE.PLACE;
        getProjectView().hidePropertiesPanel();
    }

    public void newTransitionRequest()
    {
        switchMode();
        drawable = DRAWABLE.TRANSITION;
        getProjectView().hidePropertiesPanel();
    }

    public void newArcRequest()
    {
        switchMode();
        drawable = DRAWABLE.ARC;
        getProjectView().hidePropertiesPanel();
    }

    public void newInhibitionArcRequest()
    {
        switchMode();
        drawable = DRAWABLE.IARC;
        getProjectView().hidePropertiesPanel();
    }

    public void setSelectionMode()
    {
        selectingMode = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
