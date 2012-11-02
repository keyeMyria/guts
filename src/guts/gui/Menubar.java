/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui;

import guts.gui.comp.SizedPanel;
import guts.gui.comp.StatusLED;
import guts.gui.comp.TitledBox;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author patrick
 */
public final class Menubar extends JPanel {
    
    public Menubar() {
        this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        this.setAlignmentX((float)0.0);
        
        drawStatusPanel();
        drawAntennaControlPanel();
        drawPositionControlPanel();
    }
    
    private void drawStatusPanel() {
        TitledBox statusPanel = new TitledBox("Statusinformationen",320,PANEL_HEIGHT);
        
        StatusLED antennaStatusLED = new StatusLED();
        StatusLED positionStatusLED = new StatusLED();
        
        positionStatusLED.setEnabled(false);
        
        statusPanel.add(drawStatusLine(antennaStatusLED, "Antennenausrichtung"));
        statusPanel.add(drawStatusLine(positionStatusLED, "Positionsausrichtung"));
        
        this.add(statusPanel);
    }
    
    private JPanel drawStatusLine(StatusLED led, String label) {
        SizedPanel panel = new SizedPanel(290, 35);
        panel.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        
        panel.add(led);
        panel.add(Box.createRigidArea(new Dimension(3,0)));
        panel.add(new JLabel(label));
         
        return panel;
    }
    
    private void drawAntennaControlPanel() {
        TitledBox antennaControlPanel = new TitledBox("Antennenausrichtung",220,PANEL_HEIGHT);
        
        JButton antennaControlButton = drawControlButton("Aktivieren");
        
        antennaControlPanel.add(drawAntennaSelection());
        antennaControlPanel.add(wrapControlButton(antennaControlButton));
        
        this.add(antennaControlPanel);
    }
    
    private JPanel drawAntennaSelection() {
        SizedPanel panel = new SizedPanel(220,35,FlowLayout.CENTER);
        
        JComboBox antennaSelection = new JComboBox();
        antennaSelection.addItem("Mast 1");
        antennaSelection.addItem("Mast 2");
        
        panel.add(new JLabel("Antenne:"));
        panel.add(antennaSelection);
                
        return panel;
    }
    
    private JPanel wrapControlButton(JButton button) {
        SizedPanel panel = new SizedPanel(220,35,FlowLayout.CENTER);
        panel.add(button);

        return panel;
    }
    
    private JButton drawControlButton(String label) {
        JButton button = new JButton(label);
        button.setPreferredSize(new Dimension(200,30));
        
        return button;
    }
    
    private void drawPositionControlPanel() {
        TitledBox positionControlPanel = new TitledBox("Positionsaufzeichnung",220,PANEL_HEIGHT);
        
        JButton positionControlResetButton = drawControlButton("Zurücksetzen");
        JButton positionControlToggleButton = drawControlButton("Aktivieren");
        
        positionControlPanel.add(wrapControlButton(positionControlResetButton));
        positionControlPanel.add(wrapControlButton(positionControlToggleButton));
        
        this.add(positionControlPanel);
    }
    
    public static final int PANEL_HEIGHT = 100;
}
