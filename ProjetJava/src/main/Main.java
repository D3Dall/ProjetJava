package main;

import java.io.IOException;

import core.*;
import gestionfichier.*;
import ihm.*;

public class Main {

	public static void main(String[] args) throws IOException {
		FichierExport fe = new FichierExport("ProjetJava/Data/Export.txt");
		
		Entreprise entreprise = new Entreprise();
		Fenetre fen = new FenetreApplication();
	}

}
