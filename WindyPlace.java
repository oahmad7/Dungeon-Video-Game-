/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 4
 */

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

//==================//
// WindyPlace Class //
//==================//
public class WindyPlace extends Place {
    private int destID;
    
    //--------------------------------------------------------------------------
    // Constructor
    public WindyPlace(Scanner s, int version) {
        super(s, version);
        String line = MyScanner.getCleanLine(s);
        destID = Integer.parseInt(line);
        return;
    } // End of constructor
    
    //--------------------------------------------------------------------------
    // Decides whether or not to trigger the trap
    public void updatePlace(Character c) {
        Place destination = Place.getPlaceByID(destID);
        ArrayList<String> artifactList = this.listArtifacts();
        for (String s : artifactList) {
            Artifact a = this.removeArtifact(s);
            destination.addArtifact(a);
            Character.messagePrint("-- The " + a.getName()
                                   + " was blown into the "
                                   + destination.getName() + " --", c);
        }
    } // End of updateTrap()
    
    //--------------------------------------------------------------------------
    // Prints debug information
    public void print() {
        super.print();
        return;
    } // End of print()
    
} // End of WindyPlace class
