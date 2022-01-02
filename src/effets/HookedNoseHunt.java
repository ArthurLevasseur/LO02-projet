package effets;
import modele.CarteRumeur;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.SaisirInt;

public class HookedNoseHunt extends Effet {
	
	public HookedNoseHunt() {
		super();
		this.explication = "Vous choisissez le prochain joueur.\nAvant son tour, vous prenez une carte rumeur de sa main et l'ajoutez dans la votre.";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().hookedNoseHunt(this);
	}
	
	public void executionEffet(Joueur selection) {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();
		
		
		if (selection.getCarteEnMain().isEmpty()) {
			System.out.println("il n'a pas de cartes en main");
		}
		else {
			int random = (int)(Math.random() * (selection.getCarteEnMain().size()));
			CarteRumeur carteVolee = selection.getCarteEnMain().get(random);
			selection.getCarteEnMain().remove(random);
			instanceJeu.getEnTour().getCarteEnMain().add(carteVolee);
			System.out.println("Vous avez volé : ");
			System.out.println(carteVolee);
		}


		instanceJeu.setEnTour(selection);
	}
}
