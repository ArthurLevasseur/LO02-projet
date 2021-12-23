package effets;
import modele.CarteRumeur;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.SaisirInt;

public class EffetH4 extends Effet {
	
	public EffetH4() {
		super();
		this.explication = "Vous choisissez le prochain joueur.\nAvant son tour, vous prenez une carte rumeur de sa main et l'ajoutez dans la votre.";
	}
	
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();
		Joueur choix;
		
		if (instanceJeu.getEnTour().isIA()) {
			System.out.println(instanceJeu.getEnTour().getPseudo() + " choisit un adversaire � qui voller une carte.");
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
			System.out.println("Vous avez vol� : ");
			System.out.println(carteVolee);
			return choix;
		}
	}
}
