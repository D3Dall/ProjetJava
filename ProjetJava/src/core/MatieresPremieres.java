package core;

public class MatieresPremieres extends Element{
    
        /**
         * Cr�� une mati�re premi�re sans une quantit�.
         * 
         * @param codeElement
         * L'identifiant d'une Matiere Premiere.
         * 
         * @param nom
         * Le nom d'une Matiere Premiere.
         * 
         * @param unitee
         * L'unite que l'on utilise pour d�nombrer la Matiere Premiere.
         * 
         * @param prixAchat
         * Le prix � l'achat de la Matiere Premiere.
         * 
         * @param prixVente 
         * Le prix � la vente de la Matiere Premiere.
         * 
         */
	public MatieresPremieres(String codeElement, String nom, String unitee, double prixAchat, double prixVente, float demande) {
		super(codeElement, nom, unitee, prixAchat, prixVente, demande);
		// TODO Auto-generated constructor stub
	}
        
        /**
         * Cr�� une mati�re premi�re avec une quantit�.
         * 
         * @param codeElement
         *  L'identifiant d'une Matiere Premiere.
         *  
         * @param nom
         *  Le nom d'une Matiere Premiere.
         *  
         * @param unitee
         *  L'unite que l'on utilise pour d�nombrer la Matiere Premiere.
         *  
         * @param quantite
         *  La quantit� associ�e � la Matiere Premiere.
         *  
         * @param prixAchat
         *  Le prix � l'achat de la Matiere Premiere.
         *  
         * @param prixVente 
         *  Le prix � la vente de la Matiere Premiere .
         *  
         */
	public MatieresPremieres(String codeElement, String nom, String unitee, float quantite, double prixAchat, double prixVente, float demande) {
		super(codeElement, nom, unitee, quantite, prixAchat, prixVente, demande);
		// TODO Auto-generated constructor stub
	}



}
