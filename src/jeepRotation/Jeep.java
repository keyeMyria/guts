/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jeepRotation;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Jeep {
	private int centerX, centerY; //The center of the fan
	private double angel; //Angel in radians
        private URL jeepResource;
        BufferedImage img;

	public Jeep(int centerX, int centerY) {
            jeepResource = Jeep.class.getResource("/jeepRotation/Jeep.top.png");
            
            try {
                img = ImageIO.read(jeepResource);
            } catch (IOException e) {
                System.out.println("Can't open the image you defined");
            }
	}
	
	public void update() {
		angel = addRad(angel, 0.005);
	}
	
	private double addRad(double current, double addition) {
            double value = current + addition;

            if (value >= 2 * Math.PI) {
                value -= 2 * Math.PI;
            }
            return value;
	}

	public void draw(Graphics g) {
                
            
		Graphics2D gx = (Graphics2D) g;
		//Make it look a little prettier
		gx.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		//Rotate 'angel' radians around the center
		gx.rotate(angel, 250, 250);
		gx.drawImage(img, 250-img.getWidth()/2, 250-img.getHeight()/2, null);

		
	}
}
