package guts.gui;

import guts.gui.comp.AxisVisualization;
import guts.gui.comp.StatusBox;
import guts.Config;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * The Sidebar is used as an information panel
 * where all the data get's visualized. It has 
 * no controls but implements observers.
 * 
 * @author Patrick Selge
 * @version 1.0
 */
public final class Sidebar extends JPanel {
    
    /**
     * Constructor - Sets all the parameters and then draws its elements
     * to itself
     */
    public Sidebar() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        
        // Adding the two panels to the sidebar
        this.add(drawStatusPanel());
        this.add(drawVisualPanel());
    }
    
    /**
     * Initializes and configures the status panel
     * and then calls the draw status boxes method
     * to fill itself up with content.
     * Also adds a spacing of '5' below itself.
     * 
     * @return Status Panel
     */
    private JPanel drawStatusPanel() {
        // Initializing and configuring the 'Status Panel'
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel,BoxLayout.PAGE_AXIS));
        statusPanel.setBorder(BorderFactory.createTitledBorder("Sensordaten"));
        statusPanel.setPreferredSize(new Dimension(PANEL_WIDTH,240));
        
        drawStatusBoxes(statusPanel);
        
        // Add some spacing below the 'Status Panel'
        statusPanel.add(Box.createRigidArea(new Dimension(0,5)));
        
        return statusPanel;
    }
    
    /**
     * Draws the status boxes and adds them to the JPanel 
     * specified as a parameter
     * 
     * @param panel Panel that holds all the status boxes
     */
    private void drawStatusBoxes(JPanel panel) {
        // Creating the 'Status Box' for the latitude
        latitudeStatus = new StatusBox("Latitude", StatusBox.TYPE_LATITUDE);
        panel.add(latitudeStatus);
        
        // Creating the 'Status Box' for the longitude
        longitudeStatus = new StatusBox("Longitude", StatusBox.TYPE_LONGITUDE);
        panel.add(longitudeStatus);
        
        // Creating the 'Status Box' for the orientation
        orientationStatus = new StatusBox("Ausrichtung");
        panel.add(orientationStatus);
        
        // Creating the 'Status Box' for the speed
        speedStatus = new StatusBox("Geschwindigkeit");
        panel.add(speedStatus);
    }
    
    /**
     * Initializes and configures itself. Then calls the 
     * draw axis visualization method to draw its content
     * 
     * @return Visual Panel
     */
    private JPanel drawVisualPanel() {
        // Initializing and configuring the 'Visual Panel' 
        JPanel visPanel = new JPanel();
        visPanel.setLayout(new BorderLayout());
        visPanel.setPreferredSize(new Dimension(PANEL_WIDTH,360));
        
        drawAxisVisualizations(visPanel);
        
        return visPanel;
    }
    
    /**
     * Draws the Axis Visualizations
     * 
     * @param panel Panel the Visualizations are drawn on to
     */
    private void drawAxisVisualizations(JPanel panel) {
        // Creating the visualization of the front
        jeepFront = new AxisVisualization(
                Config.VEHICLE_FRONT,
                0,
                AxisVisualization.AXIS_ROLL);
        panel.add(jeepFront);

        // Creating the visualization of the side
        jeepSide = new AxisVisualization(
                Config.VEHICLE_SIDE,
                185,
                AxisVisualization.AXIS_PITCH);
        panel.add(jeepSide);
    }
    
    // Getter
    // ------
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
    
    // Attributes and Constants
    // ------------------------
    private StatusBox latitudeStatus;
    private StatusBox longitudeStatus;
    private StatusBox orientationStatus;
    private StatusBox speedStatus;
    
    private AxisVisualization jeepSide;
    private AxisVisualization jeepFront;
    
    private static final int PANEL_WIDTH = 250;
    private static final int PANEL_HEIGHT = 600;
}
