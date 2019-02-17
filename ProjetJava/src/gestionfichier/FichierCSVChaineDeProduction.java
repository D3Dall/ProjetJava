package gestionfichier;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import core.ChaineProduction;
import core.Element;
import core.Entreprise;
import core.MatieresPremieres;
import core.ProduitsFinis;
import core.Stockage;

public class FichierCSVChaineDeProduction extends FichierCSV{
	private int index_Code;
	private int index_Nom;
	private int index_Temps;
	private int index_PersNQ;
	private int index_PersQ;
	private int index_Entree;
	private int index_Sortie;

	public FichierCSVChaineDeProduction(String path) {
		super(path);
		this.chargerEntete();
	}
	
	private void chargerEntete() {
		Scanner sc;
		try {
			sc = new Scanner(path);
			if(sc.hasNextLine()) {
				String[] ligne = sc.nextLine().split(";");
				for (int i = 0; i< ligne.length; i++) {
					if(ligne[i].toLowerCase().equals("code")) {
						this.index_Code = i;
					}else if (ligne[i].toLowerCase().contains("nom")) {
						this.index_Nom = i;
					}else if(ligne[i].toLowerCase().contains("entree")) {
						this.index_Entree = i;
					}else if(ligne[i].toLowerCase().contains("sortie")) {
						this.index_Sortie = i;
					}else if(ligne[i].toLowerCase().contains("temps")){
						this.index_Temps = i;
					}else if(ligne[i].toLowerCase().contains("personnels non qualifies")){
						this.index_PersNQ = i;
					}else if(ligne[i].toLowerCase().contains("personnels qualifies")) {
						this.index_PersQ = i;
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
	
	public void chargerDonnee() {
		ArrayList<String> elements = this.lecture();
		for (String src : elements) {
			String[] attribut = src.split(";");
			String code = attribut[this.index_Code];
			String nom = attribut[this.index_Nom];
			try {
				System.out.println("1");
				int temps = Integer.parseInt(attribut[this.index_Temps]);
				System.out.println("2");
				int personnelNQ = Integer.parseInt(attribut[this.index_PersNQ]);
				System.out.println("3");
				int personnelQ = Integer.parseInt(attribut[this.index_PersQ]);
				System.out.println("4");
				ChaineProduction cp = new ChaineProduction(code, nom, temps, personnelNQ, personnelQ);
				ChargerContenuSortie(attribut[this.index_Sortie], cp);
				ChargerContenuEntree(attribut[this.index_Entree], cp);
				Entreprise.entreprise.ajouterChaineProductionDansEntreprise(cp);
			}catch(Exception e) {
				//Erreur de format dans le fichier
			}
			
		}
	}
	
	private void ChargerContenuSortie(String attribut, ChaineProduction pr) {
		String[] elemsortie = attribut.split(",");
		if(elemsortie.length%2==0) {
			//Erreur format fichier
		}
		for (int i = 0 ; i < elemsortie.length; i+=2) {
			Element el = Entreprise.entreprise.rechercherElement(elemsortie[i].substring(elemsortie[i].indexOf('(')+1));
			try {
				float qt = Float.parseFloat(elemsortie[i+1].substring(0, elemsortie[i+1].indexOf(')')));
				pr.ajouterElementPourDictionnaireDeProductionEnSortie(el, qt);
			}catch(Exception e) {
				//Erreur
			}
		}		
	}
	
	
	private void ChargerContenuEntree(String attribut, ChaineProduction pr) {
		String[] elemsortie = attribut.split(",");
		if(elemsortie.length%2==0) {
			//Erreur format fichier
		}
		for (int i = 0 ; i < elemsortie.length; i+=2) {
			Element el = Entreprise.entreprise.rechercherElement(elemsortie[i].substring(elemsortie[i].indexOf('(')+1));
			try {
				float qt = Float.parseFloat(elemsortie[i+1].substring(0, elemsortie[i+1].indexOf(')')));
				pr.ajouterElementPourDictionnaireDeProductionEnEntree(el, qt);
			}catch(Exception e) {
				//Erreur
			}
		}	
	}

	@Override
	public void ecriture() {
		// TODO Auto-generated method stub
		
	}






}
