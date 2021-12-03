import java.util.Scanner;

public class EffetW2 extends Effet {

	public Joueur executionEffet() {

		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		int choix=0;
		
		if (instanceJeu.getAccused().isIA()) {
			System.out.println(instanceJeu.getAccused().getPseudo() + " Choisit une carte à défausser");
			if (instanceJeu.getAccused().getCarteEnMain().size() == 1) {
				choix = (int)(Math.random()*instanceJeu.getAccused().getCarteEnMain().size());
			}
			else {
				choix = (int)(Math.random()*instanceJeu.getAccused().getCarteEnMain().size());
				while (instanceJeu.getAccused().getCarteEnMain().get(choix).numCarte == 2) {
					choix = (int)(Math.random()*instanceJeu.getAccused().getCarteEnMain().size());
				}
			}
		}
		else {
			
			Scanner saisieUtilisateur = new Scanner(System.in);
			choix = saisieUtilisateur.nextInt();
			if (instanceJeu.getAccused().getCarteEnMain().size() == 1) {
				System.out.println("Vous ne pouvez défausser que cette carte même car votre main est vide");
			}
			else {
				instanceJeu.getAccused().getCarteEnMain().forEach(card -> System.out.println("TAPEZ "+instanceJeu.getAccused().getCarteEnMain().indexOf(card) + " pour défausser " + card));
				while (choix<0 || choix>instanceJeu.getAccused().getCarteEnMain().size() || instanceJeu.getAccused().getCarteEnMain().get(choix).numCarte == 2) {
					if (instanceJeu.getAccused().getCarteEnMain().get(choix).numCarte == 2) {System.out.println("Vous ne pouvez pas défausser cette carte même !");}
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