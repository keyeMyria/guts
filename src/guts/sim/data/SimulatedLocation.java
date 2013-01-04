/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.sim.data;

import guts.entities.Location;
import guts.sim.SimUtilities;

/**
 *
 * @author patrick
 */
public class SimulatedLocation {
    
    public SimulatedLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public Location to_Location() {
        return new Location(latitude, longitude);
    }
    
    public boolean checkAndCorrectOverflows() {
        return (checkAndCorrectOverflowLatitude() || 
                checkAndCorrectOverflowLongitude());
    }
    
    /**
     * Checks the latitude for any overflows and corrects them.
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
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    public double getLatitude() {
        return latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
    
    
    
    private double latitude;
    private double longitude;
}
