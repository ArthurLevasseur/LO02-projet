package vue;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.SwingConstants;

import controleur.*;

import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import modele.*;
import vue.*;
import javax.swing.JLayeredPane;

public class InterfaceDebut implements Vue {

	private JFrame frame;
	private JLabel lblBienvenu;
	private JButton btnDemarrer;
	private JButton btnQuitter;
	private ControlerGUI controler;
	private JButton btnRegles;
	
	private JLayeredPane debutJeu;

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	
	/*
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jeu instanceJeu = new Jeu();
					InterfaceDebut window = new InterfaceDebut();
					Jeu.setVueActuelle(window);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public InterfaceDebut() {
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1280, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		if (debutJeu != null) {
			debutJeu.removeAll();
			debutJeu.setVisible(false);
		}
		
		debutJeu = new JLayeredPane();
		debutJeu.setBounds(0, 0, 1280, 1080);
		frame.getContentPane().add(debutJeu);
		debutJeu.setVisible(true);
		
		lblBienvenu = new JLabel("Bienvenue dans Witch Hunt !");
		lblBienvenu.setBounds(0, 35, 1266, 49);
		lblBienvenu.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblBienvenu.setHorizontalAlignment(SwingConstants.CENTER);
		debutJeu.add(lblBienvenu);
		
		btnDemarrer = new JButton("Nouvelle Partie");
		btnDemarrer.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnDemarrer.setBounds(457, 155, 323, 80);
		debutJeu.add(btnDemarrer);
		
		btnRegles = new JButton("R\u00E8gles du jeu");
		btnRegles.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnRegles.setBounds(457, 305, 323, 80);
		debutJeu.add(btnRegles);
		this.frame.setVisible(true);
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnQuitter.setBounds(457, 455, 323, 80);
		debutJeu.add(btnQuitter);
		
		debutJeu.setVisible(true);
		
		Jeu.getInstance().getControler().setInputsDemarrer(btnDemarrer, btnQuitter, btnRegles, frame);
	}

	public JButton getBtnDemarrer() {
		return btnDemarrer;
	}

	public void setBtnDemarrer(JButton btnDemarrer) {
		this.btnDemarrer = btnDemarrer;
	}

	public JButton getBtnQuitter() {
		return btnQuitter;
	}

	public void setBtnQuitter(JButton btnQuitter) {
		this.btnQuitter = btnQuitter;
	}

	public ControlerGUI getControler() {
		return controler;
	}
	
	public void demarrerJeu() {
		this.frame.setVisible(false);
		InterfaceNbJoueurs suite = new InterfaceNbJoueurs();
	}
	
	public void afficherRegles() {
		
		if (debutJeu != null) {
			debutJeu.removeAll();
			debutJeu.setVisible(false);
		}
		
		JButton btnAnnuler = new JButton("<");
		btnAnnuler.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		btnAnnuler.setBackground(new Color(255,255,255));
		btnAnnuler.setBounds(10, 10, 60, 60);
		debutJeu.add(btnAnnuler);
		Jeu.getInstance().getControler().setInputReturnDebut(btnAnnuler);
		
		
		
		try {
			BufferedImage myPicture;
			myPicture = ImageIO.read(new File("ReglesJeu.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setBounds(0,-120,1280, 1080);
			debutJeu.add(picLabel);
			
			debutJeu.moveToFront(picLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		debutJeu.moveToFront(btnAnnuler);
		debutJeu.setVisible(true);
	}
}
