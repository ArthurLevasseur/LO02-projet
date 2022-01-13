package modele;
import effets.*;
import java.util.ArrayList;

import controleur.ControleurInter;

/**
 * Cette classe repr�sente un joueur. Elle est abstraite : les joueurs h�ritent de {@link JoueurPhysique} et {@link JoueurVirtuel}.
 *
 */

public abstract class Joueur {
	
	
	/**
	 * Total des points du joueur
	 */
	private int points = 0;
	//private int id;
	//public CarteRumeur[] carteRevelees = new CarteRumeur[4];
	
	/**
	 * Collection contenant les cartes r�vel�es par le joueur.
	 */
	private ArrayList<CarteRumeur> carteRevelees = new ArrayList<CarteRumeur>();
	//public CarteRumeur[] carteEnMain = new CarteRumeur[4];
	
	/**
	 * Collection contenant les cartes en main du joueur.
	 */
	private ArrayList<CarteRumeur> carteEnMain = new ArrayList<CarteRumeur>();
	
	/**
	 * Bool�en valant true si le joueur est accusable, false s'il ne l'est pas. Valeur modifi�e par les effets des cartes.
	 */
	private boolean accusable=true;
	//private int nbCartesEnMain = 0;
	
	/**
	 * R�f�rence � la carte identit�e associ�e au joueur.
	 * @see Identite
	 */
	private Identite identiteAssociee;
	
	/**
	 * Pseudonyme du joueur.
	 */
	private String pseudo;
	
	/**
	 * Bool�en valant true si le joueur est contraint d'accuser un autre joueur. Valeur modifi�e par les effets des cartes.
	 */
	private boolean mustAccuse = false;
	
	/**
	 * R�f�rence � un contr�leur.
	 * @see ControleurInter
	 */
	private ControleurInter inter = ControleurInter.getInstance();
	
	/**
	 * Constructeur du joueur. Met son total de points � 0, le rend accusable et lui associe une identit�.
	 */
	public Joueur() {
		this.points = 0;
		this.accusable = true;
		this.identiteAssociee = new Identite();
	}
	
	/**
	 * Getter qui retourne le nombre de points
	 * @return Nombre de points du joueur
	 */
	
	public int getPoints() {
		return this.points;
	}
	
	/**
	 * Ajoute des points au joueur
	 * @param pointsAjoutes Points � ajouter
	 */
	
	public void ajouterPoints(int pointsAjoutes) {
		this.points = this.points+pointsAjoutes;
	}
	
	/**
	 * M�thode appel�e � la fin du round chez le gagnant. Ajoute le nombre de points selon l'identit� du joueur gagnant.
	 */
	
	public void gagnerPoints() {
		if (this.identiteAssociee.getIsWitch()==true) {
			this.points += 2;
			System.out.println("Bravo Joueur " + this.pseudo + " ! Vous avez gagn� ce round en Witch ! Vous gagnez deux points");
		}
		else {
			this.points += 1;
			System.out.println("Bravo Joueur " + this.pseudo + " ! Vous avez gagn� ce round en Villager ! Vous gagnez un points");
		}
	}
	
	/**
	 * M�thode permettant de v�rifier si le joueur est une IA ou non.
	 * @return True si le joueur est une IA. False sinon.
	 */
	
	public boolean isIA() {
		if (this instanceof JoueurVirtuel) {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	/**
	 * Ajoute la carte rumeur pass�e en argument � la main du joueur.
	 * @param carteAjoutee Carte � ajouter � la main.
	 */
	
	public void prendreCarteRumeur(CarteRumeur carteAjoutee) {
		this.carteEnMain.add(carteAjoutee);
	}
	
	/**
	 * M�thode appel�e quand une carte rumeur est prise par un autre joueur. Enl�ve la carte de la main du joueur.
	 * @param indexOfCarte Id de la carte.
	 * @return La carte prise.
	 */
	
	public CarteRumeur seFairePrendreCarteRumeur(int indexOfCarte) {
		CarteRumeur cartePrise = this.carteEnMain.get(indexOfCarte);
		this.carteEnMain.remove(indexOfCarte);
		return cartePrise;
	}
	
	// ???
	
	public boolean accuser(Jeu Instance, int choix) {
		
		return true;
	}
	
	/**
	 * M�thode appel�e quand un joueur est accus�. Appelle la vue si le joueur est un joueur physique.
	 * @return
	 */
	
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
	
	/**
	 * M�thode appel�e lorsque le joueur fait le choix de jouer une carte witch.
	 */
	
	public void jouerCarteWitch() {
		
		inter.choixDeCarteWitch();
		
	}
	
	/**
	 * M�thode appel�e lorsque le joueur fait le choix de jouer une carte hunt.
	 */
	
	public void jouerCarteHunt() {
		
		inter.choixDeCarteHunt();
		
		
	}
	
	/**
	 * M�thode appel�e quand un joueur d�cide de r�veler son identit�. Donne le joueur qui doit jouer son prochain tour.
	 * @return
	 */
	
	public Joueur revelerIdentite() {
		Jeu instanceJeu = Jeu.getInstance();
		this.identiteAssociee.ReveleIdentite();
		if (this.identiteAssociee.getIsWitch() == true) {
			System.out.println("C'�tait une Witch! Bravo joueur " + instanceJeu.getEnTour().pseudo + ", vous gagnez un point et prenez le prochain tour !");
			instanceJeu.getEnTour().points += 1;
			return instanceJeu.getEnTour();
		}
		else {
			System.out.println("C'�tait un Villager! Dommage joueur " + instanceJeu.getEnTour().pseudo + ", " + this.pseudo + ", vous prenez le prochain tour !");
			instanceJeu.setEnTour(this);
			return this;
		}
	}
	
	/**
	 * Getter qui retourne la valeur de mustAccuse.
	 * @return True si le joueur doit accuser. False si le joueur ne doit pas obligatoirement accuser.
	 */

	public boolean isMustAccuse() {
		return mustAccuse;
	}
	
	/**
	 * Setter qui d�finit la valeur de mustAccuse.
	 * @param mustAccuse Valeur � affecter.
	 */

	public void setMustAccuse(boolean mustAccuse) {
		this.mustAccuse = mustAccuse;
	}
	
	/**
	 * Getter qui retourne isAccusable.
	 * @return Valeur de isAccusable
	 */

	public boolean isAccusable() {
		return accusable;
	}
	
	/**
	 * Setter qui affecte une valeur � accusable.
	 * @param accusable Valeur � affecter
	 */

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
			System.out.println("Joueur " + this.getPseudo() + ", on vous a cibl� avec la carte \"Un b�cher\", voulez-vous :\n1) R�v�ler votre identit�.\n2) D�fausser une de vos cartes rumeurs." );
			SaisirInt saisieUtilisateur = SaisirInt.getInstance();
			int choix = saisieUtilisateur.nextInt();
			if (this.carteEnMain.isEmpty()) {
				System.out.println("vous ne pouvez pas d�fausser une carte, vous n'en avez plus en main ! Vous devrez donc r�v�ler votre identit�.");
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
				System.out.println("Quelle carte voulez vous d�fausser ?");
				this.carteEnMain.forEach(card -> System.out.println("TAPEZ "+this.carteEnMain.indexOf(card) + " pour d�fausser " + card));
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
			System.out.println(this.pseudo + " choisit entre r�v�ler son identit� ou d�fausse une de ses cartes rumeurs.");
			if (this.identiteAssociee.getIsWitch() == false) {
				instanceJeu.getEnTour().points -= 1;
				return this.revelerIdentite();
			}
			if (this.carteEnMain.isEmpty()) {
				System.out.println(this.pseudo + " ne peut pas d�fausser une carte, il n'en avez plus en main ! Il doit donc r�v�ler son identit�.");
				if (this.identiteAssociee.getIsWitch() == false){
					instanceJeu.getEnTour().points -= 1;
				}
				return this.revelerIdentite();
			}
			else {
				choix = (int)(Math.random() * 1.2);
				if (choix == 1) {
					System.out.println(this.pseudo + " d�cide de r�v�ler son identit�.");
					if (this.identiteAssociee.getIsWitch() == false){
						instanceJeu.getEnTour().points -= 1;
					}
					return this.revelerIdentite();
				}
				else {
					System.out.println(this.pseudo + " d�cide de d�fausser une carte.");
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
	
	public abstract void reponseDuckingStool(int joueurCible);
	
}
