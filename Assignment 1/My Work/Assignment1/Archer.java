public class Archer extends MilitaryUnit {
	
	private int numarrows;
	
	public Archer(Tile posArcher, double hpArcher, String factArcher) {
		
		super(posArcher, hpArcher, 2, factArcher, 15.0, 2, 0);
	
		this.numarrows = 5;
	}
	
	public void takeAction(Tile t) {
		
		if (this.numarrows == 0) {
			
			this.numarrows = 5;
			
		} else {
			
			super.takeAction(t);
			this.numarrows = this.numarrows - 1;
		}
		
		
	}
	
	public boolean equals(Object o) {
		
		boolean b;
		
		if (o instanceof Archer && ((Archer) o).getPosition().equals(this.getPosition()) && ((Archer) o).getHP() == this.getHP() && ((Archer) o).getFaction().equals(this.getFaction()) && this.numarrows == ((Archer)o).numarrows) {
			b = true;
		}
		else {
			b = false;
		}
		return b;
		
	}

}
