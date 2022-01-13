package controleur;

import modele.Jeu;
import modele.SaisirInt;
import vue.*;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import controleur.*;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

/**
 * Point de départ du programme. Demande à l'utilisateur quelle vue utiliser (console ou graphique).
 *
 */

public class Main {
	
	/**
	 * Fonction de départ
	 * @param args
	 */

	public static void main(String[] args) {
		
		SaisirInt scan = SaisirInt.getInstance();
		System.out.println("Voulez-vous lancer le jeu en :\n0) Console.\n1) Interface Graphique\n");
		int choix = -1;
		while (choix<0 || choix >1) {
			System.out.println("Choisissez entre 0 et 1.\n");
			choix = scan.nextInt();
		}
		if (choix == 0) {
			Jeu instanceJeu = Jeu.getInstance();
			
			instanceJeu.setVueActuelle(new VueConsole());
			instanceJeu.getVueActuelle().demarrerJeu();
		}
		else if (choix == 1) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Jeu instanceJeu = Jeu.getInstance();
						ControlerGUI controler = new ControlerGUI();
						instanceJeu.setControler(controler);
						instanceJeu.setVueActuelle(new InterfaceDebut());
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

	}

}
