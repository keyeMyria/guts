
package osmViewer.data;

import guts.entities.Location;
import org.jdesktop.swingx.mapviewer.Waypoint;

/**
 * The tower class is used as a waypoint, but also as
 * location to calculate the antenna orientation
 * 
 * @author Patrick Selge
 * @author Cedric Ohle
 */
public class Tower extends Location {
    
    /**
     * Initializes the name attribute and passes
     * the x and y parameters to the Location class
     * 
     * @param x Latitude
     * @param y Longitude
     * @param name Name of the Tower
     * @see Waypoint
     */
    public Tower(double x, double y, String name) {
        super(x,y);
        this.name = name;
    }

    /**
     * Returns the name of the tower
     * 
     * @return Name of the tower
     */
    public String getName() {
        return this.name;
    }
    
    private String name;
}
