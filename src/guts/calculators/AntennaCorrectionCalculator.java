/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.calculators;

import guts.entities.Axis;
import guts.entities.Location;
import osmViewer.data.Tower;

/**
 * This class fetches the values of the current position 
 * of the Antenna (Pitch,Yaw,Roll), 
 * and returns the new calculated values​​, to correct the alignment of it.
 * @author Fethiye Güney
 */
public class AntennaCorrectionCalculator {
    
    private Location currentLocation;
    private Tower activeTower;
    private double currentAngle;
    
    /**
     * 
     * @param currentLocation
     * @param currentAxis
     * @param currentAngle
     * @param activeTower
     * @return new Axis Object
     */
    public Axis calculateCorrection(Location currentLocation, Axis currentAxis, 
        double currentAngle, Tower activeTower){
        // Grenzwerte festlegen
        if (currentAngle < 0 || currentAngle >= 360){
            throw new IllegalArgumentException("currentAngle must be between 0 and 359");
        }

        // Set attributes
        this.currentLocation = currentLocation;
        this.currentAngle = currentAngle;
        this.activeTower = activeTower;
        
        return new Axis(-currentAxis.getPitch(), calculateAngel(), -currentAxis.getRoll());
    }

    /**
     * 
     * @return new angle as double
     */
    private double calculateAngel(){
        double i, newAngle, deltax, deltay;

        // deltax und deltay berechnet sich aus der Differenz vom Standpunkt des 
        // gewählten Towers und vom Standpunkt der aktuellen Position des Wagens
        deltax = activeTower.getLongitude() - currentLocation.getLongitude();
        deltay = activeTower.getLatitude() - currentLocation.getLatitude();

        // Falls die Position des Towers und des Fahrzeuges die selbe ist, soll die Antenne
        // nach vorne zeigen
        if ((deltax == 0) && (deltay == 0)) {
            return 0;
        }
        
        // wenn kein deltay, dann prüfen ob deltax im pos. bereich liegt (genau auf der achse) 
        // wenn ja dann newangle 0
        // wenn nicht dann zurückgegebener Winkel dementsprechend,damit es im pos bereich liegt
        if (deltay == 0) {
            newAngle = (deltax > 0) ? 90 : 270;
        }          
        // genau wie oben, falls kein deltax, deltay überprüfen (was wieder genau auf der achse liegt, 
        // im welchem bereich das liegt 
        else if (deltax == 0) {
           newAngle = (deltay > 0) ? 0 : 180;
        }
        // wenn keines dieser fälle eintritt also nicht genau auf der achse und nicht im neg bereich
        // dann werden diese werte genommen und der arcus tangens wird berechnet
        else{
           i=deltay/deltax;
           newAngle = Math.atan(Math.toRadians(i));
        }
        
        double lon1 = Math.toRadians(currentLocation. getLongitude());
        double lon2 = Math.toRadians(activeTower.getLongitude());
        
        double lat1 = Math.toRadians(currentLocation.getLatitude());
        double lat2 = Math.toRadians(activeTower.getLatitude());
        
        System.out.println("Before: " + newAngle);
        newAngle = Math.atan2(Math.sin(lon2-lon1)*
                Math.cos(lat2), Math.cos(lat1)*
                Math.sin(lat2) - Math.sin(lat1)*
                Math.cos(lat2)*
                Math.cos(lon2-lon1)) % (2*Math.PI);
        
        
        System.out.println("After: " + Math.toDegrees(newAngle));
        // der endgültige newAngle ergibt sich dann aus der Differenz aus dem gesamten Winkelbereich
        // und der Summe des momentanen Winkels der Antennenausrichtung und des eben berechneten 
        // Winkels newAngle. Damit wir korrekte Werte haben, die im gültigen Bereich (360) liegen
        // wird der modulo gebildet
        
        return (360 + Math.toDegrees(newAngle)) % 360;
        
        // return (360 - currentAngle + newAngle) % 360;
    }
}