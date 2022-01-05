package modele;
import effets.*;
import java.util.ArrayList;

/**
 * Classe repr�sentant la d�fausse. N'est utilis�e qu'avec 5 joueurs.
 *
 */

public class Defausse {

	/**
	 * Attribut contenant la r�f�r�nce � l'instance pour le patron de conception singleton.
	 */
	
	static Defausse instance;
	//public CarteRumeur[] contenu = new CarteRumeur[12];
	
	/**
	 * Contenu de la d�fausse
	 */
	
	private ArrayList<CarteRumeur> contenu = new ArrayList<CarteRumeur>();

	
	private Defausse() {
		
	}
	
	/**
	 * D�fausse la carte fournie en argument.
	 * @param carteDef La carte � d�fausser
	 */
	
	public void defausserUneCarte(CarteRumeur carteDef) {
		this.contenu.add(carteDef);
	}
	
	/**
	 * Retourne l'instance de la d�fausse pour l'utiliser dans d'autres parties du programme
	 * @return L'instance de la d�fausse
	 */
	
	 static public Defausse getInstance() {
		 if (instance == null) {
	            instance = new Defausse();
	        }
	        return instance;
	}
	 
	 /**
	  * Retourne le contenu de la d�fausse.
	  * @return Le contenu de la d�fausse.
	  */

	public ArrayList<CarteRumeur> getContenu() {
		return contenu;
	}
	
	/**
	 * Vide la d�fausse.
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
	 * Setter permettant de modifier la collection du contenu de la d�fausse
	 * @param contenu
	 */
	
	public void setContenu(ArrayList<CarteRumeur> contenu) {
		this.contenu = contenu;
	}
}
