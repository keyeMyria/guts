package guts.entities;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Patrick Selge
 */
public class LocationTest {
    
    @Before
    public void setUp() {
        Date date = new Date();
        date.setTime(0); // january 1, 1970 00:00:00 gmt.
        
        locationTest = new Location(0.0,0.0,date);
    }


    @Test
    public void testSetAndGetTimestamp() {
        Date expResult = new Date();
        expResult.setTime(0); // january 1, 1970 00:00:00 gmt.
        
        Date result = locationTest.getTimestamp();
        
        assertTrue(expResult.equals(result));
        
        
        result.setTime(10000); // 10.000 ms after expResult  
        
        locationTest.setTimestamp(result);
        result = locationTest.getTimestamp();
        
        assertFalse(expResult.equals(result));
    }

    @Test
    public void testToString() {
        String expResult = "Thu Jan 01 01:00:00 CET 1970: [0.0] [0.0]"; 

        String result = locationTest.toString();
        
        assertTrue(expResult.equals(result));
    }

    @Test
    public void testDistanceTo_Location_ModePythagoras() {
        double expResult = 31.49; // rounded Value
        
        Location destination = new Location(0.2,0.2);
        
        double result = locationTest.distanceTo(destination);
        
        assertEquals(expResult, result, 0.1); // must be within 100 m
    }

    @Test
    public void testDistanceTo_Location_ModeExact() {
        double expResult = 31.49; // rounded Value
        
        Location destination = new Location(0.2,0.2);
        
        double result = locationTest.distanceTo(destination,2);
        
        assertEquals(expResult, result, 0.1); // must be within 100 m
    }

    @Test
    public void testGetRadianLatitude() {
        double expResult = Math.toRadians(locationTest.getLatitude());
        double result = locationTest.getLatitude();
        
        assertEquals(expResult, result, 1E-6);
    }

    @Test
    public void testGetRadianLongitude() {
        double expResult = Math.toRadians(locationTest.getLongitude());
        double result = locationTest.getLongitude();
        
        assertEquals(expResult, result, 1E-6);
    }
    
    private Location locationTest;
}
