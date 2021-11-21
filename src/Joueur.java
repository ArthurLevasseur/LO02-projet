import java.util.Scanner;
import java.util.*;

public abstract class Joueur {
	
	private int points = 0;
	private int id;
	public CarteRumeur[] carteRevelees = new CarteRumeur[4];
	//public CarteRumeur[] carteEnMain = new CarteRumeur[4];
	public ArrayList<CarteRumeur> carteEnMain = new ArrayList<CarteRumeur>();
	private boolean accusable;
	private int nbCartesEnMain = 0;
	public Identite identiteAssociee;
	protected String pseudo;
	
	public Joueur() {
		this.points = 0;
		this.accusable = true;
		this.identiteAssociee = new Identite();
	}
	
	public int getPoints() {
		return this.points;
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
	
	public Joueur seFairePrendreCarteRumeur() {
		
	}
	
	public boolean accuser(Jeu Instance, int choix) {
		
		return true;
	}
	
	public Joueur estAccuse(Jeu instance, Joueur accusateur) {
		if (this.isIA() == false) {
			System.out.println("Joueur " + this.pseudo + ", on vous accuse, que voulez vous faire?\n\n1) Révéler votre identité.\n2) Jouer une carte rumeur (effet witch?).");
			Scanner saisieUtilisateur = new Scanner(System.in);
			int choix=0;
			while (choix!=1 && choix!=2) {
				choix = saisieUtilisateur.nextInt();
				if (choix==1) {
					return this.revelerIdentite(instance, accusateur);
				}
				else if (choix==2) {
					
				}
				else {
					System.out.println("Choix invalide !");
				}
			}
		}
		else {
			
		}
		
		return accusateur;
	}
	
	public Joueur jouerCarteWitch() {
		
	}
	
	public void jouerCarteHunt() {
		System.out.println("Choisissez la carte que vous souhaitez jouer.");
		this.carteEnMain.forEach(card -> System.out.println(card));
	}
	
	public Joueur revelerIdentite(Jeu Instance, Joueur accusateur) {
		this.identiteAssociee.ReveleIdentite();
		if (this.identiteAssociee.getIsWitch() == true) {
			System.out.println("C'était une Witch! Bravo joueur " + accusateur.pseudo + ", vous gagnez un point et prenez le prochain tour !");
			accusateur.points += 1;
			return accusateur;
		}
		else {
			System.out.println("C'était un Villager! Dommage joueur " + accusateur.pseudo + ", " + this.pseudo + ", vous gagnez un point et prenez le prochain tour !");
			this.points +=1;
			return this;
		}
	}
	
}
