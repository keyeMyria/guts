/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui.comp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class RotatableImage extends JPanel implements java.util.Observer {
    private int centerX, centerY; //Center of the Image
    private int posX, posY;
    private double angel = 0; //Angel in radians
    private BufferedImage img;

    public RotatableImage(String str, int posX, int posY) {
        URL imgResource = RotatableImage.class.getResource(str);
        this.posX = posX;
        this.posY = posY;

        try {
            img = ImageIO.read(imgResource);
        } catch (IOException e) {
            System.out.println("Can't open the image you defined");
            return;
        }

        centerX = img.getWidth()/2;
        centerY = img.getHeight()/2;
        
        this.setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D gx = (Graphics2D) g;
            gx.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

            gx.rotate(angel, posX, posY);
            gx.drawImage(img, posX-centerX, posY-centerY, null);
    }

    public void rotateTo(double angel) {
        this.angel = angel;
    }
    
    @Override
    public void update(Observable t, Object o) {
        rotateTo(Math.toRadians(((Double)o).doubleValue()));
    }
}
