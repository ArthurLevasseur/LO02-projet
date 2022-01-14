package modele;

/**
 * Classe strat�gie. D�finit un certain nombre de m�thodes abstraites devant �tre impl�ment�es par les strat�gies. Au stade actuel du projet, une seule strat�gie a �t� cr�e.
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
