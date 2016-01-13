/*
* This just starts the program and brings up the frame.
* This program creates a paint program that lets the user select a shape and 
* a foreground an dbackground color for the shape. Also allows user to delete
* most recent shape, clear all shapes and move shapes around. 
*
*
* Authors: Alex Herman and Allison Bodvig
*/
package paint;

import javax.swing.*;

public class Paint {
    //create appFrame 
    public static void main(String[] args) {
        JFrame app = new AppFrame();
        app.setVisible(true);
    }
    
}