import java.util.Scanner;

public class EffetH5 extends Effet {
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		Scanner saisieUtilisateur = new Scanner(System.in);
		
		if (instanceJeu.getEnTour().isIA()) {
			System.out.println(instanceJeu.getEnTour().getPseudo() + "choisit le prochain joueur.");
			return instanceJeu.getJoueur(((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirProchainJoueur());
		}
		else {
			Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
			return choix;
		}
	}
}
