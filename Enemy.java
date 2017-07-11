
public class Enemy {
	
	private int health;
	
	public Enemy() {
		health = 10;
	}
	
	public void dealDamageEnemy(int damage){
		this.health -= damage;
	}
	
	public boolean isAlive(){
		if(health <= 0){
			return true;
		}else{
			return false;
		}
	}
	
	public int showHealth(){
		return health;
	}

}
