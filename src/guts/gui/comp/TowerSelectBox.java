/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui.comp;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import osmViewer.data.Tower;

/**
 *
 * @author patrick
 */
public class TowerSelectBox extends JComboBox implements ActionListener {
    private ArrayList<Tower> towers;
    private Tower activeTower;

    public TowerSelectBox() {
        this.addActionListener(this);
        // this.setEditable(true);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
         if(this.towers != null) {
            if(this.getItemCount() != this.towers.size()) {
                this.removeAllItems();
                for(Tower t : towers) {
                    this.addItem(t);
                }
            }
        } 
    }
    
    
    public void setTowers(ArrayList<Tower> towers) {
        this.towers = towers;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        JComboBox cm = (JComboBox)ae.getSource();
        this.activeTower = (Tower)cm.getSelectedItem();
    }
    
    public Tower getActiveTower() {
        return this.activeTower;
    }
}
