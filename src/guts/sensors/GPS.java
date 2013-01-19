package guts.sensors;

import guts.Config;
import guts.entities.Location;
import guts.sim.SimGPS;

/**
 * This class provides access to the locationdata from a GPS. It handles the
 * hardware access or if enabled access to the simulated GPS.
 * @author Cedric Ohle
 * @author Patrick Selge
 */
public class GPS extends java.util.Observable {
    private int address;
    private SimGPS simGPS;
    
    /**
     * Hardware constructor
     * @param address
     */
    public GPS(int address){
        this.address = address;
    }
    
    /**
     * Constructor for simulation purposes
     */
    public GPS(){
        this.simGPS = SimGPS.getInstance();
    }
    
    /**
     * Allows to set a GPS startingpoint
     * @param latitude
     * @param longitude
     */
    public void setStartPoint(double latitude, double longitude) {
        simGPS.setLocation(new Location(latitude, longitude));
    }
    
    
    /**
     * This function returns the current location as a location object.
     * @return location as a location object
     */
    public Location fetchLocation(){
        if (Config.SIMULATIONENABLED){
            
            Location location = simGPS.getLocation();
            setChanged();
            notifyObservers(location);
            
            return location;
        }
        else{
            // Implement real hardware access
            return null;
        }
    }
    
}
