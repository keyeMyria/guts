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

     @Test
     public void testCurrentAngleInBounds() 
     {
        acc.calculateCorrection(new Location(0.0,0.0),new Axis(20.0,0.0,20.0),25, new Tower(0.0,0.0, "Test"));
     }
     
     // testet, ob currentAngle innerhalb 0 und 359 liegt 
     // da die Werte, die außerhalb liegen, ungültig sind
     @Test
     public void testCurrentAngleOutOfBounds() 
     {
        exception.expect(IllegalArgumentException.class);
        
        acc.calculateCorrection(new Location(0.0,0.0),new Axis(20.0,0.0,20.0),-1, new Tower(0.0,0.0, "Test"));
        acc.calculateCorrection(new Location(0.0,0.0),new Axis(20.0,0.0,20.0),360, new Tower(0.0,0.0, "Test"));
     }
     
     @Test
     public void testInvertAngleAndZeroDelta()
     {
        Axis actAxis = acc.calculateCorrection(new Location(0.0,0.0), new Axis(20.0,0.0,20.0), 0.0, new Tower(0.0, 0.0, "Test"));
        Axis reqAxis = new Axis(-20.0,0.0,-20.0);
        
        // Gramm fragen, warum assertEquals nur mit der toString() Methode funktioniert
        assertEquals(reqAxis.toString(), actAxis.toString());
     }
}