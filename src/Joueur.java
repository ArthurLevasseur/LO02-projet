
public abstract class Joueur {
	
	private int points = 0;
	private int id;
	public CarteRumeur[] carteRevelees = new CarteRumeur[4];
	public CarteRumeur[] carteEnMain = new CarteRumeur[4];
	private boolean accusable;
	private int nbCartesEnMain = 0;
	public Identite identiteAssociee;
	
	public Joueur() {
		this.points = 0;
		this.accusable = true;
		this.identiteAssociee = new Identite();
	}
	
	public void gagnerPoints() {
		
	}
	
	public void revelerCarteRumeur() {
		
	}
	
	public boolean isIA() {
		if (this instanceof JoueurVirtuel) {
			return true;
		}
		
		else {
			return false;
		}
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
