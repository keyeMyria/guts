package guts.sim;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Holds all the utilities needed to do some basic maths in a simulation.
 * It provides the tools to have testable math without invoking your private
 * methods or setting seeds in different places.
 * Be careful about speed aspects. These methods do not provide high performance
 * calculation, but docus more on an easy to use aspect.
 * 
 * @author Patrick Selge
 * @version 1.0
 * @since 1.0
 */
public class SimUtilities {
    
    /**
     * Constructor of the utilities class
     * initilizes a random object to have testable random results
     */
    public SimUtilities() {
        this.random = new Random();
    }
    
    /**
     * Returns the sign (+/-) of a given value
     * 
     * @param Any double value
     * @return bool +1 or -1 
     */
    public static int getSign(double value) {
        return (value >= 0) ? 1 : -1;
    }
    
    /**
     * Returns a random number that lies between smallest and largest 
     * and is also a multiple of stepSize. The result is correct to 
     * ›this.precision‹ decimal places. The ›largest‹ number is corrected
     * to be a multiple of stepSize.
     * 
     * @param smallest  The smalles possible number that the result may be
     * @param largest   The largest included number the result may be
     * @param stepSize  The number of which the result must be a multiple of 
     * @return  A random number that matched the specification
     */
    public double getRandomBetween(double smallest, double largest, double stepSize) {
        largest = makeGGV((largest-smallest), stepSize) + smallest;
 
        double randomValue = Math.abs(random.nextInt()) * stepSize;  
        double tmp_result = (randomValue % (largest+stepSize-smallest));
                
        tmp_result = (stepSize % 1 == 0) ? tmp_result : tmp_result - stepSize;
        double result = tmp_result + smallest;
                
        return round(result, this.precision);
    }
     
    /**
     * Returns a truncated version of unrounded. The result is corrected
     * to ›precision‹ decimal places. 0.5 transforms to 1.0
     * 
     * @param unrounded The unrounded number
     * @param precision The number of decimal places
     * @return The rounded number
     */
    public static double round(double unrounded, int precision) {
        BigDecimal rounded = new BigDecimal(unrounded)
            .setScale(precision, BigDecimal.ROUND_HALF_UP);
        
        return rounded.doubleValue();
    }
    
    /**
     * Returns the largest in common multiple of a and b
     * 
     * @param a A arbitrary number
     * @param b Another arbitrary number
     * @return May return a if b is larger then a
     */
    public static double makeGGV(double a, double b) {
        if((b - (a%b)) > EPSILON) {
            return a - (a%b);   
        }
        return a;
    }
   
    /**
     * Makes it possible to test the other functions.
     * Sets the seed for all the random functions laying with in.
     * 
     * @param seed Gives a seed to the random object.
     */
    public void setRandomSeed(long seed) {
        this.random.setSeed(seed);
    }
    
    /**
     * Sets the number of decimal places for all the functions where you can't
     * pass this number as a parameter
     * 
     * @param precision the number of decimal places
     */
    public void setPrecision(int precision) {
        this.precision = precision;
    }
    
    private int precision = 6;
    private Random random;
    private static final double EPSILON = 1E-6;
}
