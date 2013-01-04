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
    private double speed;
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
        this.speed = 0;
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
        
        
        calculateNewLocation(angel);
        
        checkAndCorrectOverflowLatitude();
        checkAndCorrectOverflowLongitude();

        this.location = new Location(newLatitude, newLongitude);
        return this.location;
    }
    
    /**
     * Calculates the new longitudedelta for further calculations.
     * @param angel
     * @return longitudedelta
     */
    private double calculateLatitudeDelta(double angel){
        return ((calculateSpeed() * FACTOR+1)/proportionFactor)*calculateLocationCompensationFactor(angel);
    }
    
    /**
     * Calculates the new Speed.
     * @return speedfactor
     */
    private double calculateSpeed(){
        speed = (utils.getRandomBetween(-1,1,0.01) + speed);
        if (speed < 0){
            // We can't drive backwards
            return 0;
        }else{
            // Create upper speedlimit
            return speed % 2;
        }
    }
    
    /**
     * Calculates the compensationfactor for the locationdelta
     * based on the current angel of the car
     * @param angel
     * @return compensationfactor
     */
    private double calculateLocationCompensationFactor(double angel){
        if(((angel > 0) && (angel < 90)) || ((angel > 180) && (angel < 270))){
            return Math.abs((angel%90)/45);
        } else{
            return Math.abs(((angel%90)-90)/45);
        }
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
            calculateQuadrantLocations(angel, calculateLatitudeDelta(angel));
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
    
    private void calculateQuadrantLocations(double angel, double longitudedelta) {
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
