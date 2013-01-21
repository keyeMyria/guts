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
     * Calculates the new needed angel of the antenna relative to the car position.
     * @return new angle as double
     */
    private double calculateAngel(){
        
        double lon1 = Math.toRadians(currentLocation. getLongitude());
        double lon2 = Math.toRadians(activeTower.getLongitude());
        
        double lat1 = Math.toRadians(currentLocation.getLatitude());
        double lat2 = Math.toRadians(activeTower.getLatitude());
        
        double newAngle = Math.atan2(Math.sin(lon2-lon1)*
                Math.cos(lat2), Math.cos(lat1)*
                Math.sin(lat2) - Math.sin(lat1)*
                Math.cos(lat2)*
                Math.cos(lon2-lon1)) % (2*Math.PI);
        
        // der endgültige newAngle ergibt sich dann aus der Differenz aus dem gesamten Winkelbereich
        // und der Summe des momentanen Winkels der Antennenausrichtung und des eben berechneten 
        // Winkels newAngle. Damit wir korrekte Werte haben, die im gültigen Bereich (360) liegen
        // wird der modulo gebildet
        
        return (360 + Math.toDegrees(newAngle)) % 360;
    }
}