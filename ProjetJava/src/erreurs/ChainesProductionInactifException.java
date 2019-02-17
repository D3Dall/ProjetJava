package erreurs;

public class ChainesProductionInactifException extends Exception {
	public ChainesProductionInactifException() {
		super("Chaine de production inactif");
	}
	
	public String toString() {
		return "Toutes les chaines de production sont inactif (niveau d'activité = 0)";
	}
}
