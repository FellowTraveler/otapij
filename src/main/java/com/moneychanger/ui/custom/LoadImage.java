package com.moneychanger.ui.custom;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class LoadImage extends JPanel {

    BufferedImage image;
    Dimension size = new Dimension();

    public LoadImage(BufferedImage image) {
        try {
            this.image = image;
            size.setSize(475, 275);
            //size.setSize(image.getWidth(), image.getHeight());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid image file. Please select proper image", "Image Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
    }

    /**
     * Drawing an image can allow for more
     * flexibility in processing/editing.
     */
    protected void paintComponent(Graphics g) {
        // Center image in this component.
        int x = (getWidth() - size.width) / 2;
        int y = (getHeight() - size.height) / 2;
        g.drawImage(image, x, y, this);
    }

    public Dimension getPreferredSize() {
        return size;
    }

    public static void main(String[] args) throws IOException {
        String path = "/Users/administrator/Desktop/images.jpeg";
        BufferedImage image = ImageIO.read(new File(path));
        LoadImage test = new LoadImage(image);
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new JScrollPane(test));
        f.setSize(400, 400);
        f.setLocation(200, 200);
        f.setVisible(true);
        //showIcon(image);
    }
}
