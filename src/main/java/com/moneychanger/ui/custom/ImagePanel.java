package com.moneychanger.ui.custom;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImagePanel extends JPanel {

    BufferedImage image;
    Dimension size = new Dimension();

    public ImagePanel() {
    }
    
    
    public void SetImageBuffer(BufferedImage image) {
        try {
            this.image = image;
            size.setSize(this.image.getWidth(),this.image.getHeight());
            //size.setSize(image.getWidth(), image.getHeight());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid image file. Please select proper image", "Image Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
      public boolean SetImage(String imagePath) {
                
        if (null == imagePath) {
            JOptionPane.showMessageDialog(this, "Image Path is Bad! Set In Config!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (3 > imagePath.length()) {
            JOptionPane.showMessageDialog(this, "Image Path is Too Short! Set In Config!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        File filePath = null;

        try {
            filePath = new File(imagePath);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Image Path is Invalid!", e.getMessage(), JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (null == filePath) {
            JOptionPane.showMessageDialog(this, "Image Path is NULL!", "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!filePath.exists()) {
            JOptionPane.showMessageDialog(this, "Image Path dosn't exist!", "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }

            BufferedImage image = null;
            try {
                try {
                    image = ImageIO.read(filePath);
                } catch (IOException ex) {
                    Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Unable To Load Image!", e.getMessage(), JOptionPane.ERROR_MESSAGE);
                return false;

            }

        if (null == image) {
            JOptionPane.showMessageDialog(this, "Image Is Null!", "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
       
        image.getWidth();
        
        SetImageBuffer(image);
        
        return true;
    }


    /**
     * Drawing an image can allow for more
     * flexibility in processing/editing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Center image in this component.
        
        int x = (getWidth() > size.width ) ? (getWidth() - size.width) / 2 : 0;
        int y = (getHeight() > size.height ) ? (getHeight() - size.height) / 2 : 0;
        g.drawImage(image, x, y, this);
    }

    @Override
    public Dimension getPreferredSize() {
        return size;
    }

    public static void main(String[] args) throws IOException {
        String path = "/Users/administrator/Desktop/images.jpeg";
        BufferedImage image = ImageIO.read(new File(path));
        ImagePanel test = new ImagePanel();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new JScrollPane(test));
        f.setSize(400, 400);
        f.setLocation(200, 200);
        f.setVisible(true);
        //showIcon(image);
    }
}
