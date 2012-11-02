/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.gui;

import guts.Config;
import guts.gui.comp.DrawableCanvas;
import guts.gui.comp.RotatableImage;
import guts.utils.*;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.mapviewer.GeoPosition;

/**
 *
 * @author Patrick Selge
 */
public final class MapPanel extends JLayeredPane {
    
    public MapPanel() {
        drawMapPanel();
    }
    
    private void drawMapPanel() {   
        
        JPanel mapKit;
        
        // checks if the computer can reach one of the specified urls
        if(ConnectionCheck.isOnline("http://www.google.com") ||
           ConnectionCheck.isOnline("http://www.heise.de")) {
            
            JXMapKit mk = new JXMapKit();
            mk.setName("mapKit");
            

            mk.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps); 

            mk.setMiniMapVisible(false);
            mk.setZoomSliderVisible(false);
            mk.setAddressLocation(new GeoPosition(52.483791,13.226141)); 
            
            mapKit = mk;
        } else {
            mapKit = new JPanel();
            mapKit.setBackground(Color.DARK_GRAY);
        }

            mapKit.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            mapKit.setBounds(0, 0, 752, 602);
                   
        
            this.add(mapKit, JLayeredPane.DEFAULT_LAYER);
        
        
        JPanel minimap = drawMinimap();
        this.add(minimap, JLayeredPane.POPUP_LAYER);
        
    }
    
    private DrawableCanvas drawMinimap() {
        DrawableCanvas minimap = new DrawableCanvas(481,Mainframe.FRAME_HEIGHT-Menubar.PANEL_HEIGHT-279,280,280);  
        minimap.setOpaque(true);
        minimap.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        minimap.setBackground(new Color(0, 0, 0, 175));
        
        JLayeredPane layeredMiniMap = new JLayeredPane();
        
        minimap.add(layeredMiniMap);
         
        DrawableCanvas layer1 = new DrawableCanvas(0,0,280,280);
        DrawableCanvas layer2 = new DrawableCanvas(0,0,280,280);
        
        jeep = new guts.gui.comp.RotatableImage(Config.VEHICLE_TOP,140, 140);
        antenna = new guts.gui.comp.RotatableImage("/img/antenna.png",140,140);        
        
        layeredMiniMap.add(layer1, JLayeredPane.DEFAULT_LAYER);
        layeredMiniMap.add(layer2, JLayeredPane.POPUP_LAYER);
        
        layer1.add(jeep);
        layer2.add(antenna);
        
        return minimap;
    }
    
    public RotatableImage getJeep() {
        return this.jeep;
    }
    
    public RotatableImage getAntenna() {
        return this.antenna;
    }
    
    private RotatableImage jeep;
    private RotatableImage antenna;
    
}
