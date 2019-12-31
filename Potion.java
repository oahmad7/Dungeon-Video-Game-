/* Potion.java
 * 
 * Author: Osama Ahmad
 * UIN: 663191463
 * 
 * Class for potions that characters may drink. There are different potion sizes,
 * each healing a different amount.
 */
import java.util.Scanner;
import java.util.Random;

public class Potion extends Artifact {
	
	private int nHealth;
	private int healAmount;
	
	public Potion(Scanner s) {
		super(s,1);
		
		Random r = new Random();
		int randInt = r.nextInt(3 - 1 + 1) + 1;
		
		if(randInt == 1) {
			name = "Small Potion";
			description = "A potion in a small flask.";
			healAmount = 5;
		}
		else if(randInt == 2) {
			name = "Medium Potion";
			description = "A potion in a Medium Bottle.";
			healAmount = 10;
		}
		else{
			name = "Large Potion";
			description = "A potion in a Large Jug.";
			healAmount = 20;
		}
		
		
	}
	
	@Override
	public boolean use(Character c, Place p) {
		// Restore an amount of health to the player based on what type of potion they drink.
		nHealth = c.getHealth() + healAmount;
		System.out.println("You drink a health potion!");
			
		if(nHealth >= 100) {
			nHealth = 100;
			System.out.println("You are now at full health!");
		}		
		c.setHealth(nHealth);
		return true;
	}

}
