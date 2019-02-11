package ihm;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import core.ChaineProduction;
import core.Element;
import core.Entreprise;
import core.Couple;

public abstract class JModeleTabCPE extends AbstractTableModel{
	private final ArrayList<Couple<Element, Float>> dic;
	private final String[] entetes =  {"Code", "Nom", "Quantité disponible", "Quantité necessaire"};
	
	public JModeleTabCPE (ArrayList<Couple<Element, Float>> dic) {
		super();
		this.dic = new ArrayList<Couple<Element, Float>>();
		this.dic.addAll(dic);
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.dic.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex){
        case 0:
            return this.dic.get(rowIndex).getObjeta().getCodeElement();
        case 1:
            return this.dic.get(rowIndex).getObjeta().getNom();
        case 2:
            return this.dic.get(rowIndex).getObjeta().getStock();
        case 3:
            return this.dic.get(rowIndex).getObjetb() + this.dic.get(rowIndex).getObjeta().getStock().getUnitee();
        default:
            return null; //Ne devrait jamais arriver
		}	
	}
	
	public void actualiser(ArrayList<Couple<Element, Float>> listechaine) {
		this.dic.clear();
		this.dic.addAll(listechaine);
		this.fireTableDataChanged();
	}	
	
}
