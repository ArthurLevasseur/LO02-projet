package modele;
import effets.*;
import java.util.ArrayList;

import controleur.ControleurInter;



public abstract class Joueur {
	
	private int points = 0;
	private int id;
	//public CarteRumeur[] carteRevelees = new CarteRumeur[4];
	private ArrayList<CarteRumeur> carteRevelees = new ArrayList<CarteRumeur>();
	//public CarteRumeur[] carteEnMain = new CarteRumeur[4];
	private ArrayList<CarteRumeur> carteEnMain = new ArrayList<CarteRumeur>();
	private boolean accusable=true;
	private int nbCartesEnMain = 0;
	private Identite identiteAssociee;
	private String pseudo;
	private boolean mustAccuse = false;
	private ControleurInter inter = ControleurInter.getInstance();
	
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

		if (this.isIA() == false) {
			instanceJeu.getVueActuelle().repondreAccusation(this);
		}
		else {
			int choix;
			if (this.carteEnMain.isEmpty() || (this.carteEnMain.size() == 1 && this.carteEnMain.get(0).getNumCarte() == 3 && this.carteRevelees.isEmpty())) {
				choix = 1;
			}
			else {
				choix = (int) (Math.random() * 2);
			}
			
			if (choix == 0) {
				this.jouerCarteWitch();
			}
			else {
				this.revelerIdentite();
			}
		}
		



		return instanceJeu.getEnTour();
	}
	
	public void jouerCarteWitch() {
		
		inter.choixDeCarteWitch();
		
	}
	
	public void jouerCarteHunt() {
		
		inter.choixDeCarteHunt();
		
		
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
			System.out.println("C'était un Villager! Dommage joueur " + instanceJeu.getEnTour().pseudo + ", " + this.pseudo + ", vous prenez le prochain tour !");
			instanceJeu.setEnTour(this);
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

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public Joueur accusedBucher() {
		Jeu instanceJeu = Jeu.getInstance();
		if (this.isIA() == false) {
			System.out.println("Joueur " + this.getPseudo() + ", on vous a ciblé avec la carte \"Un bûcher\", voulez-vous :\n1) Révéler votre identité.\n2) Défausser une de vos cartes rumeurs." );
			SaisirInt saisieUtilisateur = SaisirInt.getInstance();
			int choix = saisieUtilisateur.nextInt();
			if (this.carteEnMain.isEmpty()) {
				System.out.println("vous ne pouvez pas défausser une carte, vous n'en avez plus en main ! Vous devrez donc révéler votre identité.");
				choix = 1;
			}
			else {
				while (choix!=1 && choix!=2) {
					System.out.println("Choix invalide !");
					choix = saisieUtilisateur.nextInt();
				}
			};
			if (choix == 1) {
				if (this.identiteAssociee.getIsWitch() == false){
					instanceJeu.getEnTour().points -= 1;
				}
				return this.revelerIdentite();
			}
			else {
				Defausse instanceDefausse = Defausse.getInstance();
				System.out.println("Quelle carte voulez vous défausser ?");
				this.carteEnMain.forEach(card -> System.out.println("TAPEZ "+this.carteEnMain.indexOf(card) + " pour défausser " + card));
				choix = saisieUtilisateur.nextInt();
				while (choix<0 || choix>this.carteEnMain.size() || this.carteEnMain.get(choix).getNumCarte() == 7){
					System.out.println("Choix invalide !");
					choix = saisieUtilisateur.nextInt();
				}
				
				instanceDefausse.defausserUneCarte(this.seFairePrendreCarteRumeur(choix));
				return this;
				
			}
		}
		else {
			int choix;
			System.out.println(this.pseudo + " choisit entre révéler son identité ou défausse une de ses cartes rumeurs.");
			if (this.identiteAssociee.getIsWitch() == false) {
				instanceJeu.getEnTour().points -= 1;
				return this.revelerIdentite();
			}
			if (this.carteEnMain.isEmpty()) {
				System.out.println(this.pseudo + " ne peut pas défausser une carte, il n'en avez plus en main ! Il doit donc révéler son identité.");
				if (this.identiteAssociee.getIsWitch() == false){
					instanceJeu.getEnTour().points -= 1;
				}
				return this.revelerIdentite();
			}
			else {
				choix = (int)(Math.random() * 1.2);
				if (choix == 1) {
					System.out.println(this.pseudo + " décide de révéler son identité.");
					if (this.identiteAssociee.getIsWitch() == false){
						instanceJeu.getEnTour().points -= 1;
					}
					return this.revelerIdentite();
				}
				else {
					System.out.println(this.pseudo + " décide de défausser une carte.");
					Defausse instanceDefausse = Defausse.getInstance();
					int choixCarte = (int) (Math.random() * this.carteEnMain.size());
					instanceDefausse.defausserUneCarte(this.seFairePrendreCarteRumeur(choixCarte));
					return this;
				}
			}
		}
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Identite getIdentiteAssociee() {
		return identiteAssociee;
	}

	public void setIdentiteAssociee(Identite identiteAssociee) {
		this.identiteAssociee = identiteAssociee;
	}

	public ArrayList<CarteRumeur> getCarteRevelees() {
		return carteRevelees;
	}

	public void createCarteRevelees() {
		this.carteRevelees = new ArrayList<CarteRumeur>();
	}

	public ArrayList<CarteRumeur> getCarteEnMain() {
		return carteEnMain;
	}

	public void createCarteEnMain() {
		this.carteEnMain = new ArrayList<CarteRumeur>();
	}

	public void setCarteRevelees(ArrayList<CarteRumeur> carteRevelees) {
		this.carteRevelees = carteRevelees;
	}

	public void setCarteEnMain(ArrayList<CarteRumeur> carteEnMain) {
		this.carteEnMain = carteEnMain;
	}

	public abstract void repondreAccusation();

	public abstract void jouerTour();

	public abstract void recapIA();

	public abstract void recapIAAccused();
	
	public abstract void reponseDuckingStool(int joueurCible);
	
}
