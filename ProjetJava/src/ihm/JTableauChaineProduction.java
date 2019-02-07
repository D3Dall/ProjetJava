package ihm;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JTable;

import core.ChaineProduction;
import core.Entreprise;

public class JTableauChaineProduction extends JTableau{
	
	private static String titre[] = {"Code", "Nom", "Temps"};
	private ChaineProduction[] datas;
		
	public JTableauChaineProduction() {
		this(Entreprise.entreprise.getListeChaineProduction());
	}
	public JTableauChaineProduction(ArrayList<ChaineProduction> listechaineR) {
		this.construireData(listechaineR);
		this.tableau = new JTable(this.construireData(listechaineR), JTableauChaineProduction.titre);
		
		this.setLayout(new BorderLayout());
		this.add(this.tableau.getTableHeader(), BorderLayout.NORTH);
		this.add(this.tableau, BorderLayout.CENTER);
	}	
	
	public Object[][] construireData(ArrayList<ChaineProduction> listechaineR) {
		Object[][] tab = new Object[listechaineR.size()][3];
		this.datas = new ChaineProduction[listechaineR.size()];
		for (int i= 0;i<listechaineR.size(); i++) {
			ChaineProduction cp = listechaineR.get(i);
			this.datas[i]= cp;
			tab[i][0] = cp.getCodeChaineProduction();
			tab[i][1] = cp.getNom();
			tab[i][2] = cp.getTemps() + " minutes";
		}
		return tab;
	}
	
	public ChaineProduction getData(int indice) {
		return this.datas[indice];
	}
	
}
