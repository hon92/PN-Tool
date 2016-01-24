package com;

import com.views.IToolController;
import com.views.PluginPickerDialog;
import com.views.Window;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import lib.GeneratorID;
import net.Net;
import net.interfaces.INetLoader;

public class PNTool implements IToolController
{

    private final Window window;
    private IProject currentProject;
    private final INetLoader netLoader = new XmlNetLoader();
    private final ISimulator simulator = new Simulator(this);
    private final Set<AlgorithmModel> availablePlugins = new HashSet<>();
    private final Set<AlgorithmModel> usedPlugins = new HashSet<>();

    public PNTool(Window window)
    {
        this.window = window;
    }

    @Override
    public void createProject()
    {
        String newProjectName = JOptionPane.showInputDialog(window, "Project name", "New project", JOptionPane.PLAIN_MESSAGE);
        if (newProjectName != null && newProjectName.length() > 0)
        {
            if (!newProjectName.endsWith(".xml"))
            {
                newProjectName += ".xml";
            }

            GeneratorID.getInstance().reset();
            Net net = new Net(newProjectName);
            File projectFile = new File(newProjectName);
            currentProject = new SimpleProject(net, projectFile);
            window.showView();
            window.getView().setNet(net);
            window.setTitle(net.getName());
        }
    }

    private void openProject(File projectFile)
    {
        if (isOpenedProject())
        {
            closeCurrentProject();
        }

        try
        {
            Net net = netLoader.loadNet(projectFile);
            currentProject = new SimpleProject(net, projectFile);
            window.showView();
            window.getView().setNet(net);
            window.setTitle(net.getName());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(window, ex.getMessage());
        }

    }

    @Override
    public void saveProject()
    {
        if (isOpenedProject())
        {
            try
            {
                netLoader.saveNet(currentProject.getProjectFile(), currentProject.getNet());
            }
            catch (Exception ex)
            {
                JOptionPane.showConfirmDialog(window, ex.getMessage());
            }

        }
    }

    private void saveAsProject(File fileName)
    {
        if (!isOpenedProject())
        {
            return;
        }
        try
        {
            netLoader.saveNet(fileName, currentProject.getNet());
        }
        catch (Exception ex)
        {
            JOptionPane.showConfirmDialog(window, ex.getMessage());
        }
    }

    @Override
    public ISimulator getSimulator()
    {
        return simulator;
    }

    public Window getWindow()
    {
        return window;
    }

    public IProject getProject()
    {
        return currentProject;
    }

    @Override
    public boolean isOpenedProject()
    {
        return this.currentProject != null;
    }

    @Override
    public void openProject()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));
        int retVal = fileChooser.showOpenDialog(window);
        if (retVal == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            closeSimulator();
            openProject(selectedFile);
        }
    }

    private void closeSimulator()
    {
        if (simulator.isRunning())
        {
            simulator.stopSimulation();
            window.swapSimulationButtons();
        }
    }

    @Override
    public void closeCurrentProject()
    {
        if (!isOpenedProject())
        {
            return;
        }
        closeSimulator();
        getWindow().setTitle("");
        getWindow().hideView();
        window.getProjectView().hidePropertiesPanel();
        GeneratorID.getInstance().reset();
        currentProject = null;
    }

    @Override
    public void saveProjectAs()
    {
        if (!isOpenedProject())
        {
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));
        int retVal = fileChooser.showSaveDialog(window);
        if (retVal == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.getName().endsWith(".xml"))
            {
                saveAsProject(selectedFile);
            }
            else
            {
                JOptionPane.showMessageDialog(window, "Wrong save file name", "Save as", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void generateReachibilityGraph()
    {
        if (!isOpenedProject())
        {
            return;
        }

        File[] foundJarFiles = null;
        try
        {
            URL url = ClassLoader.getSystemClassLoader().getResource("plugins");
            File pluginsFolder = new File(url.toURI());
            foundJarFiles = pluginsFolder.listFiles(new FileFilter()
            {

                @Override
                public boolean accept(File pathname)
                {
                    if (!pathname.isFile())
                    {
                        return false;
                    }
                    if (!pathname.getName().endsWith(".jar"))
                    {
                        return false;
                    }
                    return true;
                }

            }
            );
        }
        catch (URISyntaxException ex)
        {

        }

        PluginPickerDialog ppd = new PluginPickerDialog(window, true, availablePlugins, usedPlugins);
        if (foundJarFiles != null)
        {
            ppd.setPluginsFromFolder(foundJarFiles);
        }
        PluginPickerDialog.STATE state = ppd.showDialog();
        if (state != PluginPickerDialog.STATE.OK)
        {
            return;
        }

        availablePlugins.clear();
        availablePlugins.addAll(ppd.getAvailablePlugins());
        usedPlugins.clear();
        usedPlugins.addAll(ppd.getUsedPlugins());

        String projectDirectory = getProject().getProjectDirectory();
        String projectName = getProject().getProjectNameWithoutExtension();
        final String dotFileName = projectDirectory + projectName + ".dot";

        ReachibilitySet rs = new ReachibilitySet(getProject().getNet());
        for (AlgorithmModel am : usedPlugins)
        {
            rs.addAlgoritmus(am);
        }

        MarkingGraph graph = rs.getGraph();
        File graphFile = new File(dotFileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(graphFile, false)))
        {
            bw.write(graph.build());
            bw.flush();
            JOptionPane.showMessageDialog(getWindow(),
                    "Reachibility graph was created in .dot file\n in "
                    + getProject().getProjectDirectory()
                    + getProject().getProjectNameWithoutExtension()
                    + ".dot");
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(getWindow(), projectName + " write error.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}
