/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 5
 */

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class IONPC extends JFrame implements IO {
    private JTextArea textBox;
    private JScrollBar scrollBar;
    
    //--------------------------------------------------------------------------
    public IONPC(String name) {
        this.setVisible(true);
        this.setTitle(name);
        this.setSize(400, 100);
        this.setResizable(true);
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        
        textBox = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textBox);
        scrollBar = scrollPane.getVerticalScrollBar();
        
        this.add(scrollPane);
    }
    
    //--------------------------------------------------------------------------
    // Print to textbox
    public void display(String str) {
        textBox.setCaretPosition(textBox.getDocument().getLength());
        textBox.replaceSelection(str + "\n");
        textBox.setCaretPosition(textBox.getDocument().getLength());

        System.out.println(str);
    }
    
    //--------------------------------------------------------------------------
    // Dummy function, NPCs don't need input
    public String getLine() { return null; }
    
    //--------------------------------------------------------------------------
    // Dummy function
    public void selectInterface(int i) { }
    
    //--------------------------------------------------------------------------
    public void setInventory(ArrayList<String> inve, int carry, int limit) {
        // TODO
    }
}
