package controleur;

import modele.*;
import vue.*;

public class Round {
	
	private Joueur[] identitesRevelees = new Joueur[6];
	private Joueur gagnantRound = null;
	
	public Round() {
		
		
	}
	
	
	public void debutRound(Joueur premierJoueur) {
		
		Jeu instanceJeu = Jeu.getInstance();
		
		for (int i = 0; i < instanceJeu.getNombreJoueurs(); i++) {
			instanceJeu.getJoueur(i).getIdentiteAssociee().choisirIdentite(instanceJeu.getJoueur(i),instanceJeu.getJoueur(i).isIA());
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
	
	public void choisirProchainJoueur() {
		
	}
	
	public Joueur jouerTour() {

		//Affichage des infos g�n�rales du joueur en tour
		
		
		Joueur prochainJoueur;
		Jeu instanceJeu = Jeu.getInstance();
		SaisirInt scan = SaisirInt.getInstance();
		int choix = instanceJeu.getVueActuelle().debutTour();
		instanceJeu.getEnTour().setMustAccuse(false);
		int choixAccuse = 0;
		
		if (choix == 1) {
			choixAccuse = instanceJeu.getVueActuelle().choixAccuse();
		}
		
				
		//En situation habituelle (si apr�s les v�rification, le choix reste d'accuser un joueur):
		if (choix==1) {
				
			//Si le choix correspond � un joueur ciblable
			if (0<choixAccuse && choixAccuse<instanceJeu.getNombreJoueurs()+1 && instanceJeu.getJoueur(choixAccuse-1).getIdentiteAssociee().getDevoilee() == false && instanceJeu.getJoueur(choixAccuse-1)!=instanceJeu.getEnTour() && instanceJeu.getJoueur(choixAccuse-1).isAccusable()==true) {
				//R�initialisation de la variable accusable (si ce n'est pas encore fait) 
				for (int i=0 ; i<instanceJeu.getNombreJoueurs() ; i++) {
					if (instanceJeu.getJoueur(i).isAccusable()==false) {
						instanceJeu.getJoueur(i).setAccusable(true);
					}
				}
				
				//On d�finit le joueur accus� dans le jeu
				instanceJeu.setAccused(instanceJeu.getJoueur(choixAccuse-1));
				
				//On appelle la m�thode d'un joueur accus� (elle retourne le prochain joueur, ce qui d�pend des futurs choix de l'accus�)
				prochainJoueur = instanceJeu.getJoueur(choixAccuse-1).estAccuse();
				
				return prochainJoueur;
			}
			//Si le choix ne correspond pas � un joueur ciblable
			else {
				System.out.println("Choix invalide !");
				choixAccuse = -1;
				choixAccuse = instanceJeu.getVueActuelle().choixAccuse();
				
			}
}
		
	
	if (choix == 2) {
		boolean peutJouerCarte = false;

		for (int i=0; i<instanceJeu.getEnTour().getCarteEnMain().size(); i++) {
			if (((instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() != 1 && instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() != 2) || ((instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() == 1 || instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() == 2)) && instanceJeu.getEnTour().getIdentiteAssociee().getDevoilee() == true) && (instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() != 3 || (instanceJeu.getEnTour().getCarteEnMain().get(i).getNumCarte() == 3 && !(instanceJeu.getEnTour().getCarteRevelees().isEmpty())))) {
				peutJouerCarte = true;
			}
		}
		if (peutJouerCarte == true) {
			//Appelle de la m�thode de jeu d'une carte rumeur, renvoie le prochain joueur (ce qui d�pend des choix du joueur en tour)
			return instanceJeu.getEnTour().jouerCarteHunt();
		}
		else {
			//La boucle while va se r�effectuer avec choix =1, donc une accusation
			choix = 1;
			System.out.println("Vous ne pouvez pas jouer de cartes hunts !");
		}
		
	}
	else {
		System.out.println("Choix invalide !");
	}
		return null;
	}
	
	public void revelerIdentiteJoueur() {
		
	}
	
	public void distribuerIdentite() {
		
	}
}
