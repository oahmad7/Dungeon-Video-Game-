/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

/*
 * Edited by Martin to just get the name of the character for the message to player purpose
 */

import java.util.Scanner;

public class NPC extends Character {
    public NPC(Scanner f, int _version, int placeID, Scanner k) {
        super(f, _version, placeID, k);
    }
    
    // Returns the character's name
    public String getName() {
        return super.getName();
    } 
  
    // Prints a specific message to a player
    private void npcToPlayerMessage() {
        System.out.println("Message from NPC (" + getName() + "): ");
        System.out.println("Hello.");
    } 
    
}
