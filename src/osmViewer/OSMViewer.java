/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osmViewer;

import osmViewer.data.Tower;
import guts.Config;
import guts.entities.Location;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.event.MouseInputListener;
import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;

/**
 *
 * @author Patrick Selge
 */
public final class OSMViewer extends JXMapKit {
    private Set<osmViewer.data.Waypoint> waypoints;
    private TrackDrawer painter;
    
    public OSMViewer(Set<osmViewer.data.Waypoint> waypoints) {
        // Configuring the MapKit to our needs
        this.setName("OSMViewer");
        this.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        this.setMiniMapVisible(false);
        this.setZoomSliderVisible(false); 
        
        this.setAddressLocation(new GeoPosition(Config.STARTLAT, Config.STARTLON));
        
        // Getting the MapViewer out of the Kit
        mapView = this.getMainMap();
        mapView.setZoom(1); // Setting the default zoom to it's maximum
        mapView.setZoomEnabled(false); // disabling the mousewheel zoom
        
        // Initializes the Mouse and Actionlisteners, as well as the Popup Menu
        this.initControlElements();
        
        this.waypoints = waypoints;
    }

    public void setTowers(ArrayList<Tower> towers) {
        this.towers = towers;
        painter.setTowers(towers);
    }
    
    public Set<osmViewer.data.Waypoint> getWaypoints() {
        return this.waypoints;
    }
    
    /**
     * Sets the zoomlevel. Can be limited by the MAX_ZOOM constant.
     * 
     * @param zoomlevel Sets the zoom level.
     */
    @Override
    public void setZoom(int zoomlevel) {
        if(zoomlevel < MAX_ZOOM) {
            super.setZoom(zoomlevel);
        }
    }
    
    public void setTower() {
        String towerName = popUpMenu.askForTowerName();
        towers.add(new Tower(geopos.getLatitude(), geopos.getLongitude(), towerName));
    }
    
    public void setOverlayPainter(TrackDrawer painter) {
        this.painter = painter;
        mapView.setOverlayPainter(this.painter);
    }
    
    public Rectangle getViewportBounds() {
        return mapView.getViewportBounds();
    }


    public Point2D[] getPixelMapOfLocation(Location location) {
        Point2D[] points = new Point2D[MAX_ZOOM];
        for(int i = 0; i < MAX_ZOOM; i++) {
            points[i] = mapView.getTileFactory().geoToPixel(location, i+1);
        }
        
        return points;
    }
    
    private void initControlElements() {
        EventListener listener = new PopUpListener(this);
        
        popUpMenu = new PopUpMenu((ActionListener) listener);
        mapView.addMouseListener((MouseInputListener) listener);
    }
    
    
    public void showPopUpMenu(Component cmp, int x, int y) {
        popUpMenu.show((Component)this, x, y);
        geopos = mapView.convertPointToGeoPosition(new Point2D.Double(x, y));
    }
    
        
    

    
    private JXMapViewer mapView;
    
    private static final int MAX_ZOOM = 5; 
    private GeoPosition geopos;
    protected PopUpMenu popUpMenu;
    
    private ArrayList<Tower> towers;

}
