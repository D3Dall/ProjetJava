package ihm;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import core.ChaineProduction;
import core.Entreprise;
import core.Personnel;
import core.Production;

public class JModeleTabEmploiDuTemps extends AbstractTableModel {
	private final String[][] datas;
	private final String[] entetes;
	
	public JModeleTabEmploiDuTemps(Entreprise entreprise) {
		this.entetes = new String[61];
		this.datas = new String[entreprise.getListePersonnel().size()][61];
		this.entetes[0] = "Personnel";
		for(int i = 1; i<60; i++) {
			this.entetes[i] = ""+(i-1);
		}
		for(int i = 0; i<entreprise.getListePersonnel().size(); i++) {
			Personnel pers = entreprise.getListePersonnel().get(i);
			this.datas[i][0] = pers.getCodePersonnel() + " " + pers.getNom() + " " + pers.getPrenom();
			for(ChaineProduction cp : entreprise.getListeChaineProduction()) {
				for(Production p : cp.getListeproduction()) {
					if(p.getListePersonnel().contains(pers)) {
						for (int l = p.getHeure(); l<p.getHeure()+cp.getTemps(); l++) {
							this.datas[i][l+1] = cp.getCodeChaineProduction() + " " + cp.getNom();
						}
					}
				}
			}
		}
	}

	@Override
	public int getColumnCount() {
		return this.entetes.length;
	}

	@Override
	public int getRowCount() {
		return this.datas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return datas[rowIndex][columnIndex];
	}
	
	public String getColumnName(int col){ 
      return this.entetes[col]; 
   }
	
	public String toString() {
		String retour = "";
		for(String s : this.entetes) {
			retour += s +  ";";
		}
		retour += "\n";
		for(int i = 0; i < this.datas.length; i++) {
			for(String s : this.datas[i]) {
				if(s == null) {
					retour += ";";
				}
				else {
					retour += s + ";";
				}
			}
			retour += "\n";
		}
		return retour;
	}
}
