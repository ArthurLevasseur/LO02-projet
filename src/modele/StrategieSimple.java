package modele;
	import java.util.ArrayList;
import java.util.Collections;

public class StrategieSimple extends Strategie {
	
	private int choixActionTour;
	private int choixCarte;
	private int joueurCibleAccuser = -1;
	private int choixCarteJoueur;
	private int choixCartePrise;
	
	private Joueur joueurEnTour;
	
	private int choixAccuse;
	private int choixCarteHunt;
	private int choixCarteHuntCarte;
	private int cibleHunt;
	private int secondeCibleHunt;
	
	public void reinitialiserAttributs() {
		
		//WITCH
		this.choixActionTour = -1;
		this.choixCarte = -1;
		this.choixCarteJoueur = -1;
		this.choixCartePrise = -1;
		this.joueurEnTour = null;
		
		//HUNT
		this.choixAccuse = -1;
		this.choixCarteHunt = -1;
		this.choixCarteHuntCarte = -1;
		this.cibleHunt = -1;
		this.secondeCibleHunt = -1;
	}
	
	public int choisirActionTour() {
		int choix = (int) (Math.random() * 1.5);
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
			
			
			for (int i=0 ; i<instance.getNombreJoueurs() ; i++) {
				if (((instance.getJoueur(i).getIdentiteAssociee().getDevoilee() == true && instance.getJoueur(i).getIdentiteAssociee().getIsWitch() == false) || instance.getJoueur(i).getIdentiteAssociee().getDevoilee() == false) && instance.getJoueur(i)!=instance.getEnTour()) {
					possibilites.add(i);
				}
			}
			Collections.shuffle(possibilites);
			System.out.println("Il décide de choisir le joueur " + instance.getJoueur(possibilites.get(0)).getPseudo());
			return possibilites.get(0);
	}
	
	public int choisirAutreJoueur() {
		
		Jeu instance = Jeu.getInstance();
		ArrayList<Integer> possibilites = new ArrayList<Integer>();
		
		
		for (int i=0 ; i<instance.getNombreJoueurs() ; i++) {
			if (instance.getJoueur(i)!=instance.getEnTour()) {
				possibilites.add(i);
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

	public int choixCarteMainWitch() {
		Jeu instance = Jeu.getInstance();
		int choix = (int)(Math.random() * instance.getAccused().getCarteEnMain().size());
		if (instance.getAccused().getCarteEnMain().isEmpty()) {
			return -1;
		}
		return choix;
	}
	
	public int choixCarteMainWitchTheInquisition() {
		Jeu instance = Jeu.getInstance();
		int choix = (int)(Math.random() * instance.getAccused().getCarteEnMain().size());
		if (instance.getAccused().getCarteEnMain().isEmpty() || (instance.getAccused().getCarteEnMain().size() == 1 && instance.getAccused().getCarteEnMain().get(0).getNumCarte() == 2)) {
			return -1;
		}
		return choix;
	}

	public int choixReveleesHunt() {
		Jeu instance = Jeu.getInstance();
		int choix = (int)(Math.random() * instance.getEnTour().getCarteRevelees().size());
		if (instance.getEnTour().getCarteRevelees().isEmpty()) {
			return -1;
		}
		return choix;
	}

	public int choixReveleesWitch() {
		Jeu instance = Jeu.getInstance();
		int choix = (int)(Math.random() * instance.getAccused().getCarteRevelees().size());
		if (instance.getAccused().getCarteRevelees().isEmpty()) {
			return -1;
		}
		else {
			return choix;
		}
	}
	
	public int choixReveleesWitchPointedHat() {
		Jeu instance = Jeu.getInstance();
		if (instance.getAccused().getCarteRevelees().isEmpty() || (instance.getAccused().getCarteRevelees().size() == 1 && instance.getAccused().getCarteRevelees().get(0).getNumCarte() == 3)) {
			return -1;
		}
		int choix = (int)(Math.random() * instance.getAccused().getCarteRevelees().size());
		while (instance.getAccused().getCarteRevelees().get(choix).getNumCarte() == 3) {
			choix = (int)(Math.random() * instance.getAccused().getCarteRevelees().size());
		}
		
		return choix;
	}
	
	public int choixReveleesHuntPointedHat() {
		Jeu instance = Jeu.getInstance();
		if (instance.getEnTour().getCarteRevelees().isEmpty() || (instance.getEnTour().getCarteRevelees().size() == 1 && instance.getEnTour().getCarteRevelees().get(0).getNumCarte() == 3)) {
			return -1;
		}
		int choix = (int)(Math.random() * instance.getEnTour().getCarteRevelees().size());
		while (instance.getEnTour().getCarteRevelees().get(choix).getNumCarte() == 3) {
			choix = (int)(Math.random() * instance.getEnTour().getCarteRevelees().size());
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

		choixActionTour = 2;
		
		if (choixCarte == -1) {
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
		if (choixCarte != -1) {
			System.out.println("choix Carte :" + Jeu.getInstance().getEnTour().getCarteEnMain().get(choixCarte).getNomCarte());
		}
		System.out.println("Joueur en tour :" + Jeu.getInstance().getEnTour().getPseudo());
		System.out.println("ChoixAction : " + choixActionTour);
		
		if (joueurCibleAccuser != -1 && !(Jeu.getInstance().getJoueur(joueurCibleAccuser).getIdentiteAssociee().getDevoilee()) ) {
			choixActionTour = 1;
			choixAccuse = joueurCibleAccuser;
			joueurCibleAccuser = -1;

		}
		else {
			joueurCibleAccuser = -1;
		}
		
		joueurEnTour = Jeu.getInstance().getEnTour();
		
		if (choixActionTour == 1) {
			//DECIDE D'ACCUSER
			Jeu.getInstance().setAccused(Jeu.getInstance().getJoueur(choixAccuse));
			Jeu.getInstance().getVueActuelle().initialize();
			Jeu.getInstance().getVueActuelle().accuser();
			Jeu.getInstance().getVueActuelle().recapIAHunt(joueurEnTour, choixActionTour, choixAccuse, choixCarteHunt, cibleHunt, choixCarteHuntCarte, secondeCibleHunt);
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
		int emplacementCarte = choisirCarteWitch();
		
		if (emplacementCarte == -1) {
			choixActionTour = 1;
		}
		else {
			choixCarte = Jeu.getInstance().getAccused().getCarteEnMain().get(emplacementCarte).getNumCarte();
			System.out.println("Carte witch jouée : " + choixCarte);
		}

		
		
		System.out.println(choixActionTour);
		if (choixActionTour == 1) {
			this.revelerIA();
			Jeu.getInstance().getVueActuelle().passerTourSuivant();
		}
		else {
			Jeu.getInstance().getVueActuelle().choisirWitch();
			this.jouerWitch(emplacementCarte);
		}
		
	}
	
	private void jouerWitch(int emplacementCarte) {
		
		CarteRumeur carte = Jeu.getInstance().getAccused().getCarteEnMain().get(emplacementCarte);
		Jeu.getInstance().getAccused().getCarteRevelees().add(carte);
		Jeu.getInstance().getAccused().seFairePrendreCarteRumeur(emplacementCarte);
		
		carte.appliquerEffetWitch(Jeu.getInstance().getAccused());

		if (carte.getNumCarte() == 2) {
			int emplacementCartePrise = choixCarteMainWitchTheInquisition();
			if (emplacementCartePrise != -1) {
				choixCartePrise = Jeu.getInstance().getAccused().getCarteEnMain().get(emplacementCartePrise).getNumCarte();
				Jeu.getInstance().getTasDefausse().defausserUneCarte(Jeu.getInstance().getAccused().seFairePrendreCarteRumeur(emplacementCartePrise));
			}
			Jeu.getInstance().setEnTour(Jeu.getInstance().getAccused());
			Jeu.getInstance().getVueActuelle().passerTourSuivant();
		}
		if (carte.getNumCarte() == 3) {
			int emplacementCartePrise = choixReveleesWitchPointedHat();
			if (emplacementCartePrise != -1) {
				choixCartePrise = Jeu.getInstance().getAccused().getCarteRevelees().get(emplacementCartePrise).getNumCarte();
				Jeu.getInstance().getAccused().prendreCarteRumeur(Jeu.getInstance().getAccused().getCarteRevelees().get(emplacementCartePrise));
				Jeu.getInstance().getAccused().getCarteRevelees().remove(emplacementCartePrise);
			}
			Jeu.getInstance().setEnTour(Jeu.getInstance().getAccused());
			Jeu.getInstance().getVueActuelle().passerTourSuivant();
		}
		if (carte.getNumCarte() == 4) {
			int emplacementCartePrise = (int) (Math.random() * Jeu.getInstance().getEnTour().getCarteEnMain().size());
			if (!(Jeu.getInstance().getEnTour().getCarteEnMain().isEmpty())) {
				choixCartePrise = Jeu.getInstance().getEnTour().getCarteEnMain().get(emplacementCartePrise).getNumCarte();
				Jeu.getInstance().getAccused().getCarteEnMain().add(Jeu.getInstance().getEnTour().seFairePrendreCarteRumeur(emplacementCartePrise));
			}
			Jeu.getInstance().setEnTour(Jeu.getInstance().getAccused());
			Jeu.getInstance().getVueActuelle().passerTourSuivant();
		}
		if (carte.getNumCarte() == 7) {
			int prochainJoueur = choisirProchainJoueurWitch();
			choixCarteJoueur = prochainJoueur;
			Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(prochainJoueur));
			Jeu.getInstance().getVueActuelle().passerTourSuivant();
		}
		if (carte.getNumCarte() == 8) {

			Jeu.getInstance().getVueActuelle().passerTourSuivant();
		}
		
		if (carte.getNumCarte() == 9) {
			
			int prochainJoueur = choisirProchainJoueurWitch();
			choixCarteJoueur = prochainJoueur;
			Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(prochainJoueur));
			Jeu.getInstance().getAccused().setAccusable(false);
			Jeu.getInstance().getVueActuelle().passerTourSuivantAccusable();
		}
	}

	public void revelerIA() {
		Jeu.getInstance().getAccused().revelerIdentite();
		Jeu.getInstance().getVueActuelle().reveler();
	}
	
	public void jouerHunt(int emplacementCarte) {
		CarteRumeur carte = Jeu.getInstance().getEnTour().getCarteEnMain().get(emplacementCarte);
		choixCarteHunt = carte.getNumCarte();
		Jeu.getInstance().getEnTour().getCarteRevelees().add(carte);
		Jeu.getInstance().getEnTour().seFairePrendreCarteRumeur(emplacementCarte);
		joueurEnTour = Jeu.getInstance().getEnTour();
		
		System.out.println("Cartes en main : " + Jeu.getInstance().getEnTour().getCarteEnMain().size());
		System.out.println("Cartes révélées : " + Jeu.getInstance().getEnTour().getCarteRevelees().size());
		
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
			cibleHunt = choisirProchainJoueur();
			Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(cibleHunt));
			if (Jeu.getInstance().getJoueur(cibleHunt).getIdentiteAssociee().getIsWitch()) {
				joueurCibleAccuser = cibleHunt;
			}
		}
		if (carte.getNumCarte() == 3) {
			int emplacementCartePrise = choixReveleesHuntPointedHat();
			choixCarteHuntCarte = Jeu.getInstance().getEnTour().getCarteRevelees().get(emplacementCartePrise).getNumCarte();
			
			CarteRumeur carteRecup = Jeu.getInstance().getEnTour().getCarteRevelees().get(emplacementCartePrise);
			Jeu.getInstance().getEnTour().prendreCarteRumeur(carteRecup);
			Jeu.getInstance().getEnTour().getCarteRevelees().remove(emplacementCartePrise);
			
			cibleHunt = choisirProchainJoueur();
			Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(cibleHunt));
		}
		if (carte.getNumCarte() == 4) {
			cibleHunt = choisirProchainJoueur();
			if (Jeu.getInstance().getJoueur(cibleHunt).getCarteEnMain().isEmpty()) {
				Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(cibleHunt));
			}
			else {
				int randomCard = (int) (Math.random() * Jeu.getInstance().getJoueur(cibleHunt).getCarteEnMain().size());
				Jeu.getInstance().getVueActuelle().afficherCarteVolee(Jeu.getInstance().getJoueur(cibleHunt).getCarteEnMain().get(randomCard));
				Jeu.getInstance().getEnTour().getCarteEnMain().add(Jeu.getInstance().getJoueur(cibleHunt).seFairePrendreCarteRumeur(randomCard));
				Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(cibleHunt));
			}
		}
		if (carte.getNumCarte() == 5 || carte.getNumCarte() == 6 ) {
			cibleHunt = choisirProchainJoueur();
			Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(cibleHunt));
		}
		if (carte.getNumCarte() == 7) {
			cibleHunt = choisirAccuse();
			
		}
		if (carte.getNumCarte() == 8 || carte.getNumCarte() == 10) {
			if (!Jeu.getInstance().getEnTour().getIdentiteAssociee().getIsWitch()) {
				cibleHunt = choisirProchainJoueur();
				Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(cibleHunt));
			}
		}
		if (carte.getNumCarte() == 9) {
			cibleHunt = choisirProchainJoueur();
			Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(cibleHunt));
		}
		if (carte.getNumCarte() == 11) {//
			
			if (!Jeu.getInstance().getTasDefausse().getContenu().isEmpty()) {
				int emplacementCartePrise = choisirCarteDefausse();
				choixCarteHuntCarte = Jeu.getInstance().getTasDefausse().getContenu().get(emplacementCartePrise).getNumCarte();
				
				Jeu.getInstance().getEnTour().prendreCarteRumeur(Jeu.getInstance().getTasDefausse().getContenu().get(emplacementCartePrise));
				Jeu.getInstance().getTasDefausse().getContenu().remove(emplacementCartePrise);

			}
			else {
				choixCarteHuntCarte = -1;
			}
			Jeu.getInstance().getTasDefausse().getContenu().add(Jeu.getInstance().getEnTour().getCarteRevelees().get(Jeu.getInstance().getEnTour().getCarteRevelees().size() - 1));
			Jeu.getInstance().getEnTour().getCarteRevelees().remove(Jeu.getInstance().getEnTour().getCarteRevelees().size() - 1);
		}
		if (carte.getNumCarte() == 12) {
			
			secondeCibleHunt = choisirAutreJoueur();
			Jeu.getInstance().setAccused(Jeu.getInstance().getJoueur(secondeCibleHunt));
			int emplacementCartePrise = choixReveleesWitch();
			
			
			if (emplacementCartePrise != -1) {
				choixCarteHuntCarte = Jeu.getInstance().getJoueur(secondeCibleHunt).getCarteRevelees().get(emplacementCartePrise).getNumCarte();
				Jeu.getInstance().getEnTour().getCarteEnMain().add(Jeu.getInstance().getJoueur(secondeCibleHunt).getCarteRevelees().get(emplacementCartePrise));
				Jeu.getInstance().getJoueur(secondeCibleHunt).getCarteRevelees().remove(emplacementCartePrise);
			}
			else {
				choixCarteHuntCarte = -1;
			}
			
			cibleHunt = choisirProchainJoueur();
			Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(cibleHunt));
		}
		Jeu.getInstance().getVueActuelle().recapIAHunt(joueurEnTour, choixActionTour, choixAccuse, choixCarteHunt, cibleHunt, choixCarteHuntCarte, secondeCibleHunt);
	}
	
	private int choisirCarteDefausse() {
		int choix = (int) (Math.random() * Jeu.getInstance().getTasDefausse().getContenu().size());

		if (Jeu.getInstance().getTasDefausse().getContenu().isEmpty()) {
			return -1;
		}
		else {
			return choix;
		}
	}

	public void recapIA() {
		Jeu.getInstance().getVueActuelle().recapIA(joueurEnTour,choixActionTour,choixCarte,choixCarteJoueur,choixCartePrise);
		Jeu.getInstance().setAccused(null);
		this.reinitialiserAttributs();
	}
	
	public void recapIAAccused() {
		Jeu.getInstance().getVueActuelle().recapIAAccused(joueurEnTour,choixActionTour,choixCarte,choixCarteJoueur,choixCartePrise);
		Jeu.getInstance().setAccused(null);
		this.reinitialiserAttributs();
	}
	
	
}
