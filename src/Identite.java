import java.util.Scanner;

public class Identite {

	private boolean isWitch;
	private boolean devoilee;
	private Joueur joueurAssocie;
	
	public Identite() {
		this.isWitch = false;
		this.devoilee = false;
	}
	
	public boolean getDevoilee() {
		return this.devoilee;
	}
	
	public void ReveleIdentite() {
		this.devoilee = true;
	}
	
	public boolean getIsWitch() {
		return this.isWitch;
	}
	
	public void choisirIdentite(Joueur joueurAsso, boolean isIA) {
		
		this.joueurAssocie = joueurAsso;
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
				System.out.println("Joueur " + this.joueurAssocie.pseudo + " : Choisissez votre identité.\n\n1) Villageois.\n2) Sorcière.");
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
			else {
				System.out.println("Choix invalide ! veuillez choisir entre 1 et 2.");
			}
		}
		
		
	}
}
