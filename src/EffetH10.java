import java.util.ArrayList;
import java.util.Scanner;

public class EffetH10 extends Effet {
	
	public EffetH10() {
		super();
		this.explication = "R�velez votre identit�. Si vous �tes un villageois, vous choisissez qui joue. Si vous �tes une sorci�re, le joueur � votre gauche joue.";
	}
	
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();
		
		instanceJeu.getEnTour().getIdentiteAssociee().ReveleIdentite();
		if (instanceJeu.getEnTour().getIdentiteAssociee().getIsWitch() == true) {
			System.out.println("Vous etiez une sorci�re. Le prochain joueur sera celui � votre gauche.");
			int i = 0;
			ArrayList<Joueur> listeJoueurs = new ArrayList<>();
			for (i=0; i<instanceJeu.getEnsembleJoueurs().length; i++) {
				if ((instanceJeu.getJoueur(i).getIdentiteAssociee().getDevoilee() == true && instanceJeu.getJoueur(i).getIdentiteAssociee().getIsWitch() == false) || instanceJeu.getJoueur(i).getIdentiteAssociee().getDevoilee() == false) {
					listeJoueurs.add(instanceJeu.getJoueur(i));
				}
				if (instanceJeu.getJoueur(i) == instanceJeu.getEnTour()) {
					if (i == instanceJeu.getEnsembleJoueurs().length-1) {
						return instanceJeu.getJoueur(0);
					}
					else {return instanceJeu.getJoueur(i+1);}
				}
			}
			for (i=0; i<listeJoueurs.size(); i++) {
				if (listeJoueurs.get(i) == instanceJeu.getEnTour()) {
					if (i == listeJoueurs.size()-1) {
						return listeJoueurs.get(0);
					}
					else {return listeJoueurs.get(i+1);}
				}
			}
		}
		else {
			System.out.println("Vous etiez un villageois.");
			if (instanceJeu.getEnTour().isIA() == false) {
				return instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
			}
			else {
				int choix = ((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirProchainJoueur();
				return instanceJeu.getJoueur(choix);
			}
			
		}
		
		return null;
		
	}
}
