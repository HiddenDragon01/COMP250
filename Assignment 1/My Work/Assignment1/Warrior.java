public class Warrior extends MilitaryUnit {
	
	public Warrior(Tile posWar, double hpWar, String factWar) {
	
		super(posWar, hpWar, 1, factWar, 20.0, 1, 25);
	
	}
	
	public boolean equals(Object o) {
		
		if (o instanceof Warrior) {
			return super.equals(o);
			
		} else {
			return false;
		}
	}

}
