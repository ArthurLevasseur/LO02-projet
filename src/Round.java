import java.util.Scanner;
import java.util.*;

public class Round {
	
	private Joueur[] identitesRevelees = new Joueur[6];
	private Joueur gagnantRound = null;
	
	public Round(Jeu Instance, Joueur premierJoueur) {
		for (int i = 0; i < Instance.getNombreJoueurs(); i++) {
			Instance.getJoueur(i).getIdentiteAssociee().choisirIdentite(Instance.getJoueur(i),Instance.getJoueur(i).isIA());
		}
		//GagnantRound permet de déterminer le premier joueur du round suivant, sauf pour le premier round.
		if (gagnantRound != null) {
			premierJoueur = gagnantRound;
		}
		System.out.println("c'est au joueur " + premierJoueur.pseudo + " de commencer ce round !");
		
		
		Instance.setEnTour(premierJoueur);
		
		//Tour du premier joueur qui retourne le joueur du tour suivant dans la variable "enTour"
		Instance.setEnTour(this.jouerTour(Instance));
		int nombreIdenDevoilee = 0;
		
		//Boucle des tours des joueurs, s'arrête lorsqu'un joueur a 5 points, ou qu'il ne reste qu'un joueur en jeu.
		while (nombreIdenDevoilee < Instance.getNombreJoueurs()-1) {
			
			
			System.out.println("c'est au joueur " + Instance.getEnTour().pseudo + " de jouer son tour !");
			
			//Tour des joueurs qui retourne le joueur du tour suivant dans la variable "enTour"
			Instance.setEnTour(this.jouerTour(Instance));
			
			//Vérification du nombre d'identité révélées pour savoir si c'est la fin d'un round
			nombreIdenDevoilee = 0;
			for (int i=0; i<Instance.getNombreJoueurs(); i++) {
				if (Instance.getJoueur(i).getIdentiteAssociee().getDevoilee() == true) {
					nombreIdenDevoilee += 1;
				}

			}

		}
		
		//Détermination du gagnant d'un round
		for (int i=0; i<Instance.getNombreJoueurs(); i++) {
			if (Instance.getJoueur(i).getIdentiteAssociee().getDevoilee() == false) {
				gagnantRound = Instance.getJoueur(i);
			}	
		}
		gagnantRound.gagnerPoints();
		
		//Détermination des gagnants du jeu (il peut y en avoir 2 si un joueur à 4 points gagne un round en accusant un villageois à 4 points)
		for (int j=0; j<Instance.getNombreJoueurs(); j++) {
			if (Instance.getJoueur(j).getPoints() > 4) {
				Instance.setGagnants(Instance.getJoueur(j));
			}
		}
		
	}
	
	public void choisirProchainJoueur() {
		
	}
	
	public Joueur jouerTour(Jeu Instance) {

		//Affichage des infos générales du joueur en tour
		Joueur prochainJoueur;
		System.out.println("\n---------------Votre identité----------------\n");
		if (Instance.getEnTour().getIdentiteAssociee().getIsWitch()) {System.out.println("Vous êtes une Witch.\n");} else {System.out.println("Vous êtes un Villager.\n");};
		
		System.out.println("-----------------Votre main------------------\n");
		for (int i=0; i<Instance.getEnTour().getCarteEnMain().size(); i++) {System.out.println("Carte " + i + " : " +Instance.getEnTour().getCarteEnMain().get(i).getNomCarte() +"\n");};
		if (Instance.getEnTour().getCarteEnMain().isEmpty()) {System.out.println("Aucunes cartes\n");}
		System.out.println("-------------Vos cartes révélées-------------\n");
		for (int i=0; i<Instance.getEnTour().getCarteRevelees().size(); i++) {System.out.println("Carte " + i + " : " +Instance.getEnTour().getCarteEnMain().get(i).getNomCarte() +"\n");};
		if (Instance.getEnTour().getCarteRevelees().isEmpty()) {System.out.println("Aucunes cartes\n");}
		
		//Création de la boucle de choix du joueur
		int choix = 0;
		Scanner saisieUtilisateur = new Scanner(System.in);
		while (choix !=2) {
			
			//Si le joueur est ciblé par la carte "Mauvais oeil", son choix est automatiquement d'accuser un autre joueur.
			if (Instance.getEnTour().isMustAccuse() == true) {
				System.out.println("Vous ne pouvez pas utiliser de carte rumeur ce tour-ci, vous devez accuser un joueur.");
				choix = 1;
			}
			//Choix du joueur
			else {
				System.out.println("Que voulez vous faire?\n\n1) Accuser un autre joueur.\n2) Jouer une carte Rumeur (effet Hunt!)");
				
				//Pour un joueur physique
				if (Instance.getEnTour() instanceof JoueurPhysique) {
					choix = saisieUtilisateur.nextInt();
				}
				
				//Pour une IA
				else {
					choix = ((JoueurVirtuel) Instance.getEnTour()).getStrategieActuelle().choisirActionTour();
				}
				
			}
			
			//réinitialisation de l'effet de "Mauvais oeil".
			Instance.getEnTour().setMustAccuse(false);
			
			if (choix == 1) {
				//Le joueur décide d'accuser.
				System.out.println("Qui voulez vous accuser?");
				int compteur = 0;

				//Affichage des joueurs ciblables.
				for (int i=1 ; i<Instance.getNombreJoueurs()+1 ; i++) {
					if (Instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false && Instance.getJoueur(i-1)!=Instance.getEnTour() && Instance.getJoueur(i-1).isAccusable()==true) {
						compteur += 1;
						System.out.println("Joueur " + (i) + ") " + Instance.getJoueur(i-1).pseudo + " (points : " + Instance.getJoueur(i-1).getPoints() + ")");
					}
				}
				//Dans le cas de 2 derniers joueurs en round et l'un d'eux n'est pas accusable
				if (compteur==0) {
					//Réinitialisation de la variable accusable
					for (int i=0 ; i<Instance.getNombreJoueurs() ; i++) {
						if (Instance.getJoueur(i).isAccusable()==false) {
							Instance.getJoueur(i).setAccusable(true);
						}
					}
					System.out.println("Vous ne pouvez accuser personne.");
					
					//Dans le cas où ne joueur, en plus n'aurait pas de cartes en main.
					if (!Instance.getEnTour().getCarteEnMain().isEmpty()) {
						System.out.println("Vous devez donc utiliser un effet Hunt.");
						choix = 2;
					}
					else {
						System.out.println("Vous n'avez pas non plus de cartes dans votre main, par défaut, l'effet de la carte \"Mauvais Oeil\" s'estompe.");
					}
				}
				
				//En situation habituelle (si après les vérification, le choix reste d'accuser un joueur):
				if (choix==1) {
					int choixAccuse=-1;
					
					
					//Création de la boucle de choix d'accusation du joueur
					while (0>choixAccuse || choixAccuse>Instance.getNombreJoueurs()+1 || Instance.getJoueur(choixAccuse-1).getIdentiteAssociee().getDevoilee() == true || Instance.getJoueur(choixAccuse-1)==Instance.getEnTour() || Instance.getJoueur(choixAccuse-1).isAccusable()==false) {
						
						//Pour les joueurs physiques
						if (Instance.getEnTour() instanceof JoueurPhysique) {
							choixAccuse = saisieUtilisateur.nextInt();
						}
							//Pour les IAs
						else {
							choixAccuse = ((JoueurVirtuel) Instance.getEnTour()).getStrategieActuelle().choisirAccuse();
						}
						
						//Si le choix correspond à un joueur ciblable
						if (0<choixAccuse && choixAccuse<Instance.getNombreJoueurs()+1 && Instance.getJoueur(choixAccuse-1).getIdentiteAssociee().getDevoilee() == false && Instance.getJoueur(choixAccuse-1)!=Instance.getEnTour() && Instance.getJoueur(choixAccuse-1).isAccusable()==true) {
							//Réinitialisation de la variable accusable (si ce n'est pas encore fait) 
							for (int i=0 ; i<Instance.getNombreJoueurs() ; i++) {
								if (Instance.getJoueur(i).isAccusable()==false) {
									Instance.getJoueur(i).setAccusable(true);
								}
							}
							
							//On définit le joueur accusé dans le jeu
							Instance.setAccused(Instance.getJoueur(choixAccuse-1));
							
							//On appelle la méthode d'un joueur accusé (elle retourne le prochain joueur, ce qui dépend des futurs choix de l'accusé)
							prochainJoueur = Instance.getJoueur(choixAccuse-1).estAccuse();
							
							return prochainJoueur;
						}
						//Si le choix ne correspond pas à un joueur ciblable
						else {
							System.out.println("Choix invalide !");
							choixAccuse = -1;
						}
					}
				}
				
			}
			
			else if (choix == 2) {
				if (Instance.getEnTour().getCarteEnMain().isEmpty()) {
					//La boucle while va se réeffectuer avec choix =1, donc une accusation
					System.out.println("Vous n'avez plus de cartes rumeurs !");
					choix = 1;
					
				}
				else {
					//Appelle de la méthode de jeu d'une carte rumeur, renvoie le prochain joueur (ce qui dépend des choix du joueur en tour)
					return Instance.getEnTour().jouerCarteHunt();
				}
				
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
