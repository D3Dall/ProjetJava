package core;

public class Dysfonctionnement extends Exception {
	private int heure;
	private String causeErreur;
	private String detailErreur;
	public Dysfonctionnement(int heure, String causeErreur, String detailErreur) {
		this.heure=heure;
		this.causeErreur = causeErreur;
		this.detailErreur = detailErreur;
	}
	
	public String toString() {
		return "t=" + this.heure + " : " + this.causeErreur + " - " + this.detailErreur;
	}
	
	
	//GETTERS

	public String getCauseErreur() {
		return causeErreur;
	}

	public String getDetailErreur() {
		return detailErreur;
	}

	public int getHeure() {
		return heure;
	}	
	
	
}
