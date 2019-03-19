package ihm;


import javax.swing.table.AbstractTableModel;

import core.ChaineProduction;
import core.Dysfonctionnement;
import core.Entreprise;
import core.Production;

public class JModeleTab_CP_EDT extends JModeleTab_EDT {
	
	public JModeleTab_CP_EDT(Entreprise entreprise) {
		super(constructionData(entreprise),constructionEntete());
		
		
	}
	private static String[] constructionEntete() {
		String[] entetes = new String[61];
		entetes[0] = "Chaines de production";
		
		for(int i = 1; i<60; i++) {
			entetes[i] = ""+(i-1);
		}
		return entetes;
	}
	
	private static String[][] constructionData(Entreprise entreprise){
		String[][] datas = new String[entreprise.getListeChaineProduction().size()][61];
		for(int i = 0; i<entreprise.getListeChaineProduction().size(); i++) {
			ChaineProduction chaine = entreprise.getListeChaineProduction().get(i);
			datas[i][0] = chaine.getCodeChaineProduction() + " " + chaine.getNom();
			for(Production p : chaine.getListeproduction()) {
				for (int l = p.getHeure(); l<p.getHeure()+chaine.getTemps(); l++) {
					datas[i][l+1] = chaine.getNiveauActivitee() + " ";
				}
			}
			System.out.println("il y a " + chaine.getListeDysfonctionnement().size() + " dysfonctionnements");
			for(Dysfonctionnement d : chaine.getListeDysfonctionnement()) {
				datas[i][d.getHeure()+1] = d.getCauseErreur();
			}
		}
		return datas;
	}
	
}
