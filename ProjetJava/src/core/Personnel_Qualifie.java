package core;
import java.util.ArrayList;

import core.Personnel;

public class Personnel_Qualifie extends Personnel {

	/**
	 * Créé un Personnel qualifié.
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
	public Personnel_Qualifie(String codePersonnel, String nom, String prenom, int tempsTravail, ArrayList<Integer> repos) {
		super(codePersonnel, nom, prenom, tempsTravail, repos);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		String retour = super.toString(); 
		retour += "Qualification : Qualifié\n\n";  
		return retour;
	}
}
