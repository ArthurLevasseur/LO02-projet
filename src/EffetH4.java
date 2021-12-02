import java.util.Scanner;

public class EffetH4 extends Effet {
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		Scanner saisieUtilisateur = new Scanner(System.in);
		
		Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur. Vous prenez une carte de sa main.");
		int random = (int)(Math.random() * (choix.getCarteEnMain().size()+ 1));
		CarteRumeur carteVolee = choix.getCarteEnMain().get(random);
		choix.getCarteEnMain().remove(random);
		instanceJeu.getEnTour().getCarteEnMain().add(carteVolee);
		System.out.println("Vous avez volé : ");
		System.out.println(carteVolee);
		return choix;
		
	}
}
