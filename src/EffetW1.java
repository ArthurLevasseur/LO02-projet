import java.util.Scanner;

public class EffetW1 extends Effet {
	
	public EffetW1() {
		super();
		this.explication = "Vous prenez le prochain tour.";
	}

	public Joueur executionEffet() {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();

		return instanceJeu.getAccused();
	}
}