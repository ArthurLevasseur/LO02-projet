package effets;
import modele.CarteRumeur;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.SaisirInt;

/**
 * Le joueur qui joue cet effet prend une carte de la main du joueur qui l'a accusé, puis prends le prochain tour.
 */

public class HookedNoseWitch extends Effet {
	
	public HookedNoseWitch() {
		super();
		this.explication = "Vous prenez une carte de la main du joueur qui vous a accusé.\nVous prenez le prochain tour.";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().hookedNoseWitch(this);
	}
	
	public void executionEffet(int choix) {
		
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		
		if (choix != -1) {
			CarteRumeur carteARecuperer = instanceJeu.getEnTour().getCarteEnMain().get(choix);
			instanceJeu.getAccused().prendreCarteRumeur(carteARecuperer);
		}
		instanceJeu.setEnTour(instanceJeu.getAccused());
	}
}