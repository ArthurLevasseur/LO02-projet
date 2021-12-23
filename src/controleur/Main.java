package controleur;

import modele.Jeu;
import vue.VueConsole;

public class Main {

	public static void main(String[] args) {
		
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.setVueActuelle(new VueConsole());
		instanceJeu.getVueActuelle().demarrerJeu();

	}

}
