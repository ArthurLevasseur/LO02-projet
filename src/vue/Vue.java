package vue;

import java.util.ArrayList;

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
	public void jouerHunt(); //à supprimer peut-être plus tard ?
	public void choisirHunt(Joueur j);
	public void accuser();
	public void reveler();
	public void demarrerTour(int joueur, boolean b);
	public void passerTourSuivant();
	public void passerRoundSuivant();
	public void leave();
	public void fight(ArrayList<Joueur> listeGagnants);
	public void choisirWitch();
	public void setBtnAnnulerWitch();
	public void setBtnAnnulerHunt();
	public void setBtnAnnulerAccuse();
	
}
