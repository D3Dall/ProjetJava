package core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class ChaineProduction {
	
        /**
         * L'identifiant de la chaine de production (UNIQUE)
         */
	private String codeChaineProduction;
	
        /**
         * Le nom de la chaine de production
         */
	private String nom;
	
        /**
         * Le niveau d'activite de la chaine de production.
         * 
         */
	private int niveauActivitee;
	
        /**
         * temps que met la chaine de production pour produire
         */
	private int temps;
	
        /**
         * Liste des couples des Elements en ENTREE
         * chaque couple correspond a la quantitee des elements qui seront consommes pour une production
         */
	private ArrayList<Couple<Element, Float>> entree;
	
        /**
         * Liste des couples des Elements en SORTIE
         * chaque couple correspond a la quantitee des elements qui seront consommés pour une production au niveau d'activité 1
         */
	private ArrayList<Couple<Element, Float>> sortie;
		
		/**
		 * Liste des productions faites par l'entreprise
		 */
	private ArrayList<Production> listeproduction;
	
		/**
		 * Nombre de personne(s) non qualifie(e)(s) necessaire(s)
		 */
	private int nombre_Personne_Non_Qualifiee;
	
		/**
		 * Nombre de personne(s) qualifie(e)(s) necessaire(s)
		 */
	private int nombre_Personne_Qualifiee;
	
	
        /**
         * Crée une chaine de production avec une liste de production vide
         * 
         * @param codeChaineProduction 
         * L'identifiant de la chaine de production (UNIQUE).
         * 
         * @param nom 
         * Le nom de la chaine de production.
         * 
         * @param niveauActivitee 
         * Le niveau d'activité de la chaine de production.
         * 
         * @param temps 
         * Le temps que met la chaine pour faire une production.
         * 
         * @param nbrPersNQ 
         * Le nombre de personne(s) non qualifié(e)(s) necessaire(s).
         * 
         * @param nbrPersQ 
         * Le nombre de personne(s) qualifié(e)(s) necessaire(s).
         * 
         */
	public ChaineProduction(String codeChaineProduction, String nom, int niveauActivitee, int temps, int nbrPersNQ, int nbrPersQ) {
		this.codeChaineProduction = codeChaineProduction;
		this.nom = nom;
		this.niveauActivitee = 1;
		this.temps = temps;
		this.entree = new ArrayList<Couple<Element, Float>> ();
		this.sortie = new ArrayList<Couple<Element, Float>> ();
		this.listeproduction = new ArrayList<Production>();
		this.nombre_Personne_Non_Qualifiee =nbrPersNQ;
		this.nombre_Personne_Qualifiee = nbrPersQ;
	}
        
        /**
         * Crée une chaine de production avec une liste de production vide.
         * 
         * @param codeChaineProduction 
         * L'identifiant de la chaine de production (UNIQUE).
         * 
         * @param nom 
         * Le nom de la chaine de production.
         * 
         * @param temps
         * Le temps que met la chaine pour faire une production.
         * 
         * @param nbrPersNQ 
         * Le nombre de personne(s) non qualifié(e)(s) necessaire(s).
         * 
         * @param nbrPersQ 
         * Le nombre de personne(s) qualifié(e)(s) necessaire(s).
         */
	public ChaineProduction(String codeChaineProduction, String nom, int temps, int nbrPersNQ, int nbrPersQ) {
		this(codeChaineProduction, nom, 0, temps, nbrPersNQ, nbrPersQ);
	}
        
        /**
         * Ajoute un element dans le dictionnaire de production en ENTREE (entree).
         * 
         * @param elem 
         * L'element à ajouter au dictionnaire.
         *  
         * @param quantitee 
         * La quantite associée.
         */
	public void ajouterElementPourDictionnaireDeProductionEnEntree(Element elem, float quantitee) {
		this.entree.add(new Couple(elem, quantitee));
	}
        
        /**
         * Ajoute un element dans le dictionnaire de production en SORTIE.
         * 
         * @param elem 
         * L'element à ajouter au dictionnaire.
         * 
         * @param quantitee 
         * La quantite associée.
         * 
         */
	public void ajouterElementPourDictionnaireDeProductionEnSortie(Element elem, float quantitee) {
		this.sortie.add(new Couple(elem, quantitee));
	}
        
        /**
         * Retire un element dans le dictionnaire de production en ENTREE.
         * 
         * @param elem
         *  L'element que l'on veut retirer au dictionnaire.
         */
	public void retirerElementPourDictionnaireDeProductionEnEntree(Element elem) {
		this.entree.remove(elem);
	}
        
        /**
         * Retire un element dans le dictionnaire de production en SORTIE.
         * 
         * @param elem
         *  L'element que l'on veut retirer au dictionnaire.
         *  
         */
	public void retirerElementPourDictionnaireDeProductionEnSortie(ProduitsFinis elem) {
		this.entree.remove(elem);
	}
	
	
        /**
         * Retourne Vrai ou Faux selon la correspondance entre les paramètres envoyés et les attribut de la chaine de production.
         *  Les paramètres null ou 0 ne sont pas pris en compte dans la correspondance.
         *  
         * @param code
         * Le code de la probable chaine de production.
         * 
         * @param nom
         *  Le nom de la probable chaine de production.
         *  
         * @param temps
         *  Le temps de la probable chaine de production.
         *  
         * @return vrai s'il y a correspondance, faux sinon.
         */
    public boolean isChaineDeProduction(String code, String nom, int temps) {
        if(this.codeChaineProduction.contains(code) && this.nom.contains(nom) && (temps==this.temps || temps==0)){
        	return true; 
        }
        return false;
	}
        
        /**
         * Decrit l'objet sous forme d'une chaine de caracteres.
         * 
         * @return l'objet sous forme d'une chaine de caracteres
         */
	public String toString() {
		String src = this.codeChaineProduction + " - " + this.nom + " - " + this.temps;
		src += "\nElement en Entrée :\n";
		for (Couple<Element, Float> c : this.entree) {
			src += c.getObjeta().getCodeElement() + " " + c.getObjeta().getNom() + " Quantitée dont on a besoin " + c.getObjetb();
		}
		src += "\nElement en Sortie :\n";
		for(Couple<Element, Float> c : this.sortie) {
			src += c.getObjeta().getCodeElement() + " " + c.getObjeta().getNom() + " Quantitée qui en ressort " + c.getObjetb() + ";\n";
		}
		src += "\n\n\n";
		return src;
		
	}
	
        /**
         * Efface les previsions.
         * Efface donc toutes les productions de la liste des productions.
         * 
         * Reintègre les stocks à l'etat de départ.
         */
	public void effacerPrevision() {
		for (Production p : this.listeproduction) {
			for (Couple<Element, Float> c : this.entree) {
				c.getObjeta().getStock().ajouter(p.getNiveauProduction()*c.getObjetb());
			}
			for(Couple<Element, Float> c : this.sortie) {
				c.getObjeta().getStock().retirer(p.getNiveauProduction()*c.getObjetb());
			}
		}
		this.listeproduction.clear();
	}
	
        /**
         * Voit si la chaine de production et en activitée ou non.
         * 
         * @return
         * vrai si les stocks permettent la production, faux sinon.
         * 
         */
	public boolean estActive() {
		if(this.niveauActivitee==0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Voit si la chaine de production à le temps de produire avant la fin de la semaine de production (60h)
	 * 
	 * @param temps
	 * L'heure présente
	 * 
	 * @return
	 * Vrai si la chaine à le temps de produire, faux sinon.
	 */
	public boolean aLeTemps(int temps) {
		if(this.temps+temps>=60) {
			return false;
		}
		return true;
	}
	
	/**
	 * Voit si le stock est suffisant pour la production de la chaine de production
	 * 
	 * @return
	 * Vrai si pour tout élément dans le dictionnaire en entree, le stock est suffisant, faux sinon.
	 */
	public boolean stockSuffisant() {
		for (Couple<Element, Float> c : this.entree) {
			if(c.getObjeta().getStock().getStock()<c.getObjetb()) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Voit si la chaine de production est occupé. (Si elle est en train de produire)
	 * 
	 * @param temps
	 * L'heure de la production
	 * 
	 * @return
	 * Vrai si elle est en train de produire, faux sinon
	 * 
	 */
	public boolean estOccupe(int temps) {
		if(this.getFinDeProduction()==-1) {
			return false;
		}else if(this.getFinDeProduction()+this.temps>temps) {
			return true;
		}
		return false;
	}
	
        /**
         * 
         * Consomme toute les quantités des éléments se trouvant dans le dictionnaire en entrée.
         * Créé une Production et l'ajoute à la liste des productions
         * 
         * @param listePersonnel
         * La liste du pesonnel dont on a besoin pour démarrer la chaine de production
         * 
         */
	public void produire(int temps, ArrayList<Personnel> listePersonnel){
		for (Couple<Element, Float> c : this.entree) {
			c.getObjeta().getStock().retirer(c.getObjetb()*this.niveauActivitee);
		}
		for (Personnel p : listePersonnel) {
			p.rendreIndisponible();
			p.ajouterHeureTravail(this.temps);
		}
		this.listeproduction.add(new Production(this.niveauActivitee, temps, listePersonnel));		
	}
	
		/**
		 * Detecte la fin d'activité de la production en cours de la chaine de production.
		 * Si la dernière production en cours se termine, alors on libère les personnels occupés à cette chaine
		 *  
		 * @param temps
		 * L'heure de la production
		 */
	public void ActualiserProduction(int temps) {
		System.out.println("liblibe" + this.nom);
		if(this.getFinDeProduction()+this.temps==temps && this.getFinDeProduction()!=-1) {
			System.out.println("liberation");
			this.listeproduction.get(this.listeproduction.size()-1).liberer();
			for (Couple<Element, Float> c : this.sortie) {
				c.getObjeta().getStock().ajouter(c.getObjetb()*this.niveauActivitee);
			}
		}
	}
	
	
    /**
     * Trouve l'heure du début de la derniere production de la chaine de production. -1 s'il n'y a pas eu de production.
     * 
     * @return 
     * La date de la derniere production. -1 s'il n'y a pas eu de production.
     */
	public int getFinDeProduction() {
		if(this.listeproduction.size()==0) {
			System.out.println("première production");
			return -1;
		}
		System.out.println("Dernière production sur " + this.listeproduction.get(this.listeproduction.size()-1).getHeure());
		return this.listeproduction.get(this.listeproduction.size()-1).getHeure();
	}
	
    /**
     * Attribue un nouveau niveau d'activite pour la chaine de production.
     * 
     * @param niveauActivitee
     *  Nouveau niveau d'activite.
     *  
     */
	public void attribuerNiveauActivite(int niveauActivitee) {
		this.niveauActivitee= niveauActivitee;
	}	
    
	
		/**
		 * Renvoi la liste des elements présents dans le dictionnaire en entrée.
		 * 
		 * @return
		 * La liste des elements présents dans le dictionnaire en entrée.
		 */
    public ArrayList<Element> getElementsEnEntree(){
    	ArrayList<Element> listeElem = new ArrayList<Element>();
    	for (Couple<Element, Float> c : this.entree) {
    		listeElem.add(c.getObjeta());
    	}
    	return listeElem;
    }
    
	    /**
		 * Renvoi la liste des elements présents dans le dictionnaire en sortie.
		 * 
		 * @return
		 * La liste des elements présents dans le dictionnaire en sortie.
		 */
    public ArrayList<Element> getElementsEnSortie(){
    	ArrayList<Element> listeElem = new ArrayList<Element>();
    	for (Couple<Element, Float> c : this.sortie) {
    		listeElem.add(c.getObjeta());
    	}
    	return listeElem;
    }

    
    
    
    //Accesseurs
    
	public int getNombre_Personne_Non_Qualifiee() {
		return nombre_Personne_Non_Qualifiee;
	}

	public int getNombre_Personne_Qualifiee() {
		return nombre_Personne_Qualifiee;
	}
        
	public int getTemps() {
		return this.temps;
	}

    public String getCodeChaineProduction() {
        return this.codeChaineProduction;
    }

    public String getNom() {
        return this.nom;
    }

    public int getNiveauActivitee() {
        return this.niveauActivitee;
    }

    public ArrayList<Couple<Element, Float>> getEntree() {
        return this.entree;
    }

    public ArrayList<Couple<Element, Float>> getSortie() {
        return this.sortie;
    }

    public ArrayList<Production> getListeproduction() {
        return this.listeproduction;
    }  
        
        
}
