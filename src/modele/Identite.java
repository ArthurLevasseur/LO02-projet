package modele;

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
			SaisirInt saisieUtilisateur = SaisirInt.getInstance();
			System.out.println("\n---------------Votre identité----------------\n");
			if (joueurAsso.getIdentiteAssociee().getIsWitch()) {System.out.print("Vous êtes une Witch ");} else {System.out.print("Vous êtes un Villager ");};
			if (joueurAsso.getIdentiteAssociee().getDevoilee()) {System.out.println("devoilé.\n");} else {System.out.println("encore en round.\n");};
			System.out.println("-----------------Votre main------------------\n");
			for (int i=0; i<joueurAsso.getCarteEnMain().size(); i++) {System.out.println("Carte " + i + " : " +joueurAsso.getCarteEnMain().get(i).getNomCarte() +"\n");};
			if (joueurAsso.getCarteEnMain().isEmpty()) {System.out.println("Aucunes cartes\n");}
			System.out.println("-------------Vos cartes révélées-------------\n");
			for (int i=0; i<joueurAsso.getCarteRevelees().size(); i++) {System.out.println("Carte " + i + " : " +joueurAsso.getCarteRevelees().get(i).getNomCarte() +"\n");};
			if (joueurAsso.getCarteRevelees().isEmpty()) {System.out.println("Aucunes cartes\n");}
			
			while (choix <= 0 || choix >= 3) {
				System.out.println("Joueur " + this.joueurAssocie.pseudo + " : Choisissez votre identité.\n\n1) Villageois.\n2) Sorcière.\n");
				choix = saisieUtilisateur.nextInt();
				if (choix <=0 || choix >= 3) {
					System.out.println("Choix invalide ! veuillez choisir entre 1 et 2.");
				}
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
}
