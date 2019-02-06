package core;

public abstract class Element {
        /**
         * Identifiant d'un element
         */
	private String codeElement;
        /**
         * nom d'un element
         */
	private String nom;
        /**
         * Stock d'un element.
         * Représente la quantitee de l'element et son unite.
         */
	private Stockage stock;
        /**
         * Prix de l'element a l'achat.
         * -1 si ce prix est inconnu
         */
	private double prixAchat;
        /**
         * Prix de l'element a la vente
         * -1 si ce prix est inconnu
         */
	private double prixVente;
        
        /**
         * Instancie un Element sans quantite
         * @param codeElement
         *  Identifiant d'un element
         * @param nom
         *  nom d'un element
         * @param unitee
         *  unite que l'on utilise pour dénombrer l'element
         * @param prixAchat
         *  prix a l'achat de l'element
         * @param prixVente 
         *  prix a la vente de l'element
         */
	public Element(String codeElement, String nom, String unitee, double prixAchat, double prixVente) {
		this(codeElement, nom, unitee, 0, prixAchat, prixVente);
	}
        
        /**
         * Instancie un Element avec une quantite
         * @param codeElement
         *  Identifiant d'un element
         * @param nom
         *  nom d'un element
         * @param unitee
         *  unite que l'on utilise pour dénombrer l'element
         * @param quantite
         *  quantite associe a l'element
         * @param prixAchat
         *  prix a l'achat de l'element
         * @param prixVente 
         *  prix a la vente de l'element
         */
	public Element(String codeElement, String nom, String unitee, double quantite, double prixAchat, double prixVente) {
		this.codeElement = codeElement;
		this.nom = nom;
		this.stock = new Stockage(quantite, unitee);
		this.prixAchat = prixAchat;
		this.prixVente=prixVente;
	}
	
        /**
         * 
         * @return le stock associe a l'element 
         */
	public Stockage getStock() {
		return this.stock;
	}
	
        /**
         * Verifie si le code envoye correspond au code (identifiant) de l'element
         * @param code
         * @return vrai si le code correspond, faux sinon
         */
	public boolean CodeCorrect(String code) {
		if(this.codeElement.equals(code)) {
			return true;
		}
		return false;
	}
	
        /**
         * Transforme l'objet en une chaine de caracteres
         * @return l'objet sous forme de chaine de caracteres
         */
	public String toString() {
		return this.codeElement + " - " + this.nom + "\nprix d'achat : " + this.prixAchat
				+ "\nprix de vente :" +this.prixVente + "\n" + this.stock + "\n" ; 
	}

	public String getCodeElement() {
		return codeElement;
	}

	public String getNom() {
		return nom;
	}

	public double getPrixAchat() {
		return prixAchat;
	}

	public double getPrixVente() {
		return prixVente;
	}

	
	
	
	
	
}
