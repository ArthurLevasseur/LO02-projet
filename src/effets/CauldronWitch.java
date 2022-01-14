package effets;
import modele.*;

/**
 * Classe de l'effet Witch de la carte Cauldron. Le joueur qui a accusé celui qui joue cette carte doit défausser une carte de sa main. Le joueur accusé prend le tour.
 *
 */
public class CauldronWitch extends Effet {
	
	public CauldronWitch() {
		super();
		this.explication = "Le joueur qui vous a accusé défausse une carte aléatoire de sa main.\nVous prenez le prochain tour.";
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