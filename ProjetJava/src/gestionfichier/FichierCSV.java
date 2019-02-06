package gestionfichier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class FichierCSV{	
	protected File path;
	public FichierCSV(String path) {
		this.path=new File(path);
	}

	public ArrayList<String> Lecture(){
		ArrayList<String> ligne = new ArrayList<String>();
		Scanner sc;
		try {
			sc = new Scanner(path);
			sc.nextLine();
			while (sc.hasNextLine()) {
				ligne.add(sc.nextLine());
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return ligne;
	}
	
	public abstract void Charger();
	
	public abstract String[][] Ecriture();

	
	
}
