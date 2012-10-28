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
import guts.sensors.GPS;
import guts.sensors.Gyroscope;
import guts.sensors.MagneticFieldSensor;
import java.util.LinkedList;


public class GUTS {

    private static GUI gui;
    
    // Sensors
    private Gyroscope gyroscope;
    private MagneticFieldSensor magneticFieldSensor;
    private GPS gps;
    
    private Antenna antenna;
    private int activeTower;
    private LinkedList towers;
    private boolean storeTrackEnabled;
    private boolean antennaCorrectionEnabled;
    
    
    
    /**
     * The main function
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gui = new GUI();
        gui.main(null);
    }

    public GUTS() {
        this.antennaCorrectionEnabled = false;
        this.storeTrackEnabled = false;
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
                    gps.fetchLocation(),
                    gyroscope.fetchPosition(),
                    magneticFieldSensor.fetchAngleToMagneticNorth()
                );
        antenna.applyNewAxis(newAxis);
    }
    
    /**
     * Saves the given location with a timestamp for further use
     * @param currentLocation as location object
     */
    public void saveDataPoint(Location currentLocation){
        //todo: implementation needed
    }
    
    /**
     * Calculates and returns the current speed based on the last two datapoints.
     * @return currentSpeed as float
     */
    public float calculateSpeed(){
        //todo: implementation needed
        return 0;
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
     * @return newAxis as axis object
     */
    private Axis calculateCorrection(Location currentLocation, Axis currentAxis, float currentAngle){
        //todo: needs implementation
        return null;
    }
    
    /**
     * Sets the gyroscope.
     * @param gyroscope as gyroscope object
     */
    private void setGyrosope(Gyroscope gyroscope){
        this.gyroscope = gyroscope;
    }
    
    /**
     * Returns the gyroscope.
     * @return gyroscope as gyroscope object
     */
    private Gyroscope getGyroscope(){
        return this.gyroscope;
    }
    
    /**
     * Sets the gps.
     * @param gps as gps object
     */
    private void setGPS(GPS gps){
        this.gps = gps;
    }
    
    /**
     * Returns the gps.
     * @return gps as gps object
     */
    private GPS getGPS(){
        return this.gps;
    }
    
    /**
     * Sets the mageneticfieldsensor.
     * @param mageneticFieldSensor as MagneticFieldSensor object
     */
    private void setMagneticFieldSensor(MagneticFieldSensor mageneticFieldSensor){
        this.magneticFieldSensor = mageneticFieldSensor;
    }
    
    /**
     * Returns the mageneticfieldsensor.
     * @return magneticFieldSensor as magneticFieldSensor object
     */
    private MagneticFieldSensor getMagnetivFieldSensor(){
        return this.magneticFieldSensor;
    }
    
    /**
     * Sets the antenna.
     * @param antenna as antenna object
     */
    private void setAntenna(Antenna antenna){
        this.antenna = antenna;
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
     * Sets the list of towers.
     * @param towers as linked list object
     */
    private void setTowers(LinkedList towers){
        this.towers = towers;
    }
    
    /**
     * Returns the list of towers.
     * @return towers as linked list object
     */
    private LinkedList getTowers(){
        return this.towers;
    }

}
