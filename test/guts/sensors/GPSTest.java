/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.sensors;

import guts.entities.Location;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Samsung
 */
public class GPSTest {
    
    public GPSTest() {
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
     * Test of setStartPoint method, of class GPS.
     */
    @Test
    public void testSetStartPoint() {
        System.out.println("setStartPoint");
        double latitude = 0.0;
        double longitude = 0.0;
        GPS instance = new GPS();
        instance.setStartPoint(latitude, longitude);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchLocation method, of class GPS.
     */
    @Test
    public void testFetchLocation() {
        System.out.println("fetchLocation");
        GPS instance = new GPS();
        Location expResult = null;
        Location result = instance.fetchLocation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
