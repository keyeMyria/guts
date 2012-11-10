/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui;

import guts.gui.events.RightClickListener;
import guts.Config;
import guts.gui.comp.DrawableCanvas;
import guts.gui.comp.OSMViewer;
import guts.gui.comp.PopUpMenu;
import guts.gui.comp.RotatableImage;
import guts.gui.entities.TowerIcon;
import guts.utils.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
    
    private Set<Waypoint> waypoints = new HashSet<Waypoint>();
    
    private Set<TowerIcon> towers = new HashSet<TowerIcon>();
    private Set<Breakpoint> breakpoints = new HashSet<Breakpoint>();
            
    private WaypointPainter painter = new WaypointPainter();
    GeoPosition geopos;
    
    private JPopupMenu popUpMenu;
    
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
            

            waypoints.add(new Waypoint(52.483791,13.226141));
            
            painter.setWaypoints(waypoints);
            
            painter.setRenderer(new WaypointRenderer() {
                @Override
                public boolean paintWaypoint(Graphics2D g, JXMapViewer map, Waypoint wp) {
                    if(wp instanceof TowerIcon) {
                        TowerIcon towerIcon = (TowerIcon) wp;
                        try {
                            g.drawImage(ImageIO.read(MapPanel.class.getResource("/img/antennamast.png")), null, -11,-21);
                        } catch (Exception ex) {
                            Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        g.setColor(Color.BLACK);
                        g.drawString(towerIcon.getName(), 20, 4);
                    } else {
                        g.setColor(Color.BLUE);
                        g.drawOval(0, 0, 5, 5);
                        g.setBackground(Color.BLUE);
                    }
                    return true;
                }
            });

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
    }
    
    public void setTower() {
        System.out.print("New tower created");
        waypoints.add(new TowerIcon(52.483791,13.226141, "Test"));
    }
    
    
    
    
    
    private RotatableImage jeep;
    private RotatableImage antenna;
    private OSMViewer osm;
    
}
