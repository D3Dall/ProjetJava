package core;

import java.util.ArrayList;

import erreurs.ChainesProductionInactifException;
import erreurs.EntrepriseManquanteException;
import erreurs.ManqueDePersonnelsException;
import erreurs.ManqueDeStocksException;

public class Prevision {
	
	
	private static ArrayList<ChaineProduction> chainesProductionActive(Entreprise entreprise){
		ArrayList<ChaineProduction> chainesProductionActive = new ArrayList<ChaineProduction>();
		for(ChaineProduction cp: entreprise.getListeChaineProduction()) {
			if (cp.estActive()) {
				chainesProductionActive.add(cp);
			}
		}
		return chainesProductionActive;
	}
	
	private static void effacerPrevision(Entreprise entreprise) {
		entreprise.remettreA0();
	}

	private static void separationPersonnelParType(ArrayList<Personnel_Qualifie> persQ, ArrayList<Personnel_Non_Qualifie> persNQ, ArrayList<Personnel> pers) {
		for(Personnel pq : pers) {
			if(pq.getClass().getSimpleName().equals("Personnel_Qualifie")) {
				persQ.add((Personnel_Qualifie) pq);
			}else {
				persNQ.add((Personnel_Non_Qualifie) pq);
			}	
		}
	}
	
	private static void actualisationDesProductions(ArrayList<ChaineProduction> listechaine, int temps) {
		for (ChaineProduction c: listechaine) {
			c.ActualiserProduction(temps);
		}
	}
	
	
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

	private static void miseEnRouteChaineProduction(ArrayList<Personnel> listePNQ, ArrayList<Personnel_Qualifie> listePQ, int temps, ChaineProduction c) {
		System.out.println("Lancement de la chaine de production");
		ArrayList<Personnel> listePersonnelPourChaine = new ArrayList<Personnel>();
		listePersonnelPourChaine.addAll(listePQ);
		listePersonnelPourChaine.addAll(listePNQ);
		c.produire(temps, listePersonnelPourChaine);
	}
	
	private static void verificationResultatPrevision(int temps, ArrayList<ChaineProduction> chaineProductionActive) throws ManqueDePersonnelsException, ManqueDeStocksException {
		if(temps<60) {
			for(ChaineProduction c : chaineProductionActive) {
				if(c.stockSuffisant()) {
					throw new ManqueDePersonnelsException(temps);
				}
			}
			throw new ManqueDeStocksException(temps);
		}
	}
	
	private static boolean traitementChaine(ChaineProduction c, int temps, ArrayList<Personnel_Qualifie> persQ, ArrayList<Personnel_Non_Qualifie> persNQ) {
		boolean productionEnCours = false;
		if(c.estOccupe(temps)) {
			//Si la chaine c est occup�e on la garde en attente (on ne fait rien)
			productionEnCours=true;
		}else if (c.stockSuffisant() && c.aLeTemps(temps)) {
			//si la chaine c n'est pas occup�e, que le stock est suffisant pour la production et qu'elle � le temps de produire
			//Alors on recherche le personnel necessaire au lancement
			System.out.println("La chaine " + c.getNom() + " n'est pas occup� : recherche de personnel pour lancement" );
			ArrayList<Personnel> listePNQ = Prevision.recherchePersonnelNonQualifie(temps, persNQ, persQ, c);
			ArrayList<Personnel_Qualifie> listePQ = Prevision.recherchePersonnelQualifie(temps, persQ, c);
			System.out.println(listePNQ.size() + "- " +c.getNombre_Personne_Non_Qualifiee() + " / " + listePQ.size() + " - "+c.getNombre_Personne_Qualifiee());
			if(listePNQ.size()==c.getNombre_Personne_Non_Qualifiee() && listePQ.size()==c.getNombre_Personne_Qualifiee()) {
				Prevision.miseEnRouteChaineProduction(listePNQ, listePQ, temps, c);
				productionEnCours=true;
			}else {
				System.out.println("pas assez de personnel");
			}
		}
		return productionEnCours;
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
		boolean productionEnCours;
		System.out.println("COMMENCEMENT");
		//Recherche des chaines de productions ayant un niveau d'activit� sup�rieur � 0
		ArrayList<ChaineProduction> chaineProductionActive = Prevision.chainesProductionActive(entreprise);
		
		//Si toute les chaines sont inactifs alors il y a une Erreur
		if(chaineProductionActive.size()==0) {
			throw new ChainesProductionInactifException(); // Erreur propag�e
		}
		
		do{
			System.out.println("TEMPS : "+temps);
			
			//On part du principe que la production est sur off
			productionEnCours=false;
			
			//On actualise les productions en attente d'�tre termin�es
			Prevision.actualisationDesProductions(entreprise.getListeChaineProduction(), temps);
			
			//Pour toute les chaines de production (c)
			for (ChaineProduction c: chaineProductionActive) {
				//S'il y a eu production, la production est sur on
				if(Prevision.traitementChaine(c, temps, persQ, persNQ)) {
					productionEnCours=true;
				}
			}
			temps++;
		}while(productionEnCours && temps<60);
		
		System.out.println("fin t+"+temps);
		
		Prevision.verificationResultatPrevision(temps, chaineProductionActive);
	}
	
	
	private static ArrayList<ChaineProduction> ChainesProductionDysfonctionnelles(Entreprise entreprise){
		ArrayList<ChaineProduction> chainesProductionInactives = new ArrayList<ChaineProduction>();
		for(ChaineProduction cp: entreprise.getListeChaineProduction()) {
			if (cp.estActive() && cp.getFinDeProduction()<= 60 - cp.getTemps()) {
				chainesProductionInactives.add(cp);
			}
		}
		return chainesProductionInactives;
	}
	
	private ArrayList<ChaineDysfonctionnelle> raisonsDysfonctionnements(Entreprise entreprise) {
		ArrayList<ChaineProduction> chainesProductionDysfonctionnelles= ChainesProductionDysfonctionnelles(entreprise);
		ArrayList<ChaineDysfonctionnelle> listeChainesDysfonctionnelles = new ArrayList<ChaineDysfonctionnelle>();
		ArrayList<Personnel_Qualifie> PersonnelsQualifies = new ArrayList<Personnel_Qualifie>();
		ArrayList<Personnel_Non_Qualifie> PersonnelsNonQualifies = new ArrayList<Personnel_Non_Qualifie>();
		Prevision.separationPersonnelParType(PersonnelsQualifies, PersonnelsNonQualifies, entreprise.getListePersonnel());
		for(ChaineProduction cp : chainesProductionDysfonctionnelles) {
			String detail = "";
			if(cp.stockSuffisant()) {
				int nbPersonnelQualifieDispo = 0;
				int nbPersonnelNonQualifieDispo = 0;
				for(Personnel_Qualifie p : PersonnelsQualifies) {
					if(p.estdisponible()) {
						nbPersonnelQualifieDispo+=1;
					}
				}
				for(Personnel_Non_Qualifie p : PersonnelsNonQualifies) {
					if(p.estdisponible()) {
						nbPersonnelNonQualifieDispo+=1;
					}
				}				
				detail += "Personnel Qualifi� Requis : " + cp.getNombre_Personne_Qualifiee() + " / Personnel Non Qualifi� Requis : " + cp.getNombre_Personne_Non_Qualifiee()+"\nPersonnel Qualifi� Disponible : " + nbPersonnelQualifieDispo + " / Personnel Non Qualifi� Disponible : " + nbPersonnelNonQualifieDispo;
				listeChainesDysfonctionnelles.add(new ChaineDysfonctionnelle(cp, "Manque de Personnel", detail));
			}
			else {
				for(Couple<Element, Float> couple : cp.getEntree()) {
					if(couple.getObjeta().getStock().getStock()<couple.getObjetb()) {
						detail += "Element hors-stock :" + couple.getObjeta().getCodeElement() + " " + couple.getObjeta().getNom() + "Quantit� n�cessaire : " + couple.getObjetb() + " Quantit� disponible : " + couple.getObjeta().getStock().getStock() + "\n";
					}
				}
				listeChainesDysfonctionnelles.add(new ChaineDysfonctionnelle(cp, "Manque de Stock", detail));
			}
		}
		return listeChainesDysfonctionnelles;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
