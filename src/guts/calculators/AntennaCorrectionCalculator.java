/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.calculators;

import guts.entities.Axis;
import guts.entities.Location;


/**
     * Calculates the needed positioncorrections based on the given
     * current location, position and direction. New position values are
     * returned as axis object.
     * 
     * @param currentLocation as location object
     * @param currentAxis as axis object
     * @param currentAngle as float
     * @param activeTowerLocation as Location object
     * @return newAxis as axis object
     */

/**
 *
 * @author Fethiye Güney
 */
public class AntennaCorrectionCalculator {
    
    public Axis calculateCorrection(Location currentLocation, 
        Axis currentAxis, double currentAngle, Location activeTowerLocation){ 
    

    
    double i, Angle; 
    double newAngle;
    double deltax, deltay;
        
  deltax = activeTowerLocation.getLongitude() - currentLocation.getLongitude();
  deltay = activeTowerLocation.getLatitude() - currentLocation.getLatitude();
        
       if (deltay == 0) {
           if(deltax > 0){
               newAngle=90;
           }
           else {
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
          Angle = Math.atan(Math.toRadians(i));
          newAngle=Angle;
        }
        
       return new Axis(0,newAngle,0);
    }
}
