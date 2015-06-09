/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dtw.test.math;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import net.dtw.math.Boundd;
import net.dtw.math.Circled;
import net.dtw.math.Convexd;
import net.dtw.math.Ray2d;
import net.dtw.math.Vec2d;
import net.dtw.render.j2d.RenderHelper;

import static java.lang.Math.*;

/**
 *
 * @author Tiedye <tiedye1@hotmail.com>
 */
public class BoundsDisplay extends Component {
    
    ArrayList<Boundd> bounds;
    
    Convexd mH;
    Circled sH;

    public BoundsDisplay() {
        bounds = new ArrayList<>();
        
        ArrayList<Vec2d> vertices = new ArrayList<>();
        for (double r = 0.0; r < 2.0*PI; r += random()/2.0*PI) {
            vertices.add(Vec2d.newVec(0, 1).rotate(r));
        }
        mH = new Convexd(Vec2d.newVec(200.0, 200.0), vertices.toArray(new Vec2d[vertices.size()]));
        mH.setScale(100);
        
        bounds.add(mH);
        
        sH = new Circled(Vec2d.newVec(200.0, 200.0), 50.0);
        
        bounds.add(sH);
        
        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    double s = Math.random()*30+15;
                    bounds.add(new Circled(Vec2d.newVec(e.getX(), 400-e.getY()), s));
                    repaint();
                }
                if(e.getButton() == MouseEvent.BUTTON3){
                    double s = Math.random()*30+15;
                    ArrayList<Vec2d> vertices = new ArrayList<>();
                    for (double r = 0.0; r < 2.0*PI; r += PI/8 + random()*1.0/3.0*PI) {
                        vertices.add(Vec2d.newVec(0, 1).rotate(r));
                    }
                    Convexd c = new Convexd(Vec2d.newVec(e.getX(), 400-e.getY()), vertices.toArray(new Vec2d[vertices.size()]));
                    c.setScale(s);
                    c.setRotation(Math.random()*Math.PI*2);
                    bounds.add(c);
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //mH.setRotation(e.getX()/200.0*PI);
                //Vec2d d = Vec2d.newVec(0, 120).rotate(-e.getX()/200.0*PI);
                //sH.center = d.sum(200.0, 200.0);
                //repaint();
            }
            
        };
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }
    
    public void setRot(double r){
        mH.setRotation(r);
        Vec2d d = Vec2d.newVec(0, 120).rotate(-r);
        sH.center = d.sum(200.0, 200.0);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.scale(1.0f, -1.0f);
        g2.translate(0.0f, -400.0f);
        g2.setStroke(new BasicStroke(1));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setColor(Color.black);
        g2.fillRect(0, 0, 400, 400);
        g2.setColor(Color.green);
        RenderHelper.DrawArrow(Ray2d.newRay(Vec2d.newVec(10, 10), Vec2d.newVec(30, 10)), g2, 5.0f, 2.0f);
        g2.setColor(Color.blue);
        RenderHelper.DrawArrow(Ray2d.newRay(Vec2d.newVec(10, 10), Vec2d.newVec(10, 30)), g2, 5.0f, 2.0f);
        g2.setColor(Color.white);
        for (Boundd bound: bounds){
            RenderHelper.DrawPolygon(bound.getVerticies(), g2);
            if (bound instanceof Circled){
                Circled c = (Circled)bound;
                g2.draw(new Ellipse2D.Double(c.center.x-c.radius, c.center.y-c.radius, 2*c.radius, 2*c.radius));
            }
        }
        g2.setColor(Color.red);
        for(int i = 0; i < bounds.size() - 1; i++){
            for(int j = i + 1; j < bounds.size(); j++){
                Vec2d od = bounds.get(i).calcSA(bounds.get(j));
                if (od.magnitude() != 0.0) 
                RenderHelper.DrawArrow(Ray2d.newRay(Vec2d.newVec(200, 200), Vec2d.newVec(200, 200).sum(od)), g2, 5.0f, 2.0f);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
    
    
    
}
