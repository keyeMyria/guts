package guts.sim;
 
import guts.Config;
import guts.GUTSEntry;
 
/**
 *
 * @author Cedric Ohle
 */
public class Simulation implements Runnable{
    
    SimMagneticFieldSensor simCompass;
    SimGPS simGPS;
    SimGyroscope simGyro;
    
    public Simulation(){
        this.simCompass=SimMagneticFieldSensor.getInstance();
        this.simGPS=SimGPS.getInstance();
        this.simGyro=SimGyroscope.getInstance();
        this.simGPS.addObserver(simCompass);
    }
 
    @Override
    public void run() {
        while(true){
            if(Config.SIMULATIONENABLED){
                GUTSEntry.sem.release();
            }
            simCompass.calculateAngelToMagneticNorth();
            simGPS.fetchNewLocation();
            simGyro.calculatePosition();
            try {
                    Thread.sleep(Config.REFRESHRATE);
                } catch (InterruptedException ex) {}
        }
    }
    
}