/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.sim;

import guts.Config;

/**
 *
 * @author Cedric Ohle
 */
public class SimEngine {
    
    private double currentangle;

    public SimEngine() {
        currentangle = 0;
    }

    public double fetchAngle() {
        return currentangle;
    }

    public void move(double move) {
        if(move > Config.SERVOTURNRATE){
            currentangle = currentangle + Config.SERVOTURNRATE;
        }else if(move < -Config.SERVOTURNRATE){
            currentangle = currentangle - Config.SERVOTURNRATE;
        }else{
            currentangle = currentangle + move;
        }
        currentangle = currentangle % 359;
    }
    
}
