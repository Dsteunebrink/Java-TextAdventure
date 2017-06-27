
public class HealthPotion extends Item {
	
	private boolean usedHealth;
	
	public HealthPotion(String itemDescription) {
		super(itemDescription);
		usedHealth = true;
		// TODO Auto-generated constructor stub
	}

	public void useHealthPotion(Player player){
		if(usedHealth == true){
			player.heal(2);
			System.out.println("you have healed yourself for 2 health points");
			System.out.println("Health: " + player.showHealth());
			usedHealth = false;
		}else{
			System.out.println("You can't heal with an empty potion");
		}
		
	}

	@Override
	protected void useItem(Command command, Player player) {
		String key = player.getInventory().containsItem(this);
		 if(key == "healthPotion"){
			useHealthPotion(player);
		} 
	}
}
