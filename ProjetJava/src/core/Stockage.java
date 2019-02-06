package core;

public class Stockage {
        /**
         * nombre d'element
         */
	private double quantite;
        /**
         * unite par laquelle on denombre la quantitee
         */
	private String unitee;
	
        /**
         * Instancie un Stockage
         * @param quantite
         *  quantite d'element
         * @param unitee 
         *  unite par laquelle on denombre la quantitee
         */
	public Stockage(double quantite, String unitee) {
		this.quantite = quantite;
		this.unitee = unitee;
	}
        
        /**
         * 
         * @return la quantitee du stockage
         */
	public double getStock() {
		return this.quantite;
	}
        
        /**
         * retire du stockage la quantite envoyee
         * @param quantitee
         *  quantite dont on souhaite retirer du stockage
         */
	public void retirer(double quantitee) {
		this.quantite-=quantitee;
	}
        
        /**
         * ajoute au stockage la quantite envoyee
         * @param quantitee
         *  quantite dont on souhaite ajouter au stockage
         */
	public void ajouter(double quantitee) {
		this.quantite+=quantitee;
	}
        
        
	public String toString() {
		return "Le stock est de " + this.quantite + " " + this.unitee;
	}

	public String getUnitee() {
		return unitee;
	}
	
	
	
}
