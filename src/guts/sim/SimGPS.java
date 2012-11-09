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
        if(Config.DEBUG >= Config.LOG_ALL) {
            System.out.println("Winkel im GPS: " + angel);
        }
        angel = 90;
        double speed;
        if(((angel > 0) && (angel < 90)) || ((angel > 180) && (angel < 270))){
            speed = Math.abs((angel%90)/45);
        } else{
            speed = Math.abs(((angel%90)-90)/45);
        }
        
        
        double longitudedelta = ((Math.random() * 300+1)/(DIVIDER/ Config.REFRESHRATE))*speed;
        
        if(angel == 0) {
                //TODO x=x y=y+dy
                newLongitude = this.location.getLongitude();
                newLatitude = this.location.getLatitude() + (Math.random() * 300+1)/(DIVIDER/ Config.REFRESHRATE);
        } else if(angel == 90) {
                //TODO x=dx+x y=y
                newLongitude = this.location.getLongitude() + (Math.random() * 300+1)/(DIVIDER/ Config.REFRESHRATE);
                newLatitude = this.location.getLatitude() ;
        } else if(angel == 180) {
                //TODO x=x y=y-dy
                newLongitude = this.location.getLongitude();
                newLatitude = this.location.getLatitude() - (Math.random() * 300+1)/(DIVIDER/ Config.REFRESHRATE);
                
        } else if(angel == 270) {
                //TODO x=x-dx y=y
                newLongitude = this.location.getLongitude() - (Math.random() * 300+1)/(DIVIDER/ Config.REFRESHRATE);
                newLatitude = this.location.getLatitude();
                
        } else if(angel > 0 && angel < 90 ){
            newLongitude = this.location.getLongitude() + longitudedelta;
            newLatitude = this.location.getLatitude() + (Math.tan(Math.toRadians(90-angel))*longitudedelta);
        } else if(angel > 90 && angel < 180){
            newLongitude = this.location.getLongitude() + longitudedelta;
            newLatitude = this.location.getLatitude() - (Math.tan(Math.toRadians(angel%90))*longitudedelta);
        } else if(angel > 180 && angel < 270){
            newLongitude = this.location.getLongitude() - longitudedelta;
            newLatitude = this.location.getLatitude() - (Math.tan(Math.toRadians(270-angel))*longitudedelta);
            // /(DIVIDER/(Config.REFRESHRATE*1000000))
        } else {
            newLongitude = this.location.getLongitude() - longitudedelta;
            newLatitude = this.location.getLatitude() + (Math.tan(Math.toRadians(angel%90))*longitudedelta);
        }

        this.location = new Location(newLatitude, newLongitude);
        return this.location;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    private static final int DIVIDER = 1600000000;
    
}
