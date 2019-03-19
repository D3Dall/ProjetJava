package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import core.Entreprise;
import core.Prevision;
import erreurs.ManqueDeStocksException;
import gestionfichier.FichierCSVEmploiDuTemps;

public class JPrevision extends JPanel{	
	
	private JModeleTabCP modeleTabCP;
	private JModeleTabE modeleTabE;
	private JModeleTab_P_EDT modeleTabEDT;
	private JModeleTabP modeleTabPersonnel;
	private JModeleTab_CP_EDT modeleCPEDT;
	
	private JTableau tableauCP;
	private JTableau tableauE;
	private JTableau tableauEDT;
	private JTableau tableauPersonnel;
	private JTableau tableauCPEDT;
	
	
	private JLabel titre;
	private JPanel contenue;
	
	private JTableauTitre resumeCP;
	private JTableauTitre resumeElement;
	private JTableauTitre resumeEmploiDuTemps;
	private JTableauTitre resumePersonnel;
	private JTableauTitre resumeCPEDT;
	
	public JPrevision() {
					
		this.setLayout(new BorderLayout());
		this.titre = new JLabel("Résultat de la prévision", JLabel.CENTER);
		this.add(titre, BorderLayout.NORTH);
		
		this.contenue = new JPanel();
		this.contenue.setLayout(new GridLayout(5,1));
		this.add(contenue, BorderLayout.CENTER);
		
		this.modeleTabCP = new JModeleTabCP();
		this.modeleTabE = new JModeleTabE();
		this.modeleTabEDT = new JModeleTab_P_EDT(Entreprise.entreprise);
		this.modeleTabPersonnel = new JModeleTabP();
		this.modeleCPEDT = new JModeleTab_CP_EDT(Entreprise.entreprise);
		
		this.tableauCP = new JTableau(this.modeleTabCP);
		this.tableauE = new JTableau(this.modeleTabE);
		this.tableauEDT = new JTableau(this.modeleTabEDT);
		this.tableauPersonnel = new JTableau(this.modeleTabPersonnel);
		this.tableauCPEDT = new JTableau(this.modeleCPEDT);
		
		
		this.resumeCP = new JTableauTitre("Chaines de production de l'entreprise", this.tableauCP);
		this.resumeElement = new JTableauTitre("Elements de l'entreprise :", this.tableauE);
		this.resumeEmploiDuTemps = new JTableauTitre("Emploi du temps :", this.tableauEDT);
		this.resumePersonnel = new JTableauTitre("Personnels : ", this.tableauPersonnel);
		this.resumeCPEDT = new JTableauTitre("Activité des chaines de production : ", this.tableauCPEDT);
		this.resumeEmploiDuTemps.setPreferredSize(new Dimension());
		this.resumeCPEDT.setPreferredSize(new Dimension());
		
		
		this.contenue.add(this.resumeCP);
		this.contenue.add(this.resumeElement);
		this.contenue.add(this.resumePersonnel);
		this.contenue.add(this.resumeEmploiDuTemps);
		this.contenue.add(this.resumeCPEDT);
		
		
	}

	public JModeleTab_P_EDT getModeleTabPEDT() {
		return modeleTabEDT;
	}

	public JModeleTab_CP_EDT getModeleCPEDT() {
		return modeleCPEDT;
	}
	
	
	
	
	
	
}
