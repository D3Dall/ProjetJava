package ihm;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import core.Element;
import core.Entreprise;

public class JModeleTabP extends AbstractTableModel {
	
	private final ArrayList<Element> listeElement;
	private final String[] entetes =  {"Code", "Nom", "Prenom", "Qualifié"};
	
	public JModeleTabP (ArrayList<Element> listeElement) {
		super();
		this.listeElement=listeElement;
	}
	
	public JModeleTabP () {
		this(Entreprise.entreprise.getListeElement());
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.entetes.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.listeElement.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex){
        case 0:
            return this.listeElement.get(rowIndex).getCodeElement();
        case 1:
            return this.listeElement.get(rowIndex).getNom();
        case 2:
            return this.listeElement.get(rowIndex).getStock();
        case 3:
        	return this.listeElement.get(rowIndex).getPrixAchat();
        case 4:
        	return this.listeElement.get(rowIndex).getPrixVente();
        default:
            return null; //Ne devrait jamais arriver
		}	
	}
	
	public String getColumnName(int col){ 
	      return this.entetes[col]; 
	   }
}
