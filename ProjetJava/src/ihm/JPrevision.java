package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import core.Entreprise;
import core.Prevision;
import erreurs.ManqueDeStocksException;

public class JPrevision extends JPanel{	
	
	private JModeleTabCP modeleTabCP;
	private JModeleTabE modeleTabE;
	private JModeleTabEmploiDuTemps modeleTabEDT;
	private JModeleTabP modeleTabPersonnel;
	
	private JTableau tableauCP;
	private JTableau tableauE;
	private JTableau tableauEDT;
	private JTableau tableauPersonnel;
	
	
	private JLabel titre;
	private JPanel contenue;
	
	private JTableauTitre resumeCP;
	private JTableauTitre resumeElement;
	private JTableauTitre resumeEmploiDuTemps;
	private JTableauTitre resumePersonnel;
	
	public JPrevision() {
		
		try {
			Prevision.Prevision(Entreprise.entreprise);
		} catch (Exception e) {
			new FenetreErr(e.getMessage());
		}
				
		this.setLayout(new BorderLayout());
		this.titre = new JLabel("Résultat de la prévision", JLabel.CENTER);
		this.add(titre, BorderLayout.NORTH);
		
		this.contenue = new JPanel();
		this.contenue.setLayout(new GridLayout(4,1));
		this.add(contenue, BorderLayout.CENTER);
		
		this.modeleTabCP = new JModeleTabCP();
		this.modeleTabE = new JModeleTabE();
		this.modeleTabEDT = new JModeleTabEmploiDuTemps(Entreprise.entreprise);
		this.modeleTabPersonnel = new JModeleTabP();
		
		this.tableauCP = new JTableau(this.modeleTabCP);
		this.tableauE = new JTableau(this.modeleTabE);
		this.tableauEDT = new JTableau(this.modeleTabEDT);
		this.tableauPersonnel = new JTableau(this.modeleTabPersonnel);
		
		
		this.resumeCP = new JTableauTitre("Chaines de production de l'entreprise", this.tableauCP);
		this.resumeElement = new JTableauTitre("Elements de l'entreprise :", this.tableauE);
		this.resumeEmploiDuTemps = new JTableauTitre("Emploi du temps :", this.tableauEDT);
		this.resumePersonnel = new JTableauTitre("Personnels : ", this.tableauPersonnel);
		this.resumeEmploiDuTemps.setPreferredSize(new Dimension());
		
		this.contenue.add(this.resumeCP);
		this.contenue.add(this.resumeElement);
		this.contenue.add(this.resumeEmploiDuTemps);
		this.contenue.add(this.resumePersonnel);
	}
	
	
	
	
	
	
	
}
