package effets;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;

/**
 * Classe de l'effet Witch de DuckingStool. Le joueur qui joue la carte choisit le prochain joueur.
 *
 */

public class DuckingStoolWitch extends Effet {
	
	public DuckingStoolWitch() {
		super();
		this.explication = "Vous choisissez le prochain joueur.";
	}

	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().choisirProchainJoueurWitch(this);
	}
	
	public void executionEffet(Joueur selection) {

		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.setEnTour(selection);
		
	}
}