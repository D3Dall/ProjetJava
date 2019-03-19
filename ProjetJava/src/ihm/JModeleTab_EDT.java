package ihm;

import javax.swing.table.AbstractTableModel;

public class JModeleTab_EDT extends AbstractTableModel {
	private final String[][] datas;
	private final String[] entetes;
	
	public JModeleTab_EDT(String[][] datas, String[] entetes) {
		this.datas=datas;
		this.entetes=entetes;
		
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
