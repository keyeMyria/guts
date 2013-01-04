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
        
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testRoundOnEdgeCase() {
        double a = 0.00005;
        
        double expResult = 0.0001;
        double result = SimUtilities.round(a, 4);
        
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testMakeGGVAIsLargerB() {
        double a = 1.9;
        double b = 0.3;
        
        double expResult = 1.8;
        double result = SimUtilities.makeGGV((a),b);
        
        assertEquals(expResult, result, 1E-6);
    }
    
    @Test
    public void testMakeGGVBIsLargerA() {
        double a = 1.3;
        double b = 1.4;
        
        double expResult = 0.0;
        double result = SimUtilities.makeGGV((a),b);
        
        assertEquals(expResult, result, 1E-6);
    }
    
    @Test
    public void testMakeGGVBIsZero() {
        double a = 1.3;
        double b = 0.0;
        
        double expResult = 1.3;
        double result = SimUtilities.makeGGV((a),b);
        
        assertEquals(expResult, result, 1E-6);
    }

    @Test
    public void testGetRandomOddIntegerBetweenOneAndTen() {
        double a = 1.0;
        double b = 10.0;
        double stepSize = 2;

        int expResult = 50;        
        int result = 0;
        
        for(int i=0; i<50; i++) {
            double tmp_result = simUtilitiesTest.getRandomBetween(a, b, stepSize);
            if( (tmp_result >= 1) && (tmp_result <= 9) && 
                ((tmp_result % 2) == 1)) {
                result++; // inc result if tmp_result is geq 1, leq 9 and odd
            }
        }
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testGetRandomNumberBetweenOneAndTwoInOneFifthSteps() {
        double a = 1.0;
        double b = 2.0;
        double stepSize = 0.2;

        int expResult = 50;        
        int result = 0;
        
        for(int i=0; i<50; i++) {
            double tmp_result = simUtilitiesTest.getRandomBetween(a, b, stepSize);
            double x = (SimUtilities.round((tmp_result % 0.2),2));
            
            System.out.println(tmp_result);

            
            if( (tmp_result >= 1) && (tmp_result <= 2) && 
                ((x == 0.0) || (x == 0.2))) {
                result++; // inc result if tmp_result is geq 1, leq 2 and a multiple of 0.2
            }
        }
        assertEquals(expResult, result, 0.0);     
    }
    
    private SimUtilities simUtilitiesTest;
    
}
