package controleur;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import modele.*;
import vue.*;

public class Jeu{
	
	
	private static Vue vueActuelle;
	private int nombreJoueurs;
	private int nombrePhy;
	private int nombreIA;
	private int nombreDefausse;
	private static Jeu Instance;
	private Joueur enTour;
	private Joueur accused;
	//private CarteRumeur[] ensembleCartes;
	private ArrayList<CarteRumeur> ensembleCartes = new ArrayList<CarteRumeur>();
	private ArrayList<Joueur> ensembleJoueurs = new ArrayList<Joueur>();
	private Defausse tasDefausse;
	private ArrayList<Joueur> gagnants = new ArrayList<Joueur>();
	
	public static Vue getVueActuelle() {
		return Jeu.vueActuelle;
	}
	
	public static void setVueActuelle(Vue vue) {
		Jeu.vueActuelle = vue;
	}
	
	public int getNombreJoueurs() {
		return this.nombreJoueurs;
	}
	
	public Joueur getJoueur(int i) {
		return this.ensembleJoueurs.get(i);
	}
	
	public void setGagnants(Joueur gagnantDuJeu) {
		this.gagnants.add(gagnantDuJeu);
	}
	
	public void retirerCartes() {
		for (int i=0;i<this.ensembleJoueurs.size();i++) {
			this.getJoueur(i).createCarteEnMain();
			this.getJoueur(i).createCarteRevelees();
		}
		this.tasDefausse.resetContenu();
	}
	
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
	
	
	public int getNombrePhy() {
		return nombrePhy;
	}

	public void setNombrePhy(int nombrePhy) {
		this.nombrePhy = nombrePhy;
	}

	public int getNombreIA() {
		return nombreIA;
	}

	public void setNombreIA(int nombreIA) {
		this.nombreIA = nombreIA;
	}
	
	public void initGame() {
		
		vueActuelle.initialisationDeLaPartie();
		
		System.out.println("La partie va commencer, configuration : \n	- Nombre de joueurs physiques : " + this.nombrePhy + "\n	- Nombre de joueurs virtuels : " + this.nombreIA + "\n"); //à delete
		
		System.out.println("Création des joueurs...\n"); //à delete
		
		this.nombreJoueurs = this.nombrePhy + this.nombreIA;
		for (int i = 0; i < this.nombrePhy; i++) {
			this.ensembleJoueurs.add(new JoueurPhysique());
		}
		for (int i = 0; i < this.nombreIA; i++) {
			this.ensembleJoueurs.add(new JoueurVirtuel());
		}
		
		System.out.println("Création de la défausse..."); //à delete
		//this.nombreJoueurs = nbPhy; // à modifier, le nombre de joueurs = nombre de joueurs totaux
		this.tasDefausse = Defausse.getInstance();
		
		
		System.out.println("Création des cartes rumeurs..."); //à delete
		for (int i = 0; i < 12; i++) {
			
			this.ensembleCartes.add(new CarteRumeur());
			//System.out.println(this.ensembleCartes[i]);
		}
	}
	
	 /*TEST DU TABLEAU DE CARTES DU DEBUT
	public void afficherCartes() {
		for (int i = 0; i < 12; i++) {
			System.out.println(this.ensembleCartes[i]);
		}
	}*/
	
	
	public void distributionCartesRumeurs() {
		CarteRumeur transition;
		System.out.println("Mélange des cartes Rumeurs...");
		
		/*int carteEch1;
		int carteEch2;
		for (int i = 0; i < 100; i++) {
			carteEch1 = (int) (Math.random() * ( 13 - 1 ));
			carteEch2 = (int) (Math.random() * ( 13 - 1 ));
			if (carteEch1 != carteEch2) {
				transition = this.ensembleCartes[carteEch1];
				this.ensembleCartes[carteEch1] = this.ensembleCartes[carteEch2];
				this.ensembleCartes[carteEch2] = transition;
			}
		
		}*/
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
		
		/*	TEST DE LA DISTRIBUTION POUR QUELQUES VALEURS
		System.out.print("joueur 1 carte 1 " + ensembleJoueurs[0].carteEnMain[0]);
		System.out.print("joueur 1 carte 2 " + ensembleJoueurs[0].carteEnMain[1]);
		System.out.print("joueur 2 carte 2 " + ensembleJoueurs[1].carteEnMain[1]);
		System.out.print("defausse " + tasDefausse.contenu[1]);
		*/
		
	}
	
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
		}
		
		if (Instance.gagnants.size()>1) {
			try {
				this.jouerTieBreaker();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
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
			roundEnCours.debutRound(instanceJeu.getJoueur(premierJoueur));
			
			this.determinerGagnant();
			//Analyse si un joueur a atteint les 5 points à l'aide de maxPoints à la fin d'un round
			/*for (int i=0; i<this.nombreJoueurs; i++ ) {
				if (this.ensembleJoueurs.get(i).getPoints() > maxPoints) {
					maxPoints = this.ensembleJoueurs.get(i).getPoints();
				}
			}*/
			
			
		}
		
			
		
	}
	
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
	
	public Joueur getEnTour() {
		return this.enTour;
	}
	
	public void setEnTour(Joueur joueur) {
		this.enTour = joueur;
	}
	
	public Joueur getAccused() {
		return this.accused;
	}
	
	public void setAccused(Joueur joueur) {
		this.accused = joueur;
	}
	
	public static Jeu getInstance() {
		if (Instance == null) {
            Instance = new Jeu();
        }
        return Instance;
    }
	

	public ArrayList<Joueur> getEnsembleJoueurs() {
		return ensembleJoueurs;
	}

	public void setEnsembleJoueurs(ArrayList<Joueur> ensembleJoueurs) {
		this.ensembleJoueurs = ensembleJoueurs;
	}
	
	
	public static void main(String[] args) {
		
		/*
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jeu instanceJeu = new Jeu();
					InterfaceDebut window = new InterfaceDebut();
					Jeu.setVueActuelle(window);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		*/
		
		
		
		
		
		
		
		//démarrage du jeu par console
		
		/*
		Jeu instanceJeu = new Jeu();
		instanceJeu.vueActuelle = new VueConsole();
		int choixDemarrer = instanceJeu.getVueActuelle().demarrerJeu();
		
		if (choixDemarrer == 1) {
			instanceJeu.nombrePhy = instanceJeu.getVueActuelle().demanderNombreJoueursPhysiques();
			instanceJeu.nombreIA = instanceJeu.getVueActuelle().demanderNombreJoueursVirtuels();
			instanceJeu.initGame();
			instanceJeu.orgaRounds();
		}
		
		System.exit(1);
		*/
	}
}
