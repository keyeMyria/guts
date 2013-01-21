
package guts.actors;

/**
 * This class represents a special form of a engine with a limited movementradius.
 * @author Cedric Ohle
 */
public class ServoLatchEngine extends ServoEngine{
    private double leftMax;
    private double rightMax;

    /*
     * Hardware constructor
     * @params EngineAddress as int
     * @params leftMax as double
     * @params rightMax as double
     */
    public ServoLatchEngine(int EngineAddress,double leftMax, double rightMax) {
        super(EngineAddress);
        this.leftMax = leftMax;
        this.rightMax = rightMax;
    }

    /*
     * Constructor for simulation purposes
     * @params leftMax as double
     * @params rightMax as double
     */
    public ServoLatchEngine(double leftMax, double rightMax, double startPosition) {
        super(startPosition);
        this.leftMax = leftMax;
        this.rightMax = rightMax;
    }
    
    
    /**
     * Allows setting of a new angle to get to.
     * Calculates the needed direction and distance to drive the servoengine and
     * moves it. If the movement borders are exceeded the servo is moved to the border.
     * @params angle angle the new angle as float
     */
    @Override
    public void moveToAngle(double angle){
        double currentAngle = fetchAngle();
        double movement = 0;
        if (angle < currentAngle){
            // counterclockwise
            movement = -1 * (currentAngle - angle);
            if(Math.abs(movement) > (currentAngle - leftMax)){
                movement = -1 * (currentAngle - leftMax);
            }
        } else if(angle > currentAngle) {
            // clockwise
            movement = (angle - currentAngle);
            if(movement > (rightMax - currentAngle)){
                movement = rightMax - currentAngle;
            }
            
        }
        super.move(movement);
    }

}
