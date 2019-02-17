package ihm;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import core.Element;
import core.Entreprise;
import core.Personnel;

public class JModeleTabP extends AbstractTableModel {
	
	private final ArrayList<Personnel> listePersonnel;
	private final String[] entetes =  {"Code", "Nom", "Prenom","Contrat", "Temps de travail effectif", "%", "Qualifié"};
	
	public JModeleTabP (ArrayList<Personnel> listeElement) {
		super();
		this.listePersonnel=listeElement;
	}
	
	public JModeleTabP () {
		this(Entreprise.entreprise.getListePersonnel());
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.entetes.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.listePersonnel.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex){
        case 0:
            return this.listePersonnel.get(rowIndex).getCodePersonnel();
        case 1:
            return this.listePersonnel.get(rowIndex).getNom();
        case 2:
            return this.listePersonnel.get(rowIndex).getPrenom();
        case 3:
        	return this.listePersonnel.get(rowIndex).getTempsTravailMAX();
        case 4:
        	return this.listePersonnel.get(rowIndex).getTempsTravail();
        case 5:
        	return this.listePersonnel.get(rowIndex).getTempsTravail()*100/this.listePersonnel.get(rowIndex).getTempsTravailMAX();
        case 6:
        	if(this.listePersonnel.get(rowIndex).getClass().getSimpleName().equals("Personnel_Non_Qualifie")){
        		return "Oui";
        	}else {
        		return "Non";
        	}
        default:
            return null; //Ne devrait jamais arriver
		}	
	}
	
	public String getColumnName(int col){ 
	      return this.entetes[col]; 
	   }
}
