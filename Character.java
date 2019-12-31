/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Character {
    private int id;
    private String name;
    private String description;
    private int carryWeight;
    private int maxCarryWeight;
    private Place currentPlace;
    private TreeMap<String, Artifact> inventory;
    private static TreeMap<Integer, Character> allCharacters;
    private DecisionMaker controller;
    private int health;
    
    private IO io;
    
    //--------------------------------------------------------------------------
    // Constructor 1
    public Character(Scanner fileScanner, int _version,
                     int placeID, Scanner keyScanner) {
        
        // Initialize collections
        inventory = new TreeMap<String, Artifact>();
        maxCarryWeight = 20;
        carryWeight = 0;
        health = 100;
        if (allCharacters == null) {
            allCharacters = new TreeMap<Integer, Character>();
        }
        
        // Get attributes
        String words[] = MyScanner.getCleanLine(fileScanner).split("[ \t]+", 2);
        id = Integer.parseInt(words[0]);
        name = words[1];
        
        // Get description
        description = "";
        int ndescr = Integer.parseInt(MyScanner.getCleanLine(fileScanner));
        for (int i = 0; i < ndescr; i++) {
            description += MyScanner.getCleanLine(fileScanner) + "\n";
        }
        
        // Check ID uniqueness and place in collections
        if (allCharacters.get(id) == null) {
            allCharacters.put(id, this);
        }
        else {
            System.out.println("Duplicate character ID " + id);
        }
        
        if (placeID == 0) {
            currentPlace = Place.getRandomPlace();
        }
        else {
            currentPlace = Place.getPlaceByID(placeID);
        }
        currentPlace.addCharacter(id, this);
        
        // Set DecisionMaker
        if ((this instanceof Player) == true) {
            controller = new PlayerControl(keyScanner);
            io = new IOPlayer(name);
        }
        else {
            controller = new NPCControl();
            io = new IONPC(name);
        }
    } // End of constructor 1
    
    //--------------------------------------------------------------------------
    // Constructor 2
    public Character(String _name, String _desc, Place _p, Scanner keyScanner) {
        name = _name;
        description = _desc;
        
        // Default to place 2
        currentPlace = _p;
        
        // Initalize collections
        inventory = new TreeMap<String, Artifact>();
        maxCarryWeight = 20;
        carryWeight = 0;
        health = 100;
        if (allCharacters == null) {
            allCharacters = new TreeMap<Integer, Character>();
        }
        
        // Set ID and add to collections
        id = 1;
        while (allCharacters.get(id) != null) {
            id++;
        }
        allCharacters.put(id, this);
        _p.addCharacter(id, this);
        
        // Set DecisionMaker
        if ((this instanceof Player) == true) {
            controller = new PlayerControl(keyScanner);
            io = new IOPlayer(name);
        }
        else {
            controller = new NPCControl();
            io = new IONPC(name);
        }
    } // End of constructor 2
    
    //--------------------------------------------------------------------------
    // Returns the character's ID
    public int getID() {
        return id;
    } // End of getID()
    
    //--------------------------------------------------------------------------
    // Returns the character with the given ID
    public static Character getCharacterByID(int _id) {
        return allCharacters.get(_id);
    } // End of getCharacterByID()
    
    //--------------------------------------------------------------------------
    // Returns the character's name
    public String getName() {
        return name;
    } // End of getName()
    
    //--------------------------------------------------------------------------
    // Returns the character's current Place
    public Place getCurrentPlace() {
        return currentPlace;
    } // End of getCurrentPlace()
    
    //--------------------------------------------------------------------------
    // Returns a list of artifacts in the character's inventory
    public ArrayList<String> listArtifacts() {
        ArrayList<String> artifactList = new ArrayList<String>();
        for (Map.Entry<String, Artifact> x : inventory.entrySet()) {
            artifactList.add(x.getValue().getName());
        }
        return artifactList;
    } // End of listArtifacts()
    
    //--------------------------------------------------------------------------
    // Returns a list of artifact IDs in the character's inventory
    public ArrayList<Integer> listArtifactIDs() {
        ArrayList<Integer> artifactList = new ArrayList<Integer>();
        for (Map.Entry<String, Artifact> x : inventory.entrySet()) {
            artifactList.add(x.getValue().getID());
        }
        return artifactList;
    } // End of listArtifactIDs()
    
    //--------------------------------------------------------------------------
    // Follow direction
    public void go(String _dir) {
        
        messagePrint(name + " went " + _dir + ".");
        currentPlace.removeCharacter(id);
        currentPlace = currentPlace.followDirection(_dir);
        currentPlace.addCharacter(id, this);
        if (currentPlace == Place.getPlaceByID(1) && this instanceof Player) {
            io.display(name + " exited.");
            Game.quitGame();
            return;
        }
        messagePrint(name + " is in the " + currentPlace.getName() + ".");
    } // End of getCurrentPlace()
    
    //--------------------------------------------------------------------------
    // This character makes a move
    public void makeMove() {
        io.setInventory(this.listArtifacts(), carryWeight, maxCarryWeight);
        io.setEnabled(true);
        currentPlace.display(this);
        
        boolean moveComplete = false;
        while (!moveComplete) {
            Move m = controller.getMove(this, currentPlace, io);
            
            if (m.execute()) {
                moveComplete = true;
            }
            if (!(m instanceof MoveNull)) {
                io.setInventory(this.listArtifacts(),
                                carryWeight, maxCarryWeight);
            }
        }
        currentPlace.updatePlace();
        currentPlace.updatePlace(this);
        
        if (this instanceof Player) {
            io.setEnabled(false);
        }
        
    } // End of makeMove()
    
    //--------------------------------------------------------------------------
    // Show inventory
    public void displayInventory() {
        io.display("* Current Inventory (" + carryWeight
                           + "/" + maxCarryWeight + ") *");
        
        if (inventory.size() < 1) {
            io.display("--Empty--");
        }
        for (Map.Entry<String, Artifact> x : inventory.entrySet()) {
            x.getValue().displayInInventory();
        }
        System.out.print("\n");
        return;
    } // End of displayInventory()
    
    //--------------------------------------------------------------------------
    // Adds an artifact to the inventory
    public void addArtifact(Artifact a) {
        if (a == null) return;
        if (inventory.get(a.getName().toLowerCase()) == null) {
            inventory.put(a.getName().toLowerCase(), a);
            carryWeight += a.getMobility();
        }
        return;
    } // End of addArtifact()
    
    //--------------------------------------------------------------------------
    // Removes and returns an artifact from the inventory
    public Artifact removeArtifact(String str) {
        Artifact a = inventory.remove(str.toLowerCase());
        if (a == null) return null;
        carryWeight -= a.getMobility();
        return a;
    } // End of removeArtifact()
    
    //--------------------------------------------------------------------------
    // Returns how much more this character can carry
    public int getRemainingMobility() {
        return maxCarryWeight - carryWeight;
    } // End of getRemainingMobility()
    
    //--------------------------------------------------------------------------
    // Get total mobility
    public int getMobility() {
        return carryWeight;
    }
    
    //--------------------------------------------------------------------------
    // Set current place
    public void setCurrentPlace(Place p) {
        currentPlace = p;
    } // End of setCurrentPlace()
    
    //--------------------------------------------------------------------------
    // Debug print
    public void print() {
        System.out.println("=== CHARACTER " + id + " ===");
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Artifacts:");
        for (Map.Entry<String, Artifact> x : inventory.entrySet()) {
            System.out.println(x.getValue().getName());
        }
        System.out.println("====================\n");
    } // End of print()
    
    //--------------------------------------------------------------------------
    // Display during play
    public void display() {
        io.display(description);
    } // End of display()
    
    //--------------------------------------------------------------------------
    // Prints a message about character actions
    public static void messagePrint(String str, Character _c) {
        _c.io.display(str);
    } // End of printMessage()
    
    //--------------------------------------------------------------------------
    // Prints a message about character actions
    private void messagePrint(String str) {
        io.display(str);
    } // End of printMessage()
 
    //--------------------------------------------------------------------------
    // Returns the character's current health
    public int getHealth(){
        return health;
    } // End of getHealth()
 
    //--------------------------------------------------------------------------
    // Set the character's new health
    public void setHealth(int _health){
        this.health = _health;
    } // End of setHealth()
}
