package modele;

abstract public class Effet {
	
	protected String explication;

	public abstract void appelVue();
	public abstract void executionEffet();
	public abstract void executionEffet(Joueur selection);
	
}
