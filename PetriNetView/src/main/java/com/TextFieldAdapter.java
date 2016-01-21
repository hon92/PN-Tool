/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Honza
 */
public class TextFieldAdapter implements DocumentListener
{

    public void update(String newText)
    {

    }

    @Override
    public void insertUpdate(DocumentEvent e)
    {
        notify(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e)
    {
        notify(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e)
    {
        notify(e);
    }

    private void notify(DocumentEvent e)
    {
        try
        {
            update(e.getDocument().getText(0, e.getDocument().getLength()));
        }
        catch (BadLocationException ex)
        {
        }
    }

}
