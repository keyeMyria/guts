
package guts.actors;

import guts.entities.Axis;

/**
 * This class represents the antenna. It has three engines to allow to be
 * positionend.
 * @author Cedric Ohle
 */
public class Antenna {
    private ServoEngine yawEngine;
    private ServoLatchEngine pitchEngine;
    private ServoLatchEngine rollEngine;
    
    public Antenna(int yawEngineAddress, int pitchEngineAddress , int rollEngineAddress){
        this.pitchEngine = new ServoLatchEngine(pitchEngineAddress);
        this.rollEngine = new ServoLatchEngine(rollEngineAddress);
        this.yawEngine = new ServoEngine(yawEngineAddress);
        
    }
    
    public Antenna(){
        this.pitchEngine = new ServoLatchEngine();
        this.rollEngine = new ServoLatchEngine();
        this.yawEngine = new ServoEngine();
    }
    
    /**
     * Sets the yawengine
     * @param yawEngine as engine object
     */
    private void setYawEngine(ServoEngine yawEngine){
        this.yawEngine = yawEngine;
    }
    
    /**
     * Sets the pitchEngine
     * @param pitchEngine as latchengine object
     */
    private void setPitchEngine(ServoLatchEngine pitchEngine){
        this.pitchEngine = pitchEngine;
    }
    
    /**
     * Sets the rollEngine
     * @param rollEngine as latchengine object
     */
    private void setRollEngine(ServoLatchEngine rollEngine){
        this.rollEngine = rollEngine;
    }
    
    public double getYawAngle() {
        return this.yawEngine.fetchAngle();
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
    