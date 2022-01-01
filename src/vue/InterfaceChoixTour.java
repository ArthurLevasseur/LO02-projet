package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JTextPane;

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
import java.util.concurrent.TimeUnit;
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
	private JLayeredPane LayeredPaneChoixNext;
	private JLayeredPane layeredPaneCarteWitch;
	private JLayeredPane layeredPaneCarteHunt;
	private JLayeredPane LayeredPaneRecap;
	private JLayeredPane LayeredPaneChoixDuckingStool;
	
	private boolean accusable;
	private int compteur;
	private int compteurAccusable;
	private int carteJouable;
	
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
	private JLabel lblSecret;
	private JPanel panelChoixNext;
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public InterfaceChoixTour() {
		Jeu.getInstance().setVueActuelle(this);
		Jeu.getInstance().addObserver(this);
		
		if (Jeu.getInstance().getEnTour().isIA()) {
			Jeu.getInstance().getEnTour().jouerTour();
		}
		else {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						initialize();
					}
						
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void isAccusedIA(int joueurAccused) {
		Jeu.getInstance().getVueActuelle().repondreAccusation(joueurAccused);
		if (Jeu.getInstance().getJoueur(joueurAccused).isIA()) {
			
			Jeu.getInstance().getJoueur(joueurAccused).repondreAccusation();
		}
		
	}
	
	public void initialize() {
		
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
					infoJoueur[compteur][1] = "WITCH";
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
			flowLayout_1.setHgap(30);
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
			flowLayout_1.setHgap(30);
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
		
		
		
		
		
		LayeredPaneEnsemble.moveToFront(LayeredPaneChoixTour);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		Jeu.getInstance().getControler().setInputsTour(this.btnAccuser,this.btnJouerCarte);
		
		
		
		
		
		
		
		
		
		this.frame.setVisible(true);
	}
	
	@Override
	public void update(Observable o, Object arg) {

	}
	
	@Override
	public void debutTour() {
	}
	@Override
	public void accuser() {
		
		
		this.btnAccuser.setVisible(false);
		this.btnJouerCarte.setVisible(false);
		
		
		LayeredPaneAccuser = new JLayeredPane();
		LayeredPaneAccuser.setBounds(0, 0, 1280, 1080);
		LayeredPaneAccuser.setBackground(UIManager.getColor("Button.background"));
		LayeredPaneEnsemble.add(LayeredPaneAccuser);
		
		JButton btnAnnuler = new JButton("<");
		btnAnnuler.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		btnAnnuler.setBackground(new Color(255,255,255));
		btnAnnuler.setBounds(10, 10, 60, 60);
		LayeredPaneAccuser.add(btnAnnuler);
		
		JPanel panelChoixAccuse = new JPanel();
		panelChoixAccuse.setBackground(UIManager.getColor("Button.background"));
		panelChoixAccuse.setBounds(0, 0, 1280, 1080);
		LayeredPaneAccuser.add(panelChoixAccuse);
		
		JPanel pnlTitreAccuse = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlTitreAccuse.getLayout();
		flowLayout_2.setHgap(400);
		pnlTitreAccuse.setBackground(new Color(0, 0, 0, 0));
		panelChoixAccuse.add(pnlTitreAccuse);
		
		JLabel lblQuiVoulezvousAccuser = new JLabel("Qui voulez-vous accuser ?");
		lblQuiVoulezvousAccuser.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		pnlTitreAccuse.add(lblQuiVoulezvousAccuser);
		

		compteur = 0;
		compteurAccusable = 0;
		Jeu.getInstance().getEnsembleJoueurs().forEach(Joueur -> {	
			
			
			
			if (Joueur.getIdentiteAssociee().getDevoilee() == false && Joueur.isAccusable() == true && Joueur != Jeu.getInstance().getEnTour()) {
				
				compteurAccusable++;
				
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
		
		if (compteurAccusable == 0) {
			carteJouable = 0;
			Jeu.getInstance().getEnTour().getCarteEnMain().forEach(card -> {		
				try {
					if (!(((card.getNumCarte()==1 || card.getNumCarte()==2) && (Jeu.getInstance().getEnTour().getIdentiteAssociee().getIsWitch() == true || Jeu.getInstance().getEnTour().getIdentiteAssociee().getDevoilee() == false)) || (card.getNumCarte() == 3 && Jeu.getInstance().getEnTour().getCarteRevelees().isEmpty()))) {
						carteJouable++;
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			});
			if (carteJouable == 0) {
				
				Jeu.getInstance().getEnsembleJoueurs().forEach(Joueur -> {	
					if (Joueur.isAccusable() == false) {
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
				});
			}
		}
		
		
		Jeu.getInstance().getControler().setInputBackAccuse(btnAnnuler);
		
		LayeredPaneEnsemble.moveToFront(LayeredPaneAccuser);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		LayeredPaneChoixTour.setVisible(false);
		
		
	}
	@Override
	public void repondreAccusation(int joueur) {
		
		
		
		LayeredPaneAccuser.removeAll();

		LayeredPaneChoixTour.removeAll();
		
		lblTitre.setText("\"" + Jeu.getInstance().getJoueur(joueur).getPseudo() + "\", \"" + Jeu.getInstance().getEnTour().getPseudo() + "\" vous accuse !");
		
		
		
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
			flowLayout_1.setHgap(30);
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
			flowLayout_1.setHgap(30);
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
		
		lblTitre = new JLabel("\"" + Jeu.getInstance().getJoueur(joueur).getPseudo() + "\", \"" + Jeu.getInstance().getEnTour().getPseudo() + "\" vous accuse !");
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
			lblTitreReveler = new JLabel("Joueur \"" + Jeu.getInstance().getAccused().getPseudo() + "\", vous �tiez un Villager !");
			lblTitreReveler.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
			lblTitreReveler.setHorizontalAlignment(SwingConstants.CENTER);
			panelTitreReveler.add(lblTitreReveler);
			
			lblDescriptifReveler = new JLabel("Joueur \"" + Jeu.getInstance().getAccused().getPseudo() + "\", vous prenez le prochain tour !");
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
					infoJoueur[compteur][1] = "WITCH";
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
		Jeu.getInstance().getEnsembleJoueurs().forEach(Joueur ->{
			Joueur.setAccusable(true);
		});
		
		
		if (Jeu.getInstance().getRound().isRoundEnd()) {
			
			Jeu.getInstance().getGagnantRound().gagnerPoints();
			
			InterfaceFinRound finRound = new InterfaceFinRound(Jeu.getInstance().getGagnantRound());
			
		}
		else {
			Jeu.getInstance().getRound().jouerUnTour();
		}
		
	}
	
	public void choisirHunt() {
		
		layeredPaneCarteHunt = new JLayeredPane();
		layeredPaneCarteHunt.setBounds(0, 0, 1280, 1080);
		LayeredPaneEnsemble.add(layeredPaneCarteHunt);
		
		JPanel panelTitreCarteHunt = new JPanel();
		panelTitreCarteHunt.setBounds(0, 35, 1266, 50);
		layeredPaneCarteHunt.add(panelTitreCarteHunt);
		
		JLabel lblTitreCarteHunt = new JLabel("Quelle carte voulez-vous jouer ?");
		lblTitreCarteHunt.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panelTitreCarteHunt.add(lblTitreCarteHunt);
		
		Panel panelCartesHunt = new Panel();
		panelCartesHunt.setBackground(SystemColor.menu);
		panelCartesHunt.setBounds(21, 251, 1224, 294);
		layeredPaneCarteHunt.add(panelCartesHunt);
		
		JPanel pnlTitreHunt = new JPanel();
		pnlTitreHunt.setBackground(SystemColor.menu);
		FlowLayout flowLayout = (FlowLayout) pnlTitreHunt.getLayout();
		flowLayout.setHgap(500);
		panelCartesHunt.add(pnlTitreHunt);
		
		JLabel pnlCartesHunt = new JLabel("Cartes en main :");
		pnlCartesHunt.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		pnlTitreHunt.add(pnlCartesHunt);
		
		compteur = 0;
		
		Jeu.getInstance().getEnTour().getCarteEnMain().forEach(card -> {		
			try {
				if (!(((card.getNumCarte()==1 || card.getNumCarte()==2) && (Jeu.getInstance().getEnTour().getIdentiteAssociee().getIsWitch() == true || Jeu.getInstance().getEnTour().getIdentiteAssociee().getDevoilee() == false)) || (card.getNumCarte() == 3 && Jeu.getInstance().getEnTour().getCarteRevelees().isEmpty()))) {
					
					BufferedImage myPicture = ImageIO.read(new File("Carte" + card.getNumCarte() + ".png"));
					JButton picLabel = new JButton(new ImageIcon(myPicture));
					picLabel.setBackground(new Color(255,255,255));
					picLabel.setSize(250, 735);
					panelCartesHunt.add(picLabel);
					Jeu.getInstance().getControler().setInputCarteEffet(true,picLabel,card,compteur);
					
				}
				
				else {
					BufferedImage myPicture = ImageIO.read(new File("Carte" + card.getNumCarte() + "grey.png"));
					JLabel picLabel = new JLabel(new ImageIcon(myPicture));
					picLabel.setSize(250, 735);
					panelCartesHunt.add(picLabel);
				}
				compteur++;
				
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		
		
		
		Panel panelCartesReveleesHunt = new Panel();
		panelCartesReveleesHunt.setBackground(SystemColor.menu);
		panelCartesReveleesHunt.setBounds(21, 543, 1224, 302);
		layeredPaneCarteHunt.add(panelCartesReveleesHunt);
		
		JPanel pnlTitreReveleesHunt = new JPanel();
		pnlTitreReveleesHunt.setBackground(SystemColor.menu);
		FlowLayout flowLayout2 = (FlowLayout) pnlTitreReveleesHunt.getLayout();
		flowLayout2.setHgap(500);
		panelCartesReveleesHunt.add(pnlTitreReveleesHunt);
		
		JLabel pnlReveleesHunt = new JLabel("Cartes r�v�l�es :");
		pnlReveleesHunt.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		pnlTitreReveleesHunt.add(pnlReveleesHunt);
			
		Jeu.getInstance().getEnTour().getCarteRevelees().forEach(carte -> {		
			try {
				BufferedImage myPicture = ImageIO.read(new File("Carte" + carte.getNumCarte() + ".png"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				picLabel.setSize(250, 735);
				panelCartesReveleesHunt.add(picLabel);
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		JButton btnAnnuler = new JButton("<");
		btnAnnuler.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		btnAnnuler.setBackground(new Color(255,255,255));
		btnAnnuler.setBounds(10, 10, 60, 60);
		layeredPaneCarteHunt.add(btnAnnuler);
		
		Jeu.getInstance().getControler().setInputBackHunt(btnAnnuler);
		
		LayeredPaneEnsemble.moveToFront(layeredPaneCarteHunt);
		layeredPaneCarteHunt.setVisible(true);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		LayeredPaneChoixTour.setVisible(false);
	}
	
	public void choisirWitch() {
		
		layeredPaneCarteWitch = new JLayeredPane();
		layeredPaneCarteWitch.setBounds(0, 0, 1280, 1080);
		LayeredPaneEnsemble.add(layeredPaneCarteWitch);
		
		JPanel panelTitreCarteWitch = new JPanel();
		panelTitreCarteWitch.setBounds(0, 35, 1266, 50);
		layeredPaneCarteWitch.add(panelTitreCarteWitch);
		
		JLabel lblTitreCarteWitch = new JLabel("Quelle carte voulez-vous jouer ?");
		lblTitreCarteWitch.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panelTitreCarteWitch.add(lblTitreCarteWitch);
		
		Panel panelCartesWitch = new Panel();
		panelCartesWitch.setBackground(SystemColor.menu);
		panelCartesWitch.setBounds(21, 251, 1224, 294);
		layeredPaneCarteWitch.add(panelCartesWitch);
		
		JPanel pnlTitreWitch = new JPanel();
		pnlTitreWitch.setBackground(SystemColor.menu);
		FlowLayout flowLayout = (FlowLayout) pnlTitreWitch.getLayout();
		flowLayout.setHgap(500);
		panelCartesWitch.add(pnlTitreWitch);
		
		JLabel pnlCartesWitch = new JLabel("Cartes en main :");
		pnlCartesWitch.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		pnlTitreWitch.add(pnlCartesWitch);
		
		compteur = 0;
		
		Jeu.getInstance().getAccused().getCarteEnMain().forEach(card -> {		
			try {
				if (!(card.getNumCarte() == 3 && Jeu.getInstance().getAccused().getCarteRevelees().isEmpty())) {
					BufferedImage myPicture = ImageIO.read(new File("Carte" + card.getNumCarte() + ".png"));
					JButton picLabel = new JButton(new ImageIcon(myPicture));
					picLabel.setBackground(new Color(255,255,255));
					picLabel.setSize(250, 735);
					panelCartesWitch.add(picLabel);
					Jeu.getInstance().getControler().setInputCarteEffet(false,picLabel,card,compteur);
					
				}
				
				else {
					BufferedImage myPicture = ImageIO.read(new File("Carte" + card.getNumCarte() + "grey.png"));
					JLabel picLabel = new JLabel(new ImageIcon(myPicture));
					picLabel.setSize(250, 735);
					panelCartesWitch.add(picLabel);
				}
				compteur++;
				
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		
		
		
		Panel panelCartesReveleesWitch = new Panel();
		panelCartesReveleesWitch.setBackground(SystemColor.menu);
		panelCartesReveleesWitch.setBounds(21, 543, 1224, 302);
		layeredPaneCarteWitch.add(panelCartesReveleesWitch);
		
		JPanel pnlTitreReveleesWitch = new JPanel();
		pnlTitreReveleesWitch.setBackground(SystemColor.menu);
		FlowLayout flowLayout2 = (FlowLayout) pnlTitreReveleesWitch.getLayout();
		flowLayout2.setHgap(500);
		panelCartesReveleesWitch.add(pnlTitreReveleesWitch);
		
		JLabel pnlReveleesWitch = new JLabel("Cartes r�v�l�es :");
		pnlReveleesWitch.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		pnlTitreReveleesWitch.add(pnlReveleesWitch);
			
		Jeu.getInstance().getAccused().getCarteRevelees().forEach(carte -> {		
			try {
				BufferedImage myPicture = ImageIO.read(new File("Carte" + carte.getNumCarte() + ".png"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				picLabel.setSize(250, 735);
				panelCartesReveleesWitch.add(picLabel);
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		JButton btnAnnuler = new JButton("<");
		btnAnnuler.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		btnAnnuler.setBackground(new Color(255,255,255));
		btnAnnuler.setBounds(10, 10, 60, 60);
		layeredPaneCarteWitch.add(btnAnnuler);
		
		Jeu.getInstance().getControler().setInputBackWitch(btnAnnuler);
		
		LayeredPaneEnsemble.moveToFront(layeredPaneCarteWitch);
		layeredPaneCarteWitch.setVisible(true);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		LayeredPaneChoixTour.setVisible(false);
	}

	public void setBtnAnnulerWitch() {
		LayeredPaneChoixTour.setVisible(true);
		LayeredPaneEnsemble.moveToFront(LayeredPaneChoixTour);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
	}
	
	public void setBtnAnnulerHunt() {
		LayeredPaneChoixTour.setVisible(true);
		LayeredPaneEnsemble.moveToFront(LayeredPaneChoixTour);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
	}
	
	public void setBtnAnnulerAccuse() {
		LayeredPaneAccuser.removeAll();
		LayeredPaneChoixTour.setVisible(true);
		LayeredPaneEnsemble.moveToFront(LayeredPaneChoixTour);
		btnAccuser.setVisible(true);
		btnJouerCarte.setVisible(true);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
	}
	
	public void prendreProchainTour(Effet effet) {
		Jeu.getInstance().setEnTour(Jeu.getInstance().getAccused());
		this.passerTourSuivant();
	}
	
	public void angryMobHunt(Effet effet) {
			
			this.btnAccuser.setVisible(false);
			this.btnJouerCarte.setVisible(false);
			
			
			LayeredPaneChoixNext = new JLayeredPane();
			LayeredPaneChoixNext.setBounds(0, 0, 1280, 1080);
			LayeredPaneChoixNext.setBackground(UIManager.getColor("Button.background"));
			LayeredPaneEnsemble.add(LayeredPaneChoixNext);
			
			JPanel panelChoixNext = new JPanel();
			panelChoixNext.setBackground(UIManager.getColor("Button.background"));
			panelChoixNext.setBounds(0, 0, 1280, 1080);
			LayeredPaneChoixNext.add(panelChoixNext);
			
			JPanel pnlTitreChoixNext = new JPanel();
			FlowLayout flowLayout_2 = (FlowLayout) pnlTitreChoixNext.getLayout();
			flowLayout_2.setHgap(400);
			pnlTitreChoixNext.setBackground(new Color(0, 0, 0, 0));
			panelChoixNext.add(pnlTitreChoixNext);
			
			JLabel lblChoixNext = new JLabel("Qui voulez-vous accuser ?");
			lblChoixNext.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
			pnlTitreChoixNext.add(lblChoixNext);
			

			compteur = 0;
			Jeu.getInstance().getEnsembleJoueurs().forEach(Joueur -> {	
				
				accusable = true;
				
				Joueur.getCarteRevelees().forEach(Card -> {
					if (Card.getNumCarte() == 5) {
						accusable = false;
					}
				});
				
				if (Joueur.getIdentiteAssociee().getDevoilee() == false && Joueur.isAccusable() == true && Joueur != Jeu.getInstance().getEnTour() && accusable == true) {
					
					JPanel pnlbouton = new JPanel();
					FlowLayout flowLayout = (FlowLayout) pnlbouton.getLayout();
					flowLayout.setHgap(400);
					pnlbouton.setBackground(new Color(0, 0, 0, 0));
					
					JButton btnJoueur = new JButton("Joueur : " + Joueur.getPseudo() + " | Points : " + Joueur.getPoints());
					btnJoueur.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
					btnJoueur.setBounds(500, 159, 323, 80);
					Jeu.getInstance().getControler().setInputAccusePlayerHunt(btnJoueur, compteur);
					
					pnlbouton.add(btnJoueur);
					
					panelChoixNext.add(pnlbouton);
					
					
				}
				
				compteur++;
				
			});
			
			LayeredPaneChoixNext.setVisible(true);
			LayeredPaneChoixTour.setVisible(false);
			layeredPaneCarteHunt.removeAll();
			layeredPaneCarteHunt.setVisible(false);
			LayeredPaneEnsemble.moveToFront(LayeredPaneChoixNext);
			LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
	}
	
	public void revelerHunt(int Joueur) {
		
		Jeu.getInstance().getJoueur(Joueur).getIdentiteAssociee().ReveleIdentite();
		
		if (!(LayeredPaneChoixDuckingStool == null)) {
			LayeredPaneChoixDuckingStool.removeAll();
			LayeredPaneChoixDuckingStool.setVisible(false);
		}
		
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
		
		
		
		if (Jeu.getInstance().getJoueur(Joueur).getIdentiteAssociee().getIsWitch() == true) {
			lblTitreReveler = new JLabel("Joueur \"" + Jeu.getInstance().getJoueur(Joueur).getPseudo() + "\" �tait une witch, bravo !");
			lblTitreReveler.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
			lblTitreReveler.setBounds(0, 35, 1266, 31);
			lblTitreReveler.setHorizontalAlignment(SwingConstants.CENTER);
			panelTitreReveler.add(lblTitreReveler);
			
			lblDescriptifReveler = new JLabel("Vous prenez le prochain tour !");
			lblDescriptifReveler.setHorizontalAlignment(SwingConstants.CENTER);
			lblDescriptifReveler.setForeground(SystemColor.controlDkShadow);
			lblDescriptifReveler.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
			lblDescriptifReveler.setBounds(0, 300, 1266, 21);
			layeredPaneReveler.add(lblDescriptifReveler);
		}
		else {
			lblTitreReveler = new JLabel("Joueur \"" + Jeu.getInstance().getJoueur(Joueur).getPseudo() + "\" �tait un Villager, dommage !");
			lblTitreReveler.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
			lblTitreReveler.setHorizontalAlignment(SwingConstants.CENTER);
			panelTitreReveler.add(lblTitreReveler);
			
			lblDescriptifReveler = new JLabel("Il prend le prochain tour !");
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
		if (LayeredPaneChoixNext != null) {
			LayeredPaneChoixNext.removeAll();
			LayeredPaneChoixNext.setVisible(false);
		}
		
		if (!(Jeu.getInstance().getJoueur(Joueur).getIdentiteAssociee().getIsWitch())) {
			Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(Joueur));
		}
		
		Jeu.getInstance().getControler().setImputNextTurn(btnTourSuivant);
	}
	
	public void choisirProchainJoueur(Effet effet) {
		
		this.btnAccuser.setVisible(false);
		this.btnJouerCarte.setVisible(false);
		
		
		
		LayeredPaneChoixNext = new JLayeredPane();
		LayeredPaneChoixNext.setBounds(0, 0, 1280, 1080);
		LayeredPaneChoixNext.setBackground(UIManager.getColor("Button.background"));
		LayeredPaneEnsemble.add(LayeredPaneChoixNext);
		
		panelChoixNext = new JPanel();
		panelChoixNext.setBackground(UIManager.getColor("Button.background"));
		panelChoixNext.setBounds(0, 0, 1280, 1080);
		LayeredPaneChoixNext.add(panelChoixNext);
		
		JPanel pnlTitreNext = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlTitreNext.getLayout();
		flowLayout_2.setHgap(400);
		pnlTitreNext.setBackground(new Color(0, 0, 0, 0));
		panelChoixNext.add(pnlTitreNext);
		
		JLabel lblNext = new JLabel("Choisissez le prochain joueur ?");
		lblNext.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		pnlTitreNext.add(lblNext);
		

		compteur = 0;
		Jeu.getInstance().getEnsembleJoueurs().forEach(Joueur -> {	
			
			
			
			if (((Joueur.getIdentiteAssociee().getDevoilee() == true && Joueur.getIdentiteAssociee().getIsWitch() == false) || Joueur.getIdentiteAssociee().getDevoilee() == false) && Joueur != Jeu.getInstance().getEnTour()) {
				
				JPanel pnlbouton = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlbouton.getLayout();
				flowLayout.setHgap(400);
				pnlbouton.setBackground(new Color(0, 0, 0, 0));
				
				JButton btnJoueur = new JButton("Joueur : " + Joueur.getPseudo() + " | Points : " + Joueur.getPoints());
				btnJoueur.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
				btnJoueur.setBounds(500, 159, 323, 80);
				Jeu.getInstance().getControler().setInputNextPlayer(btnJoueur, compteur);
				
				pnlbouton.add(btnJoueur);
				
				panelChoixNext.add(pnlbouton);
				
				
			}
			
			compteur++;
			
		});
		
		
		
		LayeredPaneChoixNext.setVisible(true);
		LayeredPaneChoixTour.setVisible(false);
		layeredPaneCarteHunt.removeAll();
		layeredPaneCarteHunt.setVisible(false);
		LayeredPaneEnsemble.moveToFront(LayeredPaneChoixNext);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
	}
	
	
	public void theInquisitionHunt(Effet effet) {
		
		this.btnAccuser.setVisible(false);
		this.btnJouerCarte.setVisible(false);
		
		
		
		LayeredPaneChoixNext = new JLayeredPane();
		LayeredPaneChoixNext.setBounds(0, 0, 1280, 1080);
		LayeredPaneChoixNext.setBackground(UIManager.getColor("Button.background"));
		LayeredPaneEnsemble.add(LayeredPaneChoixNext);
		
		panelChoixNext = new JPanel();
		panelChoixNext.setBackground(UIManager.getColor("Button.background"));
		panelChoixNext.setBounds(0, 0, 1280, 1080);
		LayeredPaneChoixNext.add(panelChoixNext);
		
		JPanel pnlTitreNext = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlTitreNext.getLayout();
		flowLayout_2.setHgap(400);
		pnlTitreNext.setBackground(new Color(0, 0, 0, 0));
		panelChoixNext.add(pnlTitreNext);
		
		JLabel lblNext = new JLabel("Choisissez le prochain joueur ?");
		lblNext.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		pnlTitreNext.add(lblNext);
		

		compteur = 0;
		Jeu.getInstance().getEnsembleJoueurs().forEach(Joueur -> {	
			
			
			
			if (((Joueur.getIdentiteAssociee().getDevoilee() == true && Joueur.getIdentiteAssociee().getIsWitch() == false) || Joueur.getIdentiteAssociee().getDevoilee() == false) && Joueur != Jeu.getInstance().getEnTour()) {
				
				JPanel pnlbouton = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlbouton.getLayout();
				flowLayout.setHgap(400);
				pnlbouton.setBackground(new Color(0, 0, 0, 0));
				
				JButton btnJoueur = new JButton("Joueur : " + Joueur.getPseudo() + " | Points : " + Joueur.getPoints());
				btnJoueur.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
				btnJoueur.setBounds(500, 159, 323, 80);
				Jeu.getInstance().getControler().setInputNextPlayerSecretly(btnJoueur, compteur);
				
				pnlbouton.add(btnJoueur);
				
				panelChoixNext.add(pnlbouton);
				
				
			}
			
			compteur++;
			
		});
		
		
		
		LayeredPaneChoixNext.setVisible(true);
		LayeredPaneChoixTour.setVisible(false);
		layeredPaneCarteHunt.removeAll();
		layeredPaneCarteHunt.setVisible(false);
		LayeredPaneEnsemble.moveToFront(LayeredPaneChoixNext);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
	}
	
	public void setTextSecret(String text) {
		
		panelChoixNext.removeAll();
		
		JButton btnNext = new JButton(text);
		btnNext.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnNext.setBounds(500, 400, 323, 80);
		Jeu.getInstance().getControler().setImputNextTurn(btnNext);
		panelChoixNext.add(btnNext);
		LayeredPaneChoixNext.moveToFront(btnNext);

	}
	
	public void discard(Effet effet) {
		
		if (!(layeredPaneCarteWitch == null)) {
			layeredPaneCarteWitch.removeAll();
			layeredPaneCarteWitch.setVisible(false);
		}
		if (!(LayeredPaneChoixDuckingStool == null)) {
			LayeredPaneChoixDuckingStool.removeAll();
			LayeredPaneChoixDuckingStool.setVisible(false);
		}
		
		layeredPaneCarteWitch = new JLayeredPane();
		layeredPaneCarteWitch.setBounds(0, 0, 1280, 1080);
		LayeredPaneEnsemble.add(layeredPaneCarteWitch);
		
		JPanel panelTitreCarteWitch = new JPanel();
		panelTitreCarteWitch.setBounds(0, 35, 1266, 50);
		layeredPaneCarteWitch.add(panelTitreCarteWitch);
		
		JLabel lblTitreCarteWitch = new JLabel("Quelle carte voulez-vous d�fausser ?");
		lblTitreCarteWitch.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panelTitreCarteWitch.add(lblTitreCarteWitch);
		
		Panel panelCartesWitch = new Panel();
		panelCartesWitch.setBackground(SystemColor.menu);
		panelCartesWitch.setBounds(21, 251, 1224, 294);
		layeredPaneCarteWitch.add(panelCartesWitch);
		
		JPanel pnlTitreWitch = new JPanel();
		pnlTitreWitch.setBackground(SystemColor.menu);
		FlowLayout flowLayout = (FlowLayout) pnlTitreWitch.getLayout();
		flowLayout.setHgap(500);
		panelCartesWitch.add(pnlTitreWitch);
		
		JLabel pnlCartesWitch = new JLabel("Cartes en main :");
		pnlCartesWitch.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		pnlTitreWitch.add(pnlCartesWitch);
		
		compteur = 0;
		Jeu.getInstance().getAccused().getCarteEnMain().forEach(card -> {		
			try {
					BufferedImage myPicture = ImageIO.read(new File("Carte" + card.getNumCarte() + ".png"));
					JButton picLabel = new JButton(new ImageIcon(myPicture));
					picLabel.setBackground(new Color(255,255,255));
					picLabel.setSize(250, 735);
					panelCartesWitch.add(picLabel);
					Jeu.getInstance().getControler().setInputDiscardCarte(picLabel,compteur);
				
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
			compteur += 1;
		});
		
		if (Jeu.getInstance().getAccused().getCarteEnMain().isEmpty()) {
			JButton btnJoueur = new JButton("Aucunes cartes en main");
			btnJoueur.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
			btnJoueur.setBounds(500, 600, 323, 80);
			Jeu.getInstance().setEnTour(Jeu.getInstance().getAccused());
			Jeu.getInstance().getVueActuelle().passerTourSuivant();
		}
		
		LayeredPaneEnsemble.moveToFront(layeredPaneCarteWitch);
		layeredPaneCarteWitch.setVisible(true);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		layeredPaneCarteWitch.setVisible(true);
	}
	
	public void pointedHat(Effet effet, boolean isHunt) {
		
		if (isHunt) {
			layeredPaneCarteHunt.removeAll();
			layeredPaneCarteHunt.setVisible(false);
			
			layeredPaneCarteHunt = new JLayeredPane();
			layeredPaneCarteHunt.setBounds(0, 0, 1280, 1080);
			LayeredPaneEnsemble.add(layeredPaneCarteHunt);
			
			JPanel panelTitreCarteHunt = new JPanel();
			panelTitreCarteHunt.setBounds(0, 35, 1266, 50);
			layeredPaneCarteHunt.add(panelTitreCarteHunt);
			
			JLabel lblTitreCarteHunt = new JLabel("Quelle carte voulez-vous r�cup�rer ?");
			lblTitreCarteHunt.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
			panelTitreCarteHunt.add(lblTitreCarteHunt);
			
			Panel panelCartesHunt = new Panel();
			panelCartesHunt.setBackground(SystemColor.menu);
			panelCartesHunt.setBounds(21, 251, 1224, 294);
			layeredPaneCarteHunt.add(panelCartesHunt);
			
			JPanel pnlTitreHunt = new JPanel();
			pnlTitreHunt.setBackground(SystemColor.menu);
			FlowLayout flowLayout = (FlowLayout) pnlTitreHunt.getLayout();
			flowLayout.setHgap(500);
			panelCartesHunt.add(pnlTitreHunt);
			
			JLabel pnlCartesHunt = new JLabel("Cartes r�v�l�es :");
			pnlCartesHunt.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
			pnlTitreHunt.add(pnlCartesHunt);
			
			compteur = 0;
			Jeu.getInstance().getEnTour().getCarteRevelees().forEach(card -> {
				if (card.getNumCarte() != 3) {
					try {
						BufferedImage myPicture = ImageIO.read(new File("Carte" + card.getNumCarte() + ".png"));
						JButton picLabel = new JButton(new ImageIcon(myPicture));
						picLabel.setBackground(new Color(255,255,255));
						picLabel.setSize(250, 735);
						panelCartesHunt.add(picLabel);
						Jeu.getInstance().getControler().setInputRecupererRevelee(true,picLabel,compteur);
					}
					
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				compteur += 1;
			});
			
			LayeredPaneEnsemble.moveToFront(layeredPaneCarteHunt);
			layeredPaneCarteHunt.setVisible(true);
			LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
			LayeredPaneSuivi.setVisible(true);
		}
		else {
			layeredPaneCarteWitch.removeAll();
			layeredPaneCarteWitch.setVisible(false);
			
			layeredPaneCarteWitch = new JLayeredPane();
			layeredPaneCarteWitch.setBounds(0, 0, 1280, 1080);
			LayeredPaneEnsemble.add(layeredPaneCarteWitch);
			
			JPanel panelTitreCarteWitch = new JPanel();
			panelTitreCarteWitch.setBounds(0, 35, 1266, 50);
			layeredPaneCarteWitch.add(panelTitreCarteWitch);
			
			JLabel lblTitreCarteWitch = new JLabel("Quelle carte voulez-vous r�cup�rer ?");
			lblTitreCarteWitch.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
			panelTitreCarteWitch.add(lblTitreCarteWitch);
			
			Panel panelCartesWitch = new Panel();
			panelCartesWitch.setBackground(SystemColor.menu);
			panelCartesWitch.setBounds(21, 251, 1224, 294);
			layeredPaneCarteWitch.add(panelCartesWitch);
			
			JPanel pnlTitreWitch = new JPanel();
			pnlTitreWitch.setBackground(SystemColor.menu);
			FlowLayout flowLayout = (FlowLayout) pnlTitreWitch.getLayout();
			flowLayout.setHgap(500);
			panelCartesWitch.add(pnlTitreWitch);
			
			JLabel pnlCartesWitch = new JLabel("Cartes r�v�l�es :");
			pnlCartesWitch.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
			pnlTitreWitch.add(pnlCartesWitch);
			
			compteur = 0;
			Jeu.getInstance().getAccused().getCarteRevelees().forEach(card -> {	
				if (card.getNumCarte() != 3) {
					try {
						BufferedImage myPicture = ImageIO.read(new File("Carte" + card.getNumCarte() + ".png"));
						JButton picLabel = new JButton(new ImageIcon(myPicture));
						picLabel.setBackground(new Color(255,255,255));
						picLabel.setSize(250, 735);
						panelCartesWitch.add(picLabel);
						Jeu.getInstance().getControler().setInputRecupererRevelee(false,picLabel,compteur);	
					}
					
					catch (Exception e) {
						e.printStackTrace();
					}
					compteur += 1;
				}
			});
			
			LayeredPaneEnsemble.moveToFront(layeredPaneCarteWitch);
			layeredPaneCarteWitch.setVisible(true);
			LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
			layeredPaneCarteWitch.setVisible(true);
		}
		
	}
	
	public void hookedNoseWitch(Effet effet) {
		layeredPaneCarteWitch.removeAll();
		layeredPaneCarteWitch.setVisible(false);
		
		layeredPaneCarteWitch = new JLayeredPane();
		layeredPaneCarteWitch.setBounds(0, 0, 1280, 1080);
		LayeredPaneEnsemble.add(layeredPaneCarteWitch);
		
		JPanel panelTitreCarteWitch = new JPanel();
		panelTitreCarteWitch.setBounds(0, 35, 1266, 50);
		layeredPaneCarteWitch.add(panelTitreCarteWitch);
		
		JLabel lblTitreCarteWitch = new JLabel("Quelle carte voulez-vous voler ?");
		lblTitreCarteWitch.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panelTitreCarteWitch.add(lblTitreCarteWitch);
		
		Panel panelCartesWitch = new Panel();
		panelCartesWitch.setBackground(SystemColor.menu);
		panelCartesWitch.setBounds(21, 251, 1224, 294);
		layeredPaneCarteWitch.add(panelCartesWitch);
		
		JPanel pnlTitreWitch = new JPanel();
		pnlTitreWitch.setBackground(SystemColor.menu);
		FlowLayout flowLayout = (FlowLayout) pnlTitreWitch.getLayout();
		flowLayout.setHgap(500);
		panelCartesWitch.add(pnlTitreWitch);
		
		JLabel pnlCartesWitch = new JLabel("Cartes en sa main :");
		pnlCartesWitch.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		pnlTitreWitch.add(pnlCartesWitch);
		
		compteur = 0;
		Jeu.getInstance().getEnTour().getCarteEnMain().forEach(card -> {		
			try {
				BufferedImage myPicture = ImageIO.read(new File("Carte" + card.getNumCarte() + ".png"));
				JButton picLabel = new JButton(new ImageIcon(myPicture));
				picLabel.setBackground(new Color(255,255,255));
				picLabel.setSize(250, 735);
				panelCartesWitch.add(picLabel);
				Jeu.getInstance().getControler().setInputVolerCarte(picLabel,compteur);	
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
			compteur += 1;
		});
		
		if (Jeu.getInstance().getEnTour().getCarteEnMain().isEmpty()) {
			Jeu.getInstance().setEnTour(Jeu.getInstance().getAccused());
			this.passerTourSuivant();
		}
		
		LayeredPaneEnsemble.moveToFront(layeredPaneCarteWitch);
		layeredPaneCarteWitch.setVisible(true);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		layeredPaneCarteWitch.setVisible(true);
	}
	
	
	
	public void hookedNoseHunt(Effet effet) {
		this.btnAccuser.setVisible(false);
		this.btnJouerCarte.setVisible(false);
		
		
		
		LayeredPaneChoixNext = new JLayeredPane();
		LayeredPaneChoixNext.setBounds(0, 0, 1280, 1080);
		LayeredPaneChoixNext.setBackground(UIManager.getColor("Button.background"));
		LayeredPaneEnsemble.add(LayeredPaneChoixNext);
		
		panelChoixNext = new JPanel();
		panelChoixNext.setBackground(UIManager.getColor("Button.background"));
		panelChoixNext.setBounds(0, 0, 1280, 1080);
		LayeredPaneChoixNext.add(panelChoixNext);
		
		JPanel pnlTitreNext = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlTitreNext.getLayout();
		flowLayout_2.setHgap(400);
		pnlTitreNext.setBackground(new Color(0, 0, 0, 0));
		panelChoixNext.add(pnlTitreNext);
		
		JLabel lblNext = new JLabel("Choisissez le prochain joueur ?");
		lblNext.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		pnlTitreNext.add(lblNext);
		

		compteur = 0;
		Jeu.getInstance().getEnsembleJoueurs().forEach(Joueur -> {	
			
			
			
			if (((Joueur.getIdentiteAssociee().getDevoilee() == true && Joueur.getIdentiteAssociee().getIsWitch() == false) || Joueur.getIdentiteAssociee().getDevoilee() == false) && Joueur != Jeu.getInstance().getEnTour()) {
				
				JPanel pnlbouton = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlbouton.getLayout();
				flowLayout.setHgap(400);
				pnlbouton.setBackground(new Color(0, 0, 0, 0));
				
				JButton btnJoueur = new JButton("Joueur : " + Joueur.getPseudo() + " | Points : " + Joueur.getPoints());
				btnJoueur.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
				btnJoueur.setBounds(500, 159, 323, 80);
				Jeu.getInstance().getControler().setInputNextPlayerVoler(btnJoueur, compteur);
				
				pnlbouton.add(btnJoueur);
				
				panelChoixNext.add(pnlbouton);
				
				
			}
			
			compteur++;
			
		});
		
		
		
		LayeredPaneChoixNext.setVisible(true);
		LayeredPaneChoixTour.setVisible(false);
		layeredPaneCarteHunt.removeAll();
		layeredPaneCarteHunt.setVisible(false);
		LayeredPaneEnsemble.moveToFront(LayeredPaneChoixNext);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
	}
	
	public void afficherCarteVolee(CarteRumeur card) {
		panelChoixNext.removeAll();
		
		JButton btnNext = new JButton("Vous lui avez vol� la carte : " + card.getNomCarte());
		btnNext.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnNext.setBounds(325, 400, 678, 80);
		Jeu.getInstance().getControler().setImputNextTurn(btnNext);
		panelChoixNext.add(btnNext);
		LayeredPaneChoixNext.moveToFront(btnNext);
	}
	
	public void choisirProchainJoueurWitch(Effet effet) {
		this.btnAccuser.setVisible(false);
		this.btnJouerCarte.setVisible(false);
		
		
		
		LayeredPaneChoixNext = new JLayeredPane();
		LayeredPaneChoixNext.setBounds(0, 0, 1280, 1080);
		LayeredPaneChoixNext.setBackground(UIManager.getColor("Button.background"));
		LayeredPaneEnsemble.add(LayeredPaneChoixNext);
		
		panelChoixNext = new JPanel();
		panelChoixNext.setBackground(UIManager.getColor("Button.background"));
		panelChoixNext.setBounds(0, 0, 1280, 1080);
		LayeredPaneChoixNext.add(panelChoixNext);
		
		JPanel pnlTitreNext = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlTitreNext.getLayout();
		flowLayout_2.setHgap(400);
		pnlTitreNext.setBackground(new Color(0, 0, 0, 0));
		panelChoixNext.add(pnlTitreNext);
		
		JLabel lblNext = new JLabel("Choisissez le prochain joueur ?");
		lblNext.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		pnlTitreNext.add(lblNext);
		

		compteur = 0;
		Jeu.getInstance().getEnsembleJoueurs().forEach(Joueur -> {	
			
			
			
			if (((Joueur.getIdentiteAssociee().getDevoilee() == true && Joueur.getIdentiteAssociee().getIsWitch() == false) || Joueur.getIdentiteAssociee().getDevoilee() == false) && Joueur != Jeu.getInstance().getAccused()) {
				
				JPanel pnlbouton = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlbouton.getLayout();
				flowLayout.setHgap(400);
				pnlbouton.setBackground(new Color(0, 0, 0, 0));
				
				JButton btnJoueur = new JButton("Joueur : " + Joueur.getPseudo() + " | Points : " + Joueur.getPoints());
				btnJoueur.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
				btnJoueur.setBounds(500, 159, 323, 80);
				Jeu.getInstance().getControler().setInputNextPlayer(btnJoueur, compteur);
				
				pnlbouton.add(btnJoueur);
				
				panelChoixNext.add(pnlbouton);
				
				
			}
			
			compteur++;
			
		});
		
		
		
		LayeredPaneChoixNext.setVisible(true);
		LayeredPaneChoixTour.setVisible(false);
		layeredPaneCarteWitch.removeAll();
		layeredPaneCarteWitch.setVisible(false);
		LayeredPaneEnsemble.moveToFront(LayeredPaneChoixNext);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
	}
	
	public void chaudronHunt(Effet effet) {
		if (!Jeu.getInstance().getEnTour().getIdentiteAssociee().getIsWitch()) {
			Jeu.getInstance().getEnTour().revelerIdentite();
			
			
			LayeredPaneChoixNext = new JLayeredPane();
			LayeredPaneChoixNext.setBounds(0, 0, 1280, 1080);
			LayeredPaneChoixNext.setBackground(UIManager.getColor("Button.background"));
			LayeredPaneEnsemble.add(LayeredPaneChoixNext);
			
			panelChoixNext = new JPanel();
			panelChoixNext.setBackground(UIManager.getColor("Button.background"));
			panelChoixNext.setBounds(0, 0, 1280, 1080);
			LayeredPaneChoixNext.add(panelChoixNext);
			
			JPanel pnlTitreNext = new JPanel();
			FlowLayout flowLayout_2 = (FlowLayout) pnlTitreNext.getLayout();
			flowLayout_2.setHgap(400);
			pnlTitreNext.setBackground(new Color(0, 0, 0, 0));
			panelChoixNext.add(pnlTitreNext);
			
			JLabel lblNext = new JLabel("Vous �tes Villager, choisissez le prochain joueur !");
			lblNext.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
			pnlTitreNext.add(lblNext);
			

			compteur = 0;
			Jeu.getInstance().getEnsembleJoueurs().forEach(Joueur -> {	
				
				
				
				if (((Joueur.getIdentiteAssociee().getDevoilee() == true && Joueur.getIdentiteAssociee().getIsWitch() == false) || Joueur.getIdentiteAssociee().getDevoilee() == false) && Joueur != Jeu.getInstance().getEnTour()) {
					
					JPanel pnlbouton = new JPanel();
					FlowLayout flowLayout = (FlowLayout) pnlbouton.getLayout();
					flowLayout.setHgap(400);
					pnlbouton.setBackground(new Color(0, 0, 0, 0));
					
					JButton btnJoueur = new JButton("Joueur : " + Joueur.getPseudo() + " | Points : " + Joueur.getPoints());
					btnJoueur.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
					btnJoueur.setBounds(500, 159, 323, 80);
					Jeu.getInstance().getControler().setInputNextPlayer(btnJoueur, compteur);
					
					pnlbouton.add(btnJoueur);
					
					panelChoixNext.add(pnlbouton);
					
					
				}
				
				compteur++;
				
			});
			
			
			
			LayeredPaneChoixNext.setVisible(true);
			LayeredPaneChoixTour.setVisible(false);
			layeredPaneCarteHunt.removeAll();
			layeredPaneCarteHunt.setVisible(false);
			LayeredPaneEnsemble.moveToFront(LayeredPaneChoixNext);
			LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		}
		else {
			Jeu.getInstance().getEnTour().revelerIdentite();
			
			
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
			
			ArrayList<Joueur> listeJoueurs = new ArrayList<>();
			for (int i=0; i<Jeu.getInstance().getEnsembleJoueurs().size(); i++) {
				if (((Jeu.getInstance().getJoueur(i).getIdentiteAssociee().getDevoilee() == true && Jeu.getInstance().getJoueur(i).getIdentiteAssociee().getIsWitch() == false) || Jeu.getInstance().getJoueur(i).getIdentiteAssociee().getDevoilee() == false) || Jeu.getInstance().getJoueur(i) == Jeu.getInstance().getEnTour()) {
					listeJoueurs.add(Jeu.getInstance().getJoueur(i));
				}
			}
			System.out.println(listeJoueurs.size());
			for (int i=0; i<listeJoueurs.size(); i++) {
				if (listeJoueurs.get(i) == Jeu.getInstance().getEnTour()) {
					if (i == listeJoueurs.size()-1) {
						Jeu.getInstance().setEnTour(listeJoueurs.get(0));
						break;
					}
					else {
						Jeu.getInstance().setEnTour(listeJoueurs.get(i+1));
						break;
					}
				}
			}
			
			lblTitreReveler = new JLabel("Joueur \"" + Jeu.getInstance().getEnTour().getPseudo() + "\", vous �tiez une Witch !");
			lblTitreReveler.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
			lblTitreReveler.setForeground(new Color(0,0,0));
			lblTitreReveler.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitreReveler.setBounds(0, 0, 1266, 21);
			panelTitreReveler.add(lblTitreReveler);
			
			lblDescriptifReveler = new JLabel("Le joueur sur votre gauche (\"" + Jeu.getInstance().getEnTour().getPseudo() + "\") prend le prochain tour !");
			lblDescriptifReveler.setHorizontalAlignment(SwingConstants.CENTER);
			lblDescriptifReveler.setForeground(SystemColor.controlDkShadow);
			lblDescriptifReveler.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
			lblDescriptifReveler.setBounds(0, 300, 1266, 21);
			layeredPaneReveler.add(lblDescriptifReveler);
			
			
			
			

			LayeredPaneEnsemble.moveToFront(layeredPaneReveler);
			LayeredPaneChoixTour.setVisible(false);
			LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
			layeredPaneCarteHunt.removeAll();
			
			Jeu.getInstance().getControler().setImputNextTurn(btnTourSuivant);
		}
	}
	
	public void chaudronWitch(Effet effet) {
		
		LayeredPaneAccuser.removeAll();
		
		int carteDefaussee = (int) (Math.random() * Jeu.getInstance().getEnTour().getCarteEnMain().size());
		
		
		
		LayeredPaneRecap = new JLayeredPane();
		LayeredPaneRecap.setBounds(0, 0, 1280, 1080);
		LayeredPaneRecap.setBackground(UIManager.getColor("Button.background"));
		LayeredPaneEnsemble.add(LayeredPaneRecap);
		
		JPanel panelRecap = new JPanel();
		panelRecap.setBackground(UIManager.getColor("Button.background"));
		panelRecap.setBounds(0, 150, 1280, 100);
		LayeredPaneRecap.add(panelRecap);
		
		JLabel lblTitreRecap = new JLabel("Joueur \"" + Jeu.getInstance().getEnTour().getPseudo() + "\" d�fausse : " + Jeu.getInstance().getEnTour().getCarteEnMain().get(carteDefaussee).getNomCarte());
		lblTitreRecap.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblTitreRecap.setForeground(new Color(0,0,0));
		lblTitreRecap.setBounds(0, 150, 1266, 21);
		lblTitreRecap.setHorizontalAlignment(SwingConstants.CENTER);
		panelRecap.add(lblTitreRecap);
		
		
		
		JPanel pnlDescriptifRecap = new JPanel();
		pnlDescriptifRecap.setBackground(UIManager.getColor("Button.background"));
		pnlDescriptifRecap.setBounds(0, 350, 1280, 100);
		LayeredPaneRecap.add(pnlDescriptifRecap);
		
		JLabel lblRecap = new JLabel("Vous prenez le prochain tour.");
		lblRecap.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		lblRecap.setForeground(SystemColor.controlDkShadow);
		lblRecap.setBounds(0, 350, 1266, 21);
		lblRecap.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDescriptifRecap.add(lblRecap);
		
		JButton btnNext = new JButton("Prochain tour");
		btnNext.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnNext.setBounds(470, 600, 323, 80);
		LayeredPaneRecap.add(btnNext);
		Jeu.getInstance().getControler().setImputNextTurn(btnNext);
		
		
		
		Jeu.getInstance().getRound().seFaireDefausserCard(carteDefaussee, Jeu.getInstance().getEnTour());
		Jeu.getInstance().setEnTour(Jeu.getInstance().getAccused());
		
		LayeredPaneRecap.setVisible(true);
		LayeredPaneAccuser.setVisible(false);
		LayeredPaneChoixTour.setVisible(false);
		layeredPaneCarteWitch.removeAll();
		layeredPaneCarteWitch.setVisible(false);
		LayeredPaneEnsemble.moveToFront(LayeredPaneRecap);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		
	}
	
	public void blackCatHunt(Effet effet) {
		layeredPaneCarteHunt.removeAll();
		layeredPaneCarteHunt.setVisible(false);
		
		layeredPaneCarteHunt = new JLayeredPane();
		layeredPaneCarteHunt.setBounds(0, 0, 1280, 1080);
		LayeredPaneEnsemble.add(layeredPaneCarteHunt);
		
		JPanel panelTitreCarteHunt = new JPanel();
		panelTitreCarteHunt.setBounds(0, 35, 1266, 50);
		layeredPaneCarteHunt.add(panelTitreCarteHunt);
		
		JLabel lblTitreCarteHunt = new JLabel("Quelle carte voulez-vous r�cup�rer ?");
		lblTitreCarteHunt.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panelTitreCarteHunt.add(lblTitreCarteHunt);
		
		Panel panelCartesHunt = new Panel();
		panelCartesHunt.setBackground(SystemColor.menu);
		panelCartesHunt.setBounds(21, 251, 1224, 294);
		layeredPaneCarteHunt.add(panelCartesHunt);
		
		JPanel pnlTitreHunt = new JPanel();
		pnlTitreHunt.setBackground(SystemColor.menu);
		FlowLayout flowLayout = (FlowLayout) pnlTitreHunt.getLayout();
		flowLayout.setHgap(500);
		panelCartesHunt.add(pnlTitreHunt);
		
		JLabel pnlCartesHunt = new JLabel("Cartes defauss�es :");
		pnlCartesHunt.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		pnlTitreHunt.add(pnlCartesHunt);
		
		compteur = 0;
		Jeu.getInstance().getTasDefausse().getContenu().forEach(card -> {
			try {
				BufferedImage myPicture = ImageIO.read(new File("Carte" + card.getNumCarte() + ".png"));
				JButton picLabel = new JButton(new ImageIcon(myPicture));
				picLabel.setBackground(new Color(255,255,255));
				picLabel.setSize(250, 735);
				panelCartesHunt.add(picLabel);
				Jeu.getInstance().getControler().setInputRecupererDefausse(picLabel,compteur);
			}
				
			catch (Exception e) {
				e.printStackTrace();
			}
			compteur += 1;
		});
		
		if (Jeu.getInstance().getTasDefausse().getContenu().isEmpty()) {
			Jeu.getInstance().getTasDefausse().getContenu().add(Jeu.getInstance().getEnTour().getCarteRevelees().get(Jeu.getInstance().getEnTour().getCarteRevelees().size() - 1));
			Jeu.getInstance().getEnTour().getCarteRevelees().remove(Jeu.getInstance().getEnTour().getCarteRevelees().size() - 1);
			
			this.passerTourSuivant();
		}
		
		LayeredPaneEnsemble.moveToFront(layeredPaneCarteHunt);
		layeredPaneCarteHunt.setVisible(true);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		layeredPaneCarteHunt.setVisible(true);
	}
	
	public void petNewtHunt(Effet effet) {
		LayeredPaneChoixNext = new JLayeredPane();
		LayeredPaneChoixNext.setBounds(0, 0, 1280, 1080);
		LayeredPaneChoixNext.setBackground(UIManager.getColor("Button.background"));
		LayeredPaneEnsemble.add(LayeredPaneChoixNext);
		
		panelChoixNext = new JPanel();
		panelChoixNext.setBackground(UIManager.getColor("Button.background"));
		panelChoixNext.setBounds(0, 0, 1280, 1080);
		LayeredPaneChoixNext.add(panelChoixNext);
		
		JPanel pnlTitreNext = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlTitreNext.getLayout();
		flowLayout_2.setHgap(400);
		pnlTitreNext.setBackground(new Color(0, 0, 0, 0));
		panelChoixNext.add(pnlTitreNext);
		
		JLabel lblNext = new JLabel("Choisissez le joueur � voler ?");
		lblNext.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		pnlTitreNext.add(lblNext);
		

		compteur = 0;
		Jeu.getInstance().getEnsembleJoueurs().forEach(Joueur -> {	
			
			
			
			if (Joueur != Jeu.getInstance().getEnTour()) {
				
				JPanel pnlbouton = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlbouton.getLayout();
				flowLayout.setHgap(400);
				pnlbouton.setBackground(new Color(0, 0, 0, 0));
				
				JButton btnJoueur = new JButton("Joueur : " + Joueur.getPseudo() + " | Points : " + Joueur.getPoints());
				btnJoueur.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
				btnJoueur.setBounds(500, 159, 323, 80);
				Jeu.getInstance().getControler().setInputNextPlayerChoixVoler(btnJoueur, compteur);
				
				pnlbouton.add(btnJoueur);
				
				panelChoixNext.add(pnlbouton);
				
				
			}
			
			compteur++;
			
		});

		
		
		LayeredPaneChoixNext.setVisible(true);
		LayeredPaneChoixTour.setVisible(false);
		layeredPaneCarteHunt.removeAll();
		layeredPaneCarteHunt.setVisible(false);
		LayeredPaneEnsemble.moveToFront(LayeredPaneChoixNext);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
	}
	
	public void petNewtChoixCarte(Joueur joueur) {
		
		LayeredPaneChoixNext.setVisible(false);
		LayeredPaneChoixNext.removeAll();
		
		layeredPaneCarteHunt = new JLayeredPane();
		layeredPaneCarteHunt.setBounds(0, 0, 1280, 1080);
		LayeredPaneEnsemble.add(layeredPaneCarteHunt);
		
		JPanel panelTitreCarteHunt = new JPanel();
		panelTitreCarteHunt.setBounds(0, 35, 1266, 50);
		layeredPaneCarteHunt.add(panelTitreCarteHunt);
		
		JLabel lblTitreCarteHunt = new JLabel("Quelle carte voulez-vous voler ?");
		lblTitreCarteHunt.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panelTitreCarteHunt.add(lblTitreCarteHunt);
		
		Panel panelCartesHunt = new Panel();
		panelCartesHunt.setBackground(SystemColor.menu);
		panelCartesHunt.setBounds(21, 251, 1224, 294);
		layeredPaneCarteHunt.add(panelCartesHunt);
		
		JPanel pnlTitreHunt = new JPanel();
		pnlTitreHunt.setBackground(SystemColor.menu);
		FlowLayout flowLayout = (FlowLayout) pnlTitreHunt.getLayout();
		flowLayout.setHgap(500);
		panelCartesHunt.add(pnlTitreHunt);
		
		JLabel pnlCartesHunt = new JLabel("Cartes r�v�l�es :");
		pnlCartesHunt.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		pnlTitreHunt.add(pnlCartesHunt);
		
		compteur = 0;
		joueur.getCarteRevelees().forEach(card -> {
			try {
				BufferedImage myPicture = ImageIO.read(new File("Carte" + card.getNumCarte() + ".png"));
				JButton picLabel = new JButton(new ImageIcon(myPicture));
				picLabel.setBackground(new Color(255,255,255));
				picLabel.setSize(250, 735);
				panelCartesHunt.add(picLabel);
				Jeu.getInstance().getControler().setInputVolerCarteJoueur(picLabel,compteur,joueur);
			}
				
			catch (Exception e) {
				e.printStackTrace();
			}
			compteur += 1;
		});
		
		if (joueur.getCarteRevelees().isEmpty()) {
			
			this.choisirProchainJoueur(null);
		}
		
		LayeredPaneEnsemble.moveToFront(layeredPaneCarteHunt);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		layeredPaneCarteHunt.setVisible(true);
	}
	
	public void duckingStoolHunt(Effet effet) {
		LayeredPaneChoixNext = new JLayeredPane();
		LayeredPaneChoixNext.setBounds(0, 0, 1280, 1080);
		LayeredPaneChoixNext.setBackground(UIManager.getColor("Button.background"));
		LayeredPaneEnsemble.add(LayeredPaneChoixNext);
		
		panelChoixNext = new JPanel();
		panelChoixNext.setBackground(UIManager.getColor("Button.background"));
		panelChoixNext.setBounds(0, 0, 1280, 1080);
		LayeredPaneChoixNext.add(panelChoixNext);
		
		JPanel pnlTitreNext = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlTitreNext.getLayout();
		flowLayout_2.setHgap(400);
		pnlTitreNext.setBackground(new Color(0, 0, 0, 0));
		panelChoixNext.add(pnlTitreNext);
		
		JLabel lblNext = new JLabel("Choisissez le joueur � cibler ?");
		lblNext.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		pnlTitreNext.add(lblNext);
		

		compteur = 0;
		Jeu.getInstance().getEnsembleJoueurs().forEach(Joueur -> {	
			
			accusable = true;
			
			Joueur.getCarteRevelees().forEach(Card -> {
				if (Card.getNumCarte() == 6) {
					accusable = false;
				}
			});
			
			if (Joueur != Jeu.getInstance().getEnTour() && accusable == true && Joueur.getIdentiteAssociee().getDevoilee() == false) {
				
				JPanel pnlbouton = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlbouton.getLayout();
				flowLayout.setHgap(400);
				pnlbouton.setBackground(new Color(0, 0, 0, 0));
				
				JButton btnJoueur = new JButton("Joueur : " + Joueur.getPseudo() + " | Points : " + Joueur.getPoints());
				btnJoueur.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
				btnJoueur.setBounds(500, 159, 323, 80);
				Jeu.getInstance().getControler().setInputDuckingStoolCible(btnJoueur, compteur);
				
				pnlbouton.add(btnJoueur);
				
				panelChoixNext.add(pnlbouton);
				
				
			}
			
			compteur++;
			
		});

		
		
		LayeredPaneChoixNext.setVisible(true);
		LayeredPaneChoixTour.setVisible(false);
		layeredPaneCarteHunt.removeAll();
		layeredPaneCarteHunt.setVisible(false);
		LayeredPaneEnsemble.moveToFront(LayeredPaneChoixNext);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
	}
	
	public void duckingStoolChoixCible(int Joueur) {
		LayeredPaneChoixNext.removeAll();
		LayeredPaneChoixNext.setVisible(false);
		
		LayeredPaneChoixDuckingStool = new JLayeredPane();
		LayeredPaneChoixDuckingStool.setBackground(UIManager.getColor("Button.background"));
		LayeredPaneChoixDuckingStool.setBounds(0, 0, 1280, 1080);
		LayeredPaneEnsemble.add(LayeredPaneChoixDuckingStool);
		LayeredPaneEnsemble.moveToFront(LayeredPaneChoixDuckingStool);
		
		
		JPanel panelTitreDuckingStool = new JPanel();
		panelTitreDuckingStool.setBounds(0, 35, 1266, 50);
		LayeredPaneChoixDuckingStool.add(panelTitreDuckingStool);
		
		JLabel lblTitreDuckingStool = new JLabel("Un joueur � jou� \"Un b�cher\" sur vous !");
		lblTitreDuckingStool.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panelTitreDuckingStool.add(lblTitreDuckingStool);
		
		JPanel pnlDescDuckingStool = new JPanel();
		pnlDescDuckingStool.setBackground(SystemColor.menu);
		pnlDescDuckingStool.setBounds(0, 300, 1266, 50);
		LayeredPaneChoixDuckingStool.add(pnlDescDuckingStool);
		
		JLabel lblDuckingStool = new JLabel("Joueur \"" + Jeu.getInstance().getJoueur(Joueur).getPseudo() + "\", que voulez-vous faire ?");
		lblDuckingStool.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		lblDuckingStool.setForeground(SystemColor.controlDkShadow);
		pnlDescDuckingStool.add(lblDuckingStool);
	
		JButton btnReveal = new JButton("Reveler votre identit�");
		btnReveal.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnReveal.setBounds(260, 159, 323, 80);
		LayeredPaneChoixDuckingStool.add(btnReveal);
		
		JButton btnDiscard = new JButton("D�fausser une carte");
		btnDiscard.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnDiscard.setBounds(675, 159, 323, 80);
		LayeredPaneChoixDuckingStool.add(btnDiscard);
		
		Jeu.getInstance().getControler().setInputsChoixDuckingStool(btnReveal,btnDiscard,Joueur);
		
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
		layeredPaneCarteHunt.setVisible(true);
	}
	
	public void evilEye(Effet effet, boolean isHunt) {
		this.btnAccuser.setVisible(false);
		this.btnJouerCarte.setVisible(false);
		
		
		
		LayeredPaneChoixNext = new JLayeredPane();
		LayeredPaneChoixNext.setBounds(0, 0, 1280, 1080);
		LayeredPaneChoixNext.setBackground(UIManager.getColor("Button.background"));
		LayeredPaneEnsemble.add(LayeredPaneChoixNext);
		
		panelChoixNext = new JPanel();
		panelChoixNext.setBackground(UIManager.getColor("Button.background"));
		panelChoixNext.setBounds(0, 0, 1280, 1080);
		LayeredPaneChoixNext.add(panelChoixNext);
		
		JPanel pnlTitreNext = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlTitreNext.getLayout();
		flowLayout_2.setHgap(400);
		pnlTitreNext.setBackground(new Color(0, 0, 0, 0));
		panelChoixNext.add(pnlTitreNext);
		
		JLabel lblNext = new JLabel("Choisissez le prochain joueur ?");
		lblNext.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		pnlTitreNext.add(lblNext);
		
		
		compteur = 0;
		
		if (isHunt) {
			Jeu.getInstance().getEnsembleJoueurs().forEach(Joueur -> {	
				
				
				
				if (((Joueur.getIdentiteAssociee().getDevoilee() == true && Joueur.getIdentiteAssociee().getIsWitch() == false) || Joueur.getIdentiteAssociee().getDevoilee() == false) && Joueur != Jeu.getInstance().getEnTour()) {
					
					JPanel pnlbouton = new JPanel();
					FlowLayout flowLayout = (FlowLayout) pnlbouton.getLayout();
					flowLayout.setHgap(400);
					pnlbouton.setBackground(new Color(0, 0, 0, 0));
					
					JButton btnJoueur = new JButton("Joueur : " + Joueur.getPseudo() + " | Points : " + Joueur.getPoints());
					btnJoueur.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
					btnJoueur.setBounds(500, 159, 323, 80);
					Jeu.getInstance().getControler().setEvilEye(isHunt, btnJoueur, compteur);
					
					pnlbouton.add(btnJoueur);
					
					panelChoixNext.add(pnlbouton);
					
					
				}
				
				compteur++;
				
			});
		}
		else {
			Jeu.getInstance().getEnsembleJoueurs().forEach(Joueur -> {	
				
				
				
				if (((Joueur.getIdentiteAssociee().getDevoilee() == true && Joueur.getIdentiteAssociee().getIsWitch() == false) || Joueur.getIdentiteAssociee().getDevoilee() == false) && Joueur != Jeu.getInstance().getAccused()) {
					
					JPanel pnlbouton = new JPanel();
					FlowLayout flowLayout = (FlowLayout) pnlbouton.getLayout();
					flowLayout.setHgap(400);
					pnlbouton.setBackground(new Color(0, 0, 0, 0));
					
					JButton btnJoueur = new JButton("Joueur : " + Joueur.getPseudo() + " | Points : " + Joueur.getPoints());
					btnJoueur.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
					btnJoueur.setBounds(500, 159, 323, 80);
					Jeu.getInstance().getControler().setEvilEye(isHunt, btnJoueur, compteur);
					
					pnlbouton.add(btnJoueur);
					
					panelChoixNext.add(pnlbouton);
					
					
				}
				
				compteur++;
				
			});
		}
		
		
		
		
		LayeredPaneChoixNext.setVisible(true);
		LayeredPaneChoixTour.setVisible(false);
		if (layeredPaneCarteWitch != null) {
			layeredPaneCarteWitch.removeAll();
			layeredPaneCarteWitch.setVisible(false);
		}
		if (layeredPaneCarteHunt != null) {
			layeredPaneCarteHunt.removeAll();
			layeredPaneCarteHunt.setVisible(false);
		}
		LayeredPaneEnsemble.moveToFront(LayeredPaneChoixNext);
		LayeredPaneEnsemble.moveToFront(LayeredPaneSuivi);
	}
	
	public void passerTourSuivantAccusable() {
		
		
		if (Jeu.getInstance().getRound().isRoundEnd()) {
			
			Jeu.getInstance().getGagnantRound().gagnerPoints();
			
			InterfaceFinRound finRound = new InterfaceFinRound(Jeu.getInstance().getGagnantRound());
			
		}
		else {
			Jeu.getInstance().getRound().jouerUnTour();
		}
		
	}
}
