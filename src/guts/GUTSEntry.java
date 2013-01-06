/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts;

import guts.gui.GUI;


/**
 *
 * @author Cedric Ohle
 */
public class GUTSEntry {
    public static GUI gui;
    public static GUTScontrol guts;
    
    /**
     * The main function
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        gui = new GUI();        
        Thread guiThread = new Thread( gui );
        guiThread.start();
        guiThread.join();  
        
        guts = new GUTScontrol();
        Thread gutsThread = new Thread( guts );
        gutsThread.start();
 
    }
}
