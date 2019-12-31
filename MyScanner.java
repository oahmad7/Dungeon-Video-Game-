/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

import java.util.Scanner;

public class MyScanner {
    private MyScanner() { }
    
    //--------------------------------------------------------------------------
    // Get line with no comments
    public static String getCleanLine(Scanner s) {
        String cleanLine = null;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (line == null) {
                return null;
            }
            int comment = line.indexOf("//");
            if (comment > 0) {
                cleanLine = line.substring(0, comment - 1);
            }
            else if (comment < 0) {
                cleanLine = line;
            }
            else {
                continue;
            }
            
            cleanLine = cleanLine.trim();
            
            if (cleanLine.length() > 0) {
                return cleanLine;
            }
            else {
                continue;
            }
        }
        return cleanLine;
    } // End of getCleanLine()
}
