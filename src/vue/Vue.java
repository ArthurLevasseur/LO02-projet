package vue;

import java.util.ArrayList;

import modele.*;
import controleur.*;

public interface Vue {

	/*
	public void demarrerJeu();
	public void demanderNombreJoueursPhysiques();
	public void demanderNombreJoueursVirtuels();
	public void determinerNomDuJoueur(Joueur j);
	public void initialisationDeLaPartie();
	public void debutTour();
	public void repondreAccusation(int joueurCible);
	public void repondreAccusation(Joueur j);
	public void choisirIdentite(Joueur j);
	public void demarrerRound();
	public void jouerHunt(); //à supprimer peut-être plus tard ?
	public void choixAccuse();
	public void choisirHunt(Joueur j);
	public void jouerWitch();
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
	
	public void revealPlayer(Effet effet);
	public void chooseAndSecretLook(Effet effet);
	public void takeBackACard(Effet effet);
	public void askOpponent(Effet effet);
	public void askOpponentSteal(Effet effet);
	public void pickRandom(Effet effet);
	public void discard(Effet effet);
	*/
	
	public void demarrerJeu();
	public void demanderNombreJoueursPhysiques();
	public void demanderNombreJoueursVirtuels();
	public void determinerNomDuJoueur(Joueur j);
	public void initialisationDeLaPartie();
	public void debutTour();
	public void repondreAccusation(int joueurCible);
	public void repondreAccusation(Joueur j);
	public void choisirIdentite(Joueur j);
	public void demarrerRound();
	public void jouerHunt(CarteRumeur choixCarte); //à supprimer peut-être plus tard ?
	public void choixAccuse();
	public void choisirHunt();
	public void jouerWitch();
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
	
	
	//FONCTIONS PRINCIPALES DES EFFETS DES CARTES
	
	public void choisirProchainJoueurWitch(Effet effet);
	public void choisirProchainJoueur(Effet effet);
	public void prendreProchainTour(Effet effet);
	public void discard(Effet effet);
	public void chaudronHunt(Effet effet);
	public void chaudronWitch(Effet effet);
	public void blackCatHunt(Effet effet);
	public void petNewtHunt(Effet effet);
	public void duckingStoolHunt(Effet effet);
	public void evilEye(Effet effet, boolean isHunt);
	public void angryMobHunt(Effet effet);
	public void theInquisitionHunt(Effet effet);
	public void pointedHat(Effet effet, boolean isHunt);
	public void hookedNoseWitch(Effet effet);
	public void hookedNoseHunt(Effet effet);
	
	//FONCTIONS SECONDAIRES DES EFFETS DES CARTES
	
	public void petNewtChoixCarte(Joueur joueur);
	public void afficherCarteVolee(CarteRumeur carteRandom);
	public void revelerHunt(int joueur);
	public void setTextSecret(String string);
	public void duckingStoolChoixCible(int joueur);
	public void passerTourSuivantAccusable();
	public void choisirProchainJoueurVoler();
	
	
	
	public void revelerIA();
	public void initialize();
	public void isAccusedIA(int joueurCible);
	public void afficherRegles();
	
}
