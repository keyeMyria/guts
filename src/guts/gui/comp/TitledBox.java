/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui.comp;

import java.awt.Dimension;
import javax.swing.*; 

/**
 *
 * @author Patrick Selge
 */
public class TitledBox extends JPanel {

    public TitledBox(String title, int width, int height) {        
        this.setMaximumSize(new Dimension(width,height));
        this.setPreferredSize(new Dimension(width,height));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createTitledBorder(title));
        this.setAlignmentX(LEFT_ALIGNMENT);
    }

    
}
