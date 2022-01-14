package controleur;

import modele.*;
import vue.*;

/**
 * Contr�leur appel� par les fonctions utilis�es en mode console. 
 * @author super
 *
 */

public class ControlerCLI {

	/**
	 * Fonction appel�e lors du d�marrage du jeu. Demande le nombre de joueurs physiques et le nombre de joueurs virtuels, puis demande � chaque joueur d'entrer son pseudo, et enfin, organise les rounds.
	 */

	public void demarrerJeu() {
		Jeu instanceJeu = Jeu.getInstance();
		
		instanceJeu.getVueActuelle().demanderNombreJoueursPhysiques();
		instanceJeu.getVueActuelle().demanderNombreJoueursVirtuels();
		instanceJeu.initGame();
		
		for (Joueur j : instanceJeu.getEnsembleJoueurs()) {
			if (j instanceof JoueurPhysique) {
				instanceJeu.getVueActuelle().determinerNomDuJoueur(j);
			}
		}
		
		
		instanceJeu.orgaRounds();
		
	}
	
	/**
	 * Arr�te le jeu.
	 */
	
	public void stopJeu() {
		System.exit(1);
	}
	
	/**
	 * Modifie le nombre de joueurs physiques.
	 * @param i nombre de joueurs
	 */
	
	public void selectionnerNombreJoueursPhysiques(int i) {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.setNombrePhy(i);
	}
	
	/**
	 * Modifie le nombre de joueurs virtuels.
	 * @param i nombre de joueurs
	 */
	
	public void selectionnerNombreJoueursVirtuels(int i) {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.setNombreIA(i);
	}
	
	/**
	 * Affecte le pseudo choisi par le joueur pass� en argument.
	 * @param j Joueur
	 * @param s pseudonyme
	 */
	
	public void choisirPseudo(Joueur j ,String s) {
		Jeu instanceJeu = Jeu.getInstance();
		
		j.setPseudo(s);
	}
	
	/**
	 * Appelle la vue demandant quelle identit� le joueur veut jouer sur ce tour.
	 */
	
	public void demanderIdentite() {
		Jeu instanceJeu = Jeu.getInstance();
		for (Joueur j : instanceJeu.getEnsembleJoueurs()) {
			instanceJeu.getVueActuelle().choisirIdentite(j);
		}
	}
	
	/**
	 * Affecte l'identit� choisie par le joueur pass� en argument.
	 * @param j Joueur
	 * @param identite Choix
	 */
	
	public void attribuerIdentite(Joueur j, int identite) {
		
		if (identite == 1) {
			j.getIdentiteAssociee().setWitch(false);
		}
		else if (identite == 2) {
			j.getIdentiteAssociee().setWitch(true);
		}
	}
	
	/**
	 * Appelle la vue utilis�e lors de l'accusation.
	 */
	
	public void accuser() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().choixAccuse();
	}
	
	/**
	 * Selectionne le joueur accus� comme accus� et appelle la vue n�cessaire pour qu'il y r�ponde.
	 * @param choixAccuse
	 */
	
	public void validerAccusation(int choixAccuse) {
		
		Jeu instanceJeu = Jeu.getInstance();
		
		//On d�finit le joueur accus� dans le jeu
		instanceJeu.setAccused(instanceJeu.getJoueur(choixAccuse-1)); // � MVCiser

		//On appelle la m�thode d'un joueur accus� (elle retourne le prochain joueur, ce qui d�pend des futurs choix de l'accus�)
		Joueur prochainJoueur = instanceJeu.getJoueur(choixAccuse-1).estAccuse();// � MVCiser / corriger ref inutile

		instanceJeu.setEnTour(prochainJoueur);
	}
	
	/**
	 * Appelle la vue permettant de choisir la carte hunt � jouer.
	 */
	
	public void jouerHunt() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().jouerHunt();
	}
	
	/**
	 * Fonction appel�e quand le joueur choisit une carte hunt. V�rifie qu'il est en capacit� de jouer la carte en question.
	 * @param j Joueur ayant jou� la carte
	 * @param choix Choix de la carte
	 */
	
	public void choisirHunt(Joueur j, int choix) {
		
		Jeu instanceJeu = Jeu.getInstance();
		
		j.getCarteEnMain().get(choix).appliquerEffetHunt();
		
		if (j.getCarteEnMain().get(choix).getNumCarte() == 11) {
			instanceJeu.getTasDefausse().getContenu().add(j.getCarteEnMain().get(choix));
			j.getCarteEnMain().remove(choix);
		}
		else {
			j.getCarteRevelees().add(j.getCarteEnMain().get(choix));
			j.getCarteEnMain().remove(choix);
		}
		
		//instanceJeu.setEnTour(next);
		
	}
	
	/**
	 * Fonction appel�e quand le joueur pass� en argument joue une carte witch.
	 * @param j Joueur
	 */
	
	public void jouerCarteWitch(Joueur j) {
		j.jouerCarteWitch();
	}
	
	/**
	 * Fonction appel�e quand le joueur pass� en argument r�v�le son identit�.
	 * @param j Joueur
	 */
	
	public void revelerIdentite(Joueur j) {
		j.revelerIdentite();
	}
	
	/**
	 * Fonction appel�e quand le joueur choisit une carte witch. V�rifie qu'il est en capacit� de jouer la carte en question.
	 * @param j Joueur ayant jou� la carte
	 * @param choix Choix de la carte
	 */
	
	public void choisirWitch(Joueur j, int choix) {
		
		Jeu instanceJeu = Jeu.getInstance();
		j.getCarteEnMain().get(choix).appliquerEffetWitch(j);
		j.getCarteRevelees().add(j.getCarteEnMain().get(choix));
		j.getCarteEnMain().remove(choix);
		//instanceJeu.setEnTour(next);
	}
	
}
