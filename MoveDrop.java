/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

import java.util.ArrayList;

//--------------------------------------------------------------------------
// DROP
public class MoveDrop implements Move {
    private Character c;
    private Place p;
    private String argument;
    
    public MoveDrop(Character _c, Place _p, String arg) {
        c = _c;
        p = _p;
        argument = arg;
    }
    public boolean execute() {
        if (argument.equalsIgnoreCase("")) {
            return false;
        }
        if (argument.equalsIgnoreCase("all")) {
            ArrayList<String> artifactList = c.listArtifacts();
            if (artifactList.size() < 1) { // Inventory empty
                Character.messagePrint(c.getName()
                                       + " isn't carrying anything.", c);
            }
            
            // Drop everything
            for (String str : artifactList) {
                if (str.equalsIgnoreCase("all")) {
                    continue;
                }
                MoveDrop m = new MoveDrop(c, p, str);
                m.execute();
            }
            return true;
        }
        
        Artifact a = c.removeArtifact(argument);
        if (a == null) { // We don't have it so we can't drop it
            Character.messagePrint(c.getName() + " tried to drop a "
                                   + argument + ", but couldn't find one.",
                                   c);
        }
        else { // Drop it
            p.addArtifact(a);
            Character.messagePrint(c.getName() + " dropped the "
                                   + a.getName() + ".", c);
        }
        return true;
    }
}
