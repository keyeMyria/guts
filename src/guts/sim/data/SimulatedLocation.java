package guts.sim.data;

import guts.entities.Location;
import guts.sim.SimUtilities;

/**
 * This class extends the standart used location to allow overflow checking and
 * setting of data which is not needed at use with real hardware.
 * @author Patrick Selge
 */
public class SimulatedLocation {
    
    /**
     * Constructor
     * @param latitude
     * @param longitude
     */
    public SimulatedLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    /**
     * converts the simlocation object as location object
     * @return a location object based on the simlocation object
     */
    public Location to_Location() {
        return new Location(latitude, longitude);
    }
    
    /**
     * Imports the data from a location object into the simlocation object
     * @param location
     */
    public void from_Location(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
    
    /**
     * Checks and corrects overflows.
     * @return true if correction was necessary
     */
    public boolean checkAndCorrectOverflows() {
        return (checkAndCorrectOverflowLatitude() || 
                checkAndCorrectOverflowLongitude());
    }
    
    /**
     * Checks the latitude for any overflows and corrects them.
     * @return true if correction was necessary
     */
    private boolean checkAndCorrectOverflowLatitude(){
        if (Math.abs(latitude) > 90) {
           int sign = SimUtilities.getSign(latitude);
           latitude = (sign * -90) +  sign * (latitude - (sign * 90));
           longitude += SimUtilities.getSign(longitude) * -180;
           
           return true;
        }
        
        return false;
    }
    
    /**
     * Checks the longitude for any overflows and corrects them.
     * @return true if correction was necessary
     */
    private boolean checkAndCorrectOverflowLongitude(){
        if (Math.abs(longitude) > 180) {
            int sign = SimUtilities.getSign(longitude);
            longitude = (sign * -180) + sign * (longitude - (sign * 180));
            
            return true;
        }
        
        return false;
    }
    
    /* Getter and Setter */
    
    /**
     * Sets the latitude
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    /**
     * Sets the longitude
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    /**
     * Returns the latitude
     * @return
     */
    public double getLatitude() {
        return latitude;
    }
    
    /**
     * Returns the longitude
     * @return
     */
    public double getLongitude() {
        return longitude;
    }
    
    
    
    private double latitude;
    private double longitude;
}
