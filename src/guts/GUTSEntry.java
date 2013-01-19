package guts;

import guts.sim.Simulation;
import java.util.concurrent.Semaphore;


/**
 * This class creates a starting point for the program.
 * If enabled, it will also create the simulation.
 * @author Cedric Ohle
 */
public class GUTSEntry {
    private static GUTScontrol guts;
    private static Simulation sim;
    public static final Semaphore sem = new Semaphore(-1, true);
    
    
    /**
     * The main function
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        // Create Simulation if needed and the the simthread
        if(Config.SIMULATIONENABLED){
           sim = new Simulation();
            Thread simThread = new Thread( sim );
            simThread.start(); 
        }
        
        // Create GUTScontrol and start the controlthread
        guts = new GUTScontrol();
        Thread gutsThread = new Thread( guts );
        gutsThread.start();
        

        
        
    }
    
    
}
