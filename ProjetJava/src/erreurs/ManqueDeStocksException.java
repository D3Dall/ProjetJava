package erreurs;

public class ManqueDeStocksException extends ManqueDeRessourceException {
	
	public ManqueDeStocksException(int temps) {
		super(temps, "Stocks");
	}
	
	public String toString() {
		return super.toString() + "Cause : Stocks insuffisant";
	}
}
