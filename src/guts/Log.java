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
    
    // Singleton Pattern implementieren
    public Log() throws IOException {
        Date now = new Date(System.currentTimeMillis());
        // Möglichkeit implementieren neue Dateien anlegen zu können
        // und die Dateien nach Notify Level zu benennen
        // Mehr Dateien sollte ebenfalls geöffnet werden können
        File f = new File(now.toString() + ".log");
        
        
        
       if ( !f.exists() ) {
            try {
              f.createNewFile();
              System.out.println( "Legte neue Datei an.");
            } catch ( IOException e ) { 
                e.printStackTrace(); 
            }
            
      }
       
       // Muss appenden können, falls !f.exists == false (else Strang) erreicht
      fw = new FileWriter(f);

    }
    
    // Benötigt ein notify Level zum anzeigen von -> Warning, Error, Info etc.
    // Exception Meldungen müssen angepasst werden
    public void writeToLog(String notify, String s) {
        try {
            fw.append(notify + ": " + s);
        } catch (IOException ex) {
        }
    }
    
    // ALLE Dateien schließen
    public void closeAll() {
        try {
            fw.close();
        } catch (IOException ex) {
        }
    }
}

