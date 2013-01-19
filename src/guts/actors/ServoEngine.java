
package guts.actors;

import guts.Config;
import guts.sim.SimEngine;

/**
 * This class represents an servoengine with a sensor to get the current angle.
 * It allows communication with the hardwareengine.
 * @author Cedric Ohle
 */
class ServoEngine {
    private int address;
    private SimEngine simEngine;

    /*
     * Hardware constructor
     * @param engineaddress as int
     */
    public ServoEngine(int EngineAddress) {
        address = EngineAddress;
    }
    
    ServoEngine(){
        this.simEngine = new SimEngine();
    }
    
    /**
     * Allows setting of a new angle to get to.
     * Calculates the needed direction and distance to drive the servoengine and
     * moves it.
     * @params angle the new angle as float
     */
    public void moveToAngle(double angle){
        double currentAngle = fetchAngle();
        if (angle < currentAngle){
            // counterclockwise
            move(-1 * (currentAngle - angle));
        } else if(angle > currentAngle) {
            // clockwise
            move(angle - currentAngle);
        }
        // currentangel == angle -> nothing to do
    }
    
    /**
     * Returns the current angle of the servoengine.
     * @return the current angle as float
     */
    public double fetchAngle(){
        if (Config.SIMULATIONENABLED){
            return simEngine.fetchAngle();
        }else{
            // Implement real hardwareaccess
            return 0;
        }
        
    }
    
    /**
     * Allows moving of the servoengine.
     * @params move the direction and distance
     */
    protected void move(double move){
        simEngine.move(move);
    }
}