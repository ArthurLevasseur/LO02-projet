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

		System.out.println("Joueur " + this.pseudo + ", on vous accuse, que voulez vous faire?\n\n1) Révéler votre identité.\n2) Jouer une carte rumeur (effet witch?).");
		Scanner saisieUtilisateur = new Scanner(System.in);
		int choix=0;
		while (choix!=1) {

			if (instanceJeu.getEnTour() instanceof JoueurPhysique) {
				choix = saisieUtilisateur.nextInt();
			}
			else {
				choix = ((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().seDefendre();
			}
			if (choix==2) {
				if (this.carteEnMain.isEmpty()) {
					System.out.println("Vous ne pouvez pas jouer de carte rumeurs, votre main est vide !");
				}
				else {
					return this.jouerCarteWitch();
				}
			}

			else if (choix==1) {
				return this.revelerIdentite();
			}
			
			else {
				System.out.println("Choix invalide !");
			}
		}



		return instanceJeu.getEnTour();
	}
	
	public Joueur jouerCarteWitch() {
		System.out.println("Choisissez la carte que vous souhaitez jouer. \n");
		
		for(CarteRumeur card : this.carteEnMain) {
			if (card.getNumCarte() == 3 && this.carteRevelees.isEmpty()) {
				System.out.println("PAS JOUABLE : "+ card + "(aucune de vos cartes n'est dévoilée)\n");
			}
			
			else {
				System.out.println("TAPEZ "+this.carteEnMain.indexOf(card) + " pour jouer " + card);
			}
		}
		
		Scanner saisieUtilisateur = new Scanner(System.in);
		int choix = saisieUtilisateur.nextInt();
		while (choix<0 || choix > this.carteEnMain.size()-1 || (this.carteEnMain.get(choix).getNumCarte() == 3 && this.carteRevelees.isEmpty())) {
			System.out.println("Choix Incompatible !");	
			choix = saisieUtilisateur.nextInt();
		}
		Joueur next = this.carteEnMain.get(choix).appliquerEffetWitch(this);
		this.carteRevelees.add(this.carteEnMain.get(choix));
		this.carteEnMain.remove(choix);
		return next;
	}
	
	public Joueur jouerCarteHunt() {
		Defausse defausse = Defausse.getInstance();
		System.out.println("Choisissez la carte que vous souhaitez jouer. \n");
		//this.carteEnMain.forEach(card -> System.out.println("TAPEZ "+this.carteEnMain.indexOf(card) + " pour jouer " + card));
		
		for(CarteRumeur card : this.carteEnMain) {
			if ((card.getNumCarte()==1 || card.getNumCarte()==2) && (this.identiteAssociee.getIsWitch() == true || this.identiteAssociee.getDevoilee() == false)) {
				System.out.println("PAS JOUABLE : "+ card + "(Vous n'êtes pas dévoilé en tant que villageois)\n");
			}
			
			else if (card.getNumCarte() == 3 && this.carteRevelees.isEmpty()) {
				System.out.println("PAS JOUABLE : "+ card + "(aucune de vos cartes n'est dévoilée)\n");
			}
			
			
			else {
				System.out.println("TAPEZ "+this.carteEnMain.indexOf(card) + " pour jouer " + card);
			}
		}
		
		Scanner saisieUtilisateur = new Scanner(System.in);
		int choix = saisieUtilisateur.nextInt();
		while (choix<0 || choix > this.carteEnMain.size()-1 || ((this.carteEnMain.get(choix).getNumCarte() == 2 || this.carteEnMain.get(choix).getNumCarte() == 1) && (this.identiteAssociee.getIsWitch() == true || this.identiteAssociee.getDevoilee() == false)) || (this.carteEnMain.get(choix).getNumCarte() == 3 && this.carteRevelees.isEmpty())) {
			System.out.println("Choix Incompatible !");	
			choix = saisieUtilisateur.nextInt();
		}
		Joueur next = this.carteEnMain.get(choix).appliquerEffetHunt();
		
		
		
		
		if (this.carteEnMain.get(choix).getNumCarte() == 11) {
			defausse.getContenu().add(this.carteEnMain.get(choix));
			this.carteEnMain.remove(choix);
		}
		else {
			this.carteRevelees.add(this.carteEnMain.get(choix));
			this.carteEnMain.remove(choix);
		}
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

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public Joueur accusedBucher() {
		Jeu instanceJeu = Jeu.getInstance();
		System.out.println("Joueur " + this.getPseudo() + ", on vous a ciblé avec la carte \"Un bûcher\", voulez-vous :\n1) Révéler votre identité.\n2) Défausser une de vos cartes rumeurs." );
		Scanner saisieUtilisateur = new Scanner(System.in);
		int choix = saisieUtilisateur.nextInt();
		while (choix!=1 && choix!=2) {
			System.out.println("Choix invalide !");
			choix = saisieUtilisateur.nextInt();
		}
		if (choix == 1) {
			if (this.identiteAssociee.getIsWitch() == false){
				instanceJeu.getEnTour().points -= 1;
				this.points -= 1;
			}
			return this.revelerIdentite();
		}
		else {
			Defausse instanceDefausse = Defausse.getInstance();
			System.out.println("Quelle carte voulez vous défausser ?");
			this.carteEnMain.forEach(card -> System.out.println("TAPEZ "+this.carteEnMain.indexOf(card) + " pour défausser " + card));
			choix = saisieUtilisateur.nextInt();
			while (choix<0 || choix>this.carteEnMain.size()){
				System.out.println("Choix invalide !");
				choix = saisieUtilisateur.nextInt();
			}
			
			instanceDefausse.defausserUneCarte(this.seFairePrendreCarteRumeur(choix));
			return this;
			
		}
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
