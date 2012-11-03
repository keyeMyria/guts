/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.sim;

import guts.entities.Axis;

/**
 *
 * @author Cedric Ohle
 */
public class SimGyroscope {
    
    private Axis axis;
    


    public double getPitch() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getRoll() {
        // -45 bis +45 Grad
        double roll = Math.random();
        
        
        return roll;
    }

    public double getYawn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
