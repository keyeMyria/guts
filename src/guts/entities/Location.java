

package guts.entities;

import java.util.Date;

/**
 * This class represents the location of a object in a two-dimensonal plane.
 * @author Cedric Ohle
 */
public class Location {
    private double longitude;
    private double latitude;
    private Date timestamp;
    
    /**
     * Sets the longitude
     * @param longitude as float
     */
    public void setLongitude(double longitude){
        this.longitude = longitude;
    }
    
    /**
     * Sets the latitude
     * @param latitude as float
     */
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    
    /**
     * Returns the longitude
     * @return longitude as float
     */
    public double getLongitude(){
        return this.longitude;
    }
    
    /**
     * Returns the latitude
     * @return latitude as float 
     */
    public double getLatitude(){
        return this.latitude;
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
        return this.timestamp + " " + this.latitude + " " + this.longitude;
        
    }
}
