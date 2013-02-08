/**
 * GUTS - GPS Utilized Tracking System
 * GUI - Graphical User Interface
 * 
 * This Package includes all the Gui Components.
 * It communicates through observers and can be
 * called by the GUTS controller.
 */
package guts.gui;

import guts.gui.comp.AxisVisualization;
import guts.gui.comp.StatusBox;
import guts.entities.Location;
import guts.gui.comp.RotatableImage;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*; 
import osmViewer.data.Tower;

/**
 * The GUI Controller communicates with the GUTS Controller
 * 
 * @author Patrick Selge
 */
public class GUI extends JFrame {
            


    /**
     * Builds up the interface
     */
    public void drawInterface() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setBackground(Color.lightGray);
             
        appWindow = new AppWindow(this);
               
        pack();
        this.setVisible(true);
    }
       
    /**
     * Need to be rewritten as observer
     * 
     * @deprecated
     * @param locat 
     */
    public void moveToWaypoint(Location locat) {
        appWindow.getMapPanel().setViewPointToLocation(locat);
    }

    // Getter
    // ------
    public RotatableImage getJeepTop() {
        return appWindow.getMapPanel().getJeepTop();
    }
    
    public AxisVisualization getJeepFront() {
        return appWindow.getSidebar().getJeepFront();
    }
    
    public RotatableImage getAntenna() {
        return appWindow.getMapPanel().getAntenna();
    }
    
    public AxisVisualization getJeepSide() {
        return appWindow.getSidebar().getJeepSide();
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
    
    public void rotateToAngle(double angle) {
        appWindow.getMapPanel().rotateAntennaTo(angle);
    }
    
    public void setTowers(ArrayList<Tower> towers) {
        appWindow.getMapPanel().setTowers(towers);
        appWindow.getMenuBar().setTowers(towers);
        
    }

    // Attributes and Constants
    // ------------------------
    private AppWindow appWindow;  

    public Tower getActiveTower() {
        return appWindow.getMenuBar().getActiveTower();
    }

    public void setActiveTower(Tower tower) {
        appWindow.getMenuBar().setActiveTower(tower);
    }
}
