/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contextmenus;

import com.views.View;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Honza
 */
public class ViewContextMenu extends JPopupMenu
{

    protected final View view;
    protected JMenuItem delete = new JMenuItem("Delete");

    public ViewContextMenu(View view)
    {
        super();
        this.view = view;

    }

    public void initButtons()
    {
        add(delete);
    }

}
