package vue;

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
			}
			else {
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
			System.out.println("Que voulez vous faire (entrez l'indice de vos choix) : \n1) Lancer une nouvelle partie \n2) Quitter le programme");
			choix = scan.nextInt();
			if (choix == 1) {
				
				return choix;
			}
			else if (choix == 2) {
				/*break;*/
				return choix;
			}
			else {
				System.out.println("veuillez choisir entre 1 et 2 !\n");
			}
			
		}
		
		return 1;
	}
	
	public int demanderNombreJoueursVirtuels() {
		Jeu instanceJeu = Jeu.getInstance();
		int choix;
		SaisirInt scan = SaisirInt.getInstance();
		System.out.println("Combien de joueurs virtuels voulez vous dans votre partie ? (minimum 3 et maximum 6 joueurs physiques et virtuels en combinés)");
		choix = -1;
		while ((choix + instanceJeu.getNombrePhy() < 3 || choix + instanceJeu.getNombrePhy() > 6) || (choix < 0 || choix > 6)) {
			choix = scan.nextInt();
			if ((choix + instanceJeu.getNombrePhy() < 3 || choix + instanceJeu.getNombrePhy() > 6) || (choix < 0 || choix > 6)) {
				System.out.println("Choix invalide");
			}
			else {
				return choix;
			}
		}
		return 2;
	}
	
}
