public class ListOfUnits {
	
	Unit[] ArrayUnits;
	int sizeListUnits;
	
	public ListOfUnits() {
		
		this.sizeListUnits = 0;
		this.ArrayUnits = new Unit[10];
		
		
	}
	
	public int size() {
		
		return this.sizeListUnits;
									
	}
		
	public Unit[] getUnits() {
				
		Unit[] ArrayElements = new Unit[this.sizeListUnits];
				
		for (int i = 0; i < this.sizeListUnits; i++) {
						
			ArrayElements[i] = ArrayUnits[i]; 
			
		}
		
		return ArrayElements;
			
	}
	
	public Unit get(int e) {
				
 		Unit s;
 		
		if (e >= 0 && e < this.sizeListUnits)  {
			s = this.ArrayUnits[e];
			return s;
		} else {
			throw new IndexOutOfBoundsException("Index out of bounds!");
		}
		
	}
	
	private int newcapacity(int oldcapacity) {
		
		int newcapacity = oldcapacity + (oldcapacity/2) + 1;
		return newcapacity;
	}
	
	public void add(Unit u) {
		
		
		if (this.sizeListUnits == this.ArrayUnits.length) {
			Unit[] longarray = new Unit[newcapacity(this.ArrayUnits.length)];
			for (int i = 0; i < this.sizeListUnits; i++) {
				longarray[i]= this.ArrayUnits[i];
			}
			this.ArrayUnits = longarray;
			this.ArrayUnits[this.sizeListUnits] = u;
			this.sizeListUnits++;
			
		} else {
			this.ArrayUnits[this.sizeListUnits] = u;
			this.sizeListUnits++;
		}
	
	}
	
	public int indexOf(Unit u) {
						
		int index = -1;
		
		for (int i = 0; i < this.sizeListUnits; i++) {
				
			if (this.ArrayUnits[i].equals(u)) {
				index = i;
				break;
			} 
		}
		
		return index;
	}
	
	public boolean remove(Unit u) {
				
		boolean b = false;
		for (int i = 0; i < this.sizeListUnits; i++) {
			if (this.ArrayUnits[i].equals(u)) {
				for (int j = i; j < this.sizeListUnits; j++) {
					this.ArrayUnits[j] = this.ArrayUnits[j+1];
				}
				this.sizeListUnits--;
				b = true;
				break;
			} 
		}
		return b;
	
	}
	
	public MilitaryUnit[] getArmy() {
		
				
		int milCount = 0;
		
						
		for (int i = 0; i < this.sizeListUnits; i++) {
				
			if (this.ArrayUnits[i] instanceof MilitaryUnit) {
				milCount++;
			}
		}
		
		
		
		MilitaryUnit[] MilitaryArray = new MilitaryUnit[milCount];
		int j = 0;
		for (int i = 0; i < this.sizeListUnits; i++) {			
			if (this.ArrayUnits[i] instanceof MilitaryUnit) {
				MilitaryArray[j] = (MilitaryUnit) this.ArrayUnits[i];
				j++;
				
			}
					
		}
		
		return MilitaryArray;
	}

}
