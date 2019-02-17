package gestionfichier;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import core.Element;
import core.Entreprise;
import core.MatieresPremieres;
import core.ProduitsFinis;

public class FichierCSVElement extends FichierCSV{
	private int index_Code;
	private int index_Nom;
	private int index_Quantite;
	private int index_Unite;
	private int index_PrixA;
	private int index_PrixV;
	private int index_Demande;
	
	public FichierCSVElement(String path) {
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
					}else if (ligne[i].toLowerCase().contains("nom")) {
						this.index_Nom = i;
					}else if(ligne[i].toLowerCase().contains("quantite")) {
						this.index_Quantite = i;
					}else if(ligne[i].toLowerCase().contains("unite")) {
						this.index_Unite = i;
					}else if(ligne[i].toLowerCase().contains("achat")){
						this.index_PrixA = i;
					}else if(ligne[i].toLowerCase().contains("vente")){
						this.index_PrixV = i;
					}else if(ligne[i].toLowerCase().contains("demande")) {
						this.index_Demande = i;
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
	 * Charge les données sur les Eléments écrites dans le fichier Element
	 */
	public void chargerDonnee() {
		ArrayList<String> elements = this.lecture();
		for (String src : elements) {
			String[] attribut = src.split(";");
			String code = attribut[this.index_Code];
			String nom = attribut[this.index_Nom];
			String unite = attribut[this.index_Unite];
			double achat;
			double vente;
			try {
				float stock = Float.parseFloat(attribut[this.index_Quantite]);
				if(attribut[this.index_PrixA].equals("NA")) {
					achat=-1;
				}else {
					achat = Double.parseDouble(attribut[this.index_PrixA]);
				}
				if(attribut[this.index_PrixV].equals("NA")) {
					vente=-1;
				}else {
					vente = Double.parseDouble(attribut[this.index_PrixV]);
				}
				if (attribut[6].equals("PF")) {
					Entreprise.entreprise.ajouterElementDansEntreprise(new MatieresPremieres(code, nom, unite, stock, achat, vente));
				}else {
					Entreprise.entreprise.ajouterElementDansEntreprise(new MatieresPremieres(code, nom, unite, stock, achat, vente));
				}
			}catch(Exception e) {
				
			}
			
		}		
	}

	@Override
	public void ecriture() {
	}

}
