/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui;

import guts.Config;
import guts.entities.Location;
import guts.gui.comp.DrawableCanvas;
import guts.gui.comp.OSMViewer;
import guts.gui.comp.PopUpMenu;
import guts.gui.comp.RotatableImage;
import guts.gui.entities.*;
import guts.utils.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.event.MouseInputListener;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;
import org.jdesktop.swingx.mapviewer.WaypointRenderer;

/**
 *
 * @author Patrick Selge
 */
public final class MapPanel extends JLayeredPane {
    
    private LinkedHashSet<Waypoint> waypoints = new LinkedHashSet<Waypoint>();
    private LinkedHashSet<TowerIcon> towers = new LinkedHashSet<TowerIcon>();
            
    private TrackDrawer painter = new TrackDrawer(waypoints, towers);
    private GeoPosition geopos;
    
    private PopUpMenu popUpMenu;
    
    public Set<Waypoint> getWaypoints() {
        return this.waypoints;
    }
    
    
    
    
        
    
    
    public MapPanel(GUI controller) {        
        popUpMenu = new PopUpMenu(controller);
        osm = new OSMViewer();
        drawMapPanel();
        
        osm.addMouseListener(controller);

    }
    
    
    
    private void drawMapPanel() {   
        
        
        
 
        

        JPanel mapKit;
        
        // TODO rewrite this into a seperate class
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
        
        
        JPanel minimap = drawMinimap();
        this.add(minimap, JLayeredPane.POPUP_LAYER);
        
    }
    
    private DrawableCanvas drawMinimap() {
        DrawableCanvas minimap = new DrawableCanvas(481,AppWindow.FRAME_HEIGHT-Menubar.PANEL_HEIGHT-279,280,280);  
        minimap.setOpaque(true);
        minimap.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        minimap.setBackground(new Color(0, 0, 0, 175));
        
        JLayeredPane layeredMiniMap = new JLayeredPane();
        
        minimap.add(layeredMiniMap);
         
        DrawableCanvas layer1 = new DrawableCanvas(0,0,280,280);
        DrawableCanvas layer2 = new DrawableCanvas(0,0,280,280);
        
        jeep = new guts.gui.comp.RotatableImage(Config.VEHICLE_TOP,140, 140);
        antenna = new guts.gui.comp.RotatableImage("/img/antenna.png",140,140);        
        
        layeredMiniMap.add(layer1, JLayeredPane.DEFAULT_LAYER);
        layeredMiniMap.add(layer2, JLayeredPane.POPUP_LAYER);
        
        layer1.add(jeep);
        layer2.add(antenna);
        
        return minimap;
    }
    
    public RotatableImage getJeepTop() {
        return this.jeep;
    }
    
    public RotatableImage getAntenna() {
        return this.antenna;
    }
    
    public void showPopUpMenu(Component cmp, int x, int y) {
        popUpMenu.show((Component)osm, x, y);
        geopos = osm.convertPointToGeoPosition(new Point2D.Double(x, y));
    }
    
    public void setTower() {
        //String name = popUpMenu.askForTowerName();
        towers.add(new TowerIcon(geopos.getLatitude(), geopos.getLongitude(), "test"));
    }
    
    
    private RotatableImage jeep;
    private RotatableImage antenna;
    private OSMViewer osm;

    public void setViewPointToLocation(Location locat) {
        //osm.setCenterPosition(locat);
        waypoints.add(new Waypoint(locat.getLatitude(), locat.getLongitude()));
    }
    
}
