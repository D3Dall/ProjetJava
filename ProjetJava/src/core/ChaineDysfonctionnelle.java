package core;

public class ChaineDysfonctionnelle extends Exception {
	private String code;
	private String nom;
	private String causeErreur;
	private String detailErreur;
	public ChaineDysfonctionnelle(String code, String nom, String causeErreur, String detailErreur) {
		this.code = code;
		this.nom = nom;
		this.causeErreur = causeErreur;
		this.detailErreur = detailErreur;
	}
	
	public ChaineDysfonctionnelle(ChaineProduction cp, String causeErreur, String detailErreur) {
		this.code = cp.getCodeChaineProduction();
		this.nom = cp.getNom();
		this.causeErreur = causeErreur;
		this.detailErreur = detailErreur;
	}
}
