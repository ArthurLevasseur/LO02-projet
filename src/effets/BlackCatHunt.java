package effets;
import modele.Defausse;
import modele.Effet;
import modele.Jeu;
import modele.Joueur;
import modele.SaisirInt;

public class BlackCatHunt extends Effet {
	
	public BlackCatHunt() {
		super();
		this.explication = "Vous ajoutez une des cartes d�fauss�es � votre main, et vous vous d�faussez de cette carte.\nVous prenez le prochain tour";
	}
	
	public void appelVue() {
		Jeu instanceJeu = Jeu.getInstance();
		instanceJeu.getVueActuelle().blackCatHunt(this);
	}
	
	public Joueur executionEffet() {
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		boolean visable = true;
		SaisirInt saisieUtilisateur = SaisirInt.getInstance();

		if (instanceDefausse.getContenu() == null) {
			System.out.println("Le tas de d�fausse est vide !");
		}
		else {
			if (instanceJeu.getEnTour().isIA() == false) {
				if (instanceDefausse.getContenu().isEmpty()) {
					System.out.println("la d�fausse est vide");
				}
				else {
					System.out.println("Voici les cartes rumeurs de la d�fausse, choisissez la carte que vous voulez prendre :");
					instanceDefausse.getContenu().forEach(card -> System.out.println("TAPEZ "+instanceDefausse.getContenu().indexOf(card) + " pour jouer " + card));
					int choix = saisieUtilisateur.nextInt();
					while (choix<0 || choix>instanceDefausse.getContenu().size()) {
						System.out.println("Choix invalide !");
						choix = saisieUtilisateur.nextInt();
					}
					instanceJeu.getEnTour().prendreCarteRumeur(instanceDefausse.seFairePrendreCarteRumeur(choix));
				}
				
			}
			else {
				System.out.println(instanceJeu.getEnTour().getPseudo() + "recup�re une carte de la d�fausse.");
				
				if (instanceDefausse.getContenu().isEmpty()) {
					System.out.println("la d�fausse est vide");
				}
				else {
					int choix = (int) (Math.random()*instanceDefausse.getContenu().size());
					instanceJeu.getEnTour().prendreCarteRumeur(instanceDefausse.seFairePrendreCarteRumeur(choix));
				}
			}

		}
		return instanceJeu.getEnTour();
	}
}
