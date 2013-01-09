package guts.gui.comp;

import guts.gui.AppWindow;
import guts.gui.Menubar;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;

/**
 * Draws a minimap, that can be positioned absolute.
 * You can add layers on top of each other
 * 
 * @author Patrick Selge
 * @version 0.9
 */
public class Minimap extends DrawableCanvas {

    /**
     * Constructor - initializes and configures the canvas
     */
    public Minimap() {
        // Initialize and configure the minimap
        super(481,AppWindow.FRAME_HEIGHT-Menubar.PANEL_HEIGHT-279,
                BOX_WIDTH,BOX_HEIGHT);
        // TODO have the height, width and delta values more flexible
        
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.setBackground(new Color(0, 0, 0, 175));
        
        // Initialize the mulipanel
        this.layers = new MultiLayeredPane();
        this.add(this.layers);
    }
    
    /**
     * Adds a new component on top of the layer stack.
     * 
     * @param comp The new front most component
     * @see MultiLayeredPane#addComponentToNewLayer(java.awt.Component) 
     */
    public void addComponentToNewLayer(Component comp) {
        DrawableCanvas layer = new DrawableCanvas(0,0,BOX_WIDTH,BOX_WIDTH);
        layers.addComponentToNewLayer(layer);
        layer.add(comp);
    }
    
    private MultiLayeredPane layers;
    
    private static final int BOX_HEIGHT = 280;
    private static final int BOX_WIDTH = 280;
}
