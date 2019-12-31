/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 5
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

//================//
// WinPlace Class //
//================//
public class WinPlace extends Place {

    //--------------------------------------------------------------------------
    // Constructor
    public WinPlace(Scanner s, int version) {
        super(s, version);
        return;
    } // End of constructor
    
    //--------------------------------------------------------------------------
    // Ends the game.
    public void updatePlace(Character c) {
        
        JDialog message = new JDialog();
        message.setLayout(new FlowLayout());
        JLabel l = new JLabel(c.getName() + " has won the game!");
        message.add(l);
        message.setVisible(true);
        message.setTitle("YOU WON");
        message.setSize(600, 100);
        message.setResizable(true);
        message.setLocation(200,200);
        
        JButton b = new JButton("exit");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        message.add(b);
        
        while (true) { }
        
        
        
    } // End of updatePlace()
     
} // End of WinPlace class
