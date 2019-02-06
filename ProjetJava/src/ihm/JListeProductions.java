package ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import core.ChaineProduction;
import core.Element;
import core.Entreprise;

public class JListeProductions extends JPanel {
	public ArrayList<PanelChaineProd> liste_chaine;
	
	public JLabel titre;
	
	
	public JListeProductions (FenetreApplication fenetre) {
				
		this.titre = new JLabel("Liste des chaines de productions");
		this.add(this.titre);
		
		ArrayList<ChaineProduction> listechaine = Entreprise.enteprise.getListeChaineProduction();
		this.liste_chaine = new ArrayList<PanelChaineProd> ();
		for (ChaineProduction cp : listechaine) {
			PanelChaineProd panel = new PanelChaineProd(cp, fenetre);
			this.liste_chaine.add(panel);
			this.add(panel, BorderLayout.CENTER);
		}
		
		
	}
	
	
	
	
	
}
