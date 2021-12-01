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
		
		
		Instance.setEnTour(premierJoueur);
		Instance.setEnTour(this.jouerTour(Instance));
		int nombreIdenDevoilee = 0;
		int maxPoints = 0;
		
		for (int i=0; i<Instance.getNombreJoueurs(); i++) {
			if (Instance.getJoueur(i).getPoints() > maxPoints) {
				maxPoints = Instance.getJoueur(i).getPoints();
			}
		}
		
		while (nombreIdenDevoilee < Instance.getNombreJoueurs()-1 && maxPoints<5) {
			
			
			System.out.println("c'est au joueur " + Instance.getEnTour().pseudo + " de jouer son tour !");
			
			Instance.setEnTour(this.jouerTour(Instance));
			
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
	
	public Joueur jouerTour(Jeu Instance) {
		int choix = 0;
		Scanner saisieUtilisateur = new Scanner(System.in);

		Joueur prochainJoueur;
		
		while (choix!=1 && choix!=2) {
			
			if (Instance.getEnTour().isMustAccuse() == true) {
				System.out.println("Vous ne pouvez pas utiliser de carte rumeur ce tour-ci, vous devez accuser un joueur.");
				choix = 1;
			}
			else {
				System.out.println("Que voulez vous faire?\n\n1) Accuser un autre joueur.\n2) Jouer une carte Rumeur (effet Hunt!)");
				// affichage des cartes : (ne marche pas) joueurEnTour.carteEnMain.forEach(card -> System.out.println(card));
				
				if (Instance.getEnTour() instanceof JoueurPhysique) {
					choix = saisieUtilisateur.nextInt();
				}
				else {
					choix = ((JoueurVirtuel) Instance.getEnTour()).getStrategieActuelle().choisirActionTour();
				}
				
			}
			
			Instance.getEnTour().setMustAccuse(false);
			
			if (choix == 1) {
				
				System.out.println("Qui voulez vous accuser?");
				int compteur = 0;

				for (int i=1 ; i<Instance.getNombreJoueurs()+1 ; i++) {
					if (Instance.getJoueur(i-1).identiteAssociee.getDevoilee() == false && Instance.getJoueur(i-1)!=Instance.getEnTour() && Instance.getJoueur(i-1).isAccusable()==true) {
						compteur += 1;
						System.out.println("Joueur " + (i) + ") " + Instance.getJoueur(i-1).pseudo + " (points : " + Instance.getJoueur(i-1).getPoints() + ")");
					}
				}
				
				
				int choixAccuse=-1;
				
				
				
				while (choixAccuse<0 || choixAccuse>Instance.getNombreJoueurs()) {
					
					if (Instance.getEnTour() instanceof JoueurPhysique) {
						choixAccuse = saisieUtilisateur.nextInt();
					}
					else {
						choixAccuse = ((JoueurVirtuel) Instance.getEnTour()).getStrategieActuelle().choisirAccuse();
					}
					
					if (0<choixAccuse && choixAccuse<Instance.getNombreJoueurs()+1 && Instance.getJoueur(choixAccuse-1).identiteAssociee.getDevoilee() == false && Instance.getJoueur(choixAccuse-1)!=Instance.getEnTour() && Instance.getJoueur(choixAccuse-1).isAccusable()==true) {
						for (int i=0 ; i<Instance.getNombreJoueurs() ; i++) {
							if (Instance.getJoueur(i).isAccusable()==false) {
								Instance.getJoueur(i).setAccusable(true);
							}
						}
						Instance.setAccused(Instance.getJoueur(choixAccuse-1));
						prochainJoueur = Instance.getJoueur(choixAccuse-1).estAccuse();
						
						return prochainJoueur;
					}
					else {
						System.out.println("Choix invalide !");
						choixAccuse = -1;
					}
				}
			}
			
			else if (choix == 2) {
				return Instance.getEnTour().jouerCarteHunt();
			}
			else {
				System.out.println("Choix invalide !");
			}
		}
		return null;
	}
	
	public void revelerIdentiteJoueur() {
		
	}
	
	public void distribuerIdentite() {
		
	}
}
