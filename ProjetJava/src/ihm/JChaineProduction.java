package ihm;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import core.ChaineProduction;
import core.Entreprise;

public class JChaineProduction extends JPanel{
	private JSplitPane pan;
	
	private JScrollPane scrollrec;
	private JScrollPane scrolltab;
	
	private JRechercheChaineProduction recpan;
	private JTableauChaineProduction tableaupanel;
	
	private JLabel titre;
	
	private FenetreApplication fenetre;
	
	public JChaineProduction(FenetreApplication fenetre) {
		this.fenetre=fenetre;
		this.recpan = new JRechercheChaineProduction(this);
		this.tableaupanel = new JTableauChaineProduction();
		this.scrollrec = new JScrollPane(this.recpan);
		this.scrolltab = new JScrollPane(this.tableaupanel);
		
		this.setLayout(new BorderLayout());
		
		this.titre = new JLabel("Liste des chaines de productions", JLabel.CENTER);
		this.pan = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.scrollrec, this.scrolltab);
		this.add(this.titre, BorderLayout.NORTH);
		this.add(this.pan, BorderLayout.CENTER);
	}
	
	public void actualiserListeChaineProduction(String code, String nom, int temps) {
		this.scrolltab.setViewportView(new JTableauChaineProduction(Entreprise.enteprise.chercherChaineDeProduction(code, nom, temps)));		
		this.scrolltab.repaint();
		this.scrolltab.revalidate();
	
	}
	
	public void changerPanel(ChaineProduction cp) {
		this.fenetre.setPanel(new JPanel());//A changer
	}
	
}
