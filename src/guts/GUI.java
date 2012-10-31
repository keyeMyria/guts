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
import guts.gui.Image;
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
        
        sensorDataPanel = new JPanel(new GridLayout(8,1));
        sensorDataPanel.setBorder(BorderFactory.createTitledBorder("Sensordaten"));
        axisVisualPanel = new JPanel(new GridLayout(2,1));
        
        leftPanel.add(sensorDataPanel);
        leftPanel.add(axisVisualPanel);
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
    
    private JPanel drawMinimap() {
        JPanel panel = new JPanel();     
        panel.setLayout(new BorderLayout());

        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panel.setBounds(481,321,280,280);
        panel.setBackground(new Color(0, 0, 0, 175));
        
         
        jeep = new guts.gui.Image("/img/Jeep.top.png",140, 140);
        jeep.setOpaque(false);
        
        
        
        panel.add(jeep);
        
        return panel;
    }
    
    public void rotateJeep(double val) {
        jeep.rotateTo(Math.toRadians(val));
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
    
    public Image jeep;
}
