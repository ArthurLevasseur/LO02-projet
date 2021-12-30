package effets;
import modele.CarteRumeur;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.SaisirInt;

public class EffetW2 extends Effet {
	
	public EffetW2() {
		super();
		this.explication = "Vous défaussez une carte de votre main.\nVous prenez le prochain tour.";
	}

	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().discard(this);
	}

	public void executionEffet(int choix) {
		
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		CarteRumeur carteADefausser = instanceJeu.getAccused().getCarteEnMain().get(choix);
		instanceDefausse.defausserUneCarte(carteADefausser);
		instanceJeu.setEnTour(instanceJeu.getAccused());
		
	}
}