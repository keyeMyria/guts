
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
    
    public Antenna(int yawEngineAddress, int pitchEngineAddress , int rollEngineAddress){
        this.pitchEngine = new LatchEngine(pitchEngineAddress);
        this.rollEngine = new LatchEngine(rollEngineAddress);
        this.yawEngine = new Engine(yawEngineAddress);
        
    }
    
    public Antenna(){
        this.pitchEngine = new LatchEngine();
        this.rollEngine = new LatchEngine();
        this.yawEngine = new Engine();
    }
    
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
     * This function allows to set a new position.
     * @param newAxis as Axis object
     */
    public void applyNewAxis(Axis newAxis){
        this.rollEngine.moveToAngle(newAxis.getRoll());
        this.pitchEngine.moveToAngle(newAxis.getPitch());
        this.yawEngine.moveToAngle(newAxis.getYawn());
    }
}
    