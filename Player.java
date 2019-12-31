/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

import java.util.Scanner;

public class Player extends Character {
    public Player(Scanner f, int _version, int placeID, Scanner k) {
        super(f, _version, placeID, k);
    }
    
    public Player(String _name, String _desc, Place _p, Scanner k) {
        super(_name, _desc, _p, k);
    }
}
