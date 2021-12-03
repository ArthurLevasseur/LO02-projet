import java.util.Scanner;

public class EffetW8 extends Effet {

	public Joueur executionEffet() {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		Joueur choix;
		
		if (instanceJeu.getAccused().isIA()) {
			System.out.println(instanceJeu.getAccused().getPseudo() + " choisit le prochain joueur.");
			choix = instanceJeu.getJoueur(((JoueurVirtuel) instanceJeu.getEnTour()).getStrategieActuelle().choisirProchainJoueurWitch());
		}
		else {
			choix = instanceJeu.selectionnerAdversaire(instanceJeu.getAccused(),"Choisissez le joueur � d�fausser.");
		}
		if (choix.getCarteEnMain().isEmpty()) {
			System.out.println("Il n'a pas de cartes en main.");
		}
		else {
			instanceDefausse.defausserUneCarte(choix.seFairePrendreCarteRumeur((int)(Math.random()*choix.getCarteEnMain().size())));
		}
		return instanceJeu.getAccused();
	}
}