import java.util.Scanner;

public class EffetW3 extends Effet {
	
	public EffetW3() {
		super();
		this.explication = "Vous reprenez une de vos propres cartes rumeurs déjà révélées dans votre main.\nVous prenez le prochain tour.";
	}
	

	public Joueur executionEffet() {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		int choix;

		if (instanceJeu.getAccused().isIA()) {
			System.out.println(instanceJeu.getAccused().getPseudo() + " choisit une carte à reprendre dans sa main.");
			choix = (int)(Math.random() * instanceJeu.getAccused().getCarteRevelees().size());
		}
		else {
			instanceJeu.getAccused().getCarteRevelees().forEach(card -> System.out.println("TAPEZ "+instanceJeu.getAccused().getCarteRevelees().indexOf(card) + " pour prendre " + card));
			Scanner saisieUtilisateur = new Scanner(System.in);
			choix = saisieUtilisateur.nextInt();			
		}
		CarteRumeur carteARecuperer = instanceJeu.getAccused().getCarteRevelees().get(choix);
		instanceJeu.getAccused().prendreCarteRumeur(carteARecuperer);
		return instanceJeu.getAccused();
		
	}
}