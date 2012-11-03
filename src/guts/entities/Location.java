

package guts.entities;

import java.util.Date;
import org.jdesktop.swingx.mapviewer.GeoPosition;

/**
 * This class represents the location of a object in a two-dimensonal plane.
 * @author Cedric Ohle
 * @author Patrick Selge
 */
public class Location extends GeoPosition {
    private Date timestamp;

    public Location(double latitude, double longitude) {
        this(latitude, longitude, new Date(System.currentTimeMillis()));
    }
    
    public Location(double latitude, double longitude, Date date) {
        super(latitude, longitude);
        this.timestamp = date;
    }

    /**
     * Sets the timestamp
     * @param timestamp as timestamp
     */
    public void setTimestamp(Date timestamp){
        this.timestamp = timestamp;
    }
    
    /**
     * Returns the latitude
     * @return latitude as float 
     */
    public Date getTimestamp(){
        return this.timestamp;
    }
    
    @Override
    public String toString(){
        return this.timestamp + " " + this.getLatitude() + " " + this.getLongitude();
        
    }
}
