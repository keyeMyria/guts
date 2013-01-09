package guts.calculators;

import guts.entities.Location;
import java.util.Date;
import java.util.Observable;

/**
 * Does all the speed calculation between to Location objects.
 * It therefor keeps an history of the last known poisition and
 * uses the new pased object to do it's math. It then saves the 
 * new object as the last known position, and so on...
 * 
 * @author Patrick Selge
 * @version 1.0
 */
public class SpeedCalculator extends java.util.Observable 
implements java.util.Observer {
    
    /**
     * Returns the current speed in km/h
     * based on the last two locations and their
     * related timestamps
     * 
     * @param current   The current location, will be used as
     *                  last location the next time the method is called
     * @return The current speed in km/h
     */
    public double calculateSpeed(Location current){
        // needs a last know location to work.
        if(lastKnownLocation == null) {
            return 0.0;
        }
        
        double time = DatesToHours(lastKnownLocation.getTimestamp(), 
                current.getTimestamp());
        double distance = current.distanceTo(lastKnownLocation);
        
        return distance/time;
    }
    
    /**
     * Transforms the time between to dates and returns 
     * them as a double in hourse
     * 
     * @param t1 A timestamp or date object
     * @param t2 A timestamp or date object
     * @return The difference between t1 and t2 in hours
     */
    private double DatesToHours(Date t1, Date t2) {
        double timeDiff = t1.getTime() - t2.getTime();
        return Math.abs(timeDiff * DIV_HOURS);
    }
    
    /**
     * Resets the Speed calculator and takes the start param as it's
     * first lastKnownLocation
     * 
     * @param start Will be set as the lastKnownPosisition
     */
    public void reset(Location start) {
        this.lastKnownLocation = start; 
    }
    
    /**
     * Reset the Speed calculator, so that calculateSpeed will
     * return 0.0 the next time it's called, because lastKnownLocation 
     * is reset to null
     * 
     * @see #calculateSpeed(guts.entities.Location) 
     */
    public void reset() {
        this.lastKnownLocation = null;
    }  
    
    /**
     * Calculates the speed and sets the location to the
     * lastKnownLocation attribute. It then notifies it's
     * observers that speed has changed.
     * 
     * @param t The observable that caused the update
     * @param o The variable the observable passed to the obeserver
     */
    @Override
    public void update(Observable t, Object o) {
        if(c++ % REFRESH_RATE == 0) {
            this.speed = calculateSpeed((Location) o);
            this.lastKnownLocation = ((Location) o);

            setChanged();
            notifyObservers(this.speed);
        }
    }
    
    /**
     * Returns the last calculated speed
     * 
     * @return The current speed
     */
    public double getSpeed() {
        return this.speed;
    }
    
    private double speed = 0.0;
    private Location lastKnownLocation = null;
    private static final double DIV_HOURS = 1/(1000.0*60.0*60.0);
    
    // A temporary counter variable. May change in next version. Do
    // not rely on.
    private int c = 0;
    private static final int REFRESH_RATE = 15;

}
