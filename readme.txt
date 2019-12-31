Christian Rivera
NetID: criver32
UIN:   672524194
-----------------
Osama Ahmad
NetID: oahmad7
UIN:   663191463
-----------------
Martin Bautista
NetID: mbauti23
UIN:   
-----------------
CS 342
Homework 5
--------------------------------------------------------------------------------

Enter "make" to build the program and "java GameTester" to run the program.  It
accepts the filename as a command line argument, or prompts for input if no
arguments are given.

Commands:
	GO X:			Follow direction X, if possible.
	LOOK:			Display the current place.
	QUIT/EXIT:		Close the program.
	INVENTORY/INVE:		View inventory.
	GET X:			Pick up artifact X.
	USE X:			Use artifact X.
	DROP X:			Drop artifact X.
	TEXT:			Switch to text UI
	GUI1:			Switch to GUI 1
	GUI2:			Switch to GUI 2
	GUI3:			Switch to GUI 3

--------------------------------------------------------------------------------
* NOTES *

- GET ALL / DROP ALL to pick up or drop everything in the room or
  in your inventory

- Input is not case sensitive.

- NPCs randomly choose a valid move based on the directions and artifacts
  available to them in the current place.  If there is nothing for them to do,
  then they will try (and fail) to go to space.

- The commands "LOOK" and "INVENTORY" do not end a Player's turn.  Another
  command can be used after these two.

- When a Player uses the "QUIT" command or travels to an exit, the game loop
  will terminate at the end of the current round.  (After all characters have
  finished making a move.)

- NPCs will never try to travel to an exit.

--------------------------------------------------------------------------------
* WORK BREAKDOWN (for HW5) *

Christian Rivera:

	NEW CLASSES:
	
	- IO interface
		- IOPlayer class, implements IO for Players
			- Switches between GUIs
			
		- IONPC class, implements IO for NPCs
			- displays what NPCs are doing
	
	- UserInterface
	
	- GUI1 class, implements UserInterface
		- Game message display pane
		- 18 buttons for compass directions
		- Inventory display pane
		- Select an artifact by entering text
		- Perform action with the action buttons
		
	- WinPlace class
		- The first character to reach this wins.
		- A message will appear, and then the game ends.
		- In mc5.gdf, the treasure room is a WinPlace.
		- (note: it is possible for a character to start there randomly)
	
	- Fixed some bugs
	
	TEST FILES:
	- mc5.gdf
	- placetest.gdf

Osama Ahmad:
	- GUI2
	
Martin Bautista:
	- GUI3, submitting late

--------------------------------------------------------------------------------
* WORK BREAKDOWN (for HW4) *

Christian Rivera:
- Extended the Place class with several new classes, which contain new 
  functionality and characteristics.
- Places:
	WindyPlace: A strong wind blows loose artifacts to a different (specific) room.
	
	HauntedPlace: Ghosts steal artifacts and move them to a different (random) room.
	
	Traps: each trap has a trigger and an effect.  These are implemented as
		interfaces so they can be combined with each other. (See UML)
		Traps can be armed/disarmed with a key (if the key value
		matches).  Traps will not hurt the character who armed them.
			**Trigger types**
			CharacterTrigger - goes off when there is a
					   certain number of characters
					   in the room.
					   
			ArtifactTrigger - goes off when there is a
					   certain number of artifacts
					   in the room.
					   
			MobilityTrigger - goes off when there the total mobility
					  in the room is greater than the threshold
					  
			SpecificTrigger - goes off when there is a specific artifact
					  or character in the room.
			
			**Effect types**
			Pitfall - all artifacts and characters fall into a different room.
			
			SpikeTrap - damages all characters in the room.  (except the trap owner)
			
			TeleportTrap - sends a random character to a random room.
			
- GDF - modified GDF format to read in new places (to support old files, add a line with
	a 0 before each place entry)

Osama Ahmad:
- Extended the Artifact class with several new classes, which contain new 
  functionality and characteristics.
  
- Created a new child class for Artifact called "Potion". When a potion is used 
  by a character, he/she is healed by a certain amount. The potion class 
  generates a random integer to determine if the potion is small, medium, or large.
  Based on the size of the potion the name, description, and healing amount is 
  modified in the Potion class constructor. When a character uses a potion, that
  character is healed by a certain amount based on the specified healAmount for
  each size of potion. 
  
- Created a new child class for Artifact called "Poison". When a poison is used 
  by a character, he/she is damaged by fifteen health.
  
- Created a new child class for Artifact called "Scroll". When a scroll is used 
  by a character, he/she is teleported to a random location. The scroll method
  does this by using the getRandomPlace() method in the Place class.
  
- Added a new private integer to the Character class called health, which is 
  initially set to 100. This int keeps track of the character's current health.
  
- Added a new getter function to the character class called "getHealth" which returns
  the character's current health.
  
- Added a new setter function to the character class called "setHealth" which sets
  a new health value for the character.

Martin Bautista:
- New NPC classes for the types that are to populate the world
- Edited the NPCControl and Game class to corporate the new NPC types
- The NPCControl class has most of the functionality that comes from the npc types
- There is also a print message that is unique to each type of NPC but has not yet been implemented correctly. Used inheritance   	to tie it to the NPC class



