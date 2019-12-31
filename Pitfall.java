/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 4
 */

import java.util.ArrayList;

//===============//
// Pitfall Class //
//===============//
public class Pitfall implements TrapEffect {
    private int destID;
    
    //--------------------------------------------------------------------------
    public Pitfall(int _destID) {
        destID = _destID;
    } // End of constructor
    
    //--------------------------------------------------------------------------
    public void applyEffect(Place p, int owner, Character _c) {
        Place destination = Place.getPlaceByID(destID);
        
        ArrayList<Integer> characters = p.listCharacters();
        ArrayList<String> artifacts = p.listArtifacts();
        
        // Send characters to the new location
        for (int i : characters) {
            Character c = p.removeCharacter(i);
            if (c == null) {
                System.out.println("* * * c is null * * *");
                continue;
            }
            destination.addCharacter(i, c);
            Character.messagePrint("-- " + c.getName() + " has fallen into the "
                               + destination.getName() + " --", c);
            
            if (!c.equals(_c)) {
                Character.messagePrint("-- " + c.getName()
                                       + " has fallen into the "
                                       + destination.getName() + " --", c);
            }
            
            c.setCurrentPlace(destination);
        }
        
        // Send artifacts to the new location
        for (String s : artifacts) {
            Artifact a = p.removeArtifact(s);
            if (a == null) {
                System.out.println("* * * a is null * * *");
                continue;
            }
            destination.addArtifact(a);
            Character.messagePrint("-- The " + a.getName()
                                   + " has fallen into the "
                                   + destination.getName() + " --", _c);
        }
    } // End of applyEffect()
}

