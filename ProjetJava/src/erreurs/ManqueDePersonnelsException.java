package erreurs;

public class ManqueDePersonnelsException extends ManqueDeRessourceException {
	
	public ManqueDePersonnelsException (int temps) {
		super(temps, "Personnels");
	}
	
	public String toString() {
		return super.toString() + "Cause : Personnels insuffisant";
	}
}
