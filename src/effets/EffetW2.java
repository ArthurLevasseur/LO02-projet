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
		this.explication = "Vous d�faussez une carte de votre main.\nVous prenez le prochain tour.";
	}


	public Joueur executionEffet() {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		int choix=0;
		
		if (instanceJeu.getAccused().isIA()) {
			System.out.println(instanceJeu.getAccused().getPseudo() + " Choisit une carte � d�fausser");
			if (instanceJeu.getAccused().getCarteEnMain().size() == 1) {
				choix = (int)(Math.random()*instanceJeu.getAccused().getCarteEnMain().size());
			}
			else {
				choix = (int)(Math.random()*instanceJeu.getAccused().getCarteEnMain().size());
				while (instanceJeu.getAccused().getCarteEnMain().get(choix).getNumCarte() == 2) {
					choix = (int)(Math.random()*instanceJeu.getAccused().getCarteEnMain().size());
				}
			}
		}
		
		else {
			
			SaisirInt saisieUtilisateur = SaisirInt.getInstance();
			choix = saisieUtilisateur.nextInt();
			if (instanceJeu.getAccused().getCarteEnMain().size() == 1) {
				System.out.println("Vous ne pouvez d�fausser que cette carte m�me car votre main est vide");
			}
			else {
				instanceJeu.getAccused().getCarteEnMain().forEach(card -> System.out.println("TAPEZ "+instanceJeu.getAccused().getCarteEnMain().indexOf(card) + " pour d�fausser " + card));
				while (choix<0 || choix>instanceJeu.getAccused().getCarteEnMain().size() || instanceJeu.getAccused().getCarteEnMain().get(choix).getNumCarte() == 2) {
					if (instanceJeu.getAccused().getCarteEnMain().get(choix).getNumCarte() == 2) {System.out.println("Vous ne pouvez pas d�fausser cette carte m�me !");}
					else {System.out.println("Choix invalide !");
					}
					choix = saisieUtilisateur.nextInt();
				}
			}
		}
		
		CarteRumeur carteADefausser = instanceJeu.getAccused().getCarteEnMain().get(choix);
		instanceDefausse.defausserUneCarte(carteADefausser);
		return instanceJeu.getAccused();
	}
}