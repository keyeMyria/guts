/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui.entities;

import org.jdesktop.swingx.mapviewer.Waypoint;

/**
 *
 * @author Patrick Selge
 */
public class TowerIcon extends Waypoint {
    private String name;

    public TowerIcon(double x, double y, String name) {
        super(x,y);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
