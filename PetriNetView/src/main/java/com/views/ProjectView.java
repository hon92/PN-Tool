/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views;

import com.ISelectionArgs;
import com.ISelectionObserver;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import net.Arc;
import net.Place;
import net.Transition;

/**
 *
 * @author Honza
 */
public class ProjectView extends javax.swing.JPanel implements ISelectionObserver
{

    private JPanel propertiesInfoPanel = null;

    public ProjectView()
    {
        initComponents();
    }

    public View getNetView()
    {
        return view;
    }

    public JPanel getPropertiesPanel()
    {
        return propertiesPanel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        horizontalSplitPanel = new javax.swing.JSplitPane();
        verticalSplitPanel = new javax.swing.JSplitPane();
        toolBar = new javax.swing.JToolBar();
        selectionModeButton = new javax.swing.JButton();
        placeButton = new javax.swing.JButton();
        transitionButton = new javax.swing.JButton();
        arcButton = new javax.swing.JButton();
        inhibitArcButton = new javax.swing.JButton();
        scrollPanel = new javax.swing.JScrollPane();
        view = new com.views.View(this);
        propertiesPanel = new javax.swing.JPanel();

        setFocusable(false);
        setLayout(new java.awt.BorderLayout());

        horizontalSplitPanel.setFocusable(false);

        verticalSplitPanel.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        verticalSplitPanel.setFocusable(false);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.setFocusable(false);
        toolBar.setMinimumSize(new java.awt.Dimension(130, 50));
        toolBar.setPreferredSize(new java.awt.Dimension(100, 50));

        selectionModeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Select All-48.png"))); // NOI18N
        selectionModeButton.setToolTipText("Selection mode");
        selectionModeButton.setFocusable(false);
        selectionModeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        selectionModeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        selectionModeButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                selectionModeButtonActionPerformed(evt);
            }
        });
        toolBar.add(selectionModeButton);

        placeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Circled-48.png"))); // NOI18N
        placeButton.setToolTipText("Place");
        placeButton.setFocusable(false);
        placeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        placeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        placeButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                placeButtonActionPerformed(evt);
            }
        });
        toolBar.add(placeButton);

        transitionButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Rectangle Stroked-48.png"))); // NOI18N
        transitionButton.setToolTipText("Transition");
        transitionButton.setFocusable(false);
        transitionButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        transitionButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        transitionButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                transitionButtonActionPerformed(evt);
            }
        });
        toolBar.add(transitionButton);

        arcButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Up Right-48.png"))); // NOI18N
        arcButton.setToolTipText("Arc");
        arcButton.setFocusable(false);
        arcButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        arcButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        arcButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                arcButtonActionPerformed(evt);
            }
        });
        toolBar.add(arcButton);

        inhibitArcButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Right 2-48.png"))); // NOI18N
        inhibitArcButton.setToolTipText("Inhibition arc");
        inhibitArcButton.setFocusable(false);
        inhibitArcButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        inhibitArcButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        inhibitArcButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                inhibitArcButtonActionPerformed(evt);
            }
        });
        toolBar.add(inhibitArcButton);

        verticalSplitPanel.setTopComponent(toolBar);

        scrollPanel.setFocusable(false);

        javax.swing.GroupLayout viewLayout = new javax.swing.GroupLayout(view);
        view.setLayout(viewLayout);
        viewLayout.setHorizontalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 734, Short.MAX_VALUE)
        );
        viewLayout.setVerticalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        scrollPanel.setViewportView(view);

        verticalSplitPanel.setRightComponent(scrollPanel);

        horizontalSplitPanel.setRightComponent(verticalSplitPanel);

        propertiesPanel.setFocusable(false);
        propertiesPanel.setLayout(new java.awt.BorderLayout());
        horizontalSplitPanel.setLeftComponent(propertiesPanel);

        add(horizontalSplitPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void selectionModeButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_selectionModeButtonActionPerformed
    {//GEN-HEADEREND:event_selectionModeButtonActionPerformed
        // TODO add your handling code here:
        //selection mode
        getNetView().setSelectionMode();
    }//GEN-LAST:event_selectionModeButtonActionPerformed

    private void placeButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_placeButtonActionPerformed
    {//GEN-HEADEREND:event_placeButtonActionPerformed
        // TODO add your handling code here:
        //new place
        getNetView().newPlaceRequest();
    }//GEN-LAST:event_placeButtonActionPerformed

    private void transitionButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_transitionButtonActionPerformed
    {//GEN-HEADEREND:event_transitionButtonActionPerformed
        // TODO add your handling code here:
        //new transition
        getNetView().newTransitionRequest();
    }//GEN-LAST:event_transitionButtonActionPerformed

    private void arcButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_arcButtonActionPerformed
    {//GEN-HEADEREND:event_arcButtonActionPerformed
        // TODO add your handling code here:
        //new arc
        getNetView().newArcRequest();
    }//GEN-LAST:event_arcButtonActionPerformed

    private void inhibitArcButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_inhibitArcButtonActionPerformed
    {//GEN-HEADEREND:event_inhibitArcButtonActionPerformed
        // TODO add your handling code here:
        getNetView().newInhibitionArcRequest();
    }//GEN-LAST:event_inhibitArcButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton arcButton;
    private javax.swing.JSplitPane horizontalSplitPanel;
    private javax.swing.JButton inhibitArcButton;
    private javax.swing.JButton placeButton;
    private javax.swing.JPanel propertiesPanel;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JButton selectionModeButton;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JButton transitionButton;
    private javax.swing.JSplitPane verticalSplitPanel;
    private com.views.View view;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onSelection(Object source, ISelectionArgs args)
    {
        hidePropertiesPanel();
        if (args.isPlace())
        {
            propertiesInfoPanel = new PlaceInfoPanel((Place) args.getSelectedItem());
        }
        else if (args.isTransition())
        {
            propertiesInfoPanel = new TransitionInfoPanel((Transition) args.getSelectedItem());
        }
        else if (args.isArc())
        {
            propertiesInfoPanel = new ArcInfoPanel((Arc) args.getSelectedItem());
        }
        setPropertiesPanel(propertiesInfoPanel);
    }

    @Override
    public void onUnSelecting(Object source, ISelectionArgs args)
    {
        hidePropertiesPanel();
    }

    private void setPropertiesPanel(JPanel panel)
    {
        if (panel != null)
        {
            this.propertiesPanel.add(panel, BorderLayout.CENTER);
            horizontalSplitPanel.setLeftComponent(this.propertiesPanel);
            this.propertiesPanel.revalidate();
            this.propertiesPanel.repaint();
        }
    }

    public void hidePropertiesPanel()
    {
        if (propertiesInfoPanel != null)
        {
            this.propertiesPanel.remove(propertiesInfoPanel);
            this.repaint();
            propertiesInfoPanel = null;
        }
    }

}
