package modele;

/**
 * Classe représentant la carte identité d'un joueur. Chaque instance de cette classe est associée à une classe {@link Joueur}
 *
 */

public class Identite {

	/**
	 * Booléen représentant l'identité du joueur. TRUE si le joueur est une Witch, FALSE si le joueur est un villager.
	 */
	private boolean isWitch;
	
	/**
	 * Booléen qui vaut TRUE si le joueur est dévoilé, FALSE si l'identité du joueur est toujours cachée.
	 */
	
	private boolean devoilee;
	private Joueur joueurAssocie;
	
	/**
	 * Constructeur de la classe. Le joueur est villageois non dévoilé par défaut.
	 */
	
	public Identite() {
		this.isWitch = false;
		this.devoilee = false;
	}
	
	/**
	 * Getter qui retourne l'état de l'identité : dévoilé ou non.
	 * @return Un booléen qui indique si le joueur est dévoilé ou non.
	 */
	
	public boolean getDevoilee() {
		return this.devoilee;
	}
	
	/**
	 * Marque l'identité du joueur comme dévoilée.
	 */
	
	public void ReveleIdentite() {
		this.devoilee = true;
	}
	
	/**
	 * Retourne le rôle du joueur : TRUE si Witch, FALSE si Villager.
	 * @return
	 */
	
	public boolean getIsWitch() {
		return this.isWitch;
	}
	
	public void choisirIdentite(Joueur joueurAsso, boolean isIA) {
		this.isWitch = isIA;
	}
	
	/**
	 * Attribue le rôle passé en argument.
	 * @param isWitch Booléen valant TRUE si Witch, FALSE si Villager.
	 */

	public void setWitch(boolean isWitch) {
		this.isWitch = isWitch;
	}

	public void setDevoilee(boolean devoilee) {
		this.devoilee = devoilee;
	}
	
	/**
	 * Choisit l'identité au hasard. Utilisée par les joueurs virtuels.
	 * @see StrategieSimple
	 */
	
	public void randomIdentite() {
		int random = (int) (Math.random() * 2);
		if (random == 0) {
			this.isWitch = true;
		}
		else {
			this.isWitch = false;
		}
		
	}
	
	
}
