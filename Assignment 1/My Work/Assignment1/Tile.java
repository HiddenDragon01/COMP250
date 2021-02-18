public class Tile {
	
	private int x;
	private int y;
	private boolean city;
	private boolean improvements;
	private ListOfUnits units;
	
	public Tile(int xcoor, int ycoor) {
		
		 this.x = xcoor;
		 this.y = ycoor;
		 this.city = false;
		 this.improvements = false;
		 this.units = new ListOfUnits();
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean isCity() {
		return this.city;
	}
	
	public boolean isImproved() {
		return this.improvements;
	}
	public void foundCity() {
		this.city = true;
	}
	
	public void buildImprovement() {
		this.improvements = true;
	}
	
	public boolean addUnit(Unit u) {
		
		
		if (u instanceof MilitaryUnit) {
			
			
			u = (MilitaryUnit) u;
		
			MilitaryUnit[] MilArray = this.units.getArmy();
			
			
			
			
			for (int i = 0; i < MilArray.length; i++) {
				if (!MilArray[i].getFaction().equals(u.getFaction() )){
					return false;
					
				} 
										
			}
										
		} 
		
		units.add(u); 

		return true;
									
		
		
	}
	
	public boolean removeUnit(Unit u) {
		boolean b = this.units.remove(u);
		return b;
	}
	
	public Unit selectWeakEnemy(String faction) {
		
		Unit weakEnemy = null;
				
		for (int i = 0; i < this.units.size(); i++) {
										
			if (this.units.get(i).getFaction().equals(faction)) {
				weakEnemy = null;
			} else if (!this.units.get(i).getFaction().equals(faction)) {
				double hpWeak = this.units.get(i).getHP();
				for (int j = 0; j < this.units.size(); j++) {
					if (!this.units.get(j).getFaction().equals(faction)) {						
						if (this.units.get(j).getHP() < hpWeak) {
							weakEnemy = this.units.get(j);
							hpWeak = this.units.get(j).getHP();
						}
					}
				} break;
								
			}							
		} 
		
		return weakEnemy;
	}
	
	public static double getDistance(Tile tileOne, Tile tileTwo) {
		
		double xdif = (tileTwo.getX() - tileOne.getX());
		double ydif = (tileTwo.getY() - tileOne.getY());
		double xdifsq = xdif * xdif;
		double ydifsq = ydif * ydif;
		double sqsum = xdifsq + ydifsq;
		double distance = Math.sqrt(sqsum);
		return distance;		
				
	}
	
	
}
