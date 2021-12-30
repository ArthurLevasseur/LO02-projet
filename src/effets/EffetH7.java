package effets;
import modele.CarteRumeur;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.SaisirInt;

public class EffetH7 extends Effet {
	
	public EffetH7() {
		super();
		this.explication = "Vous choisissez un joueur, il doit révéler son identité ou défausser une de ses cartes en main.\n	- Si c'est une sorcière, vous gagnez 1 points et prenez le prochain tour.\\n	- Si c'est un villageois, vous perdez 1 points et il prend le prochain tour.\\n	- S'il décide de défausser une carte, il prend le prochain tour.";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().duckingStoolHunt(this);
	}
	
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();
		
		if (instanceJeu.getEnTour().isIA()) {
			System.out.println(instanceJeu.getEnTour().getPseudo() + " Choisit un joueur à cibler avec la carte \"Un bûcher\"");
			Joueur selection = instanceJeu.getJoueur(((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirAccuse());
			int condition = -1;
			while (condition<0) {
				visable = true;
				for(CarteRumeur carte : selection.getCarteRevelees()) {
					if (carte.getNumCarte() == 6) {
						visable = false;
					}
				}
				if (visable == true) {
					condition = 1;
					
				}
				else {
					selection = instanceJeu.getJoueur(((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirAccuse());
					int compteur = 0;
					int idAutreJoueur=-1;
					for (int i=0; i<instanceJeu.getEnsembleJoueurs().size(); i++) {
						if ((instanceJeu.getJoueur(i).getIdentiteAssociee().getDevoilee() == false) && instanceJeu.getJoueur(i) != instanceJeu.getEnTour() && instanceJeu.getJoueur(i).isAccusable()==true){
							compteur += 1;
							idAutreJoueur = i;
						}
					}
					if (compteur == 1) {
						System.out.println("Aucun joueur n'est ciblable.");
						return instanceJeu.getJoueur(idAutreJoueur);
					}
				}
			}
			return selection.accusedBucher();
			
		}
		else {
			System.out.println("De quel joueur souhaitez vous réveler l'identité ?");
			Joueur selection = null;
			for (int i=1 ; i<instanceJeu.getNombreJoueurs()+1 ; i++) {
				visable = true;
				for(CarteRumeur carte : instanceJeu.getJoueur(i-1).getCarteRevelees()) {
					if (carte.getNumCarte() == 6) {
						visable = false;
					}
				}
				if (instanceJeu.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false && instanceJeu.getJoueur(i-1)!=instanceJeu.getEnTour() && visable == true) {
					//i += 1;
					System.out.println("Joueur " + (i) + ") " + instanceJeu.getJoueur(i-1).getPseudo() + " (points : " + instanceJeu.getJoueur(i-1).getPoints() + ")");
				}
			}
			
			int choix = -1;
			
			while (choix<1 || choix>instanceJeu.getNombreJoueurs() || (instanceJeu.getJoueur(choix-1).getIdentiteAssociee().getDevoilee() == true) || instanceJeu.getJoueur(choix-1) == instanceJeu.getEnTour()) {
				choix = saisieUtilisateur.nextInt();
				if (0<choix && choix<instanceJeu.getNombreJoueurs()+1 && instanceJeu.getJoueur(choix-1).getIdentiteAssociee().getDevoilee() == false && instanceJeu.getJoueur(choix-1) != instanceJeu.getEnTour()) {
					selection = instanceJeu.getJoueur(choix-1);
				}
				else {
					System.out.println("Choix invalide !");
				}
			}
			
			
			return selection.accusedBucher();
		}
	}
}
