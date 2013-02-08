/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osmViewer;

import guts.Config;
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
    private JMenuItem disableSimulation;
    
    public PopUpMenu(ActionListener listener) {
        
        JMenuItem newTower = new JMenuItem("Neuer Sendemast");
        newTower.setName("btn_new_tower");
        this.add(newTower);
        

        this.disableSimulation = new JMenuItem("Stoppe Simulation");
        //disableSimulation.setEnabled(false);
        disableSimulation.setName("btn_disable_sim");
        this.add(disableSimulation);

        disableSimulation.addActionListener(listener);
        
        
        JMenuItem exportKML = new JMenuItem("Exportiere Pfad");
        exportKML.setName("btn_export");
        this.add(exportKML);
        
        
        newTower.addActionListener(listener);
        exportKML.addActionListener(listener);
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
    
    @Override
    public void repaint() {
        if(disableSimulation != null) {
            if(Config.SIMULATIONENABLED) {
                disableSimulation.setName("btn_disable_sim");
                disableSimulation.setLabel("Stoppe Simulation");
            } else {
                disableSimulation.setName("btn_enable_sim");
                disableSimulation.setLabel("Starte Simulation");
            }
        }
    }
    
    
    

}
