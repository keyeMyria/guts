package guts.entities;

import java.util.Date;
import org.jdesktop.swingx.mapviewer.GeoPosition;

/**
 * Extends the GeoPosition by a timestamp and some comparision
 * methods.
 * 
 * @author Cedric Ohle
 * @author Patrick Selge
 */
public class Location extends GeoPosition {
     
    /**
     * Constructor - set's the timestamp to the moment it's instanciated.
     * Calls Location(double, double, java.util.Date) internally
     * 
     * @param latitude Longitude
     * @param longitude Latitude
     * @see #Location(double, double, java.util.Date) 
     */
    public Location(double latitude, double longitude) {
        this(latitude, longitude, new Date(System.currentTimeMillis()));
    }
    
    /**
     * Constructor - Works like Location(double, double, java.util.Date)
     * but awaits a timestamp also.
     * 
     * @param latitude Longitude
     * @param longitude Latitude
     * @param date Timestamp
     * @see #Location(double, double)
     */
    public Location(double latitude, double longitude, Date date) {
        super(latitude, longitude);
        this.timestamp = date;
    }

    /**
     * Sets the timestamp
     * 
     * @param timestamp as Date
     */
    public void setTimestamp(Date timestamp){
        this.timestamp = timestamp;
    }
    
    /**
     * Returns the Timestamp
     * 
     * @return Timestamp as a Date object
     */
    public Date getTimestamp(){
        return this.timestamp;
    }
    
    /**
     * Returns the most important attributes as a string for easy debugging
     * 
     * @return The most important data as a String
     */
    @Override
    public String toString(){
        return this.timestamp + ": [" + this.getLatitude() + "] " +
                "[" + this.getLongitude() + "]";
    }
    
    /**
     * Returns the distance between the object the method is called by and the
     * location thats passed in
     * 
     * @param location Location to be meassured against
     * @return Distance in km
     * @see #distanceTo(guts.entities.Location, int)
     */
    public double distanceTo(Location location) {
        return distanceTo(location, MODE_PYTHAGORAS);
    }
    
    /**
     * Returns the distance between the object the method is called by and the 
     * location thats passed in. It can use pythagoras (flat surface)
     * or a mode that includes the earth radius into it's calculation (sphere).
     * 
     * @param location Location to be meassured against
     * @param mode The selected mode
     * @return Distance in km
     * @see #distanceTo(guts.entities.Location) 
     * @throws IllegalArgumentsException
     */
    public double distanceTo(Location location, int mode) {
        // Saves the latitudes as radians and calculates their deltas
        double latA = this.getRadianLatitude();
        double latB = location.getRadianLatitude();
        double latDelta = (latA - latB);
        
        // Saves the latitudes as radians and calculates their deltas
        double lonDelta = (this.getRadianLongitude() - 
                location.getRadianLongitude());
        
        double angel = 0.0;
        
        // Uses the selected mode or throws an exception in case of
        // an unsupported mode
        switch(mode) {
            case MODE_PYTHAGORAS:
                angel = Math.sqrt((latDelta*latDelta) + (lonDelta*lonDelta));
                break;
            case MODE_EXACT:
                angel = Math.acos(Math.sin(latA)*Math.sin(latB) +
                        Math.cos(latA)*Math.cos(latB)*Math.cos(lonDelta));
                break;
            default:
                throw new IllegalArgumentException();
        }
        
        return Math.abs(angel * EARTH_RADIUS);
    }
    
    /**
     * Returns the Latitude in Radians
     * 
     * @return The Latitude in Radians
     */
    public double getRadianLatitude() {
        return Math.toRadians(this.getLatitude());
    }
    
    /**
     * Returns the Longitude in Radians
     * 
     * @return The Longitude in Radians
     */
    public double getRadianLongitude() {
        return Math.toRadians(this.getLongitude());
    }
    
    private Date timestamp;
    
    private static final int EARTH_RADIUS = 6370;
    public static final int MODE_PYTHAGORAS = 1;
    public static final int MODE_EXACT = 2;
}

