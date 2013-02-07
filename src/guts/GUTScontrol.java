/**
 * GUTScontrol - GPS Utilized Tracking System
 * A tracking control device that sets the antennaunit
 * orientation on an offroad vehicle
 * 
 * @author Patrick Selge
 * @author Cedric Ohle
 * @author Fethiye Güney
 * @version 0.1
 */

package guts;

import guts.calculators.*;
import guts.actors.Antenna;

import guts.entities.Axis;
import guts.entities.Location;
import osmViewer.data.TowerCollection;
import guts.entities.TrackLog;
import guts.gui.GUI;

import guts.sensors.GPS;
import guts.sensors.Gyroscope;
import guts.sensors.MagneticFieldSensor;
import java.util.logging.Level;
import java.util.logging.Logger;
import osmViewer.data.Tower;


public class GUTScontrol implements Runnable {
    
    // Sensors
    private Gyroscope gyroscope;
    private MagneticFieldSensor magneticFieldSensor;
    private GPS gps;
    
    private Antenna antenna;
    private int activeTower;
    
    // Settings
    private boolean storeTrackEnabled;
    private boolean antennaCorrectionEnabled;
    
    private TrackLog trackLog;
    private TowerCollection towers;
    
    // Current sensor values
    private double angel;
    private Location locat;
    private Axis axis;
    
    private SpeedCalculator speedCalculator;
    private AntennaCorrectionCalculator antennaCorrectionCalculator;
    
    private GUI gui;
    
    @Override 
    public void run() {
            while(true) {
                
                // Wait in case the simulation is not keeping up
                if(Config.SIMULATIONENABLED){
                    try {
                        GUTSEntry.sem.acquire();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GUTScontrol.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                // Get new data from sensors
                angel = this.magneticFieldSensor.fetchAngelToMagneticNorth();
                locat = this.gps.fetchLocation();
                axis = this.gyroscope.fetchPosition();
                
                //Calculate correction for antenna if enabled
                if(this.antennaCorrectionEnabled){
                    correctAntennaPostion();
                }
                
                
                double rotation = antennaCorrectionCalculator.rotationToNorth(antenna.getYawAngle(), angel);
                gui.rotateToAngle(rotation);
                
                gui.moveToWaypoint(locat);
                gui.repaint();
                
                // Just wait for a bit to let the processor cool down
                try {
                    Thread.sleep(Config.REFRESHRATE);
                } catch (InterruptedException ex) {}
        }
    }

    /*
     * GUTScontrol constructor.
     * Sets everything up and creates all need data structures.
     */
    public GUTScontrol() throws InterruptedException {        
        this.gui = new GUI();        
        gui.drawInterface();
        
        this.antennaCorrectionEnabled = true;
        this.storeTrackEnabled = false;
        
        // Create Hardware
        this.gps = new GPS();
        this.gps.setStartPoint(Config.CARSTARTLAT, Config.CARSTARTLON);
        
        this.gyroscope = new Gyroscope();
        this.magneticFieldSensor = new MagneticFieldSensor();
        this.antenna = new Antenna(
                Config.PITCHENGINELEFTMAX,
                Config.PITCHENGINERIGHTMAX,
                Config.ROLLENGINELEFTMAX,
                Config.ROLLENGINERIGHTMAX
                );
        
        // Create Stores
        this.towers = new TowerCollection();
        this.trackLog = new TrackLog();  
        
        // Create Guts Calculators
        this.speedCalculator = new SpeedCalculator();
        this.antennaCorrectionCalculator = new AntennaCorrectionCalculator();
        
        // Add the default tower
        this.towers.add(new Tower(Config.DEFAULTTOWERLAT, Config.DEFAULTTOWERLON, Config.DEFAULTTOWERNAME));
        this.activeTower = 0;
        
        
        magneticFieldSensor.addObserver(gui.getJeepTop());
        magneticFieldSensor.addObserver(gui.getOrientationStatusBox());
        
        gps.addObserver(gui.getLongitutdeStatusBox());
        gps.addObserver(gui.getLatitudeStatusBox());
        gps.addObserver(speedCalculator);
        
        speedCalculator.addObserver(gui.getSpeedStatusBox());
        
        gyroscope.addObserver(gui.getJeepFront());
        gyroscope.addObserver(gui.getJeepSide());
    }
   
    /**
     * Enable/Disable the antennacorrection mechanism. 
     */
    public void toggleAntennaCorrection(){
        this.antennaCorrectionEnabled = !(this.antennaCorrectionEnabled);
    }
    
    /**
     * Enable/Disable the trackrecording mechanism.
     */
    public void toggleTrackRecording(){
        this.storeTrackEnabled = !(this.storeTrackEnabled);
    }
    
    /**
     * Corrects the antenna position.
     */
    public void correctAntennaPostion(){
        Axis newAxis = antennaCorrectionCalculator.calculateCorrection(
                    angel,
                    this.locat,
                    this.axis,
                    this.towers.get(this.activeTower)
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
     * Allows setting of the tower to be used as target of the antenna.
     * @param towerID of the targettower as int
     */
    public void setActiveTower(int towerID){
        this.activeTower = towerID;
    }
    
    /**
     * Returns the ID of the active targettower.
     * @return An activeTower as int
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
