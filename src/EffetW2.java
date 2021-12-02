import java.util.Scanner;

public class EffetW2 extends Effet {

	public Joueur executionEffet() {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();

		instanceJeu.getAccused().getCarteEnMain().forEach(card -> System.out.println("TAPEZ "+instanceJeu.getAccused().getCarteEnMain().indexOf(card) + " pour défausser " + card));
		Scanner saisieUtilisateur = new Scanner(System.in);
		int choix = saisieUtilisateur.nextInt();
		while (choix<0 || choix>instanceJeu.getAccused().getCarteEnMain().size() || instanceJeu.getAccused().getCarteEnMain().get(choix).numCarte == 2) {
			if (instanceJeu.getAccused().getCarteEnMain().get(choix).numCarte == 2) {System.out.println("Vous ne pouvez pas défausser cette carte même !");}
			else {System.out.println("Choix invalide !");
			}
			choix = saisieUtilisateur.nextInt();
		}
		CarteRumeur carteADefausser = instanceJeu.getAccused().getCarteEnMain().get(choix);
		instanceDefausse.defausserUneCarte(carteADefausser);
		return instanceJeu.getAccused();
	}
}