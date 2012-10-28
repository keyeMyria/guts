/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts;

/**
 *
 * @author Cedric Ohle
 */
public class Antenna {
    private Engine yawEngine;
    private LatchEngine pitchEngine;
    private LatchEngine rollEngine;
    
    private void setYawEngine(Engine yawEngine){
        this.yawEngine = yawEngine;
    }
    
    private void setPitchEngine(LatchEngine pitchEngine){
        this.pitchEngine = pitchEngine;
    }
    
    private void setRollEngine(LatchEngine rollEngine){
        this.rollEngine = rollEngine;
    }
    
    private Engine getYawEngine(){
        return this.yawEngine;
    }
    
    private LatchEngine getPitchEngine(){
        return this.pitchEngine;
    }
    
    private LatchEngine getRollEngine(){
        return this.rollEngine;
    }
}
