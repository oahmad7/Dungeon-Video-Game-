/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

//--------------------------------------------------------------------------
// INVENTORY
public class MoveInventory implements Move {
    private Character c;
    
    public MoveInventory(Character _c) {
        c = _c;
    }
    public boolean execute() {
        c.displayInventory(); // Show the player's inventory
        return false;
    }
}
