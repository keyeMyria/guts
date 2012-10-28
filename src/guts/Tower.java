/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts;

/**
 *
 * @author Cedric Ohle
 */
public class Tower {
    private Location towerLocation;
    private int ID;
    
    
    public void setLocation(Location towerLocation){
        this.towerLocation = towerLocation;
    }
    
    public Location getLocation(){
        return this.towerLocation;
    }
    
    public int getID(){
        return this.ID;
    }
}
