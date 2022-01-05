package modele;

/**
 * Classe repr�sentant la carte identit� d'un joueur. Chaque instance de cette classe est associ�e � une classe {@link Joueur}
 *
 */

public class Identite {

	/**
	 * Bool�en repr�sentant l'identit� du joueur. TRUE si le joueur est une Witch, FALSE si le joueur est un villager.
	 */
	private boolean isWitch;
	
	/**
	 * Bool�en qui vaut TRUE si le joueur est d�voil�, FALSE si l'identit� du joueur est toujours cach�e.
	 */
	
	private boolean devoilee;
	private Joueur joueurAssocie;
	
	/**
	 * Constructeur de la classe. Le joueur est villageois non d�voil� par d�faut.
	 */
	
	public Identite() {
		this.isWitch = false;
		this.devoilee = false;
	}
	
	/**
	 * Getter qui retourne l'�tat de l'identit� : d�voil� ou non.
	 * @return Un bool�en qui indique si le joueur est d�voil� ou non.
	 */
	
	public boolean getDevoilee() {
		return this.devoilee;
	}
	
	/**
	 * Marque l'identit� du joueur comme d�voil�e.
	 */
	
	public void ReveleIdentite() {
		this.devoilee = true;
	}
	
	/**
	 * Retourne le r�le du joueur : TRUE si Witch, FALSE si Villager.
	 * @return
	 */
	
	public boolean getIsWitch() {
		return this.isWitch;
	}
	
	public void choisirIdentite(Joueur joueurAsso, boolean isIA) {
		this.isWitch = isIA;
	}
	
	/**
	 * Attribue le r�le pass� en argument.
	 * @param isWitch Bool�en valant TRUE si Witch, FALSE si Villager.
	 */

	public void setWitch(boolean isWitch) {
		this.isWitch = isWitch;
	}

	public void setDevoilee(boolean devoilee) {
		this.devoilee = devoilee;
	}
	
	/**
	 * Choisit l'identit� au hasard. Utilis�e par les joueurs virtuels.
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
