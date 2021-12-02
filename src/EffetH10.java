import java.util.Scanner;

public class EffetH10 extends Effet {
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		Scanner saisieUtilisateur = new Scanner(System.in);
		
		instanceJeu.getEnTour().getIdentiteAssociee().ReveleIdentite();
		if (instanceJeu.getEnTour().getIdentiteAssociee().getIsWitch() == true) {
			System.out.println("Vous etiez une sorcière. Le prochain joueur sera celui à votre gauche.");
			int i = 0;
			for (i=0; i<instanceJeu.getEnsembleJoueurs().length; i++) {
				if (instanceJeu.getJoueur(i) == instanceJeu.getEnTour()) {
					if (i == instanceJeu.getEnsembleJoueurs().length-1) {
						return instanceJeu.getJoueur(0);
					}
					else {return instanceJeu.getJoueur(i+1);}
				}
			}
		}
		else {
			System.out.println("Vous etiez un villageois.");
			return instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
		}
		
		return null;
		
	}
}
