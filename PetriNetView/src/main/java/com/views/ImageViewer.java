/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author Honza
 */
public class ImageViewer
{

    private final JDialog dialog;
    private final JPanel imagePanel;

    public ImageViewer(Window window, File imageFile)
    {
        BufferedImage bi;
        try
        {
            bi = ImageIO.read(imageFile);
        }
        catch (IOException ex)
        {
            bi = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        }
        dialog = new JDialog(window);
        imagePanel = new ImageView(bi);
        dialog.add(imagePanel);
        dialog.pack();
        dialog.setLocationRelativeTo(window);
    }

    public void show()
    {
        dialog.setVisible(true);
    }

    private class ImageView extends JPanel
    {

        private final Image image;

        public ImageView(Image image)
        {
            this.image = image;
            setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, null);
        }

    }

}
