package modele;
import effets.AngryMobHunt;
import effets.ToadHunt;
import effets.BlackCatHunt;
import effets.PetNewtHunt;
import effets.TheInquisitionHunt;
import effets.PointedHatHunt;
import effets.HookedNoseHunt;
import effets.BroomstickHunt;
import effets.DuckingStoolHunt;
import effets.CauldronHunt;
import effets.EvilEyeHunt;

/**
 * Classe représentant l'effet Hunt d'une carte Rumeur. Avec la classe {@link Witch}, elle compose la classe {@link CarteRumeur}.
 *
 */

public class Hunt{
	
	/**
	 * Effet associé à la partie Hunt de la carte.
	 */

	private Effet effetAssocie;
	
	/**
	 * Constructeur de la carte. Selon le numéro attribué lors de sa création, l'effet Hunt sélectionné sera différent.
	 * @param numCarte Le numéro de la carte. Le numéro correspond à l'ordre de la carte dans les règles du jeu.
	 * @see effets
	 */
	
	public Hunt(int numCarte) {
		if (numCarte == 1) {
			this.effetAssocie = new AngryMobHunt();
		}
		else if (numCarte == 2) {
			this.effetAssocie = new TheInquisitionHunt();
		}
		else if (numCarte == 3) {
			this.effetAssocie = new PointedHatHunt();
		}
		else if (numCarte == 4) {
			this.effetAssocie = new HookedNoseHunt();
		}
		else if (numCarte == 5) {
			this.effetAssocie = new BroomstickHunt();
		}
		else if (numCarte == 6) {
			this.effetAssocie = new BroomstickHunt();
		}
		else if (numCarte == 7) {
			this.effetAssocie = new DuckingStoolHunt();
		}
		else if (numCarte == 8) {
			this.effetAssocie = new CauldronHunt();
		}
		else if (numCarte == 9) {
			this.effetAssocie = new EvilEyeHunt();
		}
		else if (numCarte == 10) {
			this.effetAssocie = new CauldronHunt();
		}
		else if (numCarte == 11) {
			this.effetAssocie = new BlackCatHunt();
		}
		else if (numCarte == 12) {
			this.effetAssocie = new PetNewtHunt();
		}
	}
	
	/**
	 * Exécute l'effet en appelant la vue associée à l'effet.
	 * @param numCarte
	 */
	
	public void executerEffet(int numCarte) {
		
		this.effetAssocie.appelVue();
	}
	
	/**
	 * Convertit la carte en chaîne de caractère.
	 */
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("effet Hunt! : " + this.effetAssocie.explication);
		return str.toString();
	}

	public Effet getEffetAssocie() {
		return effetAssocie;
	}

	
}
