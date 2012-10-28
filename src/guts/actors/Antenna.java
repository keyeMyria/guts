
package guts.actors;

import guts.entities.Axis;

/**
 * This class represents the antenna. It has three engines to allow to be
 * positionend.
 * @author Cedric Ohle
 */
public class Antenna {
    private Engine yawEngine;
    private LatchEngine pitchEngine;
    private LatchEngine rollEngine;
    
    
    /**
     * Sets the yawengine
     * @param yawEngine as engine object
     */
    private void setYawEngine(Engine yawEngine){
        this.yawEngine = yawEngine;
    }
    
    /**
     * Sets the pitchEngine
     * @param pitchEngine as latchengine object
     */
    private void setPitchEngine(LatchEngine pitchEngine){
        this.pitchEngine = pitchEngine;
    }
    
    /**
     * Sets the rollEngine
     * @param rollEngine as latchengine object
     */
    private void setRollEngine(LatchEngine rollEngine){
        this.rollEngine = rollEngine;
    }
    
    /**
     * Returns the yawEngine
     * @returns yawEngine as latchengine object
     */
    private Engine getYawEngine(){
        return this.yawEngine;
    }
    
    /**
     * Returns the pitchEngine
     * @returns pitchEngine as latchengine object
     */
    private LatchEngine getPitchEngine(){
        return this.pitchEngine;
    }
    
    /**
     * Returns the rollEngine
     * @returns rollEngine as latchengine object
     */
    private LatchEngine getRollEngine(){
        return this.rollEngine;
    }
    
    /**
     * This function allows to set a new position.
     * @param newAxis as Axis object
     */
    public void applyNewAxis(Axis newAxis){
        this.getRollEngine().moveToAngle(newAxis.getRoll());
        this.getPitchEngine().moveToAngle(newAxis.getPitch());
        this.getYawEngine().moveToAngle(newAxis.getYawn());
    }
}
