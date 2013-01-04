 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.sim;

import guts.Config;
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
public class SimMagneticFieldSensorTest {
    
    private SimMagneticFieldSensor simMagneticFieldSensorTest;
    
    public SimMagneticFieldSensorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        simMagneticFieldSensorTest = new SimMagneticFieldSensor();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAngelToMagneticNorth method, of class SimMagneticFieldSensor.
     */
    @Test
    public void testGetAngelToMagneticNorth() {

    }

    /**
     * Test of getCurrentAngel method, of class SimMagneticFieldSensor.
     */
    @Test
    public void testGetCurrentAngel() {
        //System.out.println("getCurrentAngel");
        //double expResult = 0.0;
        //double result = SimMagneticFieldSensor.getCurrentAngel();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testGetDeltaAngelForRandomEqZero() {
        double random = 0.0;
        double expResult = 0.0000625*Config.REFRESHRATE;
        
        //double result = simMagneticFieldSensorTest.getDeltaAngel(random);
                
        //assertEquals(result, expResult, 1E-5);
    }
    
    @Test
    public void testGetDeltaAngelForRandomEqOne() {
        double random = 1.0;
        double expResult = 0.0188125*Config.REFRESHRATE;
        
        //double result = simMagneticFieldSensorTest.getDeltaAngel(random);
                
        //assertEquals(result, expResult, 1E-5);
    }
}
