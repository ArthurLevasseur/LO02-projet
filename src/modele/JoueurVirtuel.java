package modele;

/**
 * Classe représentant un joueur virtuel.
 *
 */

public class JoueurVirtuel extends Joueur {

	/**
	 * Référence à la stratégie actuelle du joueur. Utile pour le patron de conception Stratégie.
	 */
	private Strategie strategieActuelle;
	
	/**
	 * Variable statique servant à compter le nombre de joueurs virtuels.
	 */
	private static int nombreJoueursVirtuels;
	
	
	/**
	 * Constructeur. Appelle le constructeur de la classe mère.
	 * @see Joueur()
	 */
	public JoueurVirtuel() {
		nombreJoueursVirtuels += 1;
		Strategie strategieTest = new StrategieSimple();
		this.strategieActuelle = strategieTest;
		super.setPseudo("bot" + nombreJoueursVirtuels);
	}
	
	/**
	 * Getter retournant la stratégie du joueur.
	 * @return Stratégie du joueur
	 */
	public Strategie getStrategieActuelle() {
		return strategieActuelle;
	}

	/**
	 * Setter permettant de modifier la stratégie du joueur actuel.
	 * @param strategieActuelle Stratégie du joueur
	 */
	public void setStrategieActuelle(Strategie strategieActuelle) {
		this.strategieActuelle = strategieActuelle;
	}
	
	/**
	 * Fonction appelée quand le joueur doit répondre à une accusation. Appelle la stratégie du joueur en question.
	 */
	public void repondreAccusation() {
		this.getStrategieActuelle().repondreAccusation();
	}
	
	/**
	 * Fonction appelée quand le joueur doit jouer son tour. Appelle la stratégie du joueur en question.
	 */
	public void jouerTour() {
		this.getStrategieActuelle().jouerTourIA();
	}
	
	/**
	 * Fonction appelée en mode graphique quand le jeu doit récapituler ce que le joueur a fait durant son tour. Appelle la stratégie du joueur en question.
	 */
	public void recapIA() {
		this.getStrategieActuelle().recapIA();
	}
	
	/**
	 * Fonction appelée quand le joueur doit répondre à l'effet de la carte DuckingStool. Appelle la stratégie du joueur en question.
	 */
	public void reponseDuckingStool(int joueur) {
		this.getStrategieActuelle().reponseDuckingStool(joueur);
	}
}
