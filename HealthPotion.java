
public class HealthPotion extends Item {
	
	private boolean usedHealth;
	
	public HealthPotion(String itemDescription, int weigth) {
		super(itemDescription, weigth);
		usedHealth = true;
		// TODO Auto-generated constructor stub
	}

	public void useHealthPotion(Player player){
		if(usedHealth == true){
			player.heal(5);
			System.out.println("you have healed yourself for 5 health points.");
			System.out.println("Health: " + player.showHealth());
			usedHealth = false;
		}else{
			System.out.println("You can't heal with an empty potion.");
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
