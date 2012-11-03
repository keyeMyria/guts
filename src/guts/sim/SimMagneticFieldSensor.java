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

    }

    public double getAngelToMagneticNorth() {
        if(simLength <= 0) {
            simLength = (int)(Math.random() * (1600 / Config.REFRESHRATE)) + 1;
            direction = getNextDirection();  
        }        

        double delta = getDeltaAngel();
        
        
        if(direction != 0) {
            angel = addRad(angel, direction * delta);
        }
        
        if(Config.DEBUG >= Config.LOG_ALL) {
            System.out.println("Magnetic Field Sensor: retries:" + simLength + 
                    " | direction:" + direction + " | orientation:" + angel + 
                    " | delta:"+delta);
        }
        simLength--;
        
        return angel;
    }
    
    private double addRad(double current, double addition) {
        double value = current + addition;

        if (value >= 2 * Math.PI) {
            value -= 2 * Math.PI;
        }
        return value;
    }
    
    private int getNextDirection() {
        int multi = (((int)Math.ceil(Math.random() * 3)));
        
        if (multi==3) { return 1; }
        if (multi==2) { return -1; }
        return 0;
    }
    
    private double getDeltaAngel() {
        return ((int)(Math.random() * 300 + 1))/(16000.0 / Config.REFRESHRATE);
    }

    
    private double angel = 0;
    private int simLength = 0;
    private int direction = 0;
    
}
