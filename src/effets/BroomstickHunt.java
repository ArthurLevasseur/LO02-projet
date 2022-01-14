package effets;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.SaisirInt;

/**
 * Classe de l'effet Hunt de la carte Broomstick. Lorsque le joueur joue cet effet, il choisit le prochain jour. 
 *
 */

public class BroomstickHunt extends Effet {
	
	public BroomstickHunt() {
		super();
		this.explication = "Vous choisissez le prochain joueur.";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().choisirProchainJoueur(this);
	}
	
	public void executionEffet(Joueur selection) {
		
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.setEnTour(selection);
		
	}
}


/* Jeu instanceJeu = Jeu.getInstance();
Defausse instanceDefausse = Defausse.getInstance();
boolean visable = true;
SaisirInt saisieUtilisateur = SaisirInt.getInstance();

if (instanceJeu.getEnTour().isIA()) {
	System.out.println(instanceJeu.getEnTour().getPseudo() + "choisit le prochain joueur.");
	return instanceJeu.getJoueur(((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirProchainJoueur());
}
else {
	Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
	return choix;
}*/