package controleur;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import modele.*;
import vue.*;
import controleur.*;

public class ControlerGUI{

	
	public ControlerGUI() {
    }
	
	public void setInputsDemarrer(JButton demarrer, JButton quitter, JFrame frame) {
		
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
				Jeu.getInstance().getVueActuelle().choixAccuse();
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
				Jeu.getInstance().getVueActuelle().accuser(JoueurCible);
			}
		});
	}
	
	
}