/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui;

import guts.Config;
import guts.entities.Location;
import guts.gui.comp.DrawableCanvas;
import guts.gui.comp.RotatableImage;
import guts.sensors.GPS;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.text.MessageFormat;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.Box;
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
        
        drawStatusPanel(sensorDataPanel);
  
        sensorDataPanel.add(Box.createRigidArea(new Dimension(0,5)));

        
        JPanel axisVisualPanel = new JPanel();
        axisVisualPanel.setLayout(new BorderLayout());
        
        jeepSide = new VisualAxisPanel(Config.VEHICLE_SIDE,185);
        
        axisVisualPanel.add(jeepSide);
        axisVisualPanel.add(drawAxisPanel(YAWN_PANEL,185));
        
        axisVisualPanel.setPreferredSize(new Dimension(PANEL_WIDTH,360));
        
        this.add(sensorDataPanel);
        this.add(axisVisualPanel);
    }
    
    private void drawStatusPanel(JPanel panel) {
        latitudeStatus = new StatusBox("Latitude") {
            @Override
            public void update(Observable t, Object o) {
                if(o instanceof Location) {
                    this.textField.setText(MessageFormat.format("{0,number,##.#####}",((Location)o).getLatitude()));
                }
            }
        };
        panel.add(latitudeStatus);
        
        longitudeStatus = new StatusBox("Longitude") {
            @Override
            public void update(Observable t, Object o) {
                if(o instanceof Location) {
                    this.textField.setText(MessageFormat.format("{0,number,##.#####}",((Location)o).getLongitude()));
                }
            }
        };
        panel.add(longitudeStatus);
        
        orientationStatus = new StatusBox("Ausrichtung");
        panel.add(orientationStatus);
        
        speedStatus = new StatusBox("Geschwindigkeit");
        panel.add(speedStatus);
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
    
    public StatusBox getLatitudeStatusBox() {
        return latitudeStatus;
    }
    
    public StatusBox getLongitutdeStatusBox() {
        return longitudeStatus;
    }
    
    
    private StatusBox latitudeStatus;
    private StatusBox longitudeStatus;
    private StatusBox orientationStatus;
    private StatusBox speedStatus;
    
    private VisualAxisPanel jeepSide;
    private VisualAxisPanel jeepFront;
    
    public static final int PANEL_WIDTH = 250;
    public static final int PANEL_HEIGHT = 600;
    
    public static final int PITCH_PANEL = 0;
    public static final int YAWN_PANEL = 1;
}
