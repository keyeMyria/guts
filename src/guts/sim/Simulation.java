package guts.sim;
 
import guts.Config;
import guts.GUTSEntry;
 
/**
 * This class is the entry point for the simulation.
 * @author Cedric Ohle
 */
public class Simulation implements Runnable{
    
    SimMagneticFieldSensor simCompass;
    SimGPS simGPS;
    SimGyroscope simGyro;
    
    /**
     * Constructor. If needed creates the simulated sensors
     */
    public Simulation(){
        this.simCompass=SimMagneticFieldSensor.getInstance();
        this.simGPS=SimGPS.getInstance();
        this.simGyro=SimGyroscope.getInstance();
        this.simGPS.addObserver(simCompass);
    }
 
    /**
     * The simulation thread creates new datapoints for use in the control
     */
    @Override
    public void run() {
        while(true){
            GUTSEntry.sem.release();
            simCompass.calculateAngelToMagneticNorth();
            simGPS.fetchNewLocation();
            simGyro.calculatePosition();
            try {
                    Thread.sleep(Config.REFRESHRATE);
                } catch (InterruptedException ex) {}
        }
    }
    
}