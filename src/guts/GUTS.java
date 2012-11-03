/**
 * GUTS - GPS Utilized Tracking System
 * A tracking control device that sets the antenna
 * orientation on an offroad vehicle
 * 
 * @author Patrick Selge, Cedric Ohle
 * @version 0.1
 */

package guts;

import guts.actors.Antenna;

import guts.entities.Axis;
import guts.entities.Location;
import guts.entities.TowerCollection;
import guts.entities.TrackLog;

import guts.sensors.GPS;
import guts.sensors.Gyroscope;
import guts.sensors.MagneticFieldSensor;
import java.util.logging.Level;
import java.util.logging.Logger;




public class GUTS implements Runnable {

    private static GUI gui;
    private static GUTS guts;
    
    // Sensors
    private Gyroscope gyroscope;
    private MagneticFieldSensor magneticFieldSensor;
    private MagneticFieldSensor antennaMockObject;
    private GPS gps;
    
    private Antenna antenna;
    private int activeTower;
    
    private boolean storeTrackEnabled;
    private boolean antennaCorrectionEnabled;
    
    private TrackLog trackLog;
    private TowerCollection towers;
    
    private static double angel;
    private static double angelAntenna;

    /**
     * The main function
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        guts = new GUTS();
        gui = new GUI();
        
        Thread t1 = new Thread( gui );
        Thread t2 = new Thread( guts );
        
        t2.start();
        
        t1.start();
        t1.join();        
        
        while(true) {
            gui.rotateJeep(GUTS.angel);
            gui.rotateAntenna(GUTS.angelAntenna);
            
            Thread.sleep(Config.REFRESHRATE);   
        }  
        
    }
    
    @Override 
    public void run() {
            while(true) {
                angel = this.magneticFieldSensor.fetchAngelToMagneticNorth();
                angelAntenna = this.antennaMockObject.fetchAngelToMagneticNorth();
                try {
                //System.out.println(angel);
                    Thread.sleep(Config.REFRESHRATE);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GUTS.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
    }

    /*
     * Override default constructor for default values.
     */
    public GUTS() {
        this.antennaCorrectionEnabled = false;
        this.storeTrackEnabled = false;
        
        // Create Hardware
        this.gps = new GPS();
        this.gyroscope = new Gyroscope();
        this.magneticFieldSensor = new MagneticFieldSensor();
        this.antennaMockObject = new MagneticFieldSensor();
        this.antenna = new Antenna();
        
        // Create Stores
        this.towers = new TowerCollection();
        this.trackLog = new TrackLog();   
        
        
    }
    
    /**
     * Enable/Disable the antennacorrection mechanism. 
     */
    public void toggleAntennaCorrection(){
        if (this.antennaCorrectionEnabled == true){
            this.antennaCorrectionEnabled = false;
        }else{
            this.antennaCorrectionEnabled = true;
        }
    }
    
    /**
     * Enable/Disable the trackrecording mechanism.
     */
    public void toggleTrackRecording(){
        if (this.storeTrackEnabled == true){
            this.storeTrackEnabled = false;
        }else{
            this.storeTrackEnabled = true;
        }
    }
    
    /**
     * Corrects the antenna position.
     */
    public void correctAntennaPostion(){
        Axis newAxis = calculateCorrection(
                    this.gps.fetchLocation(),
                    this.gyroscope.fetchPosition(),
                    this.magneticFieldSensor.fetchAngelToMagneticNorth(),
                    this.towers.findByID(this.activeTower).getLocation()
                );
        antenna.applyNewAxis(newAxis);
    }
    
    /**
     * Saves the given location with a timestamp for further use
     * @param currentLocation as location object
     */
    public void saveDataPoint(Location currentLocation){
        this.trackLog.add(currentLocation);
    }
    
    /**
     * Calculates and returns the current speed based on the last two datapoints.
     * @return currentSpeed as float
     */
    public double calculateSpeed(){
        Location currentLocation = this.gps.fetchLocation();
        Location lastLocation = this.trackLog.getLast();

        double difftime = currentLocation.getTimestamp().getTime() - 
                lastLocation.getTimestamp().getTime();
        difftime = Math.abs(difftime/1000.0/60.0/60.0);
        
        // Earthraidus
        double radius = 6.371;

        double lat1 = lastLocation.getLatitude()/1E6;
        double lat2 = currentLocation.getLatitude()/1E6;
        double lon1 = lastLocation.getLongitude()/1E6;
        double lon2 = currentLocation.getLongitude()/1E6;
        double dLat = Math.toRadians(lat2-lat1);
        double dLon = Math.toRadians(lon2-lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
            Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
            Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double distance = radius * c;
        
        return distance/difftime;
    }
    
    /**
     * Returns state of trackrecording.
     * @return storeTrackEnabled as boolean
     */
    public boolean getTrackRecordingStatus(){
        return this.storeTrackEnabled;
    }
    
    /**
     * Returns state of antennacorrection.
     * @return antennaCorrectionEnabled as boolean
     */
    public boolean getAntennaCorrectionStatus(){
        return this.antennaCorrectionEnabled;
    }
    
    /**
     * Calculates the needed positioncorrections based on the given
     * current location, position and direction. New position values are
     * returned as axis object.
     * @param currentLocation as location object
     * @param currentAxis as axis object
     * @param currentAngle as float
     * @param activeTowerLocation as Location object
     * @return newAxis as axis object
     */
    private Axis calculateCorrection(Location currentLocation, Axis currentAxis,
                double currentAngle, Location activeTowerLocation){
        //todo: needs implementation
        return null;
    }
    
    /**
     * Returns the gyroscope.
     * @return gyroscope as gyroscope object
     */
    private Gyroscope getGyroscope(){
        return this.gyroscope;
    }
    
    /**
     * Returns the gps.
     * @return gps as gps object
     */
    private GPS getGPS(){
        return this.gps;
    }
    
    /**
     * Returns the mageneticfieldsensor.
     * @return magneticFieldSensor as magneticFieldSensor object
     */
    private MagneticFieldSensor getMagneticFieldSensor(){
        return this.magneticFieldSensor;
    }
    
    /**
     * Returns the antenna.
     * @return antenna as antenna object
     */
    private Antenna getAntenna(){
        return this.antenna;
    }
    
    /**
     * Allows setting of the tower to be used as target of the antenna.
     * @param towerID of the targettower as int
     */
    public void setActiveTower(int towerID){
        this.activeTower = towerID;
    }
    
    /**
     * Returns the ID of the active targettower.
     * @return activeTower as int
     */
    public int getActiveTower(){
        return this.activeTower;
    }
    
    /**
     * Returns the list of towers.
     * @return towers as linked list object
     */
    private TowerCollection getTowers(){
        return this.towers;
    }

}
