package core;

public class MatieresPremieres extends Element{
    
        /**
         * Créé une matière première sans une quantité.
         * 
         * @param codeElement
         * L'identifiant d'une Matiere Premiere.
         * 
         * @param nom
         * Le nom d'une Matiere Premiere.
         * 
         * @param unitee
         * L'unite que l'on utilise pour dénombrer la Matiere Premiere.
         * 
         * @param prixAchat
         * Le prix à l'achat de la Matiere Premiere.
         * 
         * @param prixVente 
         * Le prix à la vente de la Matiere Premiere.
         * 
         */
	public MatieresPremieres(String codeElement, String nom, String unitee, double prixAchat, double prixVente, float demande) {
		super(codeElement, nom, unitee, prixAchat, prixVente, demande);
		// TODO Auto-generated constructor stub
	}
        
        /**
         * Créé une matière première avec une quantité.
         * 
         * @param codeElement
         *  L'identifiant d'une Matiere Premiere.
         *  
         * @param nom
         *  Le nom d'une Matiere Premiere.
         *  
         * @param unitee
         *  L'unite que l'on utilise pour dénombrer la Matiere Premiere.
         *  
         * @param quantite
         *  La quantité associée à la Matiere Premiere.
         *  
         * @param prixAchat
         *  Le prix à l'achat de la Matiere Premiere.
         *  
         * @param prixVente 
         *  Le prix à la vente de la Matiere Premiere .
         *  
         */
	public MatieresPremieres(String codeElement, String nom, String unitee, float quantite, double prixAchat, double prixVente, float demande) {
		super(codeElement, nom, unitee, quantite, prixAchat, prixVente, demande);
		// TODO Auto-generated constructor stub
	}



}
