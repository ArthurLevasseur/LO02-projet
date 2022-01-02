package effets;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.SaisirInt;

public class BlackCatHunt extends Effet {
	
	public BlackCatHunt() {
		super();
		this.explication = "Vous ajoutez une des cartes défaussées à votre main, et vous vous défaussez de cette carte.\nVous prenez le prochain tour";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().blackCatHunt(this);
	}
	
	public void executionEffet(int choix) {
		
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		instanceJeu.getEnTour().prendreCarteRumeur(instanceDefausse.seFairePrendreCarteRumeur(choix));
		instanceJeu.setEnTour(instanceJeu.getEnTour());
		
	}
}
