/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

import java.util.Scanner;

public class PlayerControl implements DecisionMaker {
    private Scanner keyScanner;
    
    //--------------------------------------------------------------------------
    // Constructor
    public PlayerControl(Scanner s) {
        keyScanner = s;
    } // End of constructor
    
    //--------------------------------------------------------------------------
    //
    public Move getMove(Character c, Place p, IO io) {
        String[] input = parseInput(io); // Get input
        Move m;
        String typeStr = input[0];
        String argStr;
        if (input.length < 2) {
            argStr = "";
        }
        else {
            argStr = input[1];
        }
        
        // Determine the type of move to create
        if (typeStr.equalsIgnoreCase("go")) {
            m = new MoveGo(c, p, argStr);
        }
        else if (typeStr.equalsIgnoreCase("get")) {
            m = new MoveGet(c, p, argStr);
        }
        else if (typeStr.equalsIgnoreCase("use")) {
            m = new MoveUse(c, p, argStr);
        }
        else if (typeStr.equalsIgnoreCase("drop")) {
            m = new MoveDrop(c, p, argStr);
        }
        else if (typeStr.equalsIgnoreCase("look")) {
            m = new MoveLook(c, p);
        }
        else if ((typeStr.equalsIgnoreCase("inventory") ||
                  typeStr.equalsIgnoreCase("inve"))) {
            
            m = new MoveInventory(c);
        }
        else if (typeStr.equalsIgnoreCase("quit")
                 || typeStr.equalsIgnoreCase("exit")) {
            
            m = new MoveQuit();
        }
        else {
            m = new MoveNull();
        }
        
        return m;
    }
    
    //--------------------------------------------------------------------------
    // Get input, trim it, and return it as an array of 2 lines
    private String[] parseInput(IO io) {
        String input = io.getLine();
        input = input.trim();
        return input.split("[ \t]+", 2);
    } // End of parseInput()
}



