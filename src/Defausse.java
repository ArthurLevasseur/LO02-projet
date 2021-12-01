import java.util.*;

public class Defausse {

	static Defausse instance;
	//public CarteRumeur[] contenu = new CarteRumeur[12];
	ArrayList<CarteRumeur> contenu = new ArrayList<CarteRumeur>();
	//private int nbCartes = 0;
	
	private Defausse() {
		
	}
	
	public void defausserUneCarte(CarteRumeur carteDef) {
		this.contenu.add(carteDef);
	}
	
	 static public Defausse getInstance() {
		 if (instance == null) {
	            instance = new Defausse();
	        }
	        return instance;
	}

	public ArrayList<CarteRumeur> getContenu() {
		return contenu;
	}

	public CarteRumeur seFairePrendreCarteRumeur(int indexOfCarte) {
		CarteRumeur cartePrise = this.contenu.get(indexOfCarte);
		this.contenu.remove(indexOfCarte);
		return cartePrise;
	}
	
	public void setContenu(ArrayList<CarteRumeur> contenu) {
		this.contenu = contenu;
	}
}
