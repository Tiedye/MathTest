/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dtw.test.math;

import java.awt.Graphics2D;
import net.dtw.math.Ray2d;
import net.dtw.math.Vec2d;

/**
 *
 * @author Tiedye <tiedye1@hotmail.com>
 */
public class RenderHelper {
    
    private RenderHelper(){}
    
    public static void DrawArrow(Ray2d ray, Graphics2D g) {
        g.drawLine((int)ray.a.x, (int)ray.a.y, (int)ray.b.x, (int)ray.b.y);
        Vec2d d = ray.getDirection();
        Vec2d dn = d.norm().scale(5.0);
        Vec2d on = d.orthoNorm().scale(5.0);
        Ray2d ta = new Ray2d(ray.a.sum(on).sum(dn), ray.a);
        Ray2d ba = new Ray2d(ray.a.sum(on.scale(-1.0)).sum(dn), ray.a);
        g.drawLine((int)ta.a.x, (int)ta.a.y, (int)ta.b.x, (int)ta.b.y);
        g.drawLine((int)ba.a.x, (int)ba.a.y, (int)ba.b.x, (int)ba.b.y);
    }
    
}
