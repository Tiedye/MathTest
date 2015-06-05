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
import net.dtw.math.Boundd;
import net.dtw.math.Circled;
import net.dtw.math.Convexd;
import net.dtw.math.Ray2d;
import net.dtw.math.Vec2d;

/**
 *
 * @author Tiedye <tiedye1@hotmail.com>
 */
public class BoundsDisplay extends Component {
    
    ArrayList<Boundd> bounds;

    public BoundsDisplay() {
        bounds = new ArrayList<>();
        MouseAdapter mouseHandler = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    //double s = Math.random()*40+40;
                    double s = 60;
                    bounds.add(new Circled(Vec2d.newVec(e.getX(), e.getY()), 30));
                    repaint();
                }
                if(e.getButton() == MouseEvent.BUTTON3){
                    //double s = Math.random()*40+40;
                    double s = 60;
                    Convexd c = new Convexd(Vec2d.newVec(e.getX(), e.getY()), new Vec2d[]{
                        Vec2d.newVec(10, 30), Vec2d.newVec(24, 24),
                        Vec2d.newVec(30, -4), Vec2d.newVec(14, -26),
                        Vec2d.newVec(4, -30), Vec2d.newVec(-20, -22),
                        Vec2d.newVec(-30, 2), Vec2d.newVec(-28, 28)});
                    c.setRotation(Math.random()*Math.PI*2);
                    bounds.add(c);
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
        for (Boundd bound: bounds){
            for (Ray2d side:bound.getSides()){
                g2.drawLine((int)side.a.x, (int)side.a.y, (int)side.b.x, (int)side.b.y);
            }
            if (bound instanceof Circled){
                Circled c = (Circled)bound;
                g2.drawOval((int)(c.center.x-c.radius), (int)(c.center.y-c.radius), (int)(2*c.radius), (int)(2*c.radius));
            }
        }
        g2.setColor(Color.red);
        for(int i = 0; i < bounds.size() - 1; i++){
            for(int j = i + 1; j < bounds.size(); j++){
                Vec2d od = bounds.get(i).calcSA(bounds.get(j));
                System.out.println(od);
                RenderHelper.DrawArrow(Ray2d.newRay(Vec2d.newVec(200, 200), Vec2d.newVec(200, 200).sum(od)), g2);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
    
    
    
}
