/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osmViewer;

import guts.gui.GMapPanel;
import guts.gui.Menubar;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Set;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.MouseInputListener;
import org.jdesktop.swingx.mapviewer.GeoPosition;

/**
 *
 * @author Patrick Selge
 */
public class PopUpMenu extends JPopupMenu {
    
    public PopUpMenu(ActionListener listener) {
        
        JMenuItem newTower = new JMenuItem("Neuer Sendemast");
        newTower.setName("btn_new_tower");
        this.add(newTower);
        
        JMenuItem disableSimulation = new JMenuItem("Stoppe Simulation");
        disableSimulation.setEnabled(false);
        this.add(disableSimulation);
        
        
        newTower.addActionListener(listener);
    }
    
    public String askForTowerName() {                
        String s = "";
        while(s.equals("")) {
            s = (String)JOptionPane.showInputDialog(
                this,
                "Wie soll der Tower heißen?",
                "");
        }
        return s;
    }
    

    
    
    

}
