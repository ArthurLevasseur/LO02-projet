package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modele.Jeu;
import modele.Round;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class InterfaceIdentite implements Vue{

	private JFrame frame;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public InterfaceIdentite(int joueur) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize(joueur);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int joueur) {
		frame = new JFrame();
		frame.setBounds(0, 0, 1280, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelTitre = new JPanel();
		panelTitre.setBounds(0, 35, 1266, 50);
		frame.getContentPane().add(panelTitre);
		
		JLabel lblTitre = new JLabel("C'est le début du round : Joueur \"" + Jeu.getInstance().getJoueur(joueur).getPseudo() + "\", choisissez votre identité !");
		lblTitre.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panelTitre.add(lblTitre);
		
		JButton btnWitch = new JButton("Witch?");
		btnWitch.setForeground(Color.WHITE);
		btnWitch.setBackground(Color.BLACK);
		btnWitch.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnWitch.setBounds(191, 328, 323, 80);
		frame.getContentPane().add(btnWitch);
		
		JButton btnHunt = new JButton("Villager!");
		btnHunt.setBackground(Color.WHITE);
		btnHunt.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnHunt.setBounds(744, 328, 323, 80);
		frame.getContentPane().add(btnHunt);
		
		JTextPane txtDescriptifWitch = new JTextPane();
		txtDescriptifWitch.setForeground(UIManager.getColor("Button.darkShadow"));
		txtDescriptifWitch.setBackground(UIManager.getColor("Button.background"));
		txtDescriptifWitch.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		txtDescriptifWitch.setText("En Witch, vous gagnerez 2 points si vous \u00EAtes le dernier \u00E0 survivre, mais attention aux autres villageois qui voudront vous d\u00E9masquer pour gagner 1 point !");
		txtDescriptifWitch.setBounds(127, 439, 439, 156);
		frame.getContentPane().add(txtDescriptifWitch);
		
		JTextPane txtDescriptifHunt = new JTextPane();
		txtDescriptifHunt.setText("En Hunt, \u00E0 vous de chercher les Witch dissimul\u00E9es parmi vous, en demasquer une vous rapportera un point ! Cependant, gagner un round ne vous fera gagner qu'un point.");
		txtDescriptifHunt.setForeground(SystemColor.controlDkShadow);
		txtDescriptifHunt.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		txtDescriptifHunt.setBackground(SystemColor.menu);
		txtDescriptifHunt.setBounds(683, 439, 439, 156);
		frame.getContentPane().add(txtDescriptifHunt);
		
		
		Jeu.getInstance().setVueActuelle(this);
		this.frame.setVisible(true);
		Jeu.getInstance().getControler().setInputsChoixIdentités(btnWitch,btnHunt,joueur);
	}
	
	public void demarrerTour(int joueur, boolean choix) {
		Jeu.getInstance().getJoueur(joueur).getIdentiteAssociee().choisirIdentite(Jeu.getInstance().getJoueur(joueur), choix);
		this.frame.setVisible(false);
		
		if (joueur<Jeu.getInstance().getNombrePhy()-1) {
			Jeu.getInstance().setVueActuelle(new InterfaceIdentite(joueur+1));
		}
		else {
			if (Jeu.getInstance().getRound() == null) {
				int premierJoueur = (int) (Math.random() * Jeu.getInstance().getNombreJoueurs());
				Jeu.getInstance().setEnTour(Jeu.getInstance().getJoueur(premierJoueur));
				Round roundEnCours = new Round();
				Jeu.getInstance().setRound(roundEnCours);
				roundEnCours.initRound(Jeu.getInstance().getJoueur(premierJoueur));
			}
			else {
				Jeu.getInstance().getRound().initRound(Jeu.getInstance().getEnTour());
			}
		}
	}
	
	
}
