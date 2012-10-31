
package guts.sensors;

import guts.Config;
import guts.sim.SimMagneticFieldSensor;

/**
 * This class represents an magneticfieldsensor to allow accessing and
 * interpretating the returned values of the hardwaresensor.
 * @author Cedric Ohle
 */
public class MagneticFieldSensor {
    private int address;
    private SimMagneticFieldSensor simMagneticFieldSensor;
    
    public MagneticFieldSensor(){
        this.simMagneticFieldSensor = new SimMagneticFieldSensor();
    }
    
    public MagneticFieldSensor(int address){
        this.address = address;
    }
    
    
    /**
     * The Funtion fetches the current angle to the magnetic north from the driver
     * @return Angle to magnetic north as float
     */
    public float fetchAngleToMagneticNorth(){
        if (Config.SIMULATIONENABLED == true){
            return this.simMagneticFieldSensor.getAngleToMagneticNorth();
        } else{
            // Implement real hardware access
            return 0;
        }
    }
}
