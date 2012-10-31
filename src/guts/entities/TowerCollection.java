/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.entities;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Cedric Ohle
 */
public class TowerCollection implements Iterable<Tower> {

    private LinkedList<Tower> list = new LinkedList<Tower>();
    
    public void add(Tower tower){
        list.add(tower);
    }
    
    public Tower findByID(int ID){
        for (Tower t : list){
            if (t.getID() == ID){
                return t;
            }
        }
        return null;
    }
    
    
    @Override
    public Iterator<Tower> iterator() {
        return list.iterator();
    }
    
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    
    
}
