import java.util.Scanner;

public class EffetH12 extends Effet {
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		Scanner saisieUtilisateur = new Scanner(System.in);

		Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"De quel joueur souhaitez vous voler une carte rumeur révélée ?");
		if (instanceJeu.getEnTour().getCarteRevelees() == null) {
			System.out.println("Son tas de cartes rumeurs révélées est vide, dommage !");
		}
		else {
			System.out.println("Voici ses cartes rumeurs révélées, choisissez la carte que vous voulez reprendre :");
			choix.getCarteRevelees().forEach(card -> System.out.println("TAPEZ "+choix.getCarteRevelees().indexOf(card) + " pour jouer " + card));
			int choixCarte = saisieUtilisateur.nextInt();
			while (choixCarte<0 || choixCarte>choix.getCarteRevelees().size()) {
				System.out.println("Choix invalide !");
				choixCarte = saisieUtilisateur.nextInt();
			}
			instanceJeu.getEnTour().prendreCarteRumeur(choix.getCarteRevelees().get(choixCarte));
		}
		
		return instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");

	}
}
