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