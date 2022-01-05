package modele;
import effets.*;

/**
 * Classe repr�sentant une carte rumeur. Les effets des cartes sont d�finis dans le package effets et h�ritent de la classe {@link Effet}. 
 * 
 *
 */

public class CarteRumeur {
	
	/**
	 * Num�ro de la carte. Utilis� pour l'instanciation et l'attribution des effets (1 � 12 pour les cartes du jeu).
	 */

	protected int numCarte;
	
	/**
	 * Nom de la carte.
	 */
	
	protected String nomCarte;
	
	/**
	 * Description d'un effet suppl�mentaire de la carte, que ce soit une condition ou 
	 */
	
	protected String effetSupp = "";
	protected static int nbCarte = 0;
	
	/**
	 * R�f�rence � l'effet hunt associ�.
	 */
	
	protected Hunt effetHunt;
	
	/**
	 * R�f�rence � l'effet witch associ�.
	 */
	
	protected Witch effetWitch;
	
	/**
	 * Constructeur de la classe. Selon le num�ro de la carte la description est attribu�e.
	 */
	
	public CarteRumeur() {
		nbCarte += 1;
		numCarte = nbCarte;
		
		if (numCarte == 1) {
			this.nomCarte = "Une foule en col�re";
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
			this.nomCarte = "Un manche � balai";
			this.effetSupp = "Lorsque cette carte est r�v�l�e, vous ne pouvez pas �tre cibl� par la carte \"Une foule en col�re\".\n";
		}
		else if (numCarte == 6) {
			this.nomCarte = "Une verrue";
			this.effetSupp = "Lorsque cette carte est r�v�l�e, vous ne pouvez pas �tre cibl� par la carte \"Le b�cher\".\n";
		}
		else if (numCarte == 7) {
			this.nomCarte = "Un b�cher";
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
	 * Affiche la carte sous forme de cha�ne de caract�re.
	 */
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("Carte Rumeur : " + this.nomCarte + "(n�" + this.numCarte + ")" + "\n" + effetSupp);
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
	 * Getter pour r�cup�rer le num�ro de la carte.
	 * @return Le num�ro de la carte.
	 */

	public int getNumCarte() {
		return numCarte;
	}
	
	/**
	 * Setter pour changer le num�ro de la carte.
	 * @param numCarte Num�ro de la carte � attribuer.
	 */

	public void setNumCarte(int numCarte) {
		this.numCarte = numCarte;
	}
	
	/**
	 * Getter pour r�cup�rer le nom de la carte.
	 * @return Le nom de la carte.
	 */

	public String getNomCarte() {
		return nomCarte;
	}
	
	/**
	 * Setter pour changer le nom de la carte.
	 * @param numCarte nom de la carte � attribuer.
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
