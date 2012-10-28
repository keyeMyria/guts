
package guts;

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
     * Sets the pitchangle
     * @param pitch as float
     */
    public void setPitch(float pitch){
        this.pitch = pitch;
    }
    
    /**
     * Sets the yawnangle
     * @param yawn as float
     */
    public void setYawn(float yawn){
        this.yawn = yawn;
    }
    
    /**
     * Sets the rollangle
     * @param roll as float
     */
    public void setRoll(float roll){
        this.roll = roll;
    }
    
    /**
     * Returns the pitchangle
     * @return the pitchangle as float
     */
    public float getPitch(){
        return this.pitch;
    }
    
    /**
     * Returns the yawnangle
     * @return the yawnangle as float
     */
    public float getYawn(){
        return this.yawn;
    }
    
    /**
     * Returns the rollangle
     * @return the rollangle as float
     */
    public float getRoll(){
        return this.roll;
    }
}
