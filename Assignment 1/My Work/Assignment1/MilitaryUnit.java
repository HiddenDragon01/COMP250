public abstract class MilitaryUnit extends Unit {
	
	private double damage;
	private int attackRange;
	private int armor;
	
	public MilitaryUnit(Tile posMil, double hpMil, int mrMil, String factMil, double damage, int attackRange, int armor) {
		
		super(posMil,hpMil,mrMil,factMil);
		
		this.damage = damage;
		this.attackRange = attackRange;
		this.armor = armor;
		
		
	}
	
	public void takeAction(Tile t) {
		
		if (Tile.getDistance(t, getPosition()) >= (this.attackRange + 1)) {
			return;
		}
		
		else {
			
			Unit weakEnemy = t.selectWeakEnemy(getFaction());
			if (weakEnemy == null) {
				return;
			}
			
			else if (getPosition().isImproved() == true){
				weakEnemy.receiveDamage((this.damage*1.05));
			}
			
			else {
				weakEnemy.receiveDamage(this.damage);
			}
		}
		
	}
	
	public void receiveDamage(double damageReceived) {
		
		double multiplier = (100)/(100 + this.armor);
		super.receiveDamage(multiplier*damageReceived);
	}

}
