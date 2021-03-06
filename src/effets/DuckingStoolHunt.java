package effets;
import modele.CarteRumeur;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.SaisirInt;

/**
 * Classe de l'effet hunt de la carte DuckingStool. Le joueur qui joue cette carte en choisit un autre, qui doit r?veler son identit? ou d?fausser une de ses cartes en main. Si c'est une sorci?re, le joueur qui a choisit l'autre gagne un point et prend le tour. Si c'est un villageois, il prend le tour et le joueur qui joue la carte perd un point. S'il d?cide de d?fausser une carte, il prend le prochain tour et aucun point n'est gagn? ou perdu.
 * 
 */

public class DuckingStoolHunt extends Effet {
	
	public DuckingStoolHunt() {
		super();
		this.explication = "Vous choisissez un joueur, il doit r?v?ler son identit? ou d?fausser une de ses cartes en main.\n	- Si c'est une sorci?re, vous gagnez 1 points et prenez le prochain tour.\\n	- Si c'est un villageois, vous perdez 1 points et il prend le prochain tour.\\n	- S'il d?cide de d?fausser une carte, il prend le prochain tour.";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().duckingStoolHunt(this);
	}
	
	public void executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();
		
		if (instanceJeu.getEnTour().isIA()) {
			System.out.println(instanceJeu.getEnTour().getPseudo() + " Choisit un joueur ? cibler avec la carte \"Un b?cher\"");
			int choix = ((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirAccuse();
			if (choix != - 1) {
				Joueur selection = instanceJeu.getJoueur(choix);
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
							instanceJeu.setEnTour(instanceJeu.getJoueur(idAutreJoueur));
						}
					}
				}
				instanceJeu.setEnTour(selection.accusedBucher());
			}
			else {
				instanceJeu.setEnTour(instanceJeu.getEnTour());
			}
			
			
		}
		else {
			System.out.println("De quel joueur souhaitez vous r?veler l'identit? ?");
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
			
			
			instanceJeu.setEnTour(selection.accusedBucher());
		}
	}
}
