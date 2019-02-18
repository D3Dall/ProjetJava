package gestionfichier;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import core.ChaineProduction;

public class FichierExport {
	protected String path;
	protected WriteFile wf;
	public FichierExport(String path) {
		this.path = path;
	}
	
	public void Ecriture(ChaineProduction cp) throws IOException {
		WriteFile writer = new WriteFile(this.path, true);
		writer.writeToFile(cp.toString());
	}

}
