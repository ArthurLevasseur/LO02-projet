package vue;

import modele.Joueur;

public interface Vue {

	public void demarrerJeu();
	public void demanderNombreJoueursPhysiques();
	public void demanderNombreJoueursVirtuels();
	public void determinerNomDuJoueur(Joueur j);
	public void initialisationDeLaPartie();
	public void debutTour();
	public void choixAccuse();
	public void repondreAccusation();
	public void choisirIdentite(Joueur j);
	
}
