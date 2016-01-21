package com;

import com.contextmenus.ArcContextMenu;
import com.contextmenus.PlaceContextMenu;
import com.contextmenus.TransitionContextMenu;
import com.views.View;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import net.Arc;
import net.InhibitionArc;
import net.InputArc;
import net.NetItem;
import net.NetItemType;
import net.NetSelector;
import net.OutputArc;
import net.Place;
import net.Transition;
import net.interfaces.INetSelector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Honza
 */
public class MouseHandler extends MouseAdapter implements ISelectingObservable
{

    private final View view;
    private final INetSelector selector;
    private NetItem selectedItem = null;
    private Place selectedPlace = null;
    private Transition selectedTransition = null;
    private final List<ISelectionObserver> selectionListeners = new ArrayList<>();

    public MouseHandler(View view)
    {
        this.view = view;
        selector = new NetSelector();
    }

    @Override
    public void addSelectingListener(ISelectionObserver selectionObserver)
    {
        selectionListeners.add(selectionObserver);
    }

    @Override
    public void removeSelectingListener(ISelectionObserver selectionObserver)
    {
        selectionListeners.remove(selectionObserver);
    }

    private void toggleNetItem(NetItem item)
    {
        if (item != null)
        {
            if (selectedItem != null)
            {
                selectedItem.setSelected(false);
            }
            item.setSelected(true);
            selectedItem = item;
        }
        else
        {
            if (selectedItem != null)
            {
                selectedItem.setSelected(false);
                selectedItem = null;
            }
        }

    }

    private void prepareContextMenu(MouseEvent e)
    {
        NetItem item = selector.getItemAtPoint(e.getX(), e.getY(), view.getNet());
        if (item != null)
        {
            if (item.getType() == NetItemType.PLACE)
            {
                view.setContextMenu(new PlaceContextMenu(view, (Place) item), e);
            }
            else if (item.getType() == NetItemType.TRANSITION)
            {
                view.setContextMenu(new TransitionContextMenu(view, (Transition) item), e);
            }
            else if (item.getType() == NetItemType.ARC)
            {
                view.setContextMenu(new ArcContextMenu(view, (Arc) item), e);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (view.isSelectingMode())
        {
            if (e.isPopupTrigger())
            {
                prepareContextMenu(e);
            }

            if (SwingUtilities.isLeftMouseButton(e))
            {
                NetItem item = selector.getItemAtPoint(e.getX(), e.getY(), view.getNet());

                if (item != null)
                {
                    for (ISelectionObserver selectionListener : selectionListeners)
                    {
                        selectionListener.onSelection(this, new SelectionArgs(item));
                    }
                }
                else
                {
                    if (selectedItem != null)
                    {
                        for (ISelectionObserver selectionListener : selectionListeners)
                        {
                            selectionListener.onUnSelecting(this, new SelectionArgs(selectedItem));
                        }
                    }
                }
                toggleNetItem(item);
            }
        }
        else
        {
            //drawing mode
            switch (view.getDrawable())
            {
                case PLACE:
                    Place p = new Place(e.getX() - Place.PLACE_DIAMETER / 2, e.getY() - Place.PLACE_DIAMETER / 2);
                    view.getNet().addPlace(p);
                    view.switchMode();
                    break;
                case TRANSITION:
                    Transition t = new Transition(e.getX(), e.getY());
                    view.getNet().addTransition(t);
                    view.switchMode();
                    break;
                case ARC:
                    solveArcRequest(e, false);
                    break;
                case IARC:
                    solveArcRequest(e, true);
                    break;
            }

        }

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (!view.isSelectingMode())
        {
            return;
        }
        if (selectedItem != null && SwingUtilities.isLeftMouseButton(e))
        {

            selectedItem.setPosition(e.getX(), e.getY());
            view.repaint();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (e.isPopupTrigger())
        {
            prepareContextMenu(e);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        if (!view.isSelectingMode())
        {
            view.setCursorLocation(e.getX(), e.getY());
            if (view.getDrawable() == View.DRAWABLE.ARC || view.getDrawable() == View.DRAWABLE.IARC)
            {
                NetItem item = selector.getItemAtPoint(e.getX(), e.getY(), view.getNet());
                if (item != null)
                {
                    if (item.getType() == NetItemType.PLACE || item.getType() == NetItemType.TRANSITION)
                    {
                        toggleNetItem(item);
                    }
                    else
                    {
                        toggleNetItem(null);
                    }
                }
            }
            view.repaint();
        }
    }

    private void solveArcRequest(MouseEvent e, boolean inhibition)
    {
        NetItem i = selector.getItemAtPoint(e.getX(), e.getY(), view.getNet());
        if (i == null && (selectedPlace != null || selectedTransition != null))
        {
            view.arcPoints.add(e.getPoint());
            return;
        }
        if (i != null && selectedPlace == null && selectedTransition == null)
        {
            selectedPlace = (i.getType() == NetItemType.PLACE) ? (Place) i : null;
            selectedTransition = (i.getType() == NetItemType.TRANSITION) ? (Transition) i : null;
            view.arcPoints.add(e.getPoint());
        }

        if (selectedItem != null)
        {
            if (selectedPlace != null && selectedItem.getType() == NetItemType.TRANSITION)
            {
                view.arcPoints.add(e.getPoint());
                Point[] points = new Point[view.arcPoints.size()];
                for (int j = 0; j < view.arcPoints.size(); j++)
                {
                    points[j] = view.arcPoints.get(j);
                }
                Arc a;
                if (inhibition)
                {
                    a = new InhibitionArc(1, (Transition) selectedItem, selectedPlace, points);
                }
                else
                {
                    a = new InputArc(1, (Transition) selectedItem, selectedPlace, points);
                }

                view.getNet().addArc(a);
                selectedPlace = null;
                selectedTransition = null;

                view.switchMode();
            }
            else if (selectedTransition != null && selectedItem.getType() == NetItemType.PLACE)
            {
                view.arcPoints.add(e.getPoint());
                Point[] points = new Point[view.arcPoints.size()];
                for (int j = 0; j < view.arcPoints.size(); j++)
                {
                    points[j] = view.arcPoints.get(j);
                }

                Arc a;
                if (inhibition)
                {
                    a = new InhibitionArc(1, selectedTransition, (Place) selectedItem, points);
                }
                else
                {
                    a = new OutputArc(1, selectedTransition, (Place) selectedItem, points);
                }
                view.getNet().addArc(a);
                selectedPlace = null;
                selectedTransition = null;
                view.switchMode();
            }
        }
    }

    public void resetSelectingItems()
    {
        toggleNetItem(null);
        selectedPlace = null;
        selectedTransition = null;
    }

    public NetItem getSelectedItem()
    {
        return selectedItem;
    }

}
