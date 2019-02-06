package gestionfichier;

import java.util.ArrayList;

public interface BaseDeDonnees {

	public ArrayList<String> Lecture();
	
	public abstract void Charger();
	
	public void Ecriture();
	
}
