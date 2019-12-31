/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class GameTester {
    public static void main(String[] args) {
        System.out.println("+-------------------------+");
        System.out.println("| Name:  Christian Rivera |");
        System.out.println("| NetID: criver32         |");
        System.out.println("+-------------------------+");
        System.out.println("| Name:  Osama Ahmad      |");
        System.out.println("| NetID: oahmad7          |");
        System.out.println("+-------------------------+");
        System.out.println("| Name:  Martin Bautista  |");
        System.out.println("| NetID: mbauti23         |");
        System.out.println("+-------------------------+");
        
        Scanner fileScanner;
        String filename = "";
        Scanner keyScanner = KeyboardScanner.getScanner();
        
        // If there was no command line input, ask for a file name
        if (args.length < 1) {
            System.out.print("Enter a file to load: ");
            filename = keyScanner.nextLine();
        }
        else {
            filename = args[0];
        }
        
        // Open the file
        File gdf = new File(filename.trim());
        try {
            fileScanner = new Scanner(gdf);
        }
        catch (Exception e) {
            System.out.println("\nAn exception occurred.  Exiting...");
            return;
        }
        
        // Load and run the game
        Game g = new Game(fileScanner, keyScanner);
        //g.print();
        g.play();
        
        return;
    }
}
