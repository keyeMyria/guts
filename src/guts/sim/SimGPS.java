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
 */
public class SimGPS {
    
    private Location location;
    
    public void setLocation(Location startLocation){
        this.location = startLocation;
    }
    
    public Location fetchNewLocation(){
        //Todo: Max 180 abfangen, bei Long und lat
        double newLongitude;
        double newLatitude;
        double angel = SimMagneticFieldSensor.getCurrentAngel();
        
        double longitudedelta = (Math.random() * 300+1)/(DIVIDER/ Config.REFRESHRATE);
        
        if(angel > 0 && angel <= 90 ){
            newLongitude = this.location.getLongitude() + longitudedelta;
            newLatitude = this.location.getLatitude() + (Math.sin(Math.toRadians(90-angel))/(DIVIDER/(Config.REFRESHRATE*1000)));
        } else if(angel > 90 && angel <= 180){
            newLongitude = this.location.getLongitude() + longitudedelta;
            newLatitude = this.location.getLatitude() - (Math.sin(Math.toRadians(angel-90))/(DIVIDER/(Config.REFRESHRATE*1000)));
        } else if(angel > 180 && angel <= 270){
            newLongitude = this.location.getLongitude() - longitudedelta;
            newLatitude = this.location.getLatitude() - (Math.sin(Math.toRadians(270-angel))/(DIVIDER/(Config.REFRESHRATE*1000)));
        } else {
            newLongitude = this.location.getLongitude() - longitudedelta;
            newLatitude = this.location.getLatitude() + (Math.sin(Math.toRadians(angel-270))/(DIVIDER/(Config.REFRESHRATE*1000)));
        }

        this.location = new Location(newLatitude, newLongitude);
        return this.location;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    private static final int DIVIDER = 1600000000;
    
}
