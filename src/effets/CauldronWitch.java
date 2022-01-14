package effets;
import modele.*;

/**
 * Classe de l'effet Witch de la carte Cauldron. Le joueur qui a accus� celui qui joue cette carte doit d�fausser une carte de sa main. Le joueur accus� prend le tour.
 *
 */
public class CauldronWitch extends Effet {
	
	public CauldronWitch() {
		super();
		this.explication = "Le joueur qui vous a accus� d�fausse une carte al�atoire de sa main.\nVous prenez le prochain tour.";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().chaudronWitch(this);
	}
	
	public void executionEffet() {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		CarteRumeur carteADefausser;
		
		if (instanceJeu.getEnTour().getCarteEnMain().isEmpty()) {
			carteADefausser = null;
			instanceJeu.setEnTour(instanceJeu.getAccused());
		}
		else {
			carteADefausser = instanceJeu.getEnTour().seFairePrendreCarteRumeur((int)(Math.random()*instanceJeu.getEnTour().getCarteEnMain().size()));
			instanceDefausse.defausserUneCarte(carteADefausser);
		}
		instanceJeu.setEnTour(instanceJeu.getAccused());
		instanceJeu.getVueActuelle().chaudronWitch2(this, carteADefausser);
	}
}