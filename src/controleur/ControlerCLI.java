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
		
		//On définit le joueur accusé dans le jeu
		instanceJeu.setAccused(instanceJeu.getJoueur(choixAccuse-1)); // à MVCiser

		//On appelle la méthode d'un joueur accusé (elle retourne le prochain joueur, ce qui dépend des futurs choix de l'accusé)
		Joueur prochainJoueur = instanceJeu.getJoueur(choixAccuse-1).estAccuse();// à MVCiser / corriger ref inutile

		instanceJeu.setEnTour(prochainJoueur);
	}
	
	public void jouerHunt() {
		
	}
	
}
