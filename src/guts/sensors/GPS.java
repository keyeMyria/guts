

package guts.sensors;

import guts.Config;
import guts.entities.Location;
import guts.sim.SimGPS;
import java.util.Date;

/**
 * This class provides access to the locationdata from a GPS. It handles the
 * hardware access.
 * @author Cedric Ohle
 */
public class GPS {
    private int address;
    private SimGPS simGPS;
    
    public GPS(int address){
        this.address = address;
    }
    
    public GPS(){
        this.simGPS = new SimGPS();
    }
    
    
    /**
     * This function returns the current location as a location object.
     * @return location as a location object
     */
    public Location fetchLocation(){
        if (Config.SIMULATIONENABLED == true){
            Location l = new Location();
            l.setTimestamp(new Date(System.currentTimeMillis()));
            l.setLongitude(this.simGPS.getLongitude());
            l.setLatitude(this.simGPS.getLatitude());
            return l;
        }
        else{
            // Implement real hardware access
            return null;
        }
    }
}
