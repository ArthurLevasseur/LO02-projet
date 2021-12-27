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
	
	public void choixDeCarte() {
		Jeu instanceJeu = Jeu.getInstance();
		
		if (instanceJeu.getVueActuelle() instanceof VueConsole) {
			instanceJeu.getVueActuelle().choisirHunt(instanceJeu.getEnTour());
		}
		else {
			
		}
	}
	
	

}
