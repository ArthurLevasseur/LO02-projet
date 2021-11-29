import java.util.Scanner;

public class Witch{

	private String effet;
	
	public Witch(int numCarte) {
		if (numCarte == 1) {
			this.effet = "Vous prenez le prochain tour.";
		}
		else if (numCarte == 2) {
			this.effet = "Vous d�faussez une carte de votre main.\nVous prenez le prochain tour.";
		}
		else if (numCarte == 3) {
			this.effet = "Vous reprenez une de vos propres cartes rumeurs d�j� r�v�l�es dans votre main.\nVous prenez le prochain tour.";
		}
		else if (numCarte == 4) {
			this.effet = "Vous prenez une carte de la main du joueur qui vous a accus�.\nVous prenez le prochain tour.";
		}
		else if (numCarte == 5) {
			this.effet = "Vous prenez le prochain tour.";
		}
		else if (numCarte == 6) {
			this.effet = "Vous prenez le prochain tour.";
		}
		else if (numCarte == 7) {
			this.effet = "Vous choisissez le prochain joueur.";
		}
		else if (numCarte == 8) {
			this.effet = "Le joueur qui vous a accus� d�fausse une carte al�atoire de sa main.\nVous prenez le prochain tour.";
		}
		else if (numCarte == 9) {
			this.effet = "Vous choisissez le prochain joueur.\nDurant son tour, le joueur cibl� devra accuser un joueur autre que vous, si possible.";
		}
		else if (numCarte == 10) {
			this.effet = "Vous prenez le prochain tour.";
		}
		else if (numCarte == 11) {
			this.effet = "Vous prenez le prochain tour.";
		}
		else if (numCarte == 12) {
			this.effet = "Vous prenez le prochain tour.";
		}
	}
	
	public Joueur executerEffet(int numCarte) {

		System.out.println("Effet appliqu� == " + effet); // � retirer par la suite
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		
		if (numCarte == 1 || numCarte == 5 || numCarte == 6 || numCarte == 10 || numCarte == 11 || numCarte == 12) {
			return instanceJeu.getAccused();
		}
		else if (numCarte == 2) {
			instanceJeu.getAccused().carteEnMain.forEach(card -> System.out.println("TAPEZ "+instanceJeu.getAccused().carteEnMain.indexOf(card) + " pour d�fausser " + card));
			Scanner saisieUtilisateur = new Scanner(System.in);
			int choix = saisieUtilisateur.nextInt();
			CarteRumeur carteADefausser = instanceJeu.getAccused().carteEnMain.get(choix);
			instanceDefausse.defausserUneCarte(carteADefausser);
			return instanceJeu.getAccused();
		}
		else if (numCarte == 3) {
			instanceJeu.getAccused().carteRevelees.forEach(card -> System.out.println("TAPEZ "+instanceJeu.getAccused().carteRevelees.indexOf(card) + " pour prendre " + card));
			Scanner saisieUtilisateur = new Scanner(System.in);
			int choix = saisieUtilisateur.nextInt();
			CarteRumeur carteARecuperer = instanceJeu.getAccused().carteRevelees.get(choix);
			instanceJeu.getAccused().prendreCarteRumeur(carteARecuperer);
			return instanceJeu.getAccused();
		}
		else if (numCarte == 4) {
			instanceJeu.getEnTour().carteEnMain.forEach(card -> System.out.println("TAPEZ "+instanceJeu.getEnTour().carteEnMain.indexOf(card) + " pour prendre " + card));
			Scanner saisieUtilisateur = new Scanner(System.in);
			int choix = saisieUtilisateur.nextInt();
			CarteRumeur carteARecuperer = instanceJeu.getEnTour().carteEnMain.get(choix);
			instanceJeu.getAccused().prendreCarteRumeur(carteARecuperer);
			return instanceJeu.getAccused();
		}

		else if (numCarte == 7) {
			Joueur choix = instanceJeu.selectionnerAdversaire("Choisissez le prochain joueur.");
			return choix;
		}
		else if (numCarte == 8) {
			Joueur choix = instanceJeu.selectionnerAdversaire("Choisissez le joueur � d�fausser.");
			instanceDefausse.defausserUneCarte(choix.seFairePrendreCarteRumeur((int)(Math.random()*choix.carteEnMain.size())));
		}
		else if (numCarte == 9) {
			Joueur choix = instanceJeu.selectionnerAdversaire("Choisissez le prochain joueur.");
			choix.setMustAccuse(true);
			instanceJeu.getAccused().setAccusable(false);
			return choix;
			
		}
		
		return null;
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("effet Witch? : " + this.effet);
		return str.toString();
	}
}
