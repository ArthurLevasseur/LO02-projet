package effets;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;

public class AngryMobWitch extends Effet {
	
	public AngryMobWitch() {
		super();
		this.explication = "Vous prenez le prochain tour.";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().prendreProchainTour(this);
	}

	public void executionEffet() {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();

		instanceJeu.setEnTour(instanceJeu.getAccused());
	}
}