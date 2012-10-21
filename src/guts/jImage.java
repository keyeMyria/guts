/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author patrick
 */
public class jImage extends JPanel {  
    private Image img;  
  
    /**
     *
     * @param img
     */
    public jImage(String img) {  
        this(new ImageIcon(img).getImage());  
    }  
  
    public jImage(Image img) {  
        this.img = img;  
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));  
        setPreferredSize(size);  
        setMinimumSize(size);  
        setMaximumSize(size);  
        setSize(size);  
        setLayout(null);  
    }  
  
    public void paintComponent(Graphics g) {  
        g.drawImage(img, 0, 0, null);  
    }
}
