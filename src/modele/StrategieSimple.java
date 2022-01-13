package modele;
import java.util.ArrayList;
import java.util.Collections;

public class StrategieSimple extends Strategie {
	
	/**
	 * int contenant le choix du tour. 1 s'il accuse, 2 s'il joue une carte.
	 */
	private int choixActionTour;
	
	/**
	 * id de la carte jouée s'il joue une carte.
	 */
	private int choixCarte;
	
	/**
	 * int contenant le joueur ciblé par l'accusation
	 */
	private int joueurCibleAccuser = -1;
	
	/**
	 * id du joueur ciblé par la carte utilisée par le joeueur
	 */
	private int choixCarteJoueur;
	
	/**
	 * id de la carte ciblée par l'effet joué par le joueur
	 */
	private int choixCartePrise;
	
	/**
	 * Référence vers le joueur en train de jouer
	 */
	private Joueur joueurEnTour;
	
	/**
	 * 
	 */
	private int choixAccuse;
	
	/**
	 * Id de la carte hunt choisie.
	 */
	private int choixCarteHunt;
	
	/**
	 * Id de la carte witch choisie.
	 */
	private int choixCarteHuntCarte;
	
	/**
	 * Id de la cible de la carte hunt.
	 */
	private int cibleHunt;
	
	/**
	 * Id de la 2e cible de la carte hunt si nécessaire.
	 */
	private int secondeCibleHunt;
	
	private boolean accusable;
	
	/**
	 * Remet les attributs de la classe à -1 pour les entiers et null pour les autres types. Utilisée à la fin d'un tour en préparation du suivant.
	 */
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
	
	/**
	 * Le joueur virtuel choisit son action du tour : accuser ou jouer un effet hunt.
	 * @return 1 s'il accuse, 2 s'il joue un effet hunt
	 */
	public int choisirActionTour() {
		int choix = (int) (Math.random() * 1.5);
		if (choix == 1) {
			//System.out.println("Il choisit d'accuser un joueur.");
			return 1;
		}
		else {
			//System.out.println("Il choisit de jouer un effet hunt.");
			return 2;
		}

	}
	
	/**
	 * Retourne l'ID du joueur accusé. Appelée quand le joueur virtuel accuse un autre joueur.
	 * @return ID du joueur
	 */
	public int choisirAccuse() {
		
		Jeu instance = Jeu.getInstance();
		ArrayList<Integer> possibilites = new ArrayList<Integer>();
		
		
		for (int i=1 ; i<instance.getNombreJoueurs()+1 ; i++) {
			if (instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false && instance.getJoueur(i-1)!=instance.getEnTour() && instance.getJoueur(i-1).isAccusable()==true) {
				possibilites.add(i-1);
			}
		}
		
		if (possibilites.isEmpty()) {
			return -1;
		}
		else {
			Collections.shuffle(possibilites);
			return possibilites.get(0);
		}
	}
	
	/**
	 * Retourne l'ID du joueur accusé par la carte angry mob. Appelée quand le joueur virtuel accuse un autre joueur.
	 * @return ID du joueur
	 */
	public int choisirAccuseAngryMob() {
		
		Jeu instance = Jeu.getInstance();
		ArrayList<Integer> possibilites = new ArrayList<Integer>();
		
		
		for (int i=1 ; i<instance.getNombreJoueurs()+1 ; i++) {
			
			accusable = true;
			
			instance.getJoueur(i-1).getCarteRevelees().forEach(Card -> {
				if (Card.getNumCarte() == 5) {
					accusable = false;
				}
			});
			
			if (instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false && instance.getJoueur(i-1)!=instance.getEnTour() && instance.getJoueur(i-1).isAccusable()==true && accusable == true) {
				possibilites.add(i-1);
			}
		}
		
		if (possibilites.isEmpty()) {
			return -1;
		}
		else {
			Collections.shuffle(possibilites);
			return possibilites.get(0);
		}
	}
	
	/**
	 * Retourne l'ID du joueur accusé par la carte Ducking Stool. Appelée quand le joueur virtuel accuse un autre joueur.
	 * @return ID du joueur
	 */
	public int choisirAccuseDuckingStool() {
		
		Jeu instance = Jeu.getInstance();
		ArrayList<Integer> possibilites = new ArrayList<Integer>();
		
		
		for (int i=1 ; i<instance.getNombreJoueurs()+1 ; i++) {
			
			accusable = true;
			
			instance.getJoueur(i-1).getCarteRevelees().forEach(Card -> {
				if (Card.getNumCarte() == 6) {
					accusable = false;
				}
			});
			
			if (instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false && instance.getJoueur(i-1)!=instance.getEnTour() && instance.getJoueur(i-1).isAccusable()==true && accusable == true) {
				possibilites.add(i-1);
			}
		}
		
		if (possibilites.isEmpty()) {
			return -1;
		}
		else {
			Collections.shuffle(possibilites);
			return possibilites.get(0);
		}
	}
	
	/**
	 * Fonction appelée quand le joueur virtuel doit choisir le prochain joueur.
	 * @return L'id du joueur sélectionné
	 */
	public int choisirProchainJoueur() {
			
			Jeu instance = Jeu.getInstance();
			ArrayList<Integer> possibilites = new ArrayList<Integer>();
			
			
			for (int i=0 ; i<instance.getNombreJoueurs() ; i++) {
				if (((instance.getJoueur(i).getIdentiteAssociee().getDevoilee() == true && instance.getJoueur(i).getIdentiteAssociee().getIsWitch() == false) || instance.getJoueur(i).getIdentiteAssociee().getDevoilee() == false) && instance.getJoueur(i)!=instance.getEnTour()) {
					possibilites.add(i);
				}
			}
			
			if (possibilites.isEmpty()) {
				return -1;
			}
			else {
				Collections.shuffle(possibilites);
				
				return possibilites.get(0);
			}
			
	}
	
	/**
	 * Fonction appelée quand le joueur virtuel doit choisir le prochain joueur.
	 * @return L'id du joueur sélectionné
	 */
	public int choisirAutreJoueur() {
		
		Jeu instance = Jeu.getInstance();
		ArrayList<Integer> possibilites = new ArrayList<Integer>();
		
		
		for (int i=0 ; i<instance.getNombreJoueurs() ; i++) {
			if (instance.getJoueur(i)!=instance.getEnTour()) {
				possibilites.add(i);
			}
		}
		Collections.shuffle(possibilites);
		//System.out.println("Il décide de choisir le joueur " + instance.getJoueur(possibilites.get(0)).getPseudo());
		return possibilites.get(0);
	}
	
	/**
	 * Fonction appelée quand le joueur virtuel doit choisir le prochain joueur à l'issue d'une carte witch.
	 * @return L'id du joueur sélectionné
	 */
	public int choisirProchainJoueurWitch() {
		
		Jeu instance = Jeu.getInstance();
		ArrayList<Integer> possibilites = new ArrayList<Integer>();
		
		
		for (int i=1 ; i<instance.getNombreJoueurs()+1 ; i++) {
			if (((instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == true && instance.getJoueur(i-1).getIdentiteAssociee().getIsWitch() == false) || instance.getJoueur(i-1).getIdentiteAssociee().getDevoilee() == false) && instance.getJoueur(i-1)!=instance.getAccused()) {
				possibilites.add(i-1);
			}
		}
		Collections.shuffle(possibilites);
		//System.out.println("Il décide de choisir le joueur " + instance.getJoueur(possibilites.get(0)).getPseudo());
		return possibilites.get(0);
	}

	/**
	 * Fonction appelée quand le joueur virtuel doit choisir une carte de sa main.
	 * @return L'id de la carte sélectionnée
	 */
	public int choixCarteMain() {
		Jeu instance = Jeu.getInstance();
		int choix = -1;
		while (choix < 0 || choix > instance.getEnTour().getCarteEnMain().size()) {
			choix = (int)(Math.random() * instance.getEnTour().getCarteEnMain().size());
		}
		return choix;
	}

	/**
	 * Fonction appelée quand le joueur virtuel doit choisir une carte de sa main après l'effet d'une carte witch.
	 * @return L'id de la carte sélectionnée
	 */
	public int choixCarteMainWitch() {
		Jeu instance = Jeu.getInstance();
		int choix = (int)(Math.random() * instance.getAccused().getCarteEnMain().size());
		if (instance.getAccused().getCarteEnMain().isEmpty()) {
			return -1;
		}
		return choix;
	}
	
	/**
	 * Fonction appelée quand le joueur virtuel doit choisir une carte de sa main après l'effet de la carte witch de l'inquisition.
	 * @return L'id de la carte sélectionnée
	 */
	public int choixCarteMainWitchTheInquisition() {
		Jeu instance = Jeu.getInstance();
		int choix = (int)(Math.random() * instance.getAccused().getCarteEnMain().size());
		if (instance.getAccused().getCarteEnMain().isEmpty() || (instance.getAccused().getCarteEnMain().size() == 1 && instance.getAccused().getCarteEnMain().get(0).getNumCarte() == 2)) {
			return -1;
		}
		return choix;
	}

	/**
	 * Fonction appelée quand le joueur virtuel doit choisir une de ses cartes revelées après l'effet d'une carte hunt.
	 * @return L'id de la carte sélectionnée
	 */
	public int choixReveleesHunt() {
		Jeu instance = Jeu.getInstance();
		int choix = (int)(Math.random() * instance.getEnTour().getCarteRevelees().size());
		if (instance.getEnTour().getCarteRevelees().isEmpty()) {
			return -1;
		}
		return choix;
	}

	/**
	 * Fonction appelée quand le joueur virtuel doit choisir une de ses cartes revelées après l'effet d'une carte witch.
	 * @return L'id de la carte sélectionnée
	 */
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
	
	/**
	 * Fonction appelée quand le joueur virtuel doit choisir une de ses cartes revelées après l'effet witch de la carte pointed hat. Empêche le joueur de reprendre la carte en question.
	 * @return L'id de la carte sélectionnée
	 */
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
	
	/**
	 * Fonction appelée quand le joueur virtuel doit choisir une de ses cartes revelées après l'effet hunt de la carte pointed hat. Empêche le joueur de reprendre la carte en question.
	 * @return L'id de la carte sélectionnée
	 */
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

	/**
	 * Fonction appelée quand le joueur doit se défendre.
	 */
	public int seDefendre() {
		
		return 1;
	}

	/**
	 * Fonction appelée quand le joueur virtuel choisit sa carte hunt.
	 * @return L'id de la carte choisie
	 */
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
	
	/**
	 * Fonction appelée quand le joueur virtuel choisit sa carte witch.
	 * @return L'id de la carte choisie
	 */
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

	/**
	 * Fonction réalisant tout le déroulement d'un tour d'une IA
	 */
	public void jouerTourIA() {
		choixActionTour = choisirActionTour();
		choixAccuse = choisirAccuse();
		choixCarte = choisirCarteHunt();
		
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

	/**
	 * Fonction utilisée pour la réponse à l'accusation d'un joueur virtuel.
	 */
	
	public void repondreAccusation() {
		choixActionTour = choisirActionTour();
		int emplacementCarte = choisirCarteWitch();
		
		if (emplacementCarte == -1) {
			choixActionTour = 1;
		}
		else {
			choixCarte = Jeu.getInstance().getAccused().getCarteEnMain().get(emplacementCarte).getNumCarte();
		}

		joueurEnTour = Jeu.getInstance().getEnTour();
		
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
		else if (carte.getNumCarte() == 3) {
			int emplacementCartePrise = choixReveleesWitchPointedHat();
			if (emplacementCartePrise != -1) {
				choixCartePrise = Jeu.getInstance().getAccused().getCarteRevelees().get(emplacementCartePrise).getNumCarte();
				Jeu.getInstance().getAccused().prendreCarteRumeur(Jeu.getInstance().getAccused().getCarteRevelees().get(emplacementCartePrise));
				Jeu.getInstance().getAccused().getCarteRevelees().remove(emplacementCartePrise);
			}
			Jeu.getInstance().setEnTour(Jeu.getInstance().getAccused());
			Jeu.getInstance().getVueActuelle().passerTourSuivant();
		}
		else if (carte.getNumCarte() == 4) {
			int emplacementCartePrise = (int) (Math.random() * Jeu.getInstance().getEnTour().getCarteEnMain().size());
			if (!(Jeu.getInstance().getEnTour().getCarteEnMain().isEmpty())) {
				choixCartePrise = Jeu.getInstance().getEnTour().getCarteEnMain().get(emplacementCartePrise).getNumCarte();
				Jeu.getInstance().getAccused().getCarteEnMain().add(Jeu.getInstance().getEnTour().seFairePrendreCarteRumeur(emplacementCartePrise));
			}
			Jeu.getInstance().setEnTour(Jeu.getInstance().getAccused());
			Jeu.getInstance().getVueActuelle().passerTourSuivant();
		}
		else if (carte.getNumCarte() == 7) {
			int prochainJoueur = choisirProchainJoueurWitch();
			choixCarteJoueur = prochainJoueur;
			Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(prochainJoueur));
			Jeu.getInstance().getVueActuelle().passerTourSuivant();
		}
		else if (carte.getNumCarte() == 8) {
			Jeu.getInstance().getVueActuelle().passerTourSuivant();
		}
		
		else if (carte.getNumCarte() == 9) {
			
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
		
		carte.appliquerEffetHunt();
		if (carte.getNumCarte() == 1) {
			cibleHunt = choisirAccuseAngryMob();
			if (cibleHunt != -1) {
				if (Jeu.getInstance().getJoueur(cibleHunt).getIdentiteAssociee().getIsWitch()) {
					Jeu.getInstance().getEnTour().setPoints(Jeu.getInstance().getEnTour().getPoints()+2);
				}
				else {
					Jeu.getInstance().getEnTour().setPoints(Jeu.getInstance().getEnTour().getPoints()-2);
				}
				Jeu.getInstance().getVueActuelle().revelerHunt(cibleHunt);
			}
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
			cibleHunt = choisirAccuseDuckingStool();
			
		}
		if (carte.getNumCarte() == 8 || carte.getNumCarte() == 10) {
			if (Jeu.getInstance().getEnTour().getIdentiteAssociee().getIsWitch() == false) {
				cibleHunt = choisirProchainJoueur();
				Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(cibleHunt));
			}
			else {
				ArrayList<Joueur> listeJoueurs = new ArrayList<>();
				for (int i=0; i<Jeu.getInstance().getEnsembleJoueurs().size(); i++) {
					if (((Jeu.getInstance().getJoueur(i).getIdentiteAssociee().getDevoilee() == true && Jeu.getInstance().getJoueur(i).getIdentiteAssociee().getIsWitch() == false) || Jeu.getInstance().getJoueur(i).getIdentiteAssociee().getDevoilee() == false) || Jeu.getInstance().getJoueur(i) == Jeu.getInstance().getEnTour()) {
						listeJoueurs.add(Jeu.getInstance().getJoueur(i));
					}
				}
				for (int i=0; i<listeJoueurs.size(); i++) {
					if (listeJoueurs.get(i) == Jeu.getInstance().getEnTour()) {
						if (i == listeJoueurs.size()-1) {
							Jeu.getInstance().setEnTour(listeJoueurs.get(0));
							break;
						}
						else {
							Jeu.getInstance().setEnTour(listeJoueurs.get(i+1));
							break;
						}
					}
				}
			}
		}
		if (carte.getNumCarte() == 9) {
			cibleHunt = choisirProchainJoueur();
			Jeu.getInstance().getEnTour().setAccusable(false);
			Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(cibleHunt));
		}
		if (carte.getNumCarte() == 11) {
			
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
	
	/**
	 * Fonction appelée quand un joueur virtuel doit piocher dans la défausse.
	 * @return Retourne l'id de la carte choisie
	 */
	private int choisirCarteDefausse() {
		int choix = (int) (Math.random() * Jeu.getInstance().getTasDefausse().getContenu().size());

		if (Jeu.getInstance().getTasDefausse().getContenu().isEmpty()) {
			return -1;
		}
		else {
			return choix;
		}
	}

	/**
	 * Réalise le récap d'un tour de l'IA.
	 */
	public void recapIA() {
		System.out.println(choixCarte);
		Jeu.getInstance().getVueActuelle().recapIA(joueurEnTour,choixActionTour,choixCarte,choixCarteJoueur,choixCartePrise);
		this.reinitialiserAttributs();
		Jeu.getInstance().setAccused(null);
	}
	
	/**
	 * Fonction appelée quand le joueur virtuel est ciblé par la carte Ducking Stool
	 */
	public void reponseDuckingStool(int joueur) {
		int choixPrincipal = (int) (Math.random() * 2);
		if (choixPrincipal == 1) {
			int choixDiscard = (int) (Math.random() * Jeu.getInstance().getJoueur(joueur).getCarteEnMain().size());
			if (Jeu.getInstance().getJoueur(joueur).getCarteEnMain().size() > 0) {
				Jeu.getInstance().getTasDefausse().defausserUneCarte(Jeu.getInstance().getJoueur(joueur).seFairePrendreCarteRumeur(choixDiscard));
				choixDiscard = Jeu.getInstance().getTasDefausse().getContenu().get(Jeu.getInstance().getTasDefausse().getContenu().size()-1).getNumCarte();
				System.out.println(choixDiscard);
			}
			else {
				choixDiscard = -1;
			}
			Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(joueur));
			Jeu.getVueActuelle().recapIADuckingStool(choixPrincipal,choixDiscard,joueur);
		}
		else {
			if (Jeu.getInstance().getJoueur(joueur).getIdentiteAssociee().getIsWitch()) {
				Jeu.getInstance().getEnTour().setPoints(Jeu.getInstance().getEnTour().getPoints()+1);
			}
			else {
				Jeu.getInstance().getEnTour().setPoints(Jeu.getInstance().getEnTour().getPoints()-1);
			}
			Jeu.getInstance().getVueActuelle().revelerHunt(joueur);
			Jeu.getVueActuelle().recapIADuckingStool(choixPrincipal,-1,joueur);
		}
	}
}
