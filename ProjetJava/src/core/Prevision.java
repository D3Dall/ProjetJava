package core;

import java.util.ArrayList;

import erreurs.ChainesProductionInactifException;
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
		for(ChaineProduction cp : entreprise.getListeChaineProduction()) {
			cp.effacerPrevision();
		}
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
	
	
	private static ArrayList<Personnel_Qualifie> recherchePersonnelQualifie(ArrayList<Personnel_Qualifie> persQ, ChaineProduction c){
		ArrayList<Personnel_Qualifie> listePQ = new ArrayList<Personnel_Qualifie>();
		int i=0;
		System.out.println("Demande de " + c.getNombre_Personne_Qualifiee() + "Personne qualifié");
		while (listePQ.size()<c.getNombre_Personne_Qualifiee() && i < persQ.size()) {
			if(persQ.get(i).estdisponible() && c.getTemps()+persQ.get(i).getTempsTravail()<persQ.get(i).getTempsTravailMAX()) {
				System.out.println("Personnel qualifié : " +persQ.get(i) + " disponible : ajout");
				listePQ.add(persQ.get(i));
				System.out.println("Il reste " + (c.getNombre_Personne_Qualifiee()-listePQ.size()) + " personnes qualifie a trouver");
			}
			i++;
		}
		
		return listePQ;
		
	}
	
	private static ArrayList<Personnel> recherchePersonnelNonQualifie(ArrayList<Personnel_Non_Qualifie> persNQ, ArrayList<Personnel_Qualifie> persQ, ChaineProduction c){
		ArrayList<Personnel> listePNQ = new ArrayList<Personnel>();
		//Recherche de personnes non qualifiées
		int  i = 0;
		System.out.println("Demande de " + c.getNombre_Personne_Non_Qualifiee() + "Personne non qualifié ");
		while (listePNQ.size()<c.getNombre_Personne_Non_Qualifiee() && i < persNQ.size()) {
			if(persNQ.get(i).estdisponible() && c.getTemps()+persNQ.get(i).getTempsTravail()<persNQ.get(i).getTempsTravailMAX()) {
				System.out.println("Personnel non qualifié : " +persNQ.get(i) + " disponible : ajout");
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
			//Si la chaine c est occupée on la met en attente
			productionEnCours=true;
		}else if (c.stockSuffisant() && c.aLeTemps(temps)) {
			//si la chaine c n'est pas occupée, que le stock est suffisant pour la production et qu'elle à le temps de produire
			//Alors on recherche le personnel necessaire au lancement
			System.out.println("La chaine " + c.getNom() + " n'est pas occupé : recherche de personnel pour lancement" );
			ArrayList<Personnel> listePNQ = Prevision.recherchePersonnelNonQualifie(persNQ, persQ, c);
			ArrayList<Personnel_Qualifie> listePQ = Prevision.recherchePersonnelQualifie(persQ, c);
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
	
	
	public static void Prevision(Entreprise entreprise) throws ManqueDeStocksException, ManqueDePersonnelsException, ChainesProductionInactifException {
		ArrayList<Personnel_Qualifie> persQ = new ArrayList<Personnel_Qualifie>();
		ArrayList<Personnel_Non_Qualifie> persNQ = new ArrayList<Personnel_Non_Qualifie>();
		
		//Suppression de la prévision précédante
		Prevision.effacerPrevision(entreprise);
		
		//Séparation du personnel en deux types
		Prevision.separationPersonnelParType(persQ, persNQ, entreprise.getListePersonnel());
		
		//initialisation
		int temps = 0;
		boolean productionEnCours;
		System.out.println("COMMENCEMENT");
		//Recherche des chaines de productions ayant un niveau d'activité supérieur à 0
		ArrayList<ChaineProduction> chaineProductionActive = Prevision.chainesProductionActive(entreprise);
		
		//Si toute les chaines sont inactifs alors il y a une Erreur
		if(chaineProductionActive.size()==0) {
			throw new ChainesProductionInactifException(); // Erreur propagée
		}
		
		do{
			System.out.println("TEMPS : "+temps);
			
			//On part du principe que la production est sur off
			productionEnCours=false;
			
			//On actualise les productions en attente d'être terminées
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
