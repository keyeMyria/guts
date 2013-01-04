package guts.sim;

import guts.Config;
import guts.entities.Location;

/**
 * This class represents the simulated GPS.
 * @author Cedric Ohle
 * @author Patrick Selge
 */
public class SimGPS extends java.util.Observable {
    
    private Location location;
    private double newLongitude;
    private double newLatitude;
    private double longitudedelta;
    private double speed;
    private static final int DIVIDER = 1600000000;
    private static final int FACTOR = 150;
    private static final int proportionFactor = DIVIDER/ Config.REFRESHRATE;
    
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

        double angel = SimMagneticFieldSensor.getCurrentAngel();
        
        calculateSpeed(angel);
        
        calculateNewLocation(angel);
        
        checkAndCorrectOverflowLatitude();
        checkAndCorrectOverflowLongitude();

        this.location = new Location(newLatitude, newLongitude);
        return this.location;
    }
    /**
     * Calculates the speedfactor and sets a new longitudedelta for further
     * calculations.
     */
    private void calculateSpeed(double angel){
        // TODO: Rework this to be more like a actual gaspedal
        if(((angel > 0) && (angel < 90)) || ((angel > 180) && (angel < 270))){
            speed = Math.abs((angel%90)/45);
        } else{
            speed = Math.abs(((angel%90)-90)/45);
        }
        
        // Neues delta für Longitude
        longitudedelta = ((Math.random() * FACTOR+1)/proportionFactor)*speed;
    }
    
    /**
     * Differentiates between axis and quadrant locations
     * @param angel The angel on the coordinate system 
     */
    private void calculateNewLocation(double angel){
        // Neue Position errechnen
        // Sonderfälle der Achsen
        if(angel % 90 == 0) {
            calculateAxisLocations(angel);
        } else {
            calculateQuadrantLocations(angel);
        }
    }
    
    private void calculateAxisLocations(double angel) {
        switch((int) angel){ 
            case 0:
                newLongitude = this.location.getLongitude();
                newLatitude = this.location.getLatitude() + (Math.random() * FACTOR+1)/proportionFactor;
                break;
            case 90:
                newLongitude = this.location.getLongitude() + (Math.random() * FACTOR+1)/proportionFactor;
                newLatitude = this.location.getLatitude();
                break;
            case 180:
                newLongitude = this.location.getLongitude();
                newLatitude = this.location.getLatitude() - (Math.random() * FACTOR+1)/proportionFactor; 
                break;
            default:
                newLongitude = this.location.getLongitude() - (Math.random() * FACTOR+1)/proportionFactor;
                newLatitude = this.location.getLatitude();  
        }
    }
    
    private void calculateQuadrantLocations(double angel) {
        if(angel > 0 && angel < 90 ){
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
           notifyObservers(location);
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
           notifyObservers(location);
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
