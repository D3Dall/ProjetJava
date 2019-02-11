package ihm;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import core.ChaineProduction;
import core.Entreprise;

public class JChaineProduction extends JPanel{
	private JSplitPane pan;
	
	private JScrollPane scrollrec;
	private JScrollPane scrolltab;
	
	private JRechercheChaineProduction recpan;
	
	private JModeleTabCP modele;
	private JTable tableau;
	private JTableau paneltableau;	
	
	private JLabel titre;
	
	private FenetreApplication fenetre;
	
	public JChaineProduction(FenetreApplication fenetre) {
		this.fenetre=fenetre;
		this.recpan = new JRechercheChaineProduction(this);
		
		this.modele = new JModeleTabCP();
		this.tableau = new JTable(this.modele);
		this.paneltableau = new JTableau(this.tableau);
		
		this.scrollrec = new JScrollPane(this.recpan);
		this.scrolltab = new JScrollPane(this.paneltableau);
		
		this.setLayout(new BorderLayout());
		
		this.titre = new JLabel("Liste des chaines de productions", JLabel.CENTER);
		this.pan = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.scrollrec, this.scrolltab);
		this.add(this.titre, BorderLayout.NORTH);
		this.add(this.pan, BorderLayout.CENTER);
	}
	
	public void actualiserListeChaineProduction(String code, String nom, int temps) {
		this.modele.actualiser(Entreprise.entreprise.chercherChaineDeProduction(code, nom, temps));
		this.repaint();
		this.revalidate();
	}
	
	public void acceder() {
		this.fenetre.setPanel(new JDetailChaineProduction(this.modele.getChaine(this.tableau.getSelectedRow())));
	}
	
}
