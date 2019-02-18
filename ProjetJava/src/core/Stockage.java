package core;

public class Stockage {
	
        /**
         * nombre d'element
         */
	private float quantite;
	
        /**
         * unite par laquelle on denombre la quantitee
         */
	private String unitee;
	
        /**
         * Créé un Stockage
         * @param quantite
         *  La quantite d'element
         * @param unitee 
         *  L'uunite par laquelle on dénombre la quantité
         */
	public Stockage(float quantite, String unitee) {
		this.quantite = quantite;
		this.unitee = unitee;
	}
        
	
        
        /**
         * Retire du stockage la quantite envoyee
         * @param quantitee
         *  La quantité dont on souhaite retirer du stockage
         */
	public void retirer(float quantite) {
		this.quantite-=quantite;
	}
        
        /**
         * Ajoute au stockage la quantite envoyée
         * @param quantite
         *  La quantité dont on souhaite ajouter au stockage
         */
	public void ajouter(float quantite) {
		this.quantite+=quantite;
	}
        
        
	public String toString() {
		return this.quantite + " " + this.unitee;
	}

	//GETTERS
	
	public String getUnitee() {
		return unitee;
	}
	
	public float getStock() {
		return this.quantite;
	}
	
	
	
}
