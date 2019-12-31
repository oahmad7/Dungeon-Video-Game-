/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

import java.util.ArrayList;

//--------------------------------------------------------------------------
// GET
public class MoveGet implements Move {
    private Character c;
    private Place p;
    private String argument;
    
    public MoveGet(Character _c, Place _p, String arg) {
        c = _c;
        p = _p;
        argument = arg;
    }
    public boolean execute() {
        if (argument.equalsIgnoreCase("")) {
            return false;
        }
        if (argument.equalsIgnoreCase("all")) {
            ArrayList<String> artifactList = p.listArtifacts();
            if (artifactList.size() < 1) { // Nothing in the room
                Character.messagePrint(c.getName()
                                       + " couldn't find anything.", c);
            }
            // Pick up everything
            for (String str : artifactList) {
                if (str.equalsIgnoreCase("all")) {
                    continue;
                }
                MoveGet m = new MoveGet(c, p, str);
                m.execute();
            }
            return true;
        }
        
        Artifact a = p.removeArtifact(argument);
        if (a == null) { // There is nothing to pick up
            Character.messagePrint(c.getName() + " can't find a "
                                   + argument + ".", c);
        }
        // It's too heavy
        else if (a.getMobility() < 0
                 || a.getMobility() > c.getRemainingMobility()) {
            
            p.addArtifact(a);
            Character.messagePrint(c.getName() + " can't carry the "
                                   + a.getName() + ".", c);
        }
        else { // Pick it up
            c.addArtifact(a);
            Character.messagePrint(c.getName() + " picked up the "
                                   + a.getName() + ".", c);
        }
        return true;
    }
}
