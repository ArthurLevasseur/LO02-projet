import java.util.Scanner;

public class Hunt{

	private String effet;
	
	public Hunt(int numCarte) {
		if (numCarte == 1) {
			this.effet = "Vous r�v�lez l'identit� d'un autre joueur.\n	- Si c'est une sorci�re, vous gagnez 2 points et prenez le prochain tour.\n	- Si c'est un villageois, vous perdez 2 points et il prend le prochain tour.";
		}
		else if (numCarte == 2) {
			this.effet = "Vous choisissez le prochain joueur.\nAvant son tour, vous regardez secr�tement l'identit� du joueur";
		}
		else if (numCarte == 3) {
			this.effet = "Vous reprenez une de vos cartes rumeurs d�j� r�v�l�es dans votre main.\nVous choisissez le prochain joueur.";
		}
		else if (numCarte == 4) {
			this.effet = "Vous choisissez le prochain joueur.\nAvant son tour, vous prenez une carte rumeur de sa main et l'ajoutez dans la votre.";
		}
		else if (numCarte == 5) {
			this.effet = "Vous choisissez le prochain joueur.";
		}
		else if (numCarte == 6) {
			this.effet = "Vous choisissez le prochain joueur.";
		}
		else if (numCarte == 7) {
			this.effet = "Vous choisissez un joueur, il doit r�v�ler son identit� ou d�fausser une de ses cartes en main.\n	- Si c'est une sorci�re, vous gagnez 1 points et prenez le prochain tour.\n	- Si c'est un villageois, vous perdez 1 points et il prend le prochain tour.\n	- S'il d�cide de d�fausser une carte, il prend le prochain tour.";
		}
		else if (numCarte == 8) {
			this.effet = "Vous r�v�lez votre identit�.\n	- Si vous �tes une sorci�re, le joueur � votre gauche prend le prochain tour.\n	- Si vous �tes un villageois, vous choisissez le prochain joueur.";
		}
		else if (numCarte == 9) {
			this.effet = "Vous choisissez le prochain joueur.\nDurant son tour, le joueur cibl� devra accuser un joueur autre que vous, si possible.";
		}
		else if (numCarte == 10) {
			this.effet = "Vous r�v�lez votre identit�.\n	- Si vous �tes une sorci�re, le joueur � votre gauche prend le prochain tour.\n	- Si vous �tes un villageois, vous choisissez le prochain joueur.";
		}
		else if (numCarte == 11) {
			this.effet = "Vous ajoutez une des cartes d�fauss�es � votre main, et vous vous d�faussez de cette carte.\nVous prenez le prochain tour";
		}
		else if (numCarte == 12) {
			this.effet = "Prenez une carte rumeur r�v�l�e de n'importe quel joueur dans votre main.\nVous choisissez le prochain joueur.";
		}
	}
	
	public Joueur executerEffet(int numCarte) {
		System.out.println("Effet appliqu� == " + effet); // � retirer par la suite
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		
		if (numCarte == 1) {
			
			// AJOUTER CONDITION
			
			//Joueur choix = null;
			boolean visable = true;
			/*do {
				choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"De quel joueur souhaitez vous r�veler l'identit� ?");
				for(CarteRumeur carte : choix.carteRevelees) {
					if (carte.getNumCarte() == 5) {
						System.out.println("Ce joueur a la carte Manche � balai r�vel�e et ne peut pas �tre vis� par cette carte.");
						visable = false;
					}
				};
			}while(!visable);*/
			
			Scanner saisieUtilisateur = new Scanner(System.in);
			System.out.println("De quel joueur souhaitez vous r�veler l'identit� ?");
			Joueur selection = null;
			for (int i=1 ; i<instanceJeu.getNombreJoueurs()+1 ; i++) {
				visable = true;
				for(CarteRumeur carte : instanceJeu.getJoueur(i-1).carteRevelees) {
					if (carte.getNumCarte() == 5) {
						visable = false;
					}
				}
				if (instanceJeu.getJoueur(i-1).identiteAssociee.getDevoilee() == false && instanceJeu.getJoueur(i-1)!=instanceJeu.getEnTour() && visable == true) {
					//i += 1;
					System.out.println("Joueur " + (i) + ") " + instanceJeu.getJoueur(i-1).pseudo + " (points : " + instanceJeu.getJoueur(i-1).getPoints() + ")");
				}
			}
			
			int choix = -1;
			
			while (choix<0 || choix>instanceJeu.getNombreJoueurs()) {
				choix = saisieUtilisateur.nextInt();
				if (0<choix && choix<instanceJeu.getNombreJoueurs()+1) {
					selection = instanceJeu.getJoueur(choix-1);
				}
			}
			
			if (selection.identiteAssociee.getIsWitch() == true) {
				System.out.println("Ce joueur est bien une Witch, vous remportez 2 points.");
				instanceJeu.getEnTour().ajouterPoints(2);
				selection.identiteAssociee.ReveleIdentite();
				return instanceJeu.getEnTour();
			}
			else {
				System.out.println("Ce joueur est un villager, vous perdez 2 points.");
				instanceJeu.getEnTour().ajouterPoints(-2);
				selection.identiteAssociee.ReveleIdentite();
				return selection;
			}
			
			
		}
			
		else if (numCarte == 2) {
			// AJOUTER CONDITION
			Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur, son identit� sera secr�tement r�vel�e.");
			if (choix.identiteAssociee.getIsWitch() == true) {
				System.out.println("Ce joueur est une witch.");
			}
			else {
				System.out.println("Ce joueur est un villager.");
			}
			return choix;
		}
		else if (numCarte == 3) {
			if (instanceJeu.getEnTour().carteRevelees == null) {
				System.out.println("Votre tas de cartes rumeurs r�v�l�es est vide !");
			}
			else {
				System.out.println("Voici vos cartes rumeurs r�v�l�es, choisissez la carte que vous voulez reprendre :");
				instanceJeu.getEnTour().carteRevelees.forEach(card -> System.out.println("TAPEZ "+instanceJeu.getEnTour().carteRevelees.indexOf(card) + " pour jouer " + card));
				Scanner saisieUtilisateur = new Scanner(System.in);
				int choix = saisieUtilisateur.nextInt();
				while (choix<0 || choix>instanceJeu.getEnTour().carteRevelees.size()) {
					System.out.println("Choix invalide !");
					choix = saisieUtilisateur.nextInt();
				}
				instanceJeu.getEnTour().prendreCarteRumeur(instanceJeu.getEnTour().carteRevelees.get(choix));
			}
			
			return instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
			
			
		}
		else if (numCarte == 4) {
			Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur. Vous prenez une carte de sa main.");
			int random = (int)(Math.random() * (choix.carteEnMain.size()+ 1));
			CarteRumeur carteVolee = choix.carteEnMain.get(random);
			choix.carteEnMain.remove(random);
			instanceJeu.getEnTour().carteEnMain.add(carteVolee);
			System.out.println("Vous avez vol� : ");
			System.out.println(carteVolee);
			return choix;
		}
		else if (numCarte == 5 || numCarte == 6) {
			Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
			return choix;
		}
		
		else if (numCarte == 7) {
			Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez un joueur. Il devra r�v�ler son identit� OU d�fausser une de ses cartes rumeurs");
			return choix.accusedBucher();
		}
		else if (numCarte == 8) {
			instanceJeu.getEnTour().identiteAssociee.ReveleIdentite();
			if (instanceJeu.getEnTour().identiteAssociee.getIsWitch() == true) {
				System.out.println("Vous etiez une sorci�re. Le prochain joueur sera celui � votre gauche.");
				int i = 0;
				for (i=0; i<instanceJeu.getEnsembleJoueurs().length; i++) {
					if (instanceJeu.getJoueur(i) == instanceJeu.getEnTour()) {
						if (i == instanceJeu.getEnsembleJoueurs().length-1) {
							return instanceJeu.getJoueur(0);
						}
						else {return instanceJeu.getJoueur(i+1);}
					}
				}
			}
			else {
				System.out.println("Vous etiez un villageois.");
				return instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
			}
		}
		else if (numCarte == 9) {
			Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getAccused(),"Choisissez le prochain joueur.");
			choix.setMustAccuse(true);
			instanceJeu.getEnTour().setAccusable(false);
			return choix;
		}
		else if (numCarte == 10) {
			instanceJeu.getEnTour().identiteAssociee.ReveleIdentite();
			if (instanceJeu.getEnTour().identiteAssociee.getIsWitch() == true) {
				System.out.println("Vous etiez une sorci�re. Le prochain joueur sera celui � votre gauche.");
				int i = 0;
				for (i=0; i<instanceJeu.getEnsembleJoueurs().length; i++) {
					if (instanceJeu.getJoueur(i) == instanceJeu.getEnTour()) {
						if (i == instanceJeu.getEnsembleJoueurs().length-1) {
							return instanceJeu.getJoueur(0);
						}
						else {return instanceJeu.getJoueur(i+1);}
					}
				}
			}
			else {
				System.out.println("Vous etiez un villageois.");
				return instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
			}
		}
		else if (numCarte == 11) {
			if (instanceDefausse.getContenu() == null) {
					System.out.println("Le tas de d�fausse est vide !");
				}
			else {
				System.out.println("Voici les cartes rumeurs de la d�fausse, choisissez la carte que vous voulez prendre :");
				instanceDefausse.getContenu().forEach(card -> System.out.println("TAPEZ "+instanceDefausse.getContenu().indexOf(card) + " pour jouer " + card));
				Scanner saisieUtilisateur = new Scanner(System.in);
				int choix = saisieUtilisateur.nextInt();
				while (choix<0 || choix>instanceDefausse.getContenu().size()) {
					System.out.println("Choix invalide !");
					choix = saisieUtilisateur.nextInt();
				}
				instanceJeu.getEnTour().prendreCarteRumeur(instanceDefausse.seFairePrendreCarteRumeur(choix));
				
			}
			return instanceJeu.getEnTour();
		}
		else if (numCarte == 12) {
			Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"De quel joueur souhaitez vous voler une carte rumeur r�v�l�e ?");
			if (instanceJeu.getEnTour().carteRevelees == null) {
				System.out.println("Son tas de cartes rumeurs r�v�l�es est vide, dommage !");
			}
			else {
				System.out.println("Voici ses cartes rumeurs r�v�l�es, choisissez la carte que vous voulez reprendre :");
				choix.carteRevelees.forEach(card -> System.out.println("TAPEZ "+choix.carteRevelees.indexOf(card) + " pour jouer " + card));
				Scanner saisieUtilisateur = new Scanner(System.in);
				int choixCarte = saisieUtilisateur.nextInt();
				while (choixCarte<0 || choixCarte>choix.carteRevelees.size()) {
					System.out.println("Choix invalide !");
					choixCarte = saisieUtilisateur.nextInt();
				}
				instanceJeu.getEnTour().prendreCarteRumeur(choix.carteRevelees.get(choixCarte));
			}
			
			return instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
		}
		
		return null;
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("effet Hunt! : " + this.effet);
		return str.toString();
	}
}
