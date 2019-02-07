package ihm;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JTable;

import core.ChaineProduction;
import core.Entreprise;

public class JTableauChaineProduction extends JTableau{
	
	public JTableauChaineProduction() {
		String titre[] = {"Code", "Nom", "Temps"};
		this.tableau = new JTable(this.construireData(), titre);
		
		this.setLayout(new BorderLayout());
		this.add(this.tableau.getTableHeader(), BorderLayout.NORTH);
		this.add(this.tableau, BorderLayout.CENTER);
	}
	public JTableauChaineProduction(ArrayList<ChaineProduction> listechaineR) {
		String titre[] = {"Code", "Nom", "Temps"};
		this.tableau = new JTable(this.construireData(listechaineR), titre);
		
		this.setLayout(new BorderLayout());
		this.add(this.tableau.getTableHeader(), BorderLayout.NORTH);
		this.add(this.tableau, BorderLayout.CENTER);
	}
	
	public Object[][] construireData() {
		ArrayList<ChaineProduction> listechaine = Entreprise.enteprise.getListeChaineProduction();
		Object [][] tab = new Object[listechaine.size()][3];
		for (int i= 0;i<listechaine.size(); i++) {
			ChaineProduction cp = listechaine.get(i);
			tab[i][0] = cp.getCodeChaineProduction();
			tab[i][1] = cp.getNom();
			tab[i][2] = cp.getTemps() + " minutes";
		}
		return tab;
	}
	public Object[][] construireData(ArrayList<ChaineProduction> listechaineR) {
		Object [][] tab = new Object[listechaineR.size()][3];
		for (int i= 0;i<listechaineR.size(); i++) {
			ChaineProduction cp = listechaineR.get(i);
			tab[i][0] = cp.getCodeChaineProduction();
			tab[i][1] = cp.getNom();
			tab[i][2] = cp.getTemps() + " minutes";
		}
		return tab;
	}
	
}
