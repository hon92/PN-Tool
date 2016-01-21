/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Honza
 */
public final class Settings
{

    private static Settings settings;
    private static final String SETTINGS_FILE_NAME = "config.xml";
    private final Map<String, String> configValues = new HashMap<>();

    private Settings()
    {

    }

    public String getVal(String name)
    {
        if (configValues.containsKey(name))
        {
            return configValues.get(name);
        }
        return "";
    }

    public Collection<String> getSettingsNames()
    {
        return configValues.values();
    }

    private void load() throws Exception
    {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(SETTINGS_FILE_NAME);
        parseConfigFile(document.getDocumentElement());
    }

    private void parseConfigFile(Element rootElement) throws Exception
    {
        NodeList nodeList = rootElement.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                String name = element.getAttribute("name");
                String value = element.getAttribute("value");
                configValues.put(name, value);
            }
        }
    }

    public static Settings getInstance()
    {
        if (settings == null)
        {
            settings = new Settings();
            try
            {
                settings.load();
            }
            catch (Exception ex)
            {
                Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return settings;
    }

}
