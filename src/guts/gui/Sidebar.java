/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui;

import guts.Config;
import guts.gui.comp.DrawableCanvas;
import guts.gui.comp.RotatableImage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Patrick Selge
 */
public final class Sidebar extends JPanel {
    
    public Sidebar() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        
        drawLeftPanel();
    }
    
    private void drawLeftPanel() {

        JPanel sensorDataPanel = new JPanel();
        sensorDataPanel.setLayout(new BoxLayout(sensorDataPanel,BoxLayout.PAGE_AXIS));
        sensorDataPanel.setBorder(BorderFactory.createTitledBorder("Sensordaten"));
        sensorDataPanel.setPreferredSize(new Dimension(PANEL_WIDTH,240));
        
        JLabel l1 = new JLabel("Latitude");
        l1.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
        sensorDataPanel.add(l1);
        sensorDataPanel.add(new JTextField());

        JLabel l2 = new JLabel("Longitude");
        l2.setBorder(BorderFactory.createEmptyBorder(13, 0, 3, 0));
        sensorDataPanel.add(l2);
        sensorDataPanel.add(new JTextField());

        JLabel l3 = new JLabel("Ausrichtung");
        l3.setBorder(BorderFactory.createEmptyBorder(13, 0, 3, 0));
        sensorDataPanel.add(l3);
        sensorDataPanel.add(new JTextField());
        
        JLabel l4 = new JLabel("Geschwindigkeit");
        l4.setBorder(BorderFactory.createEmptyBorder(13, 0, 3, 0));
        sensorDataPanel.add(l4);
        sensorDataPanel.add(new JTextField());
        
        JPanel axisVisualPanel = new JPanel();
        axisVisualPanel.setLayout(new BorderLayout());
        axisVisualPanel.add(drawAxisPanel(PITCH_PANEL,0));
        axisVisualPanel.add(drawAxisPanel(YAWN_PANEL,185));
        
        axisVisualPanel.setPreferredSize(new Dimension(PANEL_WIDTH,360));
        
        this.add(sensorDataPanel);
        this.add(axisVisualPanel);
    }
    
    private DrawableCanvas drawAxisPanel(int image, int delta) {
        DrawableCanvas pitchPanel = new DrawableCanvas(0,delta+10,250,170);
        pitchPanel.setLayout(new BorderLayout());        
        
        JLayeredPane layeredMiniMap = new JLayeredPane();
        
        pitchPanel.add(layeredMiniMap);
         
        DrawableCanvas layer1 = new DrawableCanvas(0,delta+10,250,170);
        DrawableCanvas layer2 = new DrawableCanvas(0,delta+10,250,170);
        DrawableCanvas layer3 = new DrawableCanvas(75,delta+135,90,20);
        
        RotatableImage jeepSide = null;
        if(image == PITCH_PANEL) {
            jeepSide = new guts.gui.comp.RotatableImage(Config.VEHICLE_SIDE,120, 70);
        } else {
            jeepSide = new guts.gui.comp.RotatableImage(Config.VEHICLE_FRONT, 120, 70);
        }
        RotatableImage background = new guts.gui.comp.RotatableImage("/img/box.png",120,80);      
        
        JLabel label;
        
        String a = String.format("%2.2f°", 0.0);
        
        label = new JLabel(a);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        Font newLabelFont=new Font(label.getFont().getName(),Font.BOLD,label.getFont().getSize()); 
        label.setFont(newLabelFont);        
        layer3.add(label);
        
        layeredMiniMap.add(layer1, JLayeredPane.DEFAULT_LAYER);
        layeredMiniMap.add(layer2, JLayeredPane.POPUP_LAYER);
        layeredMiniMap.add(layer3, JLayeredPane.DRAG_LAYER);
        
        layer1.add(background);
        layer2.add(jeepSide);
        
        return pitchPanel;
        
    }
    
    
    
    public static final int PANEL_WIDTH = 250;
    public static final int PANEL_HEIGHT = 600;
    
    public static final int PITCH_PANEL = 0;
    public static final int YAWN_PANEL = 1;
}
