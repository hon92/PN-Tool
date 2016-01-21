/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contextmenus;

import com.views.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import net.Arc;

/**
 *
 * @author Honza
 */
public class ArcContextMenu extends ViewContextMenu
{

    private final Arc arc;
    private final JMenuItem setCardinalityButton = new JMenuItem("Set cardinality");

    public ArcContextMenu(View view, Arc arc)
    {
        super(view);
        this.arc = arc;
        initButtons();
        super.initButtons();
    }

    @Override
    public void initButtons()
    {
        add(setCardinalityButton);
        setCardinalityButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                String result = JOptionPane.showInputDialog(view.getParent(), "Input arc cardinality", String.valueOf(arc.getCardinality()));
                if (result == null || result.equals(""))
                {
                    return;
                }
                try
                {
                    int newVal = Integer.parseInt(result);
                    arc.setCardinality(newVal);
                }
                catch (NumberFormatException ex)
                {

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
                    view.getNet().removeArc(arc);
                }
            }
        });
    }

}
