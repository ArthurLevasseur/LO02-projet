import java.util.Scanner;

public class EffetH3 extends Effet {
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		Scanner saisieUtilisateur = new Scanner(System.in);
		
		if (instanceJeu.getEnTour().getCarteRevelees() == null) {
			System.out.println("Votre tas de cartes rumeurs révélées est vide !");
		}
		else {
			System.out.println("Voici vos cartes rumeurs révélées, choisissez la carte que vous voulez reprendre :");
			instanceJeu.getEnTour().getCarteRevelees().forEach(card -> System.out.println("TAPEZ "+instanceJeu.getEnTour().getCarteRevelees().indexOf(card) + " pour jouer " + card));
			
			int choix = saisieUtilisateur.nextInt();
			while (choix<0 || choix>instanceJeu.getEnTour().getCarteRevelees().size()) {
				System.out.println("Choix invalide !");
				choix = saisieUtilisateur.nextInt();
			}
			instanceJeu.getEnTour().prendreCarteRumeur(instanceJeu.getEnTour().getCarteRevelees().get(choix));
		}
		
		return instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
		
	}
}
