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
import java.util.Collections;

//====================//
// HauntedPlace Class //
//====================//
public class HauntedPlace extends Place {

    //--------------------------------------------------------------------------
    // Constructor
    public HauntedPlace(Scanner s, int version) {
        super(s, version);
        return;
    } // End of constructor
    
    //--------------------------------------------------------------------------
    // Steals an artifact and places it in a random place.
    public void updatePlace(Character c) {
        ArrayList<String> artifactList = this.listArtifacts();
        if (artifactList.size() < 1) {
            return;
        }
        Collections.shuffle(artifactList);
        Artifact a = this.removeArtifact(artifactList.get(0));
        Character.messagePrint("-- A mischevious spirit has stolen the "
                           + a.getName() + " --", c);
        
        Place p = Place.getRandomPlace();
        
        p.addArtifact(a);
        Character.messagePrint("-- (put it in the " + p.getName() + ") --", c);
        
    } // End of updatePlace()
    
} // End of HauntedPlace class
