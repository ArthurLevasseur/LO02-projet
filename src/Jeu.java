import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.*;

public class Jeu {
	
	private int nombreJoueurs;
	private int nombrePhy;
	private int nombreIA;
	private int nombreDefausse;
	private static Jeu Instance;
	private Joueur enTour;
	private Joueur accused;
	//private CarteRumeur[] ensembleCartes;
	private ArrayList<CarteRumeur> ensembleCartes = new ArrayList<CarteRumeur>();
	private Joueur[] ensembleJoueurs;
	private Defausse tasDefausse;
	private ArrayList<Joueur> gagnants = new ArrayList<Joueur>();
	
	public int getNombreJoueurs() {
		return this.nombreJoueurs;
	}
	
	public Joueur getJoueur(int i) {
		return this.ensembleJoueurs[i];
	}
	
	public void setGagnants(Joueur gagnantDuJeu) {
		this.gagnants.add(gagnantDuJeu);
	}
	
	public void retirerCartes() {
		for (int i=0;i<this.ensembleJoueurs.length;i++) {
			this.getJoueur(i).createCarteEnMain();
			this.getJoueur(i).createCarteRevelees();
		}
		this.tasDefausse.contenu = new ArrayList<CarteRumeur>();
	}
	
	public Joueur selectionnerAdversaire(Joueur selecteur, String Message) {
		Scanner saisieUtilisateur = new Scanner(System.in);
		System.out.println(Message);
		Joueur selection = null;
		for (int i=1 ; i<Instance.getNombreJoueurs()+1 ; i++) {
			if (((Instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == true && Instance.getJoueur(i-1).getIdentiteAssociee().getIsWitch() == false) || Instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false) && Instance.getJoueur(i-1)!=selecteur) {

				if (!(Instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee())) {
					System.out.println("Joueur " + (i) + ") " + Instance.getJoueur(i-1).pseudo + "     status : EN ROUND     (points : " + Instance.getJoueur(i-1).getPoints() + ")");
				}
				else if (Instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() && Instance.getJoueur(i-1).getIdentiteAssociee().getIsWitch() == true) {
					System.out.println("Joueur " + (i) + ") " + Instance.getJoueur(i-1).pseudo + "     status : HORS ROUND     (points : " + Instance.getJoueur(i-1).getPoints() + ")");
				}
				else if (Instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() && Instance.getJoueur(i-1).getIdentiteAssociee().getIsWitch() == false) {
					System.out.println("Joueur " + (i) + ") " + Instance.getJoueur(i-1).pseudo + "     status : VILLAGEOIS PASSIF     (points : " + Instance.getJoueur(i-1).getPoints() + ")");
				}
			}
		}
		
		
		int choix = -1;
		
		while (choix<0 || choix>Instance.getNombreJoueurs() || !(((Instance.getJoueur(choix-1).getIdentiteAssociee().getDevoilee() == true && Instance.getJoueur(choix-1).getIdentiteAssociee().getIsWitch() == false) || Instance.getJoueur(choix-1).getIdentiteAssociee().getDevoilee() == false) && Instance.getJoueur(choix-1)!=selecteur)) {
			choix = saisieUtilisateur.nextInt();
			if (0<choix && choix<Instance.getNombreJoueurs()+1 && ((Instance.getJoueur(choix-1).getIdentiteAssociee().getDevoilee() == true && Instance.getJoueur(choix-1).getIdentiteAssociee().getIsWitch() == false) || Instance.getJoueur(choix-1).getIdentiteAssociee().getDevoilee() == false) && Instance.getJoueur(choix-1)!=selecteur) {
				selection = this.ensembleJoueurs[choix-1];
			}
			else {
				System.out.println("Choix invalide !");
			}
		}
		
		return selection;
	}
	
	private Jeu(){
		Scanner saisieUtilisateur = new Scanner(System.in);
		System.out.println("Combien de joueurs physiques êtes-vous? (entre 1 et 6)");
		int choix = 0;
		int joueursPhysiques = 0;
		int nbPhy = 0;
		int nbIA = 0;
		while (choix < 1 || choix > 6) {
			choix = saisieUtilisateur.nextInt();
			if (choix < 1 || choix > 6) {
				System.out.println("Veuillez choisir entre 1 et 6 joueurs");
			}
			else {
				nbPhy = choix;
			}
		}
		
		//initialisation du nombre de joueurs virtuels
		System.out.println("Combien de joueurs virtuels voulez vous dans votre partie ? (minimum 3 et maximum 6 joueurs physiques et virtuels en combinés)");
		choix = -1;
		while ((choix + nbPhy < 3 || choix + nbPhy > 6) || (choix < 0 || choix > 6)) {
			choix = saisieUtilisateur.nextInt();
			if ((choix + nbPhy < 3 || choix + nbPhy > 6) || (choix < 0 || choix > 6)) {
				System.out.println("Choix invalide");
			}
			else {
				nbIA = choix;
			}
		}
		System.out.println("La partie va commencer, configuration : \n	- Nombre de joueurs physiques : " + nbPhy + "\n	- Nombre de joueurs virtuels : " + nbIA);
		
		
		System.out.println("Création des joueurs...");
		
		this.nombreJoueurs = nbPhy + nbIA;
		this.ensembleJoueurs = new Joueur[this.nombreJoueurs];
		for (int i = 0; i < nbPhy; i++) {
			this.ensembleJoueurs[i] = new JoueurPhysique();
		}
		for (int i = 0; i < nbIA; i++) {
			this.ensembleJoueurs[i + nbPhy] = new JoueurVirtuel();
		}
		
		System.out.println("Création de la défausse...");
		//this.nombreJoueurs = nbPhy; // à modifier, le nombre de joueurs = nombre de joueurs totaux
		this.nombreIA = nbIA;
		this.tasDefausse = Defausse.getInstance();
		
		
		System.out.println("Création des cartes rumeurs...");
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
		
		System.out.println("Distribution des cartes Rumeurs...");
		if (this.nombreDefausse == 2) {
			tasDefausse.defausserUneCarte(this.ensembleCartes.get(0));
			tasDefausse.defausserUneCarte(this.ensembleCartes.get(1));
			for (int i = 0; i < 5 ; i++) {
				this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes.get(i*2+2));
				this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes.get(i*2+3));
			}
		}
		else {
			for (int i = 0; i < nombreJoueurs ; i++) {
				if (nombreJoueurs == 3) {
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes.get(i*4));
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes.get(i*4+1));
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes.get(i*4+2));
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes.get(i*4+3));
				}
				else if (nombreJoueurs == 4) {
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes.get(i*3));
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes.get(i*3+1));
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes.get(i*3+2));
				}
				else { //=6
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes.get(i*2));
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes.get(i*2+1));
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
	
	public void orgaRounds() {
		int maxPoints = 0;
		//le tout premier joueur est choisi aléatoirement
		int premierJoueur = (int) (Math.random() * Instance.nombreJoueurs);
		while (maxPoints < 5) {
			
			//Les cartes des mains des joueurs, leurs cartes révélées ainsi que la défausse sont réinitialisées
			this.retirerCartes();
			
			this.distributionCartesRumeurs();
		
			//Création d'un round (contenant le déroulement du round aussi)
			Round roundEnCours = new Round(Instance, Instance.getJoueur(premierJoueur));
			
			
			//Analyse si un joueur a atteint les 5 points à l'aide de maxPoints à la fin d'un round
			for (int i=0; i<this.nombreJoueurs; i++ ) {
				if (this.ensembleJoueurs[i].getPoints() > maxPoints) {
					maxPoints = this.ensembleJoueurs[i].getPoints();
				}
			}
			
		}
		//Affichage du/des gagnant(s) de la partie.
		System.out.print("Bravo ");
		Instance.gagnants.forEach(Joueur -> System.out.print(Joueur.pseudo +", "));
		System.out.println("vous avez gagné la partie !");
		
			
		
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
	
	
	public static void main(String[] args) throws InterruptedException {
		Scanner saisieUtilisateur = new Scanner(System.in);
		System.out.println("Bienvenu dans Witch Hunt !");
		int choix = 0;
		while (choix != 2) {
			System.out.println("Que voulez vous faire (entrez l'indice de vos choix) : \n1) Lancer une nouvelle partie \n2) Quitter le programme");
			choix = saisieUtilisateur.nextInt();
			if (choix == 1) {
				
				Instance = Jeu.getInstance();
				
				Instance.orgaRounds();
				
				if (Instance.gagnants.size()>1) {
					System.out.print(". ");
					TimeUnit.SECONDS.sleep(2);
					System.out.print(". ");
					TimeUnit.SECONDS.sleep(2);
					System.out.println(". ");
					TimeUnit.SECONDS.sleep(2);
					System.out.println("Mais il ne peut avoir qu'un gagnant");
					System.out.println("Les " + Instance.gagnants.size() + " joueurs sortent un couteau et se départagent dans un duel jusqu'à la mort !");
					TimeUnit.SECONDS.sleep(2);
					while (Instance.gagnants.size()>1) {
						System.out.print(". ");
						TimeUnit.SECONDS.sleep(2);
						System.out.print(". ");
						TimeUnit.SECONDS.sleep(2);
						System.out.println(". ");
						TimeUnit.SECONDS.sleep(2);
						int i = (int) (Math.random() * Instance.gagnants.size());
						System.out.println("Le joueur " + Instance.gagnants.get(i).pseudo + " tombe au combat !");
						Instance.gagnants.remove(i);
					}
					System.out.println("Le gagnant final de ce jeu est donc le joueur : " + Instance.gagnants.get(0).pseudo);
					System.out.println("Bravo à lui et à une prochaine !");
				}
				System.exit(1);
			}
			else if (choix == 2) {
				break;
			}
			else {
				System.out.println("veuillez choisir entre 1 et 2 !\n");
			}
			
			
		}
		System.out.println("A une prochaine !");
		System.exit(1);
	}

	public Joueur[] getEnsembleJoueurs() {
		return ensembleJoueurs;
	}

	public void setEnsembleJoueurs(Joueur[] ensembleJoueurs) {
		this.ensembleJoueurs = ensembleJoueurs;
	}
}
