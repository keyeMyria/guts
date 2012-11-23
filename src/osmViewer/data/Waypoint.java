package osmViewer.data;

import java.awt.geom.Point2D;
import guts.entities.Location;

/**
 *
 * @author Patrick Selge
 */
public class Waypoint extends Location {
    private Point2D[] points;
    
    public Waypoint(double lat, double lon) {
        super(lat, lon);
    }
    
    public void setPoints(Point2D[] pts) {
        this.points = pts;
    }
    
    public Point2D getPoints(int zoom) {
        return points[zoom-1];
    }
}
