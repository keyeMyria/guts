/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui.comp;

import java.awt.Component;
import javax.swing.JLayeredPane;

/**
 *
 * @author Patrick Selge
 */
public class MultiLayeredPane extends JLayeredPane {

    public MultiLayeredPane(Component ... comps){
        int layer = 0;
        
        
        for(Component c : comps) {
            this.add(c, new Integer(layer++)); 
        }
    }
   
}
