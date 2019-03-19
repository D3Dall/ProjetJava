package ihm;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import core.ChaineProduction;
import core.Dysfonctionnement;
import core.Entreprise;
import core.Personnel;
import core.Production;

public class JModeleTab_P_EDT extends JModeleTab_EDT {
	
	public JModeleTab_P_EDT(Entreprise entreprise) {
		super(constructionData(entreprise), constructionEntete());
		
	}
	
	private static String[] constructionEntete() {
		String[] entetes = new String[61];
		entetes[0] = "Personnel";
		for(int i = 1; i<60; i++) {
			entetes[i] = ""+(i-1);
		}
		return entetes;
	}
	
	private static String[][] constructionData(Entreprise entreprise){
		String[][] datas = new String[entreprise.getListePersonnel().size()][61];
		for(int i = 0; i<entreprise.getListePersonnel().size(); i++) {
			Personnel pers = entreprise.getListePersonnel().get(i);
			datas[i][0] = pers.getCodePersonnel() + " " + pers.getNom() + " " + pers.getPrenom();
			for(ChaineProduction cp : entreprise.getListeChaineProduction()) {
				for(Production p : cp.getListeproduction()) {
					if(p.getListePersonnel().contains(pers)) {
						for (int l = p.getHeure(); l<p.getHeure()+cp.getTemps(); l++) {
							datas[i][l+1] = cp.getCodeChaineProduction() + " " + cp.getNom();
						}
					}
				}
			}
		}
		return datas;
	}
}
