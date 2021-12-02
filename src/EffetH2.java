import java.util.Scanner;

public class EffetH2 extends Effet {
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		Scanner saisieUtilisateur = new Scanner(System.in);
		
		Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur, son identit� sera secr�tement r�vel�e.");
		while (choix.getIdentiteAssociee().getDevoilee() == true) {
			choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choix incorrecte ! Choisissez un joueur dont l'identit� n'a pas encore �t� r�v�l�e");
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
