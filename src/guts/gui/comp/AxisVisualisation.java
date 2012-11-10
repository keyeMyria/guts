/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui.comp;

import guts.gui.comp.VisualTextLabel;
import guts.Config;
import guts.gui.comp.DrawableCanvas;
import guts.gui.comp.MultiLayeredPane;
import guts.gui.comp.RotatableImage;
import java.awt.BorderLayout;
import java.util.Observable;

/**
 *
 * @author Patrick Selge
 */
public class AxisVisualisation extends DrawableCanvas implements java.util.Observer {

    public AxisVisualisation(String img, int delta) {
        super(0,delta+10,250,170);
        this.setLayout(new BorderLayout());
        
        // Building up the three layers
        DrawableCanvas backgroundLayer = new DrawableCanvas(0,delta+10,250,170);
        DrawableCanvas imageLayer = new DrawableCanvas(0,delta+10,250,170);
        DrawableCanvas textLayer = new DrawableCanvas(75,delta+135,90,20);
        
        // Initializing the components
        image = new RotatableImage(img,120, 70);
        text = new VisualTextLabel();
        
        // Adding the components to the layers
        backgroundLayer.add(new RotatableImage(Config.BACKGROUND,120,80));
        imageLayer.add(image);
        textLayer.add(text);
        
        // Drawing the three layers on a MultiLayeredPane
        this.add(new MultiLayeredPane(backgroundLayer, imageLayer, textLayer));
    }

    @Override
    public void update(Observable t, Object o) {
        image.rotateTo(Math.toRadians(((Double)o).doubleValue()));
        text.setText(((Double)o).doubleValue());
    }
    
    protected RotatableImage image;
    protected VisualTextLabel text;
}
