package effets;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.SaisirInt;

public class PetNewtHunt extends Effet {
	
	public PetNewtHunt() {
		super();
		this.explication = "Prenez une carte rumeur révélée de n'importe quel joueur dans votre main.\nVous choisissez le prochain joueur.";
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
			System.out.println("Son tas de cartes rumeurs révélées est vide, dommage !");
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
