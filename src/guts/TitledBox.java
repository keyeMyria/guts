/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts;

import java.awt.Dimension;
import javax.swing.*; 

/**
 *
 * @author patrick
 */
public class TitledBox extends JPanel {

    TitledBox(String title, int width, int height) {        
        this.setMaximumSize(new Dimension(width,height));
        this.setPreferredSize(new Dimension(width,height));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createTitledBorder(title));
        this.setAlignmentX(LEFT_ALIGNMENT);
    }

    
}
