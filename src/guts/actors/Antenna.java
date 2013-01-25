
package guts.actors;

import guts.Config;
import guts.entities.Axis;

/**
 * This class represents the antenna.
 * It has three engines to allow to be positionend.
 * @author Cedric Ohle
 */
public class Antenna {
    private ServoEngine yawEngine;
    private ServoLatchEngine pitchEngine;
    private ServoLatchEngine rollEngine;
    
    /*
     * Hardware construtor.
     * @param yawEngineAddress
     * @param pitchEngineAddress
     * @param rollEngineAddress
     * @param pitchEngineLeftMax
     * @param pitchEngineRightMax
     * @param rollEngineLeftMax
     * @param rollEngineRightMax
     */
    public Antenna(int yawEngineAddress, int pitchEngineAddress , int rollEngineAddress,double pitchEngineLeftMax, double pitchEngineRightMax, double rollEngineLeftMax, double rollEngineRightMax){
        this.pitchEngine = new ServoLatchEngine(pitchEngineAddress,pitchEngineLeftMax, pitchEngineRightMax);
        this.rollEngine = new ServoLatchEngine(rollEngineAddress,rollEngineLeftMax, rollEngineRightMax);
        this.yawEngine = new ServoEngine(yawEngineAddress);
        
    }
    
    /*
     * Constructor for simulation purposes.
     * @param pitchEngineLeftMax
     * @param pitchEngineRightMax
     * @param rollEngineLeftMax
     * @param rollEngineRightMax
     */
    public Antenna(double pitchEngineLeftMax, double pitchEngineRightMax, double rollEngineLeftMax, double rollEngineRightMax){
        this.pitchEngine = new ServoLatchEngine((double) pitchEngineLeftMax, pitchEngineRightMax, Config.PITCHENGINESTART);
        this.rollEngine = new ServoLatchEngine((double) rollEngineLeftMax, rollEngineRightMax,Config.ROLLENGINESTART);
        this.yawEngine = new ServoEngine((double) Config.YAWENGINESTART);
    }
    
    /**
     * Returns the current yaw angle of the antenna for use in gui
     * @return yaw angle of the antenna as double
     */
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
    