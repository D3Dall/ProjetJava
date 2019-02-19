package erreurs;

public class FichierCSVManquant extends Exception {
	private boolean elements;
	private boolean chaineProduction;
	private boolean personnel;
	public FichierCSVManquant(boolean elements, boolean chaineProduction, boolean personnel) {
		super("Erreur fichier(s) manquant(s)");
		this.elements = elements;
		this.chaineProduction = chaineProduction;
		this.personnel = personnel;
	}
	
	private String elementOK() {
		if(this.elements) {
			return "OK";
		}
		return "non trouvée";
	}
	private String chaineProductionOK() {
		if(this.chaineProduction) {
			return "OK";
		}
		return "non trouvée";
	}
	private String personnelOK() {
		if(this.personnel) {
			return "OK";
		}
		return "non trouvée";
	}
	
	public String toString() {
		return "Fichier csv manquant : \n elements : "+this.elementOK() + "\n chaines :" +this.chaineProductionOK()+"\n Personnel : "+this.personnelOK();
	}
	
	
	
}
