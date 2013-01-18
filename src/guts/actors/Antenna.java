
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
    
    public Antenna(int yawEngineAddress, int pitchEngineAddress , int rollEngineAddress,double pitchEngineLeftMax, double pitchEngineRightMax, double rollEngineLeftMax, double rollEngineRightMax){
        this.pitchEngine = new ServoLatchEngine(pitchEngineAddress,pitchEngineLeftMax, pitchEngineRightMax);
        this.rollEngine = new ServoLatchEngine(rollEngineAddress,rollEngineLeftMax, rollEngineRightMax);
        this.yawEngine = new ServoEngine(yawEngineAddress);
        
    }
    
    public Antenna(double pitchEngineLeftMax, double pitchEngineRightMax, double rollEngineLeftMax, double rollEngineRightMax){
        this.pitchEngine = new ServoLatchEngine(pitchEngineLeftMax, pitchEngineRightMax);
        this.rollEngine = new ServoLatchEngine(rollEngineLeftMax, rollEngineRightMax);
        this.yawEngine = new ServoEngine();
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
    