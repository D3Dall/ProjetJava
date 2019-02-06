package core;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Production {
        /**
         * Niveau d'activite de la chaine de production.
         * Cela correspond a la performance de la chaine de production
         */
	private int niveauProduction;
        /**
         * Date a laquelle on a produit
         */
	private Calendar dateProduction;
        
        /**
         * Instancie une production
         * @param niveauProduction
         *  niveau d'activite d'une chaine de production
         * @param dateProduction 
         *  date de la production
         */
	public Production(int niveauProduction, Calendar dateProduction) {
		this.niveauProduction = niveauProduction;
		this.dateProduction = dateProduction;
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
	public Calendar getDateProduction() {
		return dateProduction;
	}
        
        
}