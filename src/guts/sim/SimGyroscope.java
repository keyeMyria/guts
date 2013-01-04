/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.sim;

import guts.Config;
import guts.entities.Axis;

/**
 *
 * @author Cedric Ohle
 */
public class SimGyroscope {
    
    private Axis axis;
    private int rollSimLength = 0;
    private int rollDirection = 0;
    private int pitchSimLength = 0;
    private int pitchDirection = 0;
    
    private static SimGyroscope instance = new SimGyroscope();
    
    private SimGyroscope(){
        this.axis = new Axis(0,0,0);
    }
    
    public static SimGyroscope getInstance() {
        return instance;
    }
    
    private int getNextDirection() {
        int multi = (((int)Math.ceil(Math.random() * 3)));
        
        if (multi==3) { return 1; }
        if (multi==2) { return -1; }
        return 0;
    }
    
    private double getDeltaAngel() {
        return ((int)(Math.random() * 3000 + 1))/(1600000.0 / Config.REFRESHRATE);
    }
    
    
    public Axis getPosition(){
        // Momentan berechnen wir yawn nicht, da wir den Wert aus dem Compass erhalten
        // und nie interpretieren
        if(rollSimLength <= 0) {
            rollSimLength = (int)(Math.random() * (1600 / Config.REFRESHRATE)) + 1;
            rollDirection = getNextDirection();  
        }
        if(pitchSimLength <= 0) {
            pitchSimLength = (int)(Math.random() * (1600 / Config.REFRESHRATE)) + 1;
            pitchDirection = getNextDirection();  
        }
        
        double rolldelta = getDeltaAngel();
        double pitchdelta = getDeltaAngel();
        
     
        if(rollDirection != 0) {
            if(this.axis.getRoll() + (rollDirection * rolldelta) > 30 ){
                this.axis.setRoll(this.axis.getRoll() + (-1 * rollDirection * rolldelta));
            } else if(this.axis.getRoll() + (rollDirection * rolldelta) < -30 ){
                this.axis.setRoll(this.axis.getRoll() + (-1 * rollDirection * rolldelta));
            } else{
                this.axis.setRoll(this.axis.getRoll() + (rollDirection * rolldelta));
            }
            
        }
        if(pitchDirection != 0) {
            if(this.axis.getPitch() + (pitchDirection * pitchdelta) > 30 ){
                this.axis.setPitch(this.axis.getPitch() + (-1 * pitchDirection * pitchdelta));
            } else if(this.axis.getPitch() + (pitchDirection * pitchdelta) < -30 ){
                this.axis.setPitch(this.axis.getPitch() + (-1 * pitchDirection * pitchdelta));
            } else{
                this.axis.setPitch(this.axis.getPitch() + (pitchDirection * pitchdelta));
            }
            
        }
        
        rollSimLength--;
        pitchSimLength--;
        return this.axis;
    }

    
}
