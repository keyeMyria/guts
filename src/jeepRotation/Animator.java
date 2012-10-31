package jeepRotation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import guts.gui.*;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Animator extends JPanel implements ActionListener {
    private Image img;
    private int direction = 0;
    private int simLength = 0;
    private double angel = 0;

    public Animator(Image img) {
        this.img = img;
        new Timer(16, this).start(); //Update our animations every 16ms
    }


    private double addRad(double current, double addition) {
        double value = current + addition;

        if (value >= 2 * Math.PI) {
            value -= 2 * Math.PI;
        }
        return value;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //jeep.update(); 
        if(simLength <= 0) {
            this.simLength = (int)(Math.random() * 100) + 1;
            int multi = (((int)Math.ceil(Math.random() * 3)));

            switch(multi) {
                case 3:
                    direction = 1;
                    break;
                case 2:
                    direction = -1;
                    break;
                case 1:
                default:
                    direction = 0;
            }
        }

        System.out.println("SimLength: " + simLength + "PosNeg: " + direction);

        if(direction != 0) {
            this.angel = addRad(this.angel, (double)((int)(Math.random() * 300 +1))/(10000*direction));
            img.rotateTo(this.angel);
            repaint(); //Draw the changes
        }
        simLength--;
    }
}
