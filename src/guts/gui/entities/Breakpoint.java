/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui.entities;

import java.awt.geom.Point2D;
import org.jdesktop.swingx.mapviewer.Waypoint;

/**
 *
 * @author patrick
 */
public class Breakpoint extends Waypoint {
    private Point2D[] points;
    
    public Breakpoint(Waypoint wp, Point2D[] pts) {
        super(wp.getPosition());
        this.points = pts;
    }
    
    public Point2D getPoints(int zoom) {
        return points[zoom-1];
    }
}
