/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui.comp;

import guts.GUTScontrol;
import java.net.URL;
import javax.swing.*;

/**
 *
 * @author patrick
 */
public class StatusLED extends JLabel {
    private Icon greenLED;
    private Icon redLED;
    
    public StatusLED() {        
        cacheImages();  
        this.setIcon(greenLED);
        this.setDisabledIcon(redLED);
    }
        
    private void cacheImages() {
        URL ledResourceGreen = GUTScontrol.class.getResource("/img/green_led.png");
        URL ledResourceRed = GUTScontrol.class.getResource("/img/red_led.png");
        
        greenLED = new ImageIcon(ledResourceGreen);
        redLED = new ImageIcon(ledResourceRed);
    } 
    
}
