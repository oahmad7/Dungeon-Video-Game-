/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 5
 */

import java.util.ArrayList;

//--------------------------------------------------------------------------
public interface IO {
	public static final int TEXT = 0;
	public static final int GUI_1 = 1;
	public static final int GUI_2 = 2;
	public static final int GUI_3 = 3;
	
	public void display(String str);
	public String getLine();
	public void selectInterface(int i);
    public void setInventory(ArrayList<String> inve, int carry, int limit);
    public void setEnabled(boolean state);
}

