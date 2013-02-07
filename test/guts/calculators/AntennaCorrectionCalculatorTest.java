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
        Axis actAxis = acc.calculateCorrection(0.0, new Location(0.0,0.0), new Axis(20.0,0.0,20.0), new Tower(0.0, 0.0, "Test"));
        Axis reqAxis = new Axis(-20.0,0.0,-20.0);
        
        // Aufgrund des Objekttyps, verwenden wir hier toString.
        // Alternativ können auch drei Tests gemacht werden, mit .getYawn, .getRoll, etc...
        assertEquals(reqAxis.toString(), actAxis.toString());
     }
     
     @Test
     public void testCorrectionOfNinetyDegreeAngelToAntennaAndThirtyDegreeAtFront()
     {
         Axis actAxis = acc.calculateCorrection(30.0, new Location(0.0, 0.0), new Axis(0.0,0.0,0.0), new Tower(0.0, 10.0, "Test"));
         Axis reqAxis = new Axis(-0.0, 60.0, -0.0);
         
         assertEquals(reqAxis.toString(), actAxis.toString());
     }
     
     @Test
     public void testCorrectionOfHundredEightyDegreeAngelToAntennaAndTwoHundredSeventyDegreeAtFront()
     {
         Axis actAxis = acc.calculateCorrection(270.0, new Location(0.0, 0.0), new Axis(0.0,0.0,0.0), new Tower(0.0, -10.0, "Test"));
         Axis reqAxis = new Axis(-0.0, 0.0, -0.0);
         
         assertEquals(reqAxis.toString(), actAxis.toString());
     }
     
     @Test
     public void testCorrectionOfNinetyDegreeAngelToAntennaAndHundredEightyDegreeAtFront()
     {
         Axis actAxis = acc.calculateCorrection(180.0, new Location(0.0, 0.0), new Axis(0.0,0.0,0.0), new Tower(0.0, 10.0, "Test"));
         Axis reqAxis = new Axis(-0.0, 270.0, -0.0);
         
         assertEquals(reqAxis.toString(), actAxis.toString());
     }
     
     @Test
     public void testReverseAngel_1()
     {
         double actAngel = acc.rotationToNorth(270.0, 180.0);
         double reqAngel = 90.0;
         
         assertEquals(reqAngel, actAngel, 1E-6);
     }
     
     @Test
     public void testReverseAngel_2()
     {
         double actAngel = acc.rotationToNorth(0.0, 347.0);
         double reqAngel = 347.0;
         
         assertEquals(reqAngel, actAngel, 1E-6);
     }
     
     @Test
     public void tetReverseAngel_3()
     {
         double actAngel = acc.rotationToNorth(360.0, 2.0);
         double reqAngel = 2.0;
         
         assertEquals(reqAngel, actAngel, 1E-6);
     }
     
     @Test
     public void tetReverseAngel_4()
     {
         double actAngel = acc.rotationToNorth(360.0, 361.0);
         double reqAngel = 1.0;
         
         assertEquals(reqAngel, actAngel, 1E-6);
     }
}