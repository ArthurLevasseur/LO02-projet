package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JTextPane;
import controleur.Jeu;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.Panel;
import java.lang.reflect.Array;
import java.awt.List;
import java.awt.Label;
import javax.swing.JList;
import java.awt.Choice;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import modele.*;
import vue.*;
import controleur.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JLayeredPane;


public class InterfaceChoixTour implements Observer, Vue {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JPanel panelSuiviPartie;
	private JLabel lblTitreSuivi;
	private JPanel panelTitre;
	private JLabel lblTitre;
	private Panel panelCartesEnMain;
	private JPanel pnlTitreEnMain;
	private JLabel pnlCartesEnMain;
	private JButton btnJouerCarte;
	private JButton btnAccuser;
	private Panel panelCartesRevelees;
	private JPanel pnlTitreRevelees;
	private JLabel pnlRevelees;
	private JLabel lblDescriptif;
	
	private JLayeredPane LayeredPaneChoixTour;
	private JLayeredPane LayeredPaneSuivi;
	private JLayeredPane LayeredPaneAccuser;
	private JLayeredPane LayeredPaneEnsemble;
	
	private int compteur;
	private JPanel panelChoixAccuse;
	private JPanel pnlTitreAccuse;
	private JLabel lblQuiVoulezvousAccuser;
	
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public InterfaceChoixTour() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		String[][] infoJoueur = new String[Jeu.getInstance().getNombreJoueurs() + 1][3];
		
		compteur = 1;
		
		infoJoueur[0][0] = "PSEUDOS :";
		infoJoueur[0][1] = "STATUS :";
		infoJoueur[0][2] = "POINTS :";
		
		Jeu.getInstance().getEnsembleJoueurs().forEach((player) -> {
			
			if (player.getIdentiteAssociee().getDevoilee() == false) {
				infoJoueur[compteur][0] = player.getPseudo();
				infoJoueur[compteur][1] = "ENROUND";
				infoJoueur[compteur][2] = (player.getPoints() + "");
			}
			else {
				if (player.getIdentiteAssociee().getIsWitch() == false) {
					infoJoueur[compteur][0] = player.getPseudo();
					infoJoueur[compteur][1] = "VILLAGEOIS";
					infoJoueur[compteur][2] = (player.getPoints() + "");
				}
				else {
					infoJoueur[compteur][0] = player.getPseudo();
					infoJoueur[compteur][1] = "WHICH";
					infoJoueur[compteur][2] = (player.getPoints() + "");
				}
			}
			compteur++;
			
		});
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1280, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		LayeredPaneEnsemble = new JLayeredPane();
		LayeredPaneEnsemble.setBounds(0, 0, 1280, 1080);
		frame.getContentPane().add(LayeredPaneEnsemble);
		
		
		
		
		
		
		LayeredPaneSuivi = new JLayeredPane();
		LayeredPaneSuivi.setBackground(UIManager.getColor("Button.background"));
		LayeredPaneSuivi.setBounds(868, 0, 402, 158);
		LayeredPaneEnsemble.add(LayeredPaneSuivi);
		
		panelSuiviPartie = new JPanel();
		panelSuiviPartie.setBackground(new Color(0,0,0,0));
		panelSuiviPartie.setBounds(-17, 0, 467, 157);
		LayeredPaneSuivi.add(panelSuiviPartie);
		
		lblTitreSuivi = new JLabel("Suivi de la partie :");
		lblTitreSuivi.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		panelSuiviPartie.add(lblTitreSuivi);
		

		
		
		
		table = new JTable();
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(
			infoJoueur,
			new String[] {
					"Joueur", "Identit\u00E9", "Points"
				}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		panelSuiviPartie.add(table);
		
		
		
		LayeredPaneChoixTour = new JLayeredPane();
		LayeredPaneChoixTour.setBackground(UIManager.getColor("Button.background"));
		LayeredPaneChoixTour.setBounds(0, 0, 1280, 1080);
		LayeredPaneEnsemble.add(LayeredPaneChoixTour);
		
		panelCartesEnMain = new Panel();
		panelCartesEnMain.setBackground(UIManager.getColor("Button.background"));
		panelCartesEnMain.setBounds(21, 251, 1224, 294);
		LayeredPaneChoixTour.add(panelCartesEnMain);
		
		pnlTitreEnMain = new JPanel();
		pnlTitreEnMain.setBackground(UIManager.getColor("Button.background"));
		FlowLayout fl_pnlTitreEnMain = (FlowLayout) pnlTitreEnMain.getLayout();
		fl_pnlTitreEnMain.setHgap(500);
		panelCartesEnMain.add(pnlTitreEnMain);
		
		pnlCartesEnMain = new JLabel("Cartes en main :");
		pnlTitreEnMain.add(pnlCartesEnMain);
		pnlCartesEnMain.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));

		Jeu.getInstance().getEnTour().getCarteEnMain().forEach(carte -> {		
			try {
				BufferedImage myPicture = ImageIO.read(new File("Carte" + carte.getNumCarte() + ".png"));
				
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				picLabel.setSize(250, 735);
				panelCartesEnMain.add(picLabel);
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		
		
		
		panelTitre = new JPanel();
		panelTitre.setBounds(0, 35, 1266, 50);
		LayeredPaneChoixTour.add(panelTitre);
		
		lblTitre = new JLabel("C'est au tour du joueur " + Jeu.getInstance().getEnTour().getPseudo());
		lblTitre.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panelTitre.add(lblTitre);
		
		
		btnJouerCarte = new JButton("Jouer une carte");
		btnJouerCarte.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnJouerCarte.setBounds(260, 159, 323, 80);
		LayeredPaneChoixTour.add(btnJouerCarte);
		
		btnAccuser = new JButton("Accuser un joueur");
		btnAccuser.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnAccuser.setBounds(675, 159, 323, 80);
		LayeredPaneChoixTour.add(btnAccuser);
		
		
		
		
		panelCartesRevelees = new Panel();
		panelCartesRevelees.setBackground(UIManager.getColor("Button.background"));
		panelCartesRevelees.setBounds(21, 543, 1224, 302);
		LayeredPaneChoixTour.add(panelCartesRevelees);
		
		pnlTitreRevelees = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlTitreRevelees.getLayout();
		flowLayout.setHgap(500);
		pnlTitreRevelees.setBackground(UIManager.getColor("Button.background"));
		panelCartesRevelees.add(pnlTitreRevelees);
		
		pnlRevelees = new JLabel("Cartes r\u00E9v\u00E9l\u00E9es :");
		pnlRevelees.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		pnlTitreRevelees.add(pnlRevelees);
		
		lblDescriptif = new JLabel("Que voulez vous faire ?");
		lblDescriptif.setForeground(UIManager.getColor("Button.darkShadow"));
		lblDescriptif.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescriptif.setBounds(0, 113, 1266, 21);
		LayeredPaneChoixTour.add(lblDescriptif);
		lblDescriptif.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		
		Jeu.getInstance().getEnTour().getCarteRevelees().forEach(carte -> {		
			try {
				BufferedImage myPicture = ImageIO.read(new File("Carte" + carte.getNumCarte() + ".png"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				picLabel.setSize(250, 735);
				panelCartesRevelees.add(picLabel);
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		
		
		Jeu.getInstance().setVueActuelle(this);
		Jeu.getInstance().addObserver(this);
		this.frame.setVisible(true);
		LayeredPaneEnsemble.moveToFront(LayeredPaneChoixTour);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		Jeu.getInstance().getControler().setInputsTour(this.btnAccuser,this.btnJouerCarte);
		
		
	}
	
	@Override
	public void update(Observable o, Object arg) {

	}
	
	@Override
	public void debutTour() {
		this.btnAccuser.setVisible(false);
		this.btnJouerCarte.setVisible(false);
	}
	
	public void choixAccuse() {
		
		
		this.btnAccuser.setVisible(false);
		this.btnJouerCarte.setVisible(false);
		
		
		LayeredPaneAccuser = new JLayeredPane();
		LayeredPaneAccuser.setBounds(0, 0, 1280, 1080);
		LayeredPaneAccuser.setBackground(UIManager.getColor("Button.background"));
		LayeredPaneEnsemble.add(LayeredPaneAccuser);
		
		panelChoixAccuse = new JPanel();
		panelChoixAccuse.setBackground(UIManager.getColor("Button.background"));
		panelChoixAccuse.setBounds(0, 0, 1280, 1080);
		LayeredPaneAccuser.add(panelChoixAccuse);
		
		pnlTitreAccuse = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlTitreAccuse.getLayout();
		flowLayout_2.setHgap(400);
		pnlTitreAccuse.setBackground(new Color(0, 0, 0, 0));
		panelChoixAccuse.add(pnlTitreAccuse);
		
		lblQuiVoulezvousAccuser = new JLabel("Qui voulez-vous accuser ?");
		lblQuiVoulezvousAccuser.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		pnlTitreAccuse.add(lblQuiVoulezvousAccuser);
		

		compteur = 0;
		Jeu.getInstance().getEnsembleJoueurs().forEach(Joueur -> {		
			
			if (Joueur.getIdentiteAssociee().getDevoilee() == false && Joueur.isAccusable() == true) {
				
				JPanel pnlbouton = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlbouton.getLayout();
				flowLayout.setHgap(400);
				pnlbouton.setBackground(new Color(0, 0, 0, 0));
				
				JButton btnJoueur = new JButton("Joueur : " + Joueur.getPseudo() + " | Points : " + Joueur.getPoints());
				btnJoueur.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
				btnJoueur.setBounds(500, 159, 323, 80);
				Jeu.getInstance().getControler().setInputAccusePlayer(btnJoueur, compteur);
				
				pnlbouton.add(btnJoueur);
				
				panelChoixAccuse.add(pnlbouton);
				
				compteur++;
			}
			
		});
		LayeredPaneEnsemble.moveToFront(LayeredPaneAccuser);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		
		
	}

	public void accuser(int joueur) {
		
		//METHODE QUI ACTUALISE LES VALEURS DU JEU
		
		LayeredPaneEnsemble.moveToFront(LayeredPaneChoixTour);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		
		LayeredPaneAccuser.setVisible(false);
		
		lblTitre.setText("Joueur \"" + Jeu.getInstance().getJoueur(joueur).getPseudo() + "\", joueur \"" + Jeu.getInstance().getJoueur(joueur).getPseudo() + "\" vous accuse !");
		
		
		
		panelCartesEnMain = new Panel();
		panelCartesEnMain.setBackground(UIManager.getColor("Button.background"));
		panelCartesEnMain.setBounds(21, 251, 1224, 294);
		LayeredPaneChoixTour.add(panelCartesEnMain);
		
		pnlTitreEnMain = new JPanel();
		pnlTitreEnMain.setBackground(UIManager.getColor("Button.background"));
		FlowLayout fl_pnlTitreEnMain = (FlowLayout) pnlTitreEnMain.getLayout();
		fl_pnlTitreEnMain.setHgap(500);
		panelCartesEnMain.add(pnlTitreEnMain);
		
		pnlCartesEnMain = new JLabel("Cartes en main :");
		pnlTitreEnMain.add(pnlCartesEnMain);
		pnlCartesEnMain.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));

		Jeu.getInstance().getJoueur(joueur).getCarteEnMain().forEach(carte -> {		
			try {
				BufferedImage myPicture = ImageIO.read(new File("Carte" + carte.getNumCarte() + ".png"));
				
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				picLabel.setSize(250, 735);
				panelCartesEnMain.add(picLabel);
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		
		
		
		panelCartesRevelees = new Panel();
		panelCartesRevelees.setBackground(UIManager.getColor("Button.background"));
		panelCartesRevelees.setBounds(21, 543, 1224, 302);
		LayeredPaneChoixTour.add(panelCartesRevelees);
		
		pnlTitreRevelees = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlTitreRevelees.getLayout();
		flowLayout.setHgap(500);
		pnlTitreRevelees.setBackground(UIManager.getColor("Button.background"));
		panelCartesRevelees.add(pnlTitreRevelees);
		
		pnlRevelees = new JLabel("Cartes r\u00E9v\u00E9l\u00E9es :");
		pnlRevelees.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		pnlTitreRevelees.add(pnlRevelees);
		
		lblDescriptif = new JLabel("Que voulez vous faire ?");
		lblDescriptif.setForeground(UIManager.getColor("Button.darkShadow"));
		lblDescriptif.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescriptif.setBounds(0, 113, 1266, 21);
		LayeredPaneChoixTour.add(lblDescriptif);
		lblDescriptif.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		
		Jeu.getInstance().getJoueur(joueur).getCarteRevelees().forEach(carte -> {		
			try {
				BufferedImage myPicture = ImageIO.read(new File("Carte" + carte.getNumCarte() + ".png"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				picLabel.setSize(250, 735);
				panelCartesRevelees.add(picLabel);
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		
		
		btnJouerCarte = new JButton("Jouer une carte");
		btnJouerCarte.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnJouerCarte.setBounds(260, 159, 323, 80);
		LayeredPaneChoixTour.add(btnJouerCarte);
		
		btnAccuser = new JButton("Révéler son identité");
		btnAccuser.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnAccuser.setBounds(675, 159, 323, 80);
		LayeredPaneChoixTour.add(btnAccuser);
		
	}

}
