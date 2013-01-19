package guts.sim;

import guts.Config;

/**
 * This class implements a simulated servoengine
 * @author Cedric Ohle
 */
public class SimServoEngine {
    
    private double currentangle;

    /**
     * Constructor. Sets the initial servoengine position.
     * @param startposition as double
     */
    public SimServoEngine(double startPosition) {
        currentangle = startPosition;
    }

    /**
     * Gets the current angle of the servoengine
     * @return
     */
    public double fetchAngle() {
        return currentangle;
    }

    /**
     * Moves the servoengine by the given amount.
     * But only for the maximum turnrate based on the config
     * @param move as double
     */
    public void move(double move) {
        if(move > Config.SERVOTURNRATE){
            currentangle = currentangle + Config.SERVOTURNRATE;
        }else if(move < -Config.SERVOTURNRATE){
            currentangle = currentangle - Config.SERVOTURNRATE;
        }else{
            
        }
        
        currentangle = Math.abs(currentangle) % 359;
    }
    
}
