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

	public FichierCSVChaineDeProduction() {
		super("Data/Chaines.csv");
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<String> Lecture(){
		ArrayList<String> ligne = new ArrayList<String>();
		Scanner sc;
		try {
			sc = new Scanner(path);
			while (sc.hasNextLine()) {
				ligne.add(sc.nextLine());
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return ligne;
	}
	
	private int chercherIndexSortieElements(ArrayList<String> elements) {
		String[] element = elements.get(0).split(",");elements.remove(0);
		int index = 3;
		while (!element[index].equals("Sortie(code") && index<element.length) {
			index ++;
		}
		return index;
	}
	
	public void Charger() {
		ArrayList<String> elements = this.Lecture();
		int SortieCodeIndex = this.chercherIndexSortieElements(elements);
		for (String src : elements) {
			String[] attribut = src.split(",");
			String code = attribut[0];
			String nom = attribut[1];
			int temps = Integer.parseInt(attribut[2]);
			ChaineProduction cp = new ChaineProduction(code, nom, temps);
			Entreprise.enteprise.ajouterChaineProductionDansEntreprise(cp);
			ChargerContenuSortie(attribut, cp, SortieCodeIndex);
			ChargerContenuEntree(attribut, cp, SortieCodeIndex);
		}		
	}
	
	private void ChargerContenuSortie(String[] ligne, ChaineProduction cp, int indexOfSortie) {
		for (int i = indexOfSortie; i<ligne.length; i+=2) {
			String codeElem = ligne[i].substring(ligne[i].indexOf('(')+1);
			float quantitee = Float.parseFloat(ligne[i+1].substring(0, ligne[i+1].indexOf(')')));
			Element elem = Entreprise.enteprise.rechercherElement(codeElem);
			cp.ajouterElementPourDictionnaireDeProductionEnSortie(elem, quantitee);
		}
	}
	private void ChargerContenuEntree(String[] ligne, ChaineProduction cp, int indexOfSortie) {
		for (int i = 3; i<ligne.length && i<indexOfSortie; i+=2) {
			String codeElem = ligne[i].substring(ligne[i].indexOf('(')+1);
			float quantitee = Float.parseFloat(ligne[i+1].substring(0, ligne[i+1].indexOf(')')));
			Element elem = Entreprise.enteprise.rechercherElement(codeElem);
			cp.ajouterElementPourDictionnaireDeProductionEnEntree(elem, quantitee);
		}
	}
	

	@Override
	public String[][] Ecriture() {
		// TODO Auto-generated method stub
		return null;
	}

}
