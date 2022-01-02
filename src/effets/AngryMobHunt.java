package effets;
import modele.CarteRumeur;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.SaisirInt;

public class AngryMobHunt extends Effet {
	
	
	public AngryMobHunt() {
		super();
		this.explication = "Vous révélez l'identité d'un autre joueur.\n	- Si c'est une sorcière, vous gagnez 2 points et prenez le prochain tour.\n	- Si c'est un villageois, vous perdez 2 points et il prend le prochain tour.";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().angryMobHunt(this);
	}

	public void executionEffet(Joueur selection) {
		
		Jeu instanceJeu = Jeu.getInstance();
		
		if (selection.getIdentiteAssociee().getIsWitch() == true) {
			//System.out.println("Ce joueur est bien une Witch, vous remportez 2 points.");
			instanceJeu.getEnTour().ajouterPoints(2);
			selection.getIdentiteAssociee().ReveleIdentite();
			instanceJeu.setEnTour(instanceJeu.getEnTour());
		}
		else {
			//System.out.println("Ce joueur est un villager, vous perdez 2 points.");
			instanceJeu.getEnTour().ajouterPoints(-2);
			selection.getIdentiteAssociee().ReveleIdentite();
			instanceJeu.setEnTour(selection);
		}
		
	
	}
	
	public void executionEffet() {
		
	}
	
}
