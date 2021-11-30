import java.util.Scanner;
import java.util.*;

public abstract class Joueur {
	
	private int points = 0;
	private int id;
	//public CarteRumeur[] carteRevelees = new CarteRumeur[4];
	public ArrayList<CarteRumeur> carteRevelees = new ArrayList<CarteRumeur>();
	//public CarteRumeur[] carteEnMain = new CarteRumeur[4];
	public ArrayList<CarteRumeur> carteEnMain = new ArrayList<CarteRumeur>();
	private boolean accusable=true;
	private int nbCartesEnMain = 0;
	public Identite identiteAssociee;
	protected String pseudo;
	private boolean mustAccuse = false;
	
	public Joueur() {
		this.points = 0;
		this.accusable = true;
		this.identiteAssociee = new Identite();
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public void ajouterPoints(int pointsAjoutes) {
		this.points = this.points+pointsAjoutes;
	}
	
	public void gagnerPoints() {
		if (this.identiteAssociee.getIsWitch()==true) {
			this.points += 2;
			System.out.println("Bravo Joueur " + this.pseudo + " ! Vous avez gagné ce round en Witch ! Vous gagnez deux points");
		}
		else {
			this.points += 1;
			System.out.println("Bravo Joueur " + this.pseudo + " ! Vous avez gagné ce round en Villager ! Vous gagnez un points");
		}
	}
	
	public void revelerCarteRumeur() {
		
	}
	
	public boolean isIA() {
		if (this instanceof JoueurVirtuel) {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	public void prendreCarteRumeur(CarteRumeur carteAjoutee) {
		this.carteEnMain.add(carteAjoutee);
	}
	
	public CarteRumeur seFairePrendreCarteRumeur(int indexOfCarte) {
		CarteRumeur cartePrise = this.carteEnMain.get(indexOfCarte);
		this.carteEnMain.remove(indexOfCarte);
		return cartePrise;
	}
	
	public boolean accuser(Jeu Instance, int choix) {
		
		return true;
	}
	
	public Joueur estAccuse() {

		Jeu instanceJeu = Jeu.getInstance();

		instanceJeu.setEnTour(this);

		System.out.println("Joueur " + this.pseudo + ", on vous accuse, que voulez vous faire?\n\n1) Révéler votre identité.\n2) Jouer une carte rumeur (effet witch?).");
		Scanner saisieUtilisateur = new Scanner(System.in);
		int choix=0;
		while (choix!=1 && choix!=2) {

			if (instanceJeu.getEnTour() instanceof JoueurPhysique) {
				choix = saisieUtilisateur.nextInt();
			}
			else {
				choix = ((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().seDefendre();
			}


			if (choix==1) {
				return this.revelerIdentite();
			}
			else if (choix==2) {
				return this.jouerCarteWitch();
			}
			else {
				System.out.println("Choix invalide !");
			}
		}



		return instanceJeu.getEnTour();
	}
	
	public Joueur jouerCarteWitch() {
		System.out.println("Choisissez la carte que vous souhaitez jouer. \n");
		this.carteEnMain.forEach(card -> System.out.println("TAPEZ "+this.carteEnMain.indexOf(card) + " pour jouer " + card));
		Scanner saisieUtilisateur = new Scanner(System.in);
		int choix = saisieUtilisateur.nextInt();
		Joueur next = this.carteEnMain.get(choix).appliquerEffetWitch(this);
		this.carteRevelees.add(this.carteEnMain.get(choix));
		this.carteEnMain.remove(choix);
		return next;
	}
	
	public Joueur jouerCarteHunt() {
		System.out.println("Choisissez la carte que vous souhaitez jouer. \n");
		this.carteEnMain.forEach(card -> System.out.println("TAPEZ "+this.carteEnMain.indexOf(card) + " pour jouer " + card));
		Scanner saisieUtilisateur = new Scanner(System.in);
		int choix = saisieUtilisateur.nextInt();
		Joueur next = this.carteEnMain.get(choix).appliquerEffetHunt();
		this.carteRevelees.add(this.carteEnMain.get(choix));
		this.carteEnMain.remove(choix);
		return next;
	}
	
	public Joueur revelerIdentite() {
		Jeu instanceJeu = Jeu.getInstance();
		this.identiteAssociee.ReveleIdentite();
		if (this.identiteAssociee.getIsWitch() == true) {
			System.out.println("C'était une Witch! Bravo joueur " + instanceJeu.getEnTour().pseudo + ", vous gagnez un point et prenez le prochain tour !");
			instanceJeu.getEnTour().points += 1;
			return instanceJeu.getEnTour();
		}
		else {
			System.out.println("C'était un Villager! Dommage joueur " + instanceJeu.getEnTour().pseudo + ", " + this.pseudo + ", vous gagnez un point et prenez le prochain tour !");
			this.points +=1;
			return this;
		}
	}

	public boolean isMustAccuse() {
		return mustAccuse;
	}

	public void setMustAccuse(boolean mustAccuse) {
		this.mustAccuse = mustAccuse;
	}

	public boolean isAccusable() {
		return accusable;
	}

	public void setAccusable(boolean accusable) {
		this.accusable = accusable;
	}
	
}
