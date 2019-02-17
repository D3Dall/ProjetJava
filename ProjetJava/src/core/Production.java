package core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Production {
        /**
         * Niveau d'activite de la chaine de production.
         * Cela correspond a la performance de la chaine de production
         */
	private int niveauProduction;
        /**
         * Heure a laquelle on a produit
         */
	private int heure;
	private ArrayList<Personnel> listePersonnel;
	
	
	public Production(int niveauProduction, int heure, ArrayList<Personnel> listePersonnel) {
		this.niveauProduction = niveauProduction;
		this.heure = heure;
		this.listePersonnel=listePersonnel;
		for (Personnel p : this.listePersonnel) {
			p.rendreIndisponible();
		}
	}
	
        /**
         * 
         * @return le niveau de production lors de la production 
         */
	public int getNiveauProduction() {
		return this.niveauProduction;
	}

        /**
         * 
         * @return la date de la production 
         */
	public int getHeure() {
		return this.heure;
	}
	
	public void liberer() {
		for(Personnel p : this.listePersonnel) {
			p.rendreDisponible();
		}
	}

	public ArrayList<Personnel> getListePersonnel() {
		return listePersonnel;
	}
      
        
}