/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

import java.util.ArrayList;

//--------------------------------------------------------------------------
// GO
public class MoveGo implements Move {
    private Character c;
    private Place p;
    private String argument;
    
    public MoveGo(Character _c, Place _p, String arg) {
        c = _c;
        p = _p;
        argument = arg;
    }
    public boolean execute() {
        if (argument.equalsIgnoreCase("")) {
            return false;
        }
        Direction d = p.hasDirection(argument);
        if (d == null) { // That direction doesn't exist
            Character.messagePrint(c.getName() + " can't go "
                                   + argument + ".", c);
            return true;
        }
        else if (d.isLocked()) { // That direction is locked
            Character.messagePrint(c.getName() + " tried to go "
                                   + argument + ", but it was locked.", c);
            return true;
        }
        else { // Go in that direction
            c.go(argument);
            return true;
        }
    }
}
