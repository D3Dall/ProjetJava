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
	private JTableauElements tableaupanel;
	
	public JEtatStock() {
		this.setLayout(new BorderLayout());
		
		this.titre = new JLabel("Etat des stocks", JLabel.CENTER);
		
		this.tableaupanel = new JTableauElements();
		
		
		this.add(this.titre, BorderLayout.NORTH);
		this.add(this.tableaupanel, BorderLayout.CENTER);
	}
	
	
	
	
	
}
