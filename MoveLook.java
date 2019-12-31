/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

//--------------------------------------------------------------------------
// LOOK
public class MoveLook implements Move {
    private Character c;
    private Place p;
    
    public MoveLook(Character _c, Place _p) {
        c = _c;
        p = _p;
    }
    public boolean execute() {
        if (p != null) {
            p.display(c); // Display place p to character c
        }
        return false;
    }
}
