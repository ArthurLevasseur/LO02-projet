package modele;

import controleur.*;
import vue.*;

/**
 * Classe repr�sentant un round du jeu. Les classes round sont instanci�s dans la fonction {@link Jeu.orgaRounds()}.
 *
 */
public class Round {
	
	/**
	 * Tableau contenant les joueurs dont les identit�s sont revel�es.
	 */
	
	private Joueur[] identitesRevelees = new Joueur[6];
	
	/**
	 * R�f�rence � un joueur ayant gagn� le round. Est � null si le round est encore en cours.
	 */
	
	private Joueur gagnantRound = null;
	
	/**
	 * R�f�rence � un contr�leur permettant de s�parer certains affichages consoles et GUI pr�sents dans certaines m�thodes du mod�le
	 */
	
	private ControleurInter inter = ControleurInter.getInstance();
	
	public Round() {
		
		
	}
	
	/**
	 * D�bute le round. Demande aux joueurs de choisir leur identit�, puis appelle la fonction {@link jouerTour()} pour les joueurs.
	 * @param premierJoueur Joueur qui joue en premier dans ce round.
	 */
	
	public void debutRound(Joueur premierJoueur) {
		
		Jeu instanceJeu = Jeu.getInstance();
		
		for (int i = 0; i < instanceJeu.getNombreJoueurs(); i++) {
			instanceJeu.getJoueur(i).getIdentiteAssociee().choisirIdentite(instanceJeu.getJoueur(i),instanceJeu.getJoueur(i).isIA());
		}
		
		inter.choisirIdentite();
		
		for (int i=0;i<instanceJeu.getNombreJoueurs();i++) {
			instanceJeu.getJoueur(i).getIdentiteAssociee().setDevoilee(false);
		}
		
		//GagnantRound permet de d�terminer le premier joueur du round suivant, sauf pour le premier round.
		if (gagnantRound != null) {
			premierJoueur = gagnantRound;
		}
		System.out.println("c'est au joueur " + premierJoueur.getPseudo() + " de commencer ce round !");
		
		
		instanceJeu.setEnTour(premierJoueur);
		
		//Tour du premier joueur qui retourne le joueur du tour suivant dans la variable "enTour"
		instanceJeu.setEnTour(this.jouerTour());
		int nombreIdenDevoilee = 0;
		
		//Boucle des tours des joueurs, s'arr�te lorsqu'un joueur a 5 points, ou qu'il ne reste qu'un joueur en jeu.
		while (nombreIdenDevoilee < instanceJeu.getNombreJoueurs()-1) {
			
			
			System.out.println("c'est au joueur " + instanceJeu.getEnTour().getPseudo() + " de jouer son tour !");
			
			//Tour des joueurs qui retourne le joueur du tour suivant dans la variable "enTour"
			instanceJeu.setEnTour(this.jouerTour());
			
			//V�rification du nombre d'identit� r�v�l�es pour savoir si c'est la fin d'un round
			nombreIdenDevoilee = 0;
			for (int i=0; i<instanceJeu.getNombreJoueurs(); i++) {
				if (instanceJeu.getJoueur(i).getIdentiteAssociee().getDevoilee() == true) {
					nombreIdenDevoilee += 1;
				}

			}

		}
		
		//D�termination du gagnant d'un round
		for (int i=0; i<instanceJeu.getNombreJoueurs(); i++) {
			if (instanceJeu.getJoueur(i).getIdentiteAssociee().getDevoilee() == false) {
				gagnantRound = instanceJeu.getJoueur(i);
			}	
		}
		gagnantRound.gagnerPoints();
		
		
	}
	
	public void fonctionnementRound(Joueur premierJoueur) {
		
		Jeu instanceJeu = Jeu.getInstance();
		
		if (instanceJeu.getEnTour() != null) {
			premierJoueur = instanceJeu.getEnTour();
		}
		
		instanceJeu.retirerCartes();
		
		instanceJeu.distributionCartesRumeurs();
		
		instanceJeu.setEnTour(premierJoueur);
		
		
		
		this.jouerUnTour();
		
		//Tour du premier joueur qui retourne le joueur du tour suivant dans la variable "enTour"
	}
	
	public void choisirProchainJoueur() {
		
	}
	
	public void jouerUnTour() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.setVueActuelle(new InterfaceChoixTour());
		instanceJeu.getVueActuelle().debutTour();
	}
	
	public Joueur jouerTour() {

		//Affichage des infos g�n�rales du joueur en tour
		
		
		Joueur prochainJoueur;
		Jeu instanceJeu = Jeu.getInstance();
		SaisirInt scan = SaisirInt.getInstance();
		
		
		instanceJeu.getEnTour().setMustAccuse(false);
		instanceJeu.getInter().debutTour();
		
		
		return instanceJeu.getEnTour();
		
	}
	
	public void revelerIdentiteJoueur() {
		
	}
	
	public void distribuerIdentite() {
		
	}
	
	public boolean isRoundEnd() {
		int nombreIdenDevoilee = 0;
		for (int i=0; i<Jeu.getInstance().getNombreJoueurs(); i++) {
			if (Jeu.getInstance().getJoueur(i).getIdentiteAssociee().getDevoilee() == true) {
				nombreIdenDevoilee += 1;
			}

		}
		if (nombreIdenDevoilee == Jeu.getInstance().getNombreJoueurs()-1) {
			return true;
		}
		else {
			return false;
		}
	}


	public void seFaireDefausserCard(int carteDefaussee, Joueur enTour) {
		Jeu.getInstance().getTasDefausse().getContenu().add(enTour.seFairePrendreCarteRumeur(carteDefaussee));
	}
}
