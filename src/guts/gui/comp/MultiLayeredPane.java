/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui.comp;

import java.awt.Component;
import javax.swing.JLayeredPane;

/**
 * Like a JLayeredPane, but more flexible about the layers.
 * 
 * @author Patrick Selge
 * @version 1.0
 */
public class MultiLayeredPane extends JLayeredPane {

    /**
     * Builds a new JLayeredPane that automaticly adds
     * a random number of components into seperate layers
     * 
     * @param comps The components, that are stacked
     */
    public MultiLayeredPane(Component ... comps){
        // Initilizes the level
        this.level = 0;
        
        // Iterates over all components
        for(Component c : comps) {
            this.add(c, new Integer(this.level++)); 
        }
    }
    
    /**
     * Adds a new component on top of the layer stack.
     * 
     * @param comp The new front most component
     */
    public void addComponentToNewLayer(Component comp) {
        this.add(comp,new Integer(level++));
    }
    
    private int level;
   
}
