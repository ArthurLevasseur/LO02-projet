import java.util.Scanner;

public class JoueurPhysique extends Joueur {
	

	private static int nbJoueurPhy = 0;
	
	public JoueurPhysique() {
		Scanner saisiePseudo = new Scanner(System.in);
		nbJoueurPhy += 1;
		System.out.println("Ecrire le pseudo du joueur " + nbJoueurPhy);
		this.pseudo = saisiePseudo.nextLine();
	}
	
	
}
