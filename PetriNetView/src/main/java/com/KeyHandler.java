/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.views.View;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Honza
 */
public class KeyHandler extends KeyAdapter
{

    private final View view;
    private final Map<Integer, ICommand> commands = new HashMap<>();

    public KeyHandler(View view)
    {
        this.view = view;
    }

    public void addCommand(int keyEvent, ICommand command)
    {
        this.commands.put(keyEvent, command);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (commands.containsKey(e.getKeyCode()))
        {
            commands.get(e.getKeyCode()).execute();
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }

}
