/*
 * Draws the line to the panel
 */
package paint;

import java.awt.*;

/**
 *
 * @author Allison Bodvig
 */
public class Line extends Shape {
    public void draw(Graphics g)
    {
        g.setColor(borderColor);
        g.drawLine(start.x, start.y, end.x, end.y);
    }
    
    public void move(int x1, int y1, int x2, int y2)
    {
        super.start.x = x1;
        super.start.y = y1;
        super.end.x = x2;
        super.end.y = y2;
    }
    
    public String toString()
    {
        return this.getClass().toString();
    }
}
