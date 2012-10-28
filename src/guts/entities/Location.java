/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts;

/**
 *
 * @author Cedric Ohle
 */
class Location {
    private float longitude;
    private float latitude;
    
    public void setLongitude(float longitude){
        this.longitude = longitude;
    }
    
    public void setLatitude(float latitude){
        this.latitude = latitude;
    }
    
    public float getLongitude(){
        return this.longitude;
    }
    
    public float getLatitude(){
        return this.latitude;
    }
}
