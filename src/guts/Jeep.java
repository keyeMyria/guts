/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts;

import java.net.URL;
import javax.swing.*;

/**
 *
 * @author patrick
 */
public class Jeep extends JLabel {
    public static final int BIRDVIEW = 1;
    public static final int SIDEVIEW = 2;
    public static final int FRONTVIEW = 3;
    
    private Icon jeepView;
    
    public Jeep(int view) {        
        cacheImage(view);  
        this.setIcon(jeepView);
    }
        
    private void cacheImage(int view) {
        URL jeepResource;
        
        switch(view) {
            case Jeep.FRONTVIEW:
                jeepResource = GUTS.class.getResource("/guts/jeep.front.png");
                break;
            case Jeep.SIDEVIEW:
                jeepResource = GUTS.class.getResource("/guts/jeep.side.png");
                break;
            case Jeep.BIRDVIEW:
            default:
                jeepResource = GUTS.class.getResource("/guts/jeep.top.png");
        }

        jeepView = new ImageIcon(jeepResource);
    } 
}
