
public abstract class Joueur {
	
	private int points = 0;
	private int id;
	public CarteRumeur[] carteRevelees = new CarteRumeur[4];
	public CarteRumeur[] carteEnMain = new CarteRumeur[4];
	private boolean accusable;
	private int nbCartesEnMain = 0;
	
	public Joueur() {
		
	}
	
	public void gagnerPoints() {
		
	}
	
	public void revelerCarteRumeur() {
		
	}
	
	public void prendreCarteRumeur(CarteRumeur carteAjoutée) {
		carteEnMain[nbCartesEnMain] = carteAjoutée;
		this.nbCartesEnMain += 1;
	}
	
	public void seFairePrendreCarteRumeur() {
		
	}
	
	public void accuser() {
		
	}
	
	public void jouerCarteWitch() {
		
	}
	
	public void jouerCarteHunt() {
		
	}
	
}
