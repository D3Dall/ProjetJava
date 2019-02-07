package ihm;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import core.ChaineProduction;

public class JDetailChaineProduction extends JPanel{
	private ChaineProduction chaine;
	
	private JPanel information;
	
	private JLabel nom;
	private JLabel code;
	private JLabel temps;
	private JLabel lvlAct;
	
	private JLabel nomL;
	private JLabel codeL;
	private JSpinner lvlActS;
	private JLabel tempsL;
	
	private JButton changerlvlAct;
	
	private JLabel titretabEntree;
	private JLabel titretabSortie;
	
	
	private JTableauElements tabEntree;
	private JTableauElements tabSortie;
	
	public JDetailChaineProduction(ChaineProduction cp) {
		this.chaine = cp;
		
		this.information = new JPanel();
		this.information.setLayout(new GridLayout(3,2));
		
		GridLayout l = new GridLayout(5,1);
		l.setVgap(0);
		l.setHgap(0);
		this.setLayout(l);
		
		
		this.nom = new JLabel("Nom : ");
		this.code = new JLabel("Code : ");
		this.lvlAct = new JLabel("niveau d'activit� : ");
		this.temps = new JLabel (" Temps : ");
		
		this.nomL = new JLabel(this.chaine.getNom());
		this.codeL = new JLabel(this.chaine.getCodeChaineProduction());
		this.lvlActS = new JSpinner(); this.lvlActS.setValue(this.chaine.getNiveauActivitee());
		this.changerlvlAct = new JButton("Confirmer");
		this.tempsL = new JLabel (this.chaine.getTemps()+"");
		
		this.titretabEntree = new JLabel("Element en entr�e : ");
		this.titretabSortie = new JLabel("Element en sortie : ");
		
		
		this.tabEntree = new JTableauElements(this.chaine.getElementsEnEntree());
		this.tabSortie = new JTableauElements(this.chaine.getElementsEnSortie());
		
		this.information.add(this.code);
		this.information.add(this.codeL);
		this.information.add(this.nom);
		this.information.add(this.nomL);
		this.information.add(this.temps);
		this.information.add(this.tempsL);
		
		this.add(this.information);
		this.add(this.titretabEntree);
		this.add(this.tabEntree);
		this.add(this.titretabSortie);
		this.add(this.tabSortie);
		
				
		
	}
	
	
	
	
	
}
