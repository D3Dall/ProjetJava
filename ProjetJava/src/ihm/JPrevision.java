package ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import core.Entreprise;

public class JPrevision extends JPanel{	
	
	private JModeleTabCP modeleTabCP;
	private JModeleTabE modeleTabE;
	private JTableau tableauCP;
	private JTableau tableauE;
	
	
	private JLabel titre;
	private JPanel contenue;
	
	private JTableauTitre resumeCP;
	private JTableauTitre resumeElement;
	
	public JPrevision() {
		
		Entreprise.entreprise.Prevision();
		
		this.setLayout(new BorderLayout());
		this.titre = new JLabel("Résultat de la prévision", JLabel.CENTER);
		this.add(titre, BorderLayout.NORTH);
		
		this.contenue = new JPanel();
		this.contenue.setLayout(new GridLayout(2,1));
		this.add(contenue, BorderLayout.CENTER);
		
		this.modeleTabCP = new JModeleTabCP();
		this.modeleTabE = new JModeleTabE();
		
		this.tableauCP = new JTableau(this.modeleTabCP);
		this.tableauE = new JTableau(this.modeleTabE);
		
		
		
		this.resumeCP = new JTableauTitre("Chaines de production de l'entreprise", this.tableauCP);
		this.resumeElement = new JTableauTitre("Elements de l'entreprise :", this.tableauE);
		
		this.contenue.add(this.resumeCP);
		this.contenue.add(this.resumeElement);
		
		
	}
	
	
	
	
	
	
	
}
