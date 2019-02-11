package ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import core.Element;
import core.Entreprise;

public class JEtatStock extends JPanel{

	private JLabel titre;
	private JPanel tableaupanel;
	private JTable tableau;
	private JModeleTabE modele;
	
	public JEtatStock() {
		this.setLayout(new BorderLayout());
		
		this.titre = new JLabel("Etat des stocks", JLabel.CENTER);
		
		this.modele = new JModeleTabE();
		this.tableau = new JTable(this.modele);
		
		this.tableaupanel = new JPanel();
		this.tableaupanel.setLayout(new BorderLayout());
		this.tableaupanel.add(this.tableau.getTableHeader(), BorderLayout.NORTH);
		this.tableaupanel.add(this.tableau, BorderLayout.CENTER);		
		
		this.add(this.titre, BorderLayout.NORTH);
		this.add(this.tableaupanel, BorderLayout.CENTER);
	}
	
	
	
	
	
}
