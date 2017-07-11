import java.util.Set;

class Player{
	
	private Room currentRoom;
	private int health;
    private Enemy enemy;
	private Inventory inventory;
	
	public Player(){
		health = 15;
    	setEnemy(new Enemy());
		inventory = new Inventory();
	}

	/**
	 * @return the currentRoom
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * @param currentRoom the currentRoom to set
	 */
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	public void dealDamage(int damage){
		this.health -= damage;
	}
	
	public void heal(int healAmount){
		this.health += healAmount;
	}
	
	public int showHealth(){
		return health;
	}
	
	public void IsAlive(){
		if(health <= 0){
			System.out.println("You were taking too long and died because of blood loss.");
			System.exit(0);
		}
	}
	
	public boolean isAlive(){
		if(health <= 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	private String getItemString(){
    	String returnString = "";
    	Set<String> keys = inventory.getInventory().keySet();
    	for (String string : keys) {
    		returnString += " " + string;
		}
    	return returnString;
    }
	
	public Item getItem(String key)
    {
        return (Item)inventory.getInventory().get(key);
    }
	
	public void takeItem(Command command){
    	
    	if(!command.hasSecondWord()){
    		// if there is no second word, we don't know what to take...
    		System.out.println("Take what?");
            return;
    	}
    	
    	String item = command.getSecondWord();
    	
    	Item takenItem = getCurrentRoom().getItem(item);  
    	
    	if(takenItem == null)
    		System.out.println("There is no such item here.");
    	else{
    		if (getCurrentRoom().getInventory().containsItem(takenItem) != null) {
    			System.out.println("You took the" + getCurrentRoom().getOnlyItem());
    			String key = getCurrentRoom().getInventory().containsItem(takenItem);
    			getCurrentRoom().getInventory().removeItem(takenItem);
    			getInventory().addItem(key,takenItem);
    			if(key == "book"){
    				getCurrentRoom().getInventory().addItem(key,takenItem);
        			getInventory().removeItem(takenItem);
    				System.out.println("There was a spell on the book you burned yourself.");
    				System.out.println("because the book burned your hand, you dropped it.");
    				dealDamage(1);
    				System.out.println("Health: " + showHealth());
    			}
    		}
    	}
    }
	
	public void dropItem(Command command){
		
		if(!command.hasSecondWord()){
			// if there is no second word, we don't know what to drop...
			System.out.println("Drop what?");
			return;
		}
	
		String item = command.getSecondWord();
	
		Item takenItem = getItem(item);
	
		if(takenItem == null)
			System.out.println("There is no such item here.");
		else{
			if (getInventory().containsItem(takenItem) != null) {
				String key = getInventory().containsItem(takenItem);
				getInventory().removeItem(takenItem);
				getCurrentRoom().getInventory().addItem(key,takenItem);
			}
			System.out.println("You dropped the" + getCurrentRoom().getOnlyItem());
		}
	}

	public void useItem(Command command) {
		
		if(!command.hasSecondWord()){
			// if there is no second word, we don't know what to use...
			System.out.println("Use what?");
			return;
		}
		
		String item = command.getSecondWord();
		
		Item usedItem = getItem(item);
		
		if(usedItem == null) {
			System.out.println("You don't have such item.");
			return;
		}
		usedItem.useItem(command, this);
		
	}

	/**
	 * @return the enemy
	 */
	public Enemy getEnemy() {
		return enemy;
	}

	/**
	 * @param enemy the enemy to set
	 */
	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
}