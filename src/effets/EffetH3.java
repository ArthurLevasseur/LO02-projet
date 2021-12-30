package effets;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.SaisirInt;

public class EffetH3 extends Effet {
	
	public EffetH3() {
		super();
		this.explication = "Vous reprenez une de vos cartes rumeurs déjà révélées dans votre main.\nVous choisissez le prochain joueur.";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().takeBackACard(this);
		instanceJeu.getVueActuelle().askOpponent(this);
	}
	
	/*public void eeeeeeeexecutionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();
		
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
	*/
	public void executionEffet(int choix) {
		
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getEnTour().prendreCarteRumeur(instanceJeu.getEnTour().getCarteRevelees().get(choix));
		
	}
	
	public void executionEffet(Joueur selection) {
		
		Jeu instanceJeu = Jeu.getInstance();
		
		if (instanceJeu.getEnTour().isIA()) {
			instanceJeu.setEnTour(instanceJeu.getJoueur(((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirProchainJoueur()));
		}
		else {
			instanceJeu.setEnTour(selection); // à mvciser
		}
	}
}
