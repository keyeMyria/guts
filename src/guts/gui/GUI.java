/**
 * GUTS - GPS Utilized Tracking System
 */
package guts.gui;

import guts.gui.comp.AxisVisualisation;
import guts.gui.comp.StatusBox;
import guts.entities.Location;
import guts.gui.*;
import guts.gui.comp.RotatableImage;

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
        
        appWindow = new AppWindow(this.getContentPane());
               
        pack();
    }
        
    public void moveToWaypoint(Location locat) {
        Set<Waypoint> wp = appWindow.getMapPanel().getWaypoints();
        wp.add(new Waypoint(locat.getLatitude(), locat.getLongitude()));
        System.out.println(locat);
    }
    
    public RotatableImage getJeepTop() {
        return appWindow.getMapPanel().getJeepTop();
    }
    
    public StatusBox getLongitutdeStatusBox() {
        return appWindow.getSidebar().getLongitutdeStatusBox();
    }
    
    public StatusBox getLatitudeStatusBox() {
        return appWindow.getSidebar().getLatitudeStatusBox();
    }
    
    public StatusBox getOrientationStatusBox() {
        return appWindow.getSidebar().getOrientationStatusBox();
    }
    
    public StatusBox getSpeedStatusBox() {
        return appWindow.getSidebar().getSpeedStatusBox();
    }
    
    public AxisVisualisation getJeepFront() {
        return appWindow.getSidebar().getJeepFront();
    }
    
    public AxisVisualisation getJeepSide() {
        return appWindow.getSidebar().getJeepSide();
    }
    
    private AppWindow appWindow;  
    
}
