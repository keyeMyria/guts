/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 *
 * @author patrick
 */
public class EasyImage extends JLabel {
    BufferedImage img;
    Graphics g;
    
    public EasyImage(URL url) {
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("Can't open the image you defined");
            return;
        }
        
        g = img.createGraphics();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);    
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(img.getWidth()/2, -img.getHeight()/2);
        

        
        
                g2d.drawImage(img, 0, 0, null);

    }
    
    public void rotated(int degree) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(degree),img.getHeight()/2,img.getWidth()/2);
        
        System.out.println("rotated");
        
        this.repaint();
    }
}
