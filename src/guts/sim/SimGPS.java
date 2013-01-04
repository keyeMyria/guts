package guts.sim;

import guts.Config;
import guts.entities.Location;
import guts.sim.data.SimulatedLocation;

/**
 * This class represents the simulated GPS.
 * @author Cedric Ohle
 * @author Patrick Selge
 */
public class SimGPS extends java.util.Observable {
    
    private SimUtilities utils;
    private SimulatedLocation sLocation;
    private double speed;
    private static final int DIVIDER = 1600000000;
    private static final int FACTOR = 150;
    private static final int proportionFactor = DIVIDER/ Config.REFRESHRATE;
    private static SimGPS instance = new SimGPS();
    
    private SimGPS(){
        this.utils = new SimUtilities();
        this.speed = 0;
        
        // create new locationObject
        sLocation = new SimulatedLocation(0, 0);
    }
    
    public static SimGPS getInstance() {
        return instance;
    }
    
    /**
     * Sets the startlocation for the simulated GPS
     * @param startLocation
     */
    public void setLocation(Location startLocation){
        sLocation.from_Location(startLocation);
    }
    
    /**
     * Returns the current location in the simulated GPS
     * @return current location
     */
    public Location getLocation() {
        return sLocation.to_Location();
    }
    
    /**
     * Calculates a new location based on the compass data.
     * @return the new location
     */
    public Location fetchNewLocation(){

        double angel = SimMagneticFieldSensor.getCurrentAngel();
        
        calculateNewLocation(angel);
        
        if(sLocation.checkAndCorrectOverflows()) {
           setChanged();
           notifyObservers();
        }

        return sLocation.to_Location();
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
     * Calculates the new speed.
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
                sLocation.setLongitude(sLocation.getLongitude());
                sLocation.setLatitude(sLocation.getLatitude() + calculateAxisLocationDelta());
                break;
            case 90:
                sLocation.setLongitude(sLocation.getLongitude() + calculateAxisLocationDelta());
                sLocation.setLatitude(sLocation.getLatitude());
                break;
            case 180:
                sLocation.setLongitude(sLocation.getLongitude());
                sLocation.setLatitude(sLocation.getLatitude() - calculateAxisLocationDelta()); 
                break;
            default:
                sLocation.setLongitude(sLocation.getLongitude() - calculateAxisLocationDelta());
                sLocation.setLatitude(sLocation.getLatitude());  
        }
    }
    
    private double calculateAxisLocationDelta(){
        return (Math.random() * FACTOR+1)/proportionFactor;
    }
    
    private void calculateQuadrantLocations(double angel, double longitudedelta) {
        if(angel > 0 && angel < 90 ){
            sLocation.setLongitude(sLocation.getLongitude() + longitudedelta);
            sLocation.setLatitude(sLocation.getLatitude() + (Math.tan(Math.toRadians(90-angel))*longitudedelta));
        } else if(angel > 90 && angel < 180){
            sLocation.setLongitude(sLocation.getLongitude() + longitudedelta);
            sLocation.setLatitude(sLocation.getLatitude() - (Math.tan(Math.toRadians(angel%90))*longitudedelta));
        } else if(angel > 180 && angel < 270){
            sLocation.setLongitude(sLocation.getLongitude() - longitudedelta);
            sLocation.setLatitude(sLocation.getLatitude() - (Math.tan(Math.toRadians(270-angel))*longitudedelta));
        } else {
            sLocation.setLongitude(sLocation.getLongitude() - longitudedelta);
            sLocation.setLatitude(sLocation.getLatitude() + (Math.tan(Math.toRadians(angel%90))*longitudedelta));
        }
    }
    
    
}
