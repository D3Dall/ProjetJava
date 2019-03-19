package core;

import java.util.ArrayList;

import erreurs.ChainesProductionInactifException;
import erreurs.EntrepriseManquanteException;
import erreurs.ManqueDePersonnelsException;
import erreurs.ManqueDeRessourceException;
import erreurs.ManqueDeStocksException;

public class Prevision {
	
	/**
	 * Fabrique la liste des chaines de production actives. (On un niveau d'activit� sup � 0) selon
	 * l'entreprise qu'on lui envoie.
	 * 
	 * @param entreprise
	 * Entreprise avec laquelle on travail.
	 * 
	 * @return
	 * La liste des chaines de production actives.
	 */
	private static ArrayList<ChaineProduction> chainesProductionActive(Entreprise entreprise){
		ArrayList<ChaineProduction> chainesProductionActive = new ArrayList<ChaineProduction>();
		for(ChaineProduction cp: entreprise.getListeChaineProduction()) {
			if (cp.estActive()) {
				chainesProductionActive.add(cp);
			}
		}
		return chainesProductionActive;
	}
	
	
	
	/**
	 * Efface toute les pr�vision faite sur une entreprise.
	 * 
	 * @param entreprise
	 * Entreprise dans laquelle on travail.
	 */
	private static void effacerPrevision(Entreprise entreprise) {
		entreprise.remettreA0();
	}
	
	
	/**
	 * Separe la liste de Personnel en deux listes :
	 * - La liste des personnels non qualifi�,
	 * - La liste des personnels qualifi�.
	 * 
	 * @param persQ
	 * La liste des personnels qualifi�.
	 * 
	 * @param persNQ
	 * La liste des personnels non qualifi�.
	 * 
	 * @param pers
	 * La liste des personnels de l'entreprise.
	 * 
	 */
	private static void separationPersonnelParType(ArrayList<Personnel_Qualifie> persQ, ArrayList<Personnel_Non_Qualifie> persNQ, ArrayList<Personnel> pers) {
		for(Personnel pq : pers) {
			if(pq.getClass().getSimpleName().equals("Personnel_Qualifie")) {
				persQ.add((Personnel_Qualifie) pq);
			}else {
				persNQ.add((Personnel_Non_Qualifie) pq);
			}
		}
	}
	
	
	/**
	 * Actualise les donn�es de l'entreprise en fonction du temps donn�es
	 * 
	 * @param listechaine
	 * La cliste des chaines de production de l'entreprise sur laquelle on travail.
	 * 
	 * @param temps
	 * Le temps ou l'on se trouve lors de la pr�vision
	 */
	private static void actualisationDesProductions(ArrayList<ChaineProduction> listechaine, int temps) {
		for (ChaineProduction c: listechaine) {
			c.ActualiserProduction(temps);
		}
	}
	
	/**
	 * Recherche des personnels qualifi�s pour une chaine de production.
	 * Cette methode tiens compte du temps et v�rifie que l'employ� � le temps de travailler
	 * pour une production de la chaine de production.
	 * 
	 * @param temps
	 * Le temps ou l'on se trouve lors de la pr�vision
	 * 
	 * @param persQ
	 * Liste des personnels qualifi�s de l'entreprise
	 * 
	 * @param c
	 * La chaine de production pour laquel on recherche des Personnels qualifi�
	 * @return
	 * La liste des personnels qui sont libre d'�tre attribu� � la chaine de production
	 */
	private static ArrayList<Personnel_Qualifie> recherchePersonnelQualifie(int temps, ArrayList<Personnel_Qualifie> persQ, ChaineProduction c){
		ArrayList<Personnel_Qualifie> listePQ = new ArrayList<Personnel_Qualifie>();
		int i=0;
		System.out.println("Demande de " + c.getNombre_Personne_Qualifiee() + "Personne qualifi�");
		while (listePQ.size()<c.getNombre_Personne_Qualifiee() && i < persQ.size()) {
			if(persQ.get(i).estdisponible() && c.getTemps()+persQ.get(i).getTempsTravail()<persQ.get(i).getTempsTravailMAX() && !persQ.get(i).estEnRepos(temps, temps + c.getTemps())) {
				System.out.println("Personnel qualifi� : " +persQ.get(i) + " disponible : ajout");
				listePQ.add(persQ.get(i));
				System.out.println("Il reste " + (c.getNombre_Personne_Qualifiee()-listePQ.size()) + " personnes qualifie a trouver");
			}
			i++;
		}
		
		return listePQ;
		
	}
	
	
	
	/**
	 * Recherche des personnels non-qualifi�s pour une chaine de production.
	 * Cette methode tiens compte du temps et v�rifie que l'employ� � le temps de travailler
	 * pour une production de la chaine de production.
	 * 
	 * @param temps
	 * Le temps ou l'on se trouve lors de la pr�vision
	 * 
	 * @param persNQ
	 * Liste des personnels non qualifi�s de l'entreprise
	 * 
	 * @param c
	 * La chaine de production pour laquel on recherche des Personnels qualifi�
	 * @return
	 * La liste des personnels qui sont libre d'�tre attribu� � la chaine de production
	 */
	private static ArrayList<Personnel> recherchePersonnelNonQualifie(int temps, ArrayList<Personnel_Non_Qualifie> persNQ, ArrayList<Personnel_Qualifie> persQ, ChaineProduction c){
		ArrayList<Personnel> listePNQ = new ArrayList<Personnel>();
		//Recherche de personnes non qualifi�es
		int  i = 0;
		System.out.println("Demande de " + c.getNombre_Personne_Non_Qualifiee() + "Personne non qualifi� ");
		while (listePNQ.size()<c.getNombre_Personne_Non_Qualifiee() && i < persNQ.size()) {
			if(persNQ.get(i).estdisponible() && c.getTemps()+persNQ.get(i).getTempsTravail()<persNQ.get(i).getTempsTravailMAX() && !persNQ.get(i).estEnRepos(temps, temps + c.getTemps())) {
				System.out.println("Personnel non qualifi� : " +persNQ.get(i) + " disponible : ajout");
				listePNQ.add(persNQ.get(i));
				System.out.println("Il reste " + (c.getNombre_Personne_Non_Qualifiee()-listePNQ.size()) + " personnes non qualifie a trouver");
			}
			i++;
		}
		return listePNQ;
	}

	
	
	
	/**
	 * Met en route une chaine de production pour une production
	 * 
	 * @param listePNQ
	 * Liste des personnels non qualifi�s requis pour la mise en route de la chaine de production
	 * 
	 * @param listePQ
	 * Liste des personnels qualifi� requis pour la mise en route de la chaine de production
	 * 
	 * @param temps
	 * Temps ou l'on se trouve lors de la pr�vision
	 * 
	 * @param c
	 *  La chaine de production vis�e
	 */
	private static void miseEnRouteChaineProduction(ArrayList<Personnel> listePNQ, ArrayList<Personnel_Qualifie> listePQ, int temps, ChaineProduction c) {
		System.out.println("Lancement de la chaine de production");
		ArrayList<Personnel> listePersonnelPourChaine = new ArrayList<Personnel>();
		listePersonnelPourChaine.addAll(listePQ);
		listePersonnelPourChaine.addAll(listePNQ);
		c.produire(temps, listePersonnelPourChaine);
	}
	
	/**
	 * Lance la production d'une chaine de production en v�rifiant l'�tat des stocks, et le nombre de personnel requis.
	 * Si la production est impossible (dans 3 cas) on retourne une erreur (dans 2 cas -> stockIndisponible et PersonnelIndisponible)
	 * 
	 * @param c
	 * @param temps
	 * @param persQ
	 * @param persNQ
	 * @throws ManqueDeStocksException
	 * @throws ManqueDePersonnelsException
	 */
	private static void production(ChaineProduction c, int temps, ArrayList<Personnel_Qualifie> persQ, ArrayList<Personnel_Non_Qualifie> persNQ) throws ManqueDeStocksException, ManqueDePersonnelsException {
		//Si la chaine est D�j� en cours de production (occup�) on ne fait rien.
		if(!c.estOccupe(temps)) {
			if (c.stockSuffisant() && c.aLeTemps(temps)) {
				//si la chaine c n'est pas occup�e, que le stock est suffisant pour la production et qu'elle � le temps de produire.
				//Alors on recherche le personnel necessaire au lancement
				System.out.println("La chaine " + c.getNom() + " n'est pas occup� : recherche de personnel pour lancement" );
				ArrayList<Personnel> listePNQ = Prevision.recherchePersonnelNonQualifie(temps, persNQ, persQ, c);
				ArrayList<Personnel_Qualifie> listePQ = Prevision.recherchePersonnelQualifie(temps, persQ, c);
				System.out.println(listePNQ.size() + "- " +c.getNombre_Personne_Non_Qualifiee() + " / " + listePQ.size() + " - "+c.getNombre_Personne_Qualifiee());
				if(listePNQ.size()==c.getNombre_Personne_Non_Qualifiee() && listePQ.size()==c.getNombre_Personne_Qualifiee()) {
					Prevision.miseEnRouteChaineProduction(listePNQ, listePQ, temps, c);
				}else {
					System.out.println("pas assez de personnel");
					throw new ManqueDePersonnelsException(temps);
				}
			}else {
				System.out.println("stock insuffisant");
				throw new ManqueDeStocksException(temps);
			}
			
		}
	}
	
	
	public static void Prevision(Entreprise entreprise) throws ManqueDeStocksException, ManqueDePersonnelsException, ChainesProductionInactifException, EntrepriseManquanteException {
		if(entreprise == null) {
			throw new EntrepriseManquanteException();
		}
		
		ArrayList<Personnel_Qualifie> persQ = new ArrayList<Personnel_Qualifie>();
		ArrayList<Personnel_Non_Qualifie> persNQ = new ArrayList<Personnel_Non_Qualifie>();
		
		//Suppression de la pr�vision pr�c�dante
		Prevision.effacerPrevision(entreprise);
		
		//S�paration du personnel en deux types
		Prevision.separationPersonnelParType(persQ, persNQ, entreprise.getListePersonnel());
		
		//initialisation
		int temps = 0;
		System.out.println("COMMENCEMENT");
		//Recherche des chaines de productions ayant un niveau d'activit� sup�rieur � 0
		ArrayList<ChaineProduction> chaineProductionActive = Prevision.chainesProductionActive(entreprise);
		
		//Si toute les chaines sont inactifs alors il y a une Erreur
		if(chaineProductionActive.size()==0) {
			throw new ChainesProductionInactifException(); // Erreur propag�e
		}
		
		do{
			System.out.println("TEMPS : "+temps);
			
			//On actualise les productions en attente d'�tre termin�es
			Prevision.actualisationDesProductions(entreprise.getListeChaineProduction(), temps);
			
			//Pour toute les chaines de production (c)
			for (ChaineProduction c: chaineProductionActive) {
				try {
					Prevision.production(c, temps, persQ, persNQ);
				}catch(ManqueDeStocksException e) {
					System.out.println("Ajout de l'exception du stock indisponible");
					c.ajouterDysfonctionnement(new Dysfonctionnement(temps, "STOCK INDISPONIBLE", Prevision.detailStock(c)));
					
				}catch(ManqueDePersonnelsException e) {
					c.ajouterDysfonctionnement(new Dysfonctionnement(temps, "MANQUE DE PERSONNEL", Prevision.detailPersonnel(c, persNQ, persQ)));
				}
			}
			temps++;
		}while(temps<60);
		
		System.out.println("fin t+"+temps);
	}
	
	/**
	 * 
	 * Trouve et retourne les chaines de production n'ayant pas fabriqu� jusqu'au temps 60 - le temps de fabrication de la chaine
	 * 
	 * @param chaineDeProductionActive
	 * 	Chaine de production ayant un niveau d'activit� strictement sup�rieur � 0
	 * @return
	 * Retourne les chaines de production n'ayant pas fabriqu� jusqu'au temps 60 - le temps de fabrication de la chaine
	 */
	private static ArrayList<ChaineProduction> ChainesProductionDysfonctionnelles(ArrayList<ChaineProduction> chaineDeProductionActive){
		ArrayList<ChaineProduction> chainesProductionInactives = new ArrayList<ChaineProduction>();
		for(ChaineProduction cp: chaineDeProductionActive) {
			if (cp.getFinDeProduction()<= 60 - cp.getTemps()) {
				chainesProductionInactives.add(cp);
			}
		}
		return chainesProductionInactives;
	}
	
	
	private static String detailStock(ChaineProduction cp) {
		String detail="";
		
		for(Couple<Element, Float> couple : cp.getEntree()) {
			if(couple.getObjeta().getStock().getStock()<couple.getObjetb()) {
				detail += "Element hors-stock :" + couple.getObjeta().getCodeElement() + " " + couple.getObjeta().getNom() + "Quantit� n�cessaire : " + couple.getObjetb() + " Quantit� disponible : " + couple.getObjeta().getStock().getStock() + "\n";
			}
		}
		
		return detail;
		
	}
	
	private static String detailPersonnel(ChaineProduction cp, ArrayList<Personnel_Non_Qualifie> personnelsNonQualifies, ArrayList<Personnel_Qualifie> personnelsQualifies) {
		String detail = "";
		int nbPersonnelQualifieDispo = 0;
		int nbPersonnelNonQualifieDispo = 0;
		for(Personnel_Qualifie p : personnelsQualifies) {
			if(p.estdisponible()) {
				nbPersonnelQualifieDispo+=1;
			}
		}
		for(Personnel_Non_Qualifie p : personnelsNonQualifies) {
			if(p.estdisponible()) {
				nbPersonnelNonQualifieDispo+=1;
			}
		}				
		detail += "Personnel Qualifi� Requis : " + cp.getNombre_Personne_Qualifiee() + " / Personnel Non Qualifi� Requis : " + cp.getNombre_Personne_Non_Qualifiee()+"\nPersonnel Qualifi� Disponible : " + nbPersonnelQualifieDispo + " / Personnel Non Qualifi� Disponible : " + nbPersonnelNonQualifieDispo;
		return detail;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
