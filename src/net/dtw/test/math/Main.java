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
        f.add(new BoundsDisplay());
        f.pack();
        f.setVisible(true);
    }
    
}
