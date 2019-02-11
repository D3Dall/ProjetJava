package ihm;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import core.ChaineProduction;
import core.Entreprise;

public class JModeleTabCP extends AbstractTableModel{
	private final ArrayList<ChaineProduction> listeChaineProduction;
	private final String[] entetes =  {"Code", "Nom", "Temps", "niveau d'activité"};
	
	public JModeleTabCP (ArrayList<ChaineProduction> listeChaineProduction) {
		super();
		this.listeChaineProduction = new ArrayList<ChaineProduction>();
		this.listeChaineProduction.addAll(listeChaineProduction);
	}
	public JModeleTabCP () {
		this(Entreprise.entreprise.getListeChaineProduction());
	} 

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.entetes.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.listeChaineProduction.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex){
        case 0:
            return this.listeChaineProduction.get(rowIndex).getCodeChaineProduction();
        case 1:
            return this.listeChaineProduction.get(rowIndex).getNom();
        case 2:
            return this.listeChaineProduction.get(rowIndex).getTemps();
        case 3:
            return this.listeChaineProduction.get(rowIndex).getNiveauActivitee();
        default:
            return null; //Ne devrait jamais arriver
		}	
	}
	
	public void actualiser(ArrayList<ChaineProduction> listechaine) {
		this.listeChaineProduction.clear();
		this.listeChaineProduction.addAll(listechaine);
		this.fireTableDataChanged();
	}
	
	public ChaineProduction getChaine(int index) {
		return this.listeChaineProduction.get(index);
	}
	
	public String getColumnName(int col){ 
	      return this.entetes[col]; 
   }
	
	
}
