/**
 * GUTS - GPS Utilized Tracking System
 */
package guts.gui;

import guts.gui.comp.AxisVisualization;
import guts.gui.comp.StatusBox;
import guts.entities.Location;
import guts.gui.comp.RotatableImage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.Set;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import org.jdesktop.swingx.mapviewer.Waypoint;

/**
 *
 * @author Patrick Selge
 */
public class GUI extends JFrame implements Runnable,MouseInputListener,ActionListener {
            
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
        
        appWindow = new AppWindow(this);
               
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
    
    public AxisVisualization getJeepFront() {
        return appWindow.getSidebar().getJeepFront();
    }
    
    public AxisVisualization getJeepSide() {
        return appWindow.getSidebar().getJeepSide();
    }
    
    private AppWindow appWindow;  

    @Override
    public void mousePressed(MouseEvent evt) {        
        if (SwingUtilities.isRightMouseButton(evt)) {
            appWindow.getMapPanel().showPopUpMenu(
                    evt.getComponent(), 
                    evt.getX(), 
                    evt.getY());
        }
     }
 

       

    @Override
    public void mouseClicked(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() instanceof JMenuItem) {
            JMenuItem jmi = (JMenuItem)ae.getSource();
            
            if(jmi.getName().equals("btn_new_tower")) {
                appWindow.getMapPanel().setTower();
            }
        }

    }
    
    
}
