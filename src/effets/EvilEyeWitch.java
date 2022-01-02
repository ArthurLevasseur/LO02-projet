package effets;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;

public class EvilEyeWitch extends Effet {
	
	public EvilEyeWitch() {
		super();
		this.explication = "Vous choisissez le prochain joueur.\nDurant son tour, le joueur ciblé devra accuser un joueur autre que vous, si possible.";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().evilEye(this, false);
	}

	public void executionEffet(Joueur selection) {

		Jeu instanceJeu = Jeu.getInstance();
		selection.setMustAccuse(true);
		instanceJeu.getEnTour().setAccusable(false);
		instanceJeu.setEnTour(selection);
		
	}
}