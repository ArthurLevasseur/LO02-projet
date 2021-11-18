import java.util.Scanner;

public class Identite {

	private boolean isWitch;
	private boolean devoilee;
	
	public Identite() {
		this.isWitch = false;
		this.devoilee = false;
	}
	
	public void choisirIdentite(boolean isIA) {
		if (isIA) {
			int choix = (int)Math.random()*2;
			
			if (choix == 0) {
				this.isWitch = false;
				this.devoilee = false;
			}
			
			else if (choix == 1) {
				this.isWitch = true;
				this.devoilee = false;
			}
			
		}
		
		else {
			int choix = 0;
			Scanner saisieUtilisateur = new Scanner(System.in);
			while (choix <= 0 || choix >= 3) {
				System.out.println("Choisissez votre identité. 1 pour Villageois, 2 pour sorcière.");
				choix = saisieUtilisateur.nextInt();
			}
			
			if (choix == 1) {
				this.isWitch = false;
				this.devoilee = false;
			}
			
			else if (choix == 2) {
				this.isWitch = true;
				this.devoilee = false;
			}
		}
		
		
	}
	
	public void revelerIdentite() {
		
	}
}
