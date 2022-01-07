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

public class ControlerGUI extends Observable{

	
	public ControlerGUI() {
		
    }
	
	
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

	public void setInputsPseudos(JButton valider) {
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().demarrerRound();
			}
		});
		
	}

	
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
	
	public void setInputAccusePlayer(JButton bouton, int JoueurCible) {
		bouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().setAccused(Jeu.getInstance().getJoueur(JoueurCible));
				Jeu.getInstance().getVueActuelle().isAccusedIA(JoueurCible);
			}
		});
	}

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

	public void setInputNextRound(JButton btnNextRound) {
		btnNextRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().passerRoundSuivant();
			}
		});
		
	}

	public void setInputQuitter(JButton btnQuitter) {
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().leave();
			}
		});
		
	}

	public void setInputFight(JButton btnCombat, ArrayList<Joueur> listeGagnants) {
		
		btnCombat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().fight(listeGagnants);
			}
		});
	}


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


	public void setInputBackWitch(JButton btnAnnuler) {
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().setBtnAnnulerWitch();
			}
		});
	}
	
	public void setInputBackHunt(JButton btnAnnuler) {
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().setBtnAnnulerHunt();
			}
		});
	}


	public void setInputBackAccuse(JButton btnAnnuler) {
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().setBtnAnnulerAccuse();
			}
		});
	}


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


	public void setInputNextPlayer(JButton btnJoueur, int compteur) {
		btnJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(compteur));
				Jeu.getInstance().getVueActuelle().passerTourSuivant();
			}
		});
	}


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


	public void setInputDiscardCarte(JButton boutonCarte, int compteur) {
		boutonCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getTasDefausse().defausserUneCarte(Jeu.getInstance().getAccused().seFairePrendreCarteRumeur(compteur));
				Jeu.getInstance().setEnTour(Jeu.getInstance().getAccused());
				Jeu.getInstance().getVueActuelle().passerTourSuivant();
			}
		});
	}


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


	public void setInputVolerCarte(JButton boutonCarte, int compteur) {
		boutonCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getAccused().getCarteEnMain().add(Jeu.getInstance().getEnTour().seFairePrendreCarteRumeur(compteur));
				Jeu.getInstance().setEnTour(Jeu.getInstance().getAccused());
				Jeu.getInstance().getVueActuelle().passerTourSuivant();
			}
		});
	}


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
	
	public void setInputNextPlayerChoixVoler(JButton btnJoueur, int joueur) {
		btnJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().petNewtChoixCarte(Jeu.getInstance().getJoueur(joueur));
			}
		});
		
	}


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


	public void setInputReturnDebut(JButton retour) {
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().initialize();
			}
		});
	}


	public void setInputNextTurnAccusable(JButton btnTourSuivant) {
		btnTourSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().passerTourSuivantAccusable();
			}
		});
	}

	public void setInputNextTurnAccusableHunt(JButton btnTourSuivant) {
		btnTourSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu.getInstance().getVueActuelle().passerTourSuivantAccusableHunt();
			}
		});
	}

}