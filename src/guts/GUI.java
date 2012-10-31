/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts;

import guts.gui.SizedPanel;
import guts.gui.StatusLED;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import org.jdesktop.swingx.*;
import org.jdesktop.swingx.JXMapKit.DefaultProviders;
import guts.gui.*;
import org.jdesktop.swingx.mapviewer.GeoPosition;

/**
 *
 * @author Patrick Selge
 */
public class GUI extends JFrame implements Runnable {
        
    @Override 
    public void run() {
        drawInterface();
        antennaSelection.addItem("Test");
        antennaControlButton.setText("Deaktivieren");
        positionStatusLED.setEnabled(false);
        this.setVisible(true);
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {       

        
    }
    
    private void drawInterface() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setBackground(Color.lightGray);
        
        mainFrame = getContentPane();
        mainFrame.setBackground(Color.lightGray);
        mainFrame.setPreferredSize(new Dimension(1000,700));
        
        drawLeftPanel();
        drawMainPanel();

        mainFrame.add(leftPanel, BorderLayout.WEST);
        mainFrame.add(mainPanel);        
        
        pack();
    }
    
    private void drawLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(250,600));
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.PAGE_AXIS));
        
        sensorDataPanel = new JPanel();
        sensorDataPanel.setLayout(new BoxLayout(sensorDataPanel,BoxLayout.PAGE_AXIS));
        sensorDataPanel.setBorder(BorderFactory.createTitledBorder("Sensordaten"));
        sensorDataPanel.setPreferredSize(new Dimension(250,240));
        
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
        
        axisVisualPanel = new JPanel();
        axisVisualPanel.setLayout(new BorderLayout());
        axisVisualPanel.add(drawAxisPanel(Config.PITCH_PANEL,0));
        axisVisualPanel.add(drawAxisPanel(Config.YAWN_PANEL,185));
        
        axisVisualPanel.setPreferredSize(new Dimension(250,360));
        
        leftPanel.add(sensorDataPanel);
        leftPanel.add(axisVisualPanel);
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
        if(image == Config.PITCH_PANEL) {
            jeepSide = new guts.gui.RotatableImage("/img/jeep.side.png",120, 70);
        } else {
            jeepSide = new guts.gui.RotatableImage("/img/jeep.front.png", 120, 70);
        }
        RotatableImage background = new guts.gui.RotatableImage("/img/box.png",120,80);      
        
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
    
    private void drawMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        mainPanel.add(drawTopPanel());
        mainPanel.add(drawMapPanel());
    }
    
    private JPanel drawTopPanel() {
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.LINE_AXIS));
        
        topPanel.add(drawStatusPanel());
        topPanel.add(drawAntennaControlPanel());
        topPanel.add(drawPositionControlPanel());
        
        return topPanel;
    }
    
    private TitledBox drawStatusPanel() {
        statusPanel = new TitledBox("Statusinformationen",320,100);
        
        antennaStatusLED = new StatusLED();
        positionStatusLED = new StatusLED();
        
        statusPanel.add(drawStatusLine(antennaStatusLED, "Antennenausrichtung"));
        statusPanel.add(drawStatusLine(positionStatusLED, "Positionsausrichtung"));
        
        return statusPanel;
    }
    
    private JPanel drawStatusLine(StatusLED led, String label) {
        SizedPanel panel = new SizedPanel(290, 35);
        panel.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        
        panel.add(led);
        panel.add(Box.createRigidArea(new Dimension(3,0)));
        panel.add(new JLabel(label));
         
        return panel;
    }
    
    private TitledBox drawAntennaControlPanel() {
        antennaControlPanel = new TitledBox("Antennenausrichtung",220,100);
        
        antennaControlButton = drawControlButton("Aktivieren");
        
        antennaControlPanel.add(drawAntennaSelection());
        antennaControlPanel.add(wrapControlButton(antennaControlButton));
        
        return antennaControlPanel;
    }
    
    private JPanel drawAntennaSelection() {
        SizedPanel panel = new SizedPanel(220,35,FlowLayout.CENTER);
        
        antennaSelection = new JComboBox();
        
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
    
    private TitledBox drawPositionControlPanel() {
        positionControlPanel = new TitledBox("Positionsaufzeichnung",220,100);
        
        positionControlResetButton = drawControlButton("Zurücksetzen");
        positionControlToggleButton = drawControlButton("Aktivieren");
        
        positionControlPanel.add(wrapControlButton(positionControlResetButton));
        positionControlPanel.add(wrapControlButton(positionControlToggleButton));
        
        return positionControlPanel;
    }
    
    private JLayeredPane drawMapPanel() {
        layeredMap = new JLayeredPane();
        
        JXMapKit mapKit = new JXMapKit();
        mapKit.setName("mapKit");
        
        mapKit.setDefaultProvider(DefaultProviders.OpenStreetMaps); 
        
        mapKit.setMiniMapVisible(false);
        mapKit.setZoomSliderVisible(false);
        
        mapKit.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        mapKit.setBounds(0, 0, 752, 602);
        mapKit.setAddressLocation(new GeoPosition(52.483791,13.226141));
        
        
                
        JPanel minimap = drawMinimap();
        
        layeredMap.add(mapKit, JLayeredPane.DEFAULT_LAYER);
        layeredMap.add(minimap, JLayeredPane.POPUP_LAYER);
        
        return layeredMap;
    }
    
    private DrawableCanvas drawMinimap() {
        DrawableCanvas minimap = new DrawableCanvas(481,321,280,280);  
        minimap.setOpaque(true);
        minimap.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        minimap.setBackground(new Color(0, 0, 0, 175));
        
        JLayeredPane layeredMiniMap = new JLayeredPane();
        
        minimap.add(layeredMiniMap);
         
        DrawableCanvas layer1 = new DrawableCanvas(0,0,280,280);
        DrawableCanvas layer2 = new DrawableCanvas(0,0,280,280);
        
        jeep = new guts.gui.RotatableImage("/img/jeep.top.png",140, 140);
        antenna = new guts.gui.RotatableImage("/img/antenna.png",140,140);        
        
        layeredMiniMap.add(layer1, JLayeredPane.DEFAULT_LAYER);
        layeredMiniMap.add(layer2, JLayeredPane.POPUP_LAYER);
        
        layer1.add(jeep);
        layer2.add(antenna);
        
        return minimap;
    }
    
    public void rotateJeep(double val) {
        jeep.rotateTo(Math.toRadians(val));
        pack();
        repaint();
        this.setVisible(true);
    }
    
    public void rotateAntenna(double val) {
        antenna.rotateTo(Math.toRadians(val));
        pack();
        repaint();
        this.setVisible(true);
    }
    
    private Container mainFrame;
    private JPanel leftPanel;
    private JPanel mainPanel;
    private JPanel topPanel;
    
    private JLayeredPane layeredMap;
    
    private JPanel sensorDataPanel;
    private JPanel axisVisualPanel;
    private TitledBox statusPanel;
    private TitledBox antennaControlPanel;
    private TitledBox positionControlPanel;
    
    private StatusLED antennaStatusLED;
    private StatusLED positionStatusLED;
    
    private JComboBox antennaSelection;
    
    private JButton antennaControlButton;
    private JButton positionControlToggleButton;
    private JButton positionControlResetButton;    
    
    public RotatableImage jeep;
    public RotatableImage antenna;
}
