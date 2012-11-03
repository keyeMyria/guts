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
    
    private Location newLocation;
    
    public void setLocation(Location startLocation){
        this.newLocation = startLocation;
    }
    
    public void generateNewLocation(){
        //Todo: Max 180 abfangen, bei Long und lat
        
        double angel = SimMagneticFieldSensor.getCurrentAngel();
        
        double longitudedelta = (Math.random() * 300+1)/(160000000/ Config.REFRESHRATE);
        
        this.newLocation.setLongitude(this.newLocation.getLongitude() + longitudedelta);
        
        this.newLocation.setLatitude(this.newLocation.getLatitude() + Math.sin(angel));
        
    }

    public double getLongitude() {
        return this.newLocation.getLongitude();
    }

    public double getLatitude() {
        return this.newLocation.getLatitude();
    }
    
}
