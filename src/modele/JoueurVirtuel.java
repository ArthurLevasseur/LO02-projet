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
	
	
}
