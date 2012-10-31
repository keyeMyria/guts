/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.entities;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class stores the track.
 * @author Cedric Ohle
 */
public class TrackLog implements Iterable<Location>{
    
    private LinkedList<Location> list = new LinkedList<Location>();
    
    /**
     * Adds a location to the log
     * @param location
     */
    public void add(Location location){
        list.add(location);
    }
    
    public Location getLast(){
        return list.getLast();
    }
    
    /**
     * Returns a iterator
     * @return
     */
    @Override
    public Iterator<Location> iterator() {
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
