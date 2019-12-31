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

//============//
// Trap Class //
//============//
public class Trap extends Place {
    private int keyID;
    private int owner;
    private boolean armed;
    private boolean reset;
    private TrapTrigger trigger;
    private TrapEffect effect;
    
    //--------------------------------------------------------------------------
    // Constructor
    public Trap(Scanner s, int version) {
        super(s, version);
        armed = true;
        owner = 0;
        
        // Read attributes
        String line = MyScanner.getCleanLine(s);
        String words[] = line.split("[ \t]+", 6);
        if (words[0].equalsIgnoreCase("0")) {
            reset = false;
        }
        else {
            reset = true;
        }
        keyID = Integer.parseInt(words[1]);
        int triggerType = Integer.parseInt(words[2]);
        int triggerValue = Integer.parseInt(words[3]);
        int effectType = Integer.parseInt(words[4]);
        int effectValue = Integer.parseInt(words[5]);
        
        switch (triggerType) {
            case 0:
                trigger = new ArtifactTrigger(triggerValue);
                break;
            case 1:
                trigger = new CharacterTrigger(triggerValue);
                break;
            case 2:
                trigger = new SpecificTrigger(triggerValue);
                break;
            case 3:
            default:
                trigger = new MobilityTrigger(triggerValue);
        }
        
        switch (effectType) {
            case 0:
                effect = new Pitfall(effectValue);
                break;
            case 1:
                effect = new SpikeTrap(effectValue);
                break;
            case 2:
            default:
                effect = new TeleportTrap();
        }
        
        return;
    } // End of constructor
    
    //--------------------------------------------------------------------------
    // Tries to unlock directions in this place
    public boolean useKey(int key, int characterID) {
        boolean success = super.useKey(key, characterID);
        Character c = Character.getCharacterByID(characterID);
        
        // Arm/disarm the trap
        if (keyID == key) {
            armed = !armed;
            if (armed == true) {
                Character.messagePrint("The trap was armed.", c);
            }
            else {
                Character.messagePrint("The trap was disarmed.", c);
            }
            owner = characterID;
            return true;
        }
        return success;
    } // End of useKey()
    
    //--------------------------------------------------------------------------
    // Decides whether or not to trigger the trap
    public void updatePlace(Character c) {
        if (!armed) return;
        
        if (trigger.isTriggered(this)) {
            Character.messagePrint("-- A trap has been triggered in the "
                               + getName() + " --", c);
            effect.applyEffect(this, owner, c);
            if (!reset) {
                armed = false;
            }
        }
        
    } // End of updatePlace()
    
} // End of Place class
