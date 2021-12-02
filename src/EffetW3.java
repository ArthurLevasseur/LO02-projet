import java.util.Scanner;

public class EffetW3 extends Effet {

	public Joueur executionEffet() {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();

		instanceJeu.getAccused().getCarteRevelees().forEach(card -> System.out.println("TAPEZ "+instanceJeu.getAccused().getCarteRevelees().indexOf(card) + " pour prendre " + card));
		Scanner saisieUtilisateur = new Scanner(System.in);
		int choix = saisieUtilisateur.nextInt();
		CarteRumeur carteARecuperer = instanceJeu.getAccused().getCarteRevelees().get(choix);
		instanceJeu.getAccused().prendreCarteRumeur(carteARecuperer);
		return instanceJeu.getAccused();
	}
}