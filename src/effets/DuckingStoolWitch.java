package effets;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;

public class DuckingStoolWitch extends Effet {
	
	public DuckingStoolWitch() {
		super();
		this.explication = "Vous choisissez le prochain joueur.";
	}

	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().choisirProchainJoueurWitch(this);
	}
	
	public Joueur executionEffet() {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		Joueur choix;
		
		if (instanceJeu.getAccused().isIA()) {
			System.out.println(instanceJeu.getAccused().getPseudo() + " choisit le prochain joueur.");
			choix = instanceJeu.getJoueur(((JoueurVirtuel) instanceJeu.getAccused()).getStrategieActuelle().choisirProchainJoueurWitch());
		}
		else {
			choix = instanceJeu.selectionnerAdversaire(instanceJeu.getAccused(),"Choisissez le prochain joueur.");
		}
		
		return choix;
	}
}