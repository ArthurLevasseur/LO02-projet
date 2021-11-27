import java.util.Scanner;

public class Witch{

	private String effet;
	
	public Witch(int numCarte) {
		if (numCarte == 1) {
			this.effet = "Vous prenez le prochain tour.";
		}
		else if (numCarte == 2) {
			this.effet = "Vous défaussez une carte de votre main.\nVous prenez le prochain tour.";
		}
		else if (numCarte == 3) {
			this.effet = "Vous reprenez une de vos propres cartes rumeurs déjà révélées dans votre main.\nVous prenez le prochain tour.";
		}
		else if (numCarte == 4) {
			this.effet = "Vous prenez une carte de la main du joueur qui vous a accusé.\nVous prenez le prochain tour.";
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
			this.effet = "Le joueur qui vous a accusé défausse une carte aléatoire de sa main.\nVous prenez le prochain tour.";
		}
		else if (numCarte == 9) {
			this.effet = "Vous choisissez le prochain joueur.\nDurant son tour, le joueur ciblé devra accuser un joueur autre que vous, si possible.";
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

		System.out.println("Effet appliqué == " + effet); // à retirer par la suite
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		
		if (numCarte == 1 || numCarte == 10 || numCarte == 11 || numCarte == 12) {
			return instanceJeu.getEnTour();
		}
		else if (numCarte == 2) {
			instanceJeu.getEnTour().carteEnMain.forEach(card -> System.out.println("TAPEZ "+instanceJeu.getEnTour().carteEnMain.indexOf(card) + " pour défausser " + card));
			Scanner saisieUtilisateur = new Scanner(System.in);
			int choix = saisieUtilisateur.nextInt();
			CarteRumeur carteADefausser = instanceJeu.getEnTour().carteEnMain.get(choix);
			instanceDefausse.defausserUneCarte(carteADefausser);
			return instanceJeu.getEnTour();
		}
		else if (numCarte == 3) {
			
		}
		else if (numCarte == 4) {
			
		}
		else if (numCarte == 5) {
			
		}
		else if (numCarte == 6) {
			
		}
		else if (numCarte == 7) {
			Joueur choix = instanceJeu.selectionnerAdversaire("Choisissez le prochain joueur.");
			return choix;			
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
		str.append("effet Witch? : " + this.effet);
		return str.toString();
	}
}
