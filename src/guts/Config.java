package guts;

/**
 * This class holds all the settings.
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
    public static boolean SIMULATIONENABLED = true;
    public static final int DEBUG = LOG_WAR;
    public static final int REFRESHRATE = 100; // time in miliseconds
    public static final int SIMREFRESHRATE = REFRESHRATE;
    public static final int SERVOTURNRATE = 5;
    
    // vehicle vars
    public static final String VEHICLE_TOP = "/img/jeep.top.png";
    public static final String VEHICLE_SIDE = "/img/jeep.side.png";
    public static final String VEHICLE_FRONT = "/img/jeep_front.png";
    
    // other images
    public static final String IMG_BACKGROUND = "/img/box.png";
  
    // GEO Data
    public static final double STARTLAT = 52.483791;
    public static final double STARTLON = 13.226141;
    public static final double CARSTARTLAT = 52.483791;
    public static final double CARSTARTLON = 13.226141;
    public static final double DEFAULTTOWERLAT = 52.483791;
    public static final double DEFAULTTOWERLON = 13.226141;
    public static final String DEFAULTTOWERNAME = "Default";
    
    // Engine borders
    public static final double PITCHENGINELEFTMAX = 0;
    public static final double PITCHENGINERIGHTMAX = 120;
    
    public static final double ROLLENGINELEFTMAX = 0;
    public static final double ROLLENGINERIGHTMAX = 180;
    
    //Engine start positions
    public static final double PITCHENGINESTART = 90;
    public static final double ROLLENGINESTART = 90;
    public static final double YAWENGINESTART = 0;
    
}
