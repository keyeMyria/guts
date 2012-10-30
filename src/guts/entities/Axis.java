
package guts.entities;

/**
 * This class represents an axis object to describe the postion of a object in
 * the three dimensional space.
 * @author Cedric Ohle
 */
public class Axis {
    private float pitch;
    private float yawn;
    private float roll;
    
    /**
     * Sets the pitch angle
     * @param pitch as float
     */
    public void setPitch(float pitch){
        this.pitch = pitch;
    }
    
    /**
     * Sets the yawn angle
     * @param yawn as float
     */
    public void setYawn(float yawn){
        this.yawn = yawn;
    }
    
    /**
     * Sets the roll angle
     * @param roll as float
     */
    public void setRoll(float roll){
        this.roll = roll;
    }
    
    /**
     * Returns the pitch angle
     * @return the pitch angle as float
     */
    public float getPitch(){
        return this.pitch;
    }
    
    /**
     * Returns the yawn angle
     * @return the yawn angle as float
     */
    public float getYawn(){
        return this.yawn;
    }
    
    /**
     * Returns the roll angle
     * @return the roll angle as float
     */
    public float getRoll(){
        return this.roll;
    }
}
