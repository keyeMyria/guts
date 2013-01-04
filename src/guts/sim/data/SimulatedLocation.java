/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.sim.data;

import guts.entities.Location;

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
    
    /**
     * Checks the latitude for any overflows and corrects them.
     */
    public boolean checkAndCorrectOverflowLatitude(){
        if(latitude > 90){
           latitude = -90 + (latitude - 90);
           longitude += (longitude >= 0) ? -180 : 180;
           
           return true;
        }
        
        if(latitude < -90){
           latitude = 90 - (latitude + 90);
           longitude += (longitude >= 0) ? -180 : 180;

           return true;
        }
        
        return false;
    }
    
    /**
     * Checks the longitude for any overflows and corrects them.
     */
    private void checkAndCorrectOverflowLongitude(){
        // Überlauf auf den Breitengraden
        if(location.getLongitude() > 180){
           newLongitude = -180 + (location.getLongitude() - 180); 
        }
        if(location.getLongitude() < -180){
           newLongitude = 180 - (location.getLongitude() + 180); 
        }
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
