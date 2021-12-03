import java.util.Scanner;

public class EffetH9 extends Effet {
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		Scanner saisieUtilisateur = new Scanner(System.in);
		
		if (instanceJeu.getEnTour().isIA()) {
			System.out.println(instanceJeu.getEnTour().getPseudo() + " choisit le prochain joueur.");
			Joueur choix = instanceJeu.getJoueur(((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirProchainJoueur());
			choix.setMustAccuse(true);
			instanceJeu.getEnTour().setAccusable(false);
			return choix;
		}
		else {
			Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
			choix.setMustAccuse(true);
			instanceJeu.getEnTour().setAccusable(false);
			return choix;
		}
	}
}
