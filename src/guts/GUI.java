/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts;

import guts.gui.*;
import guts.gui.comp.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import org.jdesktop.swingx.*;
import org.jdesktop.swingx.JXMapKit.DefaultProviders;
import org.jdesktop.swingx.mapviewer.GeoPosition;

/**
 *
 * @author Patrick Selge
 */
public class GUI extends JFrame implements Runnable {
    
    /* will move with it's container element */    
    
        
    /**
     * used by GUTS for the creation of a thread
     */
    @Override 
    public void run() {
        drawInterface();

        this.setVisible(true);
    }
    
    private void drawInterface() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setBackground(Color.lightGray);
        
        mainframe = new Mainframe(this.getContentPane());
               
        pack();
    }
    
    public void rotateJeep(double val) {
        mainframe.getJeep().rotateTo(Math.toRadians(val));
        pack();
        repaint();
        this.setVisible(true);
    }
    
    public void rotateAntenna(double val) {
        mainframe.getAntenna().rotateTo(Math.toRadians(val));
        pack();
        repaint();
        this.setVisible(true);
    }
    
    private Mainframe mainframe;
  
    
}
