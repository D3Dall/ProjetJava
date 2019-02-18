package core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Production {
	
        /**
         * Niveau d'activite de la chaine de production.
         * Cela correspond � la performance de la chaine de production.
         */
	private int niveauProduction;
	
        /**
         * l'heure � laquelle on a produit.
         */
	private int heure;
	
		/**
		 * Liste des personnels reli�s � la production.
		 */
	private ArrayList<Personnel> listePersonnel;
	
		/**
		 * Cr�� une production
		 * 
		 * @param niveauProduction
		 *  Le niveau de production de la chaine lors de la production.
		 *  
		 * @param heure
		 *  L'heure � laquel la production a d�but�.
		 *  
		 * @param listePersonnel
		 *  La liste des personnels reli�e au fonctionnement de la chaine de production.
		 */
	public Production(int niveauProduction, int heure, ArrayList<Personnel> listePersonnel) {
		this.niveauProduction = niveauProduction;
		this.heure = heure;
		this.listePersonnel=listePersonnel;
		for (Personnel p : this.listePersonnel) {
			p.rendreIndisponible();
		}
	}
	
	/**
	 * Lib�re tout les personnels reli�s � la production.
	 */
	public void liberer() {
		for(Personnel p : this.listePersonnel) {
			p.rendreDisponible();
		}
	}
	
	//GETTERS
	
	public int getNiveauProduction() {
		return this.niveauProduction;
	}

	public int getHeure() {
		return this.heure;
	}
	
	public ArrayList<Personnel> getListePersonnel() {
		return listePersonnel;
	}
      
        
}