
package guts.actors;

import guts.Config;
import guts.sim.SimServoEngine;

/**
 * This class represents an servoengine with a sensor to get the current angle.
 * It allows communication with the hardwareengine.
 * @author Cedric Ohle
 */
class ServoEngine {
    private int address;
    private SimServoEngine simEngine;
    private double angel;

    /*
     * Hardware constructor
     * @param engineaddress as int
     */
    public ServoEngine(int EngineAddress) {
        address = EngineAddress;
    }
    
    /*
     * Constructor for simulation purposes
     */
    public ServoEngine(double startPosition){
        this.simEngine = new SimServoEngine(startPosition);
    }
    
    /**
     * Allows setting of a new angle to get to.
     * Calculates the needed direction and distance to drive the servoengine and
     * moves it.
     * @params angle the new angle as float
     */
    public void moveToAngle(double angle){
        double currentAngle = fetchAngle();
        double diffAngle = Math.abs(currentAngle - angle);
        if (diffAngle >= 180 ){
            // counterclockwise
            move(-1 * (currentAngle - angle));
        } else if(diffAngle < 180 && diffAngle > 0) {
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
            this.angel = simEngine.fetchAngle();
            return angel;
        }else{
            // Implement real hardwareaccess
            return this.angel;
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