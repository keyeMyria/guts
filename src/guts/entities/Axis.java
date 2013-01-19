
package guts.entities;

/**
 * This class represents an axis object to describe the postion of a object in
 * the three dimensional space.
 * @author Cedric Ohle
 */
public class Axis {
    private double pitch;
    private double yawn;
    private double roll;
    
    public Axis(double pitch, double yawn, double roll){
        this.pitch = pitch;
        this.yawn = yawn;
        this.roll = roll;
    }
    
    /**
     * Sets the pitch angle
     * @param pitch as float
     */
    public void setPitch(double pitch){
        this.pitch = pitch;
    }
    
    /**
     * Sets the yawn angle
     * @param yawn as float
     */
    public void setYawn(double yawn){
        this.yawn = yawn;
    }
    
    /**
     * Sets the roll angle
     * @param roll as float
     */
    public void setRoll(double roll){
        this.roll = roll;
    }
    
    /**
     * Returns the pitch angle
     * @return the pitch angle as float
     */
    public double getPitch(){
        return this.pitch;
    }
    
    /**
     * Returns the yawn angle
     * @return the yawn angle as float
     */
    public double getYawn(){
        return this.yawn;
    }
    
    /**
     * Returns the roll angle
     * @return the roll angle as float
     */
    public double getRoll(){
        return this.roll;
    }
    
    @Override
    public String toString(){
        return ("Roll: " + this.roll + " Pitch: " + this.pitch + " Yawn: " + this.yawn);
    }
}
