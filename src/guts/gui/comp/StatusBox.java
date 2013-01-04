/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui.comp;

import guts.entities.Location;
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
        this(label, TYPE_DEFAULT);
    }
    
    public StatusBox(String label, int type) {
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        
        // Setting which type of value to visualize
        this.valueType = type;
        
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
        double value = getValue(o);
        
        this.textField.setText(MessageFormat.format("{0,number,##.#####}",value));
    }
    
    private double getValue(Object o) {
        switch(this.valueType) {
            case TYPE_LONGITUDE:
                return ((Location) o).getLongitude();
            case TYPE_LATITUDE:
                return ((Location) o).getLatitude();
        }
        
        return ((Double) o).doubleValue();
    }
    
    protected JTextField textField;
    private int valueType;
    
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_LONGITUDE = 1;
    public static final int TYPE_LATITUDE = 2;
}
