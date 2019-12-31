/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

import java.util.Scanner;

//=================//
// Direction Class //
//=================//
public class Direction {
    
    private int id;
    private int lockID;
    private Place to;
    private Place from;
    private DirType dir;
    private boolean locked;
    
    //-----------------------//
    // Enumerated Directions //
    //-----------------------//
    private enum DirType {
        N("n", "north"),
        S("s", "south"),
        E("e", "east"),
        W("w", "west"),
        
        NE("ne", "northeast"),
        NW("nw", "northwest"),
        SE("se", "southeast"),
        SW("sw", "southwest"),
        
        NNE("nne", "north-northeast"),
        ENE("ene", "east-northeast"),
        ESE("ese", "east-southeast"),
        SSE("sse", "south-southeast"),
        SSW("ssw", "south-southwest"),
        WSW("wsw", "west-southwest"),
        WNW("wnw", "west-northwest"),
        NNW("nnw", "north-northwest"),
        
        U("u", "up"),
        D("d", "down");
        
        private String name, abbrev;
        
        DirType(String _abbrev, String _name) {
            name = _name;
            abbrev = _abbrev;
        }
        
        public boolean equals(String s) {
            if (name.equalsIgnoreCase(s))
                return true;
            if (abbrev.equalsIgnoreCase(s))
                return true;
            return false;
        }
    } // End of enumerated directions
    
    //--------------------------------------------------------------------------
    // Constructor
    public Direction(Scanner s, int version) {
        String line = MyScanner.getCleanLine(s);
        String words[] = line.split("[ \t]+");
        
        // Initialize fields
        id = Integer.parseInt(words[0]);
        from = Place.getPlaceByID(Integer.parseInt(words[1]));
        dir = DirType.valueOf(words[2].toUpperCase());
        int destID = Integer.parseInt(words[3]);
        lockID = Integer.parseInt(words[4]);
        
        // Set lock
        if (destID < 1) {
            locked = true;
            destID = -destID;
        }
        else {
            locked = false;
        }
        
        // Add to source place
        to = Place.getPlaceByID(destID);
        if (to == null) {
            System.out.println("Warning: direction " + id
                               + "'s destination is null.");
            
            to = Place.getPlaceByID(0);
        }
        
        from.addDirection(this);
        return;
    } // End of constructor
    
    //--------------------------------------------------------------------------
    // Returns true if the string matches this direction, otherwise false
    public boolean match(String s) {
        if (dir.equals(s.toLowerCase()))
            return true;
        return false;
    } // End of match()
    
    //--------------------------------------------------------------------------
    // Returns the direction's name
    public String getName() {
        return dir.name();
    } // End of getName()
    
    //--------------------------------------------------------------------------
    // Locks this direction
    public void lock() {
        locked = true;
        return;
    } // End of lock()
    
    //--------------------------------------------------------------------------
    // Unlocks this direction
    public void unlock() {
        locked = false;
        return;
    } // End of unlock()
    
    //--------------------------------------------------------------------------
    // Returns true if this direction is locked, false if unlocked
    public boolean isLocked() {
        return locked;
    } // End of isLocked()
    
    //--------------------------------------------------------------------------
    // Tries to unlock this direction with the given artifact key value
    public boolean useKey(int key, Character c) {
        if (lockID == 0) {
            return false;
        }
        if (key == lockID || key < 0) {
            if (locked == false) {
                Character.messagePrint("Direction " + dir.name()
                                   + " is already unlocked.", c);
                return false;
            }
            Character.messagePrint("Direction " + dir.name()
                               + " has been unlocked.", c);
            locked = false;
            return true;
        }
        return false;
    } // End of unlock()
    
    //--------------------------------------------------------------------------
    // If this direction can be followed, returns the place it leads to,
    // otherwise returns the place it came from
    public Place follow() {
        if (!locked)
            return to;
        return from;
    } // End of follow()
    
    //--------------------------------------------------------------------------
    // Prints debug information
    public void print() {
        System.out.println("=== DIRECTION OBJECT INFO ===");
        System.out.println(" * ID:     " + id);
        System.out.println(" * To:     " + to.getName());
        System.out.println(" * From:   " + from.getName());
        System.out.println(" * Name:   " + dir.name());
        System.out.println(" * Lock:   " + lockID);
        System.out.println("=============================");
        return;
    } // End of print()

} // End of Direction class
