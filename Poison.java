/* Poison.java
 * 
 * Author: Osama Ahmad
 * UIN: 663191463
 * 
 * * Class for poison that characters may drink. The poison lowers the players health by 15.
 */
import java.util.Scanner;

public class Poison extends Artifact {
	
	private int nHealth;
	public Poison(Scanner s) {
		super(s,1);
		name = "Poison";
		description = "A small green flask of poison. The large Black X on the front warns you not to consume it.";
	}
	
	@Override
	public boolean use(Character c, Place p) {
		// Lower the player's health by 15 points.
		int nHealth = c.getHealth() - 15;
		System.out.println("You drink some poison!");
				
		if(nHealth <= 0) {
			nHealth = 0;
			System.out.println("You died from drinking poison!");
			// Add NPC or Player death
		}		
		c.setHealth(nHealth);
		return true;
	}

}
