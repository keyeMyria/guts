/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.sim;

import guts.*;
import java.util.Observable;

/**
 *
 * @author Cedric Ohle
 * @author Patrick Selge
 */
public class SimMagneticFieldSensor implements java.util.Observer{
    
    /**
     * Constructer - also initializes the SimUtilities object and stores it as
     * an class var
     */
    private SimMagneticFieldSensor() {
        this.utils = new SimUtilities();
    }
    
    public static SimMagneticFieldSensor getInstance() {
        return instance;
    }

    /**
     * Recalculates the angel to north.
     * It changes the direction every simLength turns, where simLength is a
     * random number that depends on the refresh rate.
     * 
     * @return the new angel
     */
    public double getAngelToMagneticNorth() {
        // Every simLength turns, a new direction is generated
        if(simLength <= 0) {
            simLength = (int)(utils.getRandomBetween(100.0,5000.0,100.0) / Config.REFRESHRATE);
            direction = getNextDirection();  
        }        
     
        // Recalculates the angel if a change is to be expected (direction != 0)
        if(direction != 0) {
            angel += (direction * getDeltaAngel());
            angel = (angel < 0) ? (angel + 360) : (angel % 360); 
        }
              
        simLength--;
        
        return angel;
    }
    
    /**
     * Generates a random integer that lies between -1 and 1
     * 
     * @return -1: turn Left, 0: go straight, 1: turn right
     */
    private int getNextDirection() {
        return (int) utils.getRandomBetween(-1.0, 1.0, 1.0);
    }
    
    /**
     * Generates a delta angel for the next step in dependence 
     * of the refresh rate
     * 
     * @return The change in angel for the next rendering
     */
    private double getDeltaAngel() {
        return utils.getRandomBetween(0.0001 , 0.02 , 0.00001) * Config.REFRESHRATE;
    }

    
    /**
     * Returns the angel to the magnetic north that was calculated by
     * getAngelToMagneticNorth
     * 
     * @return the current angel
     * @see #getAngelToMagneticNorth()
     */
    protected static double getCurrentAngel(){
        return angel;
    }
    
    @Override
    public String toString() {
        return ("Magnetic Field Sensor: retries:" + simLength + 
            " | direction:" + direction + " | orientation:" + angel);
    } 

    @Override
    public void update(Observable o, Object o1) {
        invertAxis();
    }

    private void invertAxis() {
        angel = (angel+180) % 360;
    }
    
    private static double angel = 0;
    private int simLength = 0;
    private int direction = 0;
    
    private SimUtilities utils;
    
    private static SimMagneticFieldSensor instance = new SimMagneticFieldSensor();
    
}
