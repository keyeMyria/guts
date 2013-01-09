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
    
    public GPS(int address){
        this.address = address;
    }
    
    public GPS(){
        this.simGPS = SimGPS.getInstance();
    }
    
    public void setStartPoint(double latitude, double longitude) {
        simGPS.setLocation(new Location(latitude, longitude));
    }
    
    
    /**
     * This function returns the current location as a location object.
     * @return location as a location object
     */
    public Location fetchLocation(){
        if (Config.SIMULATIONENABLED){
            
            Location location = simGPS.fetchNewLocation();
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
