/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.calculators;

import guts.entities.Axis;
import guts.entities.Location;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import osmViewer.data.Tower;

/**
 *
 * @author Fethiye Güney
 */

public class AntennaCorrectionCalculatorTest {
    
     AntennaCorrectionCalculator acc;
    
     @Before
     public void Before() {
         acc = new AntennaCorrectionCalculator();
     }
    
     @Rule
     public ExpectedException exception = ExpectedException.none();

      // testet, ob currentAngle innerhalb 0 und 359 liegt 
     // da die Werte, die außerhalb liegen, ungültig sind. In diesem Fall wird 
     //ein gültiger Wert als Parameter übergeben. -->25, liegt im grünen bereich
     @Test
     public void testCurrentAngleInBounds() 
     {
        acc.calculateCorrection(new Location(0.0,0.0),new Axis(20.0,25,20.0), new Tower(0.0,0.0, "Test"));
     }
     
     // testet, ob currentAngle innerhalb 0 und 359 liegt 
     // da die Werte, die außerhalb liegen, ungültig sind. In diesem Fall werden 
     //2 ungültige Werte als Parameter übergeben. 
     @Test
     public void testCurrentAngleOutOfBounds() 
     {
        exception.expect(IllegalArgumentException.class);
        
        acc.calculateCorrection(new Location(0.0,0.0),new Axis(20.0,-1,20.0), new Tower(0.0,0.0, "Test"));
        acc.calculateCorrection(new Location(0.0,0.0),new Axis(20.0,360,20.0), new Tower(0.0,0.0, "Test"));
     }
 
     //testet ob der erste und dritte Parameter currentAxis.getPitch() 
     //und currentAxis.getRoll() invertiert wurden, da dies für die Ausrichtung in die richtige Richtung 
     //erforderlich ist
     
     @Test
     public void testInvertAngleAndZeroDelta()
     {
        Axis actAxis = acc.calculateCorrection(new Location(0.0,0.0), new Axis(20.0,0.0,20.0), new Tower(0.0, 0.0, "Test"));
        Axis reqAxis = new Axis(-20.0,0.0,-20.0);
        
       // hier erfolgt der Vergleich
        assertEquals(reqAxis.toString(), actAxis.toString());
     }
}