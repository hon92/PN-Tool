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
import net.Place;

/**
 *
 * @author Honza
 */
public class PlaceContextMenu extends ViewContextMenu
{

    private final Place place;
    private final JMenuItem setPlaceColorButton = new JMenuItem("Set color");
    private final JMenuItem setPlaceTokensCount = new JMenuItem("Set tokens count");
    private final JMenuItem setName = new JMenuItem("Set name");

    public PlaceContextMenu(View view, Place place)
    {
        super(view);
        this.place = place;
        initButtons();
        super.initButtons();
    }

    @Override
    public void initButtons()
    {
        delete.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (view.getNet() != null)
                {
                    view.getNet().removePlace(place);
                }
            }
        });

        add(setPlaceTokensCount);
        setPlaceTokensCount.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                String result = JOptionPane.showInputDialog(view.getParent(), "Input tokens count", String.valueOf(place.getTokensCount()));
                if (result == null || result.equals(""))
                {
                    return;
                }
                try
                {
                    int newVal = Integer.parseInt(result);
                    if (newVal < place.getCapacity() && newVal >= 0)
                    {
                        place.setTokensCount(newVal);
                    }
                }
                catch (NumberFormatException ex)
                {

                }
            }
        });

        add(setName);
        setName.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                String result = JOptionPane.showInputDialog(view.getParent(), "Input place name", place.getName());
                if (result != null)
                {
                    place.setName(result);
                }
            }
        });

        add(setPlaceColorButton);
        setPlaceColorButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                Color resultColor = JColorChooser.showDialog(view, "Choose Place color", place.getColor());
                if (resultColor != null)
                {
                    place.setColor(resultColor);
                }
            }
        });

    }

}
