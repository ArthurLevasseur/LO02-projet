package modele;

public class Round {
	
	private Joueur[] identitesRevelees = new Joueur[6];
	private Joueur gagnantRound = null;
	
	public Round(Jeu Instance, Joueur premierJoueur) {
		for (int i = 0; i < Instance.getNombreJoueurs(); i++) {
			Instance.getJoueur(i).getIdentiteAssociee().choisirIdentite(Instance.getJoueur(i),Instance.getJoueur(i).isIA());
		}
		//GagnantRound permet de d�terminer le premier joueur du round suivant, sauf pour le premier round.
		if (gagnantRound != null) {
			premierJoueur = gagnantRound;
		}
		System.out.println("c'est au joueur " + premierJoueur.pseudo + " de commencer ce round !");
		
		
		Instance.setEnTour(premierJoueur);
		
		//Tour du premier joueur qui retourne le joueur du tour suivant dans la variable "enTour"
		Instance.setEnTour(this.jouerTour());
		int nombreIdenDevoilee = 0;
		
		//Boucle des tours des joueurs, s'arr�te lorsqu'un joueur a 5 points, ou qu'il ne reste qu'un joueur en jeu.
		while (nombreIdenDevoilee < Instance.getNombreJoueurs()-1) {
			
			
			System.out.println("c'est au joueur " + Instance.getEnTour().pseudo + " de jouer son tour !");
			
			//Tour des joueurs qui retourne le joueur du tour suivant dans la variable "enTour"
			Instance.setEnTour(this.jouerTour());
			
			//V�rification du nombre d'identit� r�v�l�es pour savoir si c'est la fin d'un round
			nombreIdenDevoilee = 0;
			for (int i=0; i<Instance.getNombreJoueurs(); i++) {
				if (Instance.getJoueur(i).getIdentiteAssociee().getDevoilee() == true) {
					nombreIdenDevoilee += 1;
				}

			}

		}
		
		//D�termination du gagnant d'un round
		for (int i=0; i<Instance.getNombreJoueurs(); i++) {
			if (Instance.getJoueur(i).getIdentiteAssociee().getDevoilee() == false) {
				gagnantRound = Instance.getJoueur(i);
			}	
		}
		gagnantRound.gagnerPoints();
		
		//D�termination des gagnants du jeu (il peut y en avoir 2 si un joueur � 4 points gagne un round en accusant un villageois � 4 points)
		/*for (int j=0; j<Instance.getNombreJoueurs(); j++) {
			if (Instance.getJoueur(j).getPoints() > 4) {
				Instance.setGagnants(Instance.getJoueur(j));
			}
		}*/
		
	}
	
	public void choisirProchainJoueur() {
		
	}
	
	public Joueur jouerTour() {

		//Affichage des infos g�n�rales du joueur en tour
		
		
		Joueur prochainJoueur;
		Jeu instanceJeu = Jeu.getInstance();
		System.out.println("\n---------------Votre identit�----------------\n");
		if (instanceJeu.getEnTour().getIdentiteAssociee().getIsWitch()) {System.out.print("Vous �tes une Witch ");} else {System.out.print("Vous �tes un Villager ");};
		if (instanceJeu.getEnTour().getIdentiteAssociee().getDevoilee()) {System.out.println("devoil�.\n");} else {System.out.println("encore en round.\n");};
		System.out.println("-----------------Votre main------------------\n");
		for (int i=0; i<instanceJeu.getEnTour().getCarteEnMain().size(); i++) {System.out.println("Carte " + i + " : " +instanceJeu.getEnTour().getCarteEnMain().get(i).getNomCarte() +"\n");};
		if (instanceJeu.getEnTour().getCarteEnMain().isEmpty()) {System.out.println("Aucunes cartes\n");}
		System.out.println("-------------Vos cartes r�v�l�es-------------\n");
		for (int i=0; i<instanceJeu.getEnTour().getCarteRevelees().size(); i++) {System.out.println("Carte " + i + " : " +instanceJeu.getEnTour().getCarteRevelees().get(i).getNomCarte() +"\n");};
		if (instanceJeu.getEnTour().getCarteRevelees().isEmpty()) {System.out.println("Aucunes cartes\n");}
		
		
		//Cr�ation de la boucle de choix du joueur
		int choix = 0;
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();
		while (choix !=2) {
			
			//Si le joueur est cibl� par la carte "Mauvais oeil", son choix est automatiquement d'accuser un autre joueur.
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
			
			//r�initialisation de l'effet de "Mauvais oeil".
			instanceJeu.getEnTour().setMustAccuse(false);
			
			if (choix == 1) {
				//Le joueur d�cide d'accuser.
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
					//R�initialisation de la variable accusable
					for (int i=0 ; i<instanceJeu.getNombreJoueurs() ; i++) {
						if (instanceJeu.getJoueur(i).isAccusable()==false) {
							instanceJeu.getJoueur(i).setAccusable(true);
						}
					}
					System.out.println("Vous ne pouvez accuser personne.");
					
					//Dans le cas o� ne joueur, en plus n'aurait pas de cartes en main.
					if (!instanceJeu.getEnTour().getCarteEnMain().isEmpty()) {
						System.out.println("Vous devez donc utiliser un effet Hunt.");
						choix = 2;
					}
					else {
						System.out.println("Vous n'avez pas non plus de cartes dans votre main, par d�faut, l'effet de la carte \"Mauvais Oeil\" s'estompe.");
					}
				}
				
				//En situation habituelle (si apr�s les v�rification, le choix reste d'accuser un joueur):
				if (choix==1) {
					int choixAccuse=-1;
					
					
					//Cr�ation de la boucle de choix d'accusation du joueur
					while (0>choixAccuse || choixAccuse>instanceJeu.getNombreJoueurs()+1 || instanceJeu.getJoueur(choixAccuse-1).getIdentiteAssociee().getDevoilee() == true || instanceJeu.getJoueur(choixAccuse-1)==instanceJeu.getEnTour() || instanceJeu.getJoueur(choixAccuse-1).isAccusable()==false) {
						
						//Pour les joueurs physiques
						if (instanceJeu.getEnTour() instanceof JoueurPhysique) {
							choixAccuse = saisieUtilisateur.nextInt();
						}
							//Pour les IAs
						else {
							choixAccuse = ((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirAccuse() + 1;
						}
						
						//Si le choix correspond � un joueur ciblable
						if (0<choixAccuse && choixAccuse<instanceJeu.getNombreJoueurs()+1 && instanceJeu.getJoueur(choixAccuse-1).getIdentiteAssociee().getDevoilee() == false && instanceJeu.getJoueur(choixAccuse-1)!=instanceJeu.getEnTour() && instanceJeu.getJoueur(choixAccuse-1).isAccusable()==true) {
							//R�initialisation de la variable accusable (si ce n'est pas encore fait) 
							for (int i=0 ; i<instanceJeu.getNombreJoueurs() ; i++) {
								if (instanceJeu.getJoueur(i).isAccusable()==false) {
									instanceJeu.getJoueur(i).setAccusable(true);
								}
							}
							
							//On d�finit le joueur accus� dans le jeu
							instanceJeu.setAccused(instanceJeu.getJoueur(choixAccuse-1));
							
							//On appelle la m�thode d'un joueur accus� (elle retourne le prochain joueur, ce qui d�pend des futurs choix de l'accus�)
							prochainJoueur = instanceJeu.getJoueur(choixAccuse-1).estAccuse();
							
							return prochainJoueur;
						}
						//Si le choix ne correspond pas � un joueur ciblable
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
					//Appelle de la m�thode de jeu d'une carte rumeur, renvoie le prochain joueur (ce qui d�pend des choix du joueur en tour)
					return instanceJeu.getEnTour().jouerCarteHunt();
				}
				else {
					//La boucle while va se r�effectuer avec choix =1, donc une accusation
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
