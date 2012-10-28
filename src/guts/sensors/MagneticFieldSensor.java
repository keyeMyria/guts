
package guts.sensors;

/**
 * This class represents an magneticfieldsensor to allow accessing and
 * interpretating the returned values of the hardwaresensor.
 * @author Cedric Ohle
 */
public class MagneticFieldSensor {
    private int address;
    
    
    /**
     * The Funtion fetches the current angle to the magnetic north from the driver
     * @return Angle to magnetic nort as float
     */
    public float fetchAngleToMagneticNorth(){
        //todo: needs driver access and implementation
        return 0;
    }
}
