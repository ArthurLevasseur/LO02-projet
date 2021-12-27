package controleur;

import modele.Jeu;
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

public class Main {

	public static void main(String[] args) {
		
		Jeu instanceJeu = Jeu.getInstance();
		
		
		
		instanceJeu.setVueActuelle(new VueConsole());
		instanceJeu.getVueActuelle().demarrerJeu();
		
		
		
		
		//////////////////////////
		
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControlerGUI controler = new ControlerGUI();
					instanceJeu.setControler(controler);
					instanceJeu.setVueActuelle(new InterfaceDebut());
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		})*/;

	}

}
