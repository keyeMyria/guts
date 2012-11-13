/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui.comp;

import guts.Config;
import guts.gui.GUI;
import java.awt.Rectangle;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;
import javax.swing.event.MouseInputListener;
import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;

/**
 *
 * @author Patrick Selge
 */
public class OSMViewer extends JXMapKit {
    
    public OSMViewer() {
        // Configuring the MapKit to our needs
        this.setName("OSMViewer");
        this.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps); // We are using the default OpenStreetMaps provider
        this.setMiniMapVisible(false); // We build our own Minimap
        this.setZoomSliderVisible(false); // We are going to use the buttons instead
        
        // TODO replace with global definition!!!
        this.setAddressLocation(new GeoPosition(Config.STARTLAT,Config.STARTLON));
        
        // Getting the MapViewer out of the Kit
        mapView = this.getMainMap();
        mapView.setZoom(1); // Setting the default zoom to it's maximum
        
        // Removing the unnecessary MouseWheelListeners 
        clearMWLs();        
    }
    
    @Override
    public void setZoom(int zoomlevel) {
        if(zoomlevel < MAX_ZOOM) {
            super.setZoom(zoomlevel);
        }
    }
    
    public void setOverlayPainter(WaypointPainter painter) {
        mapView.setOverlayPainter(painter);
    }
    
    public void addMouseListener(MouseInputListener mil) {
        mapView.addMouseListener(mil);
    }
    
    public Rectangle getViewportBounds() {
        return mapView.getViewportBounds();
    }
    
    public GeoPosition convertPointToGeoPosition(Point2D p) {
        return mapView.convertPointToGeoPosition(p);
    }
    
    private void clearMWLs() {
        // Get all the active MouseWheelListeners
        MouseWheelListener[] mwls = mapView.getMouseWheelListeners();
        // Iterate over the MouseWheelListeners and remove them
        for(MouseWheelListener mwl : mwls) {
            mapView.removeMouseWheelListener(mwl);
        }
    }
    
    public Point2D[] getPixelLocations(Waypoint wp) {
        Point2D[] points = new Point2D[MAX_ZOOM];
        for(int i = 0; i < MAX_ZOOM; i++) {
            points[i] = mapView.getTileFactory().geoToPixel(wp.getPosition(), i+1);
        }
        
        return points;
    }

    
    private JXMapViewer mapView;
    
    private static final int MAX_ZOOM = 5; 

    

}
