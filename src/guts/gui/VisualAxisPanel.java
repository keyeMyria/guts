/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui;

import guts.gui.comp.DrawableCanvas;
import java.awt.BorderLayout;
import java.util.Observable;

/**
 *
 * @author Patrick Selge
 */
public class VisualAxisPanel extends DrawableCanvas implements java.util.Observer {

    public VisualAxisPanel(String img, int delta) {
        super(0,delta+10,250,170);
        this.setLayout(new BorderLayout());   
    }

    @Override
    public void update(Observable t, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
