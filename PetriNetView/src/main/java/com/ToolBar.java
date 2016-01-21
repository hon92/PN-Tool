/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import dnd.ArcTransferHandler;
import dnd.InhibitArcTransferHandler;
import dnd.PlaceTransferHandler;
import dnd.TransitionTransferHandler;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.TransferHandler;

/**
 *
 * @author Honza
 */
public class ToolBar
{

    private final JButton newPlaceButton = new JButton("Place");
    private final JButton newTransitionButton = new JButton("Transition");
    private final JButton newArc = new JButton("Arc");
    private final JButton newInhibitArc = new JButton("Inhibit arc");

    public ToolBar()
    {
        newPlaceButton.setTransferHandler(new PlaceTransferHandler());
        newTransitionButton.setTransferHandler(new TransitionTransferHandler());
        newArc.setTransferHandler(new ArcTransferHandler());
        newInhibitArc.setTransferHandler(new InhibitArcTransferHandler());

        class DragListener extends MouseAdapter
        {

            @Override
            public void mouseDragged(MouseEvent e)
            {
                JButton button = (JButton) e.getSource();
                button.getTransferHandler().exportAsDrag(button, e, TransferHandler.COPY);
            }
        }

        newPlaceButton.addMouseMotionListener(new DragListener());
        newTransitionButton.addMouseMotionListener(new DragListener());
        newArc.addMouseMotionListener(new DragListener());
        newInhibitArc.addMouseMotionListener(new DragListener());

    }

    public void addButtons(JToolBar toolBar)
    {
        toolBar.add(newPlaceButton);
        toolBar.add(new JToolBar.Separator());
        toolBar.add(newTransitionButton);
        toolBar.add(new JToolBar.Separator());
        toolBar.add(newArc);
        toolBar.add(new JToolBar.Separator());
        toolBar.add(newInhibitArc);
    }

}
