import java.util.Scanner;

public class EffetW8 extends Effet {

	public Joueur executionEffet() {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();

		Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getAccused(),"Choisissez le joueur à défausser.");
		instanceDefausse.defausserUneCarte(choix.seFairePrendreCarteRumeur((int)(Math.random()*choix.getCarteEnMain().size())));
		return instanceJeu.getAccused();
	}
}