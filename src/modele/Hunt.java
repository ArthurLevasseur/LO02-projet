package modele;
import effets.EffetH1;
import effets.EffetH10;
import effets.EffetH11;
import effets.EffetH12;
import effets.EffetH2;
import effets.EffetH3;
import effets.EffetH4;
import effets.EffetH5;
import effets.EffetH7;
import effets.EffetH8;
import effets.EffetH9;

public class Hunt{

	private Effet effetAssocie;
	
	public Hunt(int numCarte) {
		if (numCarte == 1) {
			this.effetAssocie = new EffetH1();
		}
		else if (numCarte == 2) {
			this.effetAssocie = new EffetH2();
		}
		else if (numCarte == 3) {
			this.effetAssocie = new EffetH3();
		}
		else if (numCarte == 4) {
			this.effetAssocie = new EffetH4();
		}
		else if (numCarte == 5) {
			this.effetAssocie = new EffetH5();
		}
		else if (numCarte == 6) {
			this.effetAssocie = new EffetH5();
		}
		else if (numCarte == 7) {
			this.effetAssocie = new EffetH7();
		}
		else if (numCarte == 8) {
			this.effetAssocie = new EffetH8();
		}
		else if (numCarte == 9) {
			this.effetAssocie = new EffetH9();
		}
		else if (numCarte == 10) {
			this.effetAssocie = new EffetH10();
		}
		else if (numCarte == 11) {
			this.effetAssocie = new EffetH11();
		}
		else if (numCarte == 12) {
			this.effetAssocie = new EffetH12();
		}
	}
	
	public Joueur executerEffet(int numCarte) {
		
		return this.effetAssocie.executionEffet();
		
		/*System.out.println("Effet appliqué == " + effet); // à retirer par la suite
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		
		if (numCarte == 1) {
			
			// AJOUTER CONDITION
			
			//Joueur choix = null;
			
			
			
			boolean visable = true;
			Scanner saisieUtilisateur = new Scanner(System.in);
			System.out.println("De quel joueur souhaitez vous réveler l'identité ?");
			Joueur selection = null;
			for (int i=1 ; i<instanceJeu.getNombreJoueurs()+1 ; i++) {
				visable = true;
				for(CarteRumeur carte : instanceJeu.getJoueur(i-1).getCarteRevelees()) {
					if (carte.getNumCarte() == 5) {
						visable = false;
					}
				}
				if (instanceJeu.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false && instanceJeu.getJoueur(i-1)!=instanceJeu.getEnTour() && visable == true) {
					
					System.out.println("Joueur " + (i) + ") " + instanceJeu.getJoueur(i-1).pseudo + " (points : " + instanceJeu.getJoueur(i-1).getPoints() + ")");
				}
			}
			
			int choix = -1;
			
			while (choix<0 || choix>instanceJeu.getNombreJoueurs() || instanceJeu.getJoueur(choix).getIdentiteAssociee().getDevoilee() == true || instanceJeu.getJoueur(choix)==instanceJeu.getEnTour()) {
				choix = saisieUtilisateur.nextInt();
				if (0<choix && choix<instanceJeu.getNombreJoueurs()+1 ) {
					selection = instanceJeu.getJoueur(choix-1);
				}
			}
			
			if (selection.getIdentiteAssociee().getIsWitch() == true) {
				System.out.println("Ce joueur est bien une Witch, vous remportez 2 points.");
				instanceJeu.getEnTour().ajouterPoints(2);
				selection.getIdentiteAssociee().ReveleIdentite();
				return instanceJeu.getEnTour();
			}
			else {
				System.out.println("Ce joueur est un villager, vous perdez 2 points.");
				instanceJeu.getEnTour().ajouterPoints(-2);
				selection.getIdentiteAssociee().ReveleIdentite();
				return selection;
			}
			
			
		}
			
		else if (numCarte == 2) {
			// AJOUTER CONDITION
			Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur, son identité sera secrètement révelée.");
			while (choix.getIdentiteAssociee().getDevoilee() == true) {
				choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choix incorrecte ! Choisissez un joueur dont l'identité n'a pas encore été révélée");
			}
			if (choix.getIdentiteAssociee().getIsWitch() == true) {
				System.out.println("Ce joueur est une witch.");
			}
			else {
				System.out.println("Ce joueur est un villager.");
			}
			return choix;
		}
		else if (numCarte == 3) {
			if (instanceJeu.getEnTour().getCarteRevelees() == null) {
				System.out.println("Votre tas de cartes rumeurs révélées est vide !");
			}
			else {
				System.out.println("Voici vos cartes rumeurs révélées, choisissez la carte que vous voulez reprendre :");
				instanceJeu.getEnTour().getCarteRevelees().forEach(card -> System.out.println("TAPEZ "+instanceJeu.getEnTour().getCarteRevelees().indexOf(card) + " pour jouer " + card));
				Scanner saisieUtilisateur = new Scanner(System.in);
				int choix = saisieUtilisateur.nextInt();
				while (choix<0 || choix>instanceJeu.getEnTour().getCarteRevelees().size()) {
					System.out.println("Choix invalide !");
					choix = saisieUtilisateur.nextInt();
				}
				instanceJeu.getEnTour().prendreCarteRumeur(instanceJeu.getEnTour().getCarteRevelees().get(choix));
			}
			
			return instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
			
			
		}
		else if (numCarte == 4) {
			Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur. Vous prenez une carte de sa main.");
			int random = (int)(Math.random() * (choix.getCarteEnMain().size()+ 1));
			CarteRumeur carteVolee = choix.getCarteEnMain().get(random);
			choix.getCarteEnMain().remove(random);
			instanceJeu.getEnTour().getCarteEnMain().add(carteVolee);
			System.out.println("Vous avez volé : ");
			System.out.println(carteVolee);
			return choix;
		}
		else if (numCarte == 5 || numCarte == 6) {
			Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
			return choix;
		}
		
		else if (numCarte == 7) {
			//Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez un joueur. Il devra révéler son identité OU défausser une de ses cartes rumeurs");
			
			boolean visable;
			
			Scanner saisieUtilisateur = new Scanner(System.in);
			System.out.println("De quel joueur souhaitez vous réveler l'identité ?");
			Joueur selection = null;
			for (int i=1 ; i<instanceJeu.getNombreJoueurs()+1 ; i++) {
				visable = true;
				for(CarteRumeur carte : instanceJeu.getJoueur(i-1).getCarteRevelees()) {
					if (carte.getNumCarte() == 6) {
						visable = false;
					}
				}
				if (instanceJeu.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false && instanceJeu.getJoueur(i-1)!=instanceJeu.getEnTour() && visable == true) {
					//i += 1;
					System.out.println("Joueur " + (i) + ") " + instanceJeu.getJoueur(i-1).pseudo + " (points : " + instanceJeu.getJoueur(i-1).getPoints() + ")");
				}
			}
			
			int choix = -1;
			
			while (choix<0 || choix>instanceJeu.getNombreJoueurs() || (instanceJeu.getJoueur(choix).getIdentiteAssociee().getDevoilee() == true) || instanceJeu.getJoueur(choix) == instanceJeu.getEnTour()) {
				choix = saisieUtilisateur.nextInt();
				if (0<choix && choix<instanceJeu.getNombreJoueurs()+1 && instanceJeu.getJoueur(choix).getIdentiteAssociee().getDevoilee() == false && instanceJeu.getJoueur(choix) != instanceJeu.getEnTour()) {
					selection = instanceJeu.getJoueur(choix-1);
				}
			}
			
			
			return selection.accusedBucher();
		}
		else if (numCarte == 8) {
			instanceJeu.getEnTour().getIdentiteAssociee().ReveleIdentite();
			if (instanceJeu.getEnTour().getIdentiteAssociee().getIsWitch() == true) {
				System.out.println("Vous etiez une sorcière. Le prochain joueur sera celui à votre gauche.");
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
			instanceJeu.getEnTour().getIdentiteAssociee().ReveleIdentite();
			if (instanceJeu.getEnTour().getIdentiteAssociee().getIsWitch() == true) {
				System.out.println("Vous etiez une sorcière. Le prochain joueur sera celui à votre gauche.");
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
					System.out.println("Le tas de défausse est vide !");
				}
			else {
				System.out.println("Voici les cartes rumeurs de la défausse, choisissez la carte que vous voulez prendre :");
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
			Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"De quel joueur souhaitez vous voler une carte rumeur révélée ?");
			if (instanceJeu.getEnTour().getCarteRevelees() == null) {
				System.out.println("Son tas de cartes rumeurs révélées est vide, dommage !");
			}
			else {
				System.out.println("Voici ses cartes rumeurs révélées, choisissez la carte que vous voulez reprendre :");
				choix.getCarteRevelees().forEach(card -> System.out.println("TAPEZ "+choix.getCarteRevelees().indexOf(card) + " pour jouer " + card));
				Scanner saisieUtilisateur = new Scanner(System.in);
				int choixCarte = saisieUtilisateur.nextInt();
				while (choixCarte<0 || choixCarte>choix.getCarteRevelees().size()) {
					System.out.println("Choix invalide !");
					choixCarte = saisieUtilisateur.nextInt();
				}
				instanceJeu.getEnTour().prendreCarteRumeur(choix.getCarteRevelees().get(choixCarte));
			}
			
			return instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
		}
		
		return null;*/
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("effet Hunt! : " + this.effetAssocie.explication);
		return str.toString();
	}
}
