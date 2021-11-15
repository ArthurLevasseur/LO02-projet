import java.util.Scanner;

public class JoueurPhysique extends Joueur {
	
	private String pseudo;
	private static int nbJoueurPhy = 0;
	
	public JoueurPhysique() {
		Scanner saisiePseudo = new Scanner(System.in);
		
		System.out.println("Ecrire le pseudo du joueur " + nbJoueurPhy);
		this.pseudo = saisiePseudo.toString();
		nbJoueurPhy += 1;
	}
	
	
}
