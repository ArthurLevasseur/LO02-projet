package vue;

public interface Vue {

	public int demarrerJeu();
	public int demanderNombreJoueursPhysiques();
	public int demanderNombreJoueursVirtuels();
	public void initialisationDeLaPartie();
	public int debutTour();
	public int choixAccuse();
	public int repondreAccusation();
	
}
