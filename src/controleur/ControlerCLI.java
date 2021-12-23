package controleur;

import modele.*;
import vue.*;

public class ControlerCLI {


	public void demarrerJeu() {
		Jeu instanceJeu = Jeu.getInstance();
		
		instanceJeu.getVueActuelle().demanderNombreJoueursPhysiques();
		instanceJeu.getVueActuelle().demanderNombreJoueursVirtuels();
		instanceJeu.initGame();
		
		for (Joueur j : instanceJeu.getEnsembleJoueurs()) {
			if (j instanceof JoueurPhysique) {
				instanceJeu.getVueActuelle().determinerNomDuJoueur(j);
			}
		}
		
		for (Joueur j : instanceJeu.getEnsembleJoueurs()) {
			instanceJeu.getVueActuelle().choisirIdentite(j);
		}
		
		instanceJeu.orgaRounds();
		
	}
	
	public void stopJeu() {
		System.exit(1);
	}
	
	public void selectionnerNombreJoueursPhysiques(int i) {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.setNombrePhy(i);
	}
	
	public void selectionnerNombreJoueursVirtuels(int i) {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.setNombreIA(i);
	}
	
	public void choisirPseudo(Joueur j ,String s) {
		Jeu instanceJeu = Jeu.getInstance();
		
		j.setPseudo(s);
	}
	
	public void attribuerIdentite(Joueur j, int identite) {
		
		if (identite == 1) {
			j.getIdentiteAssociee().setWitch(false);
		}
		else if (identite == 2) {
			j.getIdentiteAssociee().setWitch(true);
		}
	}
	
	public void accuser() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().choixAccuse();
	}
	
	public void validerAccusation(int choixAccuse) {
		
		Jeu instanceJeu = Jeu.getInstance();
		
		//On d�finit le joueur accus� dans le jeu
		instanceJeu.setAccused(instanceJeu.getJoueur(choixAccuse-1)); // � MVCiser

		//On appelle la m�thode d'un joueur accus� (elle retourne le prochain joueur, ce qui d�pend des futurs choix de l'accus�)
		Joueur prochainJoueur = instanceJeu.getJoueur(choixAccuse-1).estAccuse();// � MVCiser / corriger ref inutile

		instanceJeu.setEnTour(prochainJoueur);
	}
	
	public void jouerHunt() {
		
	}
	
}
