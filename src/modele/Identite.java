package modele;

public class Identite {

	private boolean isWitch;
	private boolean devoilee;
	private Joueur joueurAssocie;
	
	public Identite() {
		this.isWitch = false;
		this.devoilee = false;
	}
	
	public boolean getDevoilee() {
		return this.devoilee;
	}
	
	public void ReveleIdentite() {
		this.devoilee = true;
	}
	
	public boolean getIsWitch() {
		return this.isWitch;
	}
	
	public void choisirIdentite(Joueur joueurAsso, boolean isIA) {
		
		
		
		
	}

	public void setWitch(boolean isWitch) {
		this.isWitch = isWitch;
	}

	public void setDevoilee(boolean devoilee) {
		this.devoilee = devoilee;
	}
	
	
	
	
}
