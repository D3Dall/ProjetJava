package core;

import java.util.ArrayList;

public abstract class Personnel {
        /**
         * Identifiant d'une personne
         */
	private String codePersonnel;
        /**
         * nom d'une personne
         */
	private String nom;
        /**
         * prenom d'une personne
         */
	private String prenom;
	
	/**
	 * le temps de travail sur une semaine que la personne peut travailler
	 */
	private int tempsTravailMAX;
	
	private int tempsTravail;
	
	private boolean disponible;
	
        
        /**
         * Instancie un personnel
         * @param codePersonnel
         *  Identifiant d'un personnel
         * @param nom
         *  Nom d'un personnel
         * @param prenom
         *  Prenom d'un personnel
         * @param adresse
         *  Adresse d'un personnel
         * @param email
         *  Adresse email d'un personnel
         * @param telephone 
         *  Numero de telephone d'un personnel
         */
	public Personnel(String codePersonnel, String nom, String prenom, int tempsTravailMAX) {
		this.codePersonnel = codePersonnel;
		this.nom = nom;
		this.prenom = prenom;
		this.tempsTravailMAX = tempsTravailMAX;
		this.disponible = true;
		this.tempsTravail = 0;
	}
	
	public boolean estdisponible() {
		return this.disponible;
	}
	
	public void rendreIndisponible() {
		this.disponible=false;
	}
	public void rendreDisponible() {
		this.disponible=true;
	}
	
	public void ajouterHeureTravail(int temps) {
		this.tempsTravail+=temps;
	}
	
	public String toString() {
		return this.nom;
	}

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
	
	
	

}
