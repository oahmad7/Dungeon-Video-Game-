/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 4
 */

import java.util.ArrayList;

//-----------------//
// MobilityTrigger //
//-----------------//
// This trigger is set off when the total mobility in the room is greater than
// the threshold.
public class MobilityTrigger implements TrapTrigger {
    private int threshold;
    
    //--------------------------------------------------------------------------
    // Constructor
    public MobilityTrigger(int _threshold) {
        threshold = _threshold;
    } // End of constructor
    
    //--------------------------------------------------------------------------
    // Checks whether the trap's trigger condition is met
    public boolean isTriggered(Place p) {
        ArrayList<Integer> characterList = p.listCharacters();
        ArrayList<Integer> artifactList = p.listArtifactIDs();
        int total = 0;
        
        // Add mobility from characters
        for (int i : characterList) {
            total += Character.getCharacterByID(i).getMobility();
        }
        
        // Add mobility from loose artifacts
        total += p.getArtifactMobility();
        
        if (total < threshold) {
            return false;
        }
        
        return true;
    }
}

