/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.sim;

import guts.Config;
import guts.entities.Location;
import java.util.Date;

/**
 *
 * @author Cedric Ohle
 */
public class SimGPS {
    
    private Location newLocation;
    
    public void setLocation(Location startLocation){
        this.newLocation = startLocation;
    }
    
    public void generateNewLocation(){
        //Todo: Max 180 abfangen, bei Long und lat
        
        double angel = SimMagneticFieldSensor.getCurrentAngel();
        
        double longitudedelta = (Math.random() * 300+1)/(160000000/ Config.REFRESHRATE);
        
        if(angel > 0 && angel <= 90 ){
            this.newLocation.setLongitude(this.newLocation.getLongitude() + longitudedelta);
            this.newLocation.setLatitude(this.newLocation.getLatitude() + Math.sin(angel));
        } else if(angel > 90 && angel <= 180){
            this.newLocation.setLongitude(this.newLocation.getLongitude() + longitudedelta);
            this.newLocation.setLatitude(this.newLocation.getLatitude() - Math.sin(angel));
        } else if(angel > 180 && angel <= 270){
            this.newLocation.setLongitude(this.newLocation.getLongitude() - longitudedelta);
            this.newLocation.setLatitude(this.newLocation.getLatitude() - Math.sin(angel));
        } else {
            this.newLocation.setLongitude(this.newLocation.getLongitude() - longitudedelta);
            this.newLocation.setLatitude(this.newLocation.getLatitude() + Math.sin(angel));
        }
        this.newLocation.setTimestamp(new Date(System.currentTimeMillis()));
    }

    public double getLongitude() {
        return this.newLocation.getLongitude();
    }

    public double getLatitude() {
        return this.newLocation.getLatitude();
    }
    
}
