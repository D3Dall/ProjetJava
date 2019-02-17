package core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class ChaineProduction {
        /**
         * Identifiant de la chaine de production (UNIQUE)
         */
	private String codeChaineProduction;
        /**
         * Nom de la chaine de production
         */
	private String nom;
        /**
         * Niveau d'activite de la chaine de production.
         * Cela correspond a la performance de la chaine de production
         */
	private int niveauActivitee;
        /**
         * temps que met la chaine de production pour produire
         */
	private int temps;
	
        /**
         * Dictionnaire des Elements en ENTREE
         * Cela correspond a la quantitee des elements qui seront consommes pour une production
         */
	private ArrayList<Couple<Element, Float>> entree;
        /**
         * Dictionnaire des Elements en SORTIE
         * Cela correspond a la quantitee des elements qui seront produits pour une production
         */
	private ArrayList<Couple<Element, Float>> sortie;
	
        /**
         * Liste des productions faites par la chaine de production
         */
	private ArrayList<Production> listeproduction;
	
	/**
	 * Nombre de personne non qualifiee necessaire
	 */
	private int nombre_Personne_Non_Qualifiee;
	
	/**
	 * Nombre de personne qualifiee necessaire
	 */
	private int nombre_Personne_Qualifiee;
	
	
        /**
         * Instancie une chaine de production avec un niveau d'activite
         * @param codeChaineProduction
         *  Le code identifiant la chaine de production
         * @param nom
         *  Le nom de la chaine de production
         * @param niveauActivitee
         *  Le niveau d'activite de la production
         * @param temps 
         *  Le temps necesaire à la production
         */
	public ChaineProduction(String codeChaineProduction, String nom, int niveauActivitee, int temps, int nbrPersNQ, int nbrPersQ) {
		this.codeChaineProduction = codeChaineProduction;
		this.nom = nom;
		this.niveauActivitee = niveauActivitee;
		this.temps = temps;
		this.entree = new ArrayList<Couple<Element, Float>> ();
		this.sortie = new ArrayList<Couple<Element, Float>> ();
		this.listeproduction = new ArrayList<Production>();
		this.nombre_Personne_Non_Qualifiee =nbrPersNQ;
		this.nombre_Personne_Qualifiee = nbrPersQ;
	}
        
        /**
         * Instancie une chaine de production sans un niveau d'activite
         * (niveauActivitee = 0)
         * @param codeChaineProduction
         * Le code identifiant la chaine de production
         * @param nom
         * Le nom de la chaine de production
         * @param temps
         * Le temps necessaire à la production
         */
	public ChaineProduction(String codeChaineProduction, String nom, int temps, int nbrPersNQ, int nbrPersQ) {
		this(codeChaineProduction, nom, 0, temps, nbrPersNQ, nbrPersQ);
	}
        
        /**
         * Ajoute un element dans le dictionnaire de production en ENTREE
         * @param elem
         *  L'element que l'on veut ajouter au dictionnaire
         * @param quantitee 
         *  La quantite associé à l'element que l'on ajoute
         */
	public void ajouterElementPourDictionnaireDeProductionEnEntree(Element elem, float quantitee) {
		this.entree.add(new Couple(elem, quantitee));
	}
        
        /**
         * Ajoute un element dans le dictionnaire de production en SORTIE
         * @param elem
         *  L'element que l'on veut ajouter au dictionnaire
         * @param quantitee 
         *  La quantite associé à l'element que l'on ajoute
         */
	public void ajouterElementPourDictionnaireDeProductionEnSortie(Element elem, float quantitee) {
		this.sortie.add(new Couple(elem, quantitee));
	}
        
        /**
         * Retire un element dans le dictionnaire de production en ENTREE
         * @param elem
         *  L'element que l'on veut retirer au dictionnaire
         */
	public void retirerElementPourDictionnaireDeProductionEnEntree(Element elem) {
		this.entree.remove(elem);
	}
        
        /**
         * Retire un element dans le dictionnaire de production en SORTIE
         * @param elem
         *  L'element que l'on veut retirer au dictionnaire
         */
	public void retirerElementPourDictionnaireDeProductionEnSortie(ProduitsFinis elem) {
		this.entree.remove(elem);
	}
        
        /**
         * Modifie la quantite d'un element dans le dictionnaire de production en ENTREE
         * @param elem
         *  L'element concerne par la modification de quantite
         * @param quantitee 
         *  La nouvelle quantite que l'on veut integrer
         */
	public void modifierElementPourDictionnaireDeProductionEnEntree(Element elem, float quantitee) {
		this.ajouterElementPourDictionnaireDeProductionEnEntree(elem, quantitee);
	}
        
        /**
         * Modifie la quantite d'un element dans le dictionnaire de production en SORTIE
         * @param elem
         *  L'element concerne par la modification de quantite
         * @param quantitee 
         *  La nouvelle quantite que l'on veut integrer
         */
	public void modifierElementPourDictionnaireDeProductionEnSortie(Element elem, float quantitee) {
		this.ajouterElementPourDictionnaireDeProductionEnSortie(elem, quantitee);
	}
	
	
        /**
         * Retourne Vrai ou Faux selon la correspondance entre les paramètres non null et différent de 0 envoyé
         * et la chaine de production. Les paramètres null ou 0 ne sont pas pris en compte.
         * @param code
         *  code de la probable chaine de production
         * @param nom
         *  nom de la probable chaine de production
         * @param temps
         *  temps de la probable chaine de production
         * @return vrai s'il y a correspondance, faux sinon
         */
    public boolean isChaineDeProduction(String code, String nom, int temps) {
        if(this.codeChaineProduction.contains(code) && this.nom.contains(nom) && (temps==this.temps || temps==0)){
        	return true; 
        }
        return false;
	}
        
        /**
         * Decrit l'objet sous forme d'une chaine de caracteres
         * @return l'objet sous forme d'une chaine de caracteres
         */
	public String toString() {
		String src = this.codeChaineProduction + " - " + this.nom + " - " + this.temps;
		src += "\nElement en Entr�e :\n";
		for (Couple<Element, Float> c : this.entree) {
			src += c.getObjeta() + "Quantitee dont on a besoin " + c.getObjetb();
		}
		src += "\nElement en Sortie :\n";
		for(Couple<Element, Float> c : this.sortie) {
			src += c.getObjeta() + "Quantitee dont qui en ressort " + c.getObjetb() + ";\n";
		}
		src += "\n\n\n";
		return src;
		
	}
	
        /**
         * Efface les previsions. Efface donc tout les productions de la liste des productions.
         * Reintegre les stocks à l'etat originel.
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
         * Voit si la chaine de production peut produire en fonction des stocks des elements
         * se trouvant dans ses dictionnaires en ENTREE.
         * @return vrai si les stocks permettent la production, faux sinon.
         */
	public boolean peutProduire() {
		if(this.niveauActivitee==0) {
			return false;
		}else {
			for (Couple<Element, Float> c : this.entree) {
				if(c.getObjeta().getStock().getStock()<c.getObjetb()) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	/**
	 * 
	 */
	public boolean estOccupe(int temps) {
		if(this.getFinDeProduction()==-1) {
			return false;
		}else if(this.getFinDeProduction()+this.temps>temps) {
			return true;
		}else if(this.getFinDeProduction()+this.temps==temps) {
			this.listeproduction.get(this.listeproduction.size()-1).liberer();
		}
		return false;
	}
	
        /**
         * Consomme la quantitee des elements marquee dans le dictionnaire des ENTREE.
         * Produit la quantitee des element marquee dans le dictionnaire de SORTIE
         * Crée une Production et l'ajoute à la liste des productions
         */
	public void produire(int temps, ArrayList<Personnel> listePersonnel){
		for (Couple<Element, Float> c : this.entree) {
			c.getObjeta().getStock().retirer(c.getObjetb()*this.niveauActivitee);
		}
		for (Couple<Element, Float> c : this.sortie) {
			c.getObjeta().getStock().ajouter(c.getObjetb()*this.niveauActivitee);
		}
		for (Personnel p : listePersonnel) {
			p.rendreIndisponible();
			p.ajouterHeureTravail(this.temps);
		}
		this.listeproduction.add(new Production(this.niveauActivitee, temps, listePersonnel));		
	}
	
    /**
     * Trouve la date de la derniere production de la chaine de production.
     * @return la date de la derniere production
     */
	public int getFinDeProduction() {
		if(this.listeproduction.size()==0) {
			System.out.println("premi�re production");
			return -1;
		}
		System.out.println("Derni�re production sur " + this.listeproduction.get(this.listeproduction.size()-1).getHeure());
		return this.listeproduction.get(this.listeproduction.size()-1).getHeure();
	}
	
    /**
     * Attribue un nouveau niveau d'activite pour la chaine de production.
     * @param niveauActivitee
     *  Nouveau niveau d'activite.
     */
	public void attribuerNiveauActivite(int niveauActivitee) {
		this.niveauActivitee= niveauActivitee;
	}	
	
    /**
     * @return Le temps d'une production de la chaine de production
     */
	public int getTemps() {
		return this.temps;
	}

    public String getCodeChaineProduction() {
        return codeChaineProduction;
    }

    public String getNom() {
        return nom;
    }

    public int getNiveauActivitee() {
        return niveauActivitee;
    }

    public ArrayList<Couple<Element, Float>> getEntree() {
        return entree;
    }

    public ArrayList<Couple<Element, Float>> getSortie() {
        return sortie;
    }

    public ArrayList<Production> getListeproduction() {
        return listeproduction;
    }
    
    public ArrayList<Element> getElementsEnEntree(){
    	ArrayList<Element> listeElem = new ArrayList<Element>();
    	for (Couple<Element, Float> c : this.entree) {
    		listeElem.add(c.getObjeta());
    	}
    	return listeElem;
    }
    public ArrayList<Element> getElementsEnSortie(){
    	ArrayList<Element> listeElem = new ArrayList<Element>();
    	for (Couple<Element, Float> c : this.sortie) {
    		listeElem.add(c.getObjeta());
    	}
    	return listeElem;
    }

	public int getNombre_Personne_Non_Qualifiee() {
		return nombre_Personne_Non_Qualifiee;
	}

	public int getNombre_Personne_Qualifiee() {
		return nombre_Personne_Qualifiee;
	}
        
        
        
        
}
