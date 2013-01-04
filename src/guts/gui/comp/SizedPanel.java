/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui.comp;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author Patrick Selge
 */
public class SizedPanel extends JPanel {
    
    public SizedPanel(int width, int height, int layout) {
        this.setLayout(new FlowLayout(layout));
        
        Dimension dim = new Dimension(width,height);        
        this.setMaximumSize(dim);
        this.setPreferredSize(dim);        
    }
    
    public SizedPanel(int width, int height) {
        this(width, height, FlowLayout.LEFT);
    }


}
