package modele;
import effets.AngryMobWitch;
import effets.TheInquisitionWitch;
import effets.PointedHatWitch;
import effets.HookedNoseWitch;
import effets.DuckingStoolWitch;
import effets.CauldronWitch;
import effets.EvilEyeWitch;

/**
 * Classe repr�sentant l'effet Witch d'une carte Rumeur. Avec la classe {@link Hunt}, elle compose la classe {@link CarteRumeur}.
 *
 */

public class Witch{
	
	/**
	 * Effet associ� � la partie Witch de la carte.
	 */

	private Effet effetAssocie;
	
	/**
	 * Constructeur de la carte. Selon le num�ro attribu� lors de sa cr�ation, l'effet Witch s�lectionn� sera diff�rent.
	 * @param numCarte Le num�ro de la carte. Le num�ro correspond � l'ordre de la carte dans les r�gles du jeu.
	 * @see effets
	 */
	
	public Witch(int numCarte) {
		if (numCarte == 1) {
			this.effetAssocie = new AngryMobWitch();
		}
		else if (numCarte == 2) {
			this.effetAssocie = new TheInquisitionWitch();
		}
		else if (numCarte == 3) {
			this.effetAssocie = new PointedHatWitch();
		}
		else if (numCarte == 4) {
			this.effetAssocie = new HookedNoseWitch();
		}
		else if (numCarte == 5) {
			this.effetAssocie = new AngryMobWitch();
		}
		else if (numCarte == 6) {
			this.effetAssocie = new AngryMobWitch();
		}
		else if (numCarte == 7) {
			this.effetAssocie = new DuckingStoolWitch();
		}
		else if (numCarte == 8) {
			this.effetAssocie = new CauldronWitch();
		}
		else if (numCarte == 9) {
			this.effetAssocie = new EvilEyeWitch();
		}
		else if (numCarte == 10) {
			this.effetAssocie = new AngryMobWitch();
		}
		else if (numCarte == 11) {
			this.effetAssocie = new AngryMobWitch();
		}
		else if (numCarte == 12) {
			this.effetAssocie = new AngryMobWitch();
		}
	}
	
	/**
	 * Ex�cute l'effet en appelant la vue associ�e � l'effet.
	 * @param numCarte
	 */
	
	public void executerEffet(int numCarte) {
		this.effetAssocie.appelVue();
	}
	
	/**
	 * Convertit la carte en cha�ne de caract�re.
	 */
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("effet Witch? : " + this.effetAssocie.explication);
		return str.toString();
	}
}
