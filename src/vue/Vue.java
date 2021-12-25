package vue;

import modele.Joueur;

public interface Vue {

	public void demarrerJeu();
	public void demanderNombreJoueursPhysiques();
	public void demanderNombreJoueursVirtuels();
	public void determinerNomDuJoueur(Joueur j);
	public void initialisationDeLaPartie();
	public void debutTour();
	public void repondreAccusation(int joueurCible);
	public void choisirIdentite(Joueur j);
	public void demarrerRound();
	public void choisirHunt();
	public void accuser();
	public void reveler();
	public void demarrerTour(int joueur, boolean b);
	public void passerTourSuivant();
	public void passerRoundSuivant();
	
}
