

package guts.entities;

import java.sql.Timestamp;

/**
 * This class represents the location of a object in a two-dimensonal plane.
 * @author Cedric Ohle
 */
public class Location {
    private float longitude;
    private float latitude;
    private Timestamp timestamp;
    
    /**
     * Sets the longitude
     * @param longitude as float
     */
    public void setLongitude(float longitude){
        this.longitude = longitude;
    }
    
    /**
     * Sets the latitude
     * @param latitude as float
     */
    public void setLatitude(float latitude){
        this.latitude = latitude;
    }
    
    /**
     * Returns the longitude
     * @return longitude as float
     */
    public float getLongitude(){
        return this.longitude;
    }
    
    /**
     * Returns the latitude
     * @return latitude as float 
     */
    public float getLatitude(){
        return this.latitude;
    }
    
    /**
     * Sets the timestamp
     * @param timestamp as timestamp
     */
    public void setTimestamp(Timestamp timestamp){
        this.timestamp = timestamp;
    }
    
    /**
     * Returns the latitude
     * @return latitude as float 
     */
    public Timestamp getTimestamp(){
        return this.timestamp;
    }
    
    @Override
    public String toString(){
        return this.timestamp + " " + this.latitude + " " + this.longitude;
        
    }
}
