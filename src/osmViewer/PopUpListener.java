package osmViewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import osmViewer.export.KmlExporter;

/**
 *
 * @author Patrick Selge
 */
class PopUpListener implements MouseInputListener, ActionListener {
    private KmlExporter exporter;

    public PopUpListener(OSMViewer controller) {
        this.controller = controller;
        this.exporter = new KmlExporter();
    }
    
    @Override
    public void mousePressed(MouseEvent evt) {        
        if (SwingUtilities.isRightMouseButton(evt)) {
            controller.showPopUpMenu(
                    evt.getComponent(), 
                    evt.getX(), 
                    evt.getY());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() instanceof JMenuItem) {
            JMenuItem jmi = (JMenuItem)ae.getSource();
            
            if(jmi.getName().equals("btn_new_tower")) {
                controller.setTower();
            } else if(jmi.getName().equals("btn_export")) {
                System.out.println("Exporting KML File");
                this.exporter.exportHistoryAsXML(controller.getWaypoints());
            }
        }
    }

    // Bunch of unused methods, that are only implemented because of interface
    // -----------------------------------------------------------------------
    @Override
    public void mouseClicked(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}

    @Override
    public void mouseDragged(MouseEvent me) {}

    @Override
    public void mouseMoved(MouseEvent me) {}
    
    // Attributes and Constans
    // -----------------------
    private OSMViewer controller;
}
