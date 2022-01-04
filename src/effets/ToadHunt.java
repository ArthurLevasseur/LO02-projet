package effets;
import java.util.ArrayList;

import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.SaisirInt;

public class ToadHunt extends Effet {
	
	public ToadHunt() {
		super();
		this.explication = "Révelez votre identité. Si vous êtes un villageois, vous choisissez qui joue. Si vous êtes une sorcière, le joueur à votre gauche joue.";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().chaudronHunt(this);
	}
	
	public void executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();
		
		instanceJeu.getEnTour().getIdentiteAssociee().ReveleIdentite();
		if (instanceJeu.getEnTour().getIdentiteAssociee().getIsWitch() == true) {
			int i = 0;
			ArrayList<Joueur> listeJoueurs = new ArrayList<>();
			for (i=0; i<instanceJeu.getEnsembleJoueurs().size(); i++) {
				if ((instanceJeu.getJoueur(i).getIdentiteAssociee().getDevoilee() == true && instanceJeu.getJoueur(i).getIdentiteAssociee().getIsWitch() == false) || instanceJeu.getJoueur(i).getIdentiteAssociee().getDevoilee() == false) {
					listeJoueurs.add(instanceJeu.getJoueur(i));
				}
				if (instanceJeu.getJoueur(i) == instanceJeu.getEnTour()) {
					if (i == instanceJeu.getEnsembleJoueurs().size()-1) {
						instanceJeu.setEnTour(instanceJeu.getJoueur(0));
					}
					else {instanceJeu.setEnTour(instanceJeu.getJoueur(i+1));}
				}
			}
			for (i=0; i<listeJoueurs.size(); i++) {
				if (listeJoueurs.get(i) == instanceJeu.getEnTour()) {
					if (i == listeJoueurs.size()-1) {
						instanceJeu.setEnTour(listeJoueurs.get(0));
					}
					else {instanceJeu.setEnTour(listeJoueurs.get(i+1));}
				}
			}
		}
		else {
			if (instanceJeu.getEnTour().isIA() == false) {
				instanceJeu.setEnTour(instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur."));
			}
			else {
				int choix = ((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirProchainJoueur();
				instanceJeu.setEnTour(instanceJeu.getJoueur(choix));
			}
			
		}
		
	}
}
