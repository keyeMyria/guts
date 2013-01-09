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
    private static GUTScontrol guts;
    
    
    /**
     * The main function
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        guts = new GUTScontrol();
        Thread gutsThread = new Thread( guts );
        gutsThread.start();
 
    }
}
