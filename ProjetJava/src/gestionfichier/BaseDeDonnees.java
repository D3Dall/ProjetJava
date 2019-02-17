package gestionfichier;

import java.util.ArrayList;

public interface BaseDeDonnees {

	public ArrayList<String> lecture();
	
	public abstract void chargerDonnee();
	
	public void ecriture();
	
}
