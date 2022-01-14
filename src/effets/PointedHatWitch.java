package effets;
import java.util.Scanner;

import modele.CarteRumeur;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;

/**
 * Classe de l'effet Witch de Pointed Hat. Le joueur qui la joue reprend une de ses cartes déjà révelées. Il choisit ensuite le prochain joueur.
 */

public class PointedHatWitch extends Effet {
	
	public PointedHatWitch() {
		super();
		this.explication = "Vous reprenez une de vos propres cartes rumeurs déjà révélées dans votre main.\nVous prenez le prochain tour.";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().pointedHat(this, false);
	}

	public void executionEffet(int choix) {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();

		if (choix != -1) {
			CarteRumeur carteARecuperer = instanceJeu.getAccused().getCarteRevelees().get(choix);
			instanceJeu.getAccused().prendreCarteRumeur(carteARecuperer);
		}
		
		
		instanceJeu.setEnTour(instanceJeu.getAccused());
		
		
	}
}