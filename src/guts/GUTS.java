/**
 * GUTS - GPS Utilized Tracking System
 * A tracking control device that sets the antenna
 * orientation on an offroad vehicle
 * 
 * @author Patrick Selge
 * @version 0.1
 */

package guts;

/**
 *
 * @author Patrick Selge
 */
public class GUTS {

    private static GUI gui;
    /* test */
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gui = new GUI();
        gui.main(null);
    }
}
