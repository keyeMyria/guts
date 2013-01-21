/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osmViewer;

import osmViewer.data.Tower;
import guts.Config;
import guts.entities.Location;
import guts.gui.TrackDrawer;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;
import java.util.EventListener;
import java.util.LinkedHashSet;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
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
    
    public OSMViewer() {
        // Configuring the MapKit to our needs
        this.setName("OSMViewer");
        this.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        this.setMiniMapVisible(false);
        this.setZoomSliderVisible(false); 
        
        // TODO replace with global definition!!!
        this.setAddressLocation(new GeoPosition(Config.STARTLAT, Config.STARTLON));
        
        // Getting the MapViewer out of the Kit
        mapView = this.getMainMap();
        mapView.setZoom(1); // Setting the default zoom to it's maximum
        mapView.setZoomEnabled(false); // disabling the mousewheel zoom
        
        towers.add(new Tower(52.493791, 13.226141, "Test"));
        
        // Initializes the Mouse and Actionlisteners, as well as the Popup Menu
        this.initControlElements();
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
        painter.setTowers(towers);
        mapView.setOverlayPainter(painter);
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
    private PopUpMenu popUpMenu;
    
    private LinkedHashSet<Tower> towers = new LinkedHashSet<Tower>();

    
    
    

}
