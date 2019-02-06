package core;

public class ProduitsFinis extends Element{
        /**
         * Instancie un Produit
         * @param codeElement
         *  Identifiant d'un Produit
         * @param nom
         *  nom d'un Produit
         * @param unitee
         *  unite que l'on utilise pour dénombrer le Produit
         * @param prixAchat
         *  prix a l'achat d'un Produit
         * @param prixVente 
         *  prix a la vente d'un Produit
         */
	public ProduitsFinis(String codeElement, String nom, String unitee, double prixAchat, double prixVente) {
		super(codeElement, nom, unitee, prixAchat, prixVente);
		// TODO Auto-generated constructor stub
	}
        
        /**
         * Instancie un Produit
         * @param codeElement
         *  Identifiant d'un Produit
         * @param nom
         *  nom d'un Produit
         * @param unitee
         *  unite que l'on utilise pour dénombrer le Produit
         * @param quantite
         *  quantite associe a la Matiere Premiere
         * @param prixAchat
         *  prix a l'achat d'un Produit
         * @param prixVente 
         *  prix a la vente d'un Produit
         */
	public ProduitsFinis(String codeElement, String nom, String unitee, double quantite, double prixAchat, double prixVente) {
		super(codeElement, nom, unitee, quantite, prixAchat, prixVente);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
