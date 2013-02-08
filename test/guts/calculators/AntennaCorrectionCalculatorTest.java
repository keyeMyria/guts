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
     
     /*
      * diese Reihe testet, ob rotationToFront in allen Fällen eine gültige Winkelangabe (rel.) der Antenne
      * zurückgibt.
      * ob im falle eines negativen ergebnisses, solange 360 drauf addiert wird, bis man im
      * bereich 0-360 ist
      * 
      */
     
        @Test
     public void testRequiredAngle0()
     {
         Axis actAxis = acc.calculateCorrection(360.0, new Location(0.0, 0.0), new Axis(0.0,0.0,0.0), new Tower(0.0, 0.0, "Test"));
         double reqYawn = 0.0; //(der einegschlossene Winkel)
         
         assertEquals(reqYawn, actAxis.getYawn(), 1E-6);
     }
        
        
        @Test
     public void testRequiredAngle1()
     {
         Axis actAxis = acc.calculateCorrection(1.0, new Location(0.0, 0.0), new Axis(0.0,0.0,0.0), new Tower(0.0, 90.0, "Test"));
         double reqYawn = 89.0; //(der einegschlossene Winkel)
         
         assertEquals(reqYawn, actAxis.getYawn(), 1E-6);
     }
     
        @Test
     public void testRequiredAngle2()
     {
         Axis actAxis = acc.calculateCorrection(0.0, new Location(0.0, 0.0), new Axis(0.0,0.0,0.0), new Tower(0.0, 0.0, "Test"));
         double reqYawn = 0.0; //(der einegschlossene Winkel)
         
         assertEquals(reqYawn, actAxis.getYawn(), 1E-6);
     }
         
     @Test
     public void testRequiredAngle4()
     {
         Axis actAxis = acc.calculateCorrection(361.0, new Location(0.0, 0.0), new Axis(0.0,0.0,0.0), new Tower(0.0, -10.0, "Test"));
         double reqYawn = 269.0;
         
         assertEquals(reqYawn, actAxis.getYawn(), 1E-6);
     }
     
     @Test
     public void testRequiredAngle5()
     {
         Axis actAxis = acc.calculateCorrection(-1.0, new Location(0.0, 0.0), new Axis(0.0,0.0,0.0), new Tower(0.0, -10.0, "Test"));
         double reqYawn = 271.0;
         
         assertEquals(reqYawn, actAxis.getYawn(), 1E-6);
     }
     
      @Test
     public void testRequiredAngle6()
     {
         Axis actAxis = acc.calculateCorrection(-400.0, new Location(0.0, 0.0), new Axis(0.0,0.0,0.0), new Tower(0.0, -10.0, "Test"));
         double reqYawn = 310.0;
         
         assertEquals(reqYawn, actAxis.getYawn(), 1E-6);
     }
     @Test
     public void testRequiredAngleX()
     {
         Axis actAxis = acc.calculateCorrection(180.0, new Location(0.0, 0.0), new Axis(0.0,0.0,0.0), new Tower(0.0, 360.0, "Test"));
         double reqYawn = 90.0;
         
         assertEquals(reqYawn, actAxis.getYawn(), 1E-6);
     }
     
     @Test
     public void testRequiredAngleY()
     {
         Axis actAxis = acc.calculateCorrection(180.0, new Location(0.0, 0.0), new Axis(0.0,0.0,0.0), new Tower(0.0, -360.0, "Test"));
         double reqYawn = 270.0;
         
         assertEquals(reqYawn, actAxis.getYawn(), 1E-6);
     }
     
     
     /*
      * diese Reihe testet ob rotationToNorth gültige Winkelwerte zurückgibt
      * falls im Falle eines zu großen angel-wertes solange 360 drauf addiert wird,
      * bis man im bereich 0-360 liegt
      */
     @Test
     public void testReverseAngle1()
     {
         double actAngel = acc.rotationToNorth(1.0, 0.0);
         double reqAngel = 1.0;
         
         assertEquals(reqAngel, actAngel, 1E-6);
     }
     
     @Test
     public void testReverseAngle2()
     {
         double actAngel = acc.rotationToNorth(0.0, 347.0);
         double reqAngel = 347.0;
         
         assertEquals(reqAngel, actAngel, 1E-6);
     }
     
     @Test
     public void tetReverseAngle3()
     {
         double actAngel = acc.rotationToNorth(360.0, 361.0);
         double reqAngel = 1.0;
         
         assertEquals(reqAngel, actAngel, 1E-6);
     }
     
     @Test
     public void testReverseAngle4()
     {
         double actAngel = acc.rotationToNorth(361.0, 180.0);
         double reqAngel = 181.0;
         
         assertEquals(reqAngel, actAngel, 1E-6);
     }
     
       @Test
     public void testReverseAngle5()
     {
         double actAngel = acc.rotationToNorth(400.0, 180.0);
         double reqAngel = 220.0;
         
         assertEquals(reqAngel, actAngel, 1E-6);
     }
     
}

