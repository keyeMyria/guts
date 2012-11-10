/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui;

import guts.gui.entities.TowerIcon;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;
import org.jdesktop.swingx.mapviewer.WaypointRenderer;

/**
 *
 * @author patrick
 */
public class TrackDrawer extends WaypointPainter {
    
    public TrackDrawer(Set<Waypoint> waypoints) {
        this.waypoints = waypoints;
        
        this.setRenderer(new WaypointRenderer() {
                        
            @Override
            public boolean paintWaypoint(Graphics2D g, JXMapViewer map, Waypoint wp) {
            /*    
                g = (Graphics2D) g.create();
      //convert from viewport to world bitmap
      Rectangle rect = map.getViewportBounds();
      g.translate(-rect.x, -rect.y);

        //do the drawing
        g.setColor(Color.RED);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(2));

        int lastX = -1;
        int lastY = -1;
        for (Waypoint wpx : waypoints) {
         //convert geo to world bitmap pixel
         Point2D pt = map.getTileFactory().geoToPixel(wpx.getPosition(), map.getZoom());
         if (lastX != -1 && lastY != -1) {
            g.drawLine(lastX, lastY, (int) pt.getX(), (int) pt.getY());
         }
         lastX = (int) pt.getX();
         lastY = (int) pt.getY();
      }

      g.dispose(); */
                
                
                
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
            
            public void paint(Graphics2D g, JXMapKit map, int w, int h) {
      
    }
            
        });
    
    } 
        
    
        
/*
 *         
        
        
        
        
        
        

        
 */
    
    private Set<Waypoint> waypoints;
    

}
