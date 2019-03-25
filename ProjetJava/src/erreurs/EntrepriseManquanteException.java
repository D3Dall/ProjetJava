package erreurs;

public class EntrepriseManquanteException extends Exception {
	
	public EntrepriseManquanteException() {
		super("Aucune données importées");
	}
	
	public String toString() {
		return "Aucune donnée n'a été chargé";
	}
	
	
}
