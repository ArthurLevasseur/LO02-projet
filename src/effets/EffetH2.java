package effets;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.SaisirInt;

public class EffetH2 extends Effet {
	
	public EffetH2() {
		super();
		this.explication = "Vous choisissez le prochain joueur.\nAvant son tour, vous regardez secrètement l'identité du joueur";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().chooseAndSecretLook(this);
	}
	
	public void executionEffet(Joueur selection) {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();
		
		if (selection.getIdentiteAssociee().getIsWitch() == true) {
			System.out.println("Ce joueur est une witch.");
		}
		else {
			System.out.println("Ce joueur est un villager.");
		}
		
		instanceJeu.setEnTour(selection);
	}
}
