/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dtw.test.math;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import net.dtw.math.AABBd;
import net.dtw.math.Boundsd;
import net.dtw.math.Circled;
import net.dtw.math.Ray2d;
import net.dtw.math.Vec2d;

/**
 *
 * @author Tiedye <tiedye1@hotmail.com>
 */
public class BoundsDisplay extends Component {
    
    ArrayList<Boundsd> bounds;

    public BoundsDisplay() {
        bounds = new ArrayList<>();
        MouseAdapter mouseHandler = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    //double s = Math.random()*40+40;
                    double s = 60;
                    bounds.add(new AABBd(e.getY()+s, e.getY()-s, e.getX()+s, e.getX()-s));
                    repaint();
                }
                if(e.getButton() == MouseEvent.BUTTON3){
                    //double s = Math.random()*40+40;
                    double s = 60;
                    bounds.add(new Circled(new Vec2d(e.getX(), e.getY()), s));
                    repaint();
                }
            }
            
        };
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        g2.setColor(Color.black);
        g2.fillRect(0, 0, 400, 400);
        g2.setColor(Color.white);
        for (Boundsd bound: bounds){
            for (Ray2d side:bound.getSides()){
                g2.drawLine((int)side.a.x, (int)side.a.y, (int)side.b.x, (int)side.b.y);
            }
            Vec2d[] verticies = bound.getPoints();
            double[] radi = bound.getRadi();
            for (int i = 0; i < verticies.length; i++){
                g2.drawOval((int)(verticies[i].x-radi[i]), (int)(verticies[i].y-radi[i]), (int)radi[i]*2, (int)radi[i]*2);
            }
        }
        g2.setColor(Color.red);
        for(int i = 0; i < bounds.size() - 1; i++){
            for(int j = i + 1; j < bounds.size(); j++){
                Ray2d[] inter = bounds.get(i).calcSA(bounds.get(j));
                for(Ray2d inte: inter) {
                    RenderHelper.DrawArrow(inte, g2);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
    
    
    
}
