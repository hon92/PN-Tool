package com;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import net.Arc;
import static net.ArcType.INHIBITION;
import static net.ArcType.INPUT;
import static net.ArcType.OUTPUT;
import net.InhibitionArc;
import net.InputArc;
import net.Net;
import net.OutputArc;
import net.Place;
import net.Transition;
import net.interfaces.INetLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Honza
 */
public class XmlNetLoader implements INetLoader
{

    public XmlNetLoader()
    {
    }

    @Override
    public Net loadNet(File fileName) throws Exception
    {
        if (!fileName.exists())
        {
            return null;
        }

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(fileName);
        Net net = fillData(document);
        return net;
    }

    private Net fillData(Document d) throws Exception
    {
        Element rootElement = d.getDocumentElement();
        //net attributes -> name
        String netName = rootElement.getAttribute("name");
        Net net = new Net(netName);
        //places
        //transitions
        //arcs
        NodeList nodeList = rootElement.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            NodeList placesNodes = rootElement.getElementsByTagName("places");
            NodeList transitionsNodes = rootElement.getElementsByTagName("transitions");
            NodeList arcsNodes = rootElement.getElementsByTagName("arcs");

            if (placesNodes.getLength() != 1 && transitionsNodes.getLength() != 1 && arcsNodes.getLength() != 1)
            {
                throw new Exception("In input XML is not <places> or <transitions> or <arcs> tag inside <net> tag");
            }

            List<Place> places = loadPlace(placesNodes.item(0));
            List<Transition> transitions = loadTransition(transitionsNodes.item(0));
            List<Arc> arcs = loadArcs(arcsNodes.item(0), places, transitions);

            net.setPlaces(places);
            net.setTransitions(transitions);
            net.setArcs(arcs);
        }
        return net;
    }

    private List<Place> loadPlace(Node root)
    {
        //place attributes -> id, x, y, tokens, color
        List<Place> places = new ArrayList<>();
        NodeList list = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++)
        {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                String id = element.getAttribute("id");
                String x = element.getAttribute("x");
                String y = element.getAttribute("y");
                String tokens = element.getAttribute("tokens");
                String color = element.getAttribute("color");
                Place p = new Place(Integer.parseInt(id), Integer.parseInt(x), Integer.parseInt(y));
                if (tokens != null && !tokens.equals(""))
                {
                    p.addTokens(Integer.parseInt(tokens));
                }
                if (color != null && !color.equals(""))
                {
                    Color c = Color.decode(color);
                    p.setColor(c);
                }
                places.add(p);
            }
        }

        return places;
    }

    private List<Transition> loadTransition(Node root)
    {
        //transition attributes -> id, x, y
        List<Transition> transitions = new ArrayList<>();
        NodeList list = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++)
        {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                String id = element.getAttribute("id");
                String x = element.getAttribute("x");
                String y = element.getAttribute("y");
                String color = element.getAttribute("color");
                Transition t = new Transition(Integer.parseInt(id), Integer.parseInt(x), Integer.parseInt(y));
                if (color != null && !color.equals(""))
                {
                    Color c = Color.decode(color);
                    t.setColor(c);
                }
                transitions.add(t);
            }
        }

        return transitions;
    }

    private List<Arc> loadArcs(Node root, List<Place> places, List<Transition> transitions) throws Exception
    {
        //arc attributes -> id, from, to, n, inhibit
        List<Arc> arcs = new ArrayList<>();
        NodeList list = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++)
        {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                String id = element.getAttribute("id");
                String from = element.getAttribute("from");
                String to = element.getAttribute("to");
                String n = element.getAttribute("n");
                String inhibit = element.getAttribute("inhibit");

                int fromId = Integer.parseInt(from);
                int toId = Integer.parseInt(to);
                int arcId = Integer.parseInt(id);
                int cardinality = Integer.parseInt(n);
                boolean isInhibition = Boolean.parseBoolean(inhibit);
                boolean inputArc = false;
                Transition targetTransition = null;
                Place targetPlace = null;
                NodeList nl = element.getChildNodes();
                List<Point> pointsList = new ArrayList<>();

                for (int p = 0; p < nl.getLength(); p++)
                {
                    Node pointNode = nl.item(p);
                    if (pointNode.getNodeType() == Node.ELEMENT_NODE)
                    {
                        Element pointElement = (Element) pointNode;
                        int x = Integer.parseInt(pointElement.getAttribute("x"));
                        int y = Integer.parseInt(pointElement.getAttribute("y"));
                        pointsList.add(new Point(x, y));
                    }
                }
                Point[] points = new Point[pointsList.size()];
                points = pointsList.toArray(points);

                for (int t = 0; t < transitions.size(); t++)
                {
                    Transition tr = transitions.get(t);
                    if (tr.getId() == toId)
                    {
                        inputArc = true;
                        targetTransition = tr;
                        break;
                    }
                    else if (tr.getId() == fromId)
                    {
                        targetTransition = tr;
                        break;
                    }
                }

                for (int p = 0; p < places.size(); p++)
                {
                    Place pl = places.get(p);
                    if (inputArc)
                    {
                        if (pl.getId() == fromId)
                        {
                            targetPlace = pl;
                            break;
                        }
                    }
                    else
                    {
                        if (pl.getId() == toId)
                        {
                            targetPlace = pl;
                            break;
                        }
                    }
                }

                if (targetPlace == null || targetTransition == null)
                {
                    throw new Exception("Arc wrong 'from' or 'to' id");
                }
                Arc newArc;
                if (isInhibition)
                {
                    newArc = new InhibitionArc(arcId, cardinality, targetTransition, targetPlace);
                }
                else
                {
                    if (inputArc)
                    {
                        newArc = new InputArc(arcId, cardinality, targetTransition, targetPlace);
                    }
                    else
                    {
                        newArc = new OutputArc(arcId, cardinality, targetTransition, targetPlace);
                    }
                }
                if (points.length >= 2)
                {
                    newArc.points = points;
                }
                arcs.add(newArc);

            }
        }

        return arcs;
    }

    @Override
    public void saveNet(File file, Net net) throws Exception
    {
        try
        {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element rootElement = document.createElement("net");
            rootElement.setAttribute("name", net.getName());
            document.appendChild(rootElement);

            Element places = document.createElement("places");
            Element transitions = document.createElement("transitions");
            Element arcs = document.createElement("arcs");

            for (Place place : net.getPlaces())
            {
                Element p = document.createElement("place");
                p.setAttribute("id", Integer.toString(place.getId()));
                p.setAttribute("x", Integer.toString(place.getX()));
                p.setAttribute("y", Integer.toString(place.getY()));
                p.setAttribute("tokens", Integer.toString(place.getTokensCount()));
                p.setAttribute("color", "#" + Integer.toHexString(place.getColor().getRGB()).substring(2));
                places.appendChild(p);
            }

            for (Transition transition : net.getTransitions())
            {
                Element t = document.createElement("transation");
                t.setAttribute("id", Integer.toString(transition.getId()));
                t.setAttribute("x", Integer.toString(transition.getX()));
                t.setAttribute("y", Integer.toString(transition.getY()));
                t.setAttribute("color", "#" + Integer.toHexString(transition.getColor().getRGB()).substring(2));
                transitions.appendChild(t);
            }

            for (Arc arc : net.getArcs())
            {
                Element a = document.createElement("arc");

                a.setAttribute("id", Integer.toString(arc.getId()));
                a.setAttribute("n", Integer.toString(arc.getCardinality()));

                int toId = 0;
                int fromId = 0;
                boolean inhibition = false;

                switch (arc.getArcType())
                {
                    case INPUT:
                        toId = arc.getTransition().getId();
                        fromId = arc.getPlace().getId();
                        break;
                    case OUTPUT:
                        fromId = arc.getTransition().getId();
                        toId = arc.getPlace().getId();
                        break;
                    case INHIBITION:
                        inhibition = true;
                        toId = arc.getTransition().getId();
                        fromId = arc.getPlace().getId();
                        break;
                    default:
                        throw new Exception("Invalid arc id");
                }
                a.setAttribute("to", Integer.toString(toId));
                a.setAttribute("from", Integer.toString(fromId));
                a.setAttribute("inhibit", Boolean.toString(inhibition));

                for (int i = 0; i < arc.points.length; i++)
                {
                    Element p = document.createElement("point");
                    p.setAttribute("x", Integer.toString(arc.points[i].x));
                    p.setAttribute("y", Integer.toString(arc.points[i].y));
                    a.appendChild(p);
                }

                arcs.appendChild(a);
            }

            rootElement.appendChild(places);
            rootElement.appendChild(transitions);
            rootElement.appendChild(arcs);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(rootElement);
            StreamResult streamResult = new StreamResult(file);
            t.transform(source, streamResult);
            JOptionPane.showMessageDialog(null, "Project saved");
        }
        catch (ParserConfigurationException ex)
        {
            Logger.getLogger(XmlNetLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
