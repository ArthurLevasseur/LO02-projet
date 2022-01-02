package effets;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.JoueurVirtuel;

public class EvilEyeWitch extends Effet {
	
	public EvilEyeWitch() {
		super();
		this.explication = "Vous choisissez le prochain joueur.\nDurant son tour, le joueur ciblé devra accuser un joueur autre que vous, si possible.";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().evilEye(this, false);
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
		
		choix.setMustAccuse(true);
		instanceJeu.getAccused().setAccusable(false);
		return choix;
	}
}