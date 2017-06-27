
public class Knife extends Item {
	
	public Knife(String itemDescription) {
		super(itemDescription);
		// TODO Auto-generated constructor stub
	}

	public void useKnife(){
		System.out.println("work in progress");
	}

	@Override
	protected void useItem(Command command, Player player) {
		String key = player.getInventory().containsItem(this);
		if (key == "knife"){
			useKnife();
		}
		
	}

}
