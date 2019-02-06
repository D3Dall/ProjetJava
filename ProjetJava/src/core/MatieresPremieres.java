package core;

public class MatieresPremieres extends Element{
    
        /**
         * Instancie une Matiere Premiere
         * @param codeElement
         *  Identifiant d'une Matiere Premiere
         * @param nom
         *  nom d'une Matiere Premiere
         * @param unitee
         *  unite que l'on utilise pour dénombrer la Matiere Premiere
         * @param prixAchat
         *  prix a l'achat de la Matiere Premiere
         * @param prixVente 
         *  prix a la vente de la Matiere Premiere
         */
	public MatieresPremieres(String codeElement, String nom, String unitee, double prixAchat, double prixVente) {
		super(codeElement, nom, unitee, prixAchat, prixVente);
		// TODO Auto-generated constructor stub
	}
        
        /**
         * 
         * @param codeElement
         *  Identifiant d'une Matiere Premiere
         * @param nom
         *  nom d'une Matiere Premiere
         * @param unitee
         *  unite que l'on utilise pour dénombrer la Matiere Premiere
         * @param quantite
         *  quantite associe a la Matiere Premiere
         * @param prixAchat
         *  prix a l'achat de la Matiere Premiere
         * @param prixVente 
         *  prix a la vente de la Matiere Premiere 
         */
	public MatieresPremieres(String codeElement, String nom, String unitee, double quantite, double prixAchat, double prixVente) {
		super(codeElement, nom, unitee, quantite, prixAchat, prixVente);
		// TODO Auto-generated constructor stub
	}



}
