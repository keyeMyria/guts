package guts.sim;

import guts.Config;
import guts.entities.Location;

/**
 * This class represents the simulated GPS.
 * @author Cedric Ohle
 * @author Patrick Selge
 */
public class SimGPS extends java.util.Observable {
    
    private SimUtilities utils;
    private Location location;
    private double newLongitude;
    private double newLatitude;
    private double angel;
    private double locationCompensationFactor;
    private double speed = 0;
    private static final int DIVIDER = 1600000000;
    private static final int FACTOR = 150;
    private static final int proportionFactor = DIVIDER/ Config.REFRESHRATE;
    
    public SimGPS(){
        this.utils = new SimUtilities();
    }
    
    /**
     * Sets the startlocation for the simulated GPS
     * @param startLocation
     */
    public void setLocation(Location startLocation){
        this.location = startLocation;
    }
    
    /**
     * Returns the current location in the simulated GPS
     * @return current location
     */
    public Location getLocation() {
        return this.location;
    }
    
    /**
     * Calculates a new location based on the compass data.
     * @return the new location
     */
    public Location fetchNewLocation(){

        angel = SimMagneticFieldSensor.getCurrentAngel();
        
        calculateSpeed(angel);
        
        calculateNewLocation();
        
        checkAndCorrectOverflowLatitude();
        checkAndCorrectOverflowLongitude();

        this.location = new Location(newLatitude, newLongitude);
        return this.location;
    }
    
    /**
     * Calculates the speedfactor and sets a new longitudedelta for further
     * calculations.
     * @param angel
     * @return longitudedelta
     */
    private double calculateSpeed(double angel){
        double speeddifference;
        
        if(((angel > 0) && (angel < 90)) || ((angel > 180) && (angel < 270))){
            locationCompensationFactor = Math.abs((angel%90)/45);
        } else{
            locationCompensationFactor = Math.abs(((angel%90)-90)/45);
        }
        
        if(utils.getRandomBetween(1,2,1) == 1){
            speeddifference = -(Math.random());
        }else{
            speeddifference = Math.random();
        }
        
        speed = (speeddifference + speed);
        
        if (speed < 0){
            speed = 0;
        }else{
            speed = speed % 2;
        }
        
        // Neues delta für Longitude
        return ((speed * FACTOR+1)/proportionFactor)*locationCompensationFactor;
    }
    
    /**
     * Calculates the new locations based on the longitudedelta.
     */
    private void calculateNewLocation(){
        // Neue Position errechnen
        // Sonderfälle der Achsen
        if(angel == 0) {
                newLongitude = this.location.getLongitude();
                newLatitude = this.location.getLatitude() + (Math.random() * FACTOR+1)/proportionFactor;
        } else if(angel == 90) {
                newLongitude = this.location.getLongitude() + (Math.random() * FACTOR+1)/proportionFactor;
                newLatitude = this.location.getLatitude() ;
        } else if(angel == 180) {
                newLongitude = this.location.getLongitude();
                newLatitude = this.location.getLatitude() - (Math.random() * FACTOR+1)/proportionFactor;           
        } else if(angel == 270) {
                newLongitude = this.location.getLongitude() - (Math.random() * FACTOR+1)/proportionFactor;
                newLatitude = this.location.getLatitude();  
        // Restliche Flächen der Quadranten
        } else if(angel > 0 && angel < 90 ){
            newLongitude = this.location.getLongitude() + longitudedelta;
            newLatitude = this.location.getLatitude() + (Math.tan(Math.toRadians(90-angel))*longitudedelta);
        } else if(angel > 90 && angel < 180){
            newLongitude = this.location.getLongitude() + longitudedelta;
            newLatitude = this.location.getLatitude() - (Math.tan(Math.toRadians(angel%90))*longitudedelta);
        } else if(angel > 180 && angel < 270){
            newLongitude = this.location.getLongitude() - longitudedelta;
            newLatitude = this.location.getLatitude() - (Math.tan(Math.toRadians(270-angel))*longitudedelta);
        } else {
            newLongitude = this.location.getLongitude() - longitudedelta;
            newLatitude = this.location.getLatitude() + (Math.tan(Math.toRadians(angel%90))*longitudedelta);
        }
    }
    
    /**
     * Checks the latitude for any overflows and corrects them.
     */
    private void checkAndCorrectOverflowLatitude(){
        if(location.getLatitude() > 90){
           newLatitude = -90 + (location.getLatitude() - 90);
           if(location.getLongitude() >= 0){
               newLongitude = location.getLongitude() - 180;
           }
           else{
               newLongitude = location.getLongitude() + 180;
           }
           // Compass needs to invert axis
           setChanged();
           notifyObservers();
        }
        if(location.getLatitude() < -90){
           newLatitude = 90 - (location.getLatitude() + 90);
           if(location.getLongitude() >= 0){
               newLongitude = location.getLongitude() - 180;
           }
           else{
               newLongitude = location.getLongitude() + 180;
           }
           // Compass needs to invert axis
           setChanged();
           notifyObservers();
        }
        
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
}
