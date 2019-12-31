/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

import java.util.Scanner;
import java.util.Random;

//================//
// Artifact Class //
//================//
public class Artifact {
    
    private int id;
    protected String name;
    protected String description;
    private int value;
    private int mobility;
    private int keyID;
    
    //--------------------------------------------------------------------------
    // Constructor
    public Artifact(Scanner s, int version) {
        
        // Get the location
        String line = MyScanner.getCleanLine(s);
        int locationID = Integer.parseInt(line);
        
        // Read attributes
        line = MyScanner.getCleanLine(s);
        String words[] = line.split("[ \t]+", 5);
        
        id = Integer.parseInt(words[0]);
        value = Integer.parseInt(words[1]);
        mobility = Integer.parseInt(words[2]);
        keyID = Integer.parseInt(words[3]);
        name = words[4];
        
        // Get the description
        description = "";
        line = MyScanner.getCleanLine(s);
        int descLines = Integer.parseInt(line);
        for (int i = 0; i < descLines; i++) {
            line = MyScanner.getCleanLine(s);
            description += line;
        }
        
        // Place the artifact
        if (version < 40 || locationID > 0) {
            Place p = Place.getPlaceByID(locationID);
            if (p == null) {
                System.out.println("Warning: Artifact "
                                   + id + "'s place is null.");
                p = Place.getPlaceByID(1);
            }
            p.addArtifact(this);
        }
        else if (version == 40) {
            if (locationID < 0) {
                Character c = Character.getCharacterByID(-locationID);
                if (c == null) {
                    System.out.println("Warning: Artifact "
                                       + id + "'s character is null.");
                    c = Character.getCharacterByID(1);
                }
                c.addArtifact(this);
            }
            else if (locationID == 0) {
                Place p = Place.getRandomPlace();
                p.addArtifact(this);
                //System.out.println("Randomly added artifact "
                //                   + name + " at " + p.getName());
            }
        }
        
        return;
    } // End of constructor
    
    //--------------------------------------------------------------------------
    // Returns the artifact name
    public String getName() {
        return name;
    } // End of getName()
    
    //--------------------------------------------------------------------------
    // Returns the artifact ID
    public int getID() {
        return id;
    } // End of getName()
    
    //--------------------------------------------------------------------------
    // This is needed for the inventory mobility limit
    public int getMobility() {
       return mobility;
    } // End of getMobility()
    
    //--------------------------------------------------------------------------
    // Returns this artifact's info in the place description
    public String getProperties() {
        if (mobility >= 0)
            return name + " (Value: " + value + ", Mobility: " + mobility + ")";
        else
            return name + " (Value: " + value + ", Mobility: *immovable*)";
    } // End of getProperties()
    
    //--------------------------------------------------------------------------
    // Prints this artifact's line in the inventory screen
    public void displayInInventory() {
        System.out.println("  -" + name + "-\tValue: " + value
                           + "\tMobility: " + mobility);
        return;
    } // End of displayInInventory()
    
    //--------------------------------------------------------------------------
    // Use the artifact
    public boolean use(Character c, Place p) {
        if (keyID != 0) {
            if (p.useKey(keyID, c.getID()) == false) {
                return false;
            }
            return true;
        }
        else {
            return false;
        }
    } // End of use()
    
    //--------------------------------------------------------------------------
    // Prints debug information
    public void print() {
        System.out.println("=== ARTIFACT " + id + " ===");
        System.out.println("Name:     " + name);
        System.out.println("Desc:     " + description);
        System.out.println("Value:    " + value);
        System.out.println("Mobility: " + mobility);
        System.out.println("Key ID:   " + keyID);
        System.out.println("============================");
        return;
    } // End of print()

} // End of Artifact class
