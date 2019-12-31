/*
 * 
 * Author: Martin Bautista
 * NetID: Mbauti23
 * UIN: 663597360
 * 
 * The Chatoic NPC class that extends the normal NPC
 */
import java.util.Scanner;

public class ChaoticNPC extends NPC {
  
  public ChaoticNPC(Scanner f, int _version, int placeID, Scanner k) {
        super(f, _version, placeID, k);
    }
  
    // Returns the character's name
    public String getName() {
        return super.getName();
    } 
  
    // Prints a specific message to a player
    private void npcToPlayerMessage() {
        System.out.println("Message from NPC (" + getName() + "): ");
        System.out.println("I will destroy all artifacts dropped in this place.");
    } 
  
}