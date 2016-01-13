/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComponent;

/**
 *
 * @author Alex
 */
public interface ShapeInterface {    
    void draw(Graphics g);
    void move(int x1, int y1, int x2, int y2);
    String toString();
}