package effets;
import modele.CarteRumeur;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.SaisirInt;

public class HookedNoseWitch extends Effet {
	
	public HookedNoseWitch() {
		super();
		this.explication = "Vous prenez une carte de la main du joueur qui vous a accus�.\nVous prenez le prochain tour.";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().hookedNoseWitch(this);
	}
	
	public Joueur executionEffet() {

		int choix;
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();

		if (instanceJeu.getEnTour().getCarteEnMain().isEmpty()) {
			System.out.println("Vous ne pouvez pas lui voler une carte, sa main est vide");
		}
		else {
			if (instanceJeu.getAccused().isIA()) {
				System.out.println(instanceJeu.getAccused().getPseudo() + " choisit une carte � reprendre dans sa main.");
				choix = (int)(Math.random() * instanceJeu.getEnTour().getCarteEnMain().size());
			}
			else {
				instanceJeu.getEnTour().getCarteEnMain().forEach(card -> System.out.println("TAPEZ "+instanceJeu.getEnTour().getCarteEnMain().indexOf(card) + " pour prendre " + card));
				SaisirInt saisieUtilisateur = SaisirInt.getInstance();
				choix = saisieUtilisateur.nextInt();
			}
			CarteRumeur carteARecuperer = instanceJeu.getEnTour().getCarteEnMain().get(choix);
			instanceJeu.getAccused().prendreCarteRumeur(carteARecuperer);
		}
		return instanceJeu.getAccused();
	}
}