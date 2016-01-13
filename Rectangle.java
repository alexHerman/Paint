/*
 * Draw rectangel to panel
 */
package paint;

import java.awt.*;

/**
 *
 * @author Alex Herman
 */
public class Rectangle extends Shape {
    
    public void draw(Graphics g)
    {
        g.setColor(borderColor);
        g.drawRect(this.start.x, this.start.y, this.end.x - this.start.x, this.end.y - this.start.y);
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