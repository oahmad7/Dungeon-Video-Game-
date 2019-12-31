/*
 * Scroll.java
 * 
 * Author: Osama Ahmad
 * UIN: 663191463
 * 
 * Class for a scroll characters may use. The scroll will teleport the character to a random location.
 */
import java.util.Scanner;

public class Scroll extends Artifact {
	
	public Scroll(Scanner s, int length) {
		super(s,1);
		name = "Scroll";
		description = "An ancient scroll. Reading the spell from the Scroll will teleport you to a random location.";
	}
	
	// Teleport user to a random location
	@Override
	public boolean use(Character c, Place p) {
		
		System.out.println("You used the magical scroll!");
		
		Place currPlace = c.getCurrentPlace();
		Place telePlace = Place.getRandomPlace();
		
		c.setCurrentPlace(telePlace);
		
		System.out.println("You have been teleported from " + currPlace + "to " + telePlace + "!");
		return true;
	}

}
