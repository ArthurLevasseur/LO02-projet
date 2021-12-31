package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

import modele.Jeu;
import modele.Joueur;
import modele.Round;

import javax.swing.JButton;
import java.awt.Color;

public class InterfaceFinRound implements Vue{

	private JFrame frame;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @param gagnant 
	 */
	public InterfaceFinRound(Joueur gagnant) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize(gagnant);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @param gagnant 
	 */
	public void initialize(Joueur gagnant) {
		Jeu.getInstance().setVueActuelle(this);
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1280, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelTitre = new JPanel();
		panelTitre.setBounds(0, 35, 1266, 50);
		frame.getContentPane().add(panelTitre);
		
		JLabel lblTitre = new JLabel("Joueur " + gagnant.getPseudo() + ", vous avez gagné ce round !");
		lblTitre.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panelTitre.add(lblTitre);
		
		if (gagnant.getIdentiteAssociee().getIsWitch()) {
			JLabel lblDescriptif = new JLabel("Vous etiez une Witch, vous gagnez donc 2 points !");
			lblDescriptif.setHorizontalAlignment(SwingConstants.CENTER);
			lblDescriptif.setForeground(SystemColor.controlDkShadow);
			lblDescriptif.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
			lblDescriptif.setBounds(0, 200, 1266, 21);
			frame.getContentPane().add(lblDescriptif);
		}
		else {
			JLabel lblDescriptif = new JLabel("Vous etiez un Villager, vous gagnez donc 1 points !");
			lblDescriptif.setHorizontalAlignment(SwingConstants.CENTER);
			lblDescriptif.setForeground(SystemColor.controlDkShadow);
			lblDescriptif.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
			lblDescriptif.setBounds(0, 200, 1266, 21);
			frame.getContentPane().add(lblDescriptif);
		}
		
		JButton btnNextRound = new JButton("Round suivant");
		btnNextRound.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnNextRound.setBackground(Color.WHITE);
		btnNextRound.setBounds(476, 356, 323, 80);
		frame.getContentPane().add(btnNextRound);
		
		
		Jeu.getInstance().getControler().setInputNextRound(btnNextRound);
		this.frame.setVisible(true);
		
		Jeu.getInstance().setEnTour(gagnant);
	}
	
	public void passerRoundSuivant() {
		
		Jeu.getInstance().implementGagnant();
		
		this.frame.setVisible(false);
		
		if (Jeu.getInstance().getGagnants().size() > 0) {
			InterfaceFinJeu finJeu = new InterfaceFinJeu(Jeu.getInstance().getGagnants());
		}
		else {
			Jeu.getInstance().initRound();
		}
		
		
		
	}

}
