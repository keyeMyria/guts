/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.calculators;

import guts.entities.Axis;
import guts.entities.Location;

/**
 *
 * @author Samsung
 */
public class AntennaCorrectionCalculator(Location currentLocation, 
        Axis currentAxis, double currentAngle, Location activeTowerLocation){ 
    

    
    double i, Angle; 
    double newAngle=0.0;
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
