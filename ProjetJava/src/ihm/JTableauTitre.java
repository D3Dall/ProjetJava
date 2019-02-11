package ihm;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class JTableauTitre extends JPanel{
	private JLabel titre;
	private JTableau tableau;
	
	public JTableauTitre(String titre, JTableau tableau) {
		super();
		this.setLayout(new BorderLayout());
		this.titre = new JLabel(titre);
		this.tableau = tableau;
		
		this.add(this.titre, BorderLayout.NORTH);
		this.add(this.tableau, BorderLayout.CENTER);
	}
	public JTableauTitre(JTableau tableau) {
		this("", tableau);
	}
	
	public JTableau getTable() {
		return this.tableau;
	}
	
	
	
	
}
