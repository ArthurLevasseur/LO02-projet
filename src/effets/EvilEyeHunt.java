package effets;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.SaisirInt;

/**
 * Classe de l'effet Hunt de Evil Eye. Le joueur ciblé par la carte devra accuser un autre joueur que celui qui a joué la carte.
 *
 */

public class EvilEyeHunt extends Effet {
	
	public EvilEyeHunt() {
		super();
		this.explication = "Vous choisissez le prochain joueur.\nDurant son tour, le joueur ciblé devra accuser un joueur autre que vous, si possible.";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().evilEye(this, true);
	}
	
	public void executionEffet(Joueur selection) {
		
		Jeu instanceJeu = Jeu.getInstance();
		selection.setMustAccuse(true);
		instanceJeu.getEnTour().setAccusable(false);
		instanceJeu.setEnTour(selection);
	}
}