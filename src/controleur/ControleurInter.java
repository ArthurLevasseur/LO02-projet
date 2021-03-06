package controleur;

import modele.*;
import vue.*;
import effets.*;

public class ControleurInter {
	
	
	static ControleurInter instance;
	
	private ControleurInter() {
		
	}
	
	public static ControleurInter getInstance() {
		if (instance == null) {
			instance = new ControleurInter();
        }
        return instance;
	}
	
	
	public void entrerPseudo() {
		Jeu instanceJeu = Jeu.getInstance();
		
		if (instanceJeu.getVueActuelle() instanceof VueConsole) {
			
		}
		else {
			instanceJeu.setPseudo();
		}
	}
	
	public void choisirIdentite() {
		
		Jeu instanceJeu = Jeu.getInstance();
		
		if (instanceJeu.getVueActuelle() instanceof VueConsole) {
			for (Joueur j : instanceJeu.getEnsembleJoueurs()) {
				instanceJeu.getVueActuelle().choisirIdentite(j);
			}
		}
		else {
			
		}	
		
	}
	
	public void debutTour() {
		Jeu instanceJeu = Jeu.getInstance();
		
		if (instanceJeu.getVueActuelle() instanceof VueConsole) {
			instanceJeu.getVueActuelle().debutTour();
		}
		else {
			instanceJeu.setVueActuelle(new InterfaceChoixTour());
			instanceJeu.getVueActuelle().debutTour();
		}
	}
	
	public void choixDeCarteHunt() {
		Jeu instanceJeu = Jeu.getInstance();
		
		if (instanceJeu.getVueActuelle() instanceof VueConsole) {
			instanceJeu.getVueActuelle().choisirHunt(instanceJeu.getEnTour());
		}
		else {
			
		}
	}
	
	public void choixDeCarteWitch() {
		Jeu instanceJeu = Jeu.getInstance();
		
		if (instanceJeu.getVueActuelle() instanceof VueConsole) {
			instanceJeu.getVueActuelle().jouerWitch();
		}
		else {
			
		}
	}

}
