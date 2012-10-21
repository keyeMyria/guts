/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

/**
 *
 * @author patrick
 */
public class ovalContainer implements Icon {
    @Override
    public void paintIcon( Component c, Graphics g, int x, int y ) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, getIconWidth(), getIconHeight());
        g.setColor( Color.ORANGE );
        g.drawOval(x+1, y+1, getIconWidth()-2, getIconHeight()-2);
        g.drawOval(x, y, getIconWidth(), getIconHeight());
        g.drawOval(x+5, y+5, getIconWidth()-10, getIconHeight()-10);
        g.drawOval(x+6, y+6, getIconWidth()-12, getIconHeight()-12);
    }

    @Override
    public int getIconWidth() {
        return 150;
    }

    @Override
    public int getIconHeight() {
        return 150;
    }
}
