/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 4
 */

import java.util.ArrayList;

//------------------//
// CharacterTrigger //
//------------------//
public class CharacterTrigger implements TrapTrigger {
    private int threshold;
    
    //--------------------------------------------------------------------------
    // Constructor
    public CharacterTrigger(int _threshold) {
        threshold = _threshold;
    } // End of constructor
    
    //--------------------------------------------------------------------------
    // Checks whether the trap's trigger condition is met
    public boolean isTriggered(Place p) {
        ArrayList<Integer> characterList = p.listCharacters();
        if (characterList.size() < threshold) {
            return false;
        }
        return true;
    }
}

