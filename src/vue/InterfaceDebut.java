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

public class InterfaceDebut implements Vue{

	private JFrame frame;
	private JLabel lblBienvenu;
	private JButton btnDemarrer;
	private JButton btnQuitter;
	private Controler controler;

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	
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

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public InterfaceDebut() {
		initialize();
		Controler c = new Controler(btnDemarrer, btnQuitter, frame);
		this.frame.setVisible(true);
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1280, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblBienvenu = new JLabel("Bienvenu dans Which Hunt !");
		lblBienvenu.setBounds(0, 29, 1266, 49);
		lblBienvenu.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
		lblBienvenu.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblBienvenu);
		
		btnDemarrer = new JButton("Nouvelle Partie");
		btnDemarrer.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDemarrer.setBounds(457, 155, 323, 80);
		frame.getContentPane().add(btnDemarrer);
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnQuitter.setBounds(457, 304, 323, 80);
		frame.getContentPane().add(btnQuitter);
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

	public Controler getControler() {
		return controler;
	}
}
