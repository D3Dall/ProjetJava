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
	

	public ArrayList<Personnel> getListePersonnel() {
			return listePersonnel;
		}

	public void Prevision() {
		ArrayList<Personnel_Qualifie> persQ = new ArrayList<Personnel_Qualifie>();
		ArrayList<Personnel_Non_Qualifie> persNQ = new ArrayList<Personnel_Non_Qualifie>();
		
		for(Personnel pq : this.listePersonnel) {
			if(pq.getClass().getSimpleName().equals("Personnel_Qualifie")) {
				persQ.add((Personnel_Qualifie) pq);
			}else {
				persNQ.add((Personnel_Non_Qualifie) pq);
			}	
		}
		
		int temps = 0;
		System.out.println("COMMENCEMENT");
		
		for(ChaineProduction cp : this.listeChaineProduction) {
			cp.effacerPrevision();
		}
		ArrayList<ChaineProduction> chaineProductionActive = this.chainesProductionActive();
		while(chaineProductionActive.size()>0 && temps<=60) {
			System.out.println("TEMPS : "+temps);
			for (ChaineProduction c: chaineProductionActive) {
				if (!c.estOccupe(temps)) {
					System.out.println("La chaine " + c.getNom() + " n'est pas occupé : recherche de personnel pour lancement" );
					ArrayList<Personnel_Non_Qualifie> listePNQ = new ArrayList<Personnel_Non_Qualifie>();
					ArrayList<Personnel_Qualifie> listePQ = new ArrayList<Personnel_Qualifie>();
					int  i = 0;
					System.out.println("Demande de " + c.getNombre_Personne_Non_Qualifiee() + "Personne non qualifié");
					while (listePNQ.size()<c.getNombre_Personne_Non_Qualifiee() && i < persNQ.size()) {
						if(persNQ.get(i).estdisponible() && c.getTemps()+persNQ.get(i).getTempsTravail()<persNQ.get(i).getTempsTravailMAX()) {
							System.out.println("Personnel non qualifié : " +persNQ.get(i) + " disponible : ajout");
							listePNQ.add(persNQ.get(i));
							System.out.println("Il reste " + (c.getNombre_Personne_Non_Qualifiee()-listePNQ.size()) + " personnes non qualifie a trouver");
						}
						i++;
					}
					i=0;
					System.out.println("Demande de " + c.getNombre_Personne_Qualifiee() + "Personne qualifié");
					while (listePQ.size()<c.getNombre_Personne_Qualifiee() && i < persQ.size()) {
						if(persQ.get(i).estdisponible() && c.getTemps()+persQ.get(i).getTempsTravail()<persQ.get(i).getTempsTravailMAX()) {
							System.out.println("Personnel qualifié : " +persQ.get(i) + " disponible : ajout");
							listePQ.add(persQ.get(i));
							System.out.println("Il reste " + (c.getNombre_Personne_Qualifiee()-listePQ.size()) + " personnes non qualifie a trouver");
						}
						i++;
					}
					if(listePNQ.size()==c.getNombre_Personne_Non_Qualifiee() && listePQ.size()==c.getNombre_Personne_Qualifiee()) {
						System.out.println("Lancement de la chaine de production");
						ArrayList<Personnel> listePersonnelPourChaine = new ArrayList<Personnel>();
						listePersonnelPourChaine.addAll(listePQ);
						listePersonnelPourChaine.addAll(listePNQ);
						c.produire(temps, listePersonnelPourChaine);
					}else {
						System.out.println("pas assez de personnel");
					}
				}
			}
			chaineProductionActive=this.chainesProductionActive();
			temps++;
		}
		
		if (temps<60) {
			if(chaineProductionActive.size()==0) {
				System.out.println("PAS ASSEZ DE STOCK OU AUCUNE CHAINE ACTIVE");
			}else {
				System.out.println("PAS ASSEZ DE PERSONNEL");
			}
		}
		System.out.println("fin");
	}
}
