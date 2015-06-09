/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dtw.test.math;

import javax.swing.JFrame;

/**
 *
 * @author Tiedye <tiedye1@hotmail.com>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame f = new JFrame("Separating Axis Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BoundsDisplay b = new BoundsDisplay();
        f.add(b);
        f.pack();
        f.setVisible(true);
        double r = 0.0;
        while(true){
            r += 0.001;
            b.setRot(r);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                
            }
        }
    }
    
}
