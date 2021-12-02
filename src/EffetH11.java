import java.util.Scanner;

public class EffetH11 extends Effet {
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		Scanner saisieUtilisateur = new Scanner(System.in);

		if (instanceDefausse.getContenu() == null) {
			System.out.println("Le tas de défausse est vide !");
		}
		else {
			System.out.println("Voici les cartes rumeurs de la défausse, choisissez la carte que vous voulez prendre :");
			instanceDefausse.getContenu().forEach(card -> System.out.println("TAPEZ "+instanceDefausse.getContenu().indexOf(card) + " pour jouer " + card));
			int choix = saisieUtilisateur.nextInt();
			while (choix<0 || choix>instanceDefausse.getContenu().size()) {
				System.out.println("Choix invalide !");
				choix = saisieUtilisateur.nextInt();
			}
			instanceJeu.getEnTour().prendreCarteRumeur(instanceDefausse.seFairePrendreCarteRumeur(choix));

		}
		return instanceJeu.getEnTour();

	}
}
