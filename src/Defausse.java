
public class Defausse {

	static Defausse instance;
	public CarteRumeur[] contenu = new CarteRumeur[12];
	private int nbCartes = 0;
	
	public Defausse() {
		
	}
	
	public void defausserUneCarte(CarteRumeur carteDef) {
		contenu[nbCartes] = carteDef;
		nbCartes += 1;
	}
	
	 static public Defausse getInstance() {
		
	}
}
