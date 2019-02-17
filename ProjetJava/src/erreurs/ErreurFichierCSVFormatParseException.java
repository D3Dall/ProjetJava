package erreurs;

public class ErreurFichierCSVFormatParseException extends ErreurFichierCSVLecture {
	private String elem;
	
	public ErreurFichierCSVFormatParseException(String ligne, String fichier, String elem) {
		super(ligne, fichier);
		this.elem=elem;
	}
	
	public String toString() {
		return super.toString() + "l'element n'est pas un nombre : "+ this.elem;
	}

}
