package modele;

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
		Instance.setEnTour(this.jouerTour());
		int nombreIdenDevoilee = 0;
		
		//Boucle des tours des joueurs, s'arrête lorsqu'un joueur a 5 points, ou qu'il ne reste qu'un joueur en jeu.
		while (nombreIdenDevoilee < Instance.getNombreJoueurs()-1) {
			
			
			System.out.println("c'est au joueur " + Instance.getEnTour().pseudo + " de jouer son tour !");
			
			//Tour des joueurs qui retourne le joueur du tour suivant dans la variable "enTour"
			Instance.setEnTour(this.jouerTour());
			
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
		/*for (int j=0; j<Instance.getNombreJoueurs(); j++) {
			if (Instance.getJoueur(j).getPoints() > 4) {
				Instance.setGagnants(Instance.getJoueur(j));
			}
		}*/
		
	}
	
	public void choisirProchainJoueur() {
		
	}
	
	public Joueur jouerTour() {

		//Affichage des infos générales du joueur en tour
		
		
		Joueur prochainJoueur;
		Jeu instanceJeu = Jeu.getInstance();
		System.out.println("\n---------------Votre identité----------------\n");
		if (instanceJeu.getEnTour().getIdentiteAssociee().getIsWitch()) {System.out.print("Vous êtes une Witch ");} else {System.out.print("Vous êtes un Villager ");};
		if (instanceJeu.getEnTour().getIdentiteAssociee().getDevoilee()) {System.out.println("devoilé.\n");} else {System.out.println("encore en round.\n");};
		System.out.println("-----------------Votre main------------------\n");
		for (int i=0; i<instanceJeu.getEnTour().getCarteEnMain().size(); i++) {System.out.println("Carte " + i + " : " +instanceJeu.getEnTour().getCarteEnMain().get(i).getNomCarte() +"\n");};
		if (instanceJeu.getEnTour().getCarteEnMain().isEmpty()) {System.out.println("Aucunes cartes\n");}
		System.out.println("-------------Vos cartes révélées-------------\n");
		for (int i=0; i<instanceJeu.getEnTour().getCarteRevelees().size(); i++) {System.out.println("Carte " + i + " : " +instanceJeu.getEnTour().getCarteRevelees().get(i).getNomCarte() +"\n");};
		if (instanceJeu.getEnTour().getCarteRevelees().isEmpty()) {System.out.println("Aucunes cartes\n");}
		
		
		//Création de la boucle de choix du joueur
		int choix = 0;
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();
		while (choix !=2) {
			
			//Si le joueur est ciblé par la carte "Mauvais oeil", son choix est automatiquement d'accuser un autre joueur.
			if (instanceJeu.getEnTour().isMustAccuse() == true) {
				System.out.println("Vous ne pouvez pas utiliser de carte rumeur ce tour-ci, vous devez accuser un joueur.");
				choix = 1;
			}
			//Choix du joueur
			else {
				System.out.println("Que voulez vous faire?\n\n1) Accuser un autre joueur.\n2) Jouer une carte Rumeur (effet Hunt!)\n");
				
				//Pour un joueur physique
				if (instanceJeu.getEnTour() instanceof JoueurPhysique) {
					choix = saisieUtilisateur.nextInt();
				}
				
				//Pour une IA
				else {
					choix = ((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirActionTour();
				}
				
			};
			
			//réinitialisation de l'effet de "Mauvais oeil".
			instanceJeu.getEnTour().setMustAccuse(false);
			
			if (choix == 1) {
				//Le joueur décide d'accuser.
				System.out.println("Qui voulez vous accuser?\n");
				int compteur = 0;

				//Affichage des joueurs ciblables.
				for (int i=1 ; i<instanceJeu.getNombreJoueurs()+1 ; i++) {
					if (instanceJeu.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false && instanceJeu.getJoueur(i-1)!=instanceJeu.getEnTour() && instanceJeu.getJoueur(i-1).isAccusable()==true) {
						compteur += 1;
						System.out.println("Joueur " + (i) + ") " + instanceJeu.getJoueur(i-1).pseudo + " (points : " + instanceJeu.getJoueur(i-1).getPoints() + ")\n");
					}
				}
				//Dans le cas de 2 derniers joueurs en round et l'un d'eux n'est pas accusable
				if (compteur==0) {
					//Réinitialisation de la variable accusable
					for (int i=0 ; i<instanceJeu.getNombreJoueurs() ; i++) {
						if (instanceJeu.getJoueur(i).isAccusable()==false) {
							instanceJeu.getJoueur(i).setAccusable(true);
						}
					}
					System.out.println("Vous ne pouvez accuser personne.");
					
					//Dans le cas où ne joueur, en plus n'aurait pas de cartes en main.
					if (!instanceJeu.getEnTour().getCarteEnMain().isEmpty()) {
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
					while (0>choixAccuse || choixAccuse>instanceJeu.getNombreJoueurs()+1 || instanceJeu.getJoueur(choixAccuse-1).getIdentiteAssociee().getDevoilee() == true || instanceJeu.getJoueur(choixAccuse-1)==instanceJeu.getEnTour() || instanceJeu.getJoueur(choixAccuse-1).isAccusable()==false) {
						
						//Pour les joueurs physiques
						if (instanceJeu.getEnTour() instanceof JoueurPhysique) {
							choixAccuse = saisieUtilisateur.nextInt();
						}
							//Pour les IAs
						else {
							choixAccuse = ((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirAccuse() + 1;
						}
						
						//Si le choix correspond à un joueur ciblable
						if (0<choixAccuse && choixAccuse<instanceJeu.getNombreJoueurs()+1 && instanceJeu.getJoueur(choixAccuse-1).getIdentiteAssociee().getDevoilee() == false && instanceJeu.getJoueur(choixAccuse-1)!=instanceJeu.getEnTour() && instanceJeu.getJoueur(choixAccuse-1).isAccusable()==true) {
							//Réinitialisation de la variable accusable (si ce n'est pas encore fait) 
							for (int i=0 ; i<instanceJeu.getNombreJoueurs() ; i++) {
								if (instanceJeu.getJoueur(i).isAccusable()==false) {
									instanceJeu.getJoueur(i).setAccusable(true);
								}
							}
							
							//On définit le joueur accusé dans le jeu
							instanceJeu.setAccused(instanceJeu.getJoueur(choixAccuse-1));
							
							//On appelle la méthode d'un joueur accusé (elle retourne le prochain joueur, ce qui dépend des futurs choix de l'accusé)
							prochainJoueur = instanceJeu.getJoueur(choixAccuse-1).estAccuse();
							
							return prochainJoueur;
						}
						//Si le choix ne correspond pas à un joueur ciblable
						else {
							System.out.println("Choix invalide !");
							choixAccuse = -1;
						}
					}
				}
				
			};
			
			if (choix == 2) {
				boolean peutJouerCarte = false;

				for (int i=0; i<instanceJeu.getEnTour().getCarteEnMain().size(); i++) {
					if (((instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() != 1 && instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() != 2) || ((instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() == 1 || instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() == 2)) && instanceJeu.getEnTour().getIdentiteAssociee().getDevoilee() == true) && (instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() != 3 || (instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() == 3 && !(instanceJeu.getEnTour().getCarteRevelees().isEmpty())))) {
						peutJouerCarte = true;
					}
				}
				if (peutJouerCarte == true) {
					//Appelle de la méthode de jeu d'une carte rumeur, renvoie le prochain joueur (ce qui dépend des choix du joueur en tour)
					return instanceJeu.getEnTour().jouerCarteHunt();
				}
				else {
					//La boucle while va se réeffectuer avec choix =1, donc une accusation
					choix = 1;
					System.out.println("Vous ne pouvez pas jouer de cartes hunts !");
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
