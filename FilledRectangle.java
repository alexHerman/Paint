/*
 * Draw filled rectangel to panel
 */
package paint;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Alex Herman
 */
public class FilledRectangle extends Rectangle {    
    public void draw(Graphics g)
    {    
        g.setColor(backgroundColor);
        g.fillRect(this.start.x, this.start.y, this.end.x - this.start.x, this.end.y - this.start.y);
        super.draw(g);
    }
}
