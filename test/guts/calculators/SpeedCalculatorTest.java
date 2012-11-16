/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.calculators;

import guts.entities.Location;
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
     * Test of calculateSpeed method, of class SpeedCalculator.
     */
    @Test
    public void testCalculateSpeed() {
        System.out.println("calculateSpeed");
        Location current = null;
        SpeedCalculator instance = new SpeedCalculator();
        double expResult = 0.0;
        double result = instance.calculateSpeed(current);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class SpeedCalculator.
     */
    @Test
    public void testReset_Location() {
        System.out.println("reset");
        Location start = null;
        SpeedCalculator instance = new SpeedCalculator();
        instance.reset(start);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class SpeedCalculator.
     */
    @Test
    public void testReset_0args() {
        System.out.println("reset");
        SpeedCalculator instance = new SpeedCalculator();
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class SpeedCalculator.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Observable t = null;
        Object o = null;
        SpeedCalculator instance = new SpeedCalculator();
        instance.update(t, o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSpeed method, of class SpeedCalculator.
     */
    @Test
    public void testGetSpeed() {
        System.out.println("getSpeed");
        SpeedCalculator instance = new SpeedCalculator();
        double expResult = 0.0;
        double result = instance.getSpeed();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
