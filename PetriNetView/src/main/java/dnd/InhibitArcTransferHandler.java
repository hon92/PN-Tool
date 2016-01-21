/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnd;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 *
 * @author Honza
 */
public class InhibitArcTransferHandler extends TransferHandler
{

    private final String data = "IArcInhibit";

    public InhibitArcTransferHandler()
    {
        super(null);
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
