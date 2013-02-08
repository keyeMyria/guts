/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.calculators;

import guts.entities.Location;
import java.util.Date;
import java.util.Observable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patrick
 */
public class SpeedCalculatorTest {
    
    public SpeedCalculatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of reset method, of class SpeedCalculator.
     */
    @Test
    public void testReset_Location() {
        Location start = null;
        SpeedCalculator instance = new SpeedCalculator();
        instance.reset(start);
        double speed = instance.calculateSpeed(start);
        
        assertEquals(0.0, speed, 1E-6);
    }
    
    
    
    @Test
    public void testSpeedCalculation(){
        Location start = new Location(0.0,0.0, new Date(0));
        SpeedCalculator instance = new SpeedCalculator();
        instance.reset(start);
        double speed = instance.calculateSpeed(new Location(0.0,1.0,new Date((long)3.6E6)));
        double reqSpeed = 111.3;
        
        assertEquals(reqSpeed, speed, ((reqSpeed/100)*0.2) );
    }


}
