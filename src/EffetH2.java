import java.util.Scanner;

public class EffetH2 extends Effet {
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		Scanner saisieUtilisateur = new Scanner(System.in);
		
		Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur, son identité sera secrètement révelée.");
		while (choix.getIdentiteAssociee().getDevoilee() == true) {
			choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choix incorrecte ! Choisissez un joueur dont l'identité n'a pas encore été révélée");
		}
		if (choix.getIdentiteAssociee().getIsWitch() == true) {
			System.out.println("Ce joueur est une witch.");
		}
		else {
			System.out.println("Ce joueur est un villager.");
		}
		return choix;
	}
}
