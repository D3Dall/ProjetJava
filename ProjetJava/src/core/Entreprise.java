package core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class Entreprise {

	private ArrayList<Element> listeElements;
        /**
         * liste des chaines de production existant dans l'enteprise
         */
	private ArrayList<ChaineProduction> listeChaineProduction;
        /**
         * liste du personnel de l'entreprise
         */
	private ArrayList<Personnel> listePersonnel;
        /**
         * premiere entreprise cree
         */
	public static Entreprise entreprise;
	
        /**
         * Instancie une entreprise et si c'est la premiere l'indique en static
         */
	public Entreprise() {
		this.listeElements = new ArrayList<Element> ();
		this.listeChaineProduction = new ArrayList<ChaineProduction> ();
		this.listePersonnel = new ArrayList<Personnel> ();
		if(Entreprise.entreprise == null) {
			Entreprise.entreprise = this;
		}
		
	}
	
        /**
         * Ajoute un element dans l'entreprise
         * @param elem
         *  element que l'on souhaite ajouter
         */
	public void ajouterElementDansEntreprise(Element elem) {
		this.listeElements.add(elem);
	}
        
        /**
         * Ajoute une chaine de production dans l'entreprise
         * @param chaineproduction 
         *  chaine de production que l'on souhaite ajouter
         */
	public void ajouterChaineProductionDansEntreprise(ChaineProduction chaineproduction) {
		this.listeChaineProduction.add(chaineproduction);
	}
        
        /**
         * Ajoute un personnel a l'entreprise
         * @param personnel
         *  personnel que l'on souhaite ajouter
         */
	public void ajouterPersonnelDansEntrepise(Personnel personnel) {
		this.listePersonnel.add(personnel);
	}
	
        /**
         * Retire un element dans l'entreprise
         * @param elem
         *  element que l'on souhaite retirer
         */
	public void retirerElementDansEntreprise(Element elem) {
		this.listeElements.remove(elem);
	}
	
        /**
         * Retire une chaine de production dans l'entreprise
         * @param chaineproduction 
         *  chaine de production que l'on souhaite retirer
         */
    public void retirerChaineProductionDansEntreprise(ChaineProduction chaineproduction) {
		this.listeChaineProduction.remove(chaineproduction);
	}
	
        /**
         * Retire un personnel a l'entreprise
         * @param personnel
         *  personnel que l'on souhaite retirer
         */
    public void retirerPersonnelDansEntrepise(Personnel personnel) {
		this.listePersonnel.remove(personnel);
	}	

        /**
         * Recherche un element dans la liste des elements de l'entreprise
         * @param code
         *  identifiant de l'element que l'on recherche
         * @return l'element si on le trouve, sinon null
         */
	public Element rechercherElement(String code) {
		for(Element e : this.listeElements) {
			if(e.CodeCorrect(code)) {
				return e;
			}
		}
		return null;
	}

        /**
         * 
         * @return la liste des chaines de production de l'entreprise 
         */
	public ArrayList<ChaineProduction> getListeChaineProduction() {
		return listeChaineProduction;
	}
	
        /**
         * 
         * @return la liste des elements de l'entreprise 
         */
	public ArrayList<Element> getListeElement() {
		return this.listeElements;
	}
	
        /**
         * Recherche une chaine de production dans l'entreprise avec un code et/ou un nom et/ou un temps
         * @param code
         *  Identifiant de la chaine de production
         * @param nom
         *  nom de la chaine de production
         * @param temps
         *  temps de production de la chaine de production
         * @return la liste de correspondance des chaines de production en fonction des parametres
         */
	public ArrayList<ChaineProduction> chercherChaineDeProduction(String code, String nom, int temps) {
		ArrayList<ChaineProduction> TempCP = new ArrayList<ChaineProduction>();
		for(ChaineProduction cp : this.listeChaineProduction) {
			if(cp.isChaineDeProduction(code, nom, temps)) {
				TempCP.add(cp);
			}
		}
		return TempCP;
	}
	
        /**
         * Construit la liste des chaines de production qui sont actives (peuvent produire)
         * @return la liste des chaines de production qui sont active (niveau d'activite>0 et stock suffisant)
         */
	public ArrayList<ChaineProduction> chainesProductionActive(){
		ArrayList<ChaineProduction> chainesProductionActive = new ArrayList<ChaineProduction>();
		for(ChaineProduction cp: this.listeChaineProduction) {
			if (cp.peutProduire()) {
				chainesProductionActive.add(cp);
			}
		}
		return chainesProductionActive;
	}
	
        /**
         * Construit la liste des chaines de production qui sont actives en fonction des chaines de production envoyees(peuvent produire)
         * @param chaineproduction
         *  liste de chaine de production
         * @return la liste des chaines de production qui sont active (niveau d'activite>0 et stock suffisant)
         */
    public ArrayList<ChaineProduction> chainesProductionActive(ArrayList<ChaineProduction> chaineproduction){
		ArrayList<ChaineProduction> chainesProductionActive = new ArrayList<ChaineProduction>();
		for(ChaineProduction cp: chaineproduction) {
			if (cp.peutProduire()) {
				chainesProductionActive.add(cp);
			}
		}
		return chainesProductionActive;
	}
	
        /**
         * 
         */
	public void Prevision() {
		ArrayList<ChaineProduction> chainesProductionActive = this.chainesProductionActive();
		while(chainesProductionActive.size()>0) {
			Iterator it = chainesProductionActive.iterator();
			ChaineProduction cpfirst = (ChaineProduction)it.next();
			Calendar calmin = cpfirst.getFinDeProduction();
			calmin.add(Calendar.MINUTE, cpfirst.getTemps());
			while(it.hasNext()) {
				ChaineProduction cp = (ChaineProduction) it.next();
				Calendar cal = cp.getFinDeProduction();
				cal.add(Calendar.MINUTE, cp.getTemps());
				if(calmin.after(cal)) {
					calmin=cal;
					cpfirst=cp;
				}
			}
			cpfirst.produire();
			chainesProductionActive=this.chainesProductionActive();			
		}	
	}
	
	
	
	
}
