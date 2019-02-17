package ihm;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;

import core.ChaineProduction;

public class JDetailChaineProduction extends JPanel{
	private ChaineProduction chaine;
	
	private JPanel information;
	private JPanel inputact;
	
	private JLabel nom;
	private JLabel code;
	private JLabel temps;
	private JLabel lvlAct;
	
	private JLabel nomL;
	private JLabel codeL;
	private JSpinner lvlActS;
	private JLabel tempsL;
	
	private JButton changerlvlAct;
	
	private JTableauTitre tabEntree;
	private JTableauTitre tabSortie;
	
	public JDetailChaineProduction(ChaineProduction cp) {
		this.chaine = cp;
		
		this.information = new JPanel();
		this.information.setLayout(new GridLayout(4,2));
		
		this.inputact = new JPanel();
		this.inputact.setLayout(new GridLayout(1, 2));
		
		this.setLayout(new GridLayout(6,1));
		
		
		this.nom = new JLabel("Nom : ");
		this.code = new JLabel("Code : ");
		this.lvlAct = new JLabel("niveau d'activité : ");
		this.temps = new JLabel (" Temps : ");
		
		this.nomL = new JLabel(this.chaine.getNom());
		this.codeL = new JLabel(this.chaine.getCodeChaineProduction());
		this.lvlActS = new JSpinner(); this.lvlActS.setValue(this.chaine.getNiveauActivitee());
		this.changerlvlAct = new JButton("Confirmer");
		this.changerlvlAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	chaine.attribuerNiveauActivite((int)lvlActS.getValue());
            }
        });
		
		this.tempsL = new JLabel (this.chaine.getTemps()+" heures");		
		
		this.tabEntree = new JTableauTitre("Element en entrée", new JTableau(new JModeleTabCPEE(this.chaine.getEntree())));
		this.tabSortie = new JTableauTitre("Element en sortie", new JTableau(new JModeleTabCPES(this.chaine.getSortie())));
		
		this.information.add(this.code);
		this.information.add(this.codeL);
		this.information.add(this.nom);
		this.information.add(this.nomL);
		this.information.add(this.temps);
		this.information.add(this.tempsL);
		this.information.add(this.lvlAct);
		this.inputact.add(this.lvlActS);
		this.inputact.add(this.changerlvlAct);
		
		this.information.add(this.inputact);
		this.add(this.information);
		this.add(this.tabEntree);
		this.add(this.tabSortie);
		
				
		
	}
	
	
	
	
	
}
