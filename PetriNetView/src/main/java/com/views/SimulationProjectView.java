/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import net.NetSimulator;
import net.Place;
import net.Transition;
import net.marking.Marking;

/**
 *
 * @author Honza
 */
public class SimulationProjectView extends javax.swing.JPanel
{

    /**
     * Creates new form SimulationProjectView
     */
    private SimulationView simulationView;
    private final List<Marking> markingsHistory = new ArrayList<>();

    public SimulationProjectView()
    {
        initComponents();
    }

    public void setSimulationView(SimulationView simulationView)
    {
        this.simulationView = simulationView;
        verticalSplitPanel.setBottomComponent(simulationView);
        simulationView.grabFocus();
    }

    public void updateAvailableTransitions(List<Transition> transitionsList)
    {
        DefaultListModel<String> model = (DefaultListModel<String>) simulationPanel.getAvailableTransitionsList().getModel();
        model.clear();
        for (Transition t : transitionsList)
        {
            String newLine = String.format("%s - ID(%d)", t.getName(), t.getId());
            model.addElement(newLine);
        }
    }

    public void updateMarkingPath(Marking marking, List<Place> places)
    {
        JList markingPathList = simulationPanel.getMarkingPath();
        DefaultListModel<String> model = (DefaultListModel<String>) markingPathList.getModel();
        List<String> placesInfo = new ArrayList<>();

        int index = 0;
        for (Map.Entry<Integer, Integer> entry : marking.getMap().entrySet())
        {
            int placeId = entry.getKey();
            int value = entry.getValue();
            placesInfo.add(String.format("%s=%d", places.get(index).getName(), value));
            index++;
        }

        String newLine = String.join(", ", placesInfo);
        model.addElement(newLine);
        markingPathList.ensureIndexIsVisible(model.getSize() - 1);
        markingPathList.setSelectedIndex(model.getSize() - 1);
        markingsHistory.add(marking);
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
        toInitialButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        simulationPanel = new com.views.SimulationPanel();

        setLayout(new java.awt.BorderLayout());

        verticalSplitPanel.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        toInitialButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/First-48.png"))); // NOI18N
        toInitialButton.setToolTipText("Back to initial marking");
        toInitialButton.setFocusable(false);
        toInitialButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toInitialButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toInitialButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toInitialButtonActionPerformed(evt);
            }
        });
        toolBar.add(toInitialButton);

        previousButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Previous-48.png"))); // NOI18N
        previousButton.setToolTipText("Back to previous marking");
        previousButton.setFocusable(false);
        previousButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        previousButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        previousButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                previousButtonActionPerformed(evt);
            }
        });
        toolBar.add(previousButton);

        nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Next-48.png"))); // NOI18N
        nextButton.setToolTipText("Go to next marking");
        nextButton.setFocusable(false);
        nextButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nextButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nextButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                nextButtonActionPerformed(evt);
            }
        });
        toolBar.add(nextButton);

        verticalSplitPanel.setTopComponent(toolBar);

        horizontalSplitPanel.setRightComponent(verticalSplitPanel);
        horizontalSplitPanel.setLeftComponent(simulationPanel);

        add(horizontalSplitPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void toInitialButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_toInitialButtonActionPerformed
    {//GEN-HEADEREND:event_toInitialButtonActionPerformed
        // TODO add your handling code here:
        //back to initial marking
        DefaultListModel<String> model = (DefaultListModel< String>) simulationPanel.getAvailableTransitionsList().getModel();
        model.clear();
        NetSimulator netSimulator = simulationView.getNetSimulator();
        netSimulator.setMarking(netSimulator.getInitialMarking());
        updateAvailableTransitions(netSimulator.getAvailableTransitions());
        simulationPanel.getMarkingPath().setSelectedIndex(0);
        simulationPanel.getMarkingPath().ensureIndexIsVisible(0);
    }//GEN-LAST:event_toInitialButtonActionPerformed

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_previousButtonActionPerformed
    {//GEN-HEADEREND:event_previousButtonActionPerformed
        // TODO add your handling code here:
        //prev marking
        moveHistory(-1);
    }//GEN-LAST:event_previousButtonActionPerformed

    private void moveHistory(int where)
    {
        int selectedIndex = simulationPanel.getMarkingPath().getSelectedIndex();
        if (where >= 0)
        {
            if (selectedIndex >= markingsHistory.size() - 1)
            {
                return;
            }
        }
        else
        {
            if (selectedIndex < 1)
            {
                return;
            }
        }
        int newIndex = selectedIndex + where;

        simulationPanel.getMarkingPath().setSelectedIndex(newIndex);
        NetSimulator netSimulator = simulationView.getNetSimulator();
        netSimulator.setMarking(markingsHistory.get(newIndex));
        updateAvailableTransitions(netSimulator.getAvailableTransitions());
        simulationPanel.getMarkingPath().ensureIndexIsVisible(newIndex);
    }
    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_nextButtonActionPerformed
    {//GEN-HEADEREND:event_nextButtonActionPerformed
        // TODO add your handling code here:
        //next marking
        moveHistory(1);
    }//GEN-LAST:event_nextButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane horizontalSplitPanel;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton previousButton;
    private com.views.SimulationPanel simulationPanel;
    private javax.swing.JButton toInitialButton;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JSplitPane verticalSplitPanel;
    // End of variables declaration//GEN-END:variables
}