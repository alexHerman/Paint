/*
 * This sets the menu bar and the actionListeners for the menu items. Also 
 * implements key listener for key inputs. Calls the necessary draw classes to 
 * draw shape to the panel
 *
 *
 * Portions of this class were adpated from GUIDemo.java by Dr. Weiss
 */

package paint;

import java.awt.*;
import static java.awt.Color.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Allison Bodvig
 * @author Alex Herman
 */
public class AppFrame extends JFrame implements KeyListener {
    
    //private data
    // set shapes
    private final String [] shapeItems = {"Line", "Rectangle", "Ellipse",
    "Filled Rectangle", "Filled Ellipse"};
    private JRadioButtonMenuItem [] mShape;
    
    // set colors
    private final String [] foregroundItems = {"Red", "Blue", "Green", "Orange",
    "Purple", "Black", "Yellow", "Gray"};
    private JRadioButtonMenuItem [] mFore;
    
    private final String [] backgroundItems = {"Red", "Blue", "Green", "Orange",
    "Purple", "Black", "Yellow", "Gray"};
    private JRadioButtonMenuItem [] mBack;
    
    public DrawPanel panel = new DrawPanel();
    
    public AppFrame()
    {
        //set JFrame
        this.setFocusable(true);
        this.setSize(700,700);
        this.setTitle("Paint");
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.addKeyListener(this);
        
        Container container = getContentPane();
        container.add( panel );
        
        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar( menuBar );
        
        // File 
        JMenu fileMenu = new JMenu( "File" );
        menuBar.add( fileMenu );
        
        //open file 
        JMenuItem mOpen = new JMenuItem( "Open" );
        mOpen.addActionListener( new ActionListener()
        {
           public void actionPerformed (ActionEvent mOpen )
           {
               fileOpen();              
           }
        } ) ;
        fileMenu.add( mOpen );
        
        //save option
        JMenuItem mSave = new JMenuItem( "Save" );
        fileMenu.add( mSave );
        
        //delete option
        JMenuItem mDelete = new JMenuItem( "Delete" );
        //call to delete top most option
        mDelete.addActionListener( new ActionListener() 
        {
            public void actionPerformed (ActionEvent mDelete)
            {
                panel.deleteTopMost();
            }
        } );
        fileMenu.add( mDelete );
        
        //clear option
        JMenuItem mClear = new JMenuItem ( "Clear" );
        //call to clear panel
        mClear.addActionListener( new ActionListener ()
        {
            public void actionPerformed (ActionEvent mClear)
            {
                panel.clearList();
            }
        } );
        fileMenu.add( mClear );
        
        JMenuItem mQuit = new JMenuItem( "Quit" );
        mQuit.addActionListener( new ActionListener()
        {
           public void actionPerformed (ActionEvent mExit )
           {
               System.exit(0);
           }
        } ) ;
        fileMenu.add( mQuit );
        
        //shape radio buttons
        JMenu shapeMenu = new JMenu( "Shape" );
        menuBar.add( shapeMenu );
        ButtonGroup shapes = new ButtonGroup(); 
        mShape = new JRadioButtonMenuItem [ shapeItems.length ];
        for (int i = 0; i < shapeItems.length; i++)
        {
            mShape[i] = new JRadioButtonMenuItem( shapeItems[i] );
            shapes.add( mShape[i] );
            mShape[i].addActionListener( new shapeMenuListener());
            shapeMenu.add(mShape[i]);  
        }
        //set default shape
        mShape[0].setSelected( true );
        
         
        //Foreground Color radio button
        JMenu foreMenu = new JMenu ( "Foreground Color" );
        menuBar.add( foreMenu );
        ButtonGroup foreColors = new ButtonGroup();
        mFore = new JRadioButtonMenuItem [ foregroundItems.length ];
        for (int i = 0; i < foregroundItems.length; i++)
        {
            mFore[i] = new JRadioButtonMenuItem( foregroundItems[i] );
            foreColors.add( mFore[i] );
            mFore[i].addActionListener( new foregroundMenuListener());
            foreMenu.add(mFore[i]);  
        }
        //set default selected foreground color
        mFore[5].setSelected( true );

        //Background Color radio buttons
        JMenu backMenu = new JMenu ( "Background Color" );
        menuBar.add( backMenu );
        ButtonGroup backColors = new ButtonGroup();
        mBack = new JRadioButtonMenuItem [ backgroundItems.length ];
        for (int i = 0; i < backgroundItems.length; i++)
        {
            mBack[i] = new JRadioButtonMenuItem( backgroundItems[i] );
            backColors.add( mBack[i] );
            mBack[i].addActionListener(new backgroundMenuListener());
            backMenu.add(mBack[i]);  
        }
        //set default background color
        mBack[5].setSelected( true );
        
        //Help Menu
        JMenu helpMenu = new JMenu ( "Help" );
        menuBar.add( helpMenu );
        
        //help message
        JMenuItem mHelp = new JMenuItem ( "Help" );
        mHelp.addActionListener( new ActionListener()
        {
           public void actionPerformed (ActionEvent mHelp )
           {
               JOptionPane.showMessageDialog(null, "To run select shape and "
                       + "foreground color. \n If filled shape also select "
                       + "background color. \n Pressing 'd' deletes the last shape"
                       + ", pressing 'c' clears all shapes. \n Pressing 'Esc' or 'q' "
                       + "exits the program.", "Help", JOptionPane.INFORMATION_MESSAGE);               
           }
        } ) ;
        helpMenu.add( mHelp );
        
        //about message
        JMenuItem mAbout = new JMenuItem ( "About" );
        mAbout.addActionListener( new ActionListener()
        {
           public void actionPerformed (ActionEvent mAbout )
           {
               JOptionPane.showMessageDialog(null, "Paint Version 0.0 \n"
                       + "Authors: Alex Herman and Allison Bodvig",
                       "About", JOptionPane.INFORMATION_MESSAGE);
           }
        } ) ;
        helpMenu.add( mAbout );
    }
    
    //opens file chooser
    private void fileOpen()
    {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog( this );
        
        if (returnVal == JFileChooser.APPROVE_OPTION )
        {
            File file = fc.getSelectedFile();
            System.out.println("Opening: " + file.getName() );
        }
    }
    
    public void keyReleased( KeyEvent event )
    {
        
    }
    
    public void keyTyped( KeyEvent event )
    {
        
    }
    
    //set events for key presses
    public void keyPressed( KeyEvent event )
    {
        //'esc', 'q' keys
        if (event.getKeyCode() == 27 || event.getKeyCode() == 81)
            System.exit(0);
        //'d' key
        if (event.getKeyCode() == 68)
            panel.deleteTopMost();
        //'c' key
        if (event.getKeyCode() == 67)
            panel.clearList();
    }
    
    //action listener to call correct shape class
    private class shapeMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent event)
        {
            JRadioButtonMenuItem source = (JRadioButtonMenuItem) event.getSource(); 
            String text = source.getText();

            if (text == "Line")
                panel.currentShape = new Line();
            if (text == "Rectangle")
                panel.currentShape = new Rectangle();
            if (text == "Ellipse")
                panel.currentShape = new Ellipse();
            if (text == "Filled Rectangle")
                panel.currentShape = new FilledRectangle();
            if (text == "Filled Ellipse")
                panel.currentShape = new FilledEllipse();
        }
    }
    
    //action listener to call correct foreground color for shape
    private class foregroundMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent event)
        {
            JRadioButtonMenuItem source = (JRadioButtonMenuItem) event.getSource(); 
            String text = source.getText();

            panel.foregroundColor = GetColorFromText(text);

        }
    }
    
    //action listener to call correct backgground color for filled shapes
    private class backgroundMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent event)
        {
            JRadioButtonMenuItem source = (JRadioButtonMenuItem) event.getSource(); 
            String text = source.getText();

            panel.backgroundColor = GetColorFromText(text);

        }
    }
    
    private Color GetColorFromText(String text)
    {
        if (text == "Red")
            return red;
        if (text == "Blue")
            return blue;
        if (text == "Green")
            return green;
        if (text == "Orange")
            return orange;
        if (text == "Purple")
            return magenta;
        if (text == "Black")
            return black;
        if (text == "Yellow")
            return yellow;
        if (text == "Gray")
            return gray;
        
        return black;
    }
        
}
