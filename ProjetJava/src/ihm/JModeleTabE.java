package ihm;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import core.Element;
import core.Entreprise;

public class JModeleTabE extends AbstractTableModel {
	
	private final ArrayList<Element> listeElement;
	private final String[] entetes =  {"Code", "Nom", "Quantité en stock", "Demande", "%", "Prix à l'achat", "Prix à la vente"};
	
	public JModeleTabE (ArrayList<Element> listeElement) {
		super();
		this.listeElement=listeElement;
	}
	public JModeleTabE () {
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
        	return this.listeElement.get(rowIndex).getDemande() + this.listeElement.get(rowIndex).getStock().getUnitee();
        case 4:
        	if(this.listeElement.get(rowIndex).getDemande()==0) {
        		return "---";
        	}
        	return (int)(this.listeElement.get(rowIndex).getStock().getStock()*100/this.listeElement.get(rowIndex).getDemande());
        case 5:
        	return this.listeElement.get(rowIndex).getPrixAchat();
        case 6:
        	return this.listeElement.get(rowIndex).getPrixVente();
        default:
            return null; //Ne devrait jamais arriver
		}	
	}
	
	public String getColumnName(int col){ 
	      return this.entetes[col]; 
	   }
}
