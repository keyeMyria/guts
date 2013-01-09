/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Timestamp;
import java.sql.Date;
import java.util.logging.Level;

/**
 *
 * @author Patrick Selge
 */
public class Log {
    public static void main(String[] args) {
        try {
            Log logger = new Log();
            logger.writeToLog("Hallo Welt");
            logger.writeToLog("Hallo Welt");
            logger.writeToLog("Hallo Welt");
            logger.closeAll();
        } catch (IOException ex) { }
        
        
    }
    
    private final FileWriter fw;
    
    public Log() throws IOException {
        Date now = new Date(System.currentTimeMillis());
        File f = new File(now.toString() + ".log");
        
        
        
       if ( !f.exists() ) {
            try {
              f.createNewFile();
              System.out.println( "Legte neue Datei an.");
            } catch ( IOException e ) { 
                e.printStackTrace(); 
            }
            
      }
       

      fw = new FileWriter(f);

    }
    
    public void writeToLog(String s) {
        try {
            fw.append(s);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeAll() {
        try {
            fw.close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

