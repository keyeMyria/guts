/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui.comp;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


/**
 *
 * @author patrick
 */
public final class VisualTextLabel extends JLabel {

    public VisualTextLabel() {        
        this.setText(0.0);
        
        this.setHorizontalAlignment(SwingConstants.CENTER);
        Font newLabelFont=new Font(this.getFont().getName(),Font.BOLD,this.getFont().getSize()); 
        this.setFont(newLabelFont);        
    }
    
    public void setText(double t) {
        super.setText(String.format("%2.2f°", t));
    }
    
}
