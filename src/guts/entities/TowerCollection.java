
package guts.entities;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This Class holds a list of towers.
 * @author Cedric Ohle
 */
public class TowerCollection implements Iterable<Tower> {

    private LinkedList<Tower> list = new LinkedList<Tower>();
    
    /**
     * Allows to add a tower.
     * @param tower
     */
    public void add(Tower tower){
        list.add(tower);
    }
    
    /**
     * Finds a tower in the list by ID
     * @param ID
     * @return towerobject or null if not found
     */
    public Tower findByID(int ID){
        for (Tower t : list){
            if (t.getID() == ID){
                return t;
            }
        }
        return null;
    }
    
    
    /**
     * Iterator for list
     * @return iterator
     */
    @Override
    public Iterator<Tower> iterator() {
        return list.iterator();
    }
    
    /**
     * Returns if the list is empty
     * @return ture if empty else false
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    
    
}
