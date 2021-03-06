/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views;

import com.AlgorithmModel;
import com.PluginLoader;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import plugin.IPruningAlgoritmus;

/**
 *
 * @author Honza
 */
public class PluginPickerDialog extends javax.swing.JDialog
{

    private final PluginLoader pluginLoader = new PluginLoader();

    private final DefaultListModel<AlgorithmModel> avaModel = new DefaultListModel<>();
    private final DefaultListModel<AlgorithmModel> useModel = new DefaultListModel<>();

    private final Set<AlgorithmModel> pluginsAlgorithms = new HashSet<>();

    public STATE showDialog()
    {
        setVisible(true);
        return finalState;
    }

    public static enum STATE
    {

        OK, CANCEL
    };
    private STATE finalState = STATE.CANCEL;

    public PluginPickerDialog(java.awt.Frame parent, boolean modal, Set<AlgorithmModel> avaAlgSet, Set<AlgorithmModel> useAlgSet)
    {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        setResizable(false);
        Dimension dim = new Dimension(250, 130);
        availablePluginsNamesList.setMinimumSize(dim);
        availablePluginsNamesList.setMaximumSize(dim);
        availablePluginsNamesList.setPreferredSize(dim);

        usedPluginsList.setMinimumSize(dim);
        usedPluginsList.setMaximumSize(dim);
        usedPluginsList.setPreferredSize(dim);

        availablePluginsNamesList.setModel(avaModel);
        usedPluginsList.setModel(useModel);

        for (AlgorithmModel useAlg : useAlgSet)
        {
            useModel.addElement(useAlg);
        }
        for (AlgorithmModel avaAlg : avaAlgSet)
        {
            avaModel.addElement(avaAlg);
        }

        usedPluginsList.setCellRenderer(getCellRenderer());
        availablePluginsNamesList.setCellRenderer(getCellRenderer());
    }

    public void setPluginsFromFolder(File[] jarFiles)
    {
        pluginsAlgorithms.clear();
        try
        {
            for (File jarFile : jarFiles)
            {
                Set<IPruningAlgoritmus> algs = pluginLoader.loadPlugins(jarFile);
                for (IPruningAlgoritmus alg : algs)
                {
                    AlgorithmModel am = new AlgorithmModel(alg, Color.WHITE);
                    pluginsAlgorithms.add(am);
                    avaModel.addElement(am);
                }
            }
        }
        catch (Exception ex)
        {
            pluginsAlgorithms.clear();
            JOptionPane.showMessageDialog(this, "Plugin error:\n" + ex.getMessage(), "Invalid plugin error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadPlugin(File jarFile)
    {
        try
        {
            Set<IPruningAlgoritmus> algs = pluginLoader.loadPlugins(jarFile);
            for (IPruningAlgoritmus alg : algs)
            {
                AlgorithmModel am = new AlgorithmModel(alg, Color.WHITE);
                avaModel.addElement(am);
            }

        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Plugin error:\n" + ex.getMessage(), "Invalid plugin error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private DefaultListCellRenderer getCellRenderer()
    {
        return new DefaultListCellRenderer()
        {

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof AlgorithmModel)
                {
                    AlgorithmModel clim = (AlgorithmModel) value;
                    String name = clim.algorithm.getClass().getSimpleName();
                    setText(name);
                    setBackground(clim.color);
                    if (isSelected)
                    {
                        setBackground(getBackground().darker());
                    }
                }

                return c;
            }
        };

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

        jScrollPane1 = new javax.swing.JScrollPane();
        availablePluginsNamesList = new javax.swing.JList();
        runButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        usedPluginsList = new javax.swing.JList();
        switchPluginButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        colorButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(availablePluginsNamesList);

        runButton.setText("Run");
        runButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                runButtonActionPerformed(evt);
            }
        });

        loadButton.setText("Load plugin");
        loadButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                loadButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Available plugins");

        usedPluginsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane2.setViewportView(usedPluginsList);

        switchPluginButton.setText("<<->>");
        switchPluginButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                switchPluginButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Used plugins");

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cancelButtonActionPerformed(evt);
            }
        });

        colorButton.setText("Set color");
        colorButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                colorButtonActionPerformed(evt);
            }
        });

        removeButton.setText("Remove plugin");
        removeButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                removeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(loadButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(253, 527, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 536, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(switchPluginButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(removeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(colorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loadButton)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(switchPluginButton))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(colorButton)
                    .addComponent(removeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(runButton)
                    .addComponent(cancelButton))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_loadButtonActionPerformed
    {//GEN-HEADEREND:event_loadButtonActionPerformed
        // TODO add your handling code here:
        //load plugin
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(".jar", "jar"));

        int retVal = fileChooser.showOpenDialog(this);
        if (retVal == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null)
            {
                loadPlugin(selectedFile);
            }

        }

    }//GEN-LAST:event_loadButtonActionPerformed

    private void switchPluginButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_switchPluginButtonActionPerformed
    {//GEN-HEADEREND:event_switchPluginButtonActionPerformed
        // TODO add your handling code here:
        //switch plugins
        switchPlugins();
    }//GEN-LAST:event_switchPluginButtonActionPerformed

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_runButtonActionPerformed
    {//GEN-HEADEREND:event_runButtonActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        if (useModel.size() >= 1)
        {
            finalState = STATE.OK;
        }
        else
        {
            finalState = STATE.CANCEL;
        }
    }//GEN-LAST:event_runButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancelButtonActionPerformed
    {//GEN-HEADEREND:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        finalState = STATE.CANCEL;
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void colorButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_colorButtonActionPerformed
    {//GEN-HEADEREND:event_colorButtonActionPerformed
        // TODO add your handling code here:
        //color choose

        List<AlgorithmModel> selectedList = usedPluginsList.getSelectedValuesList();
        if (selectedList.isEmpty())
        {
            return;
        }

        Color newColor = JColorChooser.showDialog(this, "Pick algorithm color", Color.white);
        if (newColor != null)
        {
            for (AlgorithmModel clim : selectedList)
            {
                clim.color = newColor;
            }
            usedPluginsList.revalidate();
            usedPluginsList.repaint();
        }

    }//GEN-LAST:event_colorButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_removeButtonActionPerformed
    {//GEN-HEADEREND:event_removeButtonActionPerformed
        // TODO add your handling code here:
        //remove plugin
        List<AlgorithmModel> selected = availablePluginsNamesList.getSelectedValuesList();
        for (AlgorithmModel am : selected)
        {
            avaModel.removeElement(am);
        }
    }//GEN-LAST:event_removeButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(PluginPickerDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(PluginPickerDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(PluginPickerDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(PluginPickerDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                PluginPickerDialog dialog = new PluginPickerDialog(new javax.swing.JFrame(), true, new HashSet<>(), new HashSet<>());
                dialog.addWindowListener(new java.awt.event.WindowAdapter()
                {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e)
                    {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList availablePluginsNamesList;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton colorButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton runButton;
    private javax.swing.JButton switchPluginButton;
    private javax.swing.JList usedPluginsList;
    // End of variables declaration//GEN-END:variables

    private void switchPlugins()
    {
        List<AlgorithmModel> avaList = availablePluginsNamesList.getSelectedValuesList();
        List<AlgorithmModel> usedList = usedPluginsList.getSelectedValuesList();

        for (AlgorithmModel a : avaList)
        {
            avaModel.removeElement(a);
            useModel.addElement(a);
        }

        for (AlgorithmModel a : usedList)
        {
            avaModel.addElement(a);
            useModel.removeElement(a);
        }

    }

    public Set<AlgorithmModel> getAvailablePlugins()
    {
        for (AlgorithmModel am : pluginsAlgorithms)
        {
            avaModel.removeElement(am);
        }
        Set<AlgorithmModel> set = new HashSet<>();
        for (int i = 0; i < avaModel.getSize(); i++)
        {
            set.add(avaModel.get(i));
        }
        return set;
    }

    public Set<AlgorithmModel> getUsedPlugins()
    {
        Set<AlgorithmModel> set = new HashSet<>();
        for (int i = 0; i < useModel.getSize(); i++)
        {
            set.add(useModel.get(i));
        }
        for (AlgorithmModel am : pluginsAlgorithms)
        {
            useModel.removeElement(am);
        }
        return set;
    }
}
