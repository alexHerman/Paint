/*
 * A abstract class that all shapes must inherit. It provides the mechanisms for 
 * drawing the shapes to the screen and storing the relevant information.
 */

package paint;

import java.awt.*;
import javax.swing.JComponent;

/**
 * @author Allison Bodvig
 * @author Alex Herman
 */
abstract class Shape extends JComponent implements ShapeInterface{
    public Point start;
    public Point end;
    
    public Color borderColor;
    public Color backgroundColor;
}