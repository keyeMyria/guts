/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui;

import guts.gui.entities.Breakpoint;
import guts.gui.entities.TowerIcon;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedHashSet;
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

    private LinkedHashSet<Waypoint> wps;
    private LinkedHashSet<TowerIcon> towers;
    private WaypointRenderer wpRenderer;
    
    private Iterator it1;

    public TrackDrawer(
            LinkedHashSet<Waypoint> waypoints,
            LinkedHashSet<TowerIcon> towers) {
        wps = waypoints;
        this.towers = towers;

        it1 = wps.iterator();
    }

    @Override
    protected void doPaint(Graphics2D g, JXMapViewer map, int width, int height) {
        g = (Graphics2D) g.create();
        //convert from viewport to world bitmap
        Rectangle rect = map.getViewportBounds();
        g.translate(-rect.x, -rect.y);

        //do the drawing
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(2));

        int lastX = -1;
        int lastY = -1;
            /*Waypoint w = null;
            Iterator<Waypoint> it = wps.iterator();
            while(it.hasNext()) {
                
                w = it.next();
                if(!it.hasNext()) {
                    g.setColor(Color.BLUE);
                    Point2D pt = map.getTileFactory().geoToPixel(w.getPosition(), map.getZoom());
                    g.drawLine((int)pt.getX(),(int)pt.getY(),(int)pt.getX()+5,(int)pt.getY()+5);
                }
            }*/
            
         try {
            for (Waypoint wp : this.wps) {
                
                g.setColor(Color.BLUE);
                Point2D pt = ((Breakpoint) wp).getPoints(map.getZoom());
                
                if (lastX != -1 && lastY != -1) {
                    g.drawLine(lastX, lastY, (int) pt.getX(), (int) pt.getY());
                }
                
                lastX = (int) pt.getX();
                lastY = (int) pt.getY();
            }
         } catch (Exception e) {
             
         }
                


        for (TowerIcon tw : this.towers) {
            Point2D pt = map.getTileFactory().geoToPixel(tw.getPosition(), map.getZoom());

                int x = (int)pt.getX();
                int y = (int)pt.getY();
                
                g.setColor(Color.BLACK);

                try {
                    g.drawImage(ImageIO.read(TrackDrawer.class.getResource("/img/antennamast.png")), null, x-11, y-21);
                } catch (Exception ex) {
                    System.out.println("can't read the image");
                }
                
                g.drawString(tw.getName(), x+20, y+10);
        }

        g.dispose();
    }
    

}
