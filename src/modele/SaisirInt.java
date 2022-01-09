package modele;
import java.util.Scanner;

/**
 * Classe permettant la saisie d'un nombre entier dans la console.
 */

public class SaisirInt {
	private static SaisirInt scanner;
	
	/**
	 * Fonction à appeler pour réaliser la saisie d'un entier dans la console.
	 * @return L'entier saisi.
	 */
	
	public int nextInt() {
		int value;
		Scanner sc1 = new Scanner(System.in);
		try{
			
			System.out.print("Saisir : ");
			value = Integer.parseInt( sc1.nextLine() );				
		} catch (NumberFormatException e){
			System.out.println("Veuillez saisir un nombre entier !");
			value = this.nextInt();
		}
		System.out.print("\n");
		return value;
	}
	
	/**
	 * Retourne l'instance de saisirInt. Utilisé pour la réalisation du patron de conception singleton.
	 * @return L'instance de saisirInt
	 */
	
	public static SaisirInt getInstance() {
		if (scanner == null) {
            scanner = new SaisirInt();
        }
        return scanner;
    }
}
