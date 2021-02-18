public class Settler extends Unit {

	public Settler(Tile posSettler, double hpSettler, String factSettler) {
		super(posSettler, hpSettler, 2, factSettler);
		
							
	}
	
	public void takeAction(Tile t) {
		
		
		if (t == getPosition() && getPosition().isCity() == false) {
			
			getPosition().foundCity();
			getPosition().removeUnit(this);
			
		} 
		  
	}
	
	public boolean equals(Object o) {
		
		if (o instanceof Settler) {
			return super.equals(o);
			
		} else {
			return false;
		}
	}
		
}
