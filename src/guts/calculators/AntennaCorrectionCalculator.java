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
    calculateAxis(currentLocation, currentAxis, currentAngle);

    return new Axis();

    }

public double calculateAngel(Location currentLocation, Axis currentAxis, 
            double currentAngle, Location activeTowerLocation){
  
    if (currentAngle < 0 || currentAngle >= 360){
        throw new IllegalArgumentException("currentAngle must be between 0 and 359");
    }
    
  double i, newAngle, deltax, deltay;
  
  deltax = activeTowerLocation.getLongitude() - currentLocation.getLongitude();
  deltay = activeTowerLocation.getLatitude() - currentLocation.getLatitude();
        
       if (deltay == 0) {
           if(deltax > 0){
               newAngle=90;
           }
           else{
               newAngle=270;
           }
       }          
       else if (deltax == 0) {
          if(deltay > 0){
              newAngle=0;
          }
          else {
              newAngle=180;
          }
       }
      
       else{
          i=deltay/deltax;
          newAngle = Math.atan(Math.toRadians(i));
        }
        
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
