package main;

import java.io.IOException;

import core.*;
import gestionfichier.*;
import ihm.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BaseDeDonnees bdElem = new FichierCSVElement("ProjetJava/Data/Elements.csv");
		BaseDeDonnees bdCP = new FichierCSVChaineDeProduction("ProjetJava/Data/Chaines.csv");
		BaseDeDonnees bdCPers = new FichierCSVPersonnel("ProjetJava/Data/Personnel.csv");

		FichierExport fe = new FichierExport("ProjetJava/Data/Export.txt");
		
		Entreprise entreprise = new Entreprise();
		
		bdElem.chargerDonnee();
		bdCP.chargerDonnee();
		bdCPers.chargerDonnee();
		for (ChaineProduction cp : Entreprise.entreprise.getListeChaineProduction()) {
			System.out.print(cp);
			fe.Ecriture(cp);
		}
		
		Fenetre fen = new FenetreApplication();
	}

}
