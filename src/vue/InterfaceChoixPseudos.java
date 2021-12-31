package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import modele.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfaceChoixPseudos implements Vue {

	private JFrame frame;
	private static int compteur = 0;
	private JTextField textField;
	private JButton btnValider;
	private JLabel lblNewLabel;
	private JLabel lblTitre;
	private JPanel panelTitre;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public InterfaceChoixPseudos(Joueur j) {
		compteur += 1;
		initialize();
		Jeu.getInstance().setVueActuelle(this);
		this.frame.setVisible(true);
		Jeu.getInstance().getControler().setInputsPseudos(btnValider);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1280, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panelTitre = new JPanel();
		panelTitre.setBounds(0, 35, 1266, 50);
		frame.getContentPane().add(panelTitre);
		
		lblTitre = new JLabel("Joueur " + compteur + ", choisissez votre pseudo !");
		lblTitre.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panelTitre.add(lblTitre);
		
		textField = new JTextField();
		textField.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		textField.setBounds(437, 233, 395, 27);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("Pseudo :");
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		lblNewLabel.setBounds(361, 235, 76, 21);
		frame.getContentPane().add(lblNewLabel);
		
		btnValider = new JButton("Valider");
		btnValider.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnValider.setBounds(456, 403, 323, 80);
		frame.getContentPane().add(btnValider);
	}
	
	public void demarrerRound() {
		this.frame.setVisible(false);
		Jeu.getInstance().getJoueur(compteur-1).setPseudo(this.textField.getText());
		if (compteur < Jeu.getInstance().getNombrePhy()) {
			InterfaceChoixPseudos JoueurSuivant = new InterfaceChoixPseudos(Jeu.getInstance().getJoueur(compteur));
		}
		else {
			Jeu.getInstance().initRound();
		}
	}
}
