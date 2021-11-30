import java.util.*;

public class StrategieSimple extends Strategie {
	
	public int choisirActionTour() {
		System.out.println("Il choisit d'accuser un joueur.");
		return 1;
	}
	
	public int choisirAccuse() {
		
		Jeu instance = Jeu.getInstance();
		ArrayList<Integer> possibilites = new ArrayList<Integer>();
		
		
		for (int i=1 ; i<instance.getNombreJoueurs()+1 ; i++) {
			if (instance.getJoueur(i-1).identiteAssociee.getDevoilee() == false && instance.getJoueur(i-1)!=instance.getEnTour() && instance.getJoueur(i-1).isAccusable()==true) {
				possibilites.add(i);
			}
		}
		Collections.shuffle(possibilites);
		
		return possibilites.get(0);
	}

	public int seDefendre() {
		
		return 1;
	}
}
