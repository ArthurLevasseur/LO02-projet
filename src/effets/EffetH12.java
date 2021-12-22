package effets;
import controleur.Jeu;
import modele.Defausse;
import modele.Effet;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.SaisirInt;

public class EffetH12 extends Effet {
	
	public EffetH12() {
		super();
		this.explication = "Prenez une carte rumeur révélée de n'importe quel joueur dans votre main.\nVous choisissez le prochain joueur.";
	}
	
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();
		Joueur choix;

		if (instanceJeu.getEnTour().isIA()) {
			choix = instanceJeu.getJoueur(((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirProchainJoueur());
		}
		else {
			choix = instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"De quel joueur souhaitez vous voler une carte rumeur révélée ?");
		}
		
		if (choix.getCarteRevelees().isEmpty()) {
			System.out.println("Son tas de cartes rumeurs révélées est vide, dommage !");
			if (instanceJeu.getEnTour().isIA() == false) {
				return instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
			}
			else {
				int choixJoueur = ((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirProchainJoueur();
				return instanceJeu.getJoueur(choixJoueur);
			}
			
		}
		else {
			if (instanceJeu.getEnTour().isIA() == false) {
				System.out.println("Voici ses cartes rumeurs révélées, choisissez la carte que vous voulez reprendre :");
				choix.getCarteRevelees().forEach(card -> System.out.println("TAPEZ "+choix.getCarteRevelees().indexOf(card) + " pour jouer " + card));
				int choixCarte = saisieUtilisateur.nextInt();
				while (choixCarte<0 || choixCarte>choix.getCarteRevelees().size()) {
					System.out.println("Choix invalide !");
					choixCarte = saisieUtilisateur.nextInt();
				}
				instanceJeu.getEnTour().prendreCarteRumeur(choix.getCarteRevelees().get(choixCarte));
				
				return instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
			}
			else {
				int choixCarte = (int) (Math.random() * choix.getCarteRevelees().size());
				instanceJeu.getEnTour().prendreCarteRumeur(choix.getCarteRevelees().get(choixCarte));
				return instanceJeu.getJoueur(((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirProchainJoueur());
			}
			
		}
		
		

	}
}
