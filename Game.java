/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initializes all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Game
{
    private Parser parser;
    private Player player;
    private Item items;
    private Room lobby, theatre, pub, lab, office, cantine, cave, kitchen, outside, darkness;
    
    /**
     * Create the game and initialize its internal map.
     */
    public Game()
    {
    	player = new Player();
    	items = new HealthPotion("", 0);
    	items = new Knife("", 0);
    	items = new Shovel("", 0);
        createRooms();
        createItems();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        // create the rooms
        lobby = new Room("lobby", "in the lobby of the university.");
        theatre = new Room("theatre", "in a lecture theatre.");
        pub = new Room("pub", "in the campus pub.");
        lab = new Room("lab", "in a computing lab.");
        office = new Room("office", "in the computing admin office.");
        cantine = new Room("cantine", "in the cantine with alot of food."); 
        cave = new Room("cave", "in an cave its realy dark.");
        darkness = new Room("darkness", "in the cave still but you can't see anything here");
        kitchen = new Room("Kitchen","in the kitchen with a weird guy standing in front of a window.");
        kitchen.setLocked(true);
        outside = new Room("outside", "outside the university.");
        outside.setLocked1(true);

        // Initialize room exits
        lobby.setExit("east", theatre);
        lobby.setExit("south", lab);
        lobby.setExit("west", pub);

        theatre.setExit("west", lobby);
        theatre.setExit("up", cantine);
        theatre.setExit("down", cave);

        pub.setExit("east", lobby);

        lab.setExit("north", lobby);
        lab.setExit("east", office);

        office.setExit("west", lab);
        
        cantine.setExit("down", theatre);
        cantine.setExit("north", kitchen);
        
        cave.setExit("up", theatre);
        cave.setExit("west", darkness);
        
        darkness.setExit("east", cave);
        
        kitchen.setExit("south", cantine);
        kitchen.setExit("north", outside);
        
        player.setCurrentRoom(lobby);
    }
    
    private void createItems(){
    	Item knife, healthpotion, shovel, book;
    	
    	// create the items
    	knife = new Knife("a really sharp knife with blood on it.", 1);
    	healthpotion = new HealthPotion("a red potion used for healing.", 1);
    	shovel = new Shovel("a dirty shovel there is still wet dirt on it.", 1);
    	book = new Knife("a book with a weird shine on it.", 1);
    	
    	// Initialize where the items are
    	Inventory labinv = lab.getInventory();
    	labinv.addItem("knife", knife);
    	
    	Inventory officeinv = office.getInventory();
    	officeinv.addItem("book", book);
    	
    	Inventory pubinv = pub.getInventory();
    	pubinv.addItem("healthPotion", healthpotion);
    	
    	Inventory caveinv = cave.getInventory();
    	caveinv.addItem("shovel", shovel);
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            if(command != null){
            	finished = processCommand(command);
            }
            
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Horror School!");
        System.out.println("Horror School is a new, incredibly hard horror game.");
        System.out.println("You wake up you're hand is covered in blood and is bleeding fast.");
        System.out.println("You need to find something to stop the bleeding.");
        System.out.println("You walk to an university.");
        System.out.println("But when you walk inside the university the door closes behind you");
        System.out.println("You need to escape fast and find help");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);
        else if (commandWord.equals("look")){
        	if(player.getCurrentRoom().getOnlyItem() == ""){
        		System.out.println("You see nothing special about this room.");
        	}else{
        		System.out.println(player.getCurrentRoom().getItemInRoom());
        	}
        }
        else if (commandWord.equals("take")){
        	player.takeItem(command);
        	player.IsAlive();
        }
        else if (commandWord.equals("drop"))
        	player.dropItem(command);
        else if (commandWord.equals("health"))
        	System.out.println("You're health is: " + player.showHealth());
        else if (commandWord.equals("use"))
        	player.useItem(command);
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander.");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        Room currentRoom = player.getCurrentRoom();

        if (nextRoom == null){
        	System.out.println("There is no door!");
        }else {
        	if (nextRoom.getLocked()){
        		System.out.println("The door is locked you need to find a way in.");
        		return;
        	}
        		
        	if (nextRoom.getLocked1()){
        		System.out.println("There is a strange man standing in front of the window kill him to get out");
        		return;
        	}
        	player.setCurrentRoom(nextRoom);
        	if(nextRoom == outside){
            	System.out.println("You did it you escaped you find the nearest road.");
            	System.out.println("And warn some cars they stop for you and safe you you did it.");
    			System.exit(0);
            }
        	player.dealDamage(1);
        	player.IsAlive();
        	System.out.println(player.getCurrentRoom().getLongDescription());
        	System.out.println("Health: " + player.showHealth());
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }


    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();
    }
}
