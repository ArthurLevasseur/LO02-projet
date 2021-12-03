import java.util.Scanner;

public class EffetH4 extends Effet {
	
	public EffetH4() {
		super();
		this.explication = "Vous choisissez le prochain joueur.\nAvant son tour, vous prenez une carte rumeur de sa main et l'ajoutez dans la votre.";
	}
	
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		Scanner saisieUtilisateur = new Scanner(System.in);
		Joueur choix;
		
		if (instanceJeu.getEnTour().isIA()) {
			System.out.println(instanceJeu.getEnTour().getPseudo() + " choisit un adversaire à qui voller une carte.");
			choix = instanceJeu.getJoueur(((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirProchainJoueur());
		}
		else {
			choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur. Vous prenez une carte de sa main.");
		};
		
		if (choix.getCarteEnMain().isEmpty()) {
			System.out.println("il n'a pas de cartes en main");
			return choix;
		}
		else {
			int random = (int)(Math.random() * (choix.getCarteEnMain().size()));
			CarteRumeur carteVolee = choix.getCarteEnMain().get(random);
			choix.getCarteEnMain().remove(random);
			instanceJeu.getEnTour().getCarteEnMain().add(carteVolee);
			System.out.println("Vous avez volé : ");
			System.out.println(carteVolee);
			return choix;
		}
	}
}
