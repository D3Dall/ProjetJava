package gestionfichier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import core.Entreprise;
import core.Personnel_Non_Qualifie;
import core.Personnel_Qualifie;

public class FichierCSVPersonnel extends FichierCSV{
	private int index_Code;
	private int index_Nom;
	private int index_Prenom;
	private int index_Temps;
	private int index_Qualification;
	private int index_Repos;
	
	public FichierCSVPersonnel(String path) {
		super(path);
		this.chargerEntete();
		// TODO Auto-generated constructor stub
	}
	public FichierCSVPersonnel(File path) {
		super(path);
		this.chargerEntete();
		// TODO Auto-generated constructor stub
	}
	
	
	private void chargerEntete() {
		Scanner sc;
		try {
			sc = new Scanner(path);
			if(sc.hasNextLine()) {
				String[] ligne = sc.nextLine().split(";");
				for (int i = 0; i< ligne.length; i++) {
					if(ligne[i].toLowerCase().contains("code")) {
						this.index_Code = i;
					}else if (ligne[i].toLowerCase().equals("nom")) {
						System.out.println("indice " +i);
						this.index_Nom = i;
					}else if(ligne[i].toLowerCase().equals("prenom")) {
						this.index_Prenom = i;
					}else if(ligne[i].toLowerCase().contains("qualification")) {
						this.index_Qualification = i;
					}else if(ligne[i].toLowerCase().contains("temps")){
						this.index_Temps = i;
					}else if(ligne[i].toLowerCase().contains("repos")) {
						this.index_Repos= i;
					}
				}				
			}else {
				//Erreur fichier vide
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Charge les donnees sur le personnel ecrites dans le fichier Element
	 */
	public void chargerDonnee() {
		ArrayList<String> elements = this.lecture();
		for (String src : elements) {
			String[] attribut = src.split(";");
			String code = attribut[this.index_Code];
			String nom = attribut[this.index_Nom];
			String prenom = attribut[this.index_Prenom];
			int temps = Integer.parseInt(attribut[this.index_Temps]);
			String qualification = attribut[this.index_Qualification];
			ArrayList<Integer> repos = this.constructionRepos(attribut[this.index_Repos]);
			if (qualification.equals("Y")) {
				Entreprise.entreprise.ajouterPersonnelDansEntrepise(new Personnel_Qualifie(code, nom, prenom, temps, repos));
			}else {
				Entreprise.entreprise.ajouterPersonnelDansEntrepise(new Personnel_Non_Qualifie(code, nom, prenom, temps, repos));
			}
		}		
	}
	
	
	private ArrayList<Integer> constructionRepos(String src){
		ArrayList<Integer> repos = new ArrayList<Integer>();
		String[] tab = src.split(",");
		
		for (int i = 0 ; i < tab.length; i+=2) {
			try {
				int jourdeb = Integer.parseInt(tab[i].substring(tab[i].indexOf('(')+1, tab[i].length()));
				int jourfin = Integer.parseInt(tab[i+1].substring(0, tab[i+1].indexOf(')')));
				for(int j = jourdeb ; j <= jourfin ; j++) {
					repos.add(j);
				}
				
			}catch(Exception e) {
				//erreur format
			}
		}
		return repos;		
		
	}

	
	
	
	
}
