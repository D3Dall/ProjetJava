package core;

import java.util.ArrayList;

public class Personnel {
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
         * adresse d'une personne
         */
	private String adresse;
        /**
         * email d'une personne
         */
	private String email;
        /**
         * numero de telephone d'une personne
         */
	private String telephone;
        /**
         * liste des qualifications d'une personne.
         * Cela correspond aux chaines de production auquelles elle peut travailler
         */
	private ArrayList<ChaineProduction> listequalifications;
        
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
	public Personnel(String codePersonnel, String nom, String prenom, String adresse, String email, String telephone) {
		this.codePersonnel = codePersonnel;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
		this.listequalifications = new ArrayList<ChaineProduction> ();
	}
	
        /**
         * Ajoute une qualification=chaine de production
         * @param qualification
         *  chaine de production/qualification
         */
	public void ajouterQualificationPourPersonnel(ChaineProduction qualification) {
		this.listequalifications.add(qualification);
	}
        
        /**
         * Retire une qualification=chaine de production
         * @param qualification
         *  chaine de production/qualification
         */
	public void retirerQualificationPourPersonnel(ChaineProduction qualification) {
		this.listequalifications.remove(qualification);
	}
	
	
	

}
