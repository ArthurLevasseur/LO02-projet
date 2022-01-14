package effets;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.SaisirInt;

/**
 * Classe de l'effet Hunt de la carte Black Cat. Quand un joueur utilise cet effet, il peut prendre une carte de sa défausse, et défausse cette carte. Il prend le prochain tour.
 */

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
		
		if (!instanceDefausse.getContenu().isEmpty()) {
			instanceJeu.getEnTour().prendreCarteRumeur(instanceDefausse.seFairePrendreCarteRumeur(choix));
		} 
		
		instanceJeu.setEnTour(instanceJeu.getEnTour());
		
	}
}
