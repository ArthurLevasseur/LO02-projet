package modele;
import effets.*;
import java.util.ArrayList;

import controleur.ControleurInter;

/**
 * Cette classe représente un joueur. Elle est abstraite : les joueurs héritent de {@link JoueurPhysique} et {@link JoueurVirtuel}.
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
	 * Collection contenant les cartes révelées par le joueur.
	 */
	private ArrayList<CarteRumeur> carteRevelees = new ArrayList<CarteRumeur>();
	//public CarteRumeur[] carteEnMain = new CarteRumeur[4];
	
	/**
	 * Collection contenant les cartes en main du joueur.
	 */
	private ArrayList<CarteRumeur> carteEnMain = new ArrayList<CarteRumeur>();
	
	/**
	 * Booléen valant true si le joueur est accusable, false s'il ne l'est pas. Valeur modifiée par les effets des cartes.
	 */
	private boolean accusable=true;
	//private int nbCartesEnMain = 0;
	
	/**
	 * Référence à la carte identitée associée au joueur.
	 * @see Identite
	 */
	private Identite identiteAssociee;
	
	/**
	 * Pseudonyme du joueur.
	 */
	private String pseudo;
	
	/**
	 * Booléen valant true si le joueur est contraint d'accuser un autre joueur. Valeur modifiée par les effets des cartes.
	 */
	private boolean mustAccuse = false;
	
	/**
	 * Référence à un contrôleur.
	 * @see ControleurInter
	 */
	private ControleurInter inter = ControleurInter.getInstance();
	
	/**
	 * Constructeur du joueur. Met son total de points à 0, le rend accusable et lui associe une identité.
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
	 * @param pointsAjoutes Points à ajouter
	 */
	
	public void ajouterPoints(int pointsAjoutes) {
		this.points = this.points+pointsAjoutes;
	}
	
	/**
	 * Méthode appelée à la fin du round chez le gagnant. Ajoute le nombre de points selon l'identité du joueur gagnant.
	 */
	
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
	
	/**
	 * Méthode permettant de vérifier si le joueur est une IA ou non.
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
	 * Ajoute la carte rumeur passée en argument à la main du joueur.
	 * @param carteAjoutee Carte à ajouter à la main.
	 */
	
	public void prendreCarteRumeur(CarteRumeur carteAjoutee) {
		this.carteEnMain.add(carteAjoutee);
	}
	
	/**
	 * Méthode appelée quand une carte rumeur est prise par un autre joueur. Enlève la carte de la main du joueur.
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
	 * Méthode appelée quand un joueur est accusé. Appelle la vue si le joueur est un joueur physique.
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
	 * Méthode appelée lorsque le joueur fait le choix de jouer une carte witch.
	 */
	
	public void jouerCarteWitch() {
		
		inter.choixDeCarteWitch();
		
	}
	
	/**
	 * Méthode appelée lorsque le joueur fait le choix de jouer une carte hunt.
	 */
	
	public void jouerCarteHunt() {
		
		inter.choixDeCarteHunt();
		
		
	}
	
	/**
	 * Méthode appelée quand un joueur décide de réveler son identité. Donne le joueur qui doit jouer son prochain tour.
	 * @return
	 */
	
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
	
	/**
	 * Getter qui retourne la valeur de mustAccuse.
	 * @return True si le joueur doit accuser. False si le joueur ne doit pas obligatoirement accuser.
	 */

	public boolean isMustAccuse() {
		return mustAccuse;
	}
	
	/**
	 * Setter qui définit la valeur de mustAccuse.
	 * @param mustAccuse Valeur à affecter.
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
	 * Setter qui affecte une valeur à accusable.
	 * @param accusable Valeur à affecter
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
	
	public abstract void reponseDuckingStool(int joueurCible);
	
}
