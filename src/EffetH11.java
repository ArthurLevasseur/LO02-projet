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
			if (instanceJeu.getEnTour().isIA() == false) {
				if (instanceDefausse.getContenu().isEmpty()) {
					System.out.println("la défausse est vide");
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
				
			}
			else {
				System.out.println(instanceJeu.getEnTour().getPseudo() + "recupère une carte de la défausse.");
				
				if (instanceDefausse.getContenu().isEmpty()) {
					System.out.println("la défausse est vide");
				}
				else {
					int choix = (int) (Math.random()*instanceDefausse.getContenu().size());
					instanceJeu.getEnTour().prendreCarteRumeur(instanceDefausse.seFairePrendreCarteRumeur(choix));
				}
			}

		}
		return instanceJeu.getEnTour();
	}
}
