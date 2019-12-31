/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 5
 */

import java.util.ArrayList;

public interface UserInterface {
	public void display(String str);
    public String getLine();
    public void setInventory(ArrayList<String> inve, int carry, int limit);
    public void setEnabled(boolean state);
    public void setVisible(boolean state);
}

