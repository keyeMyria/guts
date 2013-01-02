/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.sim;

import guts.Config;
import guts.entities.Location;

/**
 *
 * @author Cedric Ohle
 * @author Patrick Selge
 */
public class SimGPS extends java.util.Observable {
    
    private Location location;
    private double newLongitude;
    private double newLatitude;
    private double angel;
    private double longitudedelta;
    private double speed;
    private static final int DIVIDER = 1600000000;
    private static final int FACTOR = 150;
    
    public void setLocation(Location startLocation){
        this.location = startLocation;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public Location fetchNewLocation(){

        angel = SimMagneticFieldSensor.getCurrentAngel();
        
        calculateSpeed();
        
        calculateNewLocation();
        
        checkAndCorrectOverflowLatitude();
        checkAndCorrectOverflowLongitude();

        this.location = new Location(newLatitude, newLongitude);
        return this.location;
    }
    
    private void calculateSpeed(){
        // TODO: Rework this to be more like a actual gaspedal
        if(((angel > 0) && (angel < 90)) || ((angel > 180) && (angel < 270))){
            speed = Math.abs((angel%90)/45);
        } else{
            speed = Math.abs(((angel%90)-90)/45);
        }
        
        // Neues delta für Longitude
        longitudedelta = ((Math.random() * FACTOR+1)/(DIVIDER/ Config.REFRESHRATE))*speed;
    }

    private void calculateNewLocation(){
        // Neue Position errechnen
        // Sonderfälle der Achsen
        if(angel == 0) {
                newLongitude = this.location.getLongitude();
                newLatitude = this.location.getLatitude() + (Math.random() * FACTOR+1)/(DIVIDER/ Config.REFRESHRATE);
        } else if(angel == 90) {
                newLongitude = this.location.getLongitude() + (Math.random() * FACTOR+1)/(DIVIDER/ Config.REFRESHRATE);
                newLatitude = this.location.getLatitude() ;
        } else if(angel == 180) {
                newLongitude = this.location.getLongitude();
                newLatitude = this.location.getLatitude() - (Math.random() * FACTOR+1)/(DIVIDER/ Config.REFRESHRATE);           
        } else if(angel == 270) {
                newLongitude = this.location.getLongitude() - (Math.random() * FACTOR+1)/(DIVIDER/ Config.REFRESHRATE);
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
