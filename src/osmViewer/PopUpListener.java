package osmViewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Patrick Selge
 */
class PopUpListener implements MouseInputListener, ActionListener {

    public PopUpListener(OSMViewer controller) {
        this.controller = controller;
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
