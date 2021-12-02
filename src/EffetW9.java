import java.util.Scanner;

public class EffetW9 extends Effet {

	public Joueur executionEffet() {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();

		Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getAccused(),"Choisissez le prochain joueur.");
		choix.setMustAccuse(true);
		instanceJeu.getAccused().setAccusable(false);
		return choix;
	}
}