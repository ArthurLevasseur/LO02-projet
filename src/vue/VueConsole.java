package vue;

import modele.*;

import java.util.Scanner;

import controleur.*;

public class VueConsole implements Vue {

	private ControlerCLI controleur = new ControlerCLI();

	public void demanderNombreJoueursPhysiques() {

		SaisirInt scan = SaisirInt.getInstance();
		System.out.println("Combien de joueurs physiques êtes-vous? (entre 1 et 6)");
		int choix = -1;
		while (choix < 0 || choix > 6) {
			choix = scan.nextInt();
			if (choix < 0 || choix > 6) {
				System.out.println("Veuillez choisir entre 1 et 6 joueurs");
			} else {
				controleur.selectionnerNombreJoueursPhysiques(choix);
			}
		}
	}

	public void demarrerJeu() {
		SaisirInt scan = SaisirInt.getInstance();

		System.out.println("Bienvenue dans Witch Hunt !");
		int choix = 0;
		while (choix != 2) {
			System.out.println("Que voulez vous faire (entrez l'indice de vos choix) : \n1) Lancer une nouvelle partie \n2) Quitter le programme");
			choix = scan.nextInt();
			if (choix == 1) {
				controleur.demarrerJeu();
			} else if (choix == 2) {
				controleur.stopJeu();
			} else {
				System.out.println("veuillez choisir entre 1 et 2 !\n");
			}

		}
	}

	public void demanderNombreJoueursVirtuels() {
		Jeu instanceJeu = Jeu.getInstance();
		int choix;
		SaisirInt scan = SaisirInt.getInstance();
		System.out.println(
				"Combien de joueurs virtuels voulez vous dans votre partie ? (minimum 3 et maximum 6 joueurs physiques et virtuels en combinés)");
		choix = -1;
		while ((choix + instanceJeu.getNombrePhy() < 3 || choix + instanceJeu.getNombrePhy() > 6)
				|| (choix < 0 || choix > 6)) {
			choix = scan.nextInt();
			if ((choix + instanceJeu.getNombrePhy() < 3 || choix + instanceJeu.getNombrePhy() > 6)
					|| (choix < 0 || choix > 6)) {
				System.out.println("Choix invalide");
			} else {
				controleur.selectionnerNombreJoueursVirtuels(choix);
			}
		}
	}

	public void initialisationDeLaPartie() {
		Jeu instanceJeu = Jeu.getInstance();
		System.out.println("La partie va commencer, configuration : \n	- Nombre de joueurs physiques : "
				+ instanceJeu.getNombrePhy() + "\n	- Nombre de joueurs virtuels : " + instanceJeu.getNombreIA()
				+ "\n");
		System.out.println("Mélange des cartes...");
	}

	public void determinerNomDuJoueur(Joueur j) {
		Scanner saisiePseudo = new Scanner(System.in);
		System.out.println("Ecrire le pseudo du joueur");
		j.setPseudo(saisiePseudo.nextLine());
	}

	public void choisirIdentite(Joueur j) {

		if (j instanceof JoueurVirtuel) {
			int choix = (int)Math.random()*2;

			if (choix == 0) {
				j.getIdentiteAssociee().setWitch(false);
			}

			else if (choix == 1) {
				j.getIdentiteAssociee().setWitch(true);
			}

			j.getIdentiteAssociee().setDevoilee(false);

		}

		else {
			int choix = 0;
			SaisirInt saisieUtilisateur = SaisirInt.getInstance();
			System.out.println("\n---------------Votre identité----------------\n");
			if (j.getIdentiteAssociee().getIsWitch()) {System.out.print("Vous êtes une Witch ");} else {System.out.print("Vous êtes un Villager ");};
			if (j.getIdentiteAssociee().getDevoilee()) {System.out.println("devoilé.\n");} else {System.out.println("encore en round.\n");};
			System.out.println("-----------------Votre main------------------\n");
			for (int i=0; i<j.getCarteEnMain().size(); i++) {System.out.println("Carte " + i + " : " +j.getCarteEnMain().get(i).getNomCarte() +"\n");};
			if (j.getCarteEnMain().isEmpty()) {System.out.println("Aucunes cartes\n");}
			System.out.println("-------------Vos cartes révélées-------------\n");
			for (int i=0; i<j.getCarteRevelees().size(); i++) {System.out.println("Carte " + i + " : " +j.getCarteRevelees().get(i).getNomCarte() +"\n");};
			if (j.getCarteRevelees().isEmpty()) {System.out.println("Aucunes cartes\n");}

			while (choix <= 0 || choix >= 3) {
				System.out.println("Joueur " + j.getPseudo() + " : Choisissez votre identité.\n\n1) Villageois.\n2) Sorcière.\n");
				choix = saisieUtilisateur.nextInt();
				if (choix <=0 || choix >= 3) {
					System.out.println("Choix invalide ! veuillez choisir entre 1 et 2.");
				}
			}

			controleur.attribuerIdentite(j, choix);
		}

	}

	public void debutTour() {
		Jeu instanceJeu = Jeu.getInstance();

		System.out.println("\n---------------Votre identité----------------\n");
		if (instanceJeu.getEnTour().getIdentiteAssociee().getIsWitch()) {
			System.out.print("Vous êtes une Witch ");
		} else {
			System.out.print("Vous êtes un Villager ");
		}
		;
		if (instanceJeu.getEnTour().getIdentiteAssociee().getDevoilee()) {
			System.out.println("devoilé.\n");
		} else {
			System.out.println("encore en round.\n");
		}
		;
		System.out.println("-----------------Votre main------------------\n");
		for (int i = 0; i < instanceJeu.getEnTour().getCarteEnMain().size(); i++) {
			System.out.println(
					"Carte " + i + " : " + instanceJeu.getEnTour().getCarteEnMain().get(i).getNomCarte() + "\n");
		}
		;
		if (instanceJeu.getEnTour().getCarteEnMain().isEmpty()) {
			System.out.println("Aucunes cartes\n");
		}
		System.out.println("-------------Vos cartes révélées-------------\n");
		for (int i = 0; i < instanceJeu.getEnTour().getCarteRevelees().size(); i++) {
			System.out.println(
					"Carte " + i + " : " + instanceJeu.getEnTour().getCarteRevelees().get(i).getNomCarte() + "\n");
		}
		;
		if (instanceJeu.getEnTour().getCarteRevelees().isEmpty()) {
			System.out.println("Aucunes cartes\n");
		}

		// Création de la boucle de choix du joueur
		int choix = 0;
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();
		while (choix != 2 && choix != 1) {

			// Si le joueur est ciblé par la carte "Mauvais oeil", son choix est
			// automatiquement d'accuser un autre joueur.
			if (instanceJeu.getEnTour().isMustAccuse() == true) {
				System.out.println("Vous ne pouvez pas utiliser de carte rumeur ce tour-ci, vous devez accuser un joueur.");
				choix = 1;
			}
			// Choix du joueur
			else {
				System.out.println("Que voulez vous faire?\n\n1) Accuser un autre joueur.\n2) Jouer une carte Rumeur (effet Hunt!)\n");

				// Pour un joueur physique
				if (instanceJeu.getEnTour() instanceof JoueurPhysique) {
					choix = saisieUtilisateur.nextInt();
				}

				// Pour une IA
				else {
					choix = ((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirActionTour();
				}

			}

			if (choix == 1) {
				controleur.accuser();
			}
			else if (choix == 2) {
				controleur.jouerHunt();
			}


		}
		
	}


	public void choixAccuse() {

		SaisirInt scan = SaisirInt.getInstance();
		Jeu instanceJeu = Jeu.getInstance();
		// Le joueur décide d'accuser.
		System.out.println("Qui voulez vous accuser?\n");
		int compteur = 0;

		// Affichage des joueurs ciblables.
		for (int i = 1; i < instanceJeu.getNombreJoueurs() + 1; i++) {
			if (instanceJeu.getJoueur(i - 1).getIdentiteAssociee().getDevoilee() == false
					&& instanceJeu.getJoueur(i - 1) != instanceJeu.getEnTour()
					&& instanceJeu.getJoueur(i - 1).isAccusable() == true) {
				compteur += 1;
				System.out.println("Joueur " + (i) + ") " + instanceJeu.getJoueur(i - 1).getPseudo()
						+ " (points : " + instanceJeu.getJoueur(i - 1).getPoints() + ")\n");
			}
		}
		// Dans le cas de 2 derniers joueurs en round et l'un d'eux n'est pas accusable
		if (compteur == 0) {
			// Réinitialisation de la variable accusable
			for (int i = 0; i < instanceJeu.getNombreJoueurs(); i++) {
				if (instanceJeu.getJoueur(i).isAccusable() == false) {
					instanceJeu.getJoueur(i).setAccusable(true);
				}
			}
			System.out.println("Vous ne pouvez accuser personne.");

			// Dans le cas où ne joueur, en plus n'aurait pas de cartes en main.
			if (!instanceJeu.getEnTour().getCarteEnMain().isEmpty()) {
				System.out.println("Vous devez donc utiliser un effet Hunt.");
				int choix = 2;
			} else {
				System.out.println("Vous n'avez pas non plus de cartes dans votre main, par défaut, l'effet de la carte \"Mauvais Oeil\" s'estompe.");
			}
		}

		int choixAccuse=-1;
		//Création de la boucle de choix d'accusation du joueur
		while (0>choixAccuse || choixAccuse>instanceJeu.getNombreJoueurs()+1 || instanceJeu.getJoueur(choixAccuse-1).getIdentiteAssociee().getDevoilee() == true || instanceJeu.getJoueur(choixAccuse-1)==instanceJeu.getEnTour() || instanceJeu.getJoueur(choixAccuse-1).isAccusable()==false) {

			//Pour les joueurs physiques
			if (instanceJeu.getEnTour() instanceof JoueurPhysique) {
				choixAccuse = scan.nextInt();
			}
			//Pour les IAs
			else {
				choixAccuse = ((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirAccuse() + 1;
			}

		}

		//En situation habituelle (si après les vérification, le choix reste d'accuser un joueur):

			//Si le choix correspond à un joueur ciblable
			if (0<choixAccuse && choixAccuse<instanceJeu.getNombreJoueurs()+1 && instanceJeu.getJoueur(choixAccuse-1).getIdentiteAssociee().getDevoilee() == false && instanceJeu.getJoueur(choixAccuse-1)!=instanceJeu.getEnTour() && instanceJeu.getJoueur(choixAccuse-1).isAccusable()==true) {
				//Réinitialisation de la variable accusable (si ce n'est pas encore fait) 
				for (int i=0 ; i<instanceJeu.getNombreJoueurs() ; i++) {
					if (instanceJeu.getJoueur(i).isAccusable()==false) {
						instanceJeu.getJoueur(i).setAccusable(true); // à modif ici
					}
				}
				
				controleur.validerAccusation(choixAccuse);

				
			}
			//Si le choix ne correspond pas à un joueur ciblable
			else {
				System.out.println("Choix invalide !");
				choixAccuse = -1;
				instanceJeu.getVueActuelle().choixAccuse();

			}


	}
	
	public void jouerHunt() {
		Jeu instanceJeu = Jeu.getInstance();
		boolean peutJouerCarte = false;
		
		for (int i=0; i<instanceJeu.getEnTour().getCarteEnMain().size(); i++) {
			if (((instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() != 1 && instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() != 2) || ((instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() == 1 || instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() == 2)) && instanceJeu.getEnTour().getIdentiteAssociee().getDevoilee() == true) && (instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() != 3 || (instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() == 3 && !(instanceJeu.getEnTour().getCarteRevelees().isEmpty())))) {
				peutJouerCarte = true;
			}
		}
		if (peutJouerCarte == true) {
			//Appelle de la méthode de jeu d'une carte rumeur, renvoie le prochain joueur (ce qui dépend des choix du joueur en tour)
			//instanceJeu.setEnTour(instanceJeu.getEnTour().jouerCarteHunt());
			instanceJeu.getEnTour().jouerCarteHunt();
		}
		else {
			//La boucle while va se réeffectuer avec choix =1, donc une accusation
			System.out.println("Vous ne pouvez pas jouer de cartes hunts !");
			this.debutTour();
		}
	}
	
	public void choisirHunt(Joueur j) {
		
		int choix;
		
		if (j.isIA()) {
			System.out.println(j.getPseudo() + " choisit sa carte à jouer");
			choix = (int)(Math.random() * j.getCarteEnMain().size());
			while (choix<0 || choix > j.getCarteEnMain().size()-1 || ((j.getCarteEnMain().get(choix).getNumCarte() == 2 || j.getCarteEnMain().get(choix).getNumCarte() == 1) && (j.getIdentiteAssociee().getIsWitch() == true || j.getIdentiteAssociee().getDevoilee() == false)) || (j.getCarteEnMain().get(choix).getNumCarte() == 3 && j.getCarteRevelees().isEmpty())) {
				choix = (int)(Math.random() * j.getCarteEnMain().size());
			}
			System.out.println(j.getPseudo() + " a décidé de jouer la carte " + j.getCarteEnMain().get(choix).getNomCarte());
		}
		else {
			System.out.println("Choisissez la carte que vous souhaitez jouer. \n");
			//this.carteEnMain.forEach(card -> System.out.println("TAPEZ "+this.carteEnMain.indexOf(card) + " pour jouer " + card));
			
			for(CarteRumeur card : j.getCarteEnMain()) {
				if ((card.getNumCarte()==1 || card.getNumCarte()==2) && (j.getIdentiteAssociee().getIsWitch() == true || j.getIdentiteAssociee().getDevoilee() == false)) {
					System.out.println("PAS JOUABLE : "+ card + "(Vous n'êtes pas dévoilé en tant que villageois)\n");
				}
				
				else if (card.getNumCarte() == 3 && j.getCarteRevelees().isEmpty()) {
					System.out.println("PAS JOUABLE : "+ card + "(aucune de vos cartes n'est dévoilée)\n");
				}
				
				
				else {
					System.out.println("TAPEZ "+j.getCarteEnMain().indexOf(card) + " pour jouer " + card);
				}
			}
			
			SaisirInt saisieUtilisateur = SaisirInt.getInstance();
			choix = saisieUtilisateur.nextInt();
			while (choix<0 || choix > j.getCarteEnMain().size()-1 || ((j.getCarteEnMain().get(choix).getNumCarte() == 2 || j.getCarteEnMain().get(choix).getNumCarte() == 1) && (j.getIdentiteAssociee().getIsWitch() == true || j.getIdentiteAssociee().getDevoilee() == false)) || (j.getCarteEnMain().get(choix).getNumCarte() == 3 && j.getCarteRevelees().isEmpty())) {
				System.out.println("Choix Incompatible !");	
				choix = saisieUtilisateur.nextInt();
			}
			
		}
		
		controleur.choisirHunt(j, choix);
		
	}
	
	public void repondreAccusation(Joueur j) {
		
		Jeu instanceJeu = Jeu.getInstance();
		
		System.out.println("Joueur " + j.getPseudo() + ", on vous accuse, que voulez vous faire?\n\n1) Révéler votre identité.\n2) Jouer une carte rumeur (effet witch?).");
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();
		int choix=0;
		while (choix!=1 && choix!=2) {

			choix = saisieUtilisateur.nextInt();
			
			if (choix==2) {
				if (j.getCarteEnMain().isEmpty() || (j.getCarteEnMain().size()==1 && j.getCarteEnMain().get(0).getNumCarte() == 3 && j.getCarteRevelees().isEmpty())) {
					System.out.println("Vous ne pouvez pas jouer de cartes rumeurs !");
				}
				else {
					controleur.jouerCarteWitch(j);
				}
			}

			else if (choix==1) {
				controleur.revelerIdentite(j);
			}
			
			else {
				System.out.println("Choix invalide !");
			}
		}
	}
	
	public void jouerWitch() {
		
		Jeu instanceJeu = Jeu.getInstance();
		int choix = 0;
		
		if (instanceJeu.getAccused().isIA()) {
			System.out.println(instanceJeu.getAccused().getPseudo() + " choisit sa carte à jouer");
			choix = (int)(Math.random() * instanceJeu.getAccused().getCarteEnMain().size());
			while (choix<0 || choix > instanceJeu.getAccused().getCarteEnMain().size() || (instanceJeu.getAccused().getCarteEnMain().get(choix).getNumCarte() == 3 && instanceJeu.getAccused().getCarteRevelees().isEmpty())) {
				choix = (int)(Math.random() * instanceJeu.getAccused().getCarteEnMain().size());
			}
		}
		else {
			System.out.println("\n---------------Votre identité----------------\n");
			if (instanceJeu.getAccused().getIdentiteAssociee().getIsWitch()) {System.out.print("Vous êtes une Witch ");} else {System.out.print("Vous êtes un Villager ");};
			if (instanceJeu.getAccused().getIdentiteAssociee().getDevoilee()) {System.out.println("devoilé.\n");} else {System.out.println("encore en round.\n");};
			System.out.println("-----------------Votre main------------------\n");
			for (int i=0; i<instanceJeu.getAccused().getCarteEnMain().size(); i++) {System.out.println("Carte " + i + " : " +instanceJeu.getAccused().getCarteEnMain().get(i).getNomCarte() +"\n");};
			if (instanceJeu.getAccused().getCarteEnMain().isEmpty()) {System.out.println("Aucunes cartes\n");}
			System.out.println("-------------Vos cartes révélées-------------\n");
			for (int i=0; i<instanceJeu.getAccused().getCarteRevelees().size(); i++) {System.out.println("Carte " + i + " : " +instanceJeu.getAccused().getCarteRevelees().get(i).getNomCarte() +"\n");};
			if (instanceJeu.getAccused().getCarteRevelees().isEmpty()) {System.out.println("Aucunes cartes\n");}
			System.out.println("Choisissez la carte que vous souhaitez jouer. \n");
			
			for(CarteRumeur card : instanceJeu.getAccused().getCarteEnMain()) {
				if (card.getNumCarte() == 3 && instanceJeu.getAccused().getCarteRevelees().isEmpty()) {
					System.out.println("PAS JOUABLE : "+ card + "(aucune de vos cartes n'est dévoilée)\n");
				}
				
				else {
					System.out.println("TAPEZ "+instanceJeu.getAccused().getCarteEnMain().indexOf(card) + " pour jouer " + card);
				}
			}
			
			SaisirInt saisieUtilisateur = SaisirInt.getInstance();
			choix = saisieUtilisateur.nextInt();
			while (choix<0 || choix > instanceJeu.getAccused().getCarteEnMain().size()-1 || (instanceJeu.getAccused().getCarteEnMain().get(choix).getNumCarte() == 3 && instanceJeu.getAccused().getCarteRevelees().isEmpty())) {
				System.out.println("Choix Incompatible !");	
				choix = saisieUtilisateur.nextInt();
			}
		}
		
		controleur.choisirWitch(instanceJeu.getAccused(), choix);
		
	}
	
}