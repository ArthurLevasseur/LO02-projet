import java.util.Scanner;

public class EffetW4 extends Effet {

	public Joueur executionEffet() {

		int choix;
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();

		if (instanceJeu.getEnTour().getCarteEnMain().isEmpty()) {
			System.out.println("Vous ne pouvez pas lui voler une carte, sa main est vide");
		}
		else {
			if (instanceJeu.getAccused().isIA()) {
				System.out.println(instanceJeu.getAccused().getPseudo() + " choisit une carte à reprendre dans sa main.");
				choix = (int)(Math.random() * instanceJeu.getEnTour().getCarteEnMain().size());
			}
			else {
				instanceJeu.getEnTour().getCarteEnMain().forEach(card -> System.out.println("TAPEZ "+instanceJeu.getEnTour().getCarteEnMain().indexOf(card) + " pour prendre " + card));
				Scanner saisieUtilisateur = new Scanner(System.in);
				choix = saisieUtilisateur.nextInt();
			}
			CarteRumeur carteARecuperer = instanceJeu.getEnTour().getCarteEnMain().get(choix);
			instanceJeu.getAccused().prendreCarteRumeur(carteARecuperer);
		}
		return instanceJeu.getAccused();
	}
}