

package guts.sensors;

import guts.Config;
import guts.entities.Axis;
import guts.sim.SimGyroscope;

/**
 * This class represents the gyroscope. It allows access to the position of the
 * object and manages hardwareaccess.
 * @author Cedric Ohle
 */
public class Gyroscope {
    private int address;
    private SimGyroscope simGyroscope;
    
    public Gyroscope(int address){
        this.address = address;
    }
    
    public Gyroscope(){
        this.simGyroscope = new SimGyroscope();
    }
    
    /**
     * This function returns the current position as a axis object.
     * @return currentAxis as axis object
     */
    public Axis fetchPosition(){
        if (Config.SIMULATIONENABLED == true){
            Axis a = new Axis();
            a.setPitch(simGyroscope.getPitch());
            a.setRoll(simGyroscope.getRoll());
            a.setYawn(simGyroscope.getYawn());
            return a;
        }else{
            // Implement real hardware access
            return null;
        }
        
    }
}
