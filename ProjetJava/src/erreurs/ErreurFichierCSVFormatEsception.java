package erreurs;

public class ErreurFichierCSVFormatEsception extends ErreurFichierCSVLecture {
	
	public ErreurFichierCSVFormatEsception(String ligne, String fichier) {
		super(ligne, fichier);
	}
	
	public String toString() {
		return super.toString() + "format incorect (element(s) de split (; ,) en trop ou manquant)";
	}
	
}
