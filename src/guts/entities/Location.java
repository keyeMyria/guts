

package guts.entities;

/**
 * This class represents the location of a object in a two-dimensonal plane.
 * @author Cedric Ohle
 */
public class Location {
    private float longitude;
    private float latitude;
    
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
}
