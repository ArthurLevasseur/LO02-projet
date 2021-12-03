import java.util.Scanner;

public class EffetW7 extends Effet {
	
	public EffetW7() {
		super();
		this.explication = "Vous choisissez le prochain joueur.";
	}

	public Joueur executionEffet() {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		Joueur choix;
		
		if (instanceJeu.getAccused().isIA()) {
			System.out.println(instanceJeu.getAccused().getPseudo() + " choisit le prochain joueur.");
			choix = instanceJeu.getJoueur(((JoueurVirtuel) instanceJeu.getAccused()).getStrategieActuelle().choisirProchainJoueurWitch());
		}
		else {
			choix = instanceJeu.selectionnerAdversaire(instanceJeu.getAccused(),"Choisissez le prochain joueur.");
		}
		
		return choix;
	}
}