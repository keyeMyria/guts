/**
 * GUTS - GPS Utilized Tracking System
 */
package guts;

import guts.gui.*;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Patrick Selge
 */
public class GUI extends JFrame implements Runnable {
            
    /**
     * used by GUTS for the creation of a thread
     */
    @Override 
    public void run() {
        drawInterface();

        this.setVisible(true);
    }
    
    private void drawInterface() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setBackground(Color.lightGray);
        
        mainframe = new Mainframe(this.getContentPane());
               
        pack();
    }
    
    public void rotateJeep(double val) {
        mainframe.getJeep().rotateTo(Math.toRadians(val));
    }
    
    public void rotateAntenna(double val) {
        mainframe.getAntenna().rotateTo(Math.toRadians(val));
    }
    
    private Mainframe mainframe;
  
    
}
