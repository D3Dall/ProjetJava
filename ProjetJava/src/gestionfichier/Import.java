package gestionfichier;

import java.io.File;

import core.Entreprise;
import erreurs.FichierCSVManquant;

public class Import {

	public static void Importer(File dir) throws FichierCSVManquant {
		File file[] = dir.listFiles();
		
		FichierCSVElement elem = null;
		FichierCSVChaineDeProduction chaine = null;
		FichierCSVPersonnel pers = null;
		
		for (File f : file) {
			if(!f.isDirectory()) {
				if(f.getName().toLowerCase().equals("elements.csv")) {
					elem = new FichierCSVElement(f);
				}else if(f.getName().toLowerCase().equals("chaines.csv")) {
					chaine = new FichierCSVChaineDeProduction(f);
				}else if(f.getName().toLowerCase().equals("personnel.csv")) {
					pers = new FichierCSVPersonnel(f);
				}
			}
		}
		
		if(elem!=null && chaine!=null && pers!=null) {
			Entreprise.entreprise=new Entreprise();
			elem.chargerDonnee();
			chaine.chargerDonnee();
			pers.chargerDonnee();
		}else {
			throw new FichierCSVManquant(elem!=null, chaine!=null, pers!=null);
		}
		
		
	}
	
	
	
}
