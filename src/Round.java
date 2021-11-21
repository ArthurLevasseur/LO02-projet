import java.util.Scanner;
import java.util.*;

public class Round {
	
	private Joueur[] identitesRevelees = new Joueur[6];
	private Joueur gagnantRound = null;
	
	public Round(Jeu Instance, Joueur premierJoueur) {
		for (int i = 0; i < Instance.getNombreJoueurs(); i++) {
			Instance.getJoueur(i).identiteAssociee.choisirIdentite(Instance.getJoueur(i),Instance.getJoueur(i).isIA());
		}

		if (gagnantRound != null) {
			premierJoueur = gagnantRound;
		}
		System.out.println("c'est au joueur " + premierJoueur.pseudo + " de commencer ce round !");
		
		Joueur joueurEnTour = this.jouerTour(Instance, premierJoueur);
		
		int nombreIdenDevoilee = 0;
		int maxPoints = 0;
		
		for (int i=0; i<Instance.getNombreJoueurs(); i++) {
			if (Instance.getJoueur(i).getPoints() > maxPoints) {
				maxPoints = Instance.getJoueur(i).getPoints();
			}
		}
		
		while (nombreIdenDevoilee < Instance.getNombreJoueurs()-1 && maxPoints<5) {
			System.out.println("c'est au joueur " + joueurEnTour.pseudo + " de jouer son tour !");
			
			joueurEnTour = this.jouerTour(Instance, joueurEnTour);
			
			nombreIdenDevoilee = 0;
			for (int i=0; i<Instance.getNombreJoueurs(); i++) {
				if (Instance.getJoueur(i).identiteAssociee.getDevoilee() == true) {
					nombreIdenDevoilee += 1;
				}
				if (Instance.getJoueur(i).getPoints() > maxPoints) {
					maxPoints = Instance.getJoueur(i).getPoints();
				}
			}

		}
		
		for (int i=0; i<Instance.getNombreJoueurs(); i++) {
			if (Instance.getJoueur(i).identiteAssociee.getDevoilee() == false) {
				gagnantRound = Instance.getJoueur(i);
			}	
		}
		
		gagnantRound.gagnerPoints();
		
		for (int j=0; j<Instance.getNombreJoueurs(); j++) {
			if (Instance.getJoueur(j).getPoints() > 4) {
				Instance.setGagnant(Instance.getJoueur(j));
			}
		}
	}
	
	public void choisirProchainJoueur() {
		
	}
	
	public Joueur jouerTour(Jeu Instance, Joueur joueurEnTour) {
		System.out.println("Que voulez vous faire?\n\n1) Accuser un autre joueur.\n2) Jouer une carte Rumeur (effet Hunt!)");
		// affichage des cartes : (ne marche pas) joueurEnTour.carteEnMain.forEach(card -> System.out.println(card));
		Scanner saisieUtilisateur = new Scanner(System.in);
		Joueur prochainJoueur;
		int choix = 0;
		while (choix!=1 && choix!=2) {
			choix = saisieUtilisateur.nextInt();
			if (choix == 1) {
				
				System.out.println("Qui voulez vous accuser?");
				int compteur = 0;

				for (int i=1 ; i<Instance.getNombreJoueurs()+1 ; i++) {
					if (Instance.getJoueur(i-1).identiteAssociee.getDevoilee() == false && Instance.getJoueur(i-1)!=joueurEnTour) {
						compteur += 1;
						System.out.println("Joueur " + (i) + ") " + Instance.getJoueur(i-1).pseudo + " (points : " + Instance.getJoueur(i-1).getPoints() + ")");
					}
				}
				
				
				int choixAccuse=-1;
				while (choixAccuse<0 || choixAccuse>Instance.getNombreJoueurs()) {
					choixAccuse = saisieUtilisateur.nextInt();
					if (0<choixAccuse && choixAccuse<Instance.getNombreJoueurs()+1) {
						prochainJoueur = Instance.getJoueur(choixAccuse-1).estAccuse(Instance, joueurEnTour);
						return prochainJoueur;
					}
					else {
						System.out.println("Choix invalide !");
					}
				}
			}
			
			else if (choix == 2) {
				joueurEnTour.jouerCarteHunt();
			}
			else {
				System.out.println("Choix invalide !");
			}
		}
		return joueurEnTour;
	}
	
	public void revelerIdentiteJoueur() {
		
	}
	
	public void distribuerIdentite() {
		
	}
}
