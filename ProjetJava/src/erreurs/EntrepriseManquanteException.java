package erreurs;

public class EntrepriseManquanteException extends Exception {
	
	public EntrepriseManquanteException() {
		super("Aucun contenu");
	}
	
	public String toString() {
		return "Aucune donn�e n'a �t� charg�";
	}
	
	
}
