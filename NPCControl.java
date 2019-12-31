/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

/*
 * Edited by Martin
 * Edits include: Determine what order (based on type of NPC) the actions would be prioritized in. 
 * Ex: Helpful NPC would drop an artifact to help the player
 */ 

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class NPCControl implements DecisionMaker {
    
    //--------------------------------------------------------------------------
    // Constructor
    public NPCControl() { } // End of constructor
    
    //--------------------------------------------------------------------------
    // Get move: randomly chooses an action and arguments from valid options
    public Move getMove(Character c, Place p, IO io) {
        //Random r = new Random();
        
        // Get lists of available directions and artifacts
        ArrayList<String> dirList = p.listDirections();
        ArrayList<String> artifactList = p.listArtifacts();
        ArrayList<String> inventoryList = c.listArtifacts();
        
        // Remove exits from the list of valid directions
        ArrayList<String> toRemove = new ArrayList<String>();
        for (String str : dirList) {
            if (p.followDirection(str) == Place.getPlaceByID(1)) {
                toRemove.add(str);
            }
        }
        
        // This extra list is needed because the program crashes if
        // dirList list is modified during the previous loop
        for (String str : toRemove) {
            dirList.remove(str);
        }
        
        // Create a list specific to each NPC to represent the
        // order in which to try each action
        ArrayList<Integer> actionList = new ArrayList<Integer>();
        if(c instanceof FriendlyNPC || c instanceof HelpfulNPC)
        {
        actionList.add(3); //try to drop an item to help the player first
        actionList.add(1);
        actionList.add(0);
        actionList.add(2);
        }
        if(c instanceof MischievousNPC)
        {
        actionList.add(1); //Try to grab an item first
        actionList.add(0);
        actionList.add(2);
        actionList.add(3);
        }
        if(c instanceof AggressiveNPC || c instanceof ChaoticNPC)
        {
        actionList.add(2); //try to use an item to negatively impact the player first
        actionList.add(1);
        actionList.add(0);
        actionList.add(3);
        }
        
        // Try each action until we find a valid move
        for (int i = 0; i < 4; i++) {
            if (actionList.get(i) == 0) {
                // Try to go somewhere
                if (dirList.size() < 1) {
                    continue; // Nowhere to go
                }
                // Randomize directions
                Collections.shuffle(dirList);
                // Go in the first direction
                return new MoveGo(c, p, dirList.get(0));
            }
            else if (actionList.get(i) == 1) {
                // Try to get an artifact
                if (artifactList.size() < 1) {
                    continue; // No artifacts
                }
                // Randomize artifacts
                Collections.shuffle(artifactList);
                // Get the first artifact
                return new MoveGet(c, p, artifactList.get(0));
            }
            else if (actionList.get(i) == 2) {
                // Try to use an artifact
                if (inventoryList.size() < 1) {
                    continue; // No artifacts
                }
                // Randomize inventory
                Collections.shuffle(inventoryList);
                // Use the first artifact
                return new MoveUse(c, p, inventoryList.get(0));
            }
            else if (actionList.get(i) == 3) {
                // Try to drop an artifact
                if (inventoryList.size() < 1) {
                    continue; // No artifacts
                }
                // Randomize inventory
                Collections.shuffle(inventoryList);
                // Drop the first artifact
                return new MoveDrop(c, p, inventoryList.get(0));
            }
        }
        
        // At this point there are no valid moves
        return new MoveGo(c, p, "to space");
        
    } // End of getMove
}
