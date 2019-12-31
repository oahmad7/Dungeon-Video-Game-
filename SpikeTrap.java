/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 4
 */

import java.util.ArrayList;

//=================//
// SpikeTrap Class //
//=================//
public class SpikeTrap implements TrapEffect {
    private int damage;
    
    //--------------------------------------------------------------------------
    public SpikeTrap(int _damage) {
        damage = _damage;
    } // End of constructor
    
    //--------------------------------------------------------------------------
    public void applyEffect(Place p, int owner, Character _c) {
        ArrayList<Integer> characters = p.listCharacters();
        
        // Send characters to the new location
        for (int i : characters) {
            if (i == owner) continue; // don't hurt the trap's owner
            
            Character c = p.removeCharacter(i);
            if (c == null) {
                System.out.println("* * * c is null * * *");
                continue;
            }
            p.addCharacter(i, c);
            // TODO
            Character.messagePrint("-- " + c.getName()
                                   + " was impaled on a spike --", c);
            if (!c.equals(_c)) {
                Character.messagePrint("-- " + c.getName()
                                       + " was impaled on a spike --", _c);
            }
        }
    } // End of applyEffect()
}

