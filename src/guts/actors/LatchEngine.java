
package guts.actors;

/**
 * This class represents a special form of a engine with a limited movementradius.
 * @author Cedric Ohle
 */
class LatchEngine extends Engine{
    private float leftMax;
    private float rightMax;
    
    /**
     * Sets the left movement maximum
     * @param leftMax as float
     */
    public void setLeftMax(float leftMax){
        this.leftMax = leftMax;
    }
    
    /**
     * Sets the right movement maximum
     * @param rightMax as float
     */
    public void setRightMax(float rightMax){
        this.rightMax = rightMax;
    }
    
    /**
     * Gets the left movement maximum
     * @returns leftMax as float
     */
    public float getLeftMax(){
        return this.leftMax;
    }
    
    /**
     * Gets the right movement maximum
     * @returns rightMax as float
     */
    public float getRightMax(){
        return this.rightMax;
    }
}
