package ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import core.Element;
import core.Entreprise;

public class JPersonnel extends JPanel{

	private JLabel titre;
	private JPanel tableaupanel;
	private JTable tableau;
	private JModeleTabP modele;
	
	public JPersonnel() {
		this.setLayout(new BorderLayout());
		
		this.titre = new JLabel("Personnels de l'entreprise", JLabel.CENTER);
		
		this.modele = new JModeleTabP();
		this.tableau = new JTable(this.modele);
		
		this.tableaupanel = new JPanel();
		this.tableaupanel.setLayout(new BorderLayout());
		this.tableaupanel.add(this.tableau.getTableHeader(), BorderLayout.NORTH);
		this.tableaupanel.add(this.tableau, BorderLayout.CENTER);		
		
		this.add(this.titre, BorderLayout.NORTH);
		this.add(this.tableaupanel, BorderLayout.CENTER);
	}
	
	
	
	
	
}
