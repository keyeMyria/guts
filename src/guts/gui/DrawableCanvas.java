/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author patrick
 */
public class DrawableCanvas extends JPanel {
    
    public DrawableCanvas(int x, int y, int dx, int dy) {
        setBounds(x,y,dx,dy);
        setLayout(new BorderLayout());
        setOpaque(false);
    }
}
