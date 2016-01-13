/*
 * Draw panel class to get coordinates of mouse clicks and call shape class to
 * draw shape. Also has functions for clearing and deleting top most shape. 
 * Moves shape when right click
 */
package paint;

import java.awt.*;
import static java.awt.Color.*;
import java.awt.event.*;
import static java.lang.Math.*;
import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Allison Bodvig
 * @author Alex Herman
 */
class DrawPanel extends JPanel implements MouseListener, KeyListener
{
    private int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
    private boolean leftButtonPress = false;
    private boolean rightButtonPress = false;
    public LinkedList<Shape> shapeList = new LinkedList<>();
    public Shape currentShape;
    public Shape closestShape;
    public Color foregroundColor;
    public Color backgroundColor;
    
    // constructor: set up window
    public DrawPanel()
    {
        // detect mouse click events
        addMouseListener( this );
        foregroundColor = black;
        foregroundColor = black;
        currentShape = new Line();
    }

    // must override the following MouseListener methods
    public void mouseClicked( MouseEvent event ) { }
    public void mouseEntered( MouseEvent event ) { }
    public void mouseExited( MouseEvent event ) { }

    // mouse button press events (start drawing)
    public void mousePressed( MouseEvent event )
    {
        // check for left mouse button press
        if ( SwingUtilities.isLeftMouseButton( event ) )
        {
            x1 = event.getX();
            y1 = event.getY();
            leftButtonPress = true;
        }
        
        if ( SwingUtilities.isRightMouseButton( event ))
        {
            closestShape = getClosestShape(event.getX(), event.getY());
            rightButtonPress = true;
        }
    }

    // mouse button release events (finish drawing)
    public void mouseReleased( MouseEvent event )
    {
        if ( leftButtonPress )
        {
            x2 = event.getX();
            y2 = event.getY();
            
            if (!"Line".equals(currentShape.getClass().getSimpleName()))
            {
                if (x2 < x1)
                {
                    int temp = x2;
                    x2 = x1;
                    x1 = temp;
                }

                if (y2 < y1)
                {
                    int temp = y2;
                    y2 = y1;
                    y1 = temp;
                }
            }
            
            try {
                currentShape = currentShape.getClass().newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(DrawPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DrawPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            //set points
            currentShape.start = new Point();
            currentShape.end = new Point();
            currentShape.start.x = x1;
            currentShape.start.y = y1;
            currentShape.end.x = x2;
            currentShape.end.y = y2;
            currentShape.borderColor = foregroundColor;
            currentShape.backgroundColor  = backgroundColor;
            
            //add to list
            this.shapeList.add(currentShape);
            
            leftButtonPress = false;
            repaint();
        }
        
        //looks for shape to move
        if (rightButtonPress && closestShape != null)
        {
            int x = event.getX();
            int y = event.getY();
            
            //removes shape
            shapeList.remove(closestShape);
            
            int distanceX = (closestShape.end.x - closestShape.start.x) / 2;
            int distanceY = (closestShape.end.y - closestShape.start.y) / 2;
            
            closestShape.move(x - distanceX, y - distanceY, x + distanceX, y + distanceY);
            
            //adds shape to top of list, making it the topmost shape
            shapeList.add(closestShape);
            
            rightButtonPress = false;
            
            repaint();
        }
    }

    // paintComponent() is the display callback function
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        
        for (Shape s : shapeList)
        {
            s.draw(g);
        }
    }
    
    public void keyReleased( KeyEvent event ){}
    
    public void keyTyped( KeyEvent event ){}
    
    public void keyPressed( KeyEvent event )
    {
        //exits if escape key is pressed
        if (event.getKeyCode() == 27 || event.getKeyCode() == 81)
            System.exit(0);
    }
    
    //delete topmost shape
    public void deleteTopMost()
    {
        if (!shapeList.isEmpty())
        {
            shapeList.remove(shapeList.getLast());
        }
        
        repaint();
    }
    
    //clear all shapes from the panel
    public void clearList()
    {
        shapeList.clear();
        
        repaint();
    }
    
    //finds closest shape to the x and y cooridinates
    private Shape getClosestShape(int x, int y)
    {
        double minDistance = 10000;
        Shape closest = shapeList.getFirst();
        
        // find center of each shape
        for (Shape s : shapeList)
        {
            double centerX = (s.start.x + s.end.x) / 2;
            double centerY = (s.start.y + s.end.y) / 2;
            
            double distX = x - centerX;
            double distY = y - centerY;
            double dist = sqrt(distX * distX + distY * distY);
            
            //sets closed to right click coordinates
            if (dist < minDistance)
            {
                minDistance = dist;
                closest = s;
            }
        }
        
        if (minDistance > 50)
            closest = null;
        return closest;
    }
}
