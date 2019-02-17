package erreurs;

public abstract class ErreurFichierCSVLecture extends Exception {
	private String ligne;
	private String fichier;
	
	public ErreurFichierCSVLecture(String ligne, String fichier) {
		super ("Erreur fichier csv");
		this.ligne= ligne;
		this.fichier = fichier;
	}
	
	public String toString() {
		return "Erreur fichier csv : " + this.fichier + " à la ligne : " +this.ligne;
	}
	
	
	
	
	
}
