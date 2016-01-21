package dnd;

import com.views.View;
import java.awt.Component;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import javax.swing.TransferHandler;
import net.NetItem;
import net.NetSelector;
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
public class ImportButtonTransferHandler extends TransferHandler
{

    public final DataFlavor DATA_FLAVOR = DataFlavor.stringFlavor;
    private boolean creatingArcAcvive = false;
    private ArcMouseAdapter arcMouseAdapter = null;

    public ImportButtonTransferHandler()
    {
    }

    @Override
    public boolean canImport(TransferSupport support)
    {
        return support.isDataFlavorSupported(DATA_FLAVOR);
    }

    @Override
    public boolean importData(TransferSupport support)
    {
        boolean accepted = false;
        if (canImport(support))
        {
            try
            {
                Transferable t = support.getTransferable();
                Object obj = t.getTransferData(support.getDataFlavors()[0]);
                Component component = support.getComponent();

                if (obj instanceof String && component instanceof View)
                {
                    if (obj.equals("IPlace"))
                    {
                        View view = (View) component;
                        Point dropPoint = support.getDropLocation().getDropPoint();
                        solveImportPlace(view, dropPoint);
                        accepted = true;
                    }
                    else if (obj.equals("ITransition"))
                    {
                        View view = (View) component;
                        Point dropPoint = support.getDropLocation().getDropPoint();
                        solveImportTransition(view, dropPoint);
                        accepted = true;
                    }
                    else if (obj.equals("IArc"))
                    {
                        View view = (View) component;
                        Point dropPoint = support.getDropLocation().getDropPoint();
                        solveImportArc(view, dropPoint, false);
                        accepted = true;
                    }
                    else if (obj.equals("IArcInhibit"))
                    {
                        View view = (View) component;
                        Point dropPoint = support.getDropLocation().getDropPoint();
                        solveImportArc(view, dropPoint, true);
                        accepted = true;
                    }

                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return accepted;
    }

    private void solveImportPlace(View view, Point dropPoint)
    {
        Place newPlace = new Place(dropPoint.x, dropPoint.y);
        newPlace.setName("P" + newPlace.getId());
        view.getNet().addPlace(newPlace);
        view.grabFocus();
        view.repaint();
    }

    private void solveImportTransition(View view, Point dropPoint)
    {
        Transition newTransition = new Transition(dropPoint.x + 40, dropPoint.y + 20);
        newTransition.setName("T" + newTransition.getId());
        view.getNet().addTransition(newTransition);
        view.grabFocus();
        view.repaint();
    }

    private void solveImportArc(View view, Point dropPoint, boolean inhibit)
    {
        NetSelector selector = new NetSelector();
        NetItem firstSelected = selector.getItemAtPoint(dropPoint.x, dropPoint.y, view.getNet());

        if (firstSelected != null)
        {
            arcMouseAdapter = new ArcMouseAdapter(view, selector, dropPoint, firstSelected, inhibit);
            creatingArcAcvive = true;
            view.addMouseMotionListener(arcMouseAdapter);
            view.addMouseListener(arcMouseAdapter);
        }

        view.grabFocus();
    }

    public boolean creatingArcActive()
    {
        return creatingArcAcvive;
    }

    public Line2D getArcLine()
    {
        if (arcMouseAdapter != null)
        {
            return arcMouseAdapter.line;
        }
        return new Line2D.Float();
    }

    private class ArcMouseAdapter extends MouseAdapter
    {

        private final View view;
        private final Point dropPoint;
        private Line2D line = new Line2D.Float();
        private final NetSelector selector;
        private final NetItem firstSelected;
        private boolean inhibit = false;

        private MouseMotionListener[] motionListeners;
        private MouseListener[] mListeners;

        public ArcMouseAdapter(View view, NetSelector selector, Point dropPoint, NetItem firstSelected, boolean inhibit)
        {
            this.view = view;
            this.selector = selector;
            this.dropPoint = dropPoint;
            this.firstSelected = firstSelected;
            this.inhibit = inhibit;
            disableMouseEvents();
        }

        private void disableMouseEvents()
        {
            motionListeners = view.getMouseMotionListeners();
            mListeners = view.getMouseListeners();

            for (MouseMotionListener l : motionListeners)
            {
                view.removeMouseMotionListener(l);
            }

            for (MouseListener l : mListeners)
            {
                view.removeMouseListener(l);
            }
        }

        private void enableMouseEvents()
        {
            view.removeMouseMotionListener(this);
            view.removeMouseListener(this);

            for (MouseMotionListener l : motionListeners)
            {
                view.addMouseMotionListener(l);
            }

            for (MouseListener l : mListeners)
            {
                view.addMouseListener(l);
            }
        }

        @Override
        public void mouseMoved(MouseEvent e)
        {
            line.setLine(e.getX(), e.getY(), dropPoint.x, dropPoint.y);
            view.repaint();
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            int endX = e.getX();
            int endY = e.getY();
            NetItem secondSelected = selector.getItemAtPoint(endX, endY, view.getNet());
//            if (secondSelected != null)
//            {
//                if (firstSelected.getType() == NetItemType.TRANSITION)
//                {
//                    Transition transition = (Transition) firstSelected;
//                    if (secondSelected.getType() == NetItemType.PLACE && !inhibit)
//                    {
//                        Place place = (Place) secondSelected;
//                        view.getNet().addArc(new OutputArc(1, transition, place));
//
//                    }
//                }
//                else if (firstSelected.getType() == NetItemType.PLACE)
//                {
//                    Place place = (Place) firstSelected;
//                    if (secondSelected.getType() == NetItemType.TRANSITION)
//                    {
//                        Transition transition = (Transition) secondSelected;
//
//                        if (inhibit)
//                        {
//                            view.getNet().addArc(new InhibitionArc(1, transition, place));
//                        }
//                        else
//                        {
//                            view.getNet().addArc(new InputArc(1, transition, place));
//                        }
//                    }
//                }
//                enableMouseEvents();
//
//            }
//            else
//            {
//                enableMouseEvents();
//            }
            view.repaint();
//            creatingArcAcvive = false;
        }

    }

}
