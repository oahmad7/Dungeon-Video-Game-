/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

import java.util.Scanner;

public class KeyboardScanner {
    private static Scanner s;
    
    public static Scanner getScanner() {
        if (s == null) {
            s = new Scanner(System.in);
        }
        return s;
    }
}

