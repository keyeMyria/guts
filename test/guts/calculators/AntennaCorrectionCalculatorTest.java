/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.calculators;

import guts.entities.Axis;
import guts.entities.Location;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Fethiye Güney
 */

public class AntennaCorrectionCalculatorTest {
    
     @Rule
     public ExpectedException exception = ExpectedException.none();

     // testet, ob currentAngle innerhalb 0 und 359 liegt 
     // da die Werte, die außerhalb liegen, ungültig sind
     @Test
     public void testCurrentAngleOutOfBounds() 
     {
    AntennaCorrectionCalculator testClass = new AntennaCorrectionCalculator();
    exception.expect(IllegalArgumentException.class);
    testClass.calculateAngel(new Location(0.0,0.0),new Axis(),-1, new Location(0.0,0.0));

    testClass.calculateAngel(new Location(0.0,0.0),new Axis(),360, new Location(0.0,0.0));
     }
}

