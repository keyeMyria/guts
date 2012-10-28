/**
 * GUTS - GPS Utilized Tracking System
 * A tracking control device that sets the antenna
 * orientation on an offroad vehicle
 * 
 * @author Patrick Selge
 * @version 0.1
 */

package guts;

import java.util.LinkedList;

/**
 *
 * @author Patrick Selge
 */
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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gui = new GUI();
        gui.main(null);
    }
    
    public void toggleAntennaCorrection(){
        
    }
    
    public void toggleTrackRecording(){
        
    }
    
    public void correctAntennaPostion(){
        
    }
    
    public void saveDataPoint(Location currentLocation){
        
    }
    
    public float calculateSpeed(){
        //todo: implementation needed
        return 0;
    }
    
    public boolean getTrackRecordingStatus(){
        return this.storeTrackEnabled;
    }
    
    public boolean getAntennaCorrectionStatus(){
        return this.antennaCorrectionEnabled;
    }
    
    private Axis calculateCorrection(Location currentLocation, Axis currentAxis, float currentAngle){
        //todo: needs implementation
        Axis newAxisAfterCorrection = new Axis();
        
        return newAxisAfterCorrection;
    }
    
    private void setGyrosope(Gyroscope gyroscope){
        this.gyroscope = gyroscope;
    }
    
    private Gyroscope getGyroscope(){
        return this.gyroscope;
    }
    
    private void setMagneticFieldSensor(MagneticFieldSensor mageneticFieldSensor){
        this.magneticFieldSensor = magneticFieldSensor;
    }
    
    private MagneticFieldSensor getMagnetivFieldSensor(){
        return this.magneticFieldSensor;
    }
    
    private void setAntenna(Antenna antenna){
        this.antenna = antenna;
    }
    
    private Antenna getAntenna(){
        return this.antenna;
    }
    
    public void setActiveTower(int towerID){
        this.activeTower = towerID;
    }
    
    public int getActiveTower(){
        return this.activeTower;
    }
    
    private void setTowers(LinkedList towers){
        this.towers = towers;
    }
    
    private LinkedList getTowers(){
        return this.towers;
    }

}
