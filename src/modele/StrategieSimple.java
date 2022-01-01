package modele;
	import java.util.ArrayList;
import java.util.Collections;

public class StrategieSimple extends Strategie {
	
	private int choixActionTour;
	private int choixAccuse;
	private int choixCarte;
	private int cibleHunt;
	private int joueurCibleAccuser = -1;
	
	public int choisirActionTour() {
		int choix = (int) (Math.random() * 2);
		if (choix == 1) {
			System.out.println("Il choisit d'accuser un joueur.");
			return 1;
		}
		else {
			System.out.println("Il choisit de jouer un effet hunt.");
			return 2;
		}

	}
	
	public int choisirAccuse() {
		
		Jeu instance = Jeu.getInstance();
		ArrayList<Integer> possibilites = new ArrayList<Integer>();
		
		
		for (int i=1 ; i<instance.getNombreJoueurs()+1 ; i++) {
			if (instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false && instance.getJoueur(i-1)!=instance.getEnTour() && instance.getJoueur(i-1).isAccusable()==true) {
				possibilites.add(i-1);
			}
		}
		Collections.shuffle(possibilites);
		if (possibilites == null) {
			return -1;
		}
		return possibilites.get(0);
	}
	
	public int choisirProchainJoueur() {
			
			Jeu instance = Jeu.getInstance();
			ArrayList<Integer> possibilites = new ArrayList<Integer>();
			
			
			for (int i=1 ; i<instance.getNombreJoueurs()+1 ; i++) {
				if (((instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == true && instance.getJoueur(i-1).getIdentiteAssociee().getIsWitch() == false) || instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false) && instance.getJoueur(i-1)!=instance.getEnTour()) {
					possibilites.add(i-1);
				}
			}
			Collections.shuffle(possibilites);
			System.out.println("Il décide de choisir le joueur " + instance.getJoueur(possibilites.get(0)).getPseudo());
			return possibilites.get(0);
	}
	
	public int choisirProchainJoueurWitch() {
		
		Jeu instance = Jeu.getInstance();
		ArrayList<Integer> possibilites = new ArrayList<Integer>();
		
		
		for (int i=1 ; i<instance.getNombreJoueurs()+1 ; i++) {
			if (((instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == true && instance.getJoueur(i-1).getIdentiteAssociee().getIsWitch() == false) || instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false) && instance.getJoueur(i-1)!=instance.getAccused()) {
				possibilites.add(i-1);
			}
		}
		Collections.shuffle(possibilites);
		System.out.println("Il décide de choisir le joueur " + instance.getJoueur(possibilites.get(0)).getPseudo());
		return possibilites.get(0);
}

	public int choixCarteMain() {
		Jeu instance = Jeu.getInstance();
		int choix = -1;
		while (choix < 0 || choix > instance.getEnTour().getCarteEnMain().size()) {
			choix = (int)(Math.random() * instance.getEnTour().getCarteEnMain().size());
		}
		return choix;
	}
	
	public int choixRevelees() {
		Jeu instance = Jeu.getInstance();
		int choix = -1;
		while (choix < 0 || choix > instance.getEnTour().getCarteRevelees().size()) {
			choix = (int)(Math.random() * instance.getEnTour().getCarteEnMain().size());
		}
		return choix;
	}
	
	public int seDefendre() {
		
		return 1;
	}
	
	public int choisirCarteHunt() {
		ArrayList<Integer> possiblesCartes = new ArrayList<>();
		
		for (int i = 0; i < Jeu.getInstance().getEnTour().getCarteEnMain().size(); i++) {
			if (!(((Jeu.getInstance().getEnTour().getCarteEnMain().get(i).getNumCarte()==1 || Jeu.getInstance().getEnTour().getCarteEnMain().get(i).getNumCarte()==2) && (Jeu.getInstance().getEnTour().getIdentiteAssociee().getIsWitch() == true || Jeu.getInstance().getEnTour().getIdentiteAssociee().getDevoilee() == false)) || (Jeu.getInstance().getEnTour().getCarteEnMain().get(i).getNumCarte() == 3 && Jeu.getInstance().getEnTour().getCarteRevelees().isEmpty()))) {
				possiblesCartes.add(i);
			}
		}
		
		if (possiblesCartes.isEmpty()) {
			return -1;
		}
		else {
			Collections.shuffle(possiblesCartes);
			return possiblesCartes.get(0);
		}
		
	}
	
	public int choisirCarteWitch() {
		ArrayList<Integer> possiblesCartes = new ArrayList<>();
		
		for (int i = 0; i < Jeu.getInstance().getAccused().getCarteEnMain().size(); i++) {
			if (!(Jeu.getInstance().getAccused().getCarteEnMain().get(i).getNumCarte() == 3 && Jeu.getInstance().getAccused().getCarteRevelees().isEmpty())) {
				possiblesCartes.add(i);
			}
		}
		
		if (possiblesCartes.isEmpty()) {
			return -1;
		}
		else {
			Collections.shuffle(possiblesCartes);
			return possiblesCartes.get(0);
		}
		
	}
	
	public void jouerTourIA() {
		choixActionTour = choisirActionTour();
		choixAccuse = choisirAccuse();
		choixCarte = choisirCarteHunt();
		
		if (choixCarte == -1) {
			Jeu.getInstance().getEnsembleJoueurs().forEach(joueur -> {joueur.setAccusable(true);} );
			choixActionTour = 1;
		}
		
		if (choixAccuse == -1) {
			choixActionTour = 2;
			if (choixCarte == -1) {
				Jeu.getInstance().getEnsembleJoueurs().forEach(joueur -> {joueur.setAccusable(true);} );
				choixAccuse = choisirAccuse();
				choixActionTour = 1;
			}
		}
		
		if (joueurCibleAccuser != -1 && !(Jeu.getInstance().getJoueur(joueurCibleAccuser).getIdentiteAssociee().getDevoilee()) ) {
			choixActionTour = 1;
			choixAccuse = joueurCibleAccuser;
			
		}
		else {
			joueurCibleAccuser = -1;
		}
		
		if (choixActionTour == 1) {
			//DECIDE D'ACCUSER
			Jeu.getInstance().setAccused(Jeu.getInstance().getJoueur(choixAccuse));
			Jeu.getInstance().getVueActuelle().initialize();
			Jeu.getInstance().getVueActuelle().accuser();
			Jeu.getInstance().getVueActuelle().isAccusedIA(choixAccuse);
		}
		else {
			//DECIDE JOUER CARTE
			Jeu.getInstance().getVueActuelle().initialize();
			Jeu.getInstance().getVueActuelle().choisirHunt();
			this.jouerHunt(choixCarte);
			
			
		}
	}
	
	public void repondreAccusation() {
		choixActionTour = choisirActionTour();
		choixCarte = choisirCarteWitch();
		
		if (choixCarte == -1) {
			choixActionTour = 1;
		}
		
		choixActionTour = 1;
		
		if (choixActionTour == 1) {
			this.revelerIA();
		}
		else {
			this.jouerWitch(choixCarte);
		}
	}
	
	private void jouerWitch(int emplacementCarte) {
		CarteRumeur carte = Jeu.getInstance().getAccused().getCarteEnMain().get(emplacementCarte);
		Jeu.getInstance().getAccused().getCarteRevelees().add(carte);
		Jeu.getInstance().getAccused().seFairePrendreCarteRumeur(emplacementCarte);
		
		carte.appliquerEffetWitch(Jeu.getInstance().getAccused());
		if (carte.getNumCarte() == 1) {
			Jeu.getInstance().getVueActuelle().angryMobHunt(null);
		}
	}

	public void revelerIA() {
		Jeu.getInstance().getAccused().revelerIdentite();
		Jeu.getInstance().getVueActuelle().reveler();
	}
	
	public void jouerHunt(int emplacementCarte) {
		CarteRumeur carte = Jeu.getInstance().getAccused().getCarteEnMain().get(emplacementCarte);
		Jeu.getInstance().getAccused().getCarteRevelees().add(carte);
		Jeu.getInstance().getAccused().seFairePrendreCarteRumeur(emplacementCarte);
		
		carte.appliquerEffetHunt();
		if (carte.getNumCarte() == 1) {
			cibleHunt = choisirAccuse();
			if (Jeu.getInstance().getJoueur(cibleHunt).getIdentiteAssociee().getIsWitch()) {
				Jeu.getInstance().getEnTour().setPoints(Jeu.getInstance().getEnTour().getPoints()+2);
			}
			else {
				Jeu.getInstance().getEnTour().setPoints(Jeu.getInstance().getEnTour().getPoints()-2);
			}
			Jeu.getInstance().getVueActuelle().revelerHunt(cibleHunt);
		}
		if (carte.getNumCarte() == 2) {
			cibleHunt = choisirAccuse();
			Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(cibleHunt));
			if (Jeu.getInstance().getJoueur(cibleHunt).getIdentiteAssociee().getIsWitch()) {
				joueurCibleAccuser = cibleHunt;
			}
		}
			
	}
}
