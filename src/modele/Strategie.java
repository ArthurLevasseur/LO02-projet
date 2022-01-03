package modele;

public abstract class Strategie {

	public Strategie() {
		
	}
	
	abstract public int choisirActionTour();
	
	abstract public int seDefendre();
	
	abstract public int choisirAccuse();
	
	abstract public int choisirProchainJoueur();
	
	abstract public int choisirProchainJoueurWitch();

	protected abstract void repondreAccusation();

	protected abstract void jouerTourIA();

	protected abstract void recapIA();
	
	protected abstract void recapIAAccused();
	
	protected abstract void reponseDuckingStool(int joueurCible);
	
}
