/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

//--------------------------------------------------------------------------
// USE
public class MoveUse implements Move {
    private Character c;
    private Place p;
    private String argument;
    
    public MoveUse(Character _c, Place _p, String arg) {
        c = _c;
        p = _p;
        argument = arg;
    }
    public boolean execute() {
        if (argument.equalsIgnoreCase("")) {
            return false;
        }
        Artifact a = c.removeArtifact(argument);
        if (a == null) { // We don't have the artifact
            Character.messagePrint(c.getName() + " tried to use a "
                                   + argument + ", but couldn't find one.",
                                   c);
        }
        else { // Use the artifact
            Character.messagePrint(c.getName() + " used the "
                                   + a.getName() + ".", c);
            
            if (a.use(c, p) == false) {
                Character.messagePrint("The " + a.getName()
                                       + " didn't do anything.", c);
            }
        }
        c.addArtifact(a); // Put it back in the inventory
        return true;
    }
}

