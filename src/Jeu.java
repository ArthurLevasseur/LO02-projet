import java.util.Scanner;

public class Jeu {
	
	private int nombreJoueurs;
	private int nombreIA;
	private int nombreDefausse;
	private static Jeu Instance;
	private Joueur enTour;
	private Joueur accuse;
	private CarteRumeur[] ensembleCartes;
	private Joueur[] ensembleJoueurs;
	private Defausse tasDefausse;
	
	private Jeu(int nbPhy, int nbIA){
		
		System.out.println("Cr�ation des joueurs...");
		ensembleJoueurs = new Joueur[nbPhy + nbIA];
		for (int i = 0; i < nbPhy; i++) {
			ensembleJoueurs[i] = new JoueurPhysique();
		}
		for (int i = 0; i < nbIA; i++) {
			ensembleJoueurs[i + nbPhy] = new JoueurVirtuel();
		}
		
		System.out.println("Cr�ation de la d�fausse...");
		this.nombreJoueurs = nbPhy;
		this.nombreIA = nbIA;
		if (nbPhy + nbIA == 5) {
			this.nombreDefausse = 2;
		}
		else {
			this.nombreDefausse = 0;
		}
		tasDefausse = new Defausse();
		
		this.Instance = this;
		
		System.out.println("Cr�ation des cartes rumeurs...");
		this.ensembleCartes = new CarteRumeur[12];
		for (int i = 0; i < 12; i++) {
			this.ensembleCartes[i] = new CarteRumeur();
		}
	}
	/* TEST DU TABLEAU DE CARTES DU DEBUT
	public void afficherCartes() {
		for (int i = 0; i < 12; i++) {
			System.out.println(this.ensembleCartes[i]);
		}
	}
	*/
	
	public void distributionCartesRumeurs() {
		CarteRumeur transition;
		System.out.println("M�lange des cartes Rumeurs...");
		int carteEch1;
		int carteEch2;
		for (int i = 0; i < 100; i++) {
			carteEch1 = (int) (Math.random() * ( 13 - 1 ));
			carteEch2 = (int) (Math.random() * ( 13 - 1 ));
			if (carteEch1 != carteEch2) {
				transition = this.ensembleCartes[carteEch1];
				this.ensembleCartes[carteEch1] = this.ensembleCartes[carteEch2];
				this.ensembleCartes[carteEch2] = transition;
			}
		}
		
		System.out.println("Distribution des cartes Rumeurs...");
		if (this.nombreDefausse == 2) {
			tasDefausse.defausserUneCarte(ensembleCartes[0]);
			tasDefausse.defausserUneCarte(ensembleCartes[1]);
			for (int i = 0; i < 5 ; i++) {
				this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes[i*2+2]);
				this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes[i*2+3]);
			}
		}
		else {
			for (int i = 0; i < nombreIA + nombreJoueurs ; i++) {
				if (nombreIA + nombreJoueurs == 3) {
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes[i*4]);
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes[i*4+1]);
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes[i*4+2]);
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes[i*4+3]);
				}
				else if (nombreIA + nombreJoueurs == 4) {
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes[i*3]);
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes[i*3+1]);
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes[i*3+2]);
				}
				else { //=6
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes[i*2]);
					this.ensembleJoueurs[i].prendreCarteRumeur(this.ensembleCartes[i*2+1]);
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
	
	public static Joueur getInstance() {
		
	}
	
	public static void main(String[] args) {
		Scanner saisieUtilisateur = new Scanner(System.in);
		System.out.println("Bienvenu dans Witch Hunt !");
		int choix = 0;
		while (choix != 2) {
			//initialisation du nombre de joueurs physiques
			System.out.println("Que voulez vous faire (entrez l'indice de vos choix) : \n1) Lancer une nouvelle partie \n2) Quitter le programme");
			choix = saisieUtilisateur.nextInt();
			if (choix == 1) {
				System.out.println("Combien de joueurs physiques �tes-vous? (entre 1 et 6)");
				choix = 0;
				int joueursPhysiques = 0;
				while (choix < 1 || choix > 6) {
					choix = saisieUtilisateur.nextInt();
					if (choix < 1 || choix > 6) {
						System.out.println("Veuillez choisir entre 1 et 6 joueurs");
					}
					else {
						joueursPhysiques = choix;
					}
				}
				
				//initialisation du nombre de joueurs virtuels
				System.out.println("Combien de joueurs virtuels voulez vous dans votre partie ? (minimum 3 et maximum 6 joueurs physiques et virtuels en combin�s)");
				choix = 0;
				int joueursVirtuels = 0;
				while ((choix + joueursPhysiques < 3 || choix + joueursPhysiques > 6) || (choix < 1 || choix > 6)) {
					choix = saisieUtilisateur.nextInt();
					if ((choix + joueursPhysiques < 3 || choix + joueursPhysiques > 6) || (choix < 1 || choix > 6)) {
						System.out.println("Choix invalide");
					}
					else {
						joueursVirtuels = choix;
					}
				}
				System.out.println("La partie va commencer, configuration : \n	- Nombre de joueurs physiques : " + joueursPhysiques + "\n	- Nombre de joueurs virtuels : " + joueursVirtuels);
				
				Jeu instanceJeu = new Jeu(joueursPhysiques, joueursVirtuels);
				instanceJeu.distributionCartesRumeurs();
				
				break;
				
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
}
