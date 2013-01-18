
package guts.actors;

import guts.sim.SimEngine;

/**
 * This class represents an servoengine with a sensor to get the current angle.
 * It allows communication with the hardwareengine.
 * @author Cedric Ohle
 */
class ServoEngine {
    private int address;
    private SimEngine simEngine;

    ServoEngine(int yawEngineAddress) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        //todo: needs implementation
    }
    
    /**
     * Returns the current angle of the servoengine.
     * @return the current angle as float
     */
    public double fetchAngle(){
        //todo: needs driver access and implementation
        return 180;
    }
    
    /**
     * Allows moving of the servoengine.
     * @params move the direction and distance
     */
    private void move(double move){
        //todo: needs driver access and implementation
    }
}
