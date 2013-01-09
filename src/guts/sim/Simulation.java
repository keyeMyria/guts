
package guts.sim;

import guts.Config;
import guts.GUTSEntry;

/**
 *
 * @author Cedric Ohle
 */
public class Simulation implements Runnable{
    
    /* todo:
     * Gebraucht werden: Compass, GPS, Gyro
     * Werden in dieser Reihenfolge berechnet
     * 
     * Vorbereitung
     * 1. Objekte erzeugen
     * 2. Observerbekanntmachung
     * 
     * Initiale Berechnung
     * 1.       Compass Richtung festlegen und speichern
     * 1.1          Compass benachrichtigt GPS
     * 2.       GPS holt Daten und ermittelt neue Position ausgehend vom Startwert
     * 2.1          Kommt es zum überlauf-> Rückmeldung an Compass
     * 2.2          Compass speichert neue zukünftige Richtung
     * 3.       Gyro ermittelt Startlage
     * 
     * weiterführende berechnung
     * 1.       Compass prüft ob neue Richtung gesetzt
     * 1.1          Compass ermittelt Drehung
     * 1.2          Compass benachrichtigt GPS
     * 2.       GPS holt die Daten und ermittelt neue Position ausgehend von
     *          letzter Position
     * 2.1          Überlauf prüfen -> Rückmeldung an Compass
     * 2.2          Compass speicher neue ausrichtung
     * 3.       Gyro ermittelt neue Lage ausgehend von letzter Lage
     * 
     * 
     * 
     */
    
    SimMagneticFieldSensor simCompass;
    SimGPS simGPS;
    SimGyroscope simGyro;
    
    public Simulation(){
        this.simCompass=SimMagneticFieldSensor.getInstance();
        this.simGPS=SimGPS.getInstance();
        this.simGyro=SimGyroscope.getInstance();
    }

    @Override
    public void run() {
        while(true){
            //GUTSEntry.simlock.lock();
            simCompass.calculateAngelToMagneticNorth();
            simGPS.fetchNewLocation();
            simGyro.calculatePosition();
            try {
                    Thread.sleep(Config.REFRESHRATE);
                } catch (InterruptedException ex) {}
            //GUTSEntry.guilock.unlock();
        }
    }
    
}
