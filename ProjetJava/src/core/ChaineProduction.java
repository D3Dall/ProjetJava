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
	private HashMap<Element, Double> entree;
        /**
         * Dictionnaire des Elements en SORTIE
         * Cela correspond a la quantitee des elements qui seront produits pour une production
         */
	private HashMap<Element, Double> sortie;
	
        /**
         * Liste des productions faites par la chaine de production
         */
	private ArrayList<Production> listeproduction;
	
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
	public ChaineProduction(String codeChaineProduction, String nom, int niveauActivitee, int temps) {
		this.codeChaineProduction = codeChaineProduction;
		this.nom = nom;
		this.niveauActivitee = niveauActivitee;
		this.temps = temps;
		this.entree = new HashMap<Element, Double> ();
		this.sortie = new HashMap<Element, Double> ();
		this.listeproduction = new ArrayList<Production>();
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
	public ChaineProduction(String codeChaineProduction, String nom, int temps) {
		this(codeChaineProduction, nom, 0, temps);
	}
        
        /**
         * Ajoute un element dans le dictionnaire de production en ENTREE
         * @param elem
         *  L'element que l'on veut ajouter au dictionnaire
         * @param quantitee 
         *  La quantite associé à l'element que l'on ajoute
         */
	public void ajouterElementPourDictionnaireDeProductionEnEntree(Element elem, double quantitee) {
		this.entree.put(elem, quantitee);
	}
        
        /**
         * Ajoute un element dans le dictionnaire de production en SORTIE
         * @param elem
         *  L'element que l'on veut ajouter au dictionnaire
         * @param quantitee 
         *  La quantite associé à l'element que l'on ajoute
         */
	public void ajouterElementPourDictionnaireDeProductionEnSortie(Element elem, double quantitee) {
		this.sortie.put(elem, quantitee);
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
	public void modifierElementPourDictionnaireDeProductionEnEntree(Element elem, double quantitee) {
		this.ajouterElementPourDictionnaireDeProductionEnEntree(elem, quantitee);
	}
        
        /**
         * Modifie la quantite d'un element dans le dictionnaire de production en SORTIE
         * @param elem
         *  L'element concerne par la modification de quantite
         * @param quantitee 
         *  La nouvelle quantite que l'on veut integrer
         */
	public void modifierElementPourDictionnaireDeProductionEnSortie(Element elem, double quantitee) {
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
            if(code!=null){
                if(this.codeChaineProduction.contains(code)){
                    return true;
                }
            }else{
                if (nom!=null){
                    if(this.nom.contains(nom)){
                        if(temps!=0){
                            if(this.temps==temps){
                                return true;
                            }
                        }else{
                            return true;
                        }
                    }else{
                        if(this.temps==temps){
                            return true;
                        }
                    }
                }
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
		for (Element e : this.entree.keySet()) {
			src += e + "Quantitee dont on a besoin " + this.entree.get(e);
		}
		src += "\nElement en Sortie :\n";
		for(Element e : this.sortie.keySet()) {
			src += e + "Quantitee dont qui en ressort " + this.sortie.get(e) + ";\n";
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
			for (Element e : this.entree.keySet()) {
				e.getStock().ajouter(p.getNiveauProduction()*this.entree.get(e));
			}
			for(Element e : this.sortie.keySet()) {
				e.getStock().retirer(p.getNiveauProduction()*this.sortie.get(e));
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
		}
		for (Element e : this.entree.keySet()) {
			if(e.getStock().getStock()-this.entree.get(e)*this.niveauActivitee<0) {
				return false;
			}
		}
		return true;
	}	
	
        /**
         * Consomme la quantitee des elements marquee dans le dictionnaire des ENTREE.
         * Produit la quantitee des element marquee dans le dictionnaire de SORTIE
         * Crée une Production et l'ajoute à la liste des productions
         */
	public void produire(){
		for (Element e : this.entree.keySet()) {
			e.getStock().retirer(this.entree.get(e)*this.niveauActivitee);
		}
		for (Element e : this.sortie.keySet()) {
			e.getStock().ajouter(this.sortie.get(e)*this.niveauActivitee);
		}
		Calendar cal = this.getFinDeProduction();
		cal.add(Calendar.MINUTE, this.temps);
		this.listeproduction.add(new Production(this.niveauActivitee, cal));		
	}
	
        /**
         * Trouve la date de la derniere production de la chaine de production.
         * @return la date de la derniere production
         */
	public Calendar getFinDeProduction() {
		Calendar cTemp = new GregorianCalendar();
		for(Production p : this.listeproduction) {
			if(p.getDateProduction().after(cTemp)) {
				cTemp = p.getDateProduction();
			}
		}
		return cTemp;
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

    public HashMap<Element, Double> getEntree() {
        return entree;
    }

    public HashMap<Element, Double> getSortie() {
        return sortie;
    }

    public ArrayList<Production> getListeproduction() {
        return listeproduction;
    }
        
        
        
        
}
