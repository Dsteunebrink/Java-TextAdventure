class Player{
	
	private Room currentRoom;
	private int health;
	
	public Player(){
		health = 50;
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
	
	public void IsAlive(){
		if(health <= 0){
			System.out.println("You were taking to long and died because blood loss");
			System.exit(0);
		}
	}
}