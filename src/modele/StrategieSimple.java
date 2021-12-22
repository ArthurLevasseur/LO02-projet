package modele;
	import java.util.ArrayList;
import java.util.Collections;

import controleur.Jeu;

public class StrategieSimple extends Strategie {
	
	
	
	public int choisirActionTour() {
		int choix = (int) (Math.random() * 2);
		if (choix == 1) {
			System.out.println("Il choisit d'accuser un joueur.");
			return 1;
		}
		else {
			System.out.println("Il choisit de jouer un effet hunt.");
			return 2;
		}

	}
	
	public int choisirAccuse() {
		
		Jeu instance = Jeu.getInstance();
		ArrayList<Integer> possibilites = new ArrayList<Integer>();
		
		
		for (int i=1 ; i<instance.getNombreJoueurs()+1 ; i++) {
			if (instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false && instance.getJoueur(i-1)!=instance.getEnTour() && instance.getJoueur(i-1).isAccusable()==true) {
				possibilites.add(i-1);
			}
		}
		Collections.shuffle(possibilites);
		
		return possibilites.get(0);
	}
	
	public int choisirProchainJoueur() {
			
			Jeu instance = Jeu.getInstance();
			ArrayList<Integer> possibilites = new ArrayList<Integer>();
			
			
			for (int i=1 ; i<instance.getNombreJoueurs()+1 ; i++) {
				if (((instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == true && instance.getJoueur(i-1).getIdentiteAssociee().getIsWitch() == false) || instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false) && instance.getJoueur(i-1)!=instance.getEnTour()) {
					possibilites.add(i-1);
				}
			}
			Collections.shuffle(possibilites);
			System.out.println("Il décide de choisir le joueur " + instance.getJoueur(possibilites.get(0)).getPseudo());
			return possibilites.get(0);
	}
	
	public int choisirProchainJoueurWitch() {
		
		Jeu instance = Jeu.getInstance();
		ArrayList<Integer> possibilites = new ArrayList<Integer>();
		
		
		for (int i=1 ; i<instance.getNombreJoueurs()+1 ; i++) {
			if (((instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == true && instance.getJoueur(i-1).getIdentiteAssociee().getIsWitch() == false) || instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false) && instance.getJoueur(i-1)!=instance.getAccused()) {
				possibilites.add(i-1);
			}
		}
		Collections.shuffle(possibilites);
		System.out.println("Il décide de choisir le joueur " + instance.getJoueur(possibilites.get(0)).getPseudo());
		return possibilites.get(0);
}

	public int choixCarteMain() {
		Jeu instance = Jeu.getInstance();
		int choix = -1;
		while (choix < 0 || choix > instance.getEnTour().getCarteEnMain().size()) {
			choix = (int)(Math.random() * instance.getEnTour().getCarteEnMain().size());
		}
		return choix;
	}
	
	public int choixRevelees() {
		Jeu instance = Jeu.getInstance();
		int choix = -1;
		while (choix < 0 || choix > instance.getEnTour().getCarteRevelees().size()) {
			choix = (int)(Math.random() * instance.getEnTour().getCarteEnMain().size());
		}
		return choix;
	}
	
	public int seDefendre() {
		
		return 1;
	}
}
