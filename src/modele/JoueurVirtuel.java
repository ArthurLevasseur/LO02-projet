package modele;

/**
 * Classe repr�sentant un joueur virtuel.
 *
 */

public class JoueurVirtuel extends Joueur {

	/**
	 * R�f�rence � la strat�gie actuelle du joueur. Utile pour le patron de conception Strat�gie.
	 */
	private Strategie strategieActuelle;
	
	/**
	 * Variable statique servant � compter le nombre de joueurs virtuels.
	 */
	private static int nombreJoueursVirtuels;
	
	
	/**
	 * Constructeur. Appelle le constructeur de la classe m�re.
	 * @see Joueur()
	 */
	public JoueurVirtuel() {
		nombreJoueursVirtuels += 1;
		Strategie strategieTest = new StrategieSimple();
		this.strategieActuelle = strategieTest;
		super.setPseudo("bot" + nombreJoueursVirtuels);
	}
	
	/**
	 * Getter retournant la strat�gie du joueur.
	 * @return Strat�gie du joueur
	 */
	public Strategie getStrategieActuelle() {
		return strategieActuelle;
	}

	/**
	 * Setter permettant de modifier la strat�gie du joueur actuel.
	 * @param strategieActuelle Strat�gie du joueur
	 */
	public void setStrategieActuelle(Strategie strategieActuelle) {
		this.strategieActuelle = strategieActuelle;
	}
	
	/**
	 * Fonction appel�e quand le joueur doit r�pondre � une accusation. Appelle la strat�gie du joueur en question.
	 */
	public void repondreAccusation() {
		this.getStrategieActuelle().repondreAccusation();
	}
	
	/**
	 * Fonction appel�e quand le joueur doit jouer son tour. Appelle la strat�gie du joueur en question.
	 */
	public void jouerTour() {
		this.getStrategieActuelle().jouerTourIA();
	}
	
	/**
	 * Fonction appel�e en mode graphique quand le jeu doit r�capituler ce que le joueur a fait durant son tour. Appelle la strat�gie du joueur en question.
	 */
	public void recapIA() {
		this.getStrategieActuelle().recapIA();
	}
	
	/**
	 * Fonction appel�e quand le joueur doit r�pondre � l'effet de la carte DuckingStool. Appelle la strat�gie du joueur en question.
	 */
	public void reponseDuckingStool(int joueur) {
		this.getStrategieActuelle().reponseDuckingStool(joueur);
	}
}
