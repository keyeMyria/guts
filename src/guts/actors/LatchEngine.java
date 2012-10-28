
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
    
    /**
     * Allows setting of a new angle to get to.
     * Calculates the needed direction and distance to drive the servoengine and
     * moves it if the movement borders are exceeded the servo is moved to the border.
     * @params angle the new angle as float
     */
    public void moveToAngle(float angle){
        //todo: needs implementation
    }
}
