package erreurs;

public abstract class ManqueDeRessourceException extends Exception {
	private int temps;
	
	public ManqueDeRessourceException(int temps, String ressource) {
		super("Manque de ressource : " + ressource);
		this.temps=temps;
	}
	
	public String toString() {
		return  "Arrêt des machines à t+" + this.temps;
	}
}
