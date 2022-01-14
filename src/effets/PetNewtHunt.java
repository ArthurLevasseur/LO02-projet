package effets;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.SaisirInt;

/**
 * Classe de l'effet hunt de la carte Pet Newt. Le joueur qui joue cette carte peut prendre la carte rumeur revel�e par un autre joueur de son choix. Il choisit ensuite le prochain jour.
 *
 */

public class PetNewtHunt extends Effet {
	
	public PetNewtHunt() {
		super();
		this.explication = "Prenez une carte rumeur r�v�l�e de n'importe quel joueur dans votre main.\nVous choisissez le prochain joueur.";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().petNewtHunt(this);
	}
	
	public void executionEffet(Joueur selection) {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();
		Joueur choix;

		
		if (selection.getCarteRevelees().isEmpty()) {
			System.out.println("Son tas de cartes rumeurs r�v�l�es est vide, dommage !");
			//instanceJeu.getVueActuelle().petNewtHunt(this);
		}
		
		else {
			instanceJeu.getVueActuelle().petNewtHunt2(this, selection);
		}
		

	}
	
	public void executionEffet(Joueur selection, int choix) {
		
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();
		
		if (instanceJeu.getEnTour().isIA() == false) {
			
			instanceJeu.getEnTour().prendreCarteRumeur(selection.getCarteRevelees().get(choix));
			
			instanceJeu.setEnTour(instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur."));
		}
		else {
			instanceJeu.getEnTour().prendreCarteRumeur(selection.getCarteRevelees().get(choix));
			instanceJeu.setEnTour(instanceJeu.getJoueur(((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirProchainJoueur()));
		}
	}
}
