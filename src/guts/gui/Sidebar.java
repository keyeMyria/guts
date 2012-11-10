/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui;

import guts.gui.comp.AxisVisualization;
import guts.gui.comp.StatusBox;
import guts.Config;
import guts.entities.Axis;
import guts.entities.Location;
import guts.gui.comp.DrawableCanvas;
import guts.gui.comp.RotatableImage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.text.MessageFormat;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Patrick Selge
 */
public final class Sidebar extends JPanel {
    
    public Sidebar() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        
        drawLeftPanel();
    }
    
    private void drawLeftPanel() {
        // Initializing and configuring the 'Status Panel'
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel,BoxLayout.PAGE_AXIS));
        statusPanel.setBorder(BorderFactory.createTitledBorder("Sensordaten"));
        statusPanel.setPreferredSize(new Dimension(PANEL_WIDTH,240));
        
        drawStatusPanel(statusPanel);
  
        statusPanel.add(Box.createRigidArea(new Dimension(0,5)));

        
        // Initializing and configuring the 'Visual Panel' (The two bubbles with the cars in'em
        JPanel visPanel = new JPanel();
        visPanel.setLayout(new BorderLayout());
        visPanel.setPreferredSize(new Dimension(PANEL_WIDTH,360));
        
        // Creating the visualization of the front
        jeepFront = new AxisVisualization(Config.VEHICLE_FRONT,0) {
            @Override
            public void update(Observable t, Object o) {
                image.rotateTo(Math.toRadians(((Axis)o).getRoll()));
                text.setText(((Axis)o).getRoll());
            }
        };
        visPanel.add(jeepFront);

        // Creating the visualization of the side
        jeepSide = new AxisVisualization(Config.VEHICLE_SIDE,185) {
            @Override
            public void update(Observable t, Object o) {
                image.rotateTo(Math.toRadians(((Axis)o).getPitch()));
                text.setText(((Axis)o).getPitch());
            }
        };
        visPanel.add(jeepSide);
        
        
        
        // Adding the two panels to the sidebar
        this.add(statusPanel);
        this.add(visPanel);
    }
    
    private void drawStatusPanel(JPanel panel) {
        latitudeStatus = new StatusBox("Latitude") {
            @Override
            public void update(Observable t, Object o) {
                this.textField.setText(
                        MessageFormat.format("{0,number,##.#####}",
                        ((Location)o).getLatitude()));
            }
        };
        panel.add(latitudeStatus);
        
        longitudeStatus = new StatusBox("Longitude") {
            @Override
            public void update(Observable t, Object o) {
                this.textField.setText(
                        MessageFormat.format("{0,number,##.#####}",
                        ((Location)o).getLongitude()));
            }
        };
        panel.add(longitudeStatus);
        
        orientationStatus = new StatusBox("Ausrichtung");
        panel.add(orientationStatus);
        
        speedStatus = new StatusBox("Geschwindigkeit");
        panel.add(speedStatus);
    }
    
    
    
    
    public StatusBox getLatitudeStatusBox() {
        return latitudeStatus;
    }
    
    public StatusBox getLongitutdeStatusBox() {
        return longitudeStatus;
    }
    
    public AxisVisualization getJeepSide() {
        return jeepSide;
    }
    
    public AxisVisualization getJeepFront() {
        return jeepFront;
    }
    
    public StatusBox getOrientationStatusBox() {
        return orientationStatus;
    }
    
    public StatusBox getSpeedStatusBox() {
        return speedStatus;
    }
    
    
    private StatusBox latitudeStatus;
    private StatusBox longitudeStatus;
    private StatusBox orientationStatus;
    private StatusBox speedStatus;
    
    private AxisVisualization jeepSide;
    private AxisVisualization jeepFront;
    
    public static final int PANEL_WIDTH = 250;
    public static final int PANEL_HEIGHT = 600;
    
    public static final int PITCH_PANEL = 0;
    public static final int YAWN_PANEL = 1;
}
