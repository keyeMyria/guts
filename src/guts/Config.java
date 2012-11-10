/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts;

/**
 *
 * @author Cedric Ohle
 * @author Patrick Selge
 */
public class Config {
    // enumerations
    public static final int LOG_ALL = 3;
    public static final int LOG_WAR = 2;
    public static final int LOG_ERR = 1;
    public static final int LOG_NON = 0;
    
    // global vars
    public static final boolean SIMULATIONENABLED = true;
    public static final int DEBUG = LOG_WAR;
    public static final int REFRESHRATE = 1000/24;
    
    // vehicle vars
    public static final String VEHICLE_TOP = "/img/jeep.top.png";
    public static final String VEHICLE_SIDE = "/img/jeep.side.png";
    public static final String VEHICLE_FRONT = "/img/jeep.front.png";
    
    // other images
    public static final String BACKGROUND = "/img/box.png";
}
