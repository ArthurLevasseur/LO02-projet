package modele;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import controleur.*;
import vue.*;


/** 
 * La classe principale du jeu. Elle dirige l'initialisation du jeu et son déroulement. Le déroulement des rounds est prévu par la classe {@link #round}
 * Hérite d'observable pour la communication avec l'interface graphique.
 *
 */

public class Jeu extends Observable{
	
	
	/**
	 * Attribut contenant une référence vers la vue actuelle : VueConsole si le jeu est démarré en mode console, ou une autre vue si le jeu est démarré en interface graphique
	 */
	
	private static Vue vueActuelle;
	
	/** 
	 * Nombre de joueurs peu importe leur types
	 */
	
	private int nombreJoueurs;
	
	/**
	 * Nombre de joueurs physiques
	 */
	
	private int nombrePhy;
	
	/** 
	 * Nombre de joueurs virtuels
	 */
	
	private int nombreIA;
	
	/**
	 * Nombre de cartes à défausser. Utilisé pour l'initialisation du jeu.
	 */
	
	private int nombreDefausse;
	
	/**
	 * Attribut contenant l'instance du jeu (patron de conception singleton).
	 */
	
	private static Jeu Instance;
	
	/**
	 * Attribut contenant une référence vers le joueur en train de jouer.
	 */
	
	private Joueur enTour;
	
	/**
	 * Attribut contenant une référence vers le joueur en train d'être accusé.
	 */
	
	private Joueur accused;
	
	/**
	 * Attribut contenant une collection de l'ensemble des cartes rumeurs du jeu.
	 */
	
	private ArrayList<CarteRumeur> ensembleCartes = new ArrayList<CarteRumeur>();
	
	/**
	 * Collection contenant les joueurs de la partie.
	 */
	
	private ArrayList<Joueur> ensembleJoueurs = new ArrayList<Joueur>();
	
	/**
	 * Référence à la défausse
	 * @see Defausse
	 */
	
	private Defausse tasDefausse;
	
	/**
	 * Collection contenant le ou les gagnants. Sous forme de collection pour gérer les égalités.
	 */
	
	private ArrayList<Joueur> gagnants = new ArrayList<Joueur>();
	
	/**
	 * Référence au contrôleur de l'interface graphique
	 */
	
	private ControlerGUI controler;
	private Round round;
	private int compteur;
	
	/**
	 * Référence à un contrôleur permettant de séparer certains affichages consoles et GUI présents dans certaines méthodes du modèle
	 */
	
	private ControleurInter inter = ControleurInter.getInstance();
	
	/**
	 * Getter retournant la vue actuelle.
	 * @return La vue actuelle
	 */
	
	public static Vue getVueActuelle() {
		return Jeu.vueActuelle;
	}
	
	/**
	 * Setter permettant de modifier la vue actuelle.
	 * @param vue La vue à choisir.
	 */
	
	public static void setVueActuelle(Vue vue) {
		Jeu.vueActuelle = vue;
	}
	
	/**
	 * Getter retournant le nombre de joueurs
	 * @return Le nombre de joueurs
	 */
	
	public int getNombreJoueurs() {
		return this.nombreJoueurs;
	}
	
	/**
	 * Getter retournant le joueur ayant l'id choisi
	 * @param i L'id du joueur à retourner
	 * @return Le joueur ayant l'id sélectionné
	 */
	
	public Joueur getJoueur(int i) {
		return this.ensembleJoueurs.get(i);
	}
	
	/**
	 * Setter permettant de désigner un gagnant
	 * @param gagnantDuJeu Le gagnant à ajouter à la collection
	 */
	
	public void setGagnants(Joueur gagnantDuJeu) {
		this.gagnants.add(gagnantDuJeu);
	}
	
	/**
	 * 
	 */
	
	public void retirerCartes() {
		for (int i=0;i<this.ensembleJoueurs.size();i++) {
			this.getJoueur(i).createCarteEnMain();
			this.getJoueur(i).createCarteRevelees();
		}
		this.tasDefausse.resetContenu();
	}
	
	/**
	 * Méthode permettant à un joueur de sélectionner un de ses adversaires. Un message est affiché en mode console.
	 * @param selecteur Le joueur devant sélectionner un de ses adversaires et ne figurant donc pas parmi les choix possibles.
	 * @param Message affiché
	 * @return Le joueur ayant été sélectionné
	 */
	
	public Joueur selectionnerAdversaire(Joueur selecteur, String Message) {
		SaisirInt scan = SaisirInt.getInstance();
		System.out.println(Message);
		Joueur selection = null;
		for (int i=1 ; i<Instance.getNombreJoueurs()+1 ; i++) {
			if (((Instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == true && Instance.getJoueur(i-1).getIdentiteAssociee().getIsWitch() == false) || Instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false) && Instance.getJoueur(i-1)!=selecteur) {

				if (!(Instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee())) {
					System.out.println("Joueur " + (i) + ") " + Instance.getJoueur(i-1).getPseudo() + "     status : EN ROUND     (points : " + Instance.getJoueur(i-1).getPoints() + ")");
				}
				else if (Instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() && Instance.getJoueur(i-1).getIdentiteAssociee().getIsWitch() == true) {
					System.out.println("Joueur " + (i) + ") " + Instance.getJoueur(i-1).getPseudo() + "     status : HORS ROUND     (points : " + Instance.getJoueur(i-1).getPoints() + ")");
				}
				else if (Instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() && Instance.getJoueur(i-1).getIdentiteAssociee().getIsWitch() == false) {
					System.out.println("Joueur " + (i) + ") " + Instance.getJoueur(i-1).getPseudo() + "     status : VILLAGEOIS PASSIF     (points : " + Instance.getJoueur(i-1).getPoints() + ")");
				}
			}
		}
		
		
		int choix = -1;
		
		while (choix<0 || choix>Instance.getNombreJoueurs() || !(((Instance.getJoueur(choix-1).getIdentiteAssociee().getDevoilee() == true && Instance.getJoueur(choix-1).getIdentiteAssociee().getIsWitch() == false) || Instance.getJoueur(choix-1).getIdentiteAssociee().getDevoilee() == false) && Instance.getJoueur(choix-1)!=selecteur)) {
			choix = scan.nextInt();
			if (0<choix && choix<Instance.getNombreJoueurs()+1 && ((Instance.getJoueur(choix-1).getIdentiteAssociee().getDevoilee() == true && Instance.getJoueur(choix-1).getIdentiteAssociee().getIsWitch() == false) || Instance.getJoueur(choix-1).getIdentiteAssociee().getDevoilee() == false) && Instance.getJoueur(choix-1)!=selecteur) {
				selection = this.ensembleJoueurs.get(choix-1);
			}
			else {
				System.out.println("Choix invalide !");
			}
		}
		
		return selection;
	}
	

	public Jeu(){
		
	}
	
	/**
	 * Getter du nombre de joueurs physiques
	 * @return Le nombre de joueurs physiques
	 */
	
	public int getNombrePhy() {
		return nombrePhy;
	}
	
	/**
	 * Setter du nombre de joueurs physiques
	 * @param nombrePhy Nombre de joueurs physiques
	 */

	public void setNombrePhy(int nombrePhy) {
		this.nombrePhy = nombrePhy;
	}
	
	/**
	 * Getter du nombre de joueurs virtuels
	 * @return Le nombre de joueurs physiques
	 */

	public int getNombreIA() {
		return nombreIA;
	}
	
	/**
	 * Setter du nombre de joueurs virtuels
	 * @param nombrePhy Nombre de joueurs physiques
	 */

	public void setNombreIA(int nombreIA) {
		this.nombreIA = nombreIA;
	}
	
	/**
	 * Initialise le jeu. Instancie les {@link Joueurs}, la {@link Defausse} et les  {@link CarteRumeur}. Demande ensuite aux joueurs d'écrire leurs pseudos.
	 */
	
	public void initGame() {
		
		
		
		this.nombreJoueurs = this.nombrePhy + this.nombreIA;
		for (int i = 0; i < this.nombrePhy; i++) {
			this.ensembleJoueurs.add(new JoueurPhysique());
		}
		for (int i = 0; i < this.nombreIA; i++) {
			this.ensembleJoueurs.add(new JoueurVirtuel());
		}
		
		this.tasDefausse = Defausse.getInstance();
		
		
		for (int i = 0; i < 12; i++) {
			
			this.ensembleCartes.add(new CarteRumeur());
		}
		
		this.inter.entrerPseudo();
	}
	
	public void setPseudo() {
		if (this.nombrePhy != 0) {
			InterfaceChoixPseudos interJ1 = new InterfaceChoixPseudos(this.getJoueur(0));
		}
		else {
			this.initRound();
		}
		
	}

	
	
	/**
	 * Distribue les cartes rumeurs aux joueurs et place les cartes restantes dans la défausse.
	 */
	
	
	public void distributionCartesRumeurs() {
		CarteRumeur transition;
		System.out.println("Mélange des cartes Rumeurs...");
		
		Collections.shuffle(this.ensembleCartes);
		
		System.out.println("Distribution des cartes Rumeurs...\n");
		if (this.nombreDefausse == 2) {
			tasDefausse.defausserUneCarte(this.ensembleCartes.get(0));
			tasDefausse.defausserUneCarte(this.ensembleCartes.get(1));
			for (int i = 0; i < 5 ; i++) {
				this.ensembleJoueurs.get(i).prendreCarteRumeur(this.ensembleCartes.get(i*2+2));
				this.ensembleJoueurs.get(i).prendreCarteRumeur(this.ensembleCartes.get(i*2+3));
			}
		}
		else {
			for (int i = 0; i < nombreJoueurs ; i++) {
				if (nombreJoueurs == 3) {
					this.ensembleJoueurs.get(i).prendreCarteRumeur(this.ensembleCartes.get(i*4));
					this.ensembleJoueurs.get(i).prendreCarteRumeur(this.ensembleCartes.get(i*4+1));
					this.ensembleJoueurs.get(i).prendreCarteRumeur(this.ensembleCartes.get(i*4+2));
					this.ensembleJoueurs.get(i).prendreCarteRumeur(this.ensembleCartes.get(i*4+3));
				}
				else if (nombreJoueurs == 4) {
					this.ensembleJoueurs.get(i).prendreCarteRumeur(this.ensembleCartes.get(i*3));
					this.ensembleJoueurs.get(i).prendreCarteRumeur(this.ensembleCartes.get(i*3+1));
					this.ensembleJoueurs.get(i).prendreCarteRumeur(this.ensembleCartes.get(i*3+2));
				}
				else { //=6
					this.ensembleJoueurs.get(i).prendreCarteRumeur(this.ensembleCartes.get(i*2));
					this.ensembleJoueurs.get(i).prendreCarteRumeur(this.ensembleCartes.get(i*2+1));
				}
			}
		}
		
	}
	
	/**
	 * Détermine qui est le gagnant. Si un joueur est le seul à avoir atteint ou dépassé le score de 5, il remporte la partie. Si plusieurs joueurs ont dépassé 5, celui qui a le plus de points gagne. S'ils sont à égalité, le gagnant est tiré au sort.
	 */
	
	public void implementGagnant() {
		
		int maxPoints = 5;
		
		// déterminer quel est le max de points
		for (Joueur j : ensembleJoueurs) {
			if (j.getPoints() >= maxPoints) {
				maxPoints = j.getPoints();
			}	
		}
		
		// déterminer les joueurs qui ont atteint au 5 points (si un joueur a 6 points et un autre 5, le gagnant sera celui à 6 points)
		for (Joueur j : ensembleJoueurs) {
			if (j.getPoints() == maxPoints) {
				gagnants.add(j);
			}
		}
		
		
	}
	
	/**
	 * Permet de récupérer le classement d'un joueur selon ses points.
	 * @param joueur Le joueur dont on veut connaître le classement.
	 * @return Un entier contenant son classement.
	 */
	
	public int getClassement(Joueur joueur) {
		compteur = 1;
		this.getEnsembleJoueurs().forEach(JoueurCompared -> {
			if (JoueurCompared.getPoints() > joueur.getPoints()) {
				compteur += 1;
			}
		});
		return compteur;
	}
	
	/**
	 * Détermine qui remporte la partie, départage les égalités si nécessaire.
	 */
	
	public void determinerGagnant() {
		
		int maxPoints = 0;
		
		// déterminer quel est le max de points
		for (Joueur j : ensembleJoueurs) {
			if (j.getPoints() > maxPoints) {
				maxPoints = j.getPoints();
			}
		}
		
		// déterminer les joueurs qui ont le max de points tout en étant supérieurs à 5 = le ou les gagnants
		for (Joueur j : ensembleJoueurs) {
			if (j.getPoints() == maxPoints && j.getPoints() >= 5) {
				gagnants.add(j);
			}
		}
		
		if (Instance.gagnants.size()==1) {
			System.out.print("Bravo ");
			Instance.gagnants.forEach(joueur -> System.out.print(joueur.getPseudo() +", "));
			System.out.println("vous avez gagné la partie !");
			controleur.ControlerCLI arretDuJeu = new controleur.ControlerCLI();
			arretDuJeu.stopJeu();
		}
		
		if (Instance.gagnants.size()>1) {
			try {
				this.jouerTieBreaker();
				controleur.ControlerCLI arretDuJeu = new controleur.ControlerCLI();
				arretDuJeu.stopJeu();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	/**
	 * Met en place les rounds : distribue les cartes, construit et lance les rounds.
	 * @see Round
	 */
	
	public void orgaRounds() {
		
		Jeu instanceJeu = Jeu.getInstance();
		int maxPoints = 0;
		//le tout premier joueur est choisi aléatoirement
		int premierJoueur = (int) (Math.random() * instanceJeu.nombreJoueurs);
		while (maxPoints < 5) {
			
			//Les cartes des mains des joueurs, leurs cartes révélées ainsi que la défausse sont réinitialisées
			this.retirerCartes();
			
			this.distributionCartesRumeurs();
		
			//Création d'un round (contenant le déroulement du round aussi)
			Round roundEnCours = new Round();
			this.round = roundEnCours;
			roundEnCours.debutRound(instanceJeu.getJoueur(premierJoueur));
			
			this.determinerGagnant();
			
			
		}
		
			
		
	}
	
	/**
	 * Initialise le tour. Choisit la bonne vue, instancie le tour et choisit le joueur qui commence la partie.
	 */
	
	public void initRound() {
		
		Jeu instanceJeu = Jeu.getInstance();
		
		Jeu.getInstance().getEnsembleJoueurs().forEach(joueur -> joueur.getIdentiteAssociee().randomIdentite());
		
		for (int i=0;i<instanceJeu.getNombreJoueurs();i++) {
			instanceJeu.getJoueur(i).getIdentiteAssociee().setDevoilee(false);
		}
		
		if (this.getNombrePhy()>0) {
			
			this.setVueActuelle(new InterfaceIdentite(0)); // à modifier ???
		}
		else {
			int premierJoueur = (int) (Math.random() * instanceJeu.nombreJoueurs);
			this.setEnTour(this.getJoueur(premierJoueur));
			Round roundEnCours = new Round();
			this.round = roundEnCours;
			roundEnCours.fonctionnementRound(instanceJeu.getJoueur(premierJoueur));
		}
			
		
	}
	
	
	/**
	 * Fonction qui tire au sort le vainqueur en mode console.
	 * @throws InterruptedException
	 */

	
	public void jouerTieBreaker() throws InterruptedException {
		
		Jeu instanceJeu = Jeu.getInstance();
		
		System.out.print(". ");
		TimeUnit.SECONDS.sleep(2);
		System.out.print(". ");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(". ");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Mais il ne peut avoir qu'un gagnant");
		System.out.println("Les " + instanceJeu.gagnants.size() + " joueurs sortent un couteau et se départagent dans un duel jusqu'à la mort !");
		TimeUnit.SECONDS.sleep(2);
		while (instanceJeu.gagnants.size()>1) {
			System.out.print(". ");
			TimeUnit.SECONDS.sleep(2);
			System.out.print(". ");
			TimeUnit.SECONDS.sleep(2);
			System.out.println(". ");
			TimeUnit.SECONDS.sleep(2);
			int i = (int) (Math.random() * instanceJeu.gagnants.size());
			System.out.println("Le joueur " + instanceJeu.gagnants.get(i).getPseudo() + " tombe au combat !");
			instanceJeu.gagnants.remove(i);
		}
		System.out.println("Le gagnant final de ce jeu est donc le joueur : " + instanceJeu.gagnants.get(0).getPseudo());
		System.out.println("Bravo à lui et à une prochaine !");
	}
	
	/**
	 * Getter qui retourne le joueur en tour.
	 * @return Le joueur en tour.
	 */
	
	public Joueur getEnTour() {
		return this.enTour;
	}
	
	/**
	 * Setter qui sélectionne le joueur en tour.
	 * @param joueur Le joueur qui va jouer son tour.
	 */
	
	public void setEnTour(Joueur joueur) {
		this.enTour = joueur;
	}
	
	/**
	 * Getter qui retourne le joueur accusé.
	 * @return Le joueur en train d'être accusé.
	 */
	
	public Joueur getAccused() {
		return this.accused;
	}
	
	/**
	 * Setter qui sélectionne le joueur en cours d'accusation.
	 * @param joueur Le joueur qui va être accusé.
	 */
	
	public void setAccused(Joueur joueur) {
		this.accused = joueur;
	}
	
	/**
	 * Permet de récupérer l'instance de la classe jeu, et la crée si elle n'existe pas. Utilisée dans le cadre du patron de conception singleton.
	 * @return L'instance de jeu.
	 */
	
	public static Jeu getInstance() {
		if (Instance == null) {
            Instance = new Jeu();
        }
        return Instance;
    }
	
	/**
	 * Getter qui retourne la collection contenant l'ensemble des joueurs.
	 * @return L'ensemble des joueurs.
	 */
	
	public ArrayList<Joueur> getEnsembleJoueurs() {
		return ensembleJoueurs;
	}
	
	/**
	 * Setter qui modifie la collection contenant l'ensemble des joueurs.
	 * @param ensembleJoueurs L'ensemble des joueurs qui remplacera le précédent.
	 */

	public void setEnsembleJoueurs(ArrayList<Joueur> ensembleJoueurs) {
		this.ensembleJoueurs = ensembleJoueurs;
	}
	
	/**
	 * Notifie les observers de la modification de l'état de la classe Jeu.
	 */
	
	public void actualisationInterface() {
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Setter qui modifie le nombre de joueurs.
	 * @param nombreJoueurs Nombre de joueurs à fixer.
	 */

	public void setNombreJoueurs(int nombreJoueurs) {
		this.nombreJoueurs = nombreJoueurs;
	}
	
	/**
	 * Retourne le contrôleur de l'interface graphique.
	 * @return Le contrôleur de l'interface graphique.
	 */

	public ControlerGUI getControler() {
		return controler;
	}
	
	/**
	 * Setter modifiant le contrôleur de l'interface graphique.
	 * @param controler Contrôleur de l'interface graphique
	 */

	public void setControler(ControlerGUI controler) {
		this.controler = controler;
	}
	
	/**
	 * Setter modifiant le contrôleur "intermédiaire" (contrôleur qui détermine quel affichage doit être utilisé dans certaines méthodes ne respectant pas encore totalement le principe du MVC).
	 * @param controler Contrôleur
	 */
	
	public ControleurInter getInter() {
		return inter;
	}
	
	/**
	 * Getter retournant le gagnant du round. Vérifie combien de joueurs ne sont pas encore révelés. S'il en reste qu'un, il remporte le round. Sinon, la fonction retourne null.
	 * @return Le joueur gagnant du round s'il y en a un. Sinon, null.
	 */

	public Joueur getGagnantRound() {
		
		for (int i=0; i<this.getNombreJoueurs(); i++) {
			if (this.getJoueur(i).getIdentiteAssociee().getDevoilee() == true) {
				
			}
			else {
				return this.getJoueur(i);
			}

		}
		return null;
		
	}
	
	/**
	 * Retourne le round en cours.
	 * @return Round en cours.
	 */

	public Round getRound() {
		return round;
	}
	
	/**
	 * Setter modifiant le round en cours.
	 * @param round Round en cours.
	 */

	public void setRound(Round round) {
		this.round = round;
	}
	
	/**
	 * Getter retournant la liste des gagnants.
	 * @return La liste des gagnants
	 */

	public ArrayList<Joueur> getGagnants() {
		return gagnants;
	}
	
	/**
	 * Getter de la défausse.
	 * @return La défausse
	 */

	public Defausse getTasDefausse() {
		return tasDefausse;
	}
	
	/**
	 * Setter de la défausse.
	 * @param tasDefausse La défausse.
	 */

	public void setTasDefausse(Defausse tasDefausse) {
		this.tasDefausse = tasDefausse;
	}
	
	
}
