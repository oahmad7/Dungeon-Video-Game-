/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 4
 */

import java.util.ArrayList;

//-----------------//
// ArtifactTrigger //
//-----------------//
public class ArtifactTrigger implements TrapTrigger {
    private int threshold;
    
    //--------------------------------------------------------------------------
    // Constructor
    public ArtifactTrigger(int _threshold) {
        threshold = _threshold;
    } // End of constructor
    
    //--------------------------------------------------------------------------
    // Checks whether the trap's trigger condition is met
    public boolean isTriggered(Place p) {
        ArrayList<String> artifactList = p.listArtifacts();
        if (artifactList.size() < threshold) {
            return false;
        }
        return true;
    }
}

