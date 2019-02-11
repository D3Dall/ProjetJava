package ihm;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import core.ChaineProduction;
import core.Element;

public class JTableau extends JPanel {

	protected JTable tableau;
	
	public JTableau(JTable tableau) {
		super();
		this.tableau = tableau;
		this.setLayout(new BorderLayout());
		this.add(this.tableau.getTableHeader(), BorderLayout.NORTH);
		this.add(this.tableau, BorderLayout.CENTER);
	}
	public JTableau(AbstractTableModel modele) {
		this(new JTable(modele));
	}
	
}
