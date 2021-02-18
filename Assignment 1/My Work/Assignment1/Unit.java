public abstract class Unit {

	private Tile position;
	private double hp;
	private int movingRange;
	private String faction;
	
	public Unit(Tile position, double hp, int movingRange, String faction) {
		
		this.position = position;
		this.hp = hp;
		this.movingRange = movingRange;
		this.faction = faction;
		boolean b = position.addUnit(this); 
		if (b == false) {
			throw new IllegalArgumentException("Illegal argument!");
			}
	}
	
	public final Tile getPosition() {
		
		return this.position;
	}
	
	public final double getHP() {
		
		return this.hp;
	}
	
	public final String getFaction() {
		
		return this.faction;
	}

	
	public boolean moveTo(Tile t) {
		
		boolean b;
		if (Tile.getDistance(this.position, t) < (this.movingRange + 1)) {
			b = t.addUnit(this);
			
			
			
		} else {
			b = false;
		}
		if (b == true) {
			this.position.removeUnit(this);
			this.position = t;
			
		}
		
		
		return b;
	}
	
	public void receiveDamage(double damageReceived) {
		
		if (this.position.isCity() == true) {
			this.hp = this.hp - (damageReceived * .9);
			
		} else if (this.position.isCity() == false) {
			this.hp = this.hp - damageReceived; 
		}
		
		if (this.hp <= 0) {
			this.position.removeUnit(this);
		}
	}
	
	public abstract void takeAction(Tile t);
		
	public boolean equals(Object o) {
		
		boolean b;
		
		if (o instanceof Unit && ((Unit) o).getPosition().equals(this.getPosition()) && ((Unit) o).getHP() == this.getHP() && ((Unit) o).getFaction().equals(this.getFaction())) {
			b = true;
			return b;
		}
		else {
			b = false;
			return b;
		}
		
	}
	
	
}
