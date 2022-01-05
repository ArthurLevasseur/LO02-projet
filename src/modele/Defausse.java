package modele;
import effets.*;
import java.util.ArrayList;

/**
 * Classe représentant la défausse. N'est utilisée qu'avec 5 joueurs.
 *
 */

public class Defausse {

	/**
	 * Attribut contenant la référénce à l'instance pour le patron de conception singleton.
	 */
	
	static Defausse instance;
	//public CarteRumeur[] contenu = new CarteRumeur[12];
	
	/**
	 * Contenu de la défausse
	 */
	
	private ArrayList<CarteRumeur> contenu = new ArrayList<CarteRumeur>();

	
	private Defausse() {
		
	}
	
	/**
	 * Défausse la carte fournie en argument.
	 * @param carteDef La carte à défausser
	 */
	
	public void defausserUneCarte(CarteRumeur carteDef) {
		this.contenu.add(carteDef);
	}
	
	/**
	 * Retourne l'instance de la défausse pour l'utiliser dans d'autres parties du programme
	 * @return L'instance de la défausse
	 */
	
	 static public Defausse getInstance() {
		 if (instance == null) {
	            instance = new Defausse();
	        }
	        return instance;
	}
	 
	 /**
	  * Retourne le contenu de la défausse.
	  * @return Le contenu de la défausse.
	  */

	public ArrayList<CarteRumeur> getContenu() {
		return contenu;
	}
	
	/**
	 * Vide la défausse.
	 */
	
	public void resetContenu() {
		this.contenu = new ArrayList<CarteRumeur>();
	}
	
	/**
	 * 
	 * @param indexOfCarte
	 * @return
	 */

	public CarteRumeur seFairePrendreCarteRumeur(int indexOfCarte) {
		CarteRumeur cartePrise = this.contenu.get(indexOfCarte);
		this.contenu.remove(indexOfCarte);
		return cartePrise;
	}
	
	/**
	 * Setter permettant de modifier la collection du contenu de la défausse
	 * @param contenu
	 */
	
	public void setContenu(ArrayList<CarteRumeur> contenu) {
		this.contenu = contenu;
	}
}
