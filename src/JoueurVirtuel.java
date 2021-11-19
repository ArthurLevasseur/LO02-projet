
public class JoueurVirtuel extends Joueur {

	private Strategie strategieActuelle;
	private static int nombreJoueursVirtuels;
	
	public JoueurVirtuel() {
		nombreJoueursVirtuels += 1;
		this.pseudo = "bot" + nombreJoueursVirtuels;
	}
}
