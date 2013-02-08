/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.calculators;

import guts.entities.Location;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patrick
 */
public class SpeedCalculatorTest {
    
    public SpeedCalculatorTest() {
    }
    
    /**
     * Test of reset method, of class SpeedCalculator.
     * If reseting is working and the startlocation is null, speed
     * 0 should be reported.
     */
    @Test
    public void testReset() {
        Location start = null;
        SpeedCalculator instance = new SpeedCalculator();
        instance.reset();
        double speed = instance.calculateSpeed(start);
        
        assertEquals(0.0, speed, 1E-6);
    }
    
    /**
     * Test of reset method, of class SpeedCalculator with a location set at the reset.
     * This time a location is set at the reset. Since both locations for
     * the calculation are the same, speed should be zero.
     */
    @Test
    public void testReset_Location() {
        Location start = new Location(0.0,0.0);
        SpeedCalculator instance = new SpeedCalculator();
        instance.reset(start);
        double speed = instance.calculateSpeed(start);
        
        assertEquals(0.0, speed, 1E-6);
    }
    
    
    /**
     * Test of the speedcalculationmethod.
     * One degree change of the Longitude is a distance of about 111.3 kilometers.
     * We are setting two locations, seperated by this one degree. Timestamps differ
     * by an hour. Since we calculating km/h it should be about 111.3 km/h.
     * For our purposes in the application the distances are much smaller so we
     * are using the much faster method of Phytagoras. A small bias of 0.2 percent
     * is therefor allowed.
     */
    @Test
    public void testSpeedCalculationOneDegreeLongitude(){
        Location start = new Location(0.0,0.0, new Date(0));
        SpeedCalculator instance = new SpeedCalculator();
        instance.reset(start);
        double speed = instance.calculateSpeed(new Location(0.0,1.0,new Date((long)3.6E6)));
        double reqSpeed = 111.3;
        
        assertEquals(reqSpeed, speed, ((reqSpeed/100)*0.2) );
    }
    
    /**
     * Test if zero movment is calculated correctly.
     */
    @Test
    public void testSpeedCalculationZeroMovement(){
        Location start = new Location(0.0,0.0, new Date(0));
        SpeedCalculator instance = new SpeedCalculator();
        instance.reset(start);
        double speed = instance.calculateSpeed(new Location(0.0,0.0,new Date((long)3.6E6)));
        double reqSpeed = 0;
        
        assertEquals(reqSpeed, speed, ((reqSpeed/100)*0.2) );
    }



}
