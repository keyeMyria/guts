package guts.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Patrick Selge
 */
public final class AppWindow {
    
    /**
     * Holds the main container of the application
     * 
     * @param controller A reference to the GUI Controller
     */
    public AppWindow(GUI controller) {
        container = controller.getContentPane();
        container.setBackground(Color.lightGray);
        container.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));        
        
        // build up the sidebar
        sidebar = new Sidebar();
        container.add(sidebar, BorderLayout.LINE_START);
        
        // builds up the menubar and the map
        topMenubar = new Menubar();
        System.out.println("This is before the catastroph");
        mapPanel = new GMapPanel(controller);
        System.out.println("Here I am");
        container.add(drawMainContainer(topMenubar, mapPanel));
    }
    
    
    /**
     * Adds multiple Components to a JPanel which it then returns
     * 
     * @param comps Component, like a JPanel
     * @return The main container as a JPanel
     */
    public JPanel drawMainContainer(Object ... comps) {
        JPanel mc = new JPanel();
        mc.setLayout(new BoxLayout(mc, BoxLayout.Y_AXIS));
        
        for(Object comp: comps) {
            mc.add((Component) comp);
        }
        
        return mc;
    }
    
    /**
     * Returns the mapPanel to reach it's components
     * 
     * @return the mapPanel
     */
    public GMapPanel getMapPanel() {
        return mapPanel;
    }
    
    /**
     * Returns the sidebar to reach it's components
     * 
     * @return the sidebar
     */
    public Sidebar getSidebar() {
        return this.sidebar;
    }
    
    public Menubar getTopMenubar() {
        return this.topMenubar;
    }
    
    private Container container;
    
    private Sidebar sidebar;
    private Menubar topMenubar;
    private GMapPanel mapPanel;
    
    /**
     * The width of the application
     */
    public static final int FRAME_WIDTH = 1000;
    
    /**
     * The height of the application
     */
    public static final int FRAME_HEIGHT = 700;
}
