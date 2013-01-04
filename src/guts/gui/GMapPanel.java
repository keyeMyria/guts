/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui;

import osmViewer.data.Waypoint;
import guts.gui.comp.Minimap;
import guts.Config;
import guts.entities.Location;
import osmViewer.data.WaypointSet;
import osmViewer.OSMViewer;
import guts.gui.comp.RotatableImage;
import guts.utils.*;
import java.awt.Color;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Patrick Selge
 */
public class GMapPanel extends JLayeredPane {

    public Set<Waypoint> getWaypoints() {
        return this.waypoints;
    }

    public GMapPanel(GUI controller) {        
        osm = new OSMViewer();
        drawMapPanel();
    }
    
    
    
    private void drawMapPanel() {   
        JPanel mapKit;
        
        // checks if the computer can reach one of the specified urls
        if(ConnectionCheck.isOnline("http://www.google.com") ||
           ConnectionCheck.isOnline("http://www.heise.de")) {
            


            osm.setOverlayPainter(painter);
            
            //MouseInputListener rcl = new RightClickListener();
            //osm.addMouseListener(mia);

            mapKit = osm;
        } else {
            mapKit = new JPanel();
            mapKit.setBackground(Color.DARK_GRAY);
        }

            mapKit.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            mapKit.setBounds(0, 0, 752, 602);
                   
            
            
            this.add(mapKit, JLayeredPane.DEFAULT_LAYER);
        
        
        Minimap minimap = drawMinimap();
        
        this.add(minimap, JLayeredPane.POPUP_LAYER);
        
    }
    
    /**
     * Creates The minimap and adds all of its components to it
     * 
     * @return Minimap
     */
    private Minimap drawMinimap() {
        Minimap minimap = new Minimap();
        
        // Create the components for the minimap
        jeep = new RotatableImage(Config.VEHICLE_TOP,140, 140);
        antenna = new RotatableImage("/img/antenna.png",140,140);      
        
        minimap.addComponentToNewLayer(jeep);
        minimap.addComponentToNewLayer(antenna);
        
        return minimap;
    }
    
    public void setViewPointToLocation(Location locat) {
        double lat = locat.getLatitude();
        double lon = locat.getLongitude();
        
        Waypoint wp = new Waypoint(locat.getLatitude(), locat.getLongitude());
        wp.setPoints(osm.getPixelMapOfLocation(wp));
        
        waypoints.add(wp);
        
    }
    
    
    
    public RotatableImage getJeepTop() {
        return this.jeep;
    }
    
    public RotatableImage getAntenna() {
        return this.antenna;
    }
    
    
    
    
    
    
    private RotatableImage jeep;
    private RotatableImage antenna;
    private OSMViewer osm;
    
    private WaypointSet waypoints = new WaypointSet();
            
    private TrackDrawer painter = new TrackDrawer(waypoints);

    
    
    
}
