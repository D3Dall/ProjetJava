package core;

import java.util.ArrayList;

public class Personnel_Non_Qualifie extends Personnel{

	/**
	 * Cr�� un Personnel non qualifi�.
	 * 
	 * @param codePersonnel
	 *  L'identifiant d'un personnel (UNIQUE).
	 * 
	 * @param nom
	 *  Le nom du Personnel.
	 * 
	 * @param prenom
	 *  Le nom du Personnel.
	 *  
	 * @param tempsTravail
	 *  Le temps qu'il peut travailler par semaine (inscrit dans le contrat).
	 *  
	 *  @param repos
     *    Les heures de repos du personnel
     *    
	 */
	public Personnel_Non_Qualifie(String codePersonnel, String nom, String prenom, int tempsTravail, ArrayList<Integer> repos) {
		super(codePersonnel, nom, prenom, tempsTravail, repos);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		String retour = super.toString(); 
		retour += "Qualification : Non qualifi�\n\n";  
		return retour;
	}
	
	
	
	
	
	
	
	
	
	
	

}
