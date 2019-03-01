package core;

import java.util.ArrayList;

public abstract class Personnel {
        /**
         * L'identifiant d'une personne
         */
	private String codePersonnel;
	
        /**
         * Le nom d'une personne
         */
	private String nom;
	
        /**
         * Le prenom d'une personne
         */
	private String prenom;
	
		/**
		 * Le temps de travail sur une semaine que la personne peut travailler (inscrit dans le contrat).
		 */
	private int tempsTravailMAX;
	
		/**
		 * Le temps de travail sur une semaine que la personne a travaillé.
		 */
	private int tempsTravail;
	
	private boolean disponible;
	
	private ArrayList<Integer> repos;
	
        
        /**
         * Créé un Personnel disponible.
         * 
         * @param codePersonnel
         *  L'identifiant d'un personnel.
         *  
         * @param nom
         *  Le nom d'un personnel.
         *  
         * @param prenom
         *  Le prénom d'un personnel.
         *  
         *  @param tempsTravailMAX
         *   Le temps de travail maximum du personnel par semaine (inscrit dans le contrat).
         *   
         *   @param repos
         *    Les heures de repos du personnel
         *  
         */
	public Personnel(String codePersonnel, String nom, String prenom, int tempsTravailMAX, ArrayList<Integer> repos) {
		this.codePersonnel = codePersonnel;
		this.nom = nom;
		this.prenom = prenom;
		this.tempsTravailMAX = tempsTravailMAX;
		this.disponible = true;
		this.tempsTravail = 0;
		this.repos=repos;
	}
	
	/**
	 * Voit si le personnel est disponible, s'il n'est pas occupé à une chaine de production
	 * @return
	 * Vrai si le personnel est disponible, faux sinon.
	 */
	public boolean estdisponible() {
		return this.disponible;
	}
	
	/**
	 * Rend le personnel indisponible.
	 */
	public void rendreIndisponible() {
		this.disponible=false;
	}
	
	/**
	 * Rend le personnel disponible.
	 */
	public void rendreDisponible() {
		this.disponible=true;
	}
	
	/**
	 * Ajoute du temps de travail au personnel
	 * @param temps
	 * L'heure de la production
	 */
	public void ajouterHeureTravail(int temps) {
		this.tempsTravail+=temps;
	}
	
	public boolean estEnRepos(int temps, int temps_travail) {
		if (this.repos.contains(temps)) {
			return true;
		}else {
			for (int t=temps; t<=temps_travail; t++) {
				if (this.repos.contains(t)) {
					return true;
				}
			}
		}
		return false;	
	}
	
	public String toString() {
		String retour = "";
		retour += "Personnel - Code Personnel : " + this.codePersonnel + "\n";
		retour += "Nom du Personnel : " + this.nom + "\n";
		retour += "Prenom du Personnel : " + this.prenom + "\n";
		retour += "Nombre d'heure de travail dans la semaine maximales inscritent dans le contrat : " + this.tempsTravailMAX + "\n";
		retour += "Nombre d'heure de travail effectives dans la semaine : " + this.tempsTravail + "\n";
		return retour;
	}
	
	/**
	 * Remet à 0 le temps de travail d'un personnel
	 */
	public void remettreA0() {
		this.tempsTravail=0;
	}

	// GETTERS
	public String getCodePersonnel() {
		return codePersonnel;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public int getTempsTravailMAX() {
		return tempsTravailMAX;
	}

	public int getTempsTravail() {
		return tempsTravail;
	}

	public boolean isDisponible() {
		return disponible;
	}
	public ArrayList<Integer> getRepos(){
		return this.repos;
	}
	
	
	

}
