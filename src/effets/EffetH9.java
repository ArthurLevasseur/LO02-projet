package effets;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.SaisirInt;

public class EffetH9 extends Effet {
	
	public EffetH9() {
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