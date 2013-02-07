package guts.gui.comp;

import guts.entities.Axis;
import java.awt.BorderLayout;
import java.util.Observable;

/**
 * Visualizes an axis by rotating an image inside a bubble and drawing
 * a label beneath it. It collects it's data through the observer pattern
 * and uses an Axis object.
 * 
 * @author Patrick Selge
 * @version 1.0
 */
public class AxisVisualization extends DrawableCanvas 
implements java.util.Observer {

    /**
     * Constructor - Builds up the visualization panel and sets it's 
     * representive axis.
     * 
     * @param img The centered image for the visualization of the angel
     * @param delta Offset in -y direction
     * @param axis must be one of AXIS_YAWN, AXIS_PITCH or AXIS_ROLL
     */
    public AxisVisualization(String img, int delta, int axis) {
        super(0,delta+10,250,170);
        this.setLayout(new BorderLayout());
        
        // Setting which axis to visualize
        this.axis = axis;
        
        // Building up the three layers
        DrawableCanvas backgroundLayer = new DrawableCanvas(0,delta+10,250,170);
        DrawableCanvas imageLayer = new DrawableCanvas(0,delta+10,250,170);
        DrawableCanvas textLayer = new DrawableCanvas(75,delta+135,90,20);
        
        // Initializing the components
        image = new RotatableImage(img,120, 70);
        text = new VisualTextLabel();
        
        // Adding the components to the layers
        backgroundLayer.add(new RotatableImage("/img/box.png",120,80));
        imageLayer.add(image);
        textLayer.add(text);
        
        // Drawing the three layers on a MultiLayeredPane
        this.add(new MultiLayeredPane(backgroundLayer, imageLayer, textLayer));
    }

    /**
     * Is called when ever the observable notifies a change.
     * It then rotates the image and sets the label below the image
     * to the new angel.
     * 
     * @param t The observable that caused the update
     * @param o The Axis object the observable passed to the obeserver
     */
    @Override
    public void update(Observable t, Object o) {
        double angel = getAngel((Axis) o);
        image.rotateTo(angel);
        text.setText(angel);
    }
    
    /**
     * Returns the angel of the axis that was set by the constructor
     * 
     * @param axis The Object that holds all three axis
     * @return Angel
     * @see #AxisVisualization(java.lang.String, int, int) 
     */
    private double getAngel(Axis axis) {
        switch(this.axis) {
            case AXIS_ROLL:
                return axis.getRoll();
            case AXIS_YAWN:
                return axis.getYawn();
            case AXIS_PITCH:
                return axis.getPitch();
        }
        
        return 0.0;
    }
    
    private RotatableImage image;
    private VisualTextLabel text;
    private int axis;
    
    public static final int AXIS_ROLL = 0;
    public static final int AXIS_YAWN = 1;
    public static final int AXIS_PITCH = 2;
}
