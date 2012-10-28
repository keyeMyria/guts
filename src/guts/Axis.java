/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts;

/**
 *
 * @author Cedric Ohle
 */
public class Axis {
    private float pitch;
    private float yawn;
    private float roll;
    
    public void setPitch(float pitch){
        this.pitch = pitch;
    }
    
    public void setYawn(float yawn){
        this.yawn = yawn;
    }
    
    public void setRoll(float roll){
        this.roll = roll;
    }
    
    public float getPitch(){
        return this.pitch;
    }
    
    public float getYawn(){
        return this.yawn;
    }
    
    public float getRoll(){
        return this.roll;
    }
}
