/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

/*
 * Edited by Martin
 * Edits include: Changing the way we assign the new character when the character is an NPC
 * Uses the DetermineNPC class to determine through keywords in description what type it will be
 * 
 */

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.Scanner;

//============//
// Game Class //
//============//
public class Game {
    
    private String name;
    private int version;
    private TreeMap<Integer, Character> allCharacters;
    private Place startPlace;
    private static boolean quitFlag;
    
    private Scanner keyScanner;
    private Scanner fileScanner;
    
    //----------------
    // PUBLIC METHODS
    //--------------------------------------------------------------------------
    // Constructor
    public Game(Scanner _file, Scanner _input) {
        allCharacters = new TreeMap<Integer, Character>();
        keyScanner = _input;
        fileScanner = _file;
        readFromFile();
        return;
    } // End of constructor
    
    //--------------------------------------------------------------------------
    // Prints debug information
    public void print() {
        System.out.println("======= GAME OBJECT INFO =======");
        System.out.println(" * Name:   " + name);
        System.out.print(" * Characters: \n");
        for (Map.Entry<Integer, Character> x : allCharacters.entrySet()) {
            x.getValue().print();
        }
        System.out.println("======= END OF GAME PRINT ========");
        return;
    } // End of print()
    
    //--------------------------------------------------------------------------
    // Runs game loop
    public void play() {
        
        quitFlag = true;  // This will change when a Player quits
        while (quitFlag) {
            for (Map.Entry<Integer, Character> x : allCharacters.entrySet()) {
                x.getValue().makeMove();
            }
        } // End of while loop
        
        return;
    } // End of play()
    
    //--------------------------------------------------------------------------
    // Quit the game
    public static void quitGame() {
        quitFlag = false;
        return;
    } // End of quitGame()
    
    //-----------------
    // PRIVATE METHODS
    //--------------------------------------------------------------------------
    // Reads the file and loads game data
    private void readFromFile() {
        
        // Read first line
        String firstLine[] = MyScanner.getCleanLine(fileScanner)
                                      .split("[ .\t]+", 4);
        name = firstLine[3];
        version = 10 * Integer.parseInt(firstLine[1])
                     + Integer.parseInt(firstLine[2]);
        
        // Read the number of places
        startPlace = null;
        int nPlaces = 0;
        String line;
        {
            line = MyScanner.getCleanLine(fileScanner);
            String words[] = line.split("[ \t]+", 2);
            nPlaces = Integer.parseInt(words[1]);
        }
        // Read all the places
        for (int i = 0; i < nPlaces; i++) {
            line = MyScanner.getCleanLine(fileScanner);
            int placeType = Integer.parseInt(line);
            Place p;
            switch(placeType) {
                case 0:
                default:
                    p = new Place(fileScanner, version);
                    break;
                case 1:
                    p = new Trap(fileScanner, version);
                    break;
                case 2:
                    p = new WindyPlace(fileScanner, version);
                    break;
                case 3:
                    p = new HauntedPlace(fileScanner, version);
                    break;
                case 4:
                    p = new WinPlace(fileScanner, version);
                    break;
            }
            if (i == 0) {
                startPlace = p;
            }
        }
        
        Place ex = new Place(1, "Exit", "Goodbye!");
        Place nowhere = new Place(0, "Nowhere", "How did you even get here?");
        
        // Read the number of directions
        int nDirections = 0;
        {
            line = MyScanner.getCleanLine(fileScanner);
            String words[] = line.split("[ \t]+");
            nDirections = Integer.parseInt(words[1]);
        }
        // Read all the directions
        for (int i = 0; i < nDirections; i++) {
            Direction d = new Direction(fileScanner, version);
        }
        
        // Read the number of characters
        int nCharacters = 0;
        if (version > 31) {
            line = MyScanner.getCleanLine(fileScanner);
            String words[] = line.split("[ \t]+", 2);
            nCharacters = Integer.parseInt(words[1]);
        }
        // Read all the characters
        for (int i = 0; i < nCharacters; i++) {
            String words[] = MyScanner.getCleanLine(fileScanner)
                                      .split("[ \t]+", 2);
            
            int placeID = Integer.parseInt(words[1]);
            Character c;
            if (words[0].equalsIgnoreCase("PLAYER")) {
                c = new Player(fileScanner, version, placeID, keyScanner);
            }
            else {
               DetermineNPC dnpc = new DetermineNPC("");
               int typeOfNPC = dnpc.getTypeOfNPC();
  
               if(typeOfNPC ==  0)
                  c = new FriendlyNPC(fileScanner, version, placeID, keyScanner);
               else if(typeOfNPC == 1)
                  c = new HelpfulNPC(fileScanner, version, placeID, keyScanner);
               else if(typeOfNPC ==  2)
                  c = new MischievousNPC(fileScanner, version, placeID, keyScanner);
               else if(typeOfNPC ==  3)
                  c = new AggressiveNPC(fileScanner, version, placeID, keyScanner);
               else
                  c = new ChaoticNPC(fileScanner, version, placeID, keyScanner);
            }
            allCharacters.put(c.getID(), c);
        }
        
        // Add at least one character if there are none in the file
        if (nCharacters < 1) {
            System.out.print("How many players are there?\t");
            int nPlayers = keyScanner.nextInt();
            if (nPlayers < 1) {
                nPlayers = 1;
            }
            keyScanner.nextLine();
            
            for (int i = 0; i < nPlayers; i++) {
                Character c = new Player("Player " + (i + 1), "A Player",
                                         startPlace, keyScanner);
                allCharacters.put(i + 1, c);
            }
        }
        
        // Read the number of artifacts
        int nArtifacts = 0;
        {
            line = MyScanner.getCleanLine(fileScanner);
            String words[] = line.split("[ \t]+", 2);
            nArtifacts = Integer.parseInt(words[1]);
        }
        // Read all the artifacts
        for (int i = 0; i < nArtifacts; i++) {
            Artifact a = new Artifact(fileScanner, version);
        }
        
        while (fileScanner.hasNextLine()) {
            line = MyScanner.getCleanLine(fileScanner);
            System.out.println(line);
        }
        
        return;
    } // End of readFromFile()
    
} // End of Game class
