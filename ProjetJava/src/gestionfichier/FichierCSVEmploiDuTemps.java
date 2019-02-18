package gestionfichier;

import java.io.IOException;

import core.Entreprise;
import ihm.JModeleTabEmploiDuTemps;

public class FichierCSVEmploiDuTemps extends FichierCSV {
	protected WriteFile wf;
	
	public FichierCSVEmploiDuTemps(String path) {
		super(path);
		this.wf=new WriteFile(path);
	}

	@Override
	public void chargerDonnee() {
		// TODO Auto-generated method stub
		
	}

	public void ecriture(JModeleTabEmploiDuTemps jent) throws IOException {
		this.wf.writeToFile(jent.toString());
	}

}
