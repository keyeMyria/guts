/*
 * GUTS - GPS Utilized Tracking System
 */
package guts.gui;

import guts.gui.comp.TowerSelectBox;
import guts.Config;
import guts.gui.comp.SizedPanel;
import guts.gui.comp.StatusLED;
import guts.gui.comp.TitledBox;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import osmViewer.data.Tower;

/**
 *
 * @author Patrick Selge
 */
public final class Menubar extends JPanel {
    private JButton antennaControlButton;
    private Tower activeTower;
    
    public Menubar() {
        this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        this.setAlignmentX((float)0.0);
        
        drawStatusPanel();
        drawAntennaControlPanel();
        drawPositionControlPanel();
    }
    
    private void drawStatusPanel() {
        TitledBox statusPanel = new TitledBox("Statusinformationen",320,PANEL_HEIGHT);
        
        StatusLED antennaStatusLED = new StatusLED() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                if(Config.SIMULATIONENABLED) {
                    this.enable();
                } else {
                    this.disable();
                }
            }
        };
        StatusLED positionStatusLED = new StatusLED();
        
        statusPanel.add(drawStatusLine(antennaStatusLED, "Simulation"));
        statusPanel.add(drawStatusLine(positionStatusLED, "Antennenausrichtung"));
        
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
        
        this.antennaControlButton = drawControlButton("Aktivieren");
        
        antennaControlPanel.add(drawAntennaSelection());
        antennaControlPanel.add(wrapControlButton(antennaControlButton));
        
        this.add(antennaControlPanel);
    }
    
    private JPanel drawAntennaSelection() {
        SizedPanel panel = new SizedPanel(220,35,FlowLayout.CENTER);
        
        antennaSelection = new TowerSelectBox();
        antennaControlButton.addActionListener(new AntennaControlListener(antennaSelection, this));
        
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
    
    public void setTowers(ArrayList<Tower> towers) {
        antennaSelection.setTowers(towers);
    }
    
    public void setActiveTower(Tower activeTower) {
        this.activeTower = activeTower;
    }
    
    public Tower getActiveTower() {
        return this.activeTower;
    } 
    
    public static final int PANEL_HEIGHT = 100;
    public static TowerSelectBox antennaSelection;


}
