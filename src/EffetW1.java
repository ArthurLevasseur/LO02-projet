import java.util.Scanner;

public class EffetW1 extends Effet {

	public Joueur executionEffet() {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();

		return instanceJeu.getAccused();
	}
}