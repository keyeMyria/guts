
package guts.sensors;

import guts.Config;
import guts.sim.SimMagneticFieldSensor;

/**
 * This class represents an magneticfieldsensor to allow accessing and
 * interpretating the returned values of the hardwaresensor.
 * @author Cedric Ohle
 */
public class MagneticFieldSensor extends java.util.Observable {
    private int address;
    private SimMagneticFieldSensor simMagneticFieldSensor;
    
    /**
    * Hardware constructor
    * @param address
    */
    public MagneticFieldSensor(int address){
        this.address = address;
    }
    
    /**
     * Constructor for simulation purposes
     */
    public MagneticFieldSensor(){
        this.simMagneticFieldSensor = SimMagneticFieldSensor.getInstance();
    }
    

    
    
    /**
     * The Funtion fetches the current angle to the magnetic north from the driver
     * @return Angle to magnetic north as float
     */
    public double fetchAngelToMagneticNorth(){
        if (Config.SIMULATIONENABLED) {
            double angel = this.simMagneticFieldSensor.getCurrentAngel();
            setChanged();
            notifyObservers(angel);
            
            return angel;
        } else{
            // Implement real hardware access
            return 0;
        }
    }
}
