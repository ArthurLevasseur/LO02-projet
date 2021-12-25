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
import java.awt.SystemColor;


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
	private JLayeredPane layeredPaneReveler;
	private JPanel panelTitreReveler;
	private JLabel lblTitreReveler;
	private JLabel lblDescriptifReveler;
	private JButton btnTourSuivant;
	private JPanel panelID;
	private JPanel pnlTitreID;
	private JLabel lblTitreID;
	private JPanel pnlContourID;
	private JLabel lblWitch;
	
	
	
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
		panelSuiviPartie.setBounds(-18, 0, 468, 157);
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
		table.getColumnModel().getColumn(0).setPreferredWidth(105);
		table.getColumnModel().getColumn(1).setPreferredWidth(105);
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		panelSuiviPartie.add(table);
		
		
		
		
		
		LayeredPaneChoixTour = new JLayeredPane();
		LayeredPaneChoixTour.setBackground(UIManager.getColor("Button.background"));
		LayeredPaneChoixTour.setBounds(0, 0, 1280, 1080);
		LayeredPaneEnsemble.add(LayeredPaneChoixTour);
		
		
		
		panelID = new JPanel();
		panelID.setBounds(0, 0, 388, 149);
		LayeredPaneChoixTour.add(panelID);
		
		pnlTitreID = new JPanel();
		FlowLayout fl_pnlTitreID = (FlowLayout) pnlTitreID.getLayout();
		fl_pnlTitreID.setHgap(50);
		panelID.add(pnlTitreID);
		
		lblTitreID = new JLabel("Votre identit\u00E9 :");
		lblTitreID.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		pnlTitreID.add(lblTitreID);
		
		if (Jeu.getInstance().getEnTour().getIdentiteAssociee().getIsWitch() == true) {
			
			pnlContourID = new JPanel();
			pnlContourID.setForeground(Color.WHITE);
			pnlContourID.setBackground(Color.BLACK);
			FlowLayout flowLayout_1 = (FlowLayout) pnlContourID.getLayout();
			flowLayout_1.setVgap(20);
			flowLayout_1.setHgap(50);
			panelID.add(pnlContourID);
			
			lblWitch = new JLabel("WITCH");
			lblWitch.setForeground(Color.WHITE);
			lblWitch.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
			lblWitch.setBackground(Color.BLACK);
			pnlContourID.add(lblWitch);
		}
		else {
			
			pnlContourID = new JPanel();
			pnlContourID.setForeground(Color.WHITE);
			pnlContourID.setBackground(Color.WHITE);
			FlowLayout flowLayout_1 = (FlowLayout) pnlContourID.getLayout();
			flowLayout_1.setVgap(20);
			flowLayout_1.setHgap(50);
			panelID.add(pnlContourID);
			
			lblWitch = new JLabel("VILLAGER");
			lblWitch.setForeground(Color.BLACK);
			lblWitch.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
			lblWitch.setBackground(Color.WHITE);
			pnlContourID.add(lblWitch);
		}
		
		
		
		
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
	@Override
	public void accuser() {
		
		
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
			
			
			
			if (Joueur.getIdentiteAssociee().getDevoilee() == false && Joueur.isAccusable() == true && Joueur != Jeu.getInstance().getEnTour()) {
				
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
				
				
			}
			
			compteur++;
			
		});
		LayeredPaneEnsemble.moveToFront(LayeredPaneAccuser);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		
		
	}
	@Override
	public void repondreAccusation(int joueur) {
		
		//METHODE QUI ACTUALISE LES VALEURS DU JEU
		
		
		
		LayeredPaneAccuser.setVisible(false);
		
		LayeredPaneChoixTour.removeAll();
		
		lblTitre.setText("Joueur \"" + Jeu.getInstance().getJoueur(joueur).getPseudo() + "\", joueur \"" + Jeu.getInstance().getEnTour().getPseudo() + "\" vous accuse !");
		
		
		
		LayeredPaneChoixTour = new JLayeredPane();
		LayeredPaneChoixTour.setBackground(UIManager.getColor("Button.background"));
		LayeredPaneChoixTour.setBounds(0, 0, 1280, 1080);
		LayeredPaneEnsemble.add(LayeredPaneChoixTour);
		
		
		
		panelID = new JPanel();
		panelID.setBounds(0, 0, 388, 149);
		LayeredPaneChoixTour.add(panelID);
		
		pnlTitreID = new JPanel();
		FlowLayout fl_pnlTitreID = (FlowLayout) pnlTitreID.getLayout();
		fl_pnlTitreID.setHgap(50);
		panelID.add(pnlTitreID);
		
		lblTitreID = new JLabel("Votre identit\u00E9 :");
		lblTitreID.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		pnlTitreID.add(lblTitreID);
		
		if (Jeu.getInstance().getAccused().getIdentiteAssociee().getIsWitch() == true) {
			
			pnlContourID = new JPanel();
			pnlContourID.setForeground(Color.WHITE);
			pnlContourID.setBackground(Color.BLACK);
			FlowLayout flowLayout_1 = (FlowLayout) pnlContourID.getLayout();
			flowLayout_1.setVgap(20);
			flowLayout_1.setHgap(50);
			panelID.add(pnlContourID);
			
			lblWitch = new JLabel("WITCH");
			lblWitch.setForeground(Color.WHITE);
			lblWitch.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
			lblWitch.setBackground(Color.BLACK);
			pnlContourID.add(lblWitch);
		}
		else {
			
			pnlContourID = new JPanel();
			pnlContourID.setForeground(Color.WHITE);
			pnlContourID.setBackground(Color.WHITE);
			FlowLayout flowLayout_1 = (FlowLayout) pnlContourID.getLayout();
			flowLayout_1.setVgap(20);
			flowLayout_1.setHgap(50);
			panelID.add(pnlContourID);
			
			lblWitch = new JLabel("VILLAGER");
			lblWitch.setForeground(Color.BLACK);
			lblWitch.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
			lblWitch.setBackground(Color.WHITE);
			pnlContourID.add(lblWitch);
		}
		
		
		
		
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

		Jeu.getInstance().getAccused().getCarteEnMain().forEach(carte -> {		
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
		
		lblTitre = new JLabel("Joueur \"" + Jeu.getInstance().getJoueur(joueur).getPseudo() + "\", joueur \"" + Jeu.getInstance().getEnTour().getPseudo() + "\" vous accuse !");
		lblTitre.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panelTitre.add(lblTitre);
		
		
		btnJouerCarte = new JButton("Jouer une carte");
		btnJouerCarte.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnJouerCarte.setBounds(260, 159, 323, 80);
		LayeredPaneChoixTour.add(btnJouerCarte);
		
		btnAccuser = new JButton("R�v�ler votre identit�");
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
		
		Jeu.getInstance().getAccused().getCarteRevelees().forEach(carte -> {		
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
		
		LayeredPaneEnsemble.moveToFront(LayeredPaneChoixTour);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		Jeu.getInstance().getControler().setInputsAccused(btnJouerCarte, btnAccuser);
	}
	@Override
	public void reveler() {
		Jeu.getInstance().getAccused().revelerIdentite();
		
		
		layeredPaneReveler = new JLayeredPane();
		layeredPaneReveler.setBounds(0, 0, 1280, 1080);
		LayeredPaneEnsemble.add(layeredPaneReveler);
		
		LayeredPaneEnsemble.moveToBack(layeredPaneReveler);
		
		panelTitreReveler = new JPanel();
		panelTitreReveler.setBounds(0, 35, 1266, 50);
		layeredPaneReveler.add(panelTitreReveler);
		
		btnTourSuivant = new JButton("Passer � la suite");
		btnTourSuivant.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnTourSuivant.setBounds(470, 600, 323, 80);
		layeredPaneReveler.add(btnTourSuivant);
		
		
		
		if (Jeu.getInstance().getAccused().getIdentiteAssociee().getIsWitch()==true) {
			lblTitreReveler = new JLabel("Joueur \"" + Jeu.getInstance().getAccused().getPseudo() + "\", vous �tiez une Witch !");
			lblTitreReveler.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
			lblTitreReveler.setHorizontalAlignment(SwingConstants.CENTER);
			panelTitreReveler.add(lblTitreReveler);
			
			lblDescriptifReveler = new JLabel("Joueur \"" + Jeu.getInstance().getEnTour().getPseudo() + "\", vous gagnez un point et prenez le prochain tour !");
			lblDescriptifReveler.setHorizontalAlignment(SwingConstants.CENTER);
			lblDescriptifReveler.setForeground(SystemColor.controlDkShadow);
			lblDescriptifReveler.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
			lblDescriptifReveler.setBounds(0, 300, 1266, 21);
			layeredPaneReveler.add(lblDescriptifReveler);
		}
		else {
			lblTitreReveler = new JLabel("Joueur \"" + Jeu.getInstance().getAccused().getPseudo() + "\", vous prenez le prochain tour !");
			lblTitreReveler.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
			lblTitreReveler.setHorizontalAlignment(SwingConstants.CENTER);
			panelTitreReveler.add(lblTitreReveler);
			
			lblDescriptifReveler = new JLabel("Que voulez vous faire ?");
			lblDescriptifReveler.setHorizontalAlignment(SwingConstants.CENTER);
			lblDescriptifReveler.setForeground(SystemColor.controlDkShadow);
			lblDescriptifReveler.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
			lblDescriptifReveler.setBounds(0, 300, 1266, 21);
			layeredPaneReveler.add(lblDescriptifReveler);
		}
		
		this.updateSuivi();
		LayeredPaneEnsemble.moveToFront(layeredPaneReveler);
		LayeredPaneChoixTour.setVisible(false);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		LayeredPaneAccuser.removeAll();
		
		Jeu.getInstance().getControler().setImputNextTurn(btnTourSuivant);
	}
	
	
	public void updateSuivi() {
		LayeredPaneSuivi.removeAll();
		
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
		
		
		
		
		
		
		LayeredPaneSuivi = new JLayeredPane();
		LayeredPaneSuivi.setBackground(UIManager.getColor("Button.background"));
		LayeredPaneSuivi.setBounds(868, 0, 402, 158);
		LayeredPaneEnsemble.add(LayeredPaneSuivi);
		
		panelSuiviPartie = new JPanel();
		panelSuiviPartie.setBackground(new Color(0,0,0,0));
		panelSuiviPartie.setBounds(-18, 0, 468, 157);
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
		table.getColumnModel().getColumn(0).setPreferredWidth(105);
		table.getColumnModel().getColumn(1).setPreferredWidth(105);
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		panelSuiviPartie.add(table);
	}
	
	public void passerTourSuivant() {
		this.frame.setVisible(false);
		
		
		
		if (Jeu.getInstance().isRoundEnd()) {
			InterfaceFinRound finRound = new InterfaceFinRound(Jeu.getInstance().getGagnantRound());
		}
		else {
			Jeu.getInstance().setVueActuelle(new InterfaceChoixTour());
		}
		
	}
}
