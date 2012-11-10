/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui.comp;

import java.text.MessageFormat;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Patrick Selge
 */
public class StatusBox extends JPanel implements java.util.Observer {

    public StatusBox(String label) {
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        
        // Creates the label
        JLabel lab = new JLabel(label);
        lab.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
        this.add(lab);
        
        // Creates the input field
        textField = new JTextField();
        textField.setEditable(false);
        this.add(textField);
    }
    
    public JTextField getTextField() {
        return textField;
    }
        
    
    @Override
    public void update(Observable t, Object o) {
        this.textField.setText(MessageFormat.format("{0,number,##.####}",o));
    }
    
    protected JTextField textField;
}
