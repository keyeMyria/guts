
package guts.sim;

/**
 *
 * @author Cedric Ohle
 */
public class simulation {
    
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
    
    public simulation(){
        
    }
    
}
