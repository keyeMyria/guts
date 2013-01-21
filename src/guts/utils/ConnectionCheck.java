package guts.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Checks your connections - like an internet connection
 * 
 * @author Patrick Selge
 */
public class ConnectionCheck {
    
    /**
     * Checks your online status by reading from a specified url
     * 
     * @param url Link to the website you want to use for an "online check"
     * @return Your online status
     */
    public static boolean isOnline(String url) { 
        BufferedReader reader;
        String line = null;
        
        try {
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            line = reader.readLine();
        } catch(Exception e) {}
        
        if((line) != null) {
            return true;
        }
        return false;
    }
}
