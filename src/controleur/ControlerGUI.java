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

	
	public ControlerGUI(JButton demarrer, JButton quitter, JFrame frame) {
		
		demarrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Passer à la suite, mais comment...
				 */
			}
		});
		
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		
    }
	
}