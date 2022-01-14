package controleur;

import modele.*;
import vue.*;

/**
 * Contrôleur appelé par les fonctions utilisées en mode console. 
 * @author super
 *
 */

public class ControlerCLI {

	/**
	 * Fonction appelée lors du démarrage du jeu. Demande le nombre de joueurs physiques et le nombre de joueurs virtuels, puis demande à chaque joueur d'entrer son pseudo, et enfin, organise les rounds.
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
	 * Arrête le jeu.
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
	 * Affecte le pseudo choisi par le joueur passé en argument.
	 * @param j Joueur
	 * @param s pseudonyme
	 */
	
	public void choisirPseudo(Joueur j ,String s) {
		Jeu instanceJeu = Jeu.getInstance();
		
		j.setPseudo(s);
	}
	
	/**
	 * Appelle la vue demandant quelle identité le joueur veut jouer sur ce tour.
	 */
	
	public void demanderIdentite() {
		Jeu instanceJeu = Jeu.getInstance();
		for (Joueur j : instanceJeu.getEnsembleJoueurs()) {
			instanceJeu.getVueActuelle().choisirIdentite(j);
		}
	}
	
	/**
	 * Affecte l'identité choisie par le joueur passé en argument.
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
	 * Appelle la vue utilisée lors de l'accusation.
	 */
	
	public void accuser() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().choixAccuse();
	}
	
	/**
	 * Selectionne le joueur accusé comme accusé et appelle la vue nécessaire pour qu'il y réponde.
	 * @param choixAccuse
	 */
	
	public void validerAccusation(int choixAccuse) {
		
		Jeu instanceJeu = Jeu.getInstance();
		
		//On définit le joueur accusé dans le jeu
		instanceJeu.setAccused(instanceJeu.getJoueur(choixAccuse-1)); // à MVCiser

		//On appelle la méthode d'un joueur accusé (elle retourne le prochain joueur, ce qui dépend des futurs choix de l'accusé)
		Joueur prochainJoueur = instanceJeu.getJoueur(choixAccuse-1).estAccuse();// à MVCiser / corriger ref inutile

		instanceJeu.setEnTour(prochainJoueur);
	}
	
	/**
	 * Appelle la vue permettant de choisir la carte hunt à jouer.
	 */
	
	public void jouerHunt() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().jouerHunt();
	}
	
	/**
	 * Fonction appelée quand le joueur choisit une carte hunt. Vérifie qu'il est en capacité de jouer la carte en question.
	 * @param j Joueur ayant joué la carte
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
	 * Fonction appelée quand le joueur passé en argument joue une carte witch.
	 * @param j Joueur
	 */
	
	public void jouerCarteWitch(Joueur j) {
		j.jouerCarteWitch();
	}
	
	/**
	 * Fonction appelée quand le joueur passé en argument révèle son identité.
	 * @param j Joueur
	 */
	
	public void revelerIdentite(Joueur j) {
		j.revelerIdentite();
	}
	
	/**
	 * Fonction appelée quand le joueur choisit une carte witch. Vérifie qu'il est en capacité de jouer la carte en question.
	 * @param j Joueur ayant joué la carte
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
