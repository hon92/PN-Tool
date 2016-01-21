package com.views;

import com.PNTool;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Honza
 */
public class Window extends javax.swing.JFrame
{

    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;
    public static final String TOOL_NAME = "Petri Net tool";
    private IToolController tool;
    private final ProjectView projectView = new ProjectView();

    public Window()
    {
        initComponents();
        init();
    }

    private void init()
    {
        setLocationRelativeTo(null);
        Dimension dim = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
        setPreferredSize(dim);
        setMinimumSize(dim);
        //createToolBar();
        contentPanel.add(projectView, BorderLayout.CENTER);
        projectView.setVisible(false);
        stopSimulationButton.setVisible(false);
        tool = new PNTool(this);
        setTitle("");
    }

    public JPanel getContentPanel()
    {
        return contentPanel;
    }

    public void setProjectView()
    {
        contentPanel.removeAll();
        contentPanel.add(projectView, BorderLayout.CENTER);
        contentPanel.repaint();
        contentPanel.grabFocus();
        projectView.getNetView().grabFocus();
    }

    public ProjectView getProjectView()
    {
        return projectView;
    }

    public void showView()
    {
        projectView.setVisible(true);
        projectView.getNetView().grabFocus();
    }

    public void hideView()
    {
        projectView.setVisible(false);
    }

    public View getView()
    {
        return projectView.getNetView();
    }

    @Override
    public void setTitle(String title)
    {
        title = title + " " + TOOL_NAME;
        super.setTitle(title);
    }

//    private void createToolBar()
//    {
//        new ToolBar().addButtons(toolBar);
//        toolBar.setVisible(false);
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        toolBar = new javax.swing.JToolBar();
        newProjectButton = new javax.swing.JButton();
        openProjectButton = new javax.swing.JButton();
        closeProjectButton = new javax.swing.JButton();
        saveProjectButton = new javax.swing.JButton();
        saveAsProjectButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        startSimulationButton = new javax.swing.JButton();
        stopSimulationButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        generateGraphButton = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newProjectButtonItem = new javax.swing.JMenuItem();
        loadMenuItem = new javax.swing.JMenuItem();
        closeMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        simulationMenu = new javax.swing.JMenu();
        generateRGMenuItem = new javax.swing.JMenuItem();
        createImageButton = new javax.swing.JMenuItem();
        showImageButton = new javax.swing.JMenuItem();
        runSimulationMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        newProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Create New-48.png"))); // NOI18N
        newProjectButton.setToolTipText("New project");
        newProjectButton.setFocusable(false);
        newProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newProjectButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                newProjectButtonActionPerformed(evt);
            }
        });
        toolBar.add(newProjectButton);

        openProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Open Folder-48.png"))); // NOI18N
        openProjectButton.setToolTipText("Open project");
        openProjectButton.setFocusable(false);
        openProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openProjectButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                openProjectButtonActionPerformed(evt);
            }
        });
        toolBar.add(openProjectButton);

        closeProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Close Window-48.png"))); // NOI18N
        closeProjectButton.setToolTipText("Close current project");
        closeProjectButton.setFocusable(false);
        closeProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        closeProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        closeProjectButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                closeProjectButtonActionPerformed(evt);
            }
        });
        toolBar.add(closeProjectButton);

        saveProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Save-48.png"))); // NOI18N
        saveProjectButton.setToolTipText("Save project");
        saveProjectButton.setFocusable(false);
        saveProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveProjectButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveProjectButtonActionPerformed(evt);
            }
        });
        toolBar.add(saveProjectButton);

        saveAsProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Save as-48.png"))); // NOI18N
        saveAsProjectButton.setToolTipText("Save as project");
        saveAsProjectButton.setFocusable(false);
        saveAsProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveAsProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveAsProjectButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveAsProjectButtonActionPerformed(evt);
            }
        });
        toolBar.add(saveAsProjectButton);
        toolBar.add(jSeparator1);

        startSimulationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Start-48.png"))); // NOI18N
        startSimulationButton.setToolTipText("Start simulation");
        startSimulationButton.setFocusable(false);
        startSimulationButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        startSimulationButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        startSimulationButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                startSimulationButtonActionPerformed(evt);
            }
        });
        toolBar.add(startSimulationButton);

        stopSimulationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Stop Sign-48.png"))); // NOI18N
        stopSimulationButton.setToolTipText("Stop simulation");
        stopSimulationButton.setFocusable(false);
        stopSimulationButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stopSimulationButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        stopSimulationButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                stopSimulationButtonActionPerformed(evt);
            }
        });
        toolBar.add(stopSimulationButton);
        toolBar.add(jSeparator2);

        generateGraphButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Genealogy-48.png"))); // NOI18N
        generateGraphButton.setToolTipText("Generate reachibility graph");
        generateGraphButton.setFocusable(false);
        generateGraphButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        generateGraphButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        generateGraphButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                generateGraphButtonActionPerformed(evt);
            }
        });
        toolBar.add(generateGraphButton);

        getContentPane().add(toolBar, java.awt.BorderLayout.PAGE_START);

        contentPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(contentPanel, java.awt.BorderLayout.CENTER);

        fileMenu.setText("File");

        newProjectButtonItem.setText("New project");
        newProjectButtonItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                newProjectButtonItemActionPerformed(evt);
            }
        });
        fileMenu.add(newProjectButtonItem);

        loadMenuItem.setText("Load from XML");
        loadMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                loadMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadMenuItem);

        closeMenuItem.setText("Close project");
        closeMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                closeMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(closeMenuItem);

        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setText("Save as");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        jMenuBar1.add(fileMenu);

        jMenu1.setText("Edit");
        jMenuBar1.add(jMenu1);

        simulationMenu.setText("Simulation");

        generateRGMenuItem.setText("Generate RG to file");
        generateRGMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                generateRGMenuItemActionPerformed(evt);
            }
        });
        simulationMenu.add(generateRGMenuItem);

        createImageButton.setText("Create graph image");
        createImageButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                createImageButtonActionPerformed(evt);
            }
        });
        simulationMenu.add(createImageButton);

        showImageButton.setText("Show image");
        showImageButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                showImageButtonActionPerformed(evt);
            }
        });
        simulationMenu.add(showImageButton);

        runSimulationMenuItem.setText("Start simulation");
        runSimulationMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                runSimulationMenuItemActionPerformed(evt);
            }
        });
        simulationMenu.add(runSimulationMenuItem);

        jMenuBar1.add(simulationMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_loadMenuItemActionPerformed
    {//GEN-HEADEREND:event_loadMenuItemActionPerformed
        // load from xml
        tool.openProject();
    }//GEN-LAST:event_loadMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_exitMenuItemActionPerformed
    {//GEN-HEADEREND:event_exitMenuItemActionPerformed
        // save changes, and exit
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void newProjectButtonItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newProjectButtonItemActionPerformed
    {//GEN-HEADEREND:event_newProjectButtonItemActionPerformed
        //new project
        tool.createProject();
    }//GEN-LAST:event_newProjectButtonItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveMenuItemActionPerformed
    {//GEN-HEADEREND:event_saveMenuItemActionPerformed
        //save project
        tool.saveProject();
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveAsMenuItemActionPerformed
    {//GEN-HEADEREND:event_saveAsMenuItemActionPerformed
        //save as project
        tool.saveProjectAs();
    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    private void runSimulationMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_runSimulationMenuItemActionPerformed
    {//GEN-HEADEREND:event_runSimulationMenuItemActionPerformed
        // TODO add your handling code here:
//        if (tool.isOpenedProject())
//        {
//            try
//            {
//                tool.getSimulator().createSimulation();
//            }
//            catch (CloneNotSupportedException ex)
//            {
//                JOptionPane.showMessageDialog(this, "Cloning net error", "Error", JOptionPane.ERROR_MESSAGE);
//                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }//GEN-LAST:event_runSimulationMenuItemActionPerformed

    private void closeMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_closeMenuItemActionPerformed
    {//GEN-HEADEREND:event_closeMenuItemActionPerformed
        // close project
        tool.closeCurrentProject();
    }//GEN-LAST:event_closeMenuItemActionPerformed

    private void generateRGMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_generateRGMenuItemActionPerformed
    {//GEN-HEADEREND:event_generateRGMenuItemActionPerformed
        //generate RG
        tool.generateReachibilityGraph();
    }//GEN-LAST:event_generateRGMenuItemActionPerformed

    private void showImageButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_showImageButtonActionPerformed
    {//GEN-HEADEREND:event_showImageButtonActionPerformed
        // show image button
//        if (!tool.isOpenedProject())
//        {
//            return;
//        }
//        String projectDirectory = tool.getProject().getProjectDirectory();
//        String projectName = tool.getProject().getProjectNameWithoutExtension();
//
//        final String imageFileName = projectDirectory + projectName + ".png";
//        File imageFile = new File(imageFileName);
//        if (imageFile.exists())
//        {
//            ImageViewer iv = new ImageViewer(this, imageFile);
//            iv.show();
//        }
    }//GEN-LAST:event_showImageButtonActionPerformed

    private void createImageButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_createImageButtonActionPerformed
    {//GEN-HEADEREND:event_createImageButtonActionPerformed
        // TODO add your handling code here:
        //create image
//        if (!tool.isOpenedProject())
//        {
//            return;
//        }
//
//        String projectDirectory = tool.getProject().getProjectDirectory();
//        String projectName = tool.getProject().getProjectNameWithoutExtension();
//        final String GRAPH_VIZ_DIRECTORY = Settings.getInstance().getVal("DOT_PATH");
//        final String imageFileName = projectDirectory + projectName + ".png";
//        final String dotFileName = projectDirectory + projectName + ".dot";
//
//        File graphFile = new File(dotFileName);
//        if (!graphFile.exists())
//        {
//            JOptionPane.showMessageDialog(this,
//                    ".dot file " + projectName + ".dot" + " not exist at location \n" + projectDirectory,
//                    "File not found", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        File graphVizDirectory = new File(GRAPH_VIZ_DIRECTORY);
//        if (graphVizDirectory.exists() && graphVizDirectory.canExecute())
//        {
//            try
//            {
//                List<String> commands = new ArrayList<>();
//                commands.add("dot.exe");
//                commands.add("-Tpng");
//                commands.add(graphFile.getAbsolutePath());
//                commands.add("-o");
//                commands.add(imageFileName);
//
//                ProcessBuilder processBuilder = new ProcessBuilder(commands);
//                processBuilder.directory(graphVizDirectory);
//                processBuilder.redirectErrorStream(true);
//                processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
//
//                Process process = processBuilder.start();
//                process.waitFor();
//
//            }
//            catch (IOException ex)
//            {
//                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            catch (InterruptedException ex)
//            {
//                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
//        else
//        {
//            JOptionPane.showMessageDialog(this, "Program GraphViz not found.", "Error", JOptionPane.ERROR_MESSAGE);
//        }

    }//GEN-LAST:event_createImageButtonActionPerformed

    private void newProjectButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newProjectButtonActionPerformed
    {//GEN-HEADEREND:event_newProjectButtonActionPerformed
        // TODO add your handling code here:
        //new project
        tool.createProject();
    }//GEN-LAST:event_newProjectButtonActionPerformed

    private void openProjectButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_openProjectButtonActionPerformed
    {//GEN-HEADEREND:event_openProjectButtonActionPerformed
        // TODO add your handling code here:
        //open project
        tool.openProject();
    }//GEN-LAST:event_openProjectButtonActionPerformed

    private void closeProjectButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_closeProjectButtonActionPerformed
    {//GEN-HEADEREND:event_closeProjectButtonActionPerformed
        // TODO add your handling code here:
        //close project
        tool.closeCurrentProject();
    }//GEN-LAST:event_closeProjectButtonActionPerformed

    private void saveProjectButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveProjectButtonActionPerformed
    {//GEN-HEADEREND:event_saveProjectButtonActionPerformed
        // TODO add your handling code here:
        //save project
        tool.saveProject();
    }//GEN-LAST:event_saveProjectButtonActionPerformed

    private void saveAsProjectButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveAsProjectButtonActionPerformed
    {//GEN-HEADEREND:event_saveAsProjectButtonActionPerformed
        // TODO add your handling code here:
        //save as project
        tool.saveProjectAs();
    }//GEN-LAST:event_saveAsProjectButtonActionPerformed

    private void startSimulationButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_startSimulationButtonActionPerformed
    {//GEN-HEADEREND:event_startSimulationButtonActionPerformed
        // TODO add your handling code here:
        //start simulation
        tool.getSimulator().startSimulation();
        if (tool.getSimulator().isRunning())
        {
            swapSimulationButtons();
        }
    }//GEN-LAST:event_startSimulationButtonActionPerformed

    public void swapSimulationButtons()
    {
        if (startSimulationButton.isVisible())
        {
            startSimulationButton.setVisible(false);
            stopSimulationButton.setVisible(true);
        }
        else
        {
            startSimulationButton.setVisible(true);
            stopSimulationButton.setVisible(false);
        }
    }

    private void generateGraphButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_generateGraphButtonActionPerformed
    {//GEN-HEADEREND:event_generateGraphButtonActionPerformed
        // TODO add your handling code here:
        //generate .dot file
        tool.generateReachibilityGraph();
    }//GEN-LAST:event_generateGraphButtonActionPerformed

    private void stopSimulationButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_stopSimulationButtonActionPerformed
    {//GEN-HEADEREND:event_stopSimulationButtonActionPerformed
        // TODO add your handling code here:
        //stop simulation
        tool.getSimulator().stopSimulation();
        swapSimulationButtons();
    }//GEN-LAST:event_stopSimulationButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem closeMenuItem;
    private javax.swing.JButton closeProjectButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JMenuItem createImageButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton generateGraphButton;
    private javax.swing.JMenuItem generateRGMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JMenuItem loadMenuItem;
    private javax.swing.JButton newProjectButton;
    private javax.swing.JMenuItem newProjectButtonItem;
    private javax.swing.JButton openProjectButton;
    private javax.swing.JMenuItem runSimulationMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JButton saveAsProjectButton;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JButton saveProjectButton;
    private javax.swing.JMenuItem showImageButton;
    private javax.swing.JMenu simulationMenu;
    private javax.swing.JButton startSimulationButton;
    private javax.swing.JButton stopSimulationButton;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

}
