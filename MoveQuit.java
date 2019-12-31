/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 3
 */

//--------------------------------------------------------------------------
// QUIT
public class MoveQuit implements Move {
    public MoveQuit() { }
    public boolean execute() {
        Game.quitGame();
        return true;
    }
}

