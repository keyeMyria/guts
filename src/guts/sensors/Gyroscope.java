

package guts.sensors;

import guts.Config;
import guts.entities.Axis;
import guts.sim.SimGyroscope;

/**
 * This class represents the gyroscope. It allows access to the position of the
 * object and manages hardwareaccess.
 * @author Cedric Ohle
 */
public class Gyroscope extends java.util.Observable {
    private int address;
    private SimGyroscope simGyroscope;
    private Axis axis;
    
    /**
     * Hardware constructor
     * @param address
     */
    public Gyroscope(int address){
        this.address = address;
    }
    
    /**
     * Constructor for simulation purposes
     */
    public Gyroscope(){
        this.simGyroscope = SimGyroscope.getInstance();
    }
    
    /**
     * This function returns the current position as a axis object.
     * @return currentAxis as axis object
     */
    public Axis fetchPosition(){
        if (Config.SIMULATIONENABLED == true){
            this.axis = simGyroscope.getPosition();
            setChanged();
            notifyObservers(axis);
            
            return axis;
        }else{
            // Implement real hardware access
            return this.axis;
        }
        
    }
}
