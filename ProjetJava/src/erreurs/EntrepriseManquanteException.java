package erreurs;

public class EntrepriseManquanteException extends Exception {
	
	public EntrepriseManquanteException() {
		super("Aucune donn�es import�es");
	}
	
	public String toString() {
		return "Aucune donn�e n'a �t� charg�";
	}
	
	
}
