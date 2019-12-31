/*
 * 
 * Author: Martin Bautista
 * NetID: Mbauti23
 * UIN: 663597360
 * 
 * The Helpful NPC class that extends the normal NPC
 */
import java.util.Scanner;

public class HelpfulNPC extends NPC {
  
  public HelpfulNPC(Scanner f, int _version, int placeID, Scanner k) {
        super(f, _version, placeID, k);
    }
  
  // Returns the character's name
    public String getName() {
        return super.getName();
    } 
  
    // Prints a specific message to a player
    private void npcToPlayerMessage() {
        System.out.println("Message from NPC (" + getName() + "): ");
        System.out.println("Hello traveler. Is there anything from my inventory that would assist you?");
    } 
  
}