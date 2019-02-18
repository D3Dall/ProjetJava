package core;

public abstract class Element {
        /**
         * L'identifiant d'un element.
         */
	private String codeElement;
	
        /**
         * Le nom d'un element.
         */
	private String nom;
	
        /**
         * Le stock d'un element.
         * Représente la quantité de l'element et son unité de mesure.
         * 
         */
	private Stockage stock;
	
        /**
         * Le prix de l'element a l'achat.
         * -1 si ce prix est inconnu.
         * 
         */
	private double prixAchat;
	
        /**
         * Prix de l'element a la vente.
         * -1 si ce prix est inconnu.
         */
	private double prixVente;
	
		/**
		 * Le quantité de l'élément demandé.
		 */
	private double demande;
        
        /**
         * Créé un Element sans quantité
         * 
         * @param codeElement
         *  L'dentifiant d'un element.
         *  
         * @param nom
         *  Le nom d'un element.
         *  
         * @param unitee
         *  L'unite que l'on utilise pour dénombrer l'element.
         *  
         * @param prixAchat
         *  Le prix a l'achat de l'element.
         *  
         * @param prixVente 
         *  Le prix a la vente de l'element.
         */
	public Element(String codeElement, String nom, String unitee, double prixAchat, double prixVente, float demande) {
		this(codeElement, nom, unitee, 0, prixAchat, prixVente, demande);
	}
        
        /**
         * Créé un element avec une quantité.
         * 
         * @param codeElement
         * L'identifiant d'un element.
         * 
         * @param nom
         * Le nom d'un element.
         *  
         * @param unitee
         * L'unite que l'on utilise pour dénombrer l'element.
         *  
         * @param quantite
         * La quantite associe a l'element.
         *  
         * @param prixAchat
         * Le prix à l'achat de l'element.
         *  
         * @param prixVente 
         * Le prix à la vente de l'element.
         *  
         */
	public Element(String codeElement, String nom, String unitee, float quantite, double prixAchat, double prixVente, float demande) {
		this.codeElement = codeElement;
		this.nom = nom;
		this.stock = new Stockage(quantite, unitee);
		this.prixAchat = prixAchat;
		this.prixVente=prixVente;
		this.demande= demande;
	}
	
		/**
		 * Verifie si le code envoyé correspond au code (identifiant) de l'element.
		 * 
		 * @param code
		 *  Le code de la chaine que l'on cherche.
		 *  
		 * @return 
		 *  Vrai si le code correspond, faux sinon.
		 */
	public boolean CodeCorrect(String code) {
		if(this.codeElement.equals(code)) {
			return true;
		}
		return false;
	}
	
	
	public String toString() {
		String retour = "";
		retour += this.codeElement + " - " + this.nom;
		if(this.prixAchat > 0) {
			retour += "\nprix d'achat : " + this.prixAchat;
		}
		else {
			retour += "\nprix d'achat : NA";
		}

		if(this.prixVente > 0) {
			retour += "\nprix de vente : " +this.prixVente + "\n";
		}
		else {
			retour += "\nprix de vente : NA\n";
		}
		retour += this.stock + "\n\n";
		return retour;
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

	public double getDemande() {
		return demande;
	}
	
	public Stockage getStock() {
		return this.stock;
	}
	
	
	
	
}
