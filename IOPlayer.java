/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 5
 */

import java.util.ArrayList;

//--------------------------------------------------------------------------
public class IOPlayer implements IO {
	
    private UserInterface[] ui;
    private int current;
    
    //--------------------------------------------------------------------------
    //
    public IOPlayer(String name) {
        ui = new UserInterface[4];
        ui[TEXT] = new GUIText(name);
	    
        ui[GUI_1] = new GUI1(name);
        ui[GUI_1].setVisible(false);
	    
	ui[GUI_2] = new GUI2(name);
        ui[GUI_2].setVisible(false);
	
	ui[GUI_3] = new GUI3(name);
        ui[GUI_3].setVisible(false);
        
        current = TEXT;
    }
    
    //--------------------------------------------------------------------------
    //
    public void display(String str) {
        // Print to all GUIs
        for (int i = 0; i < 4; i++) {
            ui[i].display(str);
        }
    }
    
    //--------------------------------------------------------------------------
    //
    public String getLine() {
        String line = ui[current].getLine();
        
        // Switch GUI if needed
        String[] words = line.split("[ \t]+");
        if (words[0].equalsIgnoreCase("text")) {
            selectInterface(TEXT);
            return "";
        }
        else if (words[0].equalsIgnoreCase("gui1")) {
            selectInterface(GUI_1);
            return "";
        }
        else if (words[0].equalsIgnoreCase("gui2")) {
            selectInterface(GUI_2);
            return "";
        }
        else if (words[0].equalsIgnoreCase("gui3")) {
            selectInterface(GUI_3);
            return "";
        }
        
        return line;
    }
    
    //--------------------------------------------------------------------------
    public void selectInterface(int i) {
        ui[current].setVisible(false);
        current = i;
        ui[current].setVisible(true);
    }
    
    //--------------------------------------------------------------------------
    public void setInventory(ArrayList<String> inve, int carry, int limit) {
        // Update inventory for all GUIs
        for (int i = 0; i < 4; i++) {
            ui[i].setInventory(inve, carry, limit);
        }
    }
    
    //--------------------------------------------------------------------------
    public void setEnabled(boolean state) {
        ui[current].setEnabled(state);
    }
    
}

