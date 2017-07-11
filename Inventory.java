import java.util.HashMap;

public class Inventory {
	
	private HashMap<String, Item> inventory;
	private int maxWeight;
	
	public Inventory() {
		inventory = new HashMap<String, Item>();
		maxWeight = 1;
	}
	
	public void addItem(String key, Item item){
		inventory.put(key, item);
	}
	
	public boolean removeItem(Item takenItem){
		if(inventory.containsValue(takenItem)){
			for (String key : inventory.keySet()) {
				if (inventory.get(key) == takenItem) {
					inventory.remove(key, takenItem);
				}
			}
			return true;
		}else {
			return false;
		}
	}
	
	public HashMap<String, Item> getInventory(){
		return inventory;
	}

	public String containsItem(Item takenItem) {
		if(inventory.containsValue(takenItem)){
			for (String key : inventory.keySet()) {
				if (inventory.get(key) == takenItem) {
					return key;
				}
			}
		}
		return null;
	}
}
