import java.util.Scanner;

public class EffetW7 extends Effet {

	public Joueur executionEffet() {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();

		Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getAccused(),"Choisissez le prochain joueur.");
		return choix;
	}
}