/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.sim;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Patrick Selge
 */
public class SimUtilitiesTest {

    @Before
    public void setUp() {
        simUtilitiesTest = new SimUtilities();
        simUtilitiesTest.setRandomSeed(7357); // makes the next Int -> 852519933
    }
   

    @Test
    public void testRound() {
        double a = 0.009;
        
        double expResult = 0.01;
        double result = SimUtilities.round(a, 2);
        
        assertEquals(result, expResult, 0.0);
    }
    
    @Test
    public void testRoundOnEdgeCase() {
        double a = 0.00005;
        
        double expResult = 0.0001;
        double result = SimUtilities.round(a, 4);
        
        assertEquals(result, expResult, 0.0);
    }
    
    @Test
    public void testMakeGGVAIsLargerB() {
        double a = 1.9;
        double b = 0.3;
        
        double expResult = 1.8;
        double result = SimUtilities.makeGGV((a),b);
        
        assertEquals(result, expResult, 1E-6);
    }
    
    @Test
    public void testMakeGGVBIsLargerA() {
        double a = 1.3;
        double b = 1.4;
        
        double expResult = 0.0;
        double result = SimUtilities.makeGGV((a),b);
        
        assertEquals(result, expResult, 1E-6);
    }
    
    @Test
    public void testMakeGGVBIsZero() {
        double a = 1.3;
        double b = 0.0;
        
        double expResult = 1.3;
        double result = SimUtilities.makeGGV((a),b);
        
        assertEquals(result, expResult, 1E-6);
    }

    
    /**
     * Test of getRandomBetween method, of class SimUtilities.
     */
    @Test
    public void testGetRandomOddIntegerBetweenOneAndTen() {
        double a = 1.0;
        double b = 10.0;
        double stepSize = 1;

        double expResult = 0.0;
        
        for(int i=0; i<50; i++) {
            double result = simUtilitiesTest.getRandomBetween(a, b, stepSize);
        }
        //assertTrue((result >= a && result <= b));
        //assertTrue((result%stepSize) < stepSize);        
    }
    
    @Test
    public void testGetRandomNumberBetweenOneAndTwoInOneFifthSteps() {
        double a = 1.0;
        double b = 2.0;
        double stepSize = 0.2;

        double expResult = 0.0;
        for(int i=0; i<50; i++) {
        double result = simUtilitiesTest.getRandomBetween(a, b, stepSize);

        //System.out.println(result);
        }
        //assertTrue((result >= a && result <= b));
        //assertTrue((result%stepSize) < stepSize);        
    }
    
    private SimUtilities simUtilitiesTest;
    
}
