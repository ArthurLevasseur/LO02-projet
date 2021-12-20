package modele;
import effets.EffetW1;
import effets.EffetW2;
import effets.EffetW3;
import effets.EffetW4;
import effets.EffetW7;
import effets.EffetW8;
import effets.EffetW9;

public class Witch{

	private Effet effetAssocie;
	
	public Witch(int numCarte) {
		if (numCarte == 1) {
			this.effetAssocie = new EffetW1();
		}
		else if (numCarte == 2) {
			this.effetAssocie = new EffetW2();
		}
		else if (numCarte == 3) {
			this.effetAssocie = new EffetW3();
		}
		else if (numCarte == 4) {
			this.effetAssocie = new EffetW4();
		}
		else if (numCarte == 5) {
			this.effetAssocie = new EffetW1();
		}
		else if (numCarte == 6) {
			this.effetAssocie = new EffetW1();
		}
		else if (numCarte == 7) {
			this.effetAssocie = new EffetW7();
		}
		else if (numCarte == 8) {
			this.effetAssocie = new EffetW8();
		}
		else if (numCarte == 9) {
			this.effetAssocie = new EffetW9();
		}
		else if (numCarte == 10) {
			this.effetAssocie = new EffetW1();
		}
		else if (numCarte == 11) {
			this.effetAssocie = new EffetW1();
		}
		else if (numCarte == 12) {
			this.effetAssocie = new EffetW1();
		}
	}
	
	public Joueur executerEffet(int numCarte) {
		return this.effetAssocie.executionEffet();
		/*System.out.println("Effet appliqué == " + effet); // à retirer par la suite
		Jeu instanceJeu = Jeu.getInstance();
		Defausse instanceDefausse = Defausse.getInstance();
		
		if (numCarte == 1 || numCarte == 5 || numCarte == 6 || numCarte == 10 || numCarte == 11 || numCarte == 12) {
			return instanceJeu.getAccused();
		}
		else if (numCarte == 2) {
			instanceJeu.getAccused().getCarteEnMain().forEach(card -> System.out.println("TAPEZ "+instanceJeu.getAccused().getCarteEnMain().indexOf(card) + " pour défausser " + card));
			Scanner saisieUtilisateur = new Scanner(System.in);
			int choix = saisieUtilisateur.nextInt();
			while (choix<0 || choix>instanceJeu.getAccused().getCarteEnMain().size() || instanceJeu.getAccused().getCarteEnMain().get(choix).numCarte == 2) {
				if (instanceJeu.getAccused().getCarteEnMain().get(choix).numCarte == 2) {System.out.println("Vous ne pouvez pas défausser cette carte même !");}
				else {System.out.println("Choix invalide !");
				}
				choix = saisieUtilisateur.nextInt();
			}
			CarteRumeur carteADefausser = instanceJeu.getAccused().getCarteEnMain().get(choix);
			instanceDefausse.defausserUneCarte(carteADefausser);
			return instanceJeu.getAccused();
		}
		else if (numCarte == 3) {
			instanceJeu.getAccused().getCarteRevelees().forEach(card -> System.out.println("TAPEZ "+instanceJeu.getAccused().getCarteRevelees().indexOf(card) + " pour prendre " + card));
			Scanner saisieUtilisateur = new Scanner(System.in);
			int choix = saisieUtilisateur.nextInt();
			CarteRumeur carteARecuperer = instanceJeu.getAccused().getCarteRevelees().get(choix);
			instanceJeu.getAccused().prendreCarteRumeur(carteARecuperer);
			return instanceJeu.getAccused();
		}
		else if (numCarte == 4) {
			instanceJeu.getEnTour().getCarteEnMain().forEach(card -> System.out.println("TAPEZ "+instanceJeu.getEnTour().getCarteEnMain().indexOf(card) + " pour prendre " + card));
			Scanner saisieUtilisateur = new Scanner(System.in);
			int choix = saisieUtilisateur.nextInt();
			CarteRumeur carteARecuperer = instanceJeu.getEnTour().getCarteEnMain().get(choix);
			instanceJeu.getAccused().prendreCarteRumeur(carteARecuperer);
			return instanceJeu.getAccused();
		}

		else if (numCarte == 7) {
			Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getAccused(),"Choisissez le prochain joueur.");
			return choix;
		}
		else if (numCarte == 8) {
			Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getAccused(),"Choisissez le joueur à défausser.");
			instanceDefausse.defausserUneCarte(choix.seFairePrendreCarteRumeur((int)(Math.random()*choix.getCarteEnMain().size())));
			return instanceJeu.getAccused();
		}
		else if (numCarte == 9) {
			Joueur choix = instanceJeu.selectionnerAdversaire(instanceJeu.getAccused(),"Choisissez le prochain joueur.");
			choix.setMustAccuse(true);
			instanceJeu.getAccused().setAccusable(false);
			return choix;
			
		}
		
		return null;*/
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("effet Witch? : " + this.effetAssocie.explication);
		return str.toString();
	}
}
