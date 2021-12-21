package effets;
import modele.CarteRumeur;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.SaisirInt;

public class EffetH1 extends Effet {
	
	
	public EffetH1() {
		super();
		this.explication = "Vous révélez l'identité d'un autre joueur.\n	- Si c'est une sorcière, vous gagnez 2 points et prenez le prochain tour.\n	- Si c'est un villageois, vous perdez 2 points et il prend le prochain tour.";
	}

	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();
		Joueur selection = null;
		
		if (instanceJeu.getEnTour().isIA() == false) {
			System.out.println("De quel joueur souhaitez vous réveler l'identité ?");
			for (int i=1 ; i<instanceJeu.getNombreJoueurs()+1 ; i++) {
				visable = true;
				for(CarteRumeur carte : instanceJeu.getJoueur(i-1).getCarteRevelees()) {
					if (carte.getNumCarte() == 5) {
						visable = false;
					}
				}
				if (instanceJeu.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false && instanceJeu.getJoueur(i-1)!=instanceJeu.getEnTour() && visable == true) {
					
					System.out.println("Joueur " + (i) + ") " + instanceJeu.getJoueur(i-1).getPseudo() + " (points : " + instanceJeu.getJoueur(i-1).getPoints() + ")");
				}
			}
			
			int choix = -1;
			
			while (choix<0 || choix>instanceJeu.getNombreJoueurs() || instanceJeu.getJoueur(choix).getIdentiteAssociee().getDevoilee() == true || instanceJeu.getJoueur(choix)==instanceJeu.getEnTour()) {
				choix = saisieUtilisateur.nextInt();
				visable = true;
				for(CarteRumeur carte : instanceJeu.getJoueur(choix-1).getCarteRevelees()) {
					if (carte.getNumCarte() == 5) {
						visable = false;
					}
				}
				if (0<choix && choix<instanceJeu.getNombreJoueurs()+1 && instanceJeu.getJoueur(choix)!=instanceJeu.getEnTour() && visable == true) {
					selection = instanceJeu.getJoueur(choix-1);
				}
				else {
					System.out.println("Choix invalide !");
					choix = -1;
				}
			}
		}
		else {
			System.out.print(instanceJeu.getEnTour().getPseudo() + " choisit un joueur pour révéler son identité");
			
			int condition = -1;
			while (condition < 0) {
				int choix = ((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirAccuse();
				visable = true;
				for(CarteRumeur carte : instanceJeu.getJoueur(choix).getCarteRevelees()) {
					if (carte.getNumCarte() == 5) {
						visable = false;
					}
				}
				if (0<=choix && choix<instanceJeu.getEnsembleJoueurs().size() && instanceJeu.getJoueur(choix)!=instanceJeu.getEnTour() && visable == true) {
					selection = instanceJeu.getJoueur(choix);
					condition = 1;
				}
				else {
					condition = -1;
				}
			}
		}

		
		if (selection.getIdentiteAssociee().getIsWitch() == true) {
			System.out.println("Ce joueur est bien une Witch, vous remportez 2 points.");
			instanceJeu.getEnTour().ajouterPoints(2);
			selection.getIdentiteAssociee().ReveleIdentite();
			return instanceJeu.getEnTour();
		}
		else {
			System.out.println("Ce joueur est un villager, vous perdez 2 points.");
			instanceJeu.getEnTour().ajouterPoints(-2);
			selection.getIdentiteAssociee().ReveleIdentite();
			return selection;
		}
		
		
	
	}
	
}
