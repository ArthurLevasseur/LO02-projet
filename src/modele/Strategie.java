package modele;

public abstract class Strategie {

	public Strategie() {
		
	}
	
	abstract public int choisirActionTour();
	
	abstract public int seDefendre();
	
	abstract public int choisirAccuse();
	
	abstract public int choisirProchainJoueur();
	
	abstract public int choisirProchainJoueurWitch();
	
}
