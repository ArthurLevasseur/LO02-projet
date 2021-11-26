
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
		
		if (numCarte == 1) {
			
			// AJOUTER CONDITION
			Joueur choix = instanceJeu.selectionnerAdversaire("De quel joueur souhaitez vous r�veler l'identit� ?");
			if (choix.identiteAssociee.getIsWitch() == true) {
				System.out.println("Ce joueur est bien une Witch, vous remportez 2 points.");
				instanceJeu.getEnTour().ajouterPoints(2);
				choix.identiteAssociee.ReveleIdentite();
				return instanceJeu.getEnTour();
			}
			else {
				System.out.println("Ce joueur est un villager, vous perdez 2 points.");
				instanceJeu.getEnTour().ajouterPoints(-2);
				choix.identiteAssociee.ReveleIdentite();
				return choix;
			}
			
			
		}
		else if (numCarte == 2) {
			// AJOUTER CONDITION
			Joueur choix = instanceJeu.selectionnerAdversaire("Choisissez le prochain joueur, son identit� sera secr�tement r�vel�e.");
			if (choix.identiteAssociee.getIsWitch() == true) {
				System.out.println("Ce joueur est une witch.");
			}
			else {
				System.out.println("Ce joueur est un villager.");
			}
			return choix;
		}
		else if (numCarte == 3) {
			
		}
		else if (numCarte == 4) {
			Joueur choix = instanceJeu.selectionnerAdversaire("Choisissez le prochain joueur. Vous prendez une carte de sa main.");
			int random = (int)(Math.random() * (choix.carteEnMain.size()+ 1));
			CarteRumeur carteVolee = choix.carteEnMain.get(random);
			choix.carteEnMain.remove(random);
			instanceJeu.getEnTour().carteEnMain.add(carteVolee);
			System.out.println("Vous avez vol� : ");
			System.out.println(carteVolee);
			return choix;
		}
		else if (numCarte == 5 || numCarte == 6) {
			Joueur choix = instanceJeu.selectionnerAdversaire("Choisissez le prochain joueur.");
			return choix;
		}
		
		else if (numCarte == 7) {
			
		}
		else if (numCarte == 8) {
			
		}
		else if (numCarte == 9) {
			
		}
		else if (numCarte == 10) {
			
		}
		else if (numCarte == 11) {
			
		}
		else if (numCarte == 12) {
			
		}
		
		return null;
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("effet Hunt! : " + this.effet);
		return str.toString();
	}
}
