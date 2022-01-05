package modele;
import effets.*;

/**
 * Classe représentant une carte rumeur. Les effets des cartes sont définis dans le package effets et héritent de la classe {@link Effet}. 
 * 
 *
 */

public class CarteRumeur {
	
	/**
	 * Numéro de la carte. Utilisé pour l'instanciation et l'attribution des effets (1 à 12 pour les cartes du jeu).
	 */

	protected int numCarte;
	
	/**
	 * Nom de la carte.
	 */
	
	protected String nomCarte;
	
	/**
	 * Description d'un effet supplémentaire de la carte, que ce soit une condition ou 
	 */
	
	protected String effetSupp = "";
	protected static int nbCarte = 0;
	
	/**
	 * Référence à l'effet hunt associé.
	 */
	
	protected Hunt effetHunt;
	
	/**
	 * Référence à l'effet witch associé.
	 */
	
	protected Witch effetWitch;
	
	/**
	 * Constructeur de la classe. Selon le numéro de la carte la description est attribuée.
	 */
	
	public CarteRumeur() {
		nbCarte += 1;
		numCarte = nbCarte;
		
		if (numCarte == 1) {
			this.nomCarte = "Une foule en colère";
		}
		else if (numCarte == 2) {
			this.nomCarte = "L'inquisition";
		}
		else if (numCarte == 3) {
			this.nomCarte = "Un chapeau pointu";
		}
		else if (numCarte == 4) {
			this.nomCarte = "Un nez crochu";
		}
		else if (numCarte == 5) {
			this.nomCarte = "Un manche à balai";
			this.effetSupp = "Lorsque cette carte est révélée, vous ne pouvez pas être ciblé par la carte \"Une foule en colère\".\n";
		}
		else if (numCarte == 6) {
			this.nomCarte = "Une verrue";
			this.effetSupp = "Lorsque cette carte est révélée, vous ne pouvez pas être ciblé par la carte \"Le bûcher\".\n";
		}
		else if (numCarte == 7) {
			this.nomCarte = "Un bûcher";
		}
		else if (numCarte == 8) {
			this.nomCarte = "Un chaudron";
		}
		else if (numCarte == 9) {
			this.nomCarte = "Un mauvais oeil";
		}
		else if (numCarte == 10) {
			this.nomCarte = "Un crapaud";
		}
		else if (numCarte == 11) {
			this.nomCarte = "Un chat noir";
		}
		else if (numCarte == 12) {
			this.nomCarte = "Un triton de compagnie";
		}
		
		this.effetHunt = new Hunt(numCarte);
		this.effetWitch = new Witch(numCarte);
	}
	
	/**
	 * Affiche la carte sous forme de chaîne de caractère.
	 */
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("Carte Rumeur : " + this.nomCarte + "(n°" + this.numCarte + ")" + "\n" + effetSupp);
		str.append(this.effetHunt + "\n");
		str.append(this.effetWitch + "\n");
		return str.toString();
	}
	
	/**
	 * Execute l'effet hunt. Appelle la fonction {@link Hunt.executerEffet} de la classe {@link Hunt}.
	 */
	
	public void appliquerEffetHunt() {
		this.effetHunt.executerEffet(this.numCarte);
	}
	
	/**
	 * Execute l'effet witch. Appelle la fonction {@link Witch.executerEffet} de la classe {@link Witch}.
	 */
	
	public void appliquerEffetWitch(Joueur accused) {
		this.effetWitch.executerEffet(this.numCarte);
	}
	
	/**
	 * Getter pour récupérer le numéro de la carte.
	 * @return Le numéro de la carte.
	 */

	public int getNumCarte() {
		return numCarte;
	}
	
	/**
	 * Setter pour changer le numéro de la carte.
	 * @param numCarte Numéro de la carte à attribuer.
	 */

	public void setNumCarte(int numCarte) {
		this.numCarte = numCarte;
	}
	
	/**
	 * Getter pour récupérer le nom de la carte.
	 * @return Le nom de la carte.
	 */

	public String getNomCarte() {
		return nomCarte;
	}
	
	/**
	 * Setter pour changer le nom de la carte.
	 * @param numCarte nom de la carte à attribuer.
	 */

	public void setNomCarte(String nomCarte) {
		this.nomCarte = nomCarte;
	}
	
	/**
	 * Retourne l'effet hunt de la carte.
	 * @return L'effet hunt de la carte.
	 */

	public Hunt getEffetHunt() {
		return effetHunt;
	}
	
	/**
	 * Retourne l'effet witch de la carte.
	 * @return L'effet witch de la carte.
	 */

	public Witch getEffetWitch() {
		return effetWitch;
	}

	
	
}
