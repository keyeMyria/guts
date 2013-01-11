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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GUTScontrol implements Runnable {
    
    // Sensors
    private Gyroscope gyroscope;
    private MagneticFieldSensor magneticFieldSensor;
    private GPS gps;
    
    private Antenna antenna;
    private int activeTower;
    
    private boolean storeTrackEnabled;
    private boolean antennaCorrectionEnabled;
    
    private TrackLog trackLog;
    private TowerCollection towers;
    
    private static double angel;
    private static Location locat;
    private static Axis axis;
    
    private SpeedCalculator speedCalculator;
    private AntennaCorrectionCalculator antennaCorrectionCalculator;
    
    private static GUI gui;
    private static Log logger;
      
    
    @Override 
    public void run() {
            while(true) {
                if(Config.SIMULATIONENABLED){
                    //GUTSEntry.guilock.lock();
                }
                angel = this.magneticFieldSensor.fetchAngelToMagneticNorth();
                locat = this.gps.fetchLocation();
                axis = this.gyroscope.fetchPosition();
                
                if(this.antennaCorrectionEnabled){
                    correctAntennaPostion();
                }
                
                System.out.println("Antenna correction active");
                Log.writeToLog(Log.ok_level, "Antenna position corrected");
                
                gui.moveToWaypoint(GUTScontrol.locat);
                gui.repaint();
                
                try {
                    Thread.sleep(Config.REFRESHRATE);
                } catch (InterruptedException ex) {}
                
                if(Config.SIMULATIONENABLED){
                    //GUTSEntry.simlock.unlock();
                }
        }
    }

    /*
     * Override default constructor for default values.
     */
    public GUTScontrol() throws InterruptedException {        
        this.gui = new GUI();        
        gui.drawInterface();
        
        this.antennaCorrectionEnabled = false;
        this.storeTrackEnabled = false;
        
        // Create Hardware
        this.gps = new GPS();
        this.gps.setStartPoint(52.483791, 13.226141);
        
        this.gyroscope = new Gyroscope();
        this.magneticFieldSensor = new MagneticFieldSensor();
        this.antenna = new Antenna();
        
        // Create Stores
        this.towers = new TowerCollection();
        this.trackLog = new TrackLog();  
        
        // Create Guts Calculators
        this.speedCalculator = new SpeedCalculator();
        this.antennaCorrectionCalculator = new AntennaCorrectionCalculator();
        
        
        
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
                    this.locat,
                    this.axis,
                    this.angel,
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
