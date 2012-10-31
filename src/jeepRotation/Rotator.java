/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jeepRotation;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class Rotator extends JFrame {

	public static void main(String[] args) {
		new Rotator();
	}

	public Rotator() {
		super();
		
		add(new Animator(), BorderLayout.CENTER);
		
		setVisible(true);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}