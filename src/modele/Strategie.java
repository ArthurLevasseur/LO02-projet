package modele;

/**
 * Classe stratégie. Définit un certain nombre de méthodes abstraites devant être implémentées par les stratégies. Au stade actuel du projet, une seule stratégie a été crée.
 * @see StrategieSimple
 *
 */

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
	
	protected abstract void reponseDuckingStool(int joueurCible);
	
}
