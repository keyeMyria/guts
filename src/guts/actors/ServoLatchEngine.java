
package guts.actors;

/**
 * This class represents a special form of a engine with a limited movementradius.
 * @author Cedric Ohle
 */
public class ServoLatchEngine extends ServoEngine{
    private double leftMax;
    private double rightMax;

    ServoLatchEngine(int EngineAddress) {
        //TODO: add limits
        super(EngineAddress);
    }

    ServoLatchEngine(double leftMax, double rightMax) {
        super();
        this.leftMax= leftMax;
        this.rightMax = rightMax;
    }
    
    /**
     * Sets the left movement maximum
     * @param leftMax as float
     */
    public void setLeftMax(double leftMax){
        this.leftMax = leftMax;
    }
    
    /**
     * Sets the right movement maximum
     * @param rightMax as float
     */
    public void setRightMax(double rightMax){
        this.rightMax = rightMax;
    }
    
    /**
     * Gets the left movement maximum
     * @returns leftMax as float
     */
    public double getLeftMax(){
        return this.leftMax;
    }
    
    /**
     * Gets the right movement maximum
     * @returns rightMax as float
     */
    public double getRightMax(){
        return this.rightMax;
    }
    
    /**
     * Allows setting of a new angle to get to.
     * Calculates the needed direction and distance to drive the servoengine and
     * moves it. If the movement borders are exceeded the servo is moved to the border.
     * @params angle the new angle as float
     */
    @Override
    public void moveToAngle(double angle){
        //todo: needs implementation
    }
}
