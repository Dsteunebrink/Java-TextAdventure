
public class Knife extends Item {
	
	public Knife(String itemDescription, int weigth) {
		super(itemDescription, weigth);
		// TODO Auto-generated constructor stub
	}

	public void useKnife(Player player){
		if (player.getCurrentRoom().getName().equalsIgnoreCase("kitchen")) {
			Room current = player.getCurrentRoom();
			Room room = current.getExit("north");
			if (player.getEnemy().isAlive() == true){
				System.out.println("Stop stabbing him he is already dead.");
			}else {
				System.out.println("You stabbed the man");
				player.getEnemy().dealDamageEnemy(5);
				player.getEnemy().isAlive();
				if(player.getEnemy().isAlive() == true){
					System.out.println("In his last breath he hits you you lose 2 health points.");
					if (player.isAlive() == true){
						System.out.println("In his last breath he killed you.");
						System.exit(0);
					}
					System.out.println("He is now dead you can now escape.");
					if (room.getName().equalsIgnoreCase("outside")){
						room.setLocked1(false);
					}
				}
			}
			
		}else {
			System.out.println("Why the hell would i use a knife here.");
		}
	}

	@Override
	protected void useItem(Command command, Player player) {
		String key = player.getInventory().containsItem(this);
		if (key == "knife"){
			useKnife(player);
		}
		
	}

}
