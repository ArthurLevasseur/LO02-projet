package modele;

/**
 * Classe abstraite représentant un effet. Les classes du package effets héritent de cette classe.
 * @see effets
 *
 */

abstract public class Effet {
	
	/**
	 * Chaîne de caractère contenant les effets des cartes.
	 */
	
	protected String explication;

	/**
	 * Fonction faisant appel à la vue nécessaire pour la carte.
	 */
	
	public abstract void appelVue();
	
	/**
	 * Fonction exécutant l'effet de la carte en mode console quand la vue ne retourne aucune information.
	 */
	
	public abstract void executionEffet();
	
	/**
	 * Fonction exécutant l'effet de la carte en mode console quand la vue retourne un joueur.
	 * @param selection Le joueur retourné par la vue. Généralement utilisé quand le joueur a du choisir un joueur pour réaliser l'effet de la carte.
	 */
	
	public abstract void executionEffet(Joueur selection);
	
	/**
	 * Fonction exécutant l'effet de la carte en mode console quand la vue retourne un entier.
	 * @param choix L'entier retourné par la vue. Généralement utilisé quand le joueur a du choisir une carte.
	 */
	public abstract void executionEffet(int choix);
	
	/**
	 * Fonction exécutant l'effet de la carte en mode console quand la vue retourne un joueur et un entier. Généralement utilisé quand l'effet de la cartes appelle plusieurs vues.
	 * @param selection Joueur retourné.
	 * @param choix Entier retourné.
	 */
	
	public abstract void executionEffet(Joueur selection, int choix);
	
}
