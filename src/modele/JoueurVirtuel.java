package modele;

public class JoueurVirtuel extends Joueur {

	private Strategie strategieActuelle;
	private static int nombreJoueursVirtuels;
	
	public JoueurVirtuel() {
		nombreJoueursVirtuels += 1;
		Strategie strategieTest = new StrategieSimple();
		this.strategieActuelle = strategieTest;
		super.setPseudo("bot" + nombreJoueursVirtuels);
	}

	public Strategie getStrategieActuelle() {
		return strategieActuelle;
	}

	public void setStrategieActuelle(Strategie strategieActuelle) {
		this.strategieActuelle = strategieActuelle;
	}
	
	public void repondreAccusation() {
		this.getStrategieActuelle().repondreAccusation();
	}
	
	public void jouerTour() {
		this.getStrategieActuelle().jouerTourIA();
	}
	
	public void recapIA() {
		this.getStrategieActuelle().recapIA();
	}
	
	public void recapIAAccused() {
		this.getStrategieActuelle().recapIAAccused();
	}
	
	public void reponseDuckingStool(int joueur) {
		this.getStrategieActuelle().reponseDuckingStool(joueur);
	}
}
