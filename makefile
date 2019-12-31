JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	GameTester.java \
	MyScanner.java \
	Game.java \
	Place.java \
	Direction.java \
	Artifact.java \
	Player.java \
	NPC.java \
	Character.java \
	PlayerControl.java \
	NPCControl.java \
	DecisionMaker.java \
	KeyboardScanner.java \
	Move.java \
	MoveGo.java \
	MoveGet.java \
	MoveUse.java \
	MoveDrop.java \
	MoveLook.java \
	MoveInventory.java \
	MoveQuit.java \
	MoveNull.java \
	Trap.java \
	TrapTrigger.java \
	TrapEffect.java \
	Pitfall.java \
	SpikeTrap.java \
	TeleportTrap.java \
	ArtifactTrigger.java \
	CharacterTrigger.java \
	SpecificTrigger.java \
	MobilityTrigger.java \
	WindyPlace.java \
	HauntedPlace.java \
	WinPlace.java \
	IO.java \
	IOPlayer.java \
	IONPC.java \
	UserInterface.java \
	GUI1.java \
	GUIText.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class