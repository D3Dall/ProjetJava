package main;

import core.*;
import gestionfichier.*;
import ihm.*;

public class Main {

	public static void main(String[] args) {
		
		BaseDeDonnees bdElem = new FichierCSVElement("ProjetJava/Data/Elements.csv");
		BaseDeDonnees bdCP = new FichierCSVChaineDeProduction("ProjetJava/Data/Chaines.csv");
		
		Entreprise entreprise = new Entreprise();
		
		bdElem.Charger();
		bdCP.Charger();
		
		Fenetre fen = new FenetreApplication();
		

	}

}
