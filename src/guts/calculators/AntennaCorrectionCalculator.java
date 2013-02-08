package guts.calculators;

import guts.entities.Axis;
import guts.entities.Location;
import osmViewer.data.Tower;

/**
 * This class fetches the values of the current position 
 * of the Antenna (Pitch,Yaw,Roll), 
 * and returns the new calculated values​​, to correct the alignment of it.
 * @author Fethiye Güney
 * @author Patrick Selge
 * @author Cedric Ohle
 */
public class AntennaCorrectionCalculator {
    
    private Location currentLocation;
    private Tower activeTower;
    
    /**
     * 
     * @param currentLocation
     * @param currentAxis
     * @param activeTower
     * @return new Axis Object
     */
    public Axis calculateCorrection(double currentAngel, Location currentLocation, Axis currentAxis, Tower activeTower){

        // Set attributes
        this.currentLocation = currentLocation;
        this.activeTower = activeTower;
        
        double angel = calculateAngel();
        
        return new Axis(-currentAxis.getPitch(), rotationToFront(angel, currentAngel), -currentAxis.getRoll());
    }

    /**
     * Calculates the new needed angel of the antenna relative to the car position.
     * @return new angle as double
     */
    private double calculateAngel(){
        
        /* brauchen den rad zur Angabe der Größe des Winkels (in länge und breite). 
         * Ein Winkel mit 1 Radiant --> im Kreis 1 Meter Radius einen Bogen der Länge 1 Meter. 
         */  
        double lon1 = Math.toRadians(currentLocation. getLongitude());
        double lon2 = Math.toRadians(activeTower.getLongitude());
        
        double lat1 = Math.toRadians(currentLocation.getLatitude());
        double lat2 = Math.toRadians(activeTower.getLatitude());
        
        

        /* Gibt einen Winkel zurück, 
         * dessen Tangens der Quotient zweier angegebener Zahlen ist.
         * math.atan2 trifft automatisch die richtige auswahl des quadranten, 
         * in dem das dreieck liegt
         * die Funktion erwartet zwei parameter. x- und y-wert des punktes(deltay,deltax)
         * wohin die antenne ausgerichtet werden soll (gibt also den richtigen drehwinkel zurück
         * wegen +  - 180 wird am ende der modulo gebildet
         */
        double newAngle = Math.atan2(Math.sin(lon2-lon1)*
                Math.cos(lat2), Math.cos(lat1)*
                Math.sin(lat2) - Math.sin(lat1)*
                Math.cos(lat2)*
                Math.cos(lon2-lon1)) % (2*Math.PI);
        
        /* der endgültige newAngle ergibt sich dann aus der Differenz aus dem gesamten Winkelbereich
         * und der Summe des momentanen Winkels der Antennenausrichtung und des eben berechneten 
         * Winkels newAngle. Damit wir korrekte Werte haben, die im gültigen Bereich (360) liegen
         * wird der modulo gebildet
         */
        return (360 + Math.toDegrees(newAngle)) % 360;
    }
    
    private double rotationToFront(double angel, double currentAngel) {
        angel = (angel - currentAngel);
        while (angel < 0) angel += 360;
        
        return angel;
    }
    
    public double rotationToNorth(double angel, double currentAngel) {
        angel = (angel + currentAngel);
        while (angel >= 360) angel -= 360;
        
        return angel;
    }
}
