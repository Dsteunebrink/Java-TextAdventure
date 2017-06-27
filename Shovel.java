
public class Shovel extends Item {
	
	public Shovel(String itemDescription) {
		super(itemDescription);
		// TODO Auto-generated constructor stub
	}

	public void useShovel(Player player){
		if (player.getCurrentRoom().getName().equalsIgnoreCase("cantine")) {
			System.out.println("you broke down the door with the shovel");
			Room current = player.getCurrentRoom();
			Room room = current.getExit("east");
			if (room.getName().equalsIgnoreCase("kitchen")){
				room.setLocked(false);
			}
		}else {
			System.out.println("You can't use the shovel here what do you want the do dig or something");
		}
	}

	@Override
	protected void useItem(Command command, Player player) {
		String key = player.getInventory().containsItem(this);
		if (key == "shovel"){
			useShovel(player);
		}
		
	}

}
