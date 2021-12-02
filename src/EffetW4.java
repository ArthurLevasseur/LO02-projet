import java.util.Scanner;

public class EffetW4 extends Effet {

	public Joueur executionEffet() {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();

		instanceJeu.getEnTour().getCarteEnMain().forEach(card -> System.out.println("TAPEZ "+instanceJeu.getEnTour().getCarteEnMain().indexOf(card) + " pour prendre " + card));
		Scanner saisieUtilisateur = new Scanner(System.in);
		int choix = saisieUtilisateur.nextInt();
		CarteRumeur carteARecuperer = instanceJeu.getEnTour().getCarteEnMain().get(choix);
		instanceJeu.getAccused().prendreCarteRumeur(carteARecuperer);
		return instanceJeu.getAccused();
	}
}