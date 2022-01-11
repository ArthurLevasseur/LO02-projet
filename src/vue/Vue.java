package vue;

import java.util.ArrayList;

import modele.*;
import controleur.*;

/**
 * Interface implémentée par les vues. Contient toutes les méthodes appelées par la {@link VueConsole} ou les autres vues.
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
	public void jouerHunt(); //à supprimer peut-être plus tard ?
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
	 * Vue qui lance le démarrage du jeu.
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
	 * Vue pour le début d'un tour.
	 */
	public void debutTour();
	
	/**
	 * Vue pour la réponse à une accusation.
	 * @param joueurCible Joueur qui doit répondre à l'accusation.
	 */
	public void repondreAccusation(int joueurCible);
	
	/**
	 * Vue pour la réponse à une accusation.
	 * @param joueurCible Joueur qui doit répondre à l'accusation.
	 */
	public void repondreAccusation(Joueur j);
	
	/**
	 * Vue pour le choix de l'identité.
	 * @param j Le joueur qui doit choisir son identité.
	 */
	public void choisirIdentite(Joueur j);
	
	/**
	 * Vue pour le début d'un round.
	 */
	public void demarrerRound();
	
	/**
	 * Vue appelée lorsque le joueur fait le choix de jouer une carte.
	 */
	public void jouerHunt();
	public void jouerHunt(CarteRumeur choixCarte); //à supprimer peut-être plus tard ?
	
	/**
	 * Vue pour le choix de l'accusé.
	 */
	public void choixAccuse();
	
	/**
	 * Vue utilisée pour le choix de la carte Hunt.
	 */
	public void choisirHunt();
	public void choisirHunt(Joueur j);
	
	/**
	 * Vue utilisée lorsqu'une carte Witch est jouée.
	 */
	public void jouerWitch();
	
	/**
	 * Vue utilisée lors qu'une accusation est lancée.
	 */
	public void accuser();
	
	/**
	 * Vue utilisée lorsqu'un joueur révèle son identité.
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
	 * Vue utilisée pour le départage en cas d'égalité.
	 * @param listeGagnants
	 */
	public void fight(ArrayList<Joueur> listeGagnants);
	
	/**
	 * Vue utilisée pour le choix d'une carte witch
	 */
	public void choisirWitch();
	public void setBtnAnnulerWitch();
	public void setBtnAnnulerHunt();
	public void setBtnAnnulerAccuse();
	
	
	//FONCTIONS PRINCIPALES DES EFFETS DES CARTES
	
	/**
	 * Vue utilisée pour une carte demandant de choisir le prochain joueur
	 * @param effet Référence de l'effet utilisé.
	 */
	public void choisirProchainJoueurWitch(Effet effet);
	
	/**
	 * Vue utilisée pour une carte demandant de choisir le prochain joueur
	 * @param effet Référence de l'effet utilisé.
	 */
	public void choisirProchainJoueur(Effet effet);
	
	/**
	 * Vue utilisée pour une carte permettant de prendre le tour
	 * @param effet Référence de l'effet utilisé.
	 */
	public void prendreProchainTour(Effet effet);
	
	/**
	 * Vue utilisée pour une carte demandant de défausser une carte
	 * @param effet Référence de l'effet utilisé.
	 */
	public void discard(Effet effet);
	
	/**
	 * Vue utilisée pour l'effet Hunt de la carte "Le Chaudron"
	 * @param effet Référence de l'effet utilisé.
	 */
	public void chaudronHunt(Effet effet);
	
	/**
	 * Vue utilisée pour l'effet Witch de la carte "Le Chaudron"
	 * @param effet Référence de l'effet utilisé.
	 */
	public void chaudronWitch(Effet effet);
	
	/**
	 * 2e vue utilisée pour l'effet Witch de la carte "Le Chaudron". Utilisée en mode console uniquement.
	 * @param effet Référence de l'effet utilisé.
	 * @param carte Carte à défausser
	 */
	public void chaudronWitch2(Effet effet, CarteRumeur carte);
	
	/**
	 * Vue utilisée pour l'effet Hunt de la carte "Black Cat"
	 * @param effet Référence de l'effet utilisé.
	 */
	public void blackCatHunt(Effet effet);
	
	/**
	 * Vue utilisée pour l'effet Hunt de la carte "Pet Newt"
	 * @param effet Référence de l'effet utilisé.
	 */
	public void petNewtHunt(Effet effet);
	
	/**
	 * 2e vue utilisée pour l'effet Hunt de la carte "Pet Newt"
	 * @param effet Référence de l'effet utilisé.
	 * @param selection Joueur sélectionné par la première vue
	 */
	public void petNewtHunt2(Effet effet, Joueur selection);
	
	/**
	 * Vue utilisée pour l'effet Hunt de la carte "Ducking Stool Hunt"
	 * @param effet Référence de l'effet utilisé.
	 */
	public void duckingStoolHunt(Effet effet);
	
	/**
	 * Vue utilisée pour l'effet de la carte "Evil Eye"
	 * @param effet Référence de l'effet utilisé.
	 * @param isHunt True si la carte est jouée comme effet hunt, false si jouée comme effet witch
	 */
	public void evilEye(Effet effet, boolean isHunt);
	
	/**
	 * Vue utilisée pour l'effet Hunt de la carte "Angry Mob"
	 * @param effet Référence de l'effet utilisé.
	 */
	public void angryMobHunt(Effet effet);
	
	/**
	 * Vue utilisée pour l'effet Hunt de la carte "The Inquisition"
	 * @param effet Référence de l'effet utilisé.
	 */
	public void theInquisitionHunt(Effet effet);
	
	/**
	 * Vue utilisée pour l'effet de la carte "Pointed Hat"
	 * @param effet Référence de l'effet utilisé.
	 * @param isHunt True si la carte est jouée comme effet hunt, false si jouée comme effet witch
	 */
	public void pointedHat(Effet effet, boolean isHunt);
	
	/**
	 * Vue utilisée pour l'effet Hunt de la carte "Hooked Nose"
	 * @param effet Référence de l'effet utilisé.
	 */
	public void hookedNoseWitch(Effet effet);
	
	/**
	 * Vue utilisée pour l'effet Witch de la carte "The Inquisition"
	 * @param effet Référence de l'effet utilisé.
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
