/**
 * GUTS - GPS Utilized Tracking System
 */
package guts;

import guts.entities.Location;
import guts.gui.*;

import java.awt.*;
import java.util.Set;
import javax.swing.*;
import org.jdesktop.swingx.mapviewer.Waypoint;

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
    
    public void moveToWaypoint(Location locat) {
        Set<Waypoint> wp = mainframe.getMapPanel().getWaypoints();
        wp.add(new Waypoint(locat.getLatitude(), locat.getLongitude()));
        System.out.println(locat);
    }
    
    
    private Mainframe mainframe;  
    
}
