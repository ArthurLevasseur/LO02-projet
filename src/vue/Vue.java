package vue;

import java.util.ArrayList;

import modele.*;
import controleur.*;

/**
 * Interface impl�ment�e par les vues. Contient toutes les m�thodes appel�es par la {@link VueConsole} ou les autres vues.
 *
 */

public interface Vue {

	/*
	public void demarrerJeu();
	public void demanderNombreJoueursPhysiques();
	public void demanderNombreJoueursVirtuels();
	public void determinerNomDuJoueur(Joueur j);
	public void initialisationDeLaPartie();
	public void debutTour();
	public void repondreAccusation(int joueurCible);
	public void repondreAccusation(Joueur j);
	public void choisirIdentite(Joueur j);
	public void demarrerRound();
	public void jouerHunt(); //� supprimer peut-�tre plus tard ?
	public void choixAccuse();
	public void choisirHunt(Joueur j);
	public void jouerWitch();
	public void accuser();
	public void reveler();
	public void demarrerTour(int joueur, boolean b);
	public void passerTourSuivant();
	public void passerRoundSuivant();
	public void leave();
	public void fight(ArrayList<Joueur> listeGagnants);
	public void choisirWitch();
	public void setBtnAnnulerWitch();
	public void setBtnAnnulerHunt();
	public void setBtnAnnulerAccuse();
	
	public void revealPlayer(Effet effet);
	public void chooseAndSecretLook(Effet effet);
	public void takeBackACard(Effet effet);
	public void askOpponent(Effet effet);
	public void askOpponentSteal(Effet effet);
	public void pickRandom(Effet effet);
	public void discard(Effet effet);
	*/
	
	/**
	 * Vue qui lance le d�marrage du jeu.
	 */
	public void demarrerJeu();
	
	/**
	 * Vue qui demande le nombre de joueurs physiques.
	 */
	public void demanderNombreJoueursPhysiques();
	
	/**
	 * Vue qui demande le nombre de joueurs virtuels.
	 */
	public void demanderNombreJoueursVirtuels();
	
	/**
	 * Vue qui demande le nombre de joueurs.
	 * @param j Le joueur dont on demande le nom.
	 */
	public void determinerNomDuJoueur(Joueur j);
	
	/**
	 * Vue pour l'initialisation de la partie.
	 */
	public void initialisationDeLaPartie();
	
	/**
	 * Vue pour le d�but d'un tour.
	 */
	public void debutTour();
	
	/**
	 * Vue pour la r�ponse � une accusation.
	 * @param joueurCible Joueur qui doit r�pondre � l'accusation.
	 */
	public void repondreAccusation(int joueurCible);
	
	/**
	 * Vue pour la r�ponse � une accusation.
	 * @param joueurCible Joueur qui doit r�pondre � l'accusation.
	 */
	public void repondreAccusation(Joueur j);
	
	/**
	 * Vue pour le choix de l'identit�.
	 * @param j Le joueur qui doit choisir son identit�.
	 */
	public void choisirIdentite(Joueur j);
	
	/**
	 * Vue pour le d�but d'un round.
	 */
	public void demarrerRound();
	
	/**
	 * Vue appel�e lorsque le joueur fait le choix de jouer une carte.
	 */
	public void jouerHunt();
	public void jouerHunt(CarteRumeur choixCarte); //� supprimer peut-�tre plus tard ?
	
	/**
	 * Vue pour le choix de l'accus�.
	 */
	public void choixAccuse();
	
	/**
	 * Vue utilis�e pour le choix de la carte Hunt.
	 */
	public void choisirHunt();
	public void choisirHunt(Joueur j);
	
	/**
	 * Vue utilis�e lorsqu'une carte Witch est jou�e.
	 */
	public void jouerWitch();
	
	/**
	 * Vue utilis�e lors qu'une accusation est lanc�e.
	 */
	public void accuser();
	
	/**
	 * Vue utilis�e lorsqu'un joueur r�v�le son identit�.
	 */
	public void reveler();
	public void demarrerTour(int joueur, boolean b);
	
	/**
	 * Vue lors du passage au tour suivant.
	 */
	public void passerTourSuivant();
	
	/**
	 * Vue lors du passage au round suivant.
	 */
	public void passerRoundSuivant();
	
	/**
	 * Vue lorsque l'on quitte le jeu.
	 */
	public void leave();
	
	/**
	 * Vue utilis�e pour le d�partage en cas d'�galit�.
	 * @param listeGagnants
	 */
	public void fight(ArrayList<Joueur> listeGagnants);
	
	/**
	 * Vue utilis�e pour le choix d'une carte witch
	 */
	public void choisirWitch();
	public void setBtnAnnulerWitch();
	public void setBtnAnnulerHunt();
	public void setBtnAnnulerAccuse();
	
	
	//FONCTIONS PRINCIPALES DES EFFETS DES CARTES
	
	/**
	 * Vue utilis�e pour une carte demandant de choisir le prochain joueur
	 * @param effet R�f�rence de l'effet utilis�.
	 */
	public void choisirProchainJoueurWitch(Effet effet);
	
	/**
	 * Vue utilis�e pour une carte demandant de choisir le prochain joueur
	 * @param effet R�f�rence de l'effet utilis�.
	 */
	public void choisirProchainJoueur(Effet effet);
	
	/**
	 * Vue utilis�e pour une carte permettant de prendre le tour
	 * @param effet R�f�rence de l'effet utilis�.
	 */
	public void prendreProchainTour(Effet effet);
	
	/**
	 * Vue utilis�e pour une carte demandant de d�fausser une carte
	 * @param effet R�f�rence de l'effet utilis�.
	 */
	public void discard(Effet effet);
	
	/**
	 * Vue utilis�e pour l'effet Hunt de la carte "Le Chaudron"
	 * @param effet R�f�rence de l'effet utilis�.
	 */
	public void chaudronHunt(Effet effet);
	
	/**
	 * Vue utilis�e pour l'effet Witch de la carte "Le Chaudron"
	 * @param effet R�f�rence de l'effet utilis�.
	 */
	public void chaudronWitch(Effet effet);
	
	/**
	 * 2e vue utilis�e pour l'effet Witch de la carte "Le Chaudron". Utilis�e en mode console uniquement.
	 * @param effet R�f�rence de l'effet utilis�.
	 * @param carte Carte � d�fausser
	 */
	public void chaudronWitch2(Effet effet, CarteRumeur carte);
	
	/**
	 * Vue utilis�e pour l'effet Hunt de la carte "Black Cat"
	 * @param effet R�f�rence de l'effet utilis�.
	 */
	public void blackCatHunt(Effet effet);
	
	/**
	 * Vue utilis�e pour l'effet Hunt de la carte "Pet Newt"
	 * @param effet R�f�rence de l'effet utilis�.
	 */
	public void petNewtHunt(Effet effet);
	
	/**
	 * 2e vue utilis�e pour l'effet Hunt de la carte "Pet Newt"
	 * @param effet R�f�rence de l'effet utilis�.
	 * @param selection Joueur s�lectionn� par la premi�re vue
	 */
	public void petNewtHunt2(Effet effet, Joueur selection);
	
	/**
	 * Vue utilis�e pour l'effet Hunt de la carte "Ducking Stool Hunt"
	 * @param effet R�f�rence de l'effet utilis�.
	 */
	public void duckingStoolHunt(Effet effet);
	
	/**
	 * Vue utilis�e pour l'effet de la carte "Evil Eye"
	 * @param effet R�f�rence de l'effet utilis�.
	 * @param isHunt True si la carte est jou�e comme effet hunt, false si jou�e comme effet witch
	 */
	public void evilEye(Effet effet, boolean isHunt);
	
	/**
	 * Vue utilis�e pour l'effet Hunt de la carte "Angry Mob"
	 * @param effet R�f�rence de l'effet utilis�.
	 */
	public void angryMobHunt(Effet effet);
	
	/**
	 * Vue utilis�e pour l'effet Hunt de la carte "The Inquisition"
	 * @param effet R�f�rence de l'effet utilis�.
	 */
	public void theInquisitionHunt(Effet effet);
	
	/**
	 * Vue utilis�e pour l'effet de la carte "Pointed Hat"
	 * @param effet R�f�rence de l'effet utilis�.
	 * @param isHunt True si la carte est jou�e comme effet hunt, false si jou�e comme effet witch
	 */
	public void pointedHat(Effet effet, boolean isHunt);
	
	/**
	 * Vue utilis�e pour l'effet Hunt de la carte "Hooked Nose"
	 * @param effet R�f�rence de l'effet utilis�.
	 */
	public void hookedNoseWitch(Effet effet);
	
	/**
	 * Vue utilis�e pour l'effet Witch de la carte "The Inquisition"
	 * @param effet R�f�rence de l'effet utilis�.
	 */
	public void hookedNoseHunt(Effet effet);
	
	//FONCTIONS SECONDAIRES DES EFFETS DES CARTES
	
	public void petNewtChoixCarte(Joueur joueur);
	public void afficherCarteVolee(CarteRumeur carteRandom);
	public void revelerHunt(int joueur);
	public void setTextSecret(String string);
	public void duckingStoolChoixCible(int joueur);
	public void passerTourSuivantAccusable();
	public void choisirProchainJoueurVoler();
	
	
	
	public void revelerIA();
	public void initialize();
	public void isAccusedIA(int joueurCible);
	public void afficherRegles();
	public void recapIA(Joueur joueurEnTour, int choixActionTour, int choixCarte, int choixCarteJoueur, int choixCartePrise);
	public void recapIAHunt(Joueur joueurEnTour, int choixActionTour, int choixAccuse, int choixCarteHunt, int choixCarteHuntJoueur, int choixCarteHuntCarte, int secondeCibleHunt);
	public void recapIADuckingStool(int choixPrincipal, int i, int joueur);
	public void passerTourSuivantAccusableHunt();
	
}
