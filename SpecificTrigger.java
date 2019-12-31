/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 4
 */

import java.util.ArrayList;

//-----------------//
// SpecificTrigger //
//-----------------//
// This trigger is set off when a specific character or artifact is present
// (only considers artifacts in a character's inventory, ignores artifacts
// on the ground)
public class SpecificTrigger implements TrapTrigger {
    private int id; // Positive for artifact ID and negative for character ID
    
    //--------------------------------------------------------------------------
    // Constructor
    public SpecificTrigger(int _id) {
        id = _id;
    } // End of constructor
    
    //--------------------------------------------------------------------------
    // Checks whether the trap's trigger condition is met
    public boolean isTriggered(Place p) {
        ArrayList<Integer> characterList = p.listCharacters();
        ArrayList<Integer> artifactList = p.listArtifactIDs();
        
        if (id < 0) { // Trigger by a specific character
            for (int i : characterList) {
                if (i == id) {
                    return true;
                }
            }
        }
        else { // Triggered by a specific artifact
            // Check the artifacts in each character's inventory
            for (int i : characterList) {
                ArrayList<Integer> inventory = Character.getCharacterByID(i)
                                                        .listArtifactIDs();
                for (int j : inventory) {
                    if (j == id) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}

