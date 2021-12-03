import java.util.Scanner;

public class EffetH3 extends Effet {
	
	public EffetH3() {
		super();
		this.explication = "Vous reprenez une de vos cartes rumeurs déjà révélées dans votre main.\nVous choisissez le prochain joueur.";
	}
	
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		Scanner saisieUtilisateur = new Scanner(System.in);
		
		if (instanceJeu.getEnTour().getCarteRevelees().isEmpty()) {
			System.out.println("Votre tas de cartes rumeurs révélées est vide !");
			if (instanceJeu.getEnTour().isIA()) {
				return instanceJeu.getJoueur(((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirProchainJoueur());
			}
			else {
				return instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
			}
		}
		else {
			if (instanceJeu.getEnTour().isIA() == false) {
				System.out.println("Voici vos cartes rumeurs révélées, choisissez la carte que vous voulez reprendre :");
				instanceJeu.getEnTour().getCarteRevelees().forEach(card -> System.out.println("TAPEZ "+instanceJeu.getEnTour().getCarteRevelees().indexOf(card) + " pour jouer " + card));
				
				int choix = saisieUtilisateur.nextInt();
				while (choix<0 || choix>instanceJeu.getEnTour().getCarteRevelees().size()) {
					System.out.println("Choix invalide !");
					choix = saisieUtilisateur.nextInt();
				}
				instanceJeu.getEnTour().prendreCarteRumeur(instanceJeu.getEnTour().getCarteRevelees().get(choix));
				
				return instanceJeu.selectionnerAdversaire(instanceJeu.getEnTour(),"Choisissez le prochain joueur.");
			}
			else {
				int choixCarte = (int) (Math.random() * instanceJeu.getEnTour().getCarteRevelees().size());
				instanceJeu.getEnTour().prendreCarteRumeur(instanceJeu.getEnTour().getCarteRevelees().get(choixCarte));
				return instanceJeu.getJoueur(((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirProchainJoueur());
			}
			
		}
		
		
		
	}
}
