/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui;

import guts.Config;
import guts.gui.comp.DrawableCanvas;
import guts.gui.comp.RotatableImage;
import guts.utils.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.event.MouseInputListener;
import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;
import org.jdesktop.swingx.mapviewer.WaypointRenderer;
import org.jdesktop.swingx.painter.Painter;

/**
 *
 * @author Patrick Selge
 */
public final class MapPanel extends JLayeredPane {
    
    private Set<Waypoint> waypoints = new HashSet<Waypoint>();
    private WaypointPainter painter = new WaypointPainter();
    GeoPosition geopos;
    
    private JPopupMenu popUpMenu;
    
    public class TowerIcon extends Waypoint {
        public TowerIcon(double x, double y) {
            super(x,y);
        }
    }
    
    // used to pan using press and drag mouse gestures
    private class PanMouseInputListener implements MouseInputListener {
        
        public void mousePressed(MouseEvent evt) {
            
            if (SwingUtilities.isRightMouseButton(evt)) {
                getGeoPosOfEvt(evt);
                
                popUpMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                
                repaint();
            }
        }
        
        private void getGeoPosOfEvt(MouseEvent evt) {
            Rectangle bounds = mv.getViewportBounds();
            double x = evt.getX();
            double y = evt.getY();
            //mv.setCenter(new Point2D.Double(x, y));
            
            geopos = mv.convertPointToGeoPosition(new Point2D.Double(x,y));
            
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent me) {
        }

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
    }
    
    public MapPanel() {
        drawMapPanel();
    }
    
    
    
    private void drawMapPanel() {   
        
        popUpMenu = new JPopupMenu();
        
        JMenuItem newTower = new JMenuItem("Neuer Tower");
        popUpMenu.add(newTower);
        JMenuItem newWaypoint = new JMenuItem("Neuer Wegpunkt");
        popUpMenu.add(newWaypoint);
        JMenuItem disableSimulation = new JMenuItem("Stope Simulation");
        disableSimulation.setEnabled(false);
        popUpMenu.add(disableSimulation);

 
        newTower.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                waypoints.add(new TowerIcon(geopos.getLatitude(), geopos.getLongitude()));
            }
        });
        
        newWaypoint.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                waypoints.add(new Waypoint(geopos.getLatitude(), geopos.getLongitude()));
            }
        });

        
        
        
        
        
        
        JPanel mapKit;
        
        // TODO rewrite this into a seperate class
        // checks if the computer can reach one of the specified urls
        if(ConnectionCheck.isOnline("http://www.google.com") ||
           ConnectionCheck.isOnline("http://www.heise.de")) {
            
            mk = new JXMapKit();
            mk.setName("mapKit");
            

            mk.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps); 

            mk.setMiniMapVisible(false);
            mk.setZoomSliderVisible(false);
            // TODO define a global variable, that sets the first Location to the mapkit and the simulation
            mk.setAddressLocation(new GeoPosition(52.483791,13.226141));
            waypoints.add(new Waypoint(52.483791,13.226141));
            painter.setWaypoints(waypoints);
            
            painter.setRenderer(new WaypointRenderer() {
                public boolean paintWaypoint(Graphics2D g, JXMapViewer map, Waypoint wp) {
                    if(wp instanceof TowerIcon) {
                        g.setColor(Color.RED);
                        g.drawLine(-5,-5,+5,+5);
                        g.drawLine(-5,+5,+5,-5);
                    } else {
                        g.setColor(Color.BLUE);
                        g.drawOval(0, 0, 5, 5);
                        g.setBackground(Color.BLUE);
                    }
                    return true;
                }
            });
            
            mv = mk.getMainMap();
            mv.setOverlayPainter(painter);
            
            MouseInputListener mia = new PanMouseInputListener();
            mv.addMouseListener(mia);

            mapKit = mk;
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
        DrawableCanvas minimap = new DrawableCanvas(481,Mainframe.FRAME_HEIGHT-Menubar.PANEL_HEIGHT-279,280,280);  
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
    
    public RotatableImage getJeep() {
        return this.jeep;
    }
    
    public RotatableImage getAntenna() {
        return this.antenna;
    }
    
    private RotatableImage jeep;
    private RotatableImage antenna;
    private JXMapKit mk;
    private JXMapViewer mv;
    
}
