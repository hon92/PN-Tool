/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contextmenus;

import com.views.View;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import net.Transition;

/**
 *
 * @author Honza
 */
public class TransitionContextMenu extends ViewContextMenu
{

    private final JMenuItem setNameButton = new JMenuItem("Set name");
    private final JMenuItem setColorButton = new JMenuItem("Set color");
    private final Transition transition;

    public TransitionContextMenu(View view, Transition transition)
    {
        super(view);
        this.transition = transition;
        initButtons();
        super.initButtons();
    }

    @Override
    public void initButtons()
    {
        add(setNameButton);
        setNameButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                String result = JOptionPane.showInputDialog(view.getParent(), "Input place name", transition.getName());
                if (result != null)
                {
                    transition.setName(result);
                }
            }

        });

        add(setColorButton);
        setColorButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                Color resultColor = JColorChooser.showDialog(view, "Choose Transition color", transition.getColor());
                if (resultColor != null)
                {
                    transition.setColor(resultColor);
                }
            }
        });

        delete.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (view.getNet() != null)
                {
                    view.getNet().removeTransition(transition);
                }
            }
        });
    }

}
