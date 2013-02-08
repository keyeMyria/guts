/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui;

import guts.gui.comp.TowerSelectBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import osmViewer.data.Tower;

/**
 *
 * @author patrick
 */
class AntennaControlListener implements ActionListener {
    private TowerSelectBox twsb;
    private final Menubar controller;
    
    
    public AntennaControlListener(TowerSelectBox twsb, Menubar controller) {
        this.twsb = twsb;
        this.controller = controller;
    }   

    @Override
    public void actionPerformed(ActionEvent ae) {
        controller.setActiveTower(this.twsb.getActiveTower());
    }
    
}
