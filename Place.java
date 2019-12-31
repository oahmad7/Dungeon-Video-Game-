/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

//=============//
// Place Class //
//=============//
public class Place {
    private int id;
    private String name;
    private String desc;
    private List<Direction> directions;
    private TreeMap<String, Artifact> artifacts;
    private TreeMap<Integer, Character> characters;
    private static TreeMap<Integer, Place> allPlaces;
    
    //--------------------------------------------------------------------------
    // Constructor
    public Place(int _id, String _name, String _desc) {
        if (allPlaces == null) {
            allPlaces = new TreeMap<Integer, Place>();
        }
        
        characters = new TreeMap<Integer, Character>();
        directions = new ArrayList<Direction>();
        artifacts = new TreeMap<String, Artifact>();
        
        id = _id;
        name = _name;
        desc = _desc;
        
        if (allPlaces.get(id) != null) {
            System.out.println("DUPLICATE OF PLACE #" + id);
        }
        
        allPlaces.put(id, this);
        return;
    } // End of constructor
    
    //--------------------------------------------------------------------------
    // Constructor
    public Place(Scanner s, int version) {
        
        if (allPlaces == null) {
            allPlaces = new TreeMap<Integer, Place>();
        }
        
        // Create collections
        characters = new TreeMap<Integer, Character>();
        directions = new ArrayList<Direction>();
        artifacts = new TreeMap<String, Artifact>();
        
        // Read attributes
        String line = MyScanner.getCleanLine(s);
        String words[] = line.split("[ \t]+", 2);
        id = Integer.parseInt(words[0]);
        name = words[1];
        
        // Read description lines
        line = MyScanner.getCleanLine(s);
        int descLines = Integer.parseInt(line); //
        desc = "";
        for (int j = 0; j < descLines; j++) {
            line = MyScanner.getCleanLine(s);
            desc += line + "\n";
        }
        
        // Add to collection
        if (allPlaces.get(id) != null) {
            System.out.println("DUPLICATE OF PLACE #" + id);
        }
        allPlaces.put(id, this);
        return;
    } // End of constructor
    
    //--------------------------------------------------------------------------
    // Returns the place with the given ID
    public static Place getPlaceByID(int _id) {
        return allPlaces.get(_id);
    } // End of getPlaceByID()
    
    //--------------------------------------------------------------------------
    // Returns a random place (cannot be ID 0 or 1)
    public static Place getRandomPlace() {
        Random r = new Random();
        int randID = r.nextInt(allPlaces.size() - 2) + 2;
        int i = 0;
        Place p = null;
        for (Map.Entry<Integer, Place> x : allPlaces.entrySet()) {
            if (i >= randID) {
                p = x.getValue();
                break;
            }
            i++;
        }
        return p;
    } // End of getRandomPlace()
    
    //--------------------------------------------------------------------------
    // Returns the place name
    public String getName() {
        return name;
    } // End of getName()
    
    //--------------------------------------------------------------------------
    // Returns the place description
    private String description() {
        if (artifacts.size() < 1) {
            return desc;
        }
        
        // Build a string to describe the artifacts in the room
        String artifactString;
        artifactString = "\nYou see the following artifacts in the room: ";
        // Get the name of the last artifact so we know when we're done.
        String lastArtifactName = artifacts.lastEntry().getValue().getName();
        
        for (Map.Entry<String, Artifact> x : artifacts.entrySet()) {
            String artifactName = x.getValue().getName();
            
            //artifactString += "\n";
            
            // 'and' before the last artifact
            if (artifactName.equals(lastArtifactName) && artifacts.size() > 1) {
                artifactString += "and ";
            }
            
            // Correct use of 'a' vs 'an'
            if ("aeiouAEIOU".indexOf(artifactName.charAt(0)) >= 0) {
                artifactString += "an ";
            }
            else {
                artifactString += "a ";
            }
            
            artifactString += x.getValue().getProperties();
            
            // End with a period instead of a comma
            if (artifactName.equals(lastArtifactName)) {
                artifactString += ".";
            }
            else {
                artifactString += ", ";
            }
            
        }
        return desc + artifactString;

    } // End of description()
    
    //--------------------//
    // Add/Remove Methods //
    //--------------------------------------------------------------------------
    // Adds a direction to the place
    public void addDirection(Direction _dir) {
        directions.add(_dir);
        return;
    } // End of addDirection()
    
    //--------------------------------------------------------------------------
    // Adds an artifact to the place
    public void addArtifact(Artifact a) {
        artifacts.put(a.getName().toLowerCase(), a);
        return;
    } // End of addArtifact()
    
    //--------------------------------------------------------------------------
    // Removes and returns an artifact from the place
    public Artifact removeArtifact(String str) {
        return artifacts.remove(str.toLowerCase());
    } // End of removeArtifact()
    
    //--------------------------------------------------------------------------
    // Adds a character to the place
    public void addCharacter(int _id, Character c) {
        //c.setCurrentPlace(this);
        characters.put(_id, c);
        return;
    } // End of addCharacter()
    
    //--------------------------------------------------------------------------
    // Removes and returns a character from the place
    public Character removeCharacter(int _id) {
        return characters.remove(_id);
    } // End of removeCharacter()
    //---------------------------//
    // End of Add/Remove Methods //
    //---------------------------//
    
    //--------------------------------------------------------------------------
    // Returns a list of directions
    public ArrayList<String> listDirections() {
        ArrayList<String> dirList = new ArrayList<String>();
        for (Direction d : directions) {
            dirList.add(d.getName());
        }
        return dirList;
    } // End of listDirections()
    
    //--------------------------------------------------------------------------
    // Returns a list of artifacts
    public ArrayList<String> listArtifacts() {
        ArrayList<String> artifactList = new ArrayList<String>();
        for (Map.Entry<String, Artifact> x : artifacts.entrySet()) {
            artifactList.add(x.getValue().getName());
        }
        return artifactList;
    } // End of listArtifacts()
    
    //--------------------------------------------------------------------------
    // Returns a list of artifact IDs
    public ArrayList<Integer> listArtifactIDs() {
        ArrayList<Integer> artifactList = new ArrayList<Integer>();
        for (Map.Entry<String, Artifact> x : artifacts.entrySet()) {
            artifactList.add(x.getValue().getID());
        }
        return artifactList;
    } // End of listArtifactIDs()
    
    //--------------------------------------------------------------------------
    // Returns total artifact mobility
    public int getArtifactMobility() {
        int total = 0;
        for (Map.Entry<String, Artifact> x : artifacts.entrySet()) {
            total += x.getValue().getMobility();
        }
        return total;
    } // End of getArtifactMobility()
    
    //--------------------------------------------------------------------------
    // Returns a list of character IDs
    public ArrayList<Integer> listCharacters() {
        ArrayList<Integer> characterList = new ArrayList<Integer>();
        for (Map.Entry<Integer, Character> x : characters.entrySet()) {
            characterList.add(x.getKey());
        }
        return characterList;
    } // End of listCharacters()
    
    //--------------------------------------------------------------------------
    // Checks if there is a player in the room
    public boolean hasPlayer() {
        for (Map.Entry<Integer, Character> x : characters.entrySet()) {
            if (x.getValue() instanceof Player)
                return true;
        }
        return false;
    } // End of hasPlayer()
    
    //--------------------------------------------------------------------------
    // Checks if the direction exists and returns it
    public Direction hasDirection(String _dir) {
        for (Direction d : directions) {
            if (d.match(_dir)) {
                return d;
            }
        }
        return null;
    } // End of hasDirection()
    
    //--------------------------------------------------------------------------
    // Checks if the direction is locked
    public boolean directionIsLocked(String _dir) {
        for (Direction d : directions) {
            if (d.match(_dir)) {
                return d.isLocked();
            }
        }
        return true;
    } // End of directionIsLocked()
    
    //--------------------------------------------------------------------------
    // Tries to follow the specified direction and returns the place it leads to
    public Place followDirection(String _dir) {
        // Search the list of directions for a match
        Place p = this;
        for (Direction d : directions) {
            if (d.match(_dir)) {
                p = d.follow();
                return p;
            }
        }
        return p;
    } // End of followDirection()
    
    //--------------------------------------------------------------------------
    // Tries to unlock directions in this place
    public boolean useKey(int key, int characterID) {
        boolean success = false;
        Character c = Character.getCharacterByID(characterID);
        for (Direction d : directions) {
            if (d.useKey(key, c)) {
                success = true;
            }
        }
        return success;
    } // End of useKey()
    
    //--------------------------------------------------------------------------
    // To be overridden by child classes
    public void updatePlace() { }
    
    //--------------------------------------------------------------------------
    // To be overridden by child classes
    public void updatePlace(Character c) { }
    
    //--------------------------------------------------------------------------
    // Prints debug information
    public void print() {
        System.out.println("=== PLACE OBJECT INFO ===");
        System.out.println(" * ID:            " + id);
        System.out.println(" * Name:          " + name);
        System.out.println(" * Description:   " + desc);
        System.out.print(" * Directions:    [ ");
        for (Direction d : directions) {
            System.out.print("{" + d.getName() + "} ");
        }
        System.out.print("]\n");
        System.out.print(" * Artifacts:");
        for (Map.Entry<String, Artifact> x : artifacts.entrySet()) {
            System.out.println(x.getValue().getName());
        }
        System.out.println("=========================");
        return;
    } // End of print()
    
    //--------------------------------------------------------------------------
    // Displays the place during play
    public void display(Character c) {
        Character.messagePrint("\n* " + c.getName() + " is in the "
                               + getName() +". *", c);
        
        if (c instanceof Player) {
            Character.messagePrint(description(), c);
        }
    } // End of display()
    
} // End of Place class
