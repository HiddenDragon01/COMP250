public class Worker extends Unit {

	private int numjobs;
	
	public Worker(Tile posWorker, double hpWorker, String factWorker) {
		super(posWorker, hpWorker, 2, factWorker);
						
		this.numjobs = 0;
		
	}
	
	public void takeAction(Tile t) {
		
		if (t == getPosition() && getPosition().isImproved() == false) {
			getPosition().buildImprovement();
			numjobs++;
			if (numjobs == 10) {
				getPosition().removeUnit(this);
			}
		}
	}
	
	public boolean equals(Object o) {
		
		boolean b;
		
		if (o instanceof Worker && ((Worker) o).getPosition().equals(this.getPosition()) && ((Worker) o).getHP() == this.getHP() && ((Worker) o).getFaction().equals(this.getFaction()) && ((Worker) o).numjobs == this.numjobs) {
			b = true;
		}
		else {
			b = false;
		}
		return b;
		
	}
	
}
