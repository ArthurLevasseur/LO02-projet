package modele;
import java.util.Scanner;

public class SaisirInt {
	private static SaisirInt scanner;
	
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
	
	public static SaisirInt getInstance() {
		if (scanner == null) {
            scanner = new SaisirInt();
        }
        return scanner;
    }
}
