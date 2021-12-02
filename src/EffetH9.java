import java.util.Scanner;

public class EffetH9 extends Effet {
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		Scanner saisieUtilisateur = new Scanner(System.in);
		
		Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getAccused(),"Choisissez le prochain joueur.");
		choix.setMustAccuse(true);
		instanceJeu.getEnTour().setAccusable(false);
		return choix;	
		
	}
}
