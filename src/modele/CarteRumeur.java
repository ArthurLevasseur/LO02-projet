package modele;
import effets.*;

public class CarteRumeur {

	protected int numCarte;
	protected String nomCarte;
	protected String effetSupp = "";
	protected static int nbCarte = 0;
	protected Hunt effetHunt;
	protected Witch effetWitch;
	
	
	public CarteRumeur() {
		nbCarte += 1;
		numCarte = nbCarte;
		
		if (numCarte == 1) {
			this.nomCarte = "Une foule en colère";
		}
		else if (numCarte == 2) {
			this.nomCarte = "L'inquisition";
		}
		else if (numCarte == 3) {
			this.nomCarte = "Un chapeau pointu";
		}
		else if (numCarte == 4) {
			this.nomCarte = "Un nez crochu";
		}
		else if (numCarte == 5) {
			this.nomCarte = "Un manche à balai";
			this.effetSupp = "Lorsque cette carte est révélée, vous ne pouvez pas être ciblé par la carte \"Une foule en colère\".\n";
		}
		else if (numCarte == 6) {
			this.nomCarte = "Une verrue";
			this.effetSupp = "Lorsque cette carte est révélée, vous ne pouvez pas être ciblé par la carte \"Le bûcher\".\n";
		}
		else if (numCarte == 7) {
			this.nomCarte = "Un bûcher";
		}
		else if (numCarte == 8) {
			this.nomCarte = "Un chaudron";
		}
		else if (numCarte == 9) {
			this.nomCarte = "Un mauvais oeil";
		}
		else if (numCarte == 10) {
			this.nomCarte = "Un crapaud";
		}
		else if (numCarte == 11) {
			this.nomCarte = "Un chat noir";
		}
		else if (numCarte == 12) {
			this.nomCarte = "Un triton de compagnie";
		}
		
		this.effetHunt = new Hunt(numCarte);
		this.effetWitch = new Witch(numCarte);
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("Carte Rumeur : " + this.nomCarte + "(n°" + this.numCarte + ")" + "\n" + effetSupp);
		str.append(this.effetHunt + "\n");
		str.append(this.effetWitch + "\n");
		return str.toString();
	}
	
	public void appliquerEffetHunt() {
		this.effetHunt.executerEffet(numCarte);
	}
	
	public Joueur appliquerEffetWitch(Joueur accused) {
		return this.effetWitch.executerEffet(numCarte);
	}

	public int getNumCarte() {
		return numCarte;
	}

	public void setNumCarte(int numCarte) {
		this.numCarte = numCarte;
	}

	public String getNomCarte() {
		return nomCarte;
	}

	public void setNomCarte(String nomCarte) {
		this.nomCarte = nomCarte;
	}

	public Hunt getEffetHunt() {
		return effetHunt;
	}


	public Witch getEffetWitch() {
		return effetWitch;
	}

	
	
}
