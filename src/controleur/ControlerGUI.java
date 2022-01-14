package controleur;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import modele.*;
import vue.*;
import controleur.*;

/**
 * Contrôleur utilisé pour la vue interface graphique.
 */

public class ControlerGUI extends Observable{

	
	public ControlerGUI() {
		
    }
	
	/**
	 * Contrôleur ajoutant les listeners pour les boutons du menu principal (jouer - quitter - règles). Lance le jeu pour le premier bouton, quitte le jeu pour le second, affiche les règles pour le 3e.
	 * @param demarrer
	 * @param quitter
	 * @param Regles
	 * @param frame
	 */
	
	public void setInputsDemarrer(JButton demarrer, JButton quitter, JButton Regles, JFrame frame) {
		
		demarrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().demarrerJeu();
			}
		});
		
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		
		Regles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().afficherRegles();
			}
		});
	}
	
	/**
	 * Contrôleur ajoutant les listeners pour le choix du nombre de joueur. Vérifie que les contraintes sur le nombre de joueurs sont respectées : moins de 6, au moins 1 physique.
	 * @param inter
	 * @param redPhy
	 * @param augPhy
	 * @param redIA
	 * @param augIA
	 * @param valider
	 * @param frame
	 */
	
	public void setInputsNbJoueurs(InterfaceNbJoueurs inter, JButton redPhy, JButton augPhy, JButton redIA, JButton augIA, JButton valider, JFrame frame) {
		
		redPhy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (Jeu.getInstance().getNombrePhy()>0 && (Jeu.getInstance().getNombreIA() + Jeu.getInstance().getNombrePhy() > 3)) {
					
					Jeu.getInstance().setNombrePhy(Jeu.getInstance().getNombrePhy() - 1);
					Jeu.getInstance().actualisationInterface();
				}
			}
		});
		
		augPhy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (Jeu.getInstance().getNombrePhy()<6 && (Jeu.getInstance().getNombreIA() + Jeu.getInstance().getNombrePhy() < 6)) {
					
					Jeu.getInstance().setNombrePhy(Jeu.getInstance().getNombrePhy() + 1);
					Jeu.getInstance().actualisationInterface();
				}
			}
		});
		
		redIA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (Jeu.getInstance().getNombreIA()>0 && (Jeu.getInstance().getNombreIA() + Jeu.getInstance().getNombrePhy() > 3)) {
					
					Jeu.getInstance().setNombreIA(Jeu.getInstance().getNombreIA() - 1);
					Jeu.getInstance().actualisationInterface();
				}
			}
		});
		
		augIA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Jeu.getInstance().getNombreIA()<6 && (Jeu.getInstance().getNombreIA() + Jeu.getInstance().getNombrePhy() < 6)) {
					
					Jeu.getInstance().setNombreIA(Jeu.getInstance().getNombreIA() + 1);
					Jeu.getInstance().actualisationInterface();
				}
			}
		});
		
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inter.demarrerPartie();
				
			}
		});
 }
	
	/**
	 * Contrôleur ajoutant le listener permettant de lancer un round dès que les choix du joueur sont validés.
	 * @param valider
	 */

	public void setInputsPseudos(JButton valider) {
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().demarrerRound();
			}
		});
		
	}

	/**
	 * Contrôleur permettant de récupérer l'appui sur les boutons accuser et jouer carte hunt.
	 * @param Accuser
	 * @param JouerCarte
	 */
	
	public void setInputsTour(JButton Accuser, JButton JouerCarte) {
		
		Accuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().accuser();
			}
		});
		
		JouerCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Jeu.getInstance().getVueActuelle().choisirHunt();
			}
		});
		
	}
	
	/**
	 * Contrôleur permettant de récupérer le choix du joueur accusé par le joueur lors d'une accusation.
	 * @param bouton
	 * @param JoueurCible
	 */
	
	public void setInputAccusePlayer(JButton bouton, int JoueurCible) {
		bouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().setAccused(Jeu.getInstance().getJoueur(JoueurCible));
				Jeu.getInstance().getVueActuelle().isAccusedIA(JoueurCible);
			}
		});
	}
	
	/**
	 * Contrôleur permettant de récupérer l'appui sur les boutons réveler identité et jouer carte witch lorsque le joueur est accusé.
	 * @param btnJouerCarte
	 * @param btnAccuser
	 */

	public void setInputsAccused(JButton btnJouerCarte, JButton btnAccuser) {
		btnJouerCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().choisirWitch();
			}
		});
		
		btnAccuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getAccused().revelerIdentite();
				Jeu.getInstance().getVueActuelle().reveler();
			}
		});
		
	}
	
	/**
	 * Contrôleur récupérant le choix de l'identité réalisé par le joueur au début du round.
	 * @param btnWitch
	 * @param btnHunt
	 * @param joueur
	 */

	public void setInputsChoixIdentités(JButton btnWitch, JButton btnHunt, int joueur) {
		btnWitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().demarrerTour(joueur, true);
				

				
			}
		});
		
		btnHunt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().demarrerTour(joueur, false);
			}
		});
		
	}

	public void setImputNextTurn(JButton btnTourSuivant) {
		btnTourSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().passerTourSuivant();
			}
		});
		
	}
	
	/**
	 * Contrôleur appelant la vue nécessaire au passage au prochain tour.
	 * @param btnNextRound
	 */

	public void setInputNextRound(JButton btnNextRound) {
		btnNextRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().passerRoundSuivant();
			}
		});
		
	}
	
	/**
	 * Contrôlleur permettant de quitter le jeu.
	 * @param btnQuitter
	 */

	public void setInputQuitter(JButton btnQuitter) {
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().leave();
			}
		});
		
	}
	
	/**
	 * Contrôleur appelant la vue permettant de désigner un gagnant en cas d'égalité.
	 * @param btnCombat
	 * @param listeGagnants
	 */

	public void setInputFight(JButton btnCombat, ArrayList<Joueur> listeGagnants) {
		
		btnCombat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().fight(listeGagnants);
			}
		});
	}


	/**
	 * Contrôleur appelant la vue nécessaire pour l'éxécution d'un effet de carte.
	 * @param isHunt
	 * @param picLabel
	 * @param card
	 * @param emplacementMain
	 */
	public void setInputCarteEffet(boolean isHunt, JButton picLabel, CarteRumeur card, int emplacementMain) {
		picLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isHunt) {
					Jeu.getInstance().getEnTour().seFairePrendreCarteRumeur(emplacementMain);
					Jeu.getInstance().getEnTour().getCarteRevelees().add(card);
					card.appliquerEffetHunt();
					
				}
				else {
					Jeu.getInstance().getAccused().seFairePrendreCarteRumeur(emplacementMain);
					Jeu.getInstance().getAccused().getCarteRevelees().add(card);
					card.appliquerEffetWitch(Jeu.getInstance().getAccused());
					
				}
			}
		});
		
	}

	/**
	 * Contrôleur appelant la vue précédente depuis le choix d'une carte witch.
	 * @param btnAnnuler
	 */

	public void setInputBackWitch(JButton btnAnnuler) {
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().setBtnAnnulerWitch();
			}
		});
	}
	
	/**
	 * Contrôleur appelant la vue précédente depuis le choix d'une carte hunt.
	 * @param btnAnnuler
	 */
	
	public void setInputBackHunt(JButton btnAnnuler) {
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().setBtnAnnulerHunt();
			}
		});
	}

	/**
	 * Contrôleur appelant la vue précédente depuis la vue d'une accusation.
	 * @param btnAnnuler
	 */

	public void setInputBackAccuse(JButton btnAnnuler) {
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().setBtnAnnulerAccuse();
			}
		});
	}
	
	/**
	 * Contrôleur affectant les points à l'issue de l'accusation.
	 * @param btnJoueur
	 * @param Joueur
	 */


	public void setInputAccusePlayerHunt(JButton btnJoueur, int Joueur) {
		btnJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Jeu.getInstance().getJoueur(Joueur).getIdentiteAssociee().getIsWitch()) {
					Jeu.getInstance().getEnTour().setPoints(Jeu.getInstance().getEnTour().getPoints()+2);
				}
				else {
					Jeu.getInstance().getEnTour().setPoints(Jeu.getInstance().getEnTour().getPoints()-2);
				}
				Jeu.getInstance().getVueActuelle().revelerHunt(Joueur);
			}
		});
	}
	
	/**
	 * Contrôleur appelé lors du passage au prochain tour.
	 * @param btnJoueur
	 * @param compteur
	 */

	public void setInputNextPlayer(JButton btnJoueur, int compteur) {
		btnJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(compteur));
				Jeu.getInstance().getVueActuelle().passerTourSuivant();
			}
		});
	}
	
	/**
	 * Contrôleur appelé lors de l'affichage de l'identité en secret d'un joueur.
	 * @param btnJoueur
	 * @param compteur
	 */


	public void setInputNextPlayerSecretly(JButton btnJoueur, int compteur) {
		btnJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Jeu.getInstance().getJoueur(compteur).getIdentiteAssociee().getIsWitch()) {
					Jeu.getInstance().getVueActuelle().setTextSecret("C'est une Witch !");
				}
				else {
					Jeu.getInstance().getVueActuelle().setTextSecret("C'est un Villager !");
				}
				Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(compteur));
				
				
				
			}
		});
	}

	/**
	 * Contrôleur appelé lors de la défausse d'une carte.
	 * @param boutonCarte
	 * @param compteur
	 */

	public void setInputDiscardCarte(JButton boutonCarte, int compteur) {
		boutonCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getTasDefausse().defausserUneCarte(Jeu.getInstance().getAccused().seFairePrendreCarteRumeur(compteur));
				Jeu.getInstance().setEnTour(Jeu.getInstance().getAccused());
				Jeu.getInstance().getVueActuelle().passerTourSuivant();
			}
		});
	}
	
	/**
	 * Contrôleur appelé lorsqu'un joueur récupère une de ses cartes révelées.
	 * @param isHunt
	 * @param boutonCarte
	 * @param card
	 */


	public void setInputRecupererRevelee(boolean isHunt, JButton boutonCarte, int card) {
		boutonCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isHunt){
					Jeu.getInstance().getEnTour().prendreCarteRumeur(Jeu.getInstance().getEnTour().getCarteRevelees().get(card));
					Jeu.getInstance().getEnTour().getCarteRevelees().remove(card);
					Jeu.getInstance().getVueActuelle().choisirProchainJoueur(null);
				}
				else {
					Jeu.getInstance().getAccused().prendreCarteRumeur(Jeu.getInstance().getAccused().getCarteRevelees().get(card));
					Jeu.getInstance().getAccused().getCarteRevelees().remove(card);
					Jeu.getInstance().setEnTour(Jeu.getInstance().getAccused());
					Jeu.getInstance().getVueActuelle().passerTourSuivant();
				}
			}
		});
	}

	/**
	 * Contrôleur appelé lors du vol d'une carte
	 * @param boutonCarte
	 * @param compteur
	 */

	public void setInputVolerCarte(JButton boutonCarte, int compteur) {
		boutonCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getAccused().getCarteEnMain().add(Jeu.getInstance().getEnTour().seFairePrendreCarteRumeur(compteur));
				Jeu.getInstance().setEnTour(Jeu.getInstance().getAccused());
				Jeu.getInstance().getVueActuelle().passerTourSuivant();
			}
		});
	}
	
	/**
	 * Contrôleur appelé pour retirer la carte volée par le joueur.
	 * @param btnJoueur
	 * @param joueur
	 */


	public void setInputNextPlayerVoler(JButton btnJoueur, int joueur) {
		btnJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Jeu.getInstance().getJoueur(joueur).getCarteEnMain().isEmpty()) {
					Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(joueur));
					Jeu.getInstance().getVueActuelle().passerTourSuivant();
				}
				else {
					int randomCard = (int) (Math.random() * Jeu.getInstance().getJoueur(joueur).getCarteEnMain().size());
					Jeu.getInstance().getVueActuelle().afficherCarteVolee(Jeu.getInstance().getJoueur(joueur).getCarteEnMain().get(randomCard));
					Jeu.getInstance().getEnTour().getCarteEnMain().add(Jeu.getInstance().getJoueur(joueur).seFairePrendreCarteRumeur(randomCard));
					Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(joueur));
				}
				
			}
		});
		
	}
	
	/**
	 * Contrôleur appelant la vue permettant de choisir la carte à voler
	 * @param btnJoueur
	 * @param joueur
	 */
	
	public void setInputNextPlayerChoixVoler(JButton btnJoueur, int joueur) {
		btnJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().petNewtChoixCarte(Jeu.getInstance().getJoueur(joueur));
			}
		});
		
	}
	
	/**
	 * Contrôleur appelé lorsqu'un joueur récupère une carte dans la défausse
	 * @param cartePrise
	 * @param emplacementCarte
	 */


	public void setInputRecupererDefausse(JButton cartePrise, int emplacementCarte) {
		cartePrise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getTasDefausse().getContenu().add(Jeu.getInstance().getEnTour().getCarteRevelees().get(Jeu.getInstance().getEnTour().getCarteRevelees().size() - 1));
				Jeu.getInstance().getEnTour().getCarteRevelees().remove(Jeu.getInstance().getEnTour().getCarteRevelees().size() - 1);
				
				Jeu.getInstance().getEnTour().prendreCarteRumeur(Jeu.getInstance().getTasDefausse().getContenu().get(emplacementCarte));
				Jeu.getInstance().getTasDefausse().getContenu().remove(emplacementCarte);
				Jeu.getInstance().getVueActuelle().passerTourSuivant();
			}
		});
	}


	public void setInputVolerCarteJoueur(JButton boutonCarte, int emplacementCarte, Joueur joueur) {
		boutonCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getEnTour().getCarteEnMain().add(joueur.getCarteRevelees().get(emplacementCarte));
				joueur.getCarteRevelees().remove(emplacementCarte);
				Jeu.getInstance().getVueActuelle().choisirProchainJoueur(null);
			}
		});
		
	}

/**
 * Vue appelée lorsqu'un joueur doit choisir qui cibler avec la carte ducking stool.
 * @param btnJoueur
 * @param Joueur
 */
	
	public void setInputDuckingStoolCible(JButton btnJoueur, int Joueur) {
		btnJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Jeu.getInstance().getJoueur(Joueur).isIA()) {
					Jeu.getInstance().getJoueur(Joueur).reponseDuckingStool(Joueur);
				}
				else {
					Jeu.getInstance().getVueActuelle().duckingStoolChoixCible(Joueur);
				}
			}
		});
		
	}
	
	/**
	 * Contrôleur appelé quand le joueur ciblé par la carte duckingstool doit faire son choix (se réveler ou défausser)
	 * @param btnReveal
	 * @param btnDiscard
	 * @param joueur
	 */

	
	public void setInputsChoixDuckingStool(JButton btnReveal, JButton btnDiscard, int joueur) {
		btnReveal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Jeu.getInstance().getJoueur(joueur).getIdentiteAssociee().getIsWitch()) {
					Jeu.getInstance().getEnTour().setPoints(Jeu.getInstance().getEnTour().getPoints()+1);
				}
				else {
					Jeu.getInstance().getEnTour().setPoints(Jeu.getInstance().getEnTour().getPoints()-1);
				}
				Jeu.getInstance().getVueActuelle().revelerHunt(joueur);
			}
		});
		btnDiscard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().setAccused(Jeu.getInstance().getJoueur(joueur));
				Jeu.getInstance().getVueActuelle().discard(null);
			}
		});
	}
	
	/**
	 * Contrôleur appelé lorsque la carte Evil Eye a été jouée
	 * @param isHunt
	 * @param btnJoueur
	 * @param joueur
	 */


	public void setEvilEye(boolean isHunt, JButton btnJoueur, int joueur) {
		btnJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isHunt) {
					Jeu.getInstance().getEnTour().setAccusable(false);
				}
				else {
					Jeu.getInstance().getAccused().setAccusable(false);
				}
				Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(joueur));
				
				Jeu.getInstance().getVueActuelle().passerTourSuivantAccusable();
			}
		});
	}
	
	/**
	 * Contrôleur permettant au joueur de revenir à la vue précédente.
	 * @param retour
	 */


	public void setInputReturnDebut(JButton retour) {
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().initialize();
			}
		});
	}

	/**
	 * Contrôleur permettant de passer au tour suivant. Remet à true le booleen accusable de chaque joueur. Si le joueur est une IA, le récap du tour est lancé.
	 * @param btnTourSuivant
	 */

	public void setInputNextTurnAccusable(JButton btnTourSuivant) {
		btnTourSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().passerTourSuivantAccusable();
			}
		});
	}
	
	/**
	 * Contrôleur permettant de passer au tour suivant. Remet à true le booleen accusable de chaque joueur. Si le joueur est une IA, le récap du tour est lancé.
	 * @param btnTourSuivant
	 */

	public void setInputNextTurnAccusableHunt(JButton btnTourSuivant) {
		btnTourSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().passerTourSuivantAccusableHunt();
			}
		});
	}

}