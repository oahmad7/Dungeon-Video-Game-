/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 4
 */

import java.util.ArrayList;
import java.util.Collections;

//====================//
// TeleportTrap Class //
//====================//
public class TeleportTrap implements TrapEffect {
    
    //--------------------------------------------------------------------------
    public TeleportTrap() {
    } // End of constructor
    
    //--------------------------------------------------------------------------
    public void applyEffect(Place p, int owner, Character _c) {
        Place destination = Place.getRandomPlace();
        
        ArrayList<Integer> characters = p.listCharacters();
        Collections.shuffle(characters);
        
        // Remove one character and send them to a random place
        if (characters.size() > 0) {
            int id = characters.get(0);
            Character c = p.removeCharacter(id);
            destination.addCharacter(id, c);
            
            Character.messagePrint("-- " + c.getName()
                                   + " was teleported to the "
                                   + destination.getName() + " --", c);
            
            if (!c.equals(_c)) {
                Character.messagePrint("-- " + c.getName()
                                       + " was teleported to the "
                                       + destination.getName() + " --", _c);
            }
            
            destination.addCharacter(c.getID(), c);
            c.setCurrentPlace(destination);
        }
        
    } // End of applyEffect()
}

