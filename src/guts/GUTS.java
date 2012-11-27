/**
 * GUTS - GPS Utilized Tracking System
 * A tracking control device that sets the antennajunit
 * orientation on an offroad vehicle
 * 
 * @author Patrick Selge
 * @author Cedric Ohle
 * @version 0.1
 */

package guts;

import guts.calculators.*;
import guts.gui.GUI;
import guts.actors.Antenna;

import guts.entities.Axis;
import guts.entities.Location;
import osmViewer.data.TowerCollection;
import guts.entities.TrackLog;

import guts.sensors.GPS;
import guts.sensors.Gyroscope;
import guts.sensors.MagneticFieldSensor;


public class GUTS implements Runnable {

    public static GUI gui;
    public static GUTS guts;
    
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
    
    /**
     * The main function
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        gui = new GUI();
        
        Thread guiThread = new Thread( gui );
        guiThread.start();
        guiThread.join();  
        
        guts = new GUTS();
        
        Thread gutsThread = new Thread( guts );
        
        gutsThread.start();
        

        
        while(true) {
            //gui.rotateJeep(GUTS.angel);
            //gui.rotateAntenna(GUTS.angelAntenna);
            
            
            gui.repaint();
            
            try {
                Thread.sleep(Config.REFRESHRATE);   
            } catch (InterruptedException ex) {}  
        }
    }
    
    
    @Override 
    public void run() {
            while(true) {
                angel = this.magneticFieldSensor.fetchAngelToMagneticNorth();
                locat = this.gps.fetchLocation();
                axis = this.gyroscope.fetchPosition();
                //angelAntenna = this.antennaMockObject.fetchAngelToMagneticNorth();
                
                gui.moveToWaypoint(GUTS.locat);
                
                try {
                    Thread.sleep(Config.REFRESHRATE);
                } catch (InterruptedException ex) {}
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
                    this.gps.fetchLocation(),
                    this.gyroscope.fetchPosition(),
                    this.magneticFieldSensor.fetchAngelToMagneticNorth(),
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
     * Returns the gyroscope.
     * @return A gyroscope object
     */
    private Gyroscope getGyroscope(){
        return this.gyroscope;
    }
    
    /**
     * Returns the gps.
     * @return A gps object
     */
    private GPS getGPS(){
        return this.gps;
    }
    
    /**
     * Returns the mageneticfieldsensor.
     * @return A magneticFieldSensor object
     */
    private MagneticFieldSensor getMagneticFieldSensor(){
        return this.magneticFieldSensor;
    }
    
    /**
     * Returns the antenna.
     * @return An antenna object
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
    
    private SpeedCalculator speedCalculator;
    private AntennaCorrectionCalculator antennaCorrectionCalculator;


}
