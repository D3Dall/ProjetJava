package erreurs;

public class EntrepriseManquanteException extends Exception {
	
	public EntrepriseManquanteException() {
		super("Aucun contenu");
	}
	
	public String toString() {
		return "Aucune donnée n'a été chargé";
	}
	
	
}
