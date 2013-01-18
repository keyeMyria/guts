/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

/**
 *
 * @author Patrick Selge
 */
public class Log {
    
    private static FileWriter fw;
    
    // Singleton Pattern implementieren
    private static void openFile() throws IOException {
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
      fw = new FileWriter(f, true);

    }
    
    // Benötigt ein notify Level zum anzeigen von -> Warning, Error, Info etc.
    // Exception Meldungen müssen angepasst werden
    public static void writeToLog(int notify, String s) {
        try {
            openFile();
            
            switch(notify) {
                case ok_level:
                    fw.write("[OK]: " + s + "\n");
                    break;
                case notify_level:
                    fw.write("[Notify]: " + s);
                    break;
                case warn_level:
                    fw.write("[Warn]: " + s);
                    break;
                case error_level:
                    fw.write("[ERROR]: " + s);
                    break;
                default:
                    fw.write("[UNKNOWN]: " + s);
            }
            
            closeFile();
        } catch (IOException ex) {}
    }
    
    // ALLE Dateien schließen
    private static void closeFile() {
        try {
            fw.close();
        } catch (IOException ex) {}
    }
    
    public static final int error_level = 1;
    public static final int warn_level = 2;
    public static final int notify_level = 3;
    public static final int ok_level = 4;
}

