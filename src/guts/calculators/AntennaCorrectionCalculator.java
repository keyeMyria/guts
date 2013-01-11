/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.calculators;

import guts.entities.Axis;
import guts.entities.Location;

/**
 * This class fetches the values of the current position 
 * of the Antenna (Pitch,Yaw,Roll), 
 * and returns the new calculated values​​, to correct the alignment of it.
 * @author Fethiye Güney
 */
public class AntennaCorrectionCalculator {
    
    public Axis calculateCorrection(Location currentLocation, Axis currentAxis, 
            double currentAngle, Location activeTowerLocation){ 

    
    double i, newAngle, deltax, deltay, pitch, newPitch, roll, newRoll;

    calculateAngel(currentLocation, currentAxis, currentAngle, activeTowerLocation);
    return calculateAxis(currentLocation, currentAxis, currentAngle);

    }

public double calculateAngel(Location currentLocation, Axis currentAxis, 
            double currentAngle, Location activeTowerLocation){
  
    if (currentAngle < 0 || currentAngle >= 360){
        throw new IllegalArgumentException("currentAngle must be between 0 and 359");
    }
    
  double i, newAngle, deltax, deltay;
  
  // deltax und deltay berechnet sich aus der Differenz vom Standpunkt des 
  // gewählten Towers und vom Standpunkt der aktuellen Position des Wagens
  deltax = activeTowerLocation.getLongitude() - currentLocation.getLongitude();
  deltay = activeTowerLocation.getLatitude() - currentLocation.getLatitude();
        
  //wenn kein deltay, dann prüfen ob deltax im pos. bereich liegt (genau auf der achse) 
  // wenn ja dann newangle 0
  // wenn nicht dann zurückgegebener Winkel dementsprechend,damit es im pos bereich liegt
       if (deltay == 0) {
           if(deltax > 0){
               newAngle=90;
           }
           else{
               newAngle=270;
           }
       }          
       //genau wie oben, falls kein deltax, deltay überprüfen (was wieder genau auf der achse liegt, 
       //im welchem bereich das liegt 
       else if (deltax == 0) {
          if(deltay > 0){
              newAngle=0;
          }
          else {
              newAngle=180;
          }
       }
      
       //wenn keines dieser fälle eintritt also nicht genau auf der achse und nicht im neg bereich
       // dann werden diese werte genommen und der arcus tangens wird berechnet
       else{
          i=deltay/deltax;
          newAngle = Math.atan(Math.toRadians(i));
        }
        
       //der endgültige newAngle ergibt sich dann aus der Differenz aus dem gesamten Winkelbereich
       // und der Summe des momentanen Winkels der Antennenausrichtung und des eben berechneten 
       // Winkels newAngle. Damit wir korrekte Werte haben, die im gültigen Bereich (360) liegen
       // wird der modulo gebildet
       newAngle = (360 - currentAngle + newAngle) % 360;

       return newAngle;
}

    public Axis calculateAxis(Location currentLocation, Axis currentAxis, double newAngle){
        
       double pitch, newPitch, roll, newRoll;

       
       roll=currentAxis.getRoll();
       newRoll=roll * (-1);
       pitch=currentAxis.getPitch();
       newPitch=pitch * (-1);
       
       return new Axis(newPitch,newAngle,newRoll);
    }
}
