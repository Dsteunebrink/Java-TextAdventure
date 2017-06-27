public abstract class Item {
	
	protected String itemDescription;
	
	public Item(String itemDescription) {
		
		this.itemDescription = itemDescription;
		
	}
	
	protected abstract void useItem(Command command, Player player);
}
