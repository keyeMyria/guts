
package guts.entities;

/**
 * This class represents a tower. Towers a defined by a unique ID and a location.
 * @author Cedric Ohle
 */
public class Tower {
    private Location towerLocation;
    private int ID;
    
    
    /**
     * Allows setting the position of the tower.
     * @param towerLocation as location object
     */
    public void setLocation(Location towerLocation){
        this.towerLocation = towerLocation;
    }
    
    /**
     * Allows to read the location of the tower.
     * @return towerlocation as a location object
     */
    public Location getLocation(){
        return this.towerLocation;
    }
    
    /**
     * Returns the ID of the tower.
     * @return id as integer
     */
    public int getID(){
        return this.ID;
    }
}
