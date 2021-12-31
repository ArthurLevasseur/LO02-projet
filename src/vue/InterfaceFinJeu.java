package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import modele.*;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfaceFinJeu implements Vue {

	private JFrame frame;
	private int compteur;
	private JTable table;
	private JTable table_1;
	private JLayeredPane LayeredPaneDepartage;
	private Joueur joueurGagnant;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public InterfaceFinJeu(ArrayList<Joueur> listeGagnants) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (listeGagnants.size() == 1) {
					
						initializeWinner(listeGagnants.get(0));
					}
					else {
						
						initializeDepartage(listeGagnants);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void initializeDepartage(ArrayList<Joueur> listeGagnants) {
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1280, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		LayeredPaneDepartage = new JLayeredPane();
		LayeredPaneDepartage.setBackground(SystemColor.menu);
		LayeredPaneDepartage.setBounds(0, 0, 1280, 1080);
		frame.getContentPane().add(LayeredPaneDepartage);
		
		JPanel panelTitre = new JPanel();
		panelTitre.setBounds(0, 35, 1266, 50);
		LayeredPaneDepartage.add(panelTitre);
		
		String str = "";
		
		for (int i=0; i<listeGagnants.size()-1; i++) {
			str = str + listeGagnants.get(i).getPseudo() + ", ";
		}
		
		JLabel lblTitre = new JLabel("Bravo aux joueurs :" + str + "et " + listeGagnants.get(listeGagnants.size()-1).getPseudo() + ".");
		lblTitre.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panelTitre.add(lblTitre);
		
		JPanel panelDescriptif = new JPanel();
		panelDescriptif.setSize(1280, 50);
		panelDescriptif.setLocation(0, 600);
		panelDescriptif.setBounds(0, 600, 1266, 50);
		LayeredPaneDepartage.add(panelDescriptif);
		
		JLabel lblDescriptif = new JLabel("Mais il ne peut avoir qu'un gagnant...");
		lblDescriptif.setForeground(SystemColor.controlDkShadow);
		lblDescriptif.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		panelDescriptif.add(lblDescriptif);
		
		
		JButton btnCombat = new JButton("Lancer le combat");
		btnCombat.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnCombat.setBackground(Color.WHITE);
		btnCombat.setBounds(480, 700, 320, 80);
		LayeredPaneDepartage.add(btnCombat);
		
		this.frame.setVisible(true);
		Jeu.getInstance().setVueActuelle(this);
		Jeu.getInstance().getControler().setInputFight(btnCombat, listeGagnants);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeWinner(Joueur winner) {
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1280, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelTitre = new JPanel();
		panelTitre.setBounds(0, 35, 1266, 50);
		frame.getContentPane().add(panelTitre);
		
		JLabel lblTitre = new JLabel("Joueur " + winner.getPseudo() + ", vous avez gagn\u00E9 la partie, bravo !");
		lblTitre.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panelTitre.add(lblTitre);
		
		JLabel lblDescriptif = new JLabel("Voici un petit récapitulatif des scores :");
		lblDescriptif.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescriptif.setForeground(SystemColor.controlDkShadow);
		lblDescriptif.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		lblDescriptif.setBounds(0, 200, 1266, 21);
		frame.getContentPane().add(lblDescriptif);
		
		
		
		
		String[][] infoJoueur = new String[Jeu.getInstance().getNombreJoueurs() + 1][3];
		
		compteur = 1;
		
		infoJoueur[0][0] = "PSEUDOS :";
		infoJoueur[0][1] = "CLASSEMENT :";
		infoJoueur[0][2] = "POINTS :";
		
		Jeu.getInstance().getEnsembleJoueurs().forEach((player) -> {
			
			;
			
			infoJoueur[compteur][0] = player.getPseudo();
			infoJoueur[compteur][1] = Jeu.getInstance().getClassement(player) + "e";
			infoJoueur[compteur][2] = (player.getPoints() + "");
			
			compteur++;
			
		});
		
		
		
		
		
		table = new JTable();
		table.setForeground(Color.DARK_GRAY);
		table.setSize(761, (Jeu.getInstance().getNombreJoueurs()+1)*50);
		table.setRowHeight(50);
		table.setLocation(256, 259);
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
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
		frame.getContentPane().add(table);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnQuitter.setBackground(Color.WHITE);
		btnQuitter.setBounds(480, 700, 320, 80);
		frame.getContentPane().add(btnQuitter);
		
		this.frame.setVisible(true);
		Jeu.getInstance().setVueActuelle(this);
		Jeu.getInstance().getControler().setInputQuitter(btnQuitter);
	}
	
	public void leave() {
		this.frame.setVisible(false);
	}
	
	public void fight(ArrayList<Joueur> listeGagnants) {
		
		int gagnant = (int) (Math.random() * listeGagnants.size());
		joueurGagnant = listeGagnants.get(gagnant);
		joueurGagnant.setPoints(joueurGagnant.getPoints()+1);
		LayeredPaneDepartage.setVisible(false);
		frame.setVisible(false);
		initializeWinner(joueurGagnant);
	}
}
