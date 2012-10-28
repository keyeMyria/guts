
package guts;

/**
 * This class represents an servoengine with a sensor to get the current angle.
 * It allows communication with the hardwareengine.
 * @author Cedric Ohle
 */
class Engine {
    private int address;
    
    /**
     * Allows setting of a new angle to get to.
     * Calculates the needed direction and distance to drive the servoengine and
     * moves it.
     * @params angle the new angle as float
     */
    public void moveToAngle(float angle){
        //todo: needs implementation
    }
    
    /**
     * Returns the current angle of the servoengine.
     * @return the current angle as float
     */
    public float fetchAngle(){
        //todo: needs driver access and implementation
        return 0;
    }
    
    /**
     * Allows moving of the servoengine.
     * @params move the direction and distance
     */
    private void move(float move){
        //todo: needs implementation
    }
}
