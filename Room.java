import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  For each existing exit, the room
 * stores a reference to the neighboring room.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Room
{
	private Inventory inventory;
	private Item item;
	private String description;
	private boolean locked;
	private boolean locked1;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private String name;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court
     * yard".
     */
    public Room(String name, String description)
    {
    	this.name = name;
    	inventory = new Inventory();
    	item = new HealthPotion("", 0);
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Define an exit from this room.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, in the form:
     *     You are in the kitchen.
     *     Exits: north west
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }
    
    public String getItemInRoom(){
    	return "You see a" + getItemString();
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getItemDesc(){
    	return item.itemDescription;
    }
    
    public String getOnlyItem(){
    	return getItemString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(Iterator<String> iter = keys.iterator(); iter.hasNext(); )
            returnString += " " + iter.next();
        return returnString;
    }

	private String getItemString(){
    	String returnString = "";
    	Set<String> keys = inventory.getInventory().keySet();
    	for (String string : keys) {
    		returnString += " " + string;
		}
    	return returnString;
    }
	
	private String getItemDescString(){
    	String returnItemDescString = "";
    	Set<String> itemDesc = inventory.getInventory().keySet();
    	for (String string : itemDesc) {
    		returnItemDescString += " " + string;
		}
    	return returnItemDescString;
    }
	
	public Item getItem(String key)
    {
        return (Item)inventory.getInventory().get(key);
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String direction)
    {
        return (Room)exits.get(direction);
    }

	/**
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	public void setLocked(boolean state){
		locked = state;
	}
	
	public boolean getLocked(){
		return locked;
	}

	/**
	 * @return the locked1
	 */
	public boolean getLocked1() {
		return locked1;
	}

	/**
	 * @param locked1 the locked1 to set
	 */
	public void setLocked1(boolean locked1) {
		this.locked1 = locked1;
	}

}
