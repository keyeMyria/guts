/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.sim;

import guts.*;

/**
 *
 * @author Cedric Ohle
 * @author Patrick Selge
 */
public class SimMagneticFieldSensor {
    
    public SimMagneticFieldSensor() {
        this.utils = new SimUtilities();
    }

    public double getAngelToMagneticNorth() {
        if(simLength <= 0) {
            simLength = (int)(utils.getRandomBetween(100.0,5000.0,100.0) / Config.REFRESHRATE);
            direction = getNextDirection();  
        }        
     
        if(direction != 0) {
            angel += (direction * getDeltaAngel());
            angel = (angel < 0) ? (angel + 360) : (angel % 360); 
        }
              
        simLength--;
        
        return angel;
    }
    
    protected int getNextDirection() {
        int multi = (int) utils.getRandomBetween(1.0, 3.0, 1.0);
        
        if (multi==3) { return 1; }
        if (multi==2) { return -1; }
        return 0;
    }
    
    private double getDeltaAngel() {
        return utils.getRandomBetween(0.0001 , 0.02 , 0.00001) * Config.REFRESHRATE;
    }

    
    protected static double getCurrentAngel(){
        return angel;
    }
    
    @Override
    public String toString() {
        return ("Magnetic Field Sensor: retries:" + simLength + 
            " | direction:" + direction + " | orientation:" + angel);
    }
    
    private static double angel = 0;
    private int simLength = 0;
    private int direction = 0;
    
    private SimUtilities utils;
    
}
