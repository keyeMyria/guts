/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts;

import guts.sim.Simulation;
import java.util.concurrent.locks.*;


/**
 *
 * @author Cedric Ohle
 */
public class GUTSEntry {
    private static GUTScontrol guts;
    private static Simulation sim;
    //public static Lock guilock = new ReentrantLock();
    //public static Lock simlock = new ReentrantLock();
    
    
    /**
     * The main function
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        if(Config.SIMULATIONENABLED){
           sim = new Simulation();
            Thread simThread = new Thread( sim );
            simThread.start(); 
        }

        guts = new GUTScontrol();
        Thread gutsThread = new Thread( guts );
        gutsThread.start();
        

        
        
    }
    
    
}
