package gestionfichier;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import core.Element;
import core.Entreprise;
import core.MatieresPremieres;
import core.ProduitsFinis;

public class FichierCSVElement extends FichierCSV{

	
	public FichierCSVElement(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Charge les données sur les Eléments écrites dans le fichier Element
	 */
	public void Charger() {
		ArrayList<String> elements = this.Lecture();
		for (String src : elements) {
			String[] attribut = src.split(",");
			String code = attribut[0];
			String nom = attribut[1];
			float stock = Float.parseFloat(attribut[2]);
			String unite = attribut[3];
			double achat;
			double vente;
			if(attribut[4].equals("NA")) {
				achat=-1;
			}else {
				achat = Double.parseDouble(attribut[4]);
			}
			if(attribut[5].equals("NA")) {
				vente=-1;
			}else {
				vente = Double.parseDouble(attribut[5]);
			}
			if (attribut[6].equals("PF")) {
				Entreprise.entreprise.ajouterElementDansEntreprise(new MatieresPremieres(code, nom, unite, stock, achat, vente));
			}else {
				Entreprise.entreprise.ajouterElementDansEntreprise(new MatieresPremieres(code, nom, unite, stock, achat, vente));
			}
		}		
	}

	@Override
	public void Ecriture() {
	}

}
