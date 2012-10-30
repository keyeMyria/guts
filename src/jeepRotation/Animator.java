package jeepRotation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Animator extends JPanel implements ActionListener {
	private Jeep jeep;

	public Animator() {
		setPreferredSize(new Dimension(500, 500));
		jeep = new Jeep(250, 250);
		new Timer(16, this).start(); //Update our animations every 16ms
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		jeep.draw(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jeep.update(); 
		repaint(); //Draw the changes
	}
}
