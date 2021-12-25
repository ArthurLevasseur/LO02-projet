package vue;

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
import modele.*;
import vue.*;

import javax.swing.JFrame;

public class InterfaceNbJoueurs implements Observer, Vue{

	private JFrame frame;
	private JLabel lblTitre;
	private JLabel lblJoueursPhy;
	private JLabel lblNombreDeJoueurs;
	private JButton btnRedIA;
	private JButton btnRedPhy;
	private JLabel lblNbPhy;
	private JButton btnAugPhy;
	private JButton btnAugIA;
	private JLabel lblNbIA;
	private JButton btnValider;

	/**
	 * Launch the application.
	 */
	public InterfaceNbJoueurs(){
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
	 * Create the application.
	 */

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Jeu.getInstance().setNombrePhy(3);
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1280, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblTitre = new JLabel("Choix du nombre de joueurs");
		lblTitre.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setBounds(0, 47, 1266, 30);
		frame.getContentPane().add(lblTitre);
		
		lblJoueursPhy = new JLabel("Nombre de joueurs physiques :");
		lblJoueursPhy.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		lblJoueursPhy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJoueursPhy.setBounds(388, 189, 325, 78);
		frame.getContentPane().add(lblJoueursPhy);
		
		lblNombreDeJoueurs = new JLabel("Nombre de joueurs virtuels :");
		lblNombreDeJoueurs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDeJoueurs.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		lblNombreDeJoueurs.setBounds(388, 371, 325, 78);
		frame.getContentPane().add(lblNombreDeJoueurs);
		
		btnRedIA = new JButton("<");
		btnRedIA.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRedIA.setBounds(736, 385, 50, 50);
		frame.getContentPane().add(btnRedIA);
		
		btnRedPhy = new JButton("<");
		btnRedPhy.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRedPhy.setBounds(736, 203, 50, 50);
		frame.getContentPane().add(btnRedPhy);
		
		lblNbPhy = new JLabel("3");
		lblNbPhy.setHorizontalAlignment(SwingConstants.CENTER);
		lblNbPhy.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		lblNbPhy.setBounds(784, 203, 60, 50);
		frame.getContentPane().add(lblNbPhy);
		
		btnAugPhy = new JButton(">");
		btnAugPhy.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAugPhy.setBounds(842, 203, 50, 50);
		frame.getContentPane().add(btnAugPhy);
		
		btnAugIA = new JButton(">");
		btnAugIA.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAugIA.setBounds(842, 385, 50, 50);
		frame.getContentPane().add(btnAugIA);
		
		lblNbIA = new JLabel(Jeu.getInstance().getNombreIA() + "");
		lblNbIA.setHorizontalAlignment(SwingConstants.CENTER);
		lblNbIA.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		lblNbIA.setBounds(784, 385, 60, 50);
		frame.getContentPane().add(lblNbIA);
		
		btnValider = new JButton("Lancer la partie");
		btnValider.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnValider.setBounds(500, 560, 286, 78);
		frame.getContentPane().add(btnValider);
		
		Jeu.getInstance().getControler().setInputsNbJoueurs(this, btnRedPhy, btnAugPhy, btnRedIA, btnAugIA, btnValider, frame);
		Jeu.getInstance().setVueActuelle(this);
		Jeu.getInstance().addObserver(this);
		this.frame.setVisible(true);
	}
	
	public void demarrerPartie() {
		this.frame.setVisible(false);
		Jeu.getInstance().initGame();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		lblNbPhy.setText(Jeu.getInstance().getNombrePhy() + "");
		lblNbIA.setText(Jeu.getInstance().getNombreIA() + "");
	}
	
	

}
