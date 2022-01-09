package modele;

/**
 * Classe abstraite repr�sentant un effet. Les classes du package effets h�ritent de cette classe.
 * @see effets
 *
 */

abstract public class Effet {
	
	/**
	 * Cha�ne de caract�re contenant les effets des cartes.
	 */
	
	protected String explication;

	/**
	 * Fonction faisant appel � la vue n�cessaire pour la carte.
	 */
	
	public abstract void appelVue();
	
	/**
	 * Fonction ex�cutant l'effet de la carte en mode console quand la vue ne retourne aucune information.
	 */
	
	public abstract void executionEffet();
	
	/**
	 * Fonction ex�cutant l'effet de la carte en mode console quand la vue retourne un joueur.
	 * @param selection Le joueur retourn� par la vue. G�n�ralement utilis� quand le joueur a du choisir un joueur pour r�aliser l'effet de la carte.
	 */
	
	public abstract void executionEffet(Joueur selection);
	
	/**
	 * Fonction ex�cutant l'effet de la carte en mode console quand la vue retourne un entier.
	 * @param choix L'entier retourn� par la vue. G�n�ralement utilis� quand le joueur a du choisir une carte.
	 */
	public abstract void executionEffet(int choix);
	
	/**
	 * Fonction ex�cutant l'effet de la carte en mode console quand la vue retourne un joueur et un entier. G�n�ralement utilis� quand l'effet de la cartes appelle plusieurs vues.
	 * @param selection Joueur retourn�.
	 * @param choix Entier retourn�.
	 */
	
	public abstract void executionEffet(Joueur selection, int choix);
	
}
