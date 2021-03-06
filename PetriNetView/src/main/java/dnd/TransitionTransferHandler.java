/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnd;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.TransferHandler;
import net.Transition;

/**
 *
 * @author Honza
 */
public class TransitionTransferHandler extends TransferHandler
{

    private final String data = "ITransition";

    public TransitionTransferHandler()
    {
        super(null);
        initImg();
    }

    private void initImg()
    {
        Image img = new BufferedImage(Transition.TRANSITION_WIDTH, Transition.TRANSITION_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.setColor(Color.black);
        g.drawRect(0, 0, Transition.TRANSITION_WIDTH - 1, Transition.TRANSITION_HEIGHT - 1);
        setDragImage(img);
    }

    @Override
    public int getSourceActions(JComponent c)
    {
        return DnDConstants.ACTION_COPY_OR_MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent c)
    {
        return new StringSelection(data);
    }

}
