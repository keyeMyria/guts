/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui;

import guts.gui.comp.RotatableImage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Patrick Selge
 */
public final class AppWindow {
    
    public AppWindow(GUI controller) {
        container = controller.getContentPane();
        container.setBackground(Color.lightGray);
        container.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        
        setSidebar(new Sidebar());
        setMainCanvas(new JPanel());
        setMenubar(new Menubar());
        setMapPanel(new MapPanel(controller)); 
    }
    
    public MapPanel getMapPanel() {
        return mapPanel;
    }
    
    public Sidebar getSidebar() {
        return this.sidebar;
    }
    
    private void setMainCanvas(JPanel mc) {
        mainCanvas = mc;
        mainCanvas.setLayout(new BoxLayout(mainCanvas, BoxLayout.PAGE_AXIS));
        container.add(mainCanvas);
    }
    
    
    
    public void setSidebar(Sidebar s) {
        container.add(sidebar = s, BorderLayout.WEST);
    }
    
    public void setMenubar(Menubar m) {
        mainCanvas.add(menubar = m);
    }
    
    public void setMapPanel(MapPanel mp) {
        mainCanvas.add(mapPanel = mp);
    }
    
    private Container container;
    
    private Sidebar sidebar;
    private JPanel mainCanvas;
    private Menubar menubar;
    private MapPanel mapPanel;
    
    public static final int FRAME_WIDTH = 1000;
    public static final int FRAME_HEIGHT = 700;
}
