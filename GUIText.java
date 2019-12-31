/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 5
 */

import java.util.ArrayList;
import java.util.Scanner;

public class GUIText implements UserInterface {
    
    //--------------------------------------------------------------------------
    public GUIText(String name) { }
    
    //--------------------------------------------------------------------------
    public void display(String str) {
        System.out.println(str);
    }
    
    //--------------------------------------------------------------------------
    public String getLine() {
        Scanner s = KeyboardScanner.getScanner();
        String input = s.nextLine();
        return input.trim();
    }
    
    //--------------------------------------------------------------------------
    // Dummy function
    public void setInventory(ArrayList<String> inve, int carry, int limit) { }
    
    //--------------------------------------------------------------------------
    // Dummy function
    public void setEnabled(boolean state) { }
    
    //--------------------------------------------------------------------------
    // Dummy function
    public void setVisible(boolean state) { }
    
}

