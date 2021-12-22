package vue;

import controleur.Jeu;
import modele.*;

public class VueConsole implements Vue {

	public int demanderNombreJoueursPhysiques() {

		SaisirInt scan = SaisirInt.getInstance();
		System.out.println("Combien de joueurs physiques êtes-vous? (entre 1 et 6)");
		int choix = -1;
		while (choix < 0 || choix > 6) {
			choix = scan.nextInt();
			if (choix < 0 || choix > 6) {
				System.out.println("Veuillez choisir entre 1 et 6 joueurs");
			} else {
				return choix;
			}
		}
		return 1;
	}

	public int demarrerJeu() {
		SaisirInt scan = SaisirInt.getInstance();

		System.out.println("Bienvenue dans Witch Hunt !");
		int choix = 0;
		while (choix != 2) {
			System.out.println(
					"Que voulez vous faire (entrez l'indice de vos choix) : \n1) Lancer une nouvelle partie \n2) Quitter le programme");
			choix = scan.nextInt();
			if (choix == 1) {

				return choix;
			} else if (choix == 2) {
				/* break; */
				return choix;
			} else {
				System.out.println("veuillez choisir entre 1 et 2 !\n");
			}

		}

		return 1;
	}

	public int demanderNombreJoueursVirtuels() {
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
				return choix;
			}
		}
		return 2;
	}

	public void initialisationDeLaPartie() {
		Jeu instanceJeu = Jeu.getInstance();
		System.out.println("La partie va commencer, configuration : \n	- Nombre de joueurs physiques : "
				+ instanceJeu.getNombrePhy() + "\n	- Nombre de joueurs virtuels : " + instanceJeu.getNombreIA()
				+ "\n");
		System.out.println("Mélange des cartes...");
	}

	public int debutTour() {
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
			;
			

		}
		return choix;
	}
	
	
	public int choixAccuse() {
		
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
				return choixAccuse = scan.nextInt();
			}
				//Pour les IAs
			else {
				return choixAccuse = ((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirAccuse() + 1;
			}

	}
	
	return 0;	
	
}
}