package gestionfichier;

import java.io.IOException;

import core.Entreprise;
import ihm.JModeleTab_CP_EDT;
import ihm.JModeleTab_EDT;
import ihm.JModeleTab_P_EDT;

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

	public void ecriture(JModeleTab_EDT jModeleTab_EDT) throws IOException {
		this.wf.writeToFile(jModeleTab_EDT.toString());
	}

}
